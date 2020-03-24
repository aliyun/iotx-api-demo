package device

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//BatchRegisterDeviceWithApplyIdSample 根据ApplyId批量申请设备,ApplyId是BatchCheckDeviceNames返回的
func BatchRegisterDeviceWithApplyIdSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateBatchRegisterDeviceWithApplyIdRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey
	request.ApplyId = "1237375"

	response, err := client.BatchRegisterDeviceWithApplyId(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
