package product

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//DeleteProductTagsSample 删除产品标签
func DeleteProductTagsSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateDeleteProductTagsRequest()
	request.AcceptFormat = "json"
	request.ProductKey = "a1aPVQQTFjP"
	tagKeyList := []string{"product"}
	request.ProductTagKey = &tagKeyList

	response, err := client.DeleteProductTags(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
