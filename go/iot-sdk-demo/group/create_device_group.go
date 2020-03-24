package group

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//CreateDeviceGroupSample 新建设备分组
func CreateDeviceGroupSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateCreateDeviceGroupRequest()
	request.AcceptFormat = "json"
	request.GroupName = "TestGroup"
	request.GroupDesc = "This is a test group"

	response, err := client.CreateDeviceGroup(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
