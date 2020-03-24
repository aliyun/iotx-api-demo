using Aliyun.Acs.Core;
using Aliyun.Acs.Core.Exceptions;
using Aliyun.Acs.Core.Profile;
using Aliyun.Acs.Iot.Model.V20180120;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Product
{
    class ProductManagement
    {

        //创建一个节点类型是网关的产品，禁止动态注册设备到产品下
        public void TestCreateProduct()
        {
            CreateProductRequest request = new CreateProductRequest();
            request.ProductName = "product_20190819";
            request.NodeType = 1;
            request.AliyunCommodityCode = "iothub_senior";
            request.DataFormat = 1;
            request.Id2 = false;
            request.ProtocolType = "modbus";
            request.NetType = "WIFI";
            request.Description = "Gateway use wifi.";
            request.AuthType = "id2";

            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            CreateProductResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine(response.Success);
            Console.WriteLine(response.ErrorMessage);
            CreateProductResponse.CreateProduct_Data ProductInfo = response.Data;
            Console.WriteLine("product name: " + ProductInfo.ProductName);
            Console.WriteLine("product key: " + ProductInfo.ProductKey);
            Console.WriteLine("product description: " + ProductInfo.Description);
        }


        //根据产品PK更新产品信息，如产品名称和产品描述
        public void TestUpdateProduct()
        {
            DefaultAcsClient acsclient = Demo.IotClient.GetClient();

            UpdateProductRequest request = new UpdateProductRequest();
            //更新的产品来源于CreateProduct
            request.ProductKey = "<productKey>";
            request.ProductName = "csharp_ide";
            request.Description = "package manage";

            UpdateProductResponse response = acsclient.GetAcsResponse(request);
            Console.WriteLine(response.Success);
            Console.WriteLine(response.ErrorMessage);
            Console.WriteLine(response.Code);
        }


        //根据产品ID获取产品详细信息
        public void TestQueryProduct()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            QueryProductRequest request = new QueryProductRequest();
            request.ProductKey = "<productKey>";

            QueryProductResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine(response.Success);
            Console.WriteLine(response.ErrorMessage);
            Console.WriteLine(response.Code);

            QueryProductResponse.QueryProduct_Data productData = response.Data;
            Console.WriteLine(productData.ProductName + ", " + productData.ProductKey + ", " + productData.ProductSecret + "， " + productData.AuthType);
            Console.WriteLine(productData.ToString());
        }


        //分页获取产品列表
        public void TestQueryProductList()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            QueryProductListRequest request = new QueryProductListRequest();
            request.CurrentPage = 1;
            request.PageSize = 10;

            QueryProductListResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine(response.Success);
            Console.WriteLine(response.ErrorMessage);
            Console.WriteLine(response.Code);

            QueryProductListResponse.QueryProductList_Data data = response.Data;

            Console.WriteLine(data.CurrentPage);
            Console.WriteLine(data.PageSize);
            Console.WriteLine(data.PageCount);
            Console.WriteLine(data.Total);

            List<QueryProductListResponse.QueryProductList_Data.QueryProductList_ProductInfo> productList = data.List;
            Console.WriteLine(productList.Count);
        }


        public void TestDeleteProduct()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            DeleteProductRequest request = new DeleteProductRequest();
            request.ProductKey = "<productKey>";


            DeleteProductResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine(response.Success);
            Console.WriteLine(response.ErrorMessage);
            Console.WriteLine(response.Code);
        }


        //给产品打上标签，方便用标签检索产品
        public void TestCreateProductTags()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            CreateProductTagsRequest request = new CreateProductTagsRequest();
            request.ProductKey = "<productKey>";

            List<CreateProductTagsRequest.ProductTag> productTags = new List<CreateProductTagsRequest.ProductTag>();

            CreateProductTagsRequest.ProductTag tag1 = new CreateProductTagsRequest.ProductTag();
            tag1.TagKey = "day";
            tag1.TagValue = "Friday";
            productTags.Add(tag1);

            CreateProductTagsRequest.ProductTag tag2 = new CreateProductTagsRequest.ProductTag();
            tag2.TagKey = "month";
            tag2.TagValue = "Octomber";
            productTags.Add(tag2);

            CreateProductTagsRequest.ProductTag tag3 = new CreateProductTagsRequest.ProductTag();
            tag3.TagKey = "date";
            tag3.TagValue = "2019-08-20";
            productTags.Add(tag3);

            request.ProductTags = productTags;

            CreateProductTagsResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine(response.Success);
            Console.WriteLine(response.ErrorMessage);
            Console.WriteLine(response.Code);
        }


        //更新产品已经存在的标签
        public void TestUpdateProductTags()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            UpdateProductTagsRequest request = new UpdateProductTagsRequest();
            request.ProductKey = "<productKey>";

            UpdateProductTagsRequest.ProductTag tag1 = new UpdateProductTagsRequest.ProductTag();
            tag1.TagKey = "city";
            tag1.TagValue = "shenzhen";

            UpdateProductTagsRequest.ProductTag tag2 = new UpdateProductTagsRequest.ProductTag();
            tag2.TagKey = "department";
            tag2.TagValue = "texun";
            List<UpdateProductTagsRequest.ProductTag> productTags = new List<UpdateProductTagsRequest.ProductTag>();
            productTags.Add(tag1);
            productTags.Add(tag2);

            request.ProductTags = productTags;

            UpdateProductTagsResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine(response.Success);
            Console.WriteLine(response.ErrorMessage);
            Console.WriteLine(response.Code);
        }


        //获取产品的全部标签
        public void TestListProductTags()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            ListProductTagsRequest request = new ListProductTagsRequest();
            request.ProductKey = "<productKey>";

            ListProductTagsResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine(response.Success);
            Console.WriteLine(response.ErrorMessage);
            Console.WriteLine(response.Code);

            List<ListProductTagsResponse.ListProductTags_ProductTag> productTags = response.Data;

            ListProductTagsResponse.ListProductTags_ProductTag firstTag = productTags.First();
            Console.WriteLine(firstTag.TagKey + ", " + firstTag.TagValue);
            ListProductTagsResponse.ListProductTags_ProductTag lastTag = productTags.Last();
            Console.WriteLine(lastTag.TagKey + ", " + lastTag.TagValue);
        }


        //根据标签获取产品列表，标签之间是或的关系，只要产品的标签中存在之一，产品就会被检索出来
        public void TestListProductByTags()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            ListProductByTagsRequest request = new ListProductByTagsRequest();
            List<ListProductByTagsRequest.ProductTag> productTagList = new List<ListProductByTagsRequest.ProductTag>();

            ListProductByTagsRequest.ProductTag tag1 = new ListProductByTagsRequest.ProductTag();
            tag1.TagKey = "city";
            tag1.TagValue = "shenzhen";
            productTagList.Add(tag1);

            ListProductByTagsRequest.ProductTag tag2 = new ListProductByTagsRequest.ProductTag();
            tag2.TagKey = "department";
            tag2.TagValue = "aliyun";
            productTagList.Add(tag2);

            request.ProductTags = productTagList;
            request.CurrentPage = 1;
            request.PageSize = 10;

            ListProductByTagsResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine(response.Success);
            Console.WriteLine(response.ErrorMessage);
            Console.WriteLine(response.Code);


            List<ListProductByTagsResponse.ListProductByTags_ProductInfo> productInfos = response.ProductInfos;

            for (int i = 0; i < productInfos.Count; i += 1)
            {
                ListProductByTagsResponse.ListProductByTags_ProductInfo productInfo = productInfos[i];
                Console.WriteLine(productInfo.ProductName + ", " + productInfo.ProductKey + ", " + productInfo.CreateTime + ", " + productInfo.Description);
            }
        }


        //根据标签Key列表删除对应的产品的标签
        public void TestDeleteProductTags()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            DeleteProductTagsRequest request = new DeleteProductTagsRequest();
            request.ProductKey = "<productKey>";

            List<string> ProductTagKeys = new List<string>();
            ProductTagKeys.Add("tagKey1");
            ProductTagKeys.Add("tagKey2");
            request.ProductTagKeys = ProductTagKeys;


            DeleteProductTagsResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine(response.Success);
            Console.WriteLine(response.ErrorMessage);
            Console.WriteLine(response.Code);
        }


    }
}
