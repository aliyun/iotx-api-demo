package device

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//QueryDeviceSample 查询产品的设备列表
func QueryDeviceSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateQueryDeviceRequest()
	request.AcceptFormat = "json"
	request.ProductKey = "SampleDevice"

	response, err := client.QueryDevice(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
