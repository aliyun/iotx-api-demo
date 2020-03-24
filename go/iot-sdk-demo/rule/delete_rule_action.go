package rule

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//DeleteRuleActionSample 删除指定的规则，RuleId在创建Rule时返回
func DeleteRuleActionSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateDeleteRuleActionRequest()
	request.AcceptFormat = "json"
	request.ActionId = "406870"

	response, err := client.DeleteRuleAction(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
