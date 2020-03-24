package group

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//DeleteDeviceGroupSample 删除指定分组
func DeleteDeviceGroupSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateDeleteDeviceGroupRequest()
	request.AcceptFormat = "json"
	request.GroupId = "nVWEXSFrlEdr8yJ2nOUY010200"

	response, err := client.DeleteDeviceGroup(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
