package com.aliyun.iot.api.sdk.openapi;

import com.alibaba.fastjson.JSON;
import com.aliyun.iot.util.LogUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20180120.*;

import java.util.List;

public class DeviceManager extends AbstractManager {

    private static DefaultAcsClient client = AbstractManager.getClient();

    /**
     * 注册设备
     *
     * @param productKey 产品名称  必须
     * @param deviceName 设备命名  非必须
     * @return 产品创建信息
     */
    public static RegisterDeviceResponse.Data registerDevice(String productKey, String deviceName) {

        RegisterDeviceResponse response = null;

        RegisterDeviceRequest request = new RegisterDeviceRequest();
        request.setDeviceName(deviceName);
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("注册设备成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("注册设备失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("注册设备失败！" + JSON.toJSONString(response.getData()));
        }
        return null;
    }

    /**
     * 删除设备。
     *
     * @param productKey 产品名称  非必须
     * @param deviceName 设备的名称  非必须
     * @param iotId      设备ID    非必须
     * @return 产品创建信息
     */
    public static void deleteDevice(String iotId, String productKey, String deviceName) {

        DeleteDeviceResponse response = null;

        DeleteDeviceRequest request = new DeleteDeviceRequest();
        request.setProductKey(productKey);
        request.setIotId(iotId);
        request.setDeviceName(deviceName);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("删除设备成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("删除设备失败");
                LogUtil.error(JSON.toJSONString(response));
            }

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("删除设备失败！" + JSON.toJSONString(response));
        }
    }

    /**
     * 查询指定设备的详细信息
     *
     * @param iotId      设备ID  非必须
     * @param productKey 设备所隶属的产品Key。  非必须
     * @param deviceName 设备名  非必须
     * @return 产品创建信息
     */
    public static QueryDeviceDetailResponse.Data queryDeviceDetail(String iotId, String productKey, String deviceName) {

        QueryDeviceDetailResponse response = null;
        QueryDeviceDetailRequest request = new QueryDeviceDetailRequest();
        request.setIotId(iotId);
        request.setDeviceName(deviceName);
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("查询设备详细信息成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("查询设备详细信息失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("查询设备详细信息失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 查询指定产品下的所有设备列表。
     *
     * @param productKey  产品名称  必须
     * @param pageSize    设备命名  非必须
     * @param currentPage 设备命名  非必须
     * @return 产品创建信息
     */
    public static List<QueryDeviceResponse.DeviceInfo> queryDevice(String productKey, Integer pageSize,
                                                                   Integer currentPage) {

        QueryDeviceResponse response = null;
        QueryDeviceRequest request = new QueryDeviceRequest();
        request.setProductKey(productKey);
        request.setCurrentPage(currentPage);
        request.setPageSize(pageSize);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("产品下设备列表查询成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("产品下设备列表查询失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("产品下设备列表查询失败！" + JSON.toJSONString(response.getData()));
        }
        return null;
    }

    /**
     * 批量注册多个设备
     *
     * @param productKey 产品名称  必须
     * @param count      设备的数量 必须
     * @Des 描述：
     */
    public static BatchRegisterDeviceResponse.Data batchRegisterDevice(String productKey, Integer count) {

        BatchRegisterDeviceResponse response = null;
        BatchRegisterDeviceRequest registerDeviceRequest = new BatchRegisterDeviceRequest();
        registerDeviceRequest.setCount(count);
        registerDeviceRequest.setProductKey(productKey);

        try {
            response = client.getAcsResponse(registerDeviceRequest);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("批量命名设备成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("批量命名设备失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("批量命名设备失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 在指定产品下批量自定义设备名称
     *
     * @param productKey  产品名称  非必须
     * @param deviceNames 设备的名称  非必须 描述： 该接口需要和BatchRegisterDeviceWithApplyId接口结合使用，实现在一个产品下批量注册（即新建）
     *                    多个设备，并且为每个设备单独命名。单次调用，最多能传入1,000 个设备名称。
     */
    public static BatchCheckDeviceNamesResponse.Data batchCheckDeviceNames(String productKey,
                                                                           List<String> deviceNames) {

        BatchCheckDeviceNamesResponse response = null;
        BatchCheckDeviceNamesRequest request = new BatchCheckDeviceNamesRequest();
        request.setDeviceNames(deviceNames);
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("批量命名设备成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("批量命名设备失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("批量命名设备失败！" + JSON.toJSONString(response.getData()));
        }
        return null;
    }

    /**
     * 根据申请批次ID（ApplyId）批量注册设备
     *
     * @param productKey 产品名称  必须
     * @param applyId    要批量注册的设备的申请批次ID。申请批次ID由调用BatchCheckDeviceNames接口返回。  必须
     * @Des 描述： 该接口需要和BatchCheckDeviceNames接口结合使用，实现在一个产品下批量注册（即新建）多个设备，并且为每个设备单独命名。
     */
    public static BatchRegisterDeviceWithApplyIdResponse.Data batchRegisterDeviceWithApplyId(String productKey,
                                                                                             Long applyId) {

        BatchRegisterDeviceWithApplyIdResponse response = null;
        BatchRegisterDeviceWithApplyIdRequest request = new BatchRegisterDeviceWithApplyIdRequest();
        request.setApplyId(applyId);
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("批量注册设备成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("批量注册设备失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("批量注册设备失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 查询批量注册设备申请的处理状态和结果
     *
     * @param productKey 产品名称  必须
     * @param applyId    设备的数量 必须
     * @Des 描述：
     */
    public static QueryBatchRegisterDeviceStatusResponse.Data queryBatchRegisterDeviceStatus(String productKey,
                                                                                             Long applyId) {
        QueryBatchRegisterDeviceStatusResponse response = null;
        QueryBatchRegisterDeviceStatusRequest request = new QueryBatchRegisterDeviceStatusRequest();
        request.setApplyId(applyId);
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("查询批量注册设备申请成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("查询批量注册设备申请失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("查询批量注册设备申请失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 查询批量注册的设备信息
     *
     * @param applyId     产品名称  必须
     * @param pageSize    必须
     * @param currentPage 必须
     * @Des 描述：
     */
    public static List<QueryPageByApplyIdResponse.ApplyDeviceInfo> queryPageByApplyId(Long applyId, Integer pageSize,
                                                                                      Integer currentPage) {
        QueryPageByApplyIdResponse response = null;
        QueryPageByApplyIdRequest request = new QueryPageByApplyIdRequest();
        request.setApplyId(applyId);
        request.setCurrentPage(currentPage);
        request.setPageSize(pageSize);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("查询批量注册的设备信息成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("查询批量注册的设备信息失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getApplyDeviceList();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("查询批量注册的设备信息失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 查询指定产品下的设备统计数据
     *
     * @param productKey 设备所隶属的产品Key
     * @Des 描述：
     */
    public static QueryDeviceStatisticsResponse.Data queryDeviceStatistics(String productKey) {

        QueryDeviceStatisticsResponse response = new QueryDeviceStatisticsResponse();
        QueryDeviceStatisticsRequest request = new QueryDeviceStatisticsRequest();
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("查询批量注册的设备信息成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("查询批量注册的设备信息失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("查询批量注册的设备信息失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /********************************操作设备***************************/

    /**
     * 查看指定设备的运行状态
     *
     * @param productKey 产品名称  非必须
     * @param deviceName 设备的名称  非必须
     * @param iotId      设备ID    非必须
     * @return 产品创建信息
     */
    public static GetDeviceStatusResponse.Data getDeviceStatus(String productKey, String deviceName, String iotId) {

        GetDeviceStatusResponse response = null;
        GetDeviceStatusRequest request = new GetDeviceStatusRequest();
        request.setProductKey(productKey);
        request.setDeviceName(deviceName);
        request.setIotId(iotId);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("设备状态查询成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("设备状态查询失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("设备状态查询失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 批量查看指定设备的运行状态
     *
     * @param productKey  产品名称  非必须
     * @param deviceNames 设备的名称  非必须
     * @return 产品创建信息
     */
    public static List<BatchGetDeviceStateResponse.DeviceStatus> batchGetDeviceState(String productKey,
                                                                                     List<String> deviceNames) {

        BatchGetDeviceStateResponse response = null;
        BatchGetDeviceStateRequest request = new BatchGetDeviceStateRequest();
        request.setDeviceNames(deviceNames);
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("批量设备状态查询成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("批量设备状态查询失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getDeviceStatusList();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("批量设备状态查询失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 禁用指定设备
     *
     * @param iotId      设备的名称  非必须
     * @param productKey 产品名称  非必须
     * @param deviceName 设备的名称  非必须
     * @return 产品创建信息
     */
    public static void disableThing(String iotId, String productKey, String deviceName) {
        DisableThingResponse response = null;
        DisableThingRequest request = new DisableThingRequest();
        request.setDeviceName(deviceName);
        request.setIotId(iotId);
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("禁用设备成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("禁用设备失败");
                LogUtil.error(JSON.toJSONString(response));
            }

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("禁用设备失败！" + JSON.toJSONString(response));
        }
    }

    /**
     * 启用指定设备
     *
     * @param iotId      设备的名称  非必须
     * @param productKey 产品名称  非必须
     * @param deviceName 设备的名称  非必须
     * @return 产品创建信息
     */
    public static void enableThing(String iotId, String productKey, String deviceName) {
        EnableThingResponse response = null;
        EnableThingRequest request = new EnableThingRequest();
        request.setIotId(iotId);
        request.setDeviceName(deviceName);
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("启用设备成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("启用设备失败");
                LogUtil.error(JSON.toJSONString(response));
            }

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("启用设备失败！" + JSON.toJSONString(response));
        }
    }

    /**
     * 批量查询设备详情。
     *
     * @param productKey 产品PK 必需
     * @param deviceName 指定要查询的设备名称列表。最多可包含100个设备名称。必需
     */
    public static List<BatchQueryDeviceDetailResponse.DataItem> batchQueryDeviceDetail(String productKey,
                                                                                       List<String> deviceName) {

        BatchQueryDeviceDetailResponse response = null;
        BatchQueryDeviceDetailRequest request = new BatchQueryDeviceDetailRequest();
        request.setProductKey(productKey);
        request.setDeviceNames(deviceName);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("批量查询设备详情成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("批量查询设备详情失败");
                LogUtil.error(JSON.toJSONString(response));
            }

            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("批量查询设备详情失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 查询指定设备上传到物联网平台的所有文件。
     *
     * @param iotId       要查询的设备ID，设备唯一标识符。
     * @param productKey  产品PK
     * @param deviceName  要查询的设备的名称
     * @param pageSize    产品PK 必需
     * @param currentPage 产品PK 必需
     */
    public static List<QueryDeviceFileListResponse.FileSummary> queryDeviceFileList(String iotId, String productKey,
                                                                                    String deviceName, Integer pageSize,
                                                                                    Integer currentPage) {
        QueryDeviceFileListResponse response = null;
        QueryDeviceFileListRequest request = new QueryDeviceFileListRequest();
        request.setProductKey(productKey);
        request.setIotId(iotId);
        request.setProductKey(productKey);
        request.setDeviceName(deviceName);
        request.setCurrentPage(currentPage);
        request.setPageSize(pageSize);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("查询指定设备上传到物联网平台的所有文件成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("查询指定设备上传到物联网平台的所有文件失败");
                LogUtil.error(JSON.toJSONString(response));
            }

            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("查询指定设备上传到物联网平台的所有文件失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 查询指定设备上传到物联网平台的指定文件信息。
     *
     * @param iotId      要查询的设备ID，设备唯一标识符。
     * @param productKey 产品PK
     * @param deviceName 要查询的设备的名称
     * @param fileId     文件标识符 必需
     */
    public static QueryDeviceFileResponse.Data queryDeviceFile(String iotId, String productKey, String deviceName,
                                                               String fileId) {
        QueryDeviceFileResponse response = null;
        QueryDeviceFileRequest request = new QueryDeviceFileRequest();
        request.setIotId(iotId);
        request.setProductKey(productKey);
        request.setDeviceName(deviceName);
        request.setFileId(fileId);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("查询指定设备上传到物联网平台的指定文件信息成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("查询指定设备上传到物联网平台的指定文件信息失败");
                LogUtil.error(JSON.toJSONString(response));
            }

            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("查询指定设备上传到物联网平台的指定文件信息失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 删除指定设备上传到物联网平台的指定文件。
     *
     * @param iotId      要查询的设备ID，设备唯一标识符。
     * @param productKey 产品PK
     * @param deviceName 要查询的设备的名称
     * @param fileId     文件标识符 必需
     */
    public static void deleteDeviceFile(String iotId, String productKey, String deviceName, String fileId) {
        DeleteDeviceFileResponse response = null;
        DeleteDeviceFileRequest request = new DeleteDeviceFileRequest();
        request.setIotId(iotId);
        request.setProductKey(productKey);
        request.setDeviceName(deviceName);
        request.setFileId(fileId);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("删除指定设备上传到物联网平台的指定文件成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("删除指定设备上传到物联网平台的指定文件失败");
                LogUtil.error(JSON.toJSONString(response));
            }

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("删除指定设备上传到物联网平台的指定文件失败！" + JSON.toJSONString(response));
        }
    }

    /**
     * 批量更新设备备注名称
     *
     * @param deviceNicknameInfos 包含设备标识参数（ProductKey和DeviceName组合或IotId）和备注名称参数（Nickname） 必需
     */
    public static void batchUpdateDeviceNickname(
        List<BatchUpdateDeviceNicknameRequest.DeviceNicknameInfo> deviceNicknameInfos) {
        BatchUpdateDeviceNicknameResponse response = null;
        BatchUpdateDeviceNicknameRequest request = new BatchUpdateDeviceNicknameRequest();

        request.setDeviceNicknameInfos(deviceNicknameInfos);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("批量更新设备备注名称成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("批量更新设备备注名称失败");
                LogUtil.error(JSON.toJSONString(response));
            }

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("批量更新设备备注名称失败！" + JSON.toJSONString(response));
        }

    }

    /**
     * 查询账号下的LoRaWAN入网凭证列表。
     */
    public static List<QueryLoRaJoinPermissionsResponse.JoinPermission> queryLoRaJoinPermissions() {
        QueryLoRaJoinPermissionsResponse response = null;
        QueryLoRaJoinPermissionsRequest request = new QueryLoRaJoinPermissionsRequest();

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("查询账号下的LoRaWAN入网凭证列表成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("查询账号下的LoRaWAN入网凭证列表失败");
                LogUtil.error(JSON.toJSONString(response));
            }

            return response.getJoinPermissions();
        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("查询账号下的LoRaWAN入网凭证列表失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 创建批量注册LoRaWAN设备的任务。
     *
     * @param productKey  产品PK 必需
     * @param deviceInfos LoRaWAN设备信息列表 必需                   \
     */
    public static CreateLoRaNodesTaskResponse createLoRaNodesTask(String productKey,
                                                                  List<CreateLoRaNodesTaskRequest.DeviceInfo> deviceInfos) {
        CreateLoRaNodesTaskResponse response = null;
        CreateLoRaNodesTaskRequest request = new CreateLoRaNodesTaskRequest();
        request.setProductKey(productKey);
        request.setDeviceInfos(deviceInfos);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("创建批量注册LoRaWAN设备的任务成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("创建批量注册LoRaWAN设备的任务失败");
                LogUtil.error(JSON.toJSONString(response));
            }

            return response;

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("创建批量注册LoRaWAN设备的任务失败！" + JSON.toJSONString(response));
        }
        return null;

    }

    /**
     * 查询批量注册LoRaWAN设备任务的状态。
     *
     * @param taskId 注册LoRaWAN设备任务的ID 必需
     */
    public static GetLoraNodesTaskResponse getLoraNodesTask(String taskId) {
        GetLoraNodesTaskResponse response = null;
        GetLoraNodesTaskRequest request = new GetLoraNodesTaskRequest();
        request.setTaskId(taskId);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("查询批量注册LoRaWAN设备任务的状态成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("查询批量注册LoRaWAN设备任务的状态失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response;

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("查询批量注册LoRaWAN设备任务的状态失败！" + JSON.toJSONString(response));
        }
        return null;

    }

}
