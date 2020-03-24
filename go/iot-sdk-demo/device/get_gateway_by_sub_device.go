package device

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//GetGatewayBySubDeviceSample 根据挂载的子设备信息查询对应的网关设备信息
func GetGatewayBySubDeviceSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateGetGatewayBySubDeviceRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey
	request.DeviceName = "SampleDevice"

	response, err := client.GetGatewayBySubDevice(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
