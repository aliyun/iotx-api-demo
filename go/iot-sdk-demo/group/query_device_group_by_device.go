package group

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//QueryDeviceGroupByDeviceSample 查询某一设备所在的分组列表
func QueryDeviceGroupByDeviceSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateQueryDeviceGroupByDeviceRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey
	request.DeviceName = deviceName

	response, err := client.QueryDeviceGroupByDevice(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
