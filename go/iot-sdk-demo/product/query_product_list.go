package product

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//QueryProductListSample 查询产品列表
func QueryProductListSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateQueryProductListRequest()
	request.AcceptFormat = "json"
	request.CurrentPage = "1"
	request.PageSize = "10"

	response, err := client.QueryProductList(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
