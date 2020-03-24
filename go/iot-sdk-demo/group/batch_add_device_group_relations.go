package group

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//BatchAddDeviceGroupRelationsSample 设备到某一分组（可批量添加设备）
func BatchAddDeviceGroupRelationsSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateBatchAddDeviceGroupRelationsRequest()
	request.AcceptFormat = "json"
	request.GroupId = "nVWEXSFrlEdr8yJ2nOUY010200"
	device := iot.BatchAddDeviceGroupRelationsDevice{}
	device.DeviceName = deviceName
	device.ProductKey = productKey
	deviceList := []iot.BatchAddDeviceGroupRelationsDevice{device}
	request.Device = &deviceList

	response, err := client.BatchAddDeviceGroupRelations(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
