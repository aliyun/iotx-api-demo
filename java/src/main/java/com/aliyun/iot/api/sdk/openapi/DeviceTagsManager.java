package com.aliyun.iot.api.sdk.openapi;

import com.alibaba.fastjson.JSON;
import com.aliyun.iot.util.LogUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20180120.*;

import java.util.List;

public class DeviceTagsManager extends AbstractManager {
    private static DefaultAcsClient client = AbstractManager.getClient();

    /**
     * 查询指定设备的标签列表
     *
     * @param productKey 设备所隶属的产品Key  必须
     * @param deviceName 设备名称            必须
     * @Des 描述：
     */
    public static void queryDeviceProp(String productKey, String deviceName) {

        QueryDevicePropResponse response = null;
        QueryDevicePropRequest request = new QueryDevicePropRequest();
        request.setDeviceName(deviceName);
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("设备标签查询成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("设备标签查询失败");
                LogUtil.error(JSON.toJSONString(response));
            }

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("设备标签查询失败！" + JSON.toJSONString(response));
        }
    }

    /**
     * 为指定设备设置标签
     *
     * @param productKey 设备所隶属的产品Key  必须
     * @param deviceName 设备名称            必须
     * @param props      设置的设备标签            必须
     * @Des 描述：
     */
    public static void saveDeviceProp(String productKey, String deviceName, String props) {

        SaveDevicePropResponse response = null;
        SaveDevicePropRequest request = new SaveDevicePropRequest();
        request.setDeviceName(deviceName);
        request.setProductKey(productKey);
        request.setProps(props);
        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("设备标签设置成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("设备标签设置失败");
                LogUtil.error(JSON.toJSONString(response));
            }

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("设备标签设置失败！" + JSON.toJSONString(response));
        }
    }

    /**
     * 删除设备下的指定标签
     *
     * @param productKey 设备所隶属的产品Key  必须
     * @param deviceName 设备名称            必须
     * @param propKey    设备标签键值            必须
     * @Des 描述：
     */
    public static void deleteDeviceProp(String productKey, String deviceName, String propKey) {

        DeleteDevicePropResponse response = null;
        DeleteDevicePropRequest request = new DeleteDevicePropRequest();
        request.setDeviceName(deviceName);
        request.setProductKey(productKey);
        request.setPropKey(propKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("设备标签删除成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("设备标签删除失败");
                LogUtil.error(JSON.toJSONString(response));
            }
        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("设备标签删除失败！" + JSON.toJSONString(response));
        }
    }

    /**
     * 根据标签查询设备。
     *
     * @param pageSize    指定返回结果中每页显示的记录数量，最大值是50。默认值是10。
     * @param currentPage 指定从返回结果中的第几页开始显示。默认值是1。
     * @param tags        设备标签。数量限制：可输入最多10个标签。  必须
     */

    public static void queryDeviceByTags(Integer pageSize, Integer currentPage,
                                         List<QueryDeviceByTagsRequest.Tag> tags) {

        QueryDeviceByTagsResponse response = null;
        QueryDeviceByTagsRequest request = new QueryDeviceByTagsRequest();
        request.setPageSize(pageSize);
        request.setCurrentPage(currentPage);
        request.setTags(tags);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("根据标签查询设备成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("根据标签查询设备失败");
                LogUtil.error(JSON.toJSONString(response));
            }
        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("根据标签查询设备失败！" + JSON.toJSONString(response));
        }

    }

}
