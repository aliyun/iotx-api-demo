package rule

import (
	"fmt"
	"strings"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//CreateRuleSample 对指定Topic新建一个规则，TopicType=1为自定义topic
func CreateRuleSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateCreateRuleRequest()
	request.AcceptFormat = "json"
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

	response, err := client.CreateRule(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
