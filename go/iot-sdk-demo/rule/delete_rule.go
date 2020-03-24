package rule

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//DeleteRuleSample 删除指定的规则，RuleId在创建Rule时返回
func DeleteRuleSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateDeleteRuleRequest()
	request.AcceptFormat = "json"
	request.RuleId = "427058"

	response, err := client.DeleteRule(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
