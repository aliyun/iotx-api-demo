package product

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//UpdateProductTagsSample 更新产品标签
func UpdateProductTagsSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateUpdateProductTagsRequest()
	request.AcceptFormat = "json"
	request.ProductKey = "a1aPVQQTFjP"
	tag := iot.UpdateProductTagsProductTag{}
	tag.TagKey = "product"
	tag.TagValue = "tag"
	tagList := []iot.UpdateProductTagsProductTag{tag}
	request.ProductTag = &tagList

	response, err := client.UpdateProductTags(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
