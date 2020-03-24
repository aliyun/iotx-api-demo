package product

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//ListProductTagsSample 查询产品的所有标签
func ListProductTagsSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateListProductTagsRequest()
	request.AcceptFormat = "json"
	request.ProductKey = "a1aPVQQTFjP"

	response, err := client.ListProductTags(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
