package device

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//EnableThingSample 解禁设备
func EnableThingSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateEnableThingRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey
	request.DeviceName = "SampleDevice"

	response, err := client.EnableThing(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
