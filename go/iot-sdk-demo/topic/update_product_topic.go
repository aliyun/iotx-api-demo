package topic

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//UpdateProductTopicSample 更新指定的产品Topic
func UpdateProductTopicSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateUpdateProductTopicRequest()
	request.AcceptFormat = "json"
	request.TopicId = "7094790"
	request.TopicShortName = "submit"
	request.Operation = "PUB"
	request.Desc = "sbumit a test topic"

	response, err := client.UpdateProductTopic(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
