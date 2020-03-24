package com.aliyun.iot.api.sdk.openapitest;

import com.aliyun.iot.util.ServiceUtil;
import com.aliyuncs.iot.model.v20180120.BatchAddDeviceGroupRelationsRequest;
import com.aliyuncs.iot.model.v20180120.CreateDeviceGroupResponse;
import com.aliyuncs.iot.model.v20180120.CreateProductResponse;
import com.aliyuncs.iot.model.v20180120.RegisterDeviceResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.aliyun.iot.api.sdk.openapi.DeviceManager.registerDevice;
import static com.aliyun.iot.api.sdk.openapi.GroupManager.*;
import static com.aliyun.iot.api.sdk.openapi.ProductManager.createProductTest;

public class GroupProcess {

    public static void main(String[] args) {
        GroupProcess
            groupProcess = new GroupProcess();
        groupProcess.groupThree();
    }


    /**
     * 1 创建分组
     * 2 删除分组
     * 3 创建分组
     * 4 更新分组
     * 5 查询分组列表
     * 6 添加子分组
     */
    public void groupOne() {
        //1 创建分组
        CreateDeviceGroupResponse.Data  groupObjectTemp =  createDeviceGroup(null , ServiceUtil.groupNameGenerator(),null);
        //2 删除分组
        deleteDeviceGroup(groupObjectTemp.getGroupId());

        //3 创建分组
        CreateDeviceGroupResponse.Data  groupObject = createDeviceGroup(null , ServiceUtil.groupNameGenerator(),null);

        //4 更新分组
        updateDeviceGroup(groupObject.getGroupId() ,"修改描述" );

        //5 查询分组列表
        queryDeviceGroupList(null , null ,1,10 );

        //6 添加子分组
        createDeviceGroup(groupObject.getGroupId() , ServiceUtil.groupNameGenerator(),null);

    }


    /**
     * 1 创建分组
     * 2 分组批量添加子设备
     * 3 根据分组查询设备列表
     * 4 删除分组下的设备
     */
    public void groupTwo() {
        //1 创建分组
        CreateDeviceGroupResponse.Data groupObject =  createDeviceGroup(null , ServiceUtil.groupNameGenerator(),null);
        //2 分组批量添加子设备
        // 创建 高级设备产品
        CreateProductResponse.Data divceProduct = createProductTest(ServiceUtil.productNameGenerator(), 0, "iothub_senior", 0, null, null, null, "ble", "secret");
        //添加1个子设备
        List<Map<String ,String >> addDeviceList = new ArrayList<>();
        RegisterDeviceResponse.Data  subDevice= registerDevice(divceProduct.getProductKey(), null);
        List<BatchAddDeviceGroupRelationsRequest.Device> addList = new ArrayList<>();
        BatchAddDeviceGroupRelationsRequest.Device device =new BatchAddDeviceGroupRelationsRequest.Device();
        device.setProductKey(subDevice.getProductKey());
        device.setProductKey(subDevice.getDeviceName());
        addList.add(device);

        batchAddDeviceGroupRelations(groupObject.getGroupId() , addList);

        //3 根据分组查询设备列表
        queryDeviceListByDeviceGroup(groupObject.getGroupId() ,1,10);

    }


    /**
     * 1 创建分组
     * 2 添加分组标签
     * 3 查询分组下标签列表
     */
    public void groupThree() {
        //1 创建分组
        CreateDeviceGroupResponse.Data  groupObject =  createDeviceGroup(null , ServiceUtil.groupNameGenerator(),"创建分组test");

        //2  添加分组标签
        setDeviceGroupTags(groupObject.getGroupId(),"[{\"tagKey\":\"keyone\","
            + "\"tagValue\":\"1\"},{\"tagKey\":\"KeyThree\",\"tagValue\":\"3\"}]");

        //3  查询分组下标签列表
        queryDeviceGroupTagList(groupObject.getGroupId());
    }

}
