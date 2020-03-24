package rule

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//ListRuleSample 查询指定规则的详细信息，RuleId在创建Rule时返回
func ListRuleSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateListRuleRequest()
	request.AcceptFormat = "json"
	request.CurrentPage = "1"
	request.PageSize = "10"

	response, err := client.ListRule(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
