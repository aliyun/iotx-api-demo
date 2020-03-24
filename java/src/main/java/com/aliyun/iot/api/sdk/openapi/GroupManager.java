package com.aliyun.iot.api.sdk.openapi;

import com.alibaba.fastjson.JSON;
import com.aliyun.iot.util.LogUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20180120.*;

import java.util.List;

public class GroupManager extends AbstractManager {

    private static DefaultAcsClient client = AbstractManager.getClient();

    /**
     * 新建分组。
     *
     * @param superGroupId 父组ID
     * @param groupName    分组名称            必须
     * @param groupDesc    分组描述
     * @Des 描述：
     */
    public static CreateDeviceGroupResponse.Data createDeviceGroup(String superGroupId, String groupName,
                                                                   String groupDesc) {
        CreateDeviceGroupResponse response = null;
        CreateDeviceGroupRequest request = new CreateDeviceGroupRequest();
        request.setGroupDesc(groupDesc);
        request.setGroupName(groupName);
        request.setSuperGroupId(superGroupId);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("创建分组成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("创建分组失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("创建分组失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 修改分组信息。
     *
     * @param groupId   分组ID 必须
     * @param groupDesc 分组描述
     * @Des 描述：
     */
    public static void updateDeviceGroup(String groupId, String groupDesc) {

        UpdateDeviceGroupResponse response = null;
        UpdateDeviceGroupRequest request = new UpdateDeviceGroupRequest();
        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("修改分组成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("修改分组失败");
                LogUtil.error(JSON.toJSONString(response));
            }

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("修改分组失败！" + JSON.toJSONString(response));
        }
    }

    /**
     * 删除指定分组。
     *
     * @param groupId 分组ID 必须
     * @Des 描述：
     */
    public static void deleteDeviceGroup(String groupId) {

        DeleteDeviceGroupResponse response = null;
        DeleteDeviceGroupRequest request = new DeleteDeviceGroupRequest();
        request.setGroupId(groupId);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("删除分组成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("删除分组失败");
                LogUtil.error(JSON.toJSONString(response));
            }

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("删除分组失败！" + JSON.toJSONString(response));
        }
    }

    /**
     * 添加设备到某一分组（可批量添加设备）。
     *
     * @param groupId 分组ID 必须
     * @param devices 要添加到分组的设备信息 列表
     * @Des 描述：
     */
    public static void batchAddDeviceGroupRelations(String groupId,
                                                    List<BatchAddDeviceGroupRelationsRequest.Device> devices) {

        BatchAddDeviceGroupRelationsResponse response = null;
        BatchAddDeviceGroupRelationsRequest request2 = new BatchAddDeviceGroupRelationsRequest();
        request2.setGroupId(groupId);
        request2.setDevices(devices);

        try {
            response = client.getAcsResponse(request2);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("设备添加分组成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("设备添加分组失败");
                LogUtil.error(JSON.toJSONString(response));
            }

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("设备添加分组失败！" + JSON.toJSONString(response));
        }
    }

    /**
     * 查询分组列表
     *
     * @param groupName    分组名称    传入分组名称，则根据名称进行精确查询。若不传入此参数，则进行全量分组查询。
     * @param superGroupId 父组ID
     * @param pageSize     每页记录数。最大值是200。  必须
     * @param currentPage  必须
     * @Des 描述：
     */
    public static List<QueryDeviceGroupListResponse.GroupInfo> queryDeviceGroupList(String groupName,
                                                                                    String superGroupId,
                                                                                    Integer pageSize,
                                                                                    Integer currentPage) {
        QueryDeviceGroupListResponse response = null;
        QueryDeviceGroupListRequest request = new QueryDeviceGroupListRequest();
        request.setCurrentPage(currentPage);
        request.setGroupName(groupName);
        request.setSuperGroupId(superGroupId);
        request.setPageSize(pageSize);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("分组列表查询成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("分组列表查询失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("分组列表查询失败！" + JSON.toJSONString(response.getData()));
        }
        return null;
    }

    /**
     * 添加、更新、或删除分组标签
     *
     * @param groupId   分组ID 必须
     * @param tagString JSON格式的标签数据 必须
     * @Des 描述：
     */
    public static void setDeviceGroupTags(String groupId, String tagString) {

        SetDeviceGroupTagsResponse response = null;
        SetDeviceGroupTagsRequest request = new SetDeviceGroupTagsRequest();
        request.setGroupId(groupId);
        request.setTagString(tagString);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("分组标签修改稿成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("分组标签修改稿失败");
                LogUtil.error(JSON.toJSONString(response));
            }
        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("分组标签修改稿失败！" + JSON.toJSONString(response));
        }
    }

    /**
     * 查询分组标签列表
     *
     * @param groupId 分组ID 必须
     * @Des 描述：
     */
    public static List<QueryDeviceGroupTagListResponse.GroupTagInfo> queryDeviceGroupTagList(String groupId) {

        QueryDeviceGroupTagListResponse response = null;
        QueryDeviceGroupTagListRequest request = new QueryDeviceGroupTagListRequest();
        request.setGroupId(groupId);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("查询分组标签列表成功");
                LogUtil.print(JSON.toJSONString(request));
            } else {
                LogUtil.print("分查询分组标签列表失败");
                LogUtil.error(JSON.toJSONString(request));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("查询分组标签列表失败！" + JSON.toJSONString(response.getData()));
        }
        return null;
    }

    /**
     * 某一设备所在的分组列表
     *
     * @param productKey 产品Key
     * @param deviceName 设备名称
     * @Des 描述：
     */
    public static void queryDeviceGroupByDevice(String productKey, String deviceName) {
        QueryDeviceGroupByDeviceResponse response = null;
        QueryDeviceGroupByDeviceRequest request = new QueryDeviceGroupByDeviceRequest();
        request.setDeviceName(deviceName);
        request.setProductKey(productKey);
        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("设备所在的分组列表查询成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("设备所在的分组列表查询失败");
                LogUtil.error(JSON.toJSONString(response));
            }

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("设备所在的分组列表查询失败！" + JSON.toJSONString(response));
        }
    }

    /**
     * 查询分组中的设备列表
     *
     * @param groupId     分组id  必须
     * @param currentPage
     * @param pageSize
     * @Des 描述：
     */
    public static List<QueryDeviceListByDeviceGroupResponse.SimpleDeviceInfo> queryDeviceListByDeviceGroup(
        String groupId, Integer currentPage, Integer pageSize) {

        QueryDeviceListByDeviceGroupResponse response = null;
        QueryDeviceListByDeviceGroupRequest request = null;
        request.setGroupId(groupId);
        request.setCurrentPage(currentPage);
        request.setPageSize(pageSize);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("分组中的设备列表查询成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("分组中的设备列表查询失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("分组中的设备列表查询失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 根据子分组ID查询父分组信息
     *
     * @param groupId 分组id  必须
     * @Des 描述：
     */
    public static List<QuerySuperDeviceGroupResponse.GroupInfo> querySuperDeviceGroup(String groupId) {

        QuerySuperDeviceGroupResponse response = null;
        QuerySuperDeviceGroupRequest request = new QuerySuperDeviceGroupRequest();
        request.setGroupId(groupId);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("父分组信息查询成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("父分组信息查询失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("分父分组信息查询失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 查询分组详情。
     *
     * @param groupId 分组ID，分组的全局唯一标识符。 必需
     */
    public static QueryDeviceGroupInfoResponse.Data queryDeviceGroupInfo(String groupId) {

        QueryDeviceGroupInfoResponse response = null;
        QueryDeviceGroupInfoRequest request = new QueryDeviceGroupInfoRequest();
        request.setGroupId(groupId);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("查询分组详情成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("查询分组详情失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("查询分组详情失败！" + JSON.toJSONString(response));
        }
        return null;

    }

    /**
     * 根据标签查询设备分组。
     *
     * @param tags        分组ID，分组的全局唯一标识符 必需
     * @param currentPage 分组ID，分组的全局唯一标识符 必需
     * @param pageSize    分组ID，分组的全局唯一标识符 必需
     */
    public static List<QueryDeviceGroupByTagsResponse.DeviceGroup> queryDeviceGroupByTags(
        List<QueryDeviceGroupByTagsRequest.Tag> tags, Integer currentPage, Integer pageSize) {

        QueryDeviceGroupByTagsResponse response = null;
        QueryDeviceGroupByTagsRequest request = new QueryDeviceGroupByTagsRequest();

        request.setTags(tags);
        request.setCurrentPage(currentPage);
        request.setPageSize(pageSize);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("父分组信息查询成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("父分组信息查询失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("分父分组信息查询失败！" + JSON.toJSONString(response));
        }
        return null;

    }

}
