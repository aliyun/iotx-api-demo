package device

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//BatchAddThingTopoSample 批量添加设备拓扑关系
func BatchAddThingTopoSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateBatchAddThingTopoRequest()
	request.AcceptFormat = "json"
	request.GwProductKey = productKey
	request.GwDeviceName = "SampleGateway"
	item := iot.BatchAddThingTopoTopoAddItem{}
	item.ProductKey = productKey
	item.DeviceName = "SampleDevice"
	itemList := []iot.BatchAddThingTopoTopoAddItem{item}
	request.TopoAddItem = &itemList

	response, err := client.BatchAddThingTopo(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
