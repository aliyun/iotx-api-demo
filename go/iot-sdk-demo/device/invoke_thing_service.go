package device

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//InvokeThingServiceSample 调用设备的服务
func InvokeThingServiceSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateInvokeThingServiceRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey
	request.DeviceName = "SampleDevice"
	request.Identifier = "AddKey"
	request.Args = `{"LockType":1,"UserLimit":1}`

	response, err := client.InvokeThingService(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
