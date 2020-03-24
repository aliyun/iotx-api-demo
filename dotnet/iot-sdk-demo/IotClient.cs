using System;
using System.Collections;
using Aliyun.Acs.Core;
using Aliyun.Acs.Core.Exceptions;
using Aliyun.Acs.Core.Profile;

namespace Demo
{
    internal class IotClient
    {
        public IotClient()
        {
        }

        public static DefaultAcsClient GetClient()
        {
            String regionId = Environment.GetEnvironmentVariable("IOT_TEST_ENDPOINT");
            if (string.IsNullOrEmpty(regionId))
            {
                regionId = Config.RegionId;
            }
            String accessKeyId = Environment.GetEnvironmentVariable("IOT_TEST_ACCESS_KEY_ID");
            if (string.IsNullOrEmpty(accessKeyId))
            {
                accessKeyId = Config.AccessKeyId;
            }
            String secret = Environment.GetEnvironmentVariable("IOT_TEST_ACCESS_KEY_SECRET");
            if (string.IsNullOrEmpty(secret))
            {
                secret = Config.AccessKeySecret;
            }

            IClientProfile clientProfile = DefaultProfile.GetProfile(regionId, accessKeyId, secret);
            DefaultAcsClient acsClient = new DefaultAcsClient(clientProfile);
            return acsClient;
        }
    }
}
