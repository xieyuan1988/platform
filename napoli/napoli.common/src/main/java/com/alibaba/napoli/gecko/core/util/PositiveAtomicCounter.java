/*
 * (C) 2007-2012 Alibaba Group Holding Limited.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.napoli.gecko.core.util;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * 正数的原子递增器，主要用于实现轮询
 * 
 * @author apple
 * 
 */
public class PositiveAtomicCounter {
    private final AtomicInteger atom;
    private static final int mask = 0x7FFFFFFF;


    public PositiveAtomicCounter() {
        atom = new AtomicInteger(0);
    }


    public final int incrementAndGet() {
        final int rt = atom.incrementAndGet();
        return rt & mask;
    }


    public int intValue() {
        return atom.intValue();
    }

}