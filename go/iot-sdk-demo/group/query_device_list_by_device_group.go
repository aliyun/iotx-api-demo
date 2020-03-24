package group

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//QueryDeviceListByDeviceGroupSample 查询分组中的设备列表
func QueryDeviceListByDeviceGroupSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateQueryDeviceListByDeviceGroupRequest()
	request.AcceptFormat = "json"
	request.GroupId = "nVWEXSFrlEdr8yJ2nOUY010200"
	request.PageSize = "10"
	request.CurrentPage = "1"

	response, err := client.QueryDeviceListByDeviceGroup(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
