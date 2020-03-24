package product

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//ListProductByTagsSample 根据标签查询产品
func ListProductByTagsSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateListProductByTagsRequest()
	request.AcceptFormat = "json"
	tag := iot.ListProductByTagsProductTag{}
	tag.TagKey = "product"
	tag.TagValue = "tag"
	tagList := []iot.ListProductByTagsProductTag{tag}
	request.ProductTag = &tagList

	response, err := client.ListProductByTags(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
