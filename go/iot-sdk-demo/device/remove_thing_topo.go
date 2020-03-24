package device

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//RemoveThingTopoSample 移除网关设备或子设备所具有的拓扑关系
func RemoveThingTopoSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateRemoveThingTopoRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey
	request.DeviceName = "SampleDevice"

	response, err := client.RemoveThingTopo(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
