package group

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//QueryDeviceGroupByTagsSample 根据标签查询设备分组
func QueryDeviceGroupByTagsSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateQueryDeviceGroupByTagsRequest()
	request.AcceptFormat = "json"
	tag := iot.QueryDeviceGroupByTagsTag{}
	tag.TagKey = "group"
	tag.TagValue = "tag"
	tagList := []iot.QueryDeviceGroupByTagsTag{tag}
	request.Tag = &tagList

	response, err := client.QueryDeviceGroupByTags(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
