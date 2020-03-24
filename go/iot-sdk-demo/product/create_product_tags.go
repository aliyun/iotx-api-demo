package product

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//CreateProductTagsSample 创建产品标签
func CreateProductTagsSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateCreateProductTagsRequest()
	request.AcceptFormat = "json"
	request.ProductKey = "a1aPVQQTFjP"
	tag := iot.CreateProductTagsProductTag{}
	tag.TagKey = "product"
	tag.TagValue = "tag"
	tagList := []iot.CreateProductTagsProductTag{tag}
	request.ProductTag = &tagList

	response, err := client.CreateProductTags(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
