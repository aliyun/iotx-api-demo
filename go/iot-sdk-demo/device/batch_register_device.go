package device

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//BatchRegisterDeviceSample 批次申请特定数量设备
func BatchRegisterDeviceSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateBatchRegisterDeviceRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey
	request.Count = "1"

	response, err := client.BatchRegisterDevice(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
