/*
 * Copyright (c) 2014-2016 Alibaba Group. All rights reserved.
 * License-Identifier: Apache-2.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.aliyun.iot.util;


import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServiceUtil {
	private static String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	/**
	 * 随机生成产品名称
	 * @return
	 */
	public static String productNameGenerator() {
		int length = 4;
		Random random = new Random();
	    StringBuffer sb = new StringBuffer();
	    sb.append("product");
	    for (int i = 0; i < length; i++) {
	        int number = random.nextInt(base.length());
	        sb.append(base.charAt(number));
	    }
	    return sb.toString();
	}

	/**
	 * 随机生成分组名称
	 * @return
	 */
	public static String groupNameGenerator() {
		int length = 4;
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		sb.append("group");
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	/**
	 * 随机生成设备名称list
	 * @param length
	 * @return
	 */
	public static List<String> deviceNameGenerator(int length){
		List<String> nameList = new ArrayList<String>(length);
		for (int i = 0; i < length; i++){
			int l = 4;
			Random random = new Random();     
		    StringBuffer sb = new StringBuffer(); 
		    sb.append("device_");
		    for (int j = 0; j < l; j++) {     
		        int number = random.nextInt(base.length());     
		        sb.append(base.charAt(number));     
		    }
		    nameList.add(sb.toString());
		}
		return nameList;
	}


}
