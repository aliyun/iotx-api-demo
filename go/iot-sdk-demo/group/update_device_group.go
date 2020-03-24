package group

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//UpdateDeviceGroupSample 修改分组信息
func UpdateDeviceGroupSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateUpdateDeviceGroupRequest()
	request.AcceptFormat = "json"
	request.GroupId = "nVWEXSFrlEdr8yJ2nOUY010200"
	request.GroupDesc = "This is a test group"

	response, err := client.UpdateDeviceGroup(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
