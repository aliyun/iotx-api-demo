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

package com.aliyun.iot.client;

import java.util.Properties;

import com.aliyun.iot.util.LogUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class IotClient {
	private static String accessKeyID;
	private static String accessKeySecret;
	private static String regionId;
    private static String domain;
    private static String version;



    public static DefaultAcsClient getClient() {
		DefaultAcsClient client = null;

		Properties prop = new Properties();
		try {
			prop.load(Object.class.getResourceAsStream("/config.properties"));
			accessKeyID = prop.getProperty("user.accessKeyID");
			accessKeySecret = prop.getProperty("user.accessKeySecret");
			regionId = prop.getProperty("iot.regionId");
            domain = prop.getProperty("iot.domain");
            version = prop.getProperty("iot.version");

			IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyID, accessKeySecret);
			DefaultProfile.addEndpoint(regionId, regionId, prop.getProperty("iot.productCode"),
					prop.getProperty("iot.domain"));
			// 初始化client
			client = new DefaultAcsClient(profile);

		} catch (Exception e) {
			LogUtil.print("初始化client失败！exception:" + e.getMessage());
		}

		return client;
	}

    public static String getRegionId() {
        return regionId;
    }

    public static void setRegionId(String regionId) {
        IotClient.regionId = regionId;
    }

    public static String getDomain() {
        return domain;
    }

    public static void setDomain(String domain) {
        IotClient.domain = domain;
    }

    public static String getVersion() {
        return version;
    }

    public static void setVersion(String version) {
        IotClient.version = version;
    }
}
