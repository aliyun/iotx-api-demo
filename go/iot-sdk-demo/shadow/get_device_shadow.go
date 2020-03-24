package shadow

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//GetDeviceShadowSample 查询指定设备的影子信息
func GetDeviceShadowSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateGetDeviceShadowRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey
	request.DeviceName = deviceName

	response, err := client.GetDeviceShadow(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
