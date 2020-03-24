package group

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//SetDeviceGroupTagsSample 添加或更新分组标签
func SetDeviceGroupTagsSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateSetDeviceGroupTagsRequest()
	request.AcceptFormat = "json"
	request.GroupId = "nVWEXSFrlEdr8yJ2nOUY010200"
	request.TagString = `[{"tagKey":"group","tagValue":"tag"}]`

	response, err := client.SetDeviceGroupTags(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
