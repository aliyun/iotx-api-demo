package com.aliyun.iot.api.sdk.openapi;

import com.alibaba.fastjson.JSON;
import com.aliyun.iot.util.LogUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20180120.*;

public class MessageBrokerManager {

    private static DefaultAcsClient client = AbstractManager.getClient();

    /**
     * 调用该接口向指定Topic发布消息
     *
     * @param productKey     产品名称  要发送消息产品Key。
     * @param topicFullName  要接收消息的Topic全称。可以是用户Topic类和您自定义的Topic类
     * @param messageContent 要发送的消息主体。您需要将消息原文转换成二进制数据，并进行Base64编码，从而生成消息主体。
     * @param qos            指定消息的发送方式。取值： 0：最多发送一次。1：最少发送一次。
     */
    public static PubResponse pub(String productKey, String topicFullName, String messageContent,
                                  Integer qos) {

        PubResponse response = null;

        PubRequest request = new PubRequest();
        request.setProductKey(productKey);
        request.setMessageContent(messageContent);
        request.setTopicFullName(topicFullName);
        request.setQos(qos);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("发布消息成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("发布消息失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response;

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("发布消息失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 调用该接口向订阅了指定Topic的所有设备发布广播消息
     *
     * @param productKey     要发送广播消息的产品Key。
     * @param topicFullName  要接收广播消息的Topic全称。格式为：/broadcast/${productKey}/自定义字段。其中，${productKey}是要接收广播消息的具体产品Key
     *                       ；自定义字段中您可以指定任意字段。
     * @param messageContent 要发送的消息主体。您需要将消息原文转换成二进制数据，并进行Base64编码，从而生成消息主体。
     */
    public static PubBroadcastResponse pubBroadcast(String productKey, String topicFullName, String messageContent) {

        PubBroadcastResponse response = null;

        PubBroadcastRequest request = new PubBroadcastRequest();
        request.setMessageContent(messageContent);
        request.setTopicFullName(topicFullName);
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("发布广播消息成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("发布广播消息失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response;

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("发布广播消息失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 调用该接口向指定设备发送请求消息，并同步返回响应。
     *
     * @param productKey        要发送消息的产品Key
     * @param deviceName        要接收消息的设备名称。
     * @param requestBase64Byte 要发送的请求消息内容经过Base64编码得到的字符串格式数据
     * @param timeout           等待设备回复消息的时间，单位是毫秒，取值范围是1,000 ~5,000。
     * @param topic             使用自定义的RRPC相关Topic。需要设备端配合使用，请参见设备端开发自定义Topic。不传入此参数，则使用系统默认的RRPC Topic。
     */
    public static RRpcResponse rrpc(String productKey, String deviceName, String requestBase64Byte, Integer timeout,
                                    String topic) {

        RRpcResponse response = null;
        RRpcRequest request = new RRpcRequest();
        request.setProductKey(productKey);
        request.setDeviceName(deviceName);
        request.setRequestBase64Byte(requestBase64Byte);
        request.setTimeout(timeout);
        request.setTopic(topic);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("向指定设备发送请求消息，并同步返回响应成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("向指定设备发送请求消息，并同步返回响应失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response;

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("向指定设备发送请求消息，并同步返回响应失败！" + JSON.toJSONString(response));
        }
        return null;
    }

}
