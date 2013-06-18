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
 * Authors:
 *   wuhua <wq163@163.com> , boyan <killme2008@gmail.com>
 */
package com.alibaba.napoli.metamorphosis.utils.codec;

import java.io.IOException;

/**
 * 
 * @author wuxin
 * @since 1.0, 2009-10-20 上午09:41:40
 */
public interface Serializer {
	/**
	 * 将指定的对象进行序列化.
	 * 
	 * @param obj - 需要序列化的对象
	 * @return    - 返回对象序列化后的字节码
	 */
	public byte[] encodeObject(Object obj)throws IOException;
}