package rule

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//GetRuleActionSample 查询指定规则动作的详细信息，ActionId在创建RuleAction时返回
func GetRuleActionSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateGetRuleActionRequest()
	request.AcceptFormat = "json"
	request.ActionId = "406870"

	response, err := client.GetRuleAction(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
