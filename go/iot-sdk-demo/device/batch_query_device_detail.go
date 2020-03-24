package device

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//BatchQueryDeviceDetailSample 批量查询设备详情
func BatchQueryDeviceDetailSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateBatchQueryDeviceDetailRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey
	deviceNameList := []string{"SampleDevice"}
	request.DeviceName = &deviceNameList

	response, err := client.BatchQueryDeviceDetail(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
