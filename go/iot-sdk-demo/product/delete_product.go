package product

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//DeleteProductSample 删除指定产品
func DeleteProductSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateDeleteProductRequest()
	request.AcceptFormat = "json"
	request.ProductKey = "a1aPVQQTFjP"

	response, err := client.DeleteProduct(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
