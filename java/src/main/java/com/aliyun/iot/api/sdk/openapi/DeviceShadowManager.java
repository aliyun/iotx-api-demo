package com.aliyun.iot.api.sdk.openapi;

import com.alibaba.fastjson.JSON;
import com.aliyun.iot.util.LogUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20180120.*;

public class DeviceShadowManager {

    private static DefaultAcsClient client = AbstractManager.getClient();

    /**
     * 调用该接口查询指定设备的影子信息
     *
     * @param productKey 要查询的设备所隶属的产品Key。
     * @param deviceName 要查询的设备名称
     */
    public static GetDeviceShadowResponse getDeviceShadow(String productKey, String deviceName) {
        GetDeviceShadowResponse response = null;
        GetDeviceShadowRequest request = new GetDeviceShadowRequest();
        request.setDeviceName(deviceName);
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("查询指定设备的影子信息成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("查询指定设备的影子信息失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response;

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("查询指定设备的影子信息失败！" + JSON.toJSONString(response));
        }

        return null;
    }

    /**
     * 调用该接口修改指定设备的影子信息
     *
     * @param productKey    要查询的设备所隶属的产品Key。
     * @param deviceName    要查询的设备名称
     * @param shadowMessage 修改后的设备影子信息
     */
    public static UpdateDeviceShadowResponse updateDeviceShadow(String productKey, String deviceName,
                                                                String shadowMessage) {
        UpdateDeviceShadowResponse response = null;
        UpdateDeviceShadowRequest request = new UpdateDeviceShadowRequest();
        request.setDeviceName(deviceName);
        request.setShadowMessage(shadowMessage);
        request.setProductKey(productKey);
        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("修改指定设备的影子信息成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("修改指定设备的影子信息失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response;

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("修改指定设备的影子信息失败！" + JSON.toJSONString(response));
        }

        return null;
    }
}
