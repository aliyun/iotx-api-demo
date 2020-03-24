package device

import (
	"fmt"
	"time"

	"github.com/aliyun/alibaba-cloud-sdk-go/sdk/requests"
	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//QueryDevicePropertyDataSample 查询设备的属性历史数据
func QueryDevicePropertyDataSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateQueryDevicePropertyDataRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey
	request.DeviceName = "SampleDevice"
	request.Identifier = "PowerSwitch"
	request.StartTime = requests.NewInteger64(time.Now().UnixNano()/1e6 - 3600000)
	request.EndTime = requests.NewInteger64(time.Now().UnixNano() / 1e6)
	request.Asc = "1"
	request.PageSize = "10"

	response, err := client.QueryDevicePropertyData(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
