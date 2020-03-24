package group

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//QueryDeviceGroupListSample 查询分组列表
func QueryDeviceGroupListSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateQueryDeviceGroupListRequest()
	request.AcceptFormat = "json"
	request.GroupName = "TestGroup"
	request.CurrentPage = "1"
	request.PageSize = "10"

	response, err := client.QueryDeviceGroupList(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
