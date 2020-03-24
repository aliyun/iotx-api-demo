package com.aliyun.iot.api.sdk.openapi;

import com.alibaba.fastjson.JSON;
import com.aliyun.iot.util.LogUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20180120.*;

import java.util.List;

public class ThingModelManager extends AbstractManager {

    private static DefaultAcsClient client = AbstractManager.getClient();

    /**
     * 设置设备的属性
     *
     * @param iotId      要查询的设备ID
     * @param productKey 设备所隶属的产品Key
     * @param deviceName 设备名称
     * @param items      要设置的属性信息，组成为key:value，数据格式为 JSON String。  必须
     * @Des 描述：
     */
    public static void setDeviceProperty(String iotId, String productKey, String deviceName, String items) {
        SetDevicePropertyResponse response = null;
        SetDevicePropertyRequest request = new SetDevicePropertyRequest();
        request.setDeviceName(deviceName);
        request.setIotId(iotId);
        request.setItems(items);
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("设置设备的属性成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("设置设备的属性失败");
                LogUtil.error(JSON.toJSONString(response));
            }

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("设置设备的属性失败！" + JSON.toJSONString(response));
        }
    }

    /**
     * 批量设置设备属性
     *
     * @param productKey  设置属性的设备所隶属的产品Key
     * @param deviceNames 要设置属性的设备名称列表。目前，最多支持500个设备
     * @param items       要设置的属性信息，组成为key:value，数据格式为 JSON String。  必须
     * @Des 描述：
     */
    public static void setDevicesProperty(String productKey, List<String> deviceNames, String items) {
        SetDevicesPropertyResponse response = new SetDevicesPropertyResponse();
        SetDevicesPropertyRequest request = new SetDevicesPropertyRequest();
        request.setDeviceNames(deviceNames);
        request.setItems(items);
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("批量设置设备属性成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("批量设置设备属性失败");
                LogUtil.error(JSON.toJSONString(response));
            }
        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("批量设置设备属性失败！" + JSON.toJSONString(response));
        }
    }

    /**
     * 在一个设备上执行指定的服务（调用设备服务）
     *
     * @param iotId      要查询的设备ID
     * @param productKey 设备所隶属的产品Key
     * @param deviceName 设备的名称
     * @param identifier 高级版设备的服务Identifier  必须
     * @param args       要启用服务的入参信息    必须
     * @Des 描述：
     */
    public static InvokeThingServiceResponse.Data invokeThingService(String iotId, String productKey, String deviceName,
                                                                     String identifier, String args) {
        InvokeThingServiceResponse response = null;
        InvokeThingServiceRequest request = new InvokeThingServiceRequest();
        request.setArgs(args);
        request.setDeviceName(deviceName);
        request.setIotId(iotId);
        request.setIdentifier(identifier);
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("服务执行成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("服务执行失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("服务执行失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 批量调用设备服务
     *
     * @param iotId       要查询的设备ID  必须
     * @param productKey  设备所隶属的产品Key  必须
     * @param deviceNames 设备的名称列表。最多支持500个设备  必须
     * @param identifier  高级版设备的服务Identifier  必须
     * @param args        要启用服务的入参信息  必须
     * @Des 描述：
     */
    public static void invokeThingsService(String iotId, String productKey, List<String> deviceNames,
                                           String identifier, String args) {
        InvokeThingsServiceResponse response = null;
        InvokeThingsServiceRequest request = new InvokeThingsServiceRequest();
        request.setArgs(args);
        request.setIdentifier(identifier);
        request.setDeviceNames(deviceNames);
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("批量调用设备服务成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("批量调用设备服务失败");
                LogUtil.error(JSON.toJSONString(response));
            }

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("批量调用设备服务失败！" + JSON.toJSONString(response));
        }
    }

    /**
     * 查询指定设备的属性快照
     *
     * @param iotId      要查询的设备ID  必须
     * @param productKey 设备所隶属的产品Key  必须
     * @param deviceName 设备的名称列表。最多支持500个设备  必须
     * @Des 描述：
     */
    public static QueryDevicePropertyStatusResponse.Data queryDevicePropertyStatus(String iotId, String productKey,
                                                                                   String deviceName) {

        QueryDevicePropertyStatusResponse response = null;
        QueryDevicePropertyStatusRequest request = new QueryDevicePropertyStatusRequest();
        request.setDeviceName(deviceName);
        request.setIotId(iotId);
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("批属性快照查询成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("属性快照查询失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("属性快照查询失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 查询指定设备的事件记录
     *
     * @param iotId      要查询的设备ID
     * @param productKey 设备所隶属的产品Key
     * @param deviceName 设备的名称
     * @param eventType  事件类型  info：信息。alert：告警 。 error：故障。
     * @param identifier 事件标识符   必须
     * @param startTime  要查询的事件记录的开始时间    必须
     * @param endTime    要查询的事件记录的结束时间    必须
     * @param pageSize   返回结果中每页显示的记录数
     * @param asc        排序方式
     * @Des 描述：
     */
    public static QueryDeviceEventDataResponse.Data queryDeviceEventData(String iotId, String productKey,
                                                                         String deviceName, String eventType,
                                                                         String identifier,
                                                                         Long startTime, Long endTime, Integer pageSize,
                                                                         Integer asc) {
        QueryDeviceEventDataResponse response = null;
        QueryDeviceEventDataRequest request = new QueryDeviceEventDataRequest();
        request.setAsc(asc);
        request.setDeviceName(deviceName);
        request.setEndTime(endTime);
        request.setEventType(eventType);
        request.setIotId(iotId);
        request.setStartTime(startTime);
        request.setIdentifier(identifier);
        request.setPageSize(pageSize);
        request.setProductKey(productKey);
        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("事件记录查询成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("事件记录查询失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("事件记录查询失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 查询指定设备的属性记录
     *
     * @param iotId      要查询的设备ID
     * @param productKey 设备所隶属的产品Key
     * @param deviceName 设备的名称
     * @param identifier 事件标识符   必须
     * @param startTime  要查询的事件记录的开始时间    必须
     * @param endTime    要查询的事件记录的结束时间    必须
     * @param pageSize   返回结果中每页显示的记录数
     * @param asc        排序方式
     * @Des 描述：
     */
    public static QueryDevicePropertyDataResponse.Data queryDevicePropertyData(String iotId, String productKey,
                                                                               String deviceName,
                                                                               String identifier,
                                                                               Long startTime, Long endTime,
                                                                               Integer pageSize, Integer asc) {
        QueryDevicePropertyDataResponse response = null;
        QueryDevicePropertyDataRequest request = new QueryDevicePropertyDataRequest();
        request.setAsc(asc);
        request.setProductKey(productKey);
        request.setIotId(iotId);
        request.setDeviceName(deviceName);
        request.setIdentifier(identifier);
        request.setStartTime(startTime);
        request.setEndTime(endTime);
        request.setPageSize(pageSize);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("属性记录查询成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("属性记录查询失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("属性记录查询失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 查询指定设备的服务记录
     *
     * @param iotId      要查询的设备ID
     * @param productKey 设备所隶属的产品Key
     * @param deviceName 设备的名称
     * @param identifier 事件标识符   必须
     * @param startTime  要查询的事件记录的开始时间    必须
     * @param endTime    要查询的事件记录的结束时间    必须
     * @param pageSize   返回结果中每页显示的记录数
     * @param asc        排序方式
     * @Des 描述：
     */
    public static QueryDeviceServiceDataResponse.Data queryDeviceServiceData(String iotId, String productKey,
                                                                             String deviceName,
                                                                             String identifier,
                                                                             Long startTime, Long endTime,
                                                                             Integer pageSize, Integer asc) {
        QueryDeviceServiceDataResponse response = null;
        QueryDeviceServiceDataRequest request = new QueryDeviceServiceDataRequest();
        request.setAsc(asc);
        request.setDeviceName(deviceName);
        request.setEndTime(endTime);
        request.setIdentifier(identifier);
        request.setIotId(iotId);
        request.setStartTime(startTime);
        request.setPageSize(pageSize);
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("服务记录查询成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("服务记录查询失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("属性记录查询失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 查询指定设备的属性上报数据
     *
     * @param iotId      要查询的设备ID
     * @param productKey 设备所隶属的产品Key
     * @param deviceName 设备的名称
     * @param startTime  要查询的事件记录的开始时间    必须
     * @param endTime    要查询的事件记录的结束时间    必须
     * @param pageSize   返回结果中每页显示的记录数
     * @param asc        排序方式
     * @Des 描述：
     */
    public static List<QueryDevicePropertiesDataResponse.PropertyDataInfo> queryDevicePropertiesData(String iotId,
                                                                                                     String productKey,
                                                                                                     String deviceName,
                                                                                                     List<String> identifiers,
                                                                                                     Long startTime,
                                                                                                     Long endTime,
                                                                                                     Integer pageSize,
                                                                                                     Integer asc) {
        QueryDevicePropertiesDataResponse response = null;
        QueryDevicePropertiesDataRequest request = new QueryDevicePropertiesDataRequest();
        request.setAsc(asc);
        request.setIotId(iotId);
        request.setProductKey(productKey);
        request.setDeviceName(deviceName);
        request.setIdentifiers(identifiers);
        request.setStartTime(startTime);
        request.setEndTime(endTime);
        request.setPageSize(pageSize);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("查询历史数据成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("查询历史数据失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getPropertyDataInfos();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("属性记录查询失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 为指定设备批量设置期望属性值
     *
     * @param iotId      要设置期望属性值的设备ID
     * @param productKey 产品PK
     * @param deviceName 要设置期望属性值的设备的名称。
     * @param items      要设置的期望属性值，组成为key:value 必需
     * @param versions   当前期望属性值版本，组成为key:value
     */
    public static SetDeviceDesiredPropertyResponse.Data setDeviceDesiredProperty(String iotId, String productKey,
                                                                                 String deviceName, String items,
                                                                                 String versions) {
        SetDeviceDesiredPropertyResponse response = null;
        SetDeviceDesiredPropertyRequest request = new SetDeviceDesiredPropertyRequest();
        request.setIotId(iotId);
        request.setDeviceName(deviceName);
        request.setProductKey(productKey);
        request.setItems(items);
        request.setVersions(versions);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("批量设置期望属性值成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("批量设置期望属性值失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("批量设置期望属性值失败！" + JSON.toJSONString(response));
        }
        return null;

    }

    /**
     * 查询指定设备的期望属性值。
     *
     * @param iotId       要查询的设备ID
     * @param productKey  要查询的设备所隶属的产品Key
     * @param deviceName  要查询的设备的名称
     * @param identifiers 要查询期望值的属性的标识符 (identifier) 列表
     */
    public static QueryDeviceDesiredPropertyResponse.Data queryDeviceDesiredProperty(String iotId, String productKey,
                                                                                     String deviceName,
                                                                                     List<String> identifiers) {

        QueryDeviceDesiredPropertyResponse response = null;
        QueryDeviceDesiredPropertyRequest request = new QueryDeviceDesiredPropertyRequest();
        request.setIotId(iotId);
        request.setProductKey(productKey);
        request.setDeviceName(deviceName);
        request.setIdentifiers(identifiers);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("查询指定设备的期望属性值成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("查询指定设备的期望属性值失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("查询指定设备的期望属性值失败！" + JSON.toJSONString(response));
        }
        return null;

    }

}
