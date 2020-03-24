package device

import (
	"fmt"
	"time"

	"github.com/aliyun/alibaba-cloud-sdk-go/sdk/requests"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//QueryDeviceServiceDataSample 获取设备的服务记录历史数据
func QueryDeviceServiceDataSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateQueryDeviceServiceDataRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey
	request.DeviceName = "SampleDevice"
	request.Identifier = "AddKey"
	request.StartTime = requests.NewInteger64(time.Now().UnixNano()/1e6 - 3600000)
	request.EndTime = requests.NewInteger64(time.Now().UnixNano() / 1e6)
	request.Asc = "1"

	response, err := client.QueryDeviceServiceData(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
