package com.aliyun.iot.api.sdk.openapi;

import com.alibaba.fastjson.JSON;
import com.aliyun.iot.util.LogUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20180120.*;

import java.util.List;

public class TopicManager {

    private static DefaultAcsClient client = AbstractManager.getClient();

    /**
     * 查询产品Topic类
     *
     * @param productKey 产品pk
     */
    public static QueryProductTopicResponse queryProductTopic(String productKey) {

        QueryProductTopicResponse response = new QueryProductTopicResponse();

        QueryProductTopicRequest request = new QueryProductTopicRequest();
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("查询产品Topic类成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("查询产品Topic类失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response;

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("查询产品Topic类失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 创建产品Topic类
     *
     * @param productKey     产品pk
     * @param topicShortName 设置Topic类的自定义类目名称
     * @param operation      设备对该Topic类的操作权限，取值：  SUB：订阅。 PUB：发布。 ALL：发布和订阅。
     * @param desc           Topic类的描述信息
     */
    public static CreateProductTopicResponse createProductTopic(String productKey, String topicShortName,
                                                                String operation, String desc) {

        CreateProductTopicResponse response = null;

        CreateProductTopicRequest request = new CreateProductTopicRequest();
        request.setProductKey(productKey);
        request.setDesc(desc);
        request.setTopicShortName(topicShortName);
        request.setOperation(operation);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("创建产品Topic类");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("创建产品Topic类失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response;

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("创建产品Topic类失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 修改产品Topic类
     *
     * @param topicId        要修改的Topic类的 ID
     * @param topicShortName 设置Topic类的自定义类目名称
     * @param operation      设备对该Topic类的操作权限,取值：  SUB：订阅。 PUB：发布。 ALL：发布和订阅
     * @param desc           Topic类的描述信息。长度限制为100字符（一个汉字占一个字符
     */
    public static UpdateProductTopicResponse updateProductTopic(String topicId, String topicShortName, String operation,
                                                                String desc) {

        UpdateProductTopicResponse response = null;

        UpdateProductTopicRequest request = new UpdateProductTopicRequest();
        request.setTopicId(topicId);
        request.setTopicShortName(topicShortName);
        request.setOperation(operation);
        request.setDesc(desc);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("修改产品Topic类");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("修改产品Topic类失败");
                LogUtil.error(JSON.toJSONString(response));
            }

            return response;

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("修改产品Topic类失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 删除产品Topic类
     *
     * @param topicId 要删除的Topic类的 ID
     */
    public static DeleteProductTopicResponse deleteProductTopic(String topicId) {

        DeleteProductTopicResponse response = null;

        DeleteProductTopicRequest request = new DeleteProductTopicRequest();
        request.setTopicId(topicId);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("删除产品Topic类");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("删除产品Topic类失败");
                LogUtil.error(JSON.toJSONString(response));
            }

            return response;
        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("删除产品Topic类失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 添加Topic路由表
     *
     * @param srcTopic 源Topic，即被订阅的Topic
     * @param dstTopic 目标Topic列表，即从SrcTopic订阅消息的Topic列表
     */
    public static CreateTopicRouteTableResponse createTopicRouteTable(String srcTopic, List<String> dstTopic) {

        CreateTopicRouteTableResponse response = null;

        CreateTopicRouteTableRequest request = new CreateTopicRouteTableRequest();
        request.setDstTopics(dstTopic);
        request.setSrcTopic(srcTopic);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("添加Topic路由表");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("添加Topic路由表失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response;

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("添加Topic路由表失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 查询Topic路由表
     *
     * @param topic 要查询的源Topic，即发布消息的Topic
     */
    public static QueryTopicRouteTableResponse queryTopicRouteTable(String topic) {

        QueryTopicRouteTableResponse response = null;

        QueryTopicRouteTableRequest request = new QueryTopicRouteTableRequest();
        request.setTopic(topic);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("查询Topic路由表");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("查询Topic路由表失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response;

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("查询Topic路由表失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 查询Topic反向路由表
     *
     * @param topic 要查询的源Topic，即发布消息的Topic
     */
    public static QueryTopicReverseRouteTableResponse queryTopicReverseRouteTable(String topic) {

        QueryTopicReverseRouteTableResponse response = null;

        QueryTopicReverseRouteTableRequest request = new QueryTopicReverseRouteTableRequest();
        request.setTopic(topic);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("查询Topic反向路由表");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("查询Topic反向路由表失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response;

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("查询Topic反向路由表失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 删除Topic路由表。
     *
     * @param srcTopic 源Topic，即被订阅的Topic
     * @param dstTopic 目标Topic列表，即从SrcTopic订阅消息的Topic列表
     */
    public static DeleteTopicRouteTableResponse deleteTopicRouteTable(String srcTopic, List<String> dstTopic) {

        DeleteTopicRouteTableResponse response = null;

        DeleteTopicRouteTableRequest request = new DeleteTopicRouteTableRequest();
        request.setDstTopics(dstTopic);
        request.setSrcTopic(srcTopic);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("删除Topic路由表");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("删除Topic路由表失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response;

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("删除Topic路由表失败！" + JSON.toJSONString(response));
        }
        return null;
    }

}
