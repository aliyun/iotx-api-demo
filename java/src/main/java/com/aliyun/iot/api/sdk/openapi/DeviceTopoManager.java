package com.aliyun.iot.api.sdk.openapi;

import com.alibaba.fastjson.JSON;
import com.aliyun.iot.util.LogUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20180120.*;

public class DeviceTopoManager extends AbstractManager {

    private static DefaultAcsClient client = AbstractManager.getClient();

    /**
     * 查询网关设备或子设备所具有的拓扑关系。
     *
     * @param iotId      设备所隶属的产品Key
     * @param productKey 产品Key
     * @param deviceName 设备的名称
     * @param pageSize   返回结果中每页显示的记录数量。数量限制：每页最多可显示50条记录。            必须
     * @param pageNo     设备标签键值            必须
     * @Des 描述：
     */
    public static GetThingTopoResponse.Data getThingTopo(String iotId, String productKey, String deviceName,
                                                         Integer pageSize, Integer pageNo) {

        GetThingTopoResponse response = null;
        GetThingTopoRequest request = new GetThingTopoRequest();
        request.setDeviceName(deviceName);
        request.setIotId(iotId);
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("拓扑关系查询成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("拓扑关系查询失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("拓扑关系查询失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 根据挂载的子设备信息，查询对应的网关设备信息
     *
     * @param iotId      要查询的设备ID  必须
     * @param productKey 设备所隶属的产品Key  必须
     * @param deviceName 设备的名称列表。最多支持500个设备  必须
     * @Des 描述：
     */
    public static GetGatewayBySubDeviceResponse.Data getGatewayBySubDevice(String iotId, String productKey,
                                                                           String deviceName) {

        GetGatewayBySubDeviceRequest request = new GetGatewayBySubDeviceRequest();
        request.setIotId(iotId);
        request.setDeviceName(deviceName);
        request.setProductKey(productKey);

        GetGatewayBySubDeviceResponse response = null;

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("子设备拓扑关系成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("子设备拓扑关系失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("子设备拓扑关系失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 网关设备增加拓扑关系。
     *
     * @param gwIotId       设备所隶属的产品Key
     * @param gwProductKey  网关设备所隶属的产品Ke
     * @param gwDeviceName  要通知的网关设备的名称，即网关类型设备的DeviceName
     * @param deviceListStr 要挂载在目标网关设备上的子设备数组，为JSON字符串形式
     * @Des 描述：
     */
    public static NotifyAddThingTopoResponse.Data notifyAddThingTopo(String gwIotId, String gwProductKey,
                                                                     String gwDeviceName,
                                                                     String deviceListStr) {

        NotifyAddThingTopoResponse response = null;
        NotifyAddThingTopoRequest request = new NotifyAddThingTopoRequest();
        request.setDeviceListStr(deviceListStr);
        request.setGwDeviceName(gwDeviceName);
        request.setGwIotId(gwIotId);
        request.setGwProductKey(gwProductKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("增加拓扑关系成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("增加拓扑关系失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("增加拓扑关系失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 移除网关设备或子设备所具有的拓扑关系
     *
     * @param iotId
     * @param productKey
     * @param deviceName
     * @Des 描述：
     */
    public static RegisterDeviceResponse.Data removeThingTopo(String iotId, String productKey, String deviceName) {
        RegisterDeviceResponse response = null;
        RegisterDeviceRequest request = new RegisterDeviceRequest();
        request.setProductKey(productKey);
        request.setDeviceName(deviceName);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("移除拓扑关系成功成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("移除拓扑关系成功失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("移除拓扑关系成功失败！" + JSON.toJSONString(response));
        }
        return null;
    }
}
