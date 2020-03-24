package topic

import (
	"fmt"
	"strings"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//QueryTopicRouteTableSample 查询向指定Topic订阅消息的目标Topic，即指定Topic的路由表，只支持查询用户Topic。
func QueryTopicRouteTableSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateQueryTopicRouteTableRequest()
	request.AcceptFormat = "json"
	var builder strings.Builder
	builder.WriteString("/")
	builder.WriteString(productKey)
	builder.WriteString("/")
	builder.WriteString(deviceName)
	builder.WriteString("/user/update")
	request.Topic = builder.String()

	response, err := client.QueryTopicRouteTable(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
