package group

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//BatchDeleteDeviceGroupRelationsSample 删除分组中已添加的指定设备
func BatchDeleteDeviceGroupRelationsSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateBatchDeleteDeviceGroupRelationsRequest()
	request.AcceptFormat = "json"
	request.GroupId = "nVWEXSFrlEdr8yJ2nOUY010200"
	device := iot.BatchDeleteDeviceGroupRelationsDevice{}
	device.DeviceName = deviceName
	device.ProductKey = productKey
	deviceList := []iot.BatchDeleteDeviceGroupRelationsDevice{device}
	request.Device = &deviceList

	response, err := client.BatchDeleteDeviceGroupRelations(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
