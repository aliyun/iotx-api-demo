package rule

import (
	"fmt"
	"strings"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//CreateRuleActionSample 在指定的规则下创建一个规则动作，TopicType=1为自定义topic
func CreateRuleActionSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateCreateRuleActionRequest()
	request.AcceptFormat = "json"
	request.RuleId = "427058"
	request.Type = "REPUBLISH"
	var builder strings.Builder
	builder.WriteString(`{"topic":"/`)
	builder.WriteString(productKey)
	builder.WriteString("/")
	builder.WriteString(deviceName)
	builder.WriteString(`/user/get","topicType":1}`)
	request.Configuration = builder.String()

	response, err := client.CreateRuleAction(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
