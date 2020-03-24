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

package com.aliyun.iot.api.sdk.openapi;

import com.aliyun.iot.client.IotClient;
import com.aliyun.iot.util.LogUtil;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.http.MethodType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AbstractManager {

    private static DefaultAcsClient client;

    /**
     * 初始化客户端
     *  用户的 ak
     *  用户的 Secret
     */
    static {
        client = IotClient.getClient();
    }

    /**
     * 接口请求地址 action 接口名称 domain 线上地址 version  接口版本
     */
    public static CommonRequest executeTests(String action) {
        CommonRequest request = new CommonRequest();
        request.setDomain(IotClient.getDomain());
        request.setMethod(MethodType.POST);
        request.setVersion(IotClient.getVersion());
        request.setAction(action);

        return request;
    }

    //@SuppressWarnings({ "unchecked", "rawtypes" })
    public static AcsResponse executeTest(RpcAcsRequest request) {
        AcsResponse response = null;
        try {
            response = client.getAcsResponse(request);
        } catch (Exception e) {
            LogUtil.print("执行失败：e:" + e.getMessage());
        }
        return response;
    }

    public static DefaultAcsClient getClient() {
        return client;
    }

    public void showResult(Object response) {
        if (response == null) {
            System.err.println("null response can not be invocked.");
            return;
        }
        Class<?> objClass = response.getClass();

        try {
            Method method1 = objClass.getDeclaredMethod("getRequestId");
            String requestId = (String)method1.invoke(response);
            System.out.println("requestId: " + requestId);

            Method method2 = objClass.getDeclaredMethod("getCode");
            String code = (String)method2.invoke(response);
            System.out.println("code: " + code);

            Method method3 = objClass.getDeclaredMethod("getErrorMessage");
            String errorMessage = (String)method3.invoke(response);
            System.out.println("errorMessage: " + errorMessage);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
