using Aliyun.Acs.Core;
using Aliyun.Acs.Core.Exceptions;
using Aliyun.Acs.Core.Profile;
using Aliyun.Acs.Iot.Model.V20180120;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace Device
{
    class DeviceManagement
    {

        //在某一产品下创建一台设备
        public void TestRegisterDevice()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            RegisterDeviceRequest request = new RegisterDeviceRequest();
            request.ProductKey = "<productKey>";
            request.DeviceName = "device0822";


            RegisterDeviceResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine(response.Success);
            Console.WriteLine(response.ErrorMessage);

            RegisterDeviceResponse.RegisterDevice_Data device = response.Data;
            Console.WriteLine("Device IotId: " + device.IotId);
            Console.WriteLine("Device Secret: " + device.DeviceSecret);
            Console.WriteLine("Device Name: " + device.DeviceName);
        }


        //根据产品PK和设备名称获取设备详细信息
        public void TestQueryDeviceByName()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            QueryDeviceDetailRequest request = new QueryDeviceDetailRequest();
            request.ProductKey = "<productKey>";
            request.DeviceName = "device0821";

            QueryDeviceDetailResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine(response.Success);
            Console.WriteLine(response.ErrorMessage);

            QueryDeviceDetailResponse.QueryDeviceDetail_Data DeviceInfo = response.Data;
            Console.WriteLine("productKey: " + DeviceInfo.ProductKey);
            Console.WriteLine("deviceName: " + DeviceInfo.DeviceName);
            Console.WriteLine("iotId: " + DeviceInfo.IotId);
        }


        //根据设备的唯一标识IotId获取设备详细信息
        public void TestQueryDeviceByIotId()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            QueryDeviceDetailRequest request = new QueryDeviceDetailRequest();
            request.ProductKey = "<productKey>";
            request.DeviceName = "device0821";

            QueryDeviceDetailResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine(response.Success);
            Console.WriteLine(response.ErrorMessage);

            QueryDeviceDetailResponse.QueryDeviceDetail_Data DeviceInfo = response.Data;


            QueryDeviceDetailRequest anotherRequest = new QueryDeviceDetailRequest();
            anotherRequest.IotId = DeviceInfo.IotId;

            QueryDeviceDetailResponse anotherResponse = acsClient.GetAcsResponse(anotherRequest);
            QueryDeviceDetailResponse.QueryDeviceDetail_Data DeviceInfo2 = anotherResponse.Data;

            Console.WriteLine("productKey: " + DeviceInfo2.ProductKey);
            Console.WriteLine("deviceName: " + DeviceInfo2.DeviceName);
            Console.WriteLine("iotId: " + DeviceInfo2.IotId);
        }


        //根据设备名称列表批量获取设备详情
        public void TestBatchQueryDeviceDetail()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            BatchQueryDeviceDetailRequest request = new BatchQueryDeviceDetailRequest();

            request.ProductKey = "<productKey>";
            List<string> deviceNames = new List<string>();

            deviceNames.Add("device0821");
            deviceNames.Add("device0822");
            request.DeviceNames = deviceNames;

            BatchQueryDeviceDetailResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine(response.Success);
            Console.WriteLine(response.Code);
            Console.WriteLine(response.ErrorMessage);

            List<BatchQueryDeviceDetailResponse.BatchQueryDeviceDetail_DataItem> Data = response.Data;

            for (int i = 0; i < Data.Count; i += 1)
            {
                BatchQueryDeviceDetailResponse.BatchQueryDeviceDetail_DataItem itemDetail = Data[i];
                Console.WriteLine(itemDetail.DeviceName + ", " + itemDetail.ProductKey + ", " + itemDetail.IotId);
            }
        }


        //在某产品下申请创建一批设备
        public void TestApplyDeviceWithNames()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            BatchCheckDeviceNamesRequest request = new BatchCheckDeviceNamesRequest();
            request.ProductKey = "<productKey>";
            List<String> DeviceNames = new List<string>();
            for (int i = 1; i < 20; i++)
            {
                if (i < 10)
                {
                    DeviceNames.Add("device_20171206_00" + i);
                }
                else
                {
                    DeviceNames.Add("device_20171206_0" + i);
                }
            }
            request.DeviceNames = DeviceNames;

            BatchCheckDeviceNamesResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine(response.Success);
            Console.WriteLine(response.ErrorMessage);
            Console.WriteLine("apply id: " + response.Data.ApplyId);
        }


        //查询批量申请设备的处理结果是否完成
        public void TestQueryApplyStatus()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            QueryBatchRegisterDeviceStatusRequest request = new QueryBatchRegisterDeviceStatusRequest();
            //ApplyId来源于ApplyDeviceWithNamesResponse
            request.ApplyId = 11191;

            QueryBatchRegisterDeviceStatusResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine(response.Success);
            Console.WriteLine(response.ErrorMessage);
            Console.WriteLine("Apply to create device is finish: " + response.Data.Status);
        }


        //分页获取产品的设备列表
        public void TestQueryDevice()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            QueryDeviceRequest request = new QueryDeviceRequest();
            request.ProductKey = "<productKey>";
            request.CurrentPage = 1;
            request.PageSize = 10;


            QueryDeviceResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine(response.Success);
            Console.WriteLine(response.ErrorMessage); Console.WriteLine(response.Code);
            Console.WriteLine("CurrentPage" + response.Page);
            Console.WriteLine("PageCount: " + response.PageCount);
            Console.WriteLine("Total" + response.Total);

            List<QueryDeviceResponse.QueryDevice_DeviceInfo> data = response.Data;
            for (int i = 0; i < data.Count; i += 1)
            {
                QueryDeviceResponse.QueryDevice_DeviceInfo deviceInfo = data[i];
                Console.WriteLine(deviceInfo.DeviceId + ", " + deviceInfo.DeviceName + ", " + deviceInfo.ProductKey + ", " + deviceInfo.IotId);
            }
        }


        //获取设备的在线状态，ONLINE｜OFFLINE|UNACTIVE|DISABLE
        public void TestGetDeviceStatus()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            GetDeviceStatusRequest request = new GetDeviceStatusRequest();

            request.ProductKey = "<productKey>";
            request.DeviceName = "device0821";

            GetDeviceStatusResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine(response.Success);
            Console.WriteLine(response.Code + ", " + response.ErrorMessage);

            GetDeviceStatusResponse.GetDeviceStatus_Data Data = response.Data;
            Console.WriteLine("Status: " + Data.Status);
        }


        //获取设备的在线状态，ONLINE｜OFFLINE|UNACTIVE|DISABLE
        public void TestBatchGetDeviceState()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            BatchGetDeviceStateRequest request = new BatchGetDeviceStateRequest();

            request.ProductKey = "<productKey>";

            List<string> deviceNames = new List<string>();
            deviceNames.Add("device0820");
            deviceNames.Add("device0821");
            deviceNames.Add("device0822");

            request.DeviceNames = deviceNames;


            BatchGetDeviceStateResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine(response.Success);
            Console.WriteLine(response.Code + ", " + response.ErrorMessage);

            List<BatchGetDeviceStateResponse.BatchGetDeviceState_DeviceStatus> statusList = response.DeviceStatusList;
            for (int i = 0; i < statusList.Count; i += 1)
            {
                BatchGetDeviceStateResponse.BatchGetDeviceState_DeviceStatus deviceStatus = statusList[i];
                Console.WriteLine(deviceStatus.DeviceName + ", " + deviceStatus.IotId + ", " + deviceStatus.Status);
            }
        }


        //禁用设备，设备一旦被禁用，不能再接入物联网平台，除非解禁
        public void TestDisableThing()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            String productKey = "<productKey>";
            String deviceName = "device0821";

            DisableThingRequest request = new DisableThingRequest();
            request.ProductKey = productKey;
            request.DeviceName = deviceName;

            DisableThingResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine(response.Success);
            if ((bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
            }

            GetDeviceStatusRequest request2 = new GetDeviceStatusRequest();
            request2.ProductKey = productKey;
            request2.DeviceName = deviceName;

            GetDeviceStatusResponse response2 = acsClient.GetAcsResponse(request2);
            Console.WriteLine(response2.Success);
            if ((bool)response2.Success)
            {
                Console.WriteLine(response2.Code + ", " + response2.ErrorMessage);
            }

            GetDeviceStatusResponse.GetDeviceStatus_Data Data = response2.Data;
            Console.WriteLine("Status: " + Data.Status);
        }


        //重新启用设备，被禁用的设备启用后可以再次接入物联网平台
        public void TestEnableThing()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            String productKey = "<productKey>";
            String deviceName = "device0821";

            DisableThingRequest request = new DisableThingRequest();
            request.ProductKey = productKey;
            request.DeviceName = deviceName;

            DisableThingResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine(response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
            }

            GetDeviceStatusRequest request2 = new GetDeviceStatusRequest();
            request2.ProductKey = productKey;
            request2.DeviceName = deviceName;

            GetDeviceStatusResponse response2 = acsClient.GetAcsResponse(request2);
            Console.WriteLine(response2.Success);
            if (!(bool)response2.Success)
            {
                Console.WriteLine(response2.Code + ", " + response2.ErrorMessage);
            }

            GetDeviceStatusResponse.GetDeviceStatus_Data Data = response2.Data;
            Console.WriteLine("Status: " + Data.Status);


            EnableThingRequest request3 = new EnableThingRequest();
            request3.ProductKey = productKey;
            request3.DeviceName = deviceName;

            EnableThingResponse response3 = acsClient.GetAcsResponse(request3);
            Console.WriteLine(response3.Success);
            if (!(bool)response3.Success)
            {
                Console.WriteLine(response3.Code + ", " + response3.ErrorMessage);
            }


            GetDeviceStatusRequest request4 = new GetDeviceStatusRequest();
            request4.ProductKey = productKey;
            request4.DeviceName = deviceName;

            GetDeviceStatusResponse response4 = acsClient.GetAcsResponse(request4);
            Console.WriteLine(response4.Success);
            if (!(bool)response4.Success)
            {
                Console.WriteLine(response4.Code + ", " + response4.ErrorMessage);
            }

            Data = response4.Data;
            Console.WriteLine("Status: " + Data.Status);
        }


        //批量注册设备，最多一次创建1000台设备，设备名称随机生成
        public void TestBatchRegisterDevice()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            String productKey = "<productKey>";
            BatchRegisterDeviceRequest request1 = new BatchRegisterDeviceRequest();
            request1.ProductKey = productKey;
            request1.Count = 10;

            //Step1 创建申请单，返回applyId
            BatchRegisterDeviceResponse response1 = acsClient.GetAcsResponse(request1);
            Console.WriteLine("Batch Register: " + response1.Success);
            if (!(bool)response1.Success)
            {
                Console.WriteLine(response1.Code + ", " + response1.ErrorMessage);
            }
            long applyId = (long)response1.Data.ApplyId;
            Console.WriteLine("ApplyId: " + applyId);

            String Status = "CHECK_FAILED";
            while (true)
            {
                //轮询申请单的检查进度，如果检查正常通过，则可以量产设备
                QueryBatchRegisterDeviceStatusRequest request2 = new QueryBatchRegisterDeviceStatusRequest();
                request2.ApplyId = applyId;
                request2.ProductKey = productKey;

                QueryBatchRegisterDeviceStatusResponse response2 = acsClient.GetAcsResponse(request2);

                if (!(bool)response2.Success)
                {
                    Console.WriteLine(response2.Code + ", " + response2.ErrorMessage);
                    break;
                }
                QueryBatchRegisterDeviceStatusResponse.QueryBatchRegisterDeviceStatus_Data data = response2.Data;
                Status = data.Status;
                Console.WriteLine("Query Status: " + response2.Success + ", " + Status);
                if ("CREATE_SUCCESS".Equals(Status))
                {
                    break;
                }
                else
                {
                    Thread.Sleep(1000);
                }
            }

            if ("CREATE_SUCCESS".Equals(Status))
            {
                QueryPageByApplyIdRequest request3 = new QueryPageByApplyIdRequest();
                request3.ApplyId = applyId;
                request3.CurrentPage = 1;
                request3.PageSize = 10;

                QueryPageByApplyIdResponse response3 = acsClient.GetAcsResponse(request3);
                Console.WriteLine("Query With ApplyId: " + response3.Success);
                if (!(bool)response3.Success)
                {
                    Console.WriteLine(response3.Code + ", " + response3.ErrorMessage);
                }
                Console.WriteLine("Page: " + response3.Page);
                Console.WriteLine("PageSize: " + response3.PageSize);
                Console.WriteLine("PageCount: " + response3.PageCount);
                Console.WriteLine("Total: " + response3.Total);
            }
        }


        //批量注册设备，最多一次创建1000台设备，指定设备名称
        public void TestBatchRegisterDeviceWithNames()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            String productKey = "<productKey>";
            BatchCheckDeviceNamesRequest request1 = new BatchCheckDeviceNamesRequest();
            request1.ProductKey = productKey;

            List<string> DeviceNames = new List<string>();
            for (int i = 100; i < 150; i += 1)
            {
                DeviceNames.Add("device_0821_" + i);
            }
            request1.DeviceNames = DeviceNames;

            //Step1 创建申请单，返回applyId
            BatchCheckDeviceNamesResponse response1 = acsClient.GetAcsResponse(request1);
            Console.WriteLine("Batch Check: " + response1.Success);
            if (!(bool)response1.Success)
            {
                Console.WriteLine(response1.Code + ", " + response1.ErrorMessage);
            }
            long applyId = (long)response1.Data.ApplyId;
            Console.WriteLine("ApplyId: " + applyId);

            String Status = "FAILED";
            while (true)
            {
                //Step2 轮询申请单的检查进度，如果检查正常通过，则可以量产设备
                QueryBatchRegisterDeviceStatusRequest request2 = new QueryBatchRegisterDeviceStatusRequest();
                request2.ApplyId = applyId;
                request2.ProductKey = productKey;

                QueryBatchRegisterDeviceStatusResponse response2 = acsClient.GetAcsResponse(request2);

                if (!(bool)response2.Success)
                {
                    Console.WriteLine(response2.Code + ", " + response2.ErrorMessage);
                    break;
                }
                QueryBatchRegisterDeviceStatusResponse.QueryBatchRegisterDeviceStatus_Data data = response2.Data;
                Status = data.Status;
                Console.WriteLine("Query Status: " + response2.Success + ", " + Status);
                if ("CHECK_SUCCESS".Equals(Status))
                {
                    break;

                }
                else
                {
                    if ("CHECK_FAILED".Equals(Status))
                    {
                        List<string> InvalidDevicenameList = response2.Data.InvalidList;
                        for (int j = 0; j < InvalidDevicenameList.Count; j += 1)
                        {
                            String deviceName = InvalidDevicenameList[j];
                            Console.WriteLine("Invalid DeviceName: " + deviceName);
                        }
                        break;
                    }
                    else
                    {
                        Thread.Sleep(1000);
                    }

                }

            }

            if ("CHECK_SUCCESS".Equals(Status))
            {
                //Step3 开始量产设备，量产设备是异步过程，全部待生产设备完成生产需要一段时间
                BatchRegisterDeviceWithApplyIdRequest request3 = new BatchRegisterDeviceWithApplyIdRequest();
                request3.ProductKey = productKey;
                request3.ApplyId = applyId;

                BatchRegisterDeviceWithApplyIdResponse response3 = acsClient.GetAcsResponse(request3);
                Console.WriteLine("Batch Register With ApplyId Request: " + response3.Success);
                if (!(bool)response3.Success)
                {
                    Console.WriteLine(response3.Code + ", " + response3.ErrorMessage);
                }

            }

            while (true)
            {
                //轮询申请单的检查进度，如果申请单是创建完成状态，则表明全部设备已经生产完毕
                QueryBatchRegisterDeviceStatusRequest request2 = new QueryBatchRegisterDeviceStatusRequest();
                request2.ApplyId = applyId;
                request2.ProductKey = productKey;

                QueryBatchRegisterDeviceStatusResponse response2 = acsClient.GetAcsResponse(request2);

                if (!(bool)response2.Success)
                {
                    Console.WriteLine(response2.Code + ", " + response2.ErrorMessage);
                    break;
                }
                QueryBatchRegisterDeviceStatusResponse.QueryBatchRegisterDeviceStatus_Data data = response2.Data;
                Status = data.Status;
                Console.WriteLine("Query Status: " + response2.Success + ", " + Status);
                if ("CREATE_SUCCESS".Equals(Status))
                {
                    break;

                }
                else
                {
                    Thread.Sleep(1000);
                }

            }

            //Step5 根据ApplyId，获取这一批次量产的全部设备
            QueryPageByApplyIdRequest request4 = new QueryPageByApplyIdRequest();
            request4.ApplyId = applyId;
            request4.CurrentPage = 1;
            request4.PageSize = 10;

            QueryPageByApplyIdResponse response4 = acsClient.GetAcsResponse(request4);
            Console.WriteLine("Query With ApplyId: " + response4.Success);
            if (!(bool)response4.Success)
            {
                Console.WriteLine(response4.Code + ", " + response4.ErrorMessage);
            }
            Console.WriteLine("Page: " + response4.Page);
            Console.WriteLine("PageSize: " + response4.PageSize);
            Console.WriteLine("PageCount: " + response4.PageCount);
            Console.WriteLine("Total: " + response4.Total);
        }


        //批量更新多个设备的nickname
        public void TestBatchUpdateDeviceNickname()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            String productKey = "<productKey>";
            BatchUpdateDeviceNicknameRequest request1 = new BatchUpdateDeviceNicknameRequest();

            List<BatchUpdateDeviceNicknameRequest.DeviceNicknameInfo> DeviceNicknameInfos = new List<BatchUpdateDeviceNicknameRequest.DeviceNicknameInfo>();
            BatchUpdateDeviceNicknameRequest.DeviceNicknameInfo deviceNickname1 = new BatchUpdateDeviceNicknameRequest.DeviceNicknameInfo();
            deviceNickname1.ProductKey = productKey;
            deviceNickname1.DeviceName = "device_0821_147";
            deviceNickname1.Nickname = "nick147";
            DeviceNicknameInfos.Add(deviceNickname1);


            BatchUpdateDeviceNicknameRequest.DeviceNicknameInfo deviceNickname2 = new BatchUpdateDeviceNicknameRequest.DeviceNicknameInfo();
            deviceNickname2.ProductKey = productKey;
            deviceNickname2.DeviceName = "device_0821_148";
            deviceNickname2.Nickname = "nick148";
            DeviceNicknameInfos.Add(deviceNickname2);
            request1.DeviceNicknameInfos = DeviceNicknameInfos;


            BatchUpdateDeviceNicknameResponse response1 = acsClient.GetAcsResponse(request1);
            Console.WriteLine("Update DeviceNickname: " + response1.Success);
            if (!(bool)response1.Success)
            {
                Console.WriteLine(response1.Code + ", " + response1.ErrorMessage);
            }

            QueryDeviceDetailRequest request2 = new QueryDeviceDetailRequest();
            request2.ProductKey = productKey;
            request2.DeviceName = deviceNickname1.DeviceName;

            QueryDeviceDetailResponse response2 = acsClient.GetAcsResponse(request2);
            Console.WriteLine(response2.Success);
            Console.WriteLine(response2.ErrorMessage);

            QueryDeviceDetailResponse.QueryDeviceDetail_Data DeviceInfo = response2.Data;
            Console.WriteLine("productKey: " + DeviceInfo.ProductKey);
            Console.WriteLine("deviceName: " + DeviceInfo.DeviceName);
            Console.WriteLine("iotId: " + DeviceInfo.IotId);
            Console.WriteLine("Nickname: " + DeviceInfo.Nickname);
        }


        //设置设备的标签,采用的是无则创建，有则更新的策略
        public void TestSaveDeviceProp()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            String productKey = "<productKey>";
            String devicename = "device_0821_147";
            SaveDevicePropRequest request1 = new SaveDevicePropRequest();
            request1.ProductKey = productKey;
            request1.DeviceName = devicename;


            request1.Props = "{\"city\":\"hangzhou\", \"region\":\"west\"}";

            SaveDevicePropResponse response1 = acsClient.GetAcsResponse(request1);
            Console.WriteLine("Save Device Tags: " + response1.Success);
            if (!(bool)response1.Success)
            {
                Console.WriteLine(response1.Code + ", " + response1.ErrorMessage);
            }

            QueryDevicePropRequest request2 = new QueryDevicePropRequest();
            request2.ProductKey = productKey;
            request2.DeviceName = devicename;

            QueryDevicePropResponse response2 = acsClient.GetAcsResponse(request2);
            Console.WriteLine(response2.Success);
            if (!(bool)response2.Success)
            {
                Console.WriteLine(response2.Code + ", " + response2.ErrorMessage);
            }
            Console.WriteLine("Tags: " + response2.Props);
        }


        //根据设备标签获取设备列表
        public void TestQueryDeviceByTags()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            String productKey = "<productKey>";
            String deviceTags = "{\"city\":\"hangzhou\", \"region\":\"west\"}";

            for (int i = 100; i <= 150; i += 1)
            {
                String devicename = "device_0821_" + i;
                SaveDevicePropRequest request = new SaveDevicePropRequest();
                request.ProductKey = productKey;
                request.DeviceName = devicename;
                request.Props = deviceTags;
                SaveDevicePropResponse response1 = acsClient.GetAcsResponse(request);
                Console.WriteLine("Save Device Tags: " + response1.Success);
                if (!(bool)response1.Success)
                {
                    Console.WriteLine(response1.Code + ", " + response1.ErrorMessage);
                }
            }

            QueryDeviceByTagsRequest request2 = new QueryDeviceByTagsRequest();
            request2.CurrentPage = 1;
            request2.PageSize = 50;

            List<QueryDeviceByTagsRequest.Tag> Tags = new List<QueryDeviceByTagsRequest.Tag>();
            QueryDeviceByTagsRequest.Tag tag1 = new QueryDeviceByTagsRequest.Tag();
            tag1.TagKey = "city";
            tag1.TagValue = "hangzhou";
            Tags.Add(tag1);
            request2.Tags = Tags;


            QueryDeviceByTagsResponse response2 = acsClient.GetAcsResponse(request2);
            Console.WriteLine(response2.Success);
            if (!(bool)response2.Success)
            {
                Console.WriteLine(response2.Code + ", " + response2.ErrorMessage);
            }
            Console.WriteLine("Page: " + response2.Page);
            Console.WriteLine("PageSize: " + response2.PageSize);
            Console.WriteLine("PageCount: " + response2.PageCount);
            Console.WriteLine("Total: " + response2.Total);
        }


        //删除设备的标签
        public void TestDeleteDeviceProp()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            String productKey = "<productKey>";
            String devicename = "device_0821_147";
            String propKey = "city";

            DeleteDevicePropRequest request1 = new DeleteDevicePropRequest();
            request1.ProductKey = productKey;
            request1.DeviceName = devicename;
            request1.PropKey = propKey;

            DeleteDevicePropResponse response1 = acsClient.GetAcsResponse(request1);
            Console.WriteLine("Delete Device Tags: " + response1.Success);
            if (!(bool)response1.Success)
            {
                Console.WriteLine(response1.Code + ", " + response1.ErrorMessage);
            }

            QueryDevicePropRequest request2 = new QueryDevicePropRequest();
            request2.ProductKey = productKey;
            request2.DeviceName = devicename;

            QueryDevicePropResponse response2 = acsClient.GetAcsResponse(request2);
            Console.WriteLine(response2.Success);
            if (!(bool)response2.Success)
            {
                Console.WriteLine(response2.Code + ", " + response2.ErrorMessage);
            }
            Console.WriteLine("Tags: " + response2.Props);
        }


        /*
         * 调用此接口，触发云端向网关设备下达指令（添加子设备拓扑关系），网关如果订阅对应的Topic，则会受到消息通知
         * 拓扑关系的添加成功与否是要看网关动作成功与否
         * 调用此接口的前提是，网关设备要在线，子设备也要在线，目前一台子设备只能绑定到一台网关设备上
         * */
        public void TestNotifyAddThingTopo()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            //网关设备
            String gwProductKey = "<productKey>";
            String gwDevicename = "device_0821_148";
            //普通设备（子设备）
            String ProductKey = "<productKey>";
            String DeviceName = "<deviceName>";

            NotifyAddThingTopoRequest request = new NotifyAddThingTopoRequest();
            request.GwProductKey = gwProductKey;
            request.GwDeviceName = gwDevicename;

            String deviceListString = "[{\"productKey\":\"" + ProductKey + "\", \"deviceName\":\"" + DeviceName + "\"}]";
            request.DeviceListStr = deviceListString;

            NotifyAddThingTopoResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Notify Add ThingTopo: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
            }

            NotifyAddThingTopoResponse.NotifyAddThingTopo_Data Data = response.Data;
            Console.WriteLine("Notify Add ThingTopo MessageId: " + Data.MessageId);
        }


        //获取网关设备的拓扑结构（子设备列表）
        public void TestGetThingTopo()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            //网关设备
            String gwProductKey = "<productKey>";
            String gwDevicename = "device_0821_148";
            GetThingTopoRequest request = new GetThingTopoRequest();
            request.ProductKey = gwProductKey;
            request.DeviceName = gwDevicename;
            request.PageNo = 1;
            request.PageSize = 10;

            GetThingTopoResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Get ThingTopo: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
            }

            GetThingTopoResponse.GetThingTopo_Data Data = response.Data;
            Console.WriteLine("Page: " + Data.CurrentPage);
            Console.WriteLine("PageSize: " + Data.PageSize);
            Console.WriteLine("PageCount: " + Data.PageCount);
            Console.WriteLine("Total: " + Data.Total);
        }


        //解除设备与所属网关设备的拓扑关系
        public void TestRemoveThingTopo()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            String ProductKey = "<productKey>";
            String Devicename = "device_0821_148";

            RemoveThingTopoRequest request = new RemoveThingTopoRequest();
            request.ProductKey = ProductKey;
            request.DeviceName = Devicename;

            RemoveThingTopoResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine("Remove ThingTopo: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
            }

            bool remove = (bool)response.Data;
            Console.WriteLine("Remove ThingTopo Result: " + remove);
        }


        //查看某一产品下的设备统计数据
        public void TestQueryDeviceStatistics()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            String productKey = "<productKey>";

            QueryDeviceStatisticsRequest request = new QueryDeviceStatisticsRequest();
            request.ProductKey = productKey;

            QueryDeviceStatisticsResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Query Device Statistics: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
            }

            QueryDeviceStatisticsResponse.QueryDeviceStatistics_Data data = response.Data;
            Console.WriteLine("DeviceCount: " + (long)data.DeviceCount);
            Console.WriteLine("ActiveCount: " + (long)data.ActiveCount);
            Console.WriteLine("OnlineCount: " + (long)data.OnlineCount);
        }


        //获取子设备所属的网关设备信息
        public void TestGetGatewayBySubDevice()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();


            String ProductKey = "<productKey>";
            String Devicename = "device_0821_148";

            GetGatewayBySubDeviceRequest request = new GetGatewayBySubDeviceRequest();
            request.ProductKey = ProductKey;
            request.DeviceName = Devicename;

            GetGatewayBySubDeviceResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Get Gateway: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                return;
            }

            GetGatewayBySubDeviceResponse.GetGatewayBySubDevice_Data gwDeviceData = response.Data;
            Console.WriteLine(gwDeviceData.ProductKey + ", " + gwDeviceData.DeviceName + ", " + gwDeviceData.NodeType + ", " + gwDeviceData.IotId);
        }


        //获取设备上传的文件列表，设备如何上传到文件有对应的设备端SDK可以参考
        public void TestQueryDeviceFileList()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            String ProductKey = "<productKey>";
            String Devicename = "device_0821_148";

            QueryDeviceFileListRequest request = new QueryDeviceFileListRequest();
            request.ProductKey = ProductKey;
            request.DeviceName = Devicename;
            request.CurrentPage = 1;
            request.PageSize = 10;


            QueryDeviceFileListResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Query Device File List: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                return;
            }

            Console.WriteLine("CurrentPage: " + response.CurrentPage);
            Console.WriteLine("PageSize: " + response.PageSize);
            Console.WriteLine("PageCount: " + response.PageCount);
            Console.WriteLine("Total: " + response.Total);

            List<QueryDeviceFileListResponse.QueryDeviceFileList_FileSummary> Data = response.Data;
            for (int i = 0; i < Data.Count; i += 1)
            {
                QueryDeviceFileListResponse.QueryDeviceFileList_FileSummary fileData = Data[i];
                Console.WriteLine(fileData.FileId + ", " + fileData.Name + ", " + fileData.Size + ", " + fileData.UtcCreatedOn);
            }
        }


        //获取设备的某一文件信息详情
        public void TestQueryDeviceFile()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            String ProductKey = "<productKey>";
            String Devicename = "device_0821_148";
            String fileId = "123456";

            QueryDeviceFileRequest request = new QueryDeviceFileRequest();
            request.ProductKey = ProductKey;
            request.DeviceName = Devicename;
            request.FileId = fileId;

            QueryDeviceFileResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Query Device File Detail: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                return;
            }

            QueryDeviceFileResponse.QueryDeviceFile_Data fileData = response.Data;
            Console.WriteLine(fileData.FileId + ", " + fileData.Name + ", " + fileData.Size + ", " + fileData.UtcCreatedOn);
        }


        //删除设备的某一文件
        public void TestDeleteDeviceFile()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            String ProductKey = "<productKey>";
            String Devicename = "device_0821_148";
            String fileId = "123456";

            DeleteDeviceFileRequest request = new DeleteDeviceFileRequest();

            request.ProductKey = ProductKey;
            request.DeviceName = Devicename;
            request.FileId = fileId;

            DeleteDeviceFileResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Query Device File Detail: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                return;
            }
        }


    }

}
