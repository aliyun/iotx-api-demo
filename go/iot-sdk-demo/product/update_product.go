package product

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//UpdateProductSample 修改产品信息
func UpdateProductSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateUpdateProductRequest()
	request.AcceptFormat = "json"
	request.ProductKey = "a1aPVQQTFjP"
	request.ProductName = "TestProductForever"
	request.Description = "Product test"

	response, err := client.UpdateProduct(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
