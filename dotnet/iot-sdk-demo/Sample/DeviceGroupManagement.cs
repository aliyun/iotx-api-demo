using Aliyun.Acs.Core;
using Aliyun.Acs.Core.Exceptions;
using Aliyun.Acs.Core.Profile;
using Aliyun.Acs.Iot.Model.V20180120;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DeviceGroupManagement
{
    class DeviceGroupManagement
    {

        //创建一级设备分组
        public void TestCreateLevel1DeviceGroup()
        {

            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            String groupName = "Group_0827";
            String groupDesc = "0827 Device Group";

            CreateDeviceGroupRequest request = new CreateDeviceGroupRequest();
            request.GroupName = groupName;
            request.GroupDesc = groupDesc;

            CreateDeviceGroupResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Create level1 Device Group: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
            CreateDeviceGroupResponse.CreateDeviceGroup_Data GroupData = response.Data;
            Console.WriteLine("Create Device Group: " + GroupData.GroupId);
            Console.WriteLine("Create Device Group: " + GroupData.GroupName);
            Console.WriteLine("Create Device Group: " + GroupData.UtcCreate);
            Console.WriteLine("Create Device Group: " + GroupData.GroupDesc);
        }


        //创建二级设备分组
        public void TestCreateLevel2DeviceGroup()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            String groupName = "Level1_Device_Group0827";
            String groupDesc = "0826 Device Group";

            CreateDeviceGroupRequest request = new CreateDeviceGroupRequest();
            request.GroupName = groupName;
            request.GroupDesc = groupDesc;

            CreateDeviceGroupResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Create level1 Device Group: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
            CreateDeviceGroupResponse.CreateDeviceGroup_Data GroupData = response.Data;
            Console.WriteLine("Create Device Group: " + GroupData.GroupId);
            Console.WriteLine("Create Device Group: " + GroupData.GroupName);
            Console.WriteLine("Create Device Group: " + GroupData.UtcCreate);
            Console.WriteLine("Create Device Group: " + GroupData.GroupDesc);

            CreateDeviceGroupRequest request2 = new CreateDeviceGroupRequest();
            String group2Name = "Level2_Device_group0827";
            String group2Desc = "0826 Device Group";
            request2.GroupName = group2Name;
            request2.GroupDesc = group2Desc;
            request2.SuperGroupId = GroupData.GroupId;

            CreateDeviceGroupResponse response2 = acsClient.GetAcsResponse(request2);
            Console.WriteLine("Create level2 Device Group: " + response2.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
            CreateDeviceGroupResponse.CreateDeviceGroup_Data GroupData2 = response2.Data;
            Console.WriteLine("Create Device Group: " + GroupData2.GroupId);
            Console.WriteLine("Create Device Group: " + GroupData2.GroupName);
            Console.WriteLine("Create Device Group: " + GroupData2.UtcCreate);
            Console.WriteLine("Create Device Group: " + GroupData2.GroupDesc);
        }


        //修改分组的描述信息
        public void TestUpdateDeviceGroup()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            String groupId = "uSO1pVX7LnZK8LIwUZ1N010200";
            String groupDesc = "0827 Device";

            UpdateDeviceGroupRequest request = new UpdateDeviceGroupRequest();
            request.GroupId = groupId;
            request.GroupDesc = groupDesc;

            UpdateDeviceGroupResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Update Device Group: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
        }



        //查询分组详细信息
        public void TestQueryDeviceGroupInfo()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            String groupId = "uSO1pVX7LnZK8LIwUZ1N010200";

            QueryDeviceGroupInfoRequest request = new QueryDeviceGroupInfoRequest();
            request.GroupId = groupId;

            QueryDeviceGroupInfoResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Query Device GroupInfo: " + response.Success);

            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
            QueryDeviceGroupInfoResponse.QueryDeviceGroupInfo_Data GroupInfoData = response.Data;
            Console.WriteLine("Device Group ID: " + GroupInfoData.GroupId);
            Console.WriteLine("Device Group Name: " + GroupInfoData.GroupName);
            Console.WriteLine("Device Group UtcCreate: " + GroupInfoData.UtcCreate);
            Console.WriteLine("Device Group Desc: " + GroupInfoData.GroupDesc);
        }


        //分页获取分组列表
        public void TestQueryDeviceGroupList()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            QueryDeviceGroupListRequest request = new QueryDeviceGroupListRequest();
            request.CurrentPage = 1;
            request.PageSize = 10;

            QueryDeviceGroupListResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Query Device Group List: " + response.Success);

            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
            Console.WriteLine("CurrentPage: " + response.CurrentPage);
            Console.WriteLine("PageSize: " + response.PageSize);
            Console.WriteLine("PageCount: " + response.PageCount);
            Console.WriteLine("Total: " + response.Total);


            List<QueryDeviceGroupListResponse.QueryDeviceGroupList_GroupInfo> GroupInfos = response.Data;
            for (int i = 0; i < GroupInfos.Count; i += 1)
            {
                QueryDeviceGroupListResponse.QueryDeviceGroupList_GroupInfo GroupInfoData = GroupInfos[i];
                Console.WriteLine("Device Group ID: " + GroupInfoData.GroupId);
                Console.WriteLine("Device Group Name: " + GroupInfoData.GroupName);
                Console.WriteLine("Device Group UtcCreate: " + GroupInfoData.UtcCreate);
                Console.WriteLine("Device Group Desc: " + GroupInfoData.GroupDesc);
                Console.WriteLine();
            }
        }


        //删除某一设备分组，删除设备分组之前，确定分组中没有分配的设备
        public void TestDeleteDeviceGroup()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            String groupId = "uSO1pVX7LnZK8LIwUZ1N010200";

            DeleteDeviceGroupRequest request = new DeleteDeviceGroupRequest();
            request.GroupId = groupId;

            DeleteDeviceGroupResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Delete Device Group: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
        }


        //添加多个设备到分组中，建立分组与设备之间的关系
        public void TestBatchAddDeviceGroupRelations()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            String groupId = "AjDehc8leVvBzfW6xSl4010200";

            BatchAddDeviceGroupRelationsRequest request = new BatchAddDeviceGroupRelationsRequest();
            request.GroupId = groupId;
            List<BatchAddDeviceGroupRelationsRequest.Device> Devices = new List<BatchAddDeviceGroupRelationsRequest.Device>();
            BatchAddDeviceGroupRelationsRequest.Device device1 = new BatchAddDeviceGroupRelationsRequest.Device();
            device1.ProductKey = "a1HVMkh4YlK";
            device1.DeviceName = "device_0821_148";
            Devices.Add(device1);

            BatchAddDeviceGroupRelationsRequest.Device device2 = new BatchAddDeviceGroupRelationsRequest.Device();
            device2.ProductKey = "a163Bcy1oyR";
            device2.DeviceName = "device0826";
            Devices.Add(device2);

            request.Devices = Devices;

            BatchAddDeviceGroupRelationsResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Batch Add Device Group Relations: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
            Console.WriteLine("AlreadyRelatedGroupDeviceCount: " + response.AlreadyRelatedGroupDeviceCount);
            Console.WriteLine("ExceedTenGroupDeviceCount: " + response.ExceedTenGroupDeviceCount);
            Console.WriteLine("ValidDeviceCount: " + response.ValidDeviceCount);
            Console.WriteLine("SuccessAddedDeviceCount: " + response.SuccessAddedDeviceCount);
        }



        //批量解除多个设备与某一分组的关联关系，不会删除设备，也不会删除分组
        public void TestBatchDeleteDeviceGroupRelations()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            String groupId = "AjDehc8leVvBzfW6xSl4010200";

            BatchDeleteDeviceGroupRelationsRequest request = new BatchDeleteDeviceGroupRelationsRequest();
            request.GroupId = groupId;
            List<BatchDeleteDeviceGroupRelationsRequest.Device> Devices = new List<BatchDeleteDeviceGroupRelationsRequest.Device>();
            BatchDeleteDeviceGroupRelationsRequest.Device device1 = new BatchDeleteDeviceGroupRelationsRequest.Device();
            device1.ProductKey = "a1HVMkh4YlK";
            device1.DeviceName = "device_0821_148";
            Devices.Add(device1);

            request.Devices = Devices;

            BatchDeleteDeviceGroupRelationsResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Batch Delete Device Group Relations: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
            Console.WriteLine("AlreadyRelatedGroupDeviceCount: " + response.AlreadyRelatedGroupDeviceCount);
            Console.WriteLine("ValidDeviceCount: " + response.ValidDeviceCount);
            Console.WriteLine("SuccessAddedDeviceCount: " + response.SuccessDeviceCount);
        }


        //查询某一设备所在的分组，目前1个设备最多可以添加到10个分组中
        public void TestQueryDeviceGroupByDevice()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            String productKey = "a163Bcy1oyR";
            String deviceName = "device0826";

            QueryDeviceGroupByDeviceRequest request = new QueryDeviceGroupByDeviceRequest();
            request.ProductKey = productKey;
            request.DeviceName = deviceName;

            QueryDeviceGroupByDeviceResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine(" Query Device Group ByDevice: " + response.Success);

            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }


            List<QueryDeviceGroupByDeviceResponse.QueryDeviceGroupByDevice_GroupInfo> GroupInfos = response.GroupInfos;
            for (int i = 0; i < GroupInfos.Count; i += 1)
            {
                QueryDeviceGroupByDeviceResponse.QueryDeviceGroupByDevice_GroupInfo GroupInfoData = GroupInfos[i];
                Console.WriteLine("Device Group ID: " + GroupInfoData.GroupId);
                Console.WriteLine("Device Group Name: " + GroupInfoData.GroupName);
                Console.WriteLine("Device Group UtcCreate: " + GroupInfoData.UtcCreate);
                Console.WriteLine("Device Group Desc: " + GroupInfoData.GroupDesc);
                Console.WriteLine();
            }
        }


        //根据子分组ID获取父分组信息
        public void TestQuerySuperDeviceGroup()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            String GroupId = "hcfH99RL4Td87QZf0Yzh010200";

            QuerySuperDeviceGroupRequest request = new QuerySuperDeviceGroupRequest();
            request.GroupId = GroupId;

            QuerySuperDeviceGroupResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Query Super Device Group:" + response.Success);

            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }

            List<QuerySuperDeviceGroupResponse.QuerySuperDeviceGroup_GroupInfo> GroupInfos = response.Data;
            for (int i = 0; i < GroupInfos.Count; i += 1)
            {
                QuerySuperDeviceGroupResponse.QuerySuperDeviceGroup_GroupInfo GroupInfoData = GroupInfos[i];
                Console.WriteLine("Device Group ID: " + GroupInfoData.GroupId);
                Console.WriteLine("Device Group Name: " + GroupInfoData.GroupName);
                Console.WriteLine("Device Group Desc: " + GroupInfoData.GroupDesc);
                Console.WriteLine();
            }
        }


        public void TestQueryDeviceListByDeviceGroup()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            String productKey = "a163Bcy1oyR";
            String deviceName = "device0826";

            QueryDeviceGroupByDeviceRequest request = new QueryDeviceGroupByDeviceRequest();
            request.ProductKey = productKey;
            request.DeviceName = deviceName;

            QueryDeviceGroupByDeviceResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine(" Query Device Group ByDevice: " + response.Success);

            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }

            List<QueryDeviceGroupByDeviceResponse.QueryDeviceGroupByDevice_GroupInfo> GroupInfos = response.GroupInfos;
            for (int i = 0; i < GroupInfos.Count; i += 1)
            {
                QueryDeviceGroupByDeviceResponse.QueryDeviceGroupByDevice_GroupInfo GroupInfoData = GroupInfos[i];
                Console.WriteLine("Device Group ID: " + GroupInfoData.GroupId);
                Console.WriteLine("Device Group Name: " + GroupInfoData.GroupName);
                Console.WriteLine("Device Group UtcCreate: " + GroupInfoData.UtcCreate);
                Console.WriteLine("Device Group Desc: " + GroupInfoData.GroupDesc);
                Console.WriteLine();
            }
        }



        //此接口采用覆盖更新的策略，无则创建，有则覆盖，即用来创建、更新、删除设备分组标签
        public void TestSetDeviceGroupTags()
        {

            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            String groupId = "Qii3UILf2EvmeVrBMgQi010200";

            SetDeviceGroupTagsRequest request = new SetDeviceGroupTagsRequest();
            request.GroupId = groupId;

            List<Object> tags = new List<Object>();
            var tag1 = new { tagKey = "h1", tagValue = "hour1" };
            tags.Add(tag1);

            var tag2 = new { tagKey = "h2", tagValue = "hour2" };
            tags.Add(tag2);

            string tagJson = JsonConvert.SerializeObject(tags);
            request.TagString = tagJson;

            SetDeviceGroupTagsResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Set Device Group Tags: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
        }


        //查询设备分组标签列表
        public void TestQueryDeviceGroupTagList()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            String groupId = "AjDehc8leVvBzfW6xSl4010200";


            QueryDeviceGroupTagListRequest request = new QueryDeviceGroupTagListRequest();
            request.GroupId = groupId;

            QueryDeviceGroupTagListResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Query Device Group Tag List: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
            List<QueryDeviceGroupTagListResponse.QueryDeviceGroupTagList_GroupTagInfo> GroupTagInfos = response.Data;
            for (int i = 0; i < GroupTagInfos.Count; i += 1)
            {
                QueryDeviceGroupTagListResponse.QueryDeviceGroupTagList_GroupTagInfo GroupTagInfo = GroupTagInfos[i];
                Console.WriteLine(GroupTagInfo.TagKey + ", " + GroupTagInfo.TagValue);
            }
        }


        //根据标签获取设备分组列表，支持只按照标签TagKey检索，也支持同时按照TagKey和TagValue检索（见TestQueryDeviceGroupByTags2）
        public void TestQueryDeviceGroupByTags1()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            QueryDeviceGroupByTagsRequest request = new QueryDeviceGroupByTagsRequest();
            request.CurrentPage = 1;
            request.PageSize = 10;
            List<QueryDeviceGroupByTagsRequest.Tag> tagList = new List<QueryDeviceGroupByTagsRequest.Tag>();
            QueryDeviceGroupByTagsRequest.Tag tag1 = new QueryDeviceGroupByTagsRequest.Tag();
            tag1.TagKey = "h1";
            tagList.Add(tag1);

            QueryDeviceGroupByTagsRequest.Tag tag2 = new QueryDeviceGroupByTagsRequest.Tag();
            tag2.TagKey = "h2";
            tagList.Add(tag2);
            request.Tags = tagList;

            QueryDeviceGroupByTagsResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Query Device Group Tag List: " + response.Success);

            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
            Console.WriteLine("CurrentPage: " + response.Page);
            Console.WriteLine("PageSize: " + response.PageSize);
            Console.WriteLine("PageCount: " + response.PageCount);
            Console.WriteLine("Total: " + response.Total);

            List<QueryDeviceGroupByTagsResponse.QueryDeviceGroupByTags_DeviceGroup> DeviceGroups = response.Data;
            for (int i = 0; i < DeviceGroups.Count; i += 1)
            {
                QueryDeviceGroupByTagsResponse.QueryDeviceGroupByTags_DeviceGroup DeviceGroup = DeviceGroups[i];
                Console.WriteLine("GroupId: " + DeviceGroup.GroupId);
                Console.WriteLine("GroupName: " + DeviceGroup.GroupName);
                Console.WriteLine();
            }
        }


        //根据标签获取设备分组列表，支持同时按照TagKey和TagValue检索
        public void TestQueryDeviceGroupByTags2()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            QueryDeviceGroupByTagsRequest request = new QueryDeviceGroupByTagsRequest();
            request.CurrentPage = 1;
            request.PageSize = 10;
            List<QueryDeviceGroupByTagsRequest.Tag> tagList = new List<QueryDeviceGroupByTagsRequest.Tag>();
            QueryDeviceGroupByTagsRequest.Tag tag1 = new QueryDeviceGroupByTagsRequest.Tag();
            tag1.TagKey = "h1";
            tag1.TagValue = "clock";
            tagList.Add(tag1);

            QueryDeviceGroupByTagsRequest.Tag tag2 = new QueryDeviceGroupByTagsRequest.Tag();
            tag2.TagKey = "h2";
            tag2.TagValue = "hour";
            tagList.Add(tag2);
            request.Tags = tagList;

            QueryDeviceGroupByTagsResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Query Device Group Tag List: " + response.Success);

            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
            Console.WriteLine("CurrentPage: " + response.Page);
            Console.WriteLine("PageSize: " + response.PageSize);
            Console.WriteLine("PageCount: " + response.PageCount);
            Console.WriteLine("Total: " + response.Total);

            List<QueryDeviceGroupByTagsResponse.QueryDeviceGroupByTags_DeviceGroup> DeviceGroups = response.Data;
            for (int i = 0; i < DeviceGroups.Count; i += 1)
            {
                QueryDeviceGroupByTagsResponse.QueryDeviceGroupByTags_DeviceGroup DeviceGroup = DeviceGroups[i];
                Console.WriteLine("GroupId: " + DeviceGroup.GroupId);
                Console.WriteLine("GroupName: " + DeviceGroup.GroupName);
                Console.WriteLine();
            }
        }


    }


}
