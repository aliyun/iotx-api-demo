package topic

import (
	"fmt"
	"strings"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//QueryTopicReverseRouteTableSample 查询指定Topic订阅的源Topic，即反向路由表
func QueryTopicReverseRouteTableSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateQueryTopicReverseRouteTableRequest()
	request.AcceptFormat = "json"
	var builder strings.Builder
	builder.WriteString("/")
	builder.WriteString(productKey)
	builder.WriteString("/")
	builder.WriteString("otherDevice1")
	builder.WriteString("/user/update")
	request.Topic = builder.String()

	response, err := client.QueryTopicReverseRouteTable(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
