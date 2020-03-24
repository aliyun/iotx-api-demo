using Aliyun.Acs.Core;
using Aliyun.Acs.Core.Exceptions;
using Aliyun.Acs.Core.Profile;
using Aliyun.Acs.Iot.Model.V20180120;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TopicManagement
{
    class TopicManagement
    {

        public void TestQueryProductTopic()
        {

            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            String productKey = "<productKey>";

            QueryProductTopicRequest request = new QueryProductTopicRequest();
            request.ProductKey = productKey;

            QueryProductTopicResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Query Product Topic: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
            List<QueryProductTopicResponse.QueryProductTopic_ProductTopicInfo> ProductTopicInfos = response.Data;
            for (int i = 0; i < ProductTopicInfos.Count; i += 1)
            {
                QueryProductTopicResponse.QueryProductTopic_ProductTopicInfo ProductTopicInfo = ProductTopicInfos[i];

                Console.WriteLine("Product Topic Id: " + ProductTopicInfo.Id);
                Console.WriteLine("Product Topic ProductKey: " + ProductTopicInfo.ProductKey);
                Console.WriteLine("Product Topic TopicShortName: " + ProductTopicInfo.TopicShortName);
                Console.WriteLine("Product Topic Operation: " + ProductTopicInfo.Operation);
                Console.WriteLine("Product Topic Desc: " + ProductTopicInfo.Desc);
                Console.WriteLine();
            }
        }


        //创建Topic类
        public void TestCreateProductTopic()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            String productKey = "<productKey>";

            CreateProductTopicRequest request = new CreateProductTopicRequest();
            request.ProductKey = productKey;
            request.TopicShortName = "device0826/user/logout";
            request.Operation = "PUB";
            request.Desc = "device offline";


            CreateProductTopicResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Create Product Topic: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
            long topicId = (long)response.TopicId;
            Console.WriteLine("Create Product Topic Id: " + topicId);
        }


        //更新Topic类信息
        public void TestUpdateProductTopic()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            String TopicId = "5281467";

            UpdateProductTopicRequest request = new UpdateProductTopicRequest();
            request.TopicId = TopicId;
            request.TopicShortName = "device0826/user/login";
            request.Operation = "ALL";
            request.Desc = "operation change pub to all";

            UpdateProductTopicResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Update Product Topic: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
        }


        //删除Topic类
        public void TestDeleteProductTopic()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            String TopicId = "5281467";

            DeleteProductTopicRequest request = new DeleteProductTopicRequest();
            request.TopicId = TopicId;

            DeleteProductTopicResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Delete Product Topic: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
        }


        //新建Topic与Topic之间的路由关系
        public void TestCreateTopicRouteTable()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            String SrcTopic = "/<productKey>/secondDevice/user/get";

            CreateTopicRouteTableRequest request = new CreateTopicRouteTableRequest();
            request.SrcTopic = SrcTopic;
            List<String> DstTopics = new List<string>();
            DstTopics.Add("/<productKey>/device_0821_148/user/update");

            request.DstTopics = DstTopics;

            CreateTopicRouteTableResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Create Topic Route Table: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
            bool isAllSucceed = true;
            if (response.IsAllSucceed != null)
            {
                isAllSucceed = (bool)response.IsAllSucceed;
            }

            List<Dictionary<string, string>> failedTopics = response.FailureTopics;

            Console.WriteLine("isAllSucceed: " + isAllSucceed + ", failedTopicCount: " + failedTopics.Count);
        }


        //获取某一Topic的目标路由列表
        public void TestQueryTopicRouteTable()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            String SrcTopic = "/<productKey>/secondDevice/user/get";

            QueryTopicRouteTableRequest request = new QueryTopicRouteTableRequest();
            request.Topic = SrcTopic;

            QueryTopicRouteTableResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Query Topic Route Table: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }

            List<String> dstTopics = response.DstTopics;
            for (int i = 0; i < dstTopics.Count; i += 1)
            {
                Console.WriteLine("Dsttopic: " + dstTopics[i]);
            }
        }


        //获取某一topic的源Topic，即反向路由
        public void TestQueryTopicReverseRouteTable()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            String DstTopic = "/<productKey>/device_0821_148/user/update";

            QueryTopicReverseRouteTableRequest request = new QueryTopicReverseRouteTableRequest();
            request.Topic = DstTopic;

            QueryTopicReverseRouteTableResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Query Topic Reverse Route Table: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }

            List<String> SrcTopics = response.SrcTopics;
            for (int i = 0; i < SrcTopics.Count; i += 1)
            {
                Console.WriteLine("SrcTopic: " + SrcTopics[i]);
            }
        }


        //删除消息路由表
        public void TestDeleteTopicRouteTable()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            String SrcTopic = "/<productKey>/device_0821_148/user/update";

            DeleteTopicRouteTableRequest request = new DeleteTopicRouteTableRequest();
            request.SrcTopic = SrcTopic;

            List<String> DstTopics = new List<string>();
            DstTopics.Add("/a1HVMkh4YlK/device_0821_148/user/update");

            request.DstTopics = DstTopics;

            DeleteTopicRouteTableResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Query Topic Reverse Route Table: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }

            bool isAllSucceed = true;
            if (response.IsAllSucceed != null)
            {
                isAllSucceed = (bool)response.IsAllSucceed;
            }

            List<String> FailureTopics = response.FailureTopics;

            Console.WriteLine("isAllSucceed: " + isAllSucceed + ", failedTopicCount: " + FailureTopics.Count);
        }


        //向某一topic发布消息，注意topic，消息内容要提前进行base64编码
        public void TestPub()
        {
            DefaultAcsClient client = Demo.IotClient.GetClient();

            PubRequest request = new PubRequest();
            request.ProductKey = "<productKey>";
            request.TopicFullName = "/" + request.ProductKey + "/TZCK/get";

            byte[] payload = Encoding.Default.GetBytes("Hello World.");
            String payloadStr = Convert.ToBase64String(payload);
            request.MessageContent = payloadStr;
            request.Qos = 0;

            PubResponse response = client.GetAcsResponse(request);
            Console.WriteLine("publish message result: " + response.Success);
            Console.WriteLine(response.ErrorMessage);
        }



        //发布广播消息，注意topic要以/broadcast开头
        public void TestPubBroadcast()
        {
            DefaultAcsClient client = Demo.IotClient.GetClient();

            PubBroadcastRequest request = new PubBroadcastRequest();
            request.ProductKey = "<productKey>";
            request.TopicFullName = "/broadcast/" + request.ProductKey + "/<XXXXXXX[deviceName]>";

            byte[] payload = Encoding.Default.GetBytes("Hello World.");
            String payloadStr = Convert.ToBase64String(payload);
            request.MessageContent = payloadStr;

            PubBroadcastResponse response = client.GetAcsResponse(request);
            Console.WriteLine("publish broadcast message result: " + response.Success);
            Console.WriteLine(response.ErrorMessage);
        }


        public void TestRrpc()
        {
            DefaultAcsClient client = Demo.IotClient.GetClient();

            RRpcRequest request = new RRpcRequest();
            request.ProductKey = "<productKey>";
            request.DeviceName = "<deviceName>";
            request.Timeout = 1000;

            byte[] payload = Encoding.Default.GetBytes("Hello World.");
            String payloadStr = Convert.ToBase64String(payload);
            request.RequestBase64Byte = payloadStr;

            RRpcResponse response = client.GetAcsResponse(request);
            Console.WriteLine("publish rrpc message result: " + response.Success);
            Console.WriteLine(response.ErrorMessage);
            Console.WriteLine("message ID: " + response.MessageId);
            Console.WriteLine("rrpc code: " + response.RrpcCode);
            Console.WriteLine("payload Base64Byte: " + response.PayloadBase64Byte);
        }


        //更新某一设备的影子
        public void TestUpdateDeviceShadow()
        {
            DefaultAcsClient client = Demo.IotClient.GetClient();

            UpdateDeviceShadowRequest request = new UpdateDeviceShadowRequest();
            request.ProductKey = "<productKey>";
            request.DeviceName = "<deviceName>";
            //注意设备影子格式，属性参数名称
            request.ShadowMessage = "{\"method\": \"update\",\"state\": {\"desired\": {\"color\": \"green\"}},\"version\": 2}";

            UpdateDeviceShadowResponse response = client.GetAcsResponse(request);
            Console.WriteLine(response.ErrorMessage);
            Console.WriteLine("update device shadown result: " + response.Success);
        }


        //获取某一设备的影子
        public void TestGetDeviceShadow()
        {
            DefaultAcsClient client = Demo.IotClient.GetClient();

            GetDeviceShadowRequest request = new GetDeviceShadowRequest();
            //测试的设备和上面添加动态属性的设备是同一设备
            request.ProductKey = "<productKey>";
            request.DeviceName = "<deviceName>";

            GetDeviceShadowResponse response = client.GetAcsResponse(request);
            Console.WriteLine(response.Success);
            Console.WriteLine(response.ErrorMessage);
            String ShadowMessage = response.ShadowMessage;
            Console.WriteLine("device shadown string: " + ShadowMessage);
        }


    }


}
