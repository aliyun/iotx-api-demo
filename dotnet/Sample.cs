using Aliyun.Acs.Core;
using Aliyun.Acs.Core.Exceptions;
using Aliyun.Acs.Core.Profile;
using Aliyun.Acs.Iot.Model.V20170420;
using System;
using System.Collections.Generic;
using System.Text;

namespace demo{

    class Sample
    {
        static void Main(string[] args)
        {
            TestRrpc();
        }


        //分页获取某产品下的设备列表
        private static void TestQueryDevicePageByProduct()
        {

            IClientProfile clientProfile = DefaultProfile.GetProfile("<your-region-id>", "<your-access-key-id>", "<your-access-key-secret>");
            
            DefaultAcsClient client = new DefaultAcsClient(clientProfile);

            QueryDeviceRequest request = new QueryDeviceRequest();
            request.PageSize = 10;
            request.CurrentPage = 1;
            request.ProductKey = "<productKey>";
            try
            {
                QueryDeviceResponse response = client.GetAcsResponse(request);
                Console.WriteLine(response.Success);
                Console.WriteLine(response.ErrorMessage);
                List<QueryDeviceResponse.QueryDevice_DeviceInfo> Data = response.Data;
                Console.WriteLine("count: " + Data.Count);
                foreach (QueryDeviceResponse.QueryDevice_DeviceInfo Info in Data) {
                    Console.WriteLine(Info.DeviceName);
                }
            }
            catch (ServerException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            catch (ClientException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            Console.ReadKey();
        }


        

       


        //在某一产品下创建一台设备
        private static void TestRegisterDevice() {
            IClientProfile clientProfile = DefaultProfile.GetProfile("<your-region-id>", "<your-access-key-id>", "<your-access-key-secret>");

            DefaultAcsClient client = new DefaultAcsClient(clientProfile);

            RegistDeviceRequest request = new RegistDeviceRequest();
            request.ProductKey = "<productKey>";
            request.DeviceName = "<deviceName>";

            try
            {
                RegistDeviceResponse response = client.GetAcsResponse(request);
                Console.WriteLine(response.Success);
                Console.WriteLine(response.ErrorMessage);                
                Console.WriteLine("Device Id: " + response.DeviceId);
                Console.WriteLine("Device Secret: " + response.DeviceSecret);
                Console.WriteLine("Device Status: " + response.DeviceStatus);
            }
            catch (ServerException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            catch (ClientException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            Console.ReadKey();


        }


        //根据产品PK和设备名称获取设备详细信息
        private static void TestQueryDeviceByName() {
            IClientProfile clientProfile = DefaultProfile.GetProfile("<your-region-id>", "<your-access-key-id>", "<your-access-key-secret>");

            DefaultAcsClient client = new DefaultAcsClient(clientProfile);

            QueryDeviceByNameRequest request = new QueryDeviceByNameRequest();
            request.ProductKey = "<productKey>";
            request.DeviceName = "<deviceName>";

            try
            {
                QueryDeviceByNameResponse response = client.GetAcsResponse(request);
                Console.WriteLine(response.Success);
                Console.WriteLine(response.ErrorMessage);
                QueryDeviceByNameResponse.QueryDeviceByName_DeviceInfo DeviceInfo = response.DeviceInfo;
                Console.WriteLine("productKey: " + DeviceInfo.ProductKey);
                Console.WriteLine("deviceName: " + DeviceInfo.DeviceName);
            }
            catch (ServerException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            catch (ClientException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            Console.ReadKey();
        }



        //获取一批设备的状态信息，包括状态、最后上线时间、登录IP
        private static void TestBatchGetDeviceStatus() {
            IClientProfile clientProfile = DefaultProfile.GetProfile("<your-region-id>", "<your-access-key-id>", "<your-access-key-secret>");
            DefaultAcsClient client = new DefaultAcsClient(clientProfile);
            BatchGetDeviceStateRequest request = new BatchGetDeviceStateRequest();
            request.ProductKey = "<productKey>";
            List<String> DeviceNames = new List<string>();
            DeviceNames.Add("<deviceName1>");
            DeviceNames.Add("<deviceName2>");
            DeviceNames.Add("<deviceName3>");
            DeviceNames.Add("<deviceName4>");
            DeviceNames.Add("<deviceName...>");            
            request.DeviceNames = DeviceNames;

            try
            {
                BatchGetDeviceStateResponse response = client.GetAcsResponse(request);
                Console.WriteLine(response.Success);
                Console.WriteLine(response.ErrorMessage);
                List<BatchGetDeviceStateResponse.BatchGetDeviceState_DeviceStatus> DeviceStatusList = response.DeviceStatusList;                
                foreach (BatchGetDeviceStateResponse.BatchGetDeviceState_DeviceStatus DeviceStatus in DeviceStatusList)
                {
                    Console.WriteLine(DeviceStatus.DeviceName + ", " +DeviceStatus.Status + ", " + DeviceStatus.LastOnlineTime);
                }
            }
            catch (ServerException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            catch (ClientException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            Console.ReadKey();

        }

        //输入产品名称和描述创建产品
        private static void TestCreateProduct() {
            IClientProfile clientProfile = DefaultProfile.GetProfile("<your-region-id>", "<your-access-key-id>", "<your-access-key-secret>");

            DefaultAcsClient client = new DefaultAcsClient(clientProfile);

            CreateProductRequest request = new CreateProductRequest();
            request.Name = "<productName>";
            request.Desc = "<product description>";            

            try
            {
                CreateProductResponse response = client.GetAcsResponse(request);
                Console.WriteLine(response.Success);
                Console.WriteLine(response.ErrorMessage);
                CreateProductResponse.CreateProduct_ProductInfo ProductInfo  = response.ProductInfo;
                Console.WriteLine("product name: " + ProductInfo.ProductName);
                Console.WriteLine("product key: " + ProductInfo.ProductKey);
                Console.WriteLine("product description: " + ProductInfo.ProductDesc);
            }
            catch (ServerException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            catch (ClientException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            Console.ReadKey();
        }


        //根据产品PK更新产品信息，如产品名称和产品描述
        private static void TestUpdateProduct() {
            IClientProfile clientProfile = DefaultProfile.GetProfile("<your-region-id>", "<your-access-key-id>", "<your-access-key-secret>");

            DefaultAcsClient client = new DefaultAcsClient(clientProfile);

            UpdateProductRequest request = new UpdateProductRequest();
            //更新的产品来源于CreateProductResponse
            request.ProductKey = "<productKey>";
            request.ProductName = "<productName>";
            request.ProductDesc = "<productDesc>";

            try
            {
                UpdateProductResponse response = client.GetAcsResponse(request);
                Console.WriteLine(response.Success);
                Console.WriteLine(response.ErrorMessage);                
            }
            catch (ServerException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            catch (ClientException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            Console.ReadKey();


        }


        //在某产品下申请创建一批设备
        private static void TestApplyDeviceWithNames() {
            IClientProfile clientProfile = DefaultProfile.GetProfile("<your-region-id>", "<your-access-key-id>", "<your-access-key-secret>");
            DefaultAcsClient client = new DefaultAcsClient(clientProfile);
            ApplyDeviceWithNamesRequest request = new ApplyDeviceWithNamesRequest();
            request.ProductKey = "<productKey>";
            List<String> DeviceNames = new List<string>();
            for (int i =1; i < 20; i ++) {
                if (i < 10)
                {
                    DeviceNames.Add("device_20171206_00" + i);
                }
                else {
                    DeviceNames.Add("device_20171206_0" + i);

                }                
            }
            request.DeviceNames = DeviceNames;

            try
            {
                ApplyDeviceWithNamesResponse response = client.GetAcsResponse(request);
                Console.WriteLine(response.Success);
                Console.WriteLine(response.ErrorMessage);                
                Console.WriteLine("apply id: " + response.ApplyId);               
            }
            catch (ServerException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            catch (ClientException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            Console.ReadKey();
        }



        //查询批量申请设备的设备列表
        private static void TestQueryPageByAppId()
        {
            IClientProfile clientProfile = DefaultProfile.GetProfile("<your-region-id>", "<your-access-key-id>", "<your-access-key-secret>");

            DefaultAcsClient client = new DefaultAcsClient(clientProfile);

            QueryPageByApplyIdRequest request = new QueryPageByApplyIdRequest();
            //ApplyId来源于ApplyDeviceWithNamesResponse
            request.ApplyId = 2429;
            request.CurrentPage = 2;
            request.PageSize = 20;
            try
            {
                QueryPageByApplyIdResponse response = client.GetAcsResponse(request);
                Console.WriteLine(response.Success);
                Console.WriteLine(response.ErrorMessage);
                Console.WriteLine(response.Page + ", " + response.PageSize + ", " + response.PageCount + ", " + response.Total);
                List<QueryPageByApplyIdResponse.QueryPageByApplyId_ApplyDeviceInfo> Data = response.ApplyDeviceList;
                foreach (QueryPageByApplyIdResponse.QueryPageByApplyId_ApplyDeviceInfo Info in Data)
                {
                    Console.WriteLine(Info.DeviceName);
                }
            }
            catch (ServerException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            catch (ClientException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            Console.ReadKey();

        }



        //查询批量申请设备的处理结果是否完成
        private static void TestQueryApplyStatus()
        {
            IClientProfile clientProfile = DefaultProfile.GetProfile("<your-region-id>", "<your-access-key-id>", "<your-access-key-secret>");

            DefaultAcsClient client = new DefaultAcsClient(clientProfile);

            QueryApplyStatusRequest request = new QueryApplyStatusRequest();
            //ApplyId来源于ApplyDeviceWithNamesResponse
            request.ApplyId = 11191;

            try
            {
                QueryApplyStatusResponse response = client.GetAcsResponse(request);
                Console.WriteLine(response.Success);
                Console.WriteLine(response.ErrorMessage);
                Console.WriteLine("Apply to create device is finish: " + response.Finish);
            }
            catch (ServerException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            catch (ClientException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            Console.ReadKey();

        }


        private static void TestSaveDeviceProp() {
            IClientProfile clientProfile = DefaultProfile.GetProfile("<your-region-id>", "<your-access-key-id>", "<your-access-key-secret>");

            DefaultAcsClient client = new DefaultAcsClient(clientProfile);

            SaveDevicePropRequest request = new SaveDevicePropRequest();
            request.Props = "{\"isGateWay\":\"no\"}";
            request.ProductKey = "<productKey>";
            request.DeviceName = "<deviceName>";

            try
            {
                SaveDevicePropResponse response = client.GetAcsResponse(request);
                Console.WriteLine("add new property: " + response.Success);
                Console.WriteLine(response.ErrorMessage);               
            }
            catch (ServerException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            catch (ClientException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            Console.ReadKey();
        }




        //根据产品PK和设备名称获取设备的动态属性
        private static void TestQueryDeviceProp()
        {
            IClientProfile clientProfile = DefaultProfile.GetProfile("<your-region-id>", "<your-access-key-id>", "<your-access-key-secret>");

            DefaultAcsClient client = new DefaultAcsClient(clientProfile);

            QueryDevicePropRequest request = new QueryDevicePropRequest();
            //测试的设备和上面添加动态属性的设备是同一设备
            request.ProductKey = "<productKey>";
            request.DeviceName = "<deviceName>";

            try
            {
                QueryDevicePropResponse response = client.GetAcsResponse(request);
                Console.WriteLine(response.Success);
                Console.WriteLine(response.ErrorMessage);
                String Props = response.Props;
                Console.WriteLine("properties string: " + Props);
            }
            catch (ServerException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            catch (ClientException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            Console.ReadKey();
        }



        //删除设备的某一动态属性，一次调用删除一个属性
        private static void TestDeleteDeviceProp() {
            IClientProfile clientProfile = DefaultProfile.GetProfile("<your-region-id>", "<your-access-key-id>", "<your-access-key-secret>");

            DefaultAcsClient client = new DefaultAcsClient(clientProfile);

            DeleteDevicePropRequest request = new DeleteDevicePropRequest();
            request.PropKey = "isGateWay";
            request.ProductKey = "<productKey>";
            request.DeviceName = "<deviceName>";

            try
            {
                DeleteDevicePropResponse response = client.GetAcsResponse(request);
                Console.WriteLine("delete the property [" + request.PropKey +"] : " + response.Success);
                Console.WriteLine(response.ErrorMessage);
                //删除完后，可以通过QueryDeviceProp验证一下
            }
            catch (ServerException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            catch (ClientException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            Console.ReadKey();
        }



        //更新某一设备的影子
        private static void TestUpdateDeviceShadow() {
            IClientProfile clientProfile = DefaultProfile.GetProfile("<your-region-id>", "<your-access-key-id>", "<your-access-key-secret>");

            DefaultAcsClient client = new DefaultAcsClient(clientProfile);

            UpdateDeviceShadowRequest request = new UpdateDeviceShadowRequest();
            request.ProductKey = "<productKey>";
            request.DeviceName = "<deviceName>";
            //注意设备影子格式，属性参数名称
            request.ShadowMessage = "{\"method\": \"update\",\"state\": {\"desired\": {\"color\": \"green\"}},\"version\": 2}";

            try
            {
                UpdateDeviceShadowResponse response = client.GetAcsResponse(request);                
                Console.WriteLine(response.ErrorMessage);                
                Console.WriteLine("update device shadown result: " + response.Success);
            }
            catch (ServerException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            catch (ClientException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            Console.ReadKey();

        }




        //获取某一设备的影子
        private static void TestGetDeviceShadow() {
            IClientProfile clientProfile = DefaultProfile.GetProfile("<your-region-id>", "<your-access-key-id>", "<your-access-key-secret>");

            DefaultAcsClient client = new DefaultAcsClient(clientProfile);

            GetDeviceShadowRequest request = new GetDeviceShadowRequest();
            //测试的设备和上面添加动态属性的设备是同一设备
            request.ProductKey = "<productKey>";
            request.DeviceName = "<deviceName>";

            try
            {
                GetDeviceShadowResponse response = client.GetAcsResponse(request);
                Console.WriteLine(response.Success);
                Console.WriteLine(response.ErrorMessage);
                String ShadowMessage = response.ShadowMessage;
                Console.WriteLine("device shadown string: " + ShadowMessage);
            }
            catch (ServerException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            catch (ClientException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            Console.ReadKey();
        }



        //向某一topic发布消息，注意topic，消息内容要提前进行base64编码
        private static void TestPub() {
            IClientProfile clientProfile = DefaultProfile.GetProfile("<your-region-id>", "<your-access-key-id>", "<your-access-key-secret>");

            DefaultAcsClient client = new DefaultAcsClient(clientProfile);

            PubRequest request = new PubRequest();
            request.ProductKey = "<productKey>";
            request.TopicFullName = "/" + request.ProductKey + "/<deviceName>/get";
                       
            byte[] payload = Encoding.Default.GetBytes("Hello World.");
            String payloadStr = Convert.ToBase64String(payload);
            request.MessageContent = payloadStr;
            request.Qos = 0;

            try
            {
                PubResponse response = client.GetAcsResponse(request);                
                Console.WriteLine("publish message result: " + response.Success);
                Console.WriteLine(response.ErrorMessage);
            }
            catch (ServerException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            catch (ClientException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            Console.ReadKey();
        }



        //发布广播消息，注意topic要以/broadcast开头
        private static void TestPubBroadcast() {
            IClientProfile clientProfile = DefaultProfile.GetProfile("<your-region-id>", "<your-access-key-id>", "<your-access-key-secret>");

            DefaultAcsClient client = new DefaultAcsClient(clientProfile);

            PubBroadcastRequest request = new PubBroadcastRequest();
            request.ProductKey = "<productKey>";
            request.TopicFullName = "/broadcast/" + request.ProductKey + "/<XXXXXXX[deviceName]>";

            byte[] payload = Encoding.Default.GetBytes("Hello World.");
            String payloadStr = Convert.ToBase64String(payload);
            request.MessageContent = payloadStr;
            

            try
            {
                PubBroadcastResponse response = client.GetAcsResponse(request);
                Console.WriteLine("publish broadcast message result: " + response.Success);
                Console.WriteLine(response.ErrorMessage);
            }
            catch (ServerException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            catch (ClientException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            Console.ReadKey();
        }




        private static void TestRrpc() {
            IClientProfile clientProfile = DefaultProfile.GetProfile("<your-region-id>", "<your-access-key-id>", "<your-access-key-secret>");

            DefaultAcsClient client = new DefaultAcsClient(clientProfile);

            RRpcRequest request = new RRpcRequest();
            request.ProductKey = "<productKey>";
            request.DeviceName = "<deviceName>";
            request.Timeout = 1000;

            byte[] payload = Encoding.Default.GetBytes("Hello World.");
            String payloadStr = Convert.ToBase64String(payload);            
            request.RequestBase64Byte = payloadStr;


            try
            {
                RRpcResponse response = client.GetAcsResponse(request);
                Console.WriteLine("publish rrpc message result: " + response.Success);
                Console.WriteLine(response.ErrorMessage);
                Console.WriteLine("message ID: " + response.MessageId);
                Console.WriteLine("rrpc code: " + response.RrpcCode);
                Console.WriteLine("payload Base64Byte: " + response.PayloadBase64Byte);

            }
            catch (ServerException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            catch (ClientException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            Console.ReadKey();
        }
    }

}
