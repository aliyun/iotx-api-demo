package topic

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//QueryProductTopicSample 查询指定产品的Topic，topicId在创建topic时返回
func QueryProductTopicSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateQueryProductTopicRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey

	response, err := client.QueryProductTopic(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
