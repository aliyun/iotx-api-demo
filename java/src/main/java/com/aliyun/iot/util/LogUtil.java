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

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @version $Id: LogUtil.java,v 0.1 2016年6月2日 下午4:39:18  Exp $
 */
public class LogUtil {
    
    /** 是否打印日志 **/
    public static boolean   showLog      = true;

    static SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

    /**
     * 简单日志打印
     * 
     * @param msg
     */
    public static void print(String msg) {
        if (showLog) {

            String source = null;

            try {
                StackTraceElement st = Thread.currentThread().getStackTrace()[2];
                source = "[" + st.getFileName() + "] - " + st.getMethodName() + "("
                         + st.getLineNumber() + ")";
            } catch (Exception e) {
            }

            System.out.println(fm.format(new Date()) + " - " + source + ":" + msg);
        }
    }
    
    public static void error(String msg){
    	 if (showLog) {

             String source = null;

             try {
                 StackTraceElement st = Thread.currentThread().getStackTrace()[2];
                 source = "[" + st.getFileName() + "] - " + st.getMethodName() + "("
                          + st.getLineNumber() + ")";
             } catch (Exception e) {
             }

             System.err.println(fm.format(new Date()) + " - " + source + ":" + msg);
         }
    }

}
