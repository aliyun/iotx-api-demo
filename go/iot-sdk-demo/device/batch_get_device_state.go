package device

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//BatchGetDeviceStateSample 批量获取设备状态
func BatchGetDeviceStateSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateBatchGetDeviceStateRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey
	deviceNameList := []string{"SampleDevice"}
	request.DeviceName = &deviceNameList

	response, err := client.BatchGetDeviceState(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
