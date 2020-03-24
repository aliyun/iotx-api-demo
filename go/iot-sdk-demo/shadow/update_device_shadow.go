package shadow

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//UpdateDeviceShadowSample 修改指定设备的影子信息
func UpdateDeviceShadowSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateUpdateDeviceShadowRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey
	request.DeviceName = deviceName
	request.ShadowMessage = `{"method":"update","state":{"desired":{"color":"green"}},"version":1}`

	response, err := client.UpdateDeviceShadow(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
