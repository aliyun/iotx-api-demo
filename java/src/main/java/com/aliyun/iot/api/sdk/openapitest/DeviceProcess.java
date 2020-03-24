package com.aliyun.iot.api.sdk.openapitest;

import com.aliyun.iot.util.ServiceUtil;
import com.aliyuncs.iot.model.v20180120.*;

import static com.aliyun.iot.api.sdk.openapi.DeviceManager.*;
import static com.aliyun.iot.api.sdk.openapi.DeviceTopoManager.getGatewayBySubDevice;
import static com.aliyun.iot.api.sdk.openapi.DeviceTopoManager.getThingTopo;
import static com.aliyun.iot.api.sdk.openapi.ProductManager.createProductTest;

public class DeviceProcess {

    public static void main(String[] args) {
        DeviceProcess deviceProcess = new DeviceProcess();
        deviceProcess.deviceTwo();
    }

    /**
     * 1 创建 高级设备产品，不接入网关，wifi连接方式，数据格式为json
     * 2 添加一个设备
     * 3 批量添加3个设备
     * 4 查询该产品下面的设备列表
     * 5 查询激活的设备的信息详情
     * 6 禁用激活的设备
     * 7 删除设备
     */
    public void deviceOne() {

        //1 创建 高级设备产品
        CreateProductResponse.Data productReponse = createProductTest(ServiceUtil.productNameGenerator(), 0, null, 0,
            null, null, "", "wifi", "secret");

        //2添加一个设备
        RegisterDeviceResponse.Data device = registerDevice(productReponse.getProductKey(), null);


        //3批量添加3个设备
        batchRegisterDevice(productReponse.getProductKey(), 3);

        //4查询该产品下面的设备列表
        queryDevice(productReponse.getProductKey(), 1, 10);

        //5 查询激活的设备的信息详情
        queryDeviceDetail(device.getIotId(), null, null);

        //6 禁用激活的设备
        disableThing(device.getIotId(), null, null);

        //7 删除的设备
        deleteDevice(device.getIotId(), null, null);

    }

    /**
     * 1 创建高级版本网关产品，wifi
     * 2 创建 网关的设备
     * 3 创建高级版本设备产品，ble
     * 4 创建  接入网关的子设备
     * 5 查询网关设备的拓扑关系
     * 6 根据挂载的子设备信息，查询对应的网关设备信息
     *
     */
    public void deviceTwo() {
        //1 创建 高级网关产品
        CreateProductResponse.Data  gatewayProduct = createProductTest(ServiceUtil.productNameGenerator(), 1, "iothub_senior", 0,
            null, null, null, "wifi", "secret");

        //2添加一个网关设备
        RegisterDeviceResponse.Data gatewayDevice = registerDevice(gatewayProduct.getProductKey(), null);

        //3 创建 高级设备产品
        CreateProductResponse.Data  divceProduct = createProductTest(ServiceUtil.productNameGenerator(), 0, "iothub_senior", 0,
            null, null, null, "ble", "secret");

        //4添加一个子设备
        RegisterDeviceResponse.Data subDevice= registerDevice(divceProduct.getProductKey(), null);

        //5 查询网关设备的拓扑关系
        getThingTopo(gatewayDevice.getIotId(),null,null,1,10);

        //6 根据挂载的子设备信息，查询对应的网关设备信息
        getGatewayBySubDevice(subDevice.getIotId(),null,null);
    }

}
