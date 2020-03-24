package device

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//NotifyAddThingTopoSample 通知网关增加设备拓扑关系
func NotifyAddThingTopoSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateNotifyAddThingTopoRequest()
	request.AcceptFormat = "json"
	request.GwProductKey = productKey
	request.GwDeviceName = "SampleDevice"
	request.DeviceListStr = `[{"productKey":"a1Ms***Z9pm","deviceName":"BzBe******oVf7"}]`

	response, err := client.NotifyAddThingTopo(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
