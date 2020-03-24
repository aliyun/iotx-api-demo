package com.aliyun.iot.api.sdk.openapi;

import com.alibaba.fastjson.JSON;
import com.aliyun.iot.util.LogUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20180120.*;

import java.util.List;

public class DataAPIManager extends AbstractManager {

    private static DefaultAcsClient client = AbstractManager.getClient();

    /**
     * 创建数据算法服务API。
     *
     * @param apiPath        API调用地址的自定义部分。作为API资源标识符，需具有全局唯一性。 必需
     * @param displayName    API的显示名称，需具有全局唯一性。仅支持中文汉字、英文字母、数字、下划线（_）、连接符（-）、英文括号（()）和空格，长度不超过20个字符。 必需
     * @param folderId       保存API的文件夹。 必需
     * @param desc           API的描述。 必需
     * @param originSql      API对应的原始SQL，指定数据开发的SQL样式。 必需
     * @param templateSql    服务的模板SQL，即原始SQL的模板化。 必需
     * @param requestParams  调用API的请求参数列表
     * @param responseParams API的响应参数列表。
     */
    public static CreateDataAPIServiceResponse createDataAPIService(String apiPath, String displayName, String folderId,
                                                                    String desc, String originSql, String templateSql,
                                                                    List<CreateDataAPIServiceRequest.RequestParam> requestParams,
                                                                    List<CreateDataAPIServiceRequest.ResponseParam> responseParams) {
        CreateDataAPIServiceResponse response = null;
        CreateDataAPIServiceRequest request = new CreateDataAPIServiceRequest();

        request.setApiPath(apiPath);
        request.setDisplayName(displayName);
        request.setFolderId(folderId);
        request.setDesc(desc);
        request.setOriginSql(originSql);
        request.setTemplateSql(templateSql);
        request.setRequestParams(requestParams);
        request.setResponseParams(responseParams);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("创建数据算法服务API成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("创建数据算法服务API失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response;

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("创建数据算法服务API失败！" + JSON.toJSONString(response));
        }

        return null;

    }

    /**
     * 获取数据算法服务API详情。
     *
     * @param apiSrn API资源标识符，API的全局唯一标识。 必需
     */
    public static GetDataAPIServiceDetailResponse getDataAPIServiceDetail(String apiSrn) {

        GetDataAPIServiceDetailResponse response = null;
        GetDataAPIServiceDetailRequest request = new GetDataAPIServiceDetailRequest();
        request.setApiSrn(apiSrn);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("获取数据算法服务API详情成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("获取数据算法服务API详情失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response;

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("修改指定设备的影子信息失败！" + JSON.toJSONString(response));
        }

        return null;

    }

    /**
     * 调用数据算法服务API，获取SQL查询结果。
     *
     * @param apiSrn API资源标识符，API的全局唯一标识。 必需
     * @param params 调用API的请求参数，需根据您调用CreateDataAPIService创建API时，定义的RequestParam传入请求参数。 必需
     */
    public static InvokeDataAPIServiceResponse invokeDataAPIService(String apiSrn,
                                                                    List<InvokeDataAPIServiceRequest.Param> params) {

        InvokeDataAPIServiceResponse response = null;
        InvokeDataAPIServiceRequest request = new InvokeDataAPIServiceRequest();

        request.setApiSrn(apiSrn);
        request.setParams(params);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("获取SQL查询结果成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("获取SQL查询结果失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response;

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("获取SQL查询结果失败！" + JSON.toJSONString(response));
        }

        return null;
    }

}
