package device

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//BatchCheckDeviceNamesSample 批量检查设备名称
func BatchCheckDeviceNamesSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateBatchCheckDeviceNamesRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey
	deviceNameList := []string{"SampleDevice"}
	request.DeviceName = &deviceNameList

	response, err := client.BatchCheckDeviceNames(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
