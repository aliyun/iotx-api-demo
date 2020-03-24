package topic

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//DeleteProductTopicSample 删除指定的Topic，topicId在创建topic时返回
func DeleteProductTopicSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateDeleteProductTopicRequest()
	request.AcceptFormat = "json"
	request.TopicId = "7094790"

	response, err := client.DeleteProductTopic(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
