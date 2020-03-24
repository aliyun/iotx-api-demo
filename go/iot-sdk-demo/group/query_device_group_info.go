package group

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//QueryDeviceGroupInfoSample 查询分组详情
func QueryDeviceGroupInfoSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateQueryDeviceGroupInfoRequest()
	request.AcceptFormat = "json"
	request.GroupId = "nVWEXSFrlEdr8yJ2nOUY010200"

	response, err := client.QueryDeviceGroupInfo(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
