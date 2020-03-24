package com.aliyun.iot.api.sdk.openapitest;

import com.aliyun.iot.util.ServiceUtil;
import com.aliyuncs.iot.model.v20180120.CreateProductResponse;

import static com.aliyun.iot.api.sdk.openapi.ProductManager.*;

public class ProductProcess {


    public static void main(String[] args) {
        ProductProcess productProcess = new ProductProcess();

        productProcess.createPlotOne();

    }


    /**
     *  1 创建 低级版产品
     *  2 创建 高级版产品
     *  3 列表查询
     */
    public   void createPlotOne(){

        createProductTest(ServiceUtil.productNameGenerator(), 0, null, null,
            null, null, null,"", "secret");

        createProductTest(ServiceUtil.productNameGenerator(), 0, "iothub_senior", 0,
            null, null, "customize","", "secret");

        queryProductListTest(1, 20, null);
    }



    /**
     *  1 创建 创建高级版网关产品
     *  2 修改产品名称
     *  3 删除产品
     */
    public   void createPlotTwo(){

        CreateProductResponse.Data   product = createProductTest(ServiceUtil.productNameGenerator(), 0, "iothub_senior", 1,
            null, null, "customize","", "secret");

        updateProductTest(product.getProductKey() , "testUpdate2" ,null);

        deleteProductTest(product.getProductKey());

    }




}
