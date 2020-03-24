package product

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//CreateProductSample 创建产品
func CreateProductSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateCreateProductRequest()
	request.AcceptFormat = "json"
	request.ProductName = "TestProductForever"
	request.AliyunCommodityCode = "iothub_senior"
	request.AuthType = "secret"
	request.DataFormat = "1"
	request.Description = "Product test"
	request.NodeType = "0"

	response, err := client.CreateProduct(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
