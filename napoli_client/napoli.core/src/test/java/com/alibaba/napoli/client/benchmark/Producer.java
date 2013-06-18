/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.napoli.client.benchmark;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

/**
 * @author James Strachan
 * @version $Revision: 149160 $
 */
public class Producer extends BenchmarkSupport {

    int         loops       = -1;

    private int messageSize = 1000;

    public Producer() {
    }

    public static void main(String[] args) {

        Producer tool = new Producer();
        if (args.length > 0) {
            tool.setUrl(args[0]);
        }
        if (args.length > 1) {
            tool.setTopic(parseBoolean(args[1]));
        }
        if (args.length > 2) {
            tool.setDurable(parseBoolean(args[2]));
        }

        if (args.length > 3) {
            tool.setConnectionCount(Integer.parseInt(args[3]));
        }

        if (args.length > 4) {
            tool.setMessageSize(Integer.parseInt(args[4]));
        }

        if (args.length > 5) {
            tool.setLoops(Integer.parseInt(args[5]));
        }

        if (args.length > 6) {
            tool.setConcurrent(Integer.parseInt(args[6]));
        }

        try {
            tool.setTimerLoop(true);
            tool.run();
        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }

    public void run() throws Exception {
        start();
        publish();
    }

    // Properties
    // -------------------------------------------------------------------------
    public int getMessageSize() {
        return messageSize;
    }

    public void setMessageSize(int messageSize) {
        this.messageSize = messageSize;
    }

    // Implementation methods
    // -------------------------------------------------------------------------

    protected void publish() throws Exception {
        final String text = getMessage();

        System.out.println("Publishing to: " + subjects.length + " subject(s)");

        for (int i = 0; i < subjects.length; i++) {
            final String subject = subjects[i];
            for (int j = 0; j < getConcurrent(); j++) {
                Thread thread = new Thread() {
                    public void run() {
                        try {
                            publish(text, subject);
                        } catch (JMSException e) {
                            System.out.println("Caught: " + e);
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
            }
        }

    }

    protected String getMessage() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < messageSize; i++) {
            char ch = 'X';
            buffer.append(ch);
        }

        return buffer.toString();
    }

    protected void publish(String text, String subject) throws JMSException {

        Session session = createSession();

        Destination destination = createDestination(session, subject);

        MessageProducer publisher = session.createProducer(destination);

        if (isDurable()) {
            publisher.setDeliveryMode(DeliveryMode.PERSISTENT);
        } else {
            publisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        }

        System.out.println("Starting publisher on : " + destination + " of type: "
                + destination.getClass().getName());
        System.out.println("Message length: " + text.length());
        try {
            if (loops <= 0) {
                while (true) {
                    publishLoop(session, publisher, text);
                }
            } else {
                for (int i = 0; i < loops; i++) {
                    publishLoop(session, publisher, text);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    protected void publishLoop(Session session, MessageProducer publisher, String text)
            throws JMSException {

        Message message = session.createTextMessage(text);
        // BytesMessage bytesMessage = session.createBytesMessage();

        publisher.send(message);
        if (session.getTransacted()) {
            //if ((counter % 500) == 0) {
                session.commit();
            //}
        }
        count(1);
    }

    protected String loadFile(String file) throws IOException {
        System.out.println("Loading file: " + file);

        StringBuffer buffer = new StringBuffer();
        BufferedReader in = new BufferedReader(new FileReader(file));
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            buffer.append(line);
            buffer.append(File.separator);
        }
        in.close();
        return buffer.toString();
    }

    public int getLoops() {
        return loops;
    }

    public void setLoops(int loops) {
        this.loops = loops;
    }
}
