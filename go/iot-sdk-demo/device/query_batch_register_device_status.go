package device

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//QueryBatchRegisterDeviceStatusSample 查询批量注册设备状态,ApplyId是BatchCheckDeviceNames返回的
func QueryBatchRegisterDeviceStatusSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateQueryBatchRegisterDeviceStatusRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey
	request.ApplyId = "1237375"

	response, err := client.QueryBatchRegisterDeviceStatus(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
