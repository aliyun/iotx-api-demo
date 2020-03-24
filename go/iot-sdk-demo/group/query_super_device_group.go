package group

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//QuerySuperDeviceGroupSample 根据子分组ID查询父分组信息
func QuerySuperDeviceGroupSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateQuerySuperDeviceGroupRequest()
	request.AcceptFormat = "json"
	request.GroupId = "nVWEXSFrlEdr8yJ2nOUY010200"

	response, err := client.QuerySuperDeviceGroup(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
