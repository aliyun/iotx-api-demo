package rule

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//ListRuleActionsSample 查询指定规则下的所有规则动作列表，RuleId在创建Rule时返回
func ListRuleActionsSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateListRuleActionsRequest()
	request.AcceptFormat = "json"
	request.RuleId = "427058"

	response, err := client.ListRuleActions(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
