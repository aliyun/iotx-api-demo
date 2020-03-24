package rule

import (
	"fmt"
	"strings"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//UpdateRuleSample 修改指定的规则，RuleId在创建Rule时返回
func UpdateRuleSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateUpdateRuleRequest()
	request.AcceptFormat = "json"
	request.RuleId = "427058"
	request.Name = "TestRule"
	request.ProductKey = productKey
	var builder strings.Builder
	builder.WriteString(deviceName)
	builder.WriteString("/user/update")
	request.ShortTopic = builder.String()
	request.Select = "humidity"
	request.RuleDesc = "rule test"
	request.Where = "humidity > 80"
	request.TopicType = "1"

	response, err := client.UpdateRule(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
