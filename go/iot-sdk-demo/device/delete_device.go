package device

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//DeleteDeviceSample 删除设备
func DeleteDeviceSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateDeleteDeviceRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey
	request.DeviceName = "SampleDevice"

	response, err := client.DeleteDevice(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
