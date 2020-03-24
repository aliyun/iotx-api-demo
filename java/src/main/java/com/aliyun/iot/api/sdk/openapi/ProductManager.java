package com.aliyun.iot.api.sdk.openapi;

import com.alibaba.fastjson.JSON;
import com.aliyun.iot.util.LogUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20180120.*;

import java.util.List;

public class ProductManager extends AbstractManager {

    private static DefaultAcsClient client = AbstractManager.getClient();

    /**
     * 创建产品
     *
     * @param productName         产品名称  必须
     * @param nodeType            节点类型  必须
     * @param aliyunCommodityCode 产品版本类型
     * @param dataFormat          产品数据格式
     * @param categoryId          设备类型
     * @param description
     * @param protocolType
     * @param authType
     * @return 产品创建信息
     */
    public static CreateProductResponse.Data createProductTest(String productName, Integer nodeType,
                                                               String aliyunCommodityCode,
                                                               Integer dataFormat,
                                                               Long categoryId, String description, String protocolType,
                                                               String netType,
                                                               String authType) {

        CreateProductResponse response = null;

        CreateProductRequest request = new CreateProductRequest();
        request.setProductName(productName);
        request.setNodeType(nodeType);
        request.setAliyunCommodityCode(aliyunCommodityCode);
        request.setDataFormat(dataFormat);
        request.setCategoryId(categoryId);
        request.setDescription(description);
        request.setProtocolType(protocolType);
        request.setNetType(netType);
        request.setAuthType(authType);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("创建产品成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("创建产品失败");
                LogUtil.error(JSON.toJSONString(response));
            }

            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("创建产品失败！" + JSON.toJSONString(response));
        }
        return null;
    }

    /**
     * 修改产品
     *
     * @param productKey  产品PK 必需
     * @param productName 产品名称 非必需
     * @param description 产品描述 非必需
     */
    public static void updateProductTest(String productKey, String productName, String description) {
        UpdateProductResponse updateProductResponse = null;

        UpdateProductRequest updateProductRequest = new UpdateProductRequest();
        updateProductRequest.setProductKey(productKey);
        updateProductRequest.setProductName(productName);
        updateProductRequest.setDescription(description);

        try {
            updateProductResponse = client.getAcsResponse(updateProductRequest);

            if (updateProductResponse.getSuccess() != null && updateProductResponse.getSuccess()) {
                LogUtil.print("修改产品");
                LogUtil.print(JSON.toJSONString(updateProductResponse));
            } else {
                LogUtil.print("修改产品失败");
                LogUtil.error(JSON.toJSONString(updateProductResponse));
            }

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("修改产品失败！" + JSON.toJSONString(updateProductResponse));
        }
    }

    /**
     * 修改产品
     *
     * @param productKey 产品PK 必需
     */
    public static void deleteProductTest(String productKey) {
        DeleteProductResponse response = null;

        DeleteProductRequest request = new DeleteProductRequest();
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("删除产品");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("删除产品失败");
                LogUtil.error(JSON.toJSONString(response));
            }

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("删除产品失败！");
        }
    }

    /**
     * 查询产品列表
     *
     * @param currentPage         指定从返回结果中的第几页开始显示  非必需
     * @param pageSize            指定返回结果中每页显示的记录数量，最大值是200 非必需
     * @param aliyunCommodityCode 产品描述 非必需
     */
    public static QueryProductListResponse.Data queryProductListTest(Integer currentPage, Integer pageSize,
                                                                     String aliyunCommodityCode) {

        QueryProductListResponse response = null;
        QueryProductListRequest request = new QueryProductListRequest();

        request.setAliyunCommodityCode(aliyunCommodityCode);
        request.setPageSize(pageSize);
        request.setCurrentPage(currentPage);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("查询产品列表");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("查询产品列表失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("查询产品列表失败！" + JSON.toJSONString(response));
        }
        return null;

    }

    /**
     * 查询产品详细信息
     *
     * @param productKey 产品PK 必需
     */
    public static QueryProductResponse.Data queryProductTest(String productKey) {
        QueryProductResponse response = null;

        QueryProductRequest request = new QueryProductRequest();
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("查询产品详细信息");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("查询产品失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("查询产品失败！" + JSON.toJSONString(response.getData()));
        }
        return null;
    }

    /**
     * 创建产品标签。
     *
     * @param productKey  产品PK 必需
     * @param productTags 要创建的标签。标签包括TagKey和TagValue，分别对应标签的key和value 必需
     */
    public static void createProductTags(String productKey, List<CreateProductTagsRequest.ProductTag> productTags) {
        CreateProductTagsResponse response = null;

        CreateProductTagsRequest request = new CreateProductTagsRequest();
        request.setProductKey(productKey);
        request.setProductTags(productTags);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("创建产品标签成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("创建产品标签失败");
                LogUtil.error(JSON.toJSONString(response));
            }

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("创建产品标签失败！" + JSON.toJSONString(response));
        }

    }

    /**
     * 更新产品标签。
     *
     * @param productKey  产品PK 必需
     * @param productTags 要创建的标签。标签包括TagKey和TagValue，分别对应标签的key和value 必需
     */
    public static void updateProductTags(String productKey, List<UpdateProductTagsRequest.ProductTag> productTags) {

        UpdateProductTagsResponse response = null;

        UpdateProductTagsRequest request = new UpdateProductTagsRequest();
        request.setProductKey(productKey);
        request.setProductTags(productTags);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("更新产品标签成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("更新产品标签失败");
                LogUtil.error(JSON.toJSONString(response));
            }

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("更新产品标签失败！" + JSON.toJSONString(response));
        }

    }

    /**
     * 删除产品标签。
     *
     * @param productKey     产品PK 必需
     * @param productTagKeys 产品标签键列表 必需
     */
    public static void deleteProductTags(String productKey, List<String> productTagKeys) {

        DeleteProductTagsResponse response = null;

        DeleteProductTagsRequest request = new DeleteProductTagsRequest();
        request.setProductKey(productKey);
        request.setProductTagKeys(productTagKeys);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("删除产品标签成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("删除产品标签失败");
                LogUtil.error(JSON.toJSONString(response));
            }

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("删除产品标签失败！" + JSON.toJSONString(response));
        }
    }

    /**
     * 查询产品的所有标签。
     *
     * @param productKey 产品PK 必需
     */
    public static List<ListProductTagsResponse.ProductTag> listProductTags(String productKey) {
        ListProductTagsResponse response = null;

        ListProductTagsRequest request = new ListProductTagsRequest();
        request.setProductKey(productKey);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("查询产品的所有标签成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("查询产品的所有标签失败");
                LogUtil.error(JSON.toJSONString(response));
            }

            return response.getData();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("查询产品的所有标签失败！" + JSON.toJSONString(response));
        }

        return null;

    }

    /**
     * 根据标签查询产品。
     *
     * @param productTags 产品标签。ProductTag包括TagKey和TagValue，分别对应标签的key和value 必需
     * @param ​Page​Size  指定返回结果中每页显示的记录数量。最大值是50。默认值是10。
     * @param currentPage 指定显示返回结果中的第几页。默认值为1。
     */
    public static List<ListProductByTagsResponse.ProductInfo> listProductByTags(
        List<ListProductByTagsRequest.ProductTag> productTags, Integer page​Size, Integer currentPage) {
        ListProductByTagsResponse response = null;

        ListProductByTagsRequest request = new ListProductByTagsRequest();
        request.setProductTags(productTags);
        request.setCurrentPage(currentPage);
        request.setPageSize(page​Size);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("根据标签查询产品成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("根据标签查询产品失败");
                LogUtil.error(JSON.toJSONString(response));
            }
            return response.getProductInfos();

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("根据标签查询产品失败！" + JSON.toJSONString(response));
        }
        return null;
    }

}
