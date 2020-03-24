package rule

import (
	"fmt"
	"strings"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//UpdateRuleActionSample 修改指定的规则，ActionId在创建RuleAction时返回
func UpdateRuleActionSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateUpdateRuleActionRequest()
	request.AcceptFormat = "json"
	request.ActionId = "406870"
	request.Type = "REPUBLISH"
	var builder strings.Builder
	builder.WriteString(`{"topic":"/`)
	builder.WriteString(productKey)
	builder.WriteString("/")
	builder.WriteString(deviceName)
	builder.WriteString(`/user/get","topicType":1}`)
	request.Configuration = builder.String()

	response, err := client.UpdateRuleAction(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
