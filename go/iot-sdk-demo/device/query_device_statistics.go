package device

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//QueryDeviceStatisticsSample 获取设备的统计数量
func QueryDeviceStatisticsSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateQueryDeviceStatisticsRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey

	response, err := client.QueryDeviceStatistics(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
