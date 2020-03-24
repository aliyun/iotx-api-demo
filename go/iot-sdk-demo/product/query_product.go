package product

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//QueryProductSample 查询产品详细信息
func QueryProductSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateQueryProductRequest()
	request.AcceptFormat = "json"
	request.ProductKey = "a1aPVQQTFjP"

	response, err := client.QueryProduct(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
