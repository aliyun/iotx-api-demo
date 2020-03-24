package com.aliyun.iot.api.sdk.openapitest;

import java.util.ArrayList;
import java.util.List;

import static com.aliyun.iot.api.sdk.openapi.ThingModelManager.*;

public class ThingProcess {

    public static void main(String[] args) {
        ThingProcess
            thingProcess = new ThingProcess();
        thingProcess.groupOne();
    }

    /**
     * 1 设置设备属性
     * 2 批量设置设备属性
     * 3 查询设备属性
     * 4 调用设备服务
     * 5 批量调用设备服务
     * 6 查询属性快照
     * 7 查询事件上报记录
     * 8 查询服务调用记录
     * 9 查询事件上报记录
     */
    public void groupOne() {
        /**上线设备*/
        String deviceName = "2pxuAQB2I7wGPmqq1XE3";
        String deviceProductkey = "a1QbjI2Rzhy";

        // 1 设置设备属性
        setDeviceProperty(null, deviceProductkey, deviceName,"{\"WindSpeed_18\":1}");


        // 3 查询设备属性
        queryDevicePropertyData(null, deviceProductkey, deviceName,
            "WindSpeed_18", System.currentTimeMillis() - 2000*90, System.currentTimeMillis(), 10, 1);
        // 4 调用设备服务
        invokeThingService(null, deviceProductkey, deviceName, "P2PSignalDownstream", "{}");

        // 5 批量调用设备服务
        List<String> devicesNames = new ArrayList<>();
        devicesNames.add(deviceName);
        invokeThingsService(null, deviceProductkey, devicesNames, "P2PSignalDownstream", "{}");

        // 6 查询属性快照
        queryDevicePropertyStatus(null, deviceProductkey, deviceName);

        // 7 查询属性上报记录
        List<String> list =new ArrayList<>();
        list.add("ElectricMeterState");
        queryDevicePropertiesData(null,deviceProductkey,deviceName,list,System.currentTimeMillis() - 2000*90,System.currentTimeMillis(),10,1);

        // 8 查询服务调用记录
        queryDeviceServiceData(null,deviceProductkey,deviceName,null,System.currentTimeMillis() - 2000*90,System.currentTimeMillis(),10,1);

        // 9 查询事件上报记录
        queryDeviceEventData(null,deviceProductkey,deviceName,null,null,System.currentTimeMillis() - 2000*90,System.currentTimeMillis(),10,1);
    }


}
