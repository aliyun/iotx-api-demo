package device

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//QueryPageByApplyIdSample 查询批次设备列表
func QueryPageByApplyIdSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateQueryPageByApplyIdRequest()
	request.AcceptFormat = "json"
	request.ApplyId = "1237375"

	response, err := client.QueryPageByApplyId(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
