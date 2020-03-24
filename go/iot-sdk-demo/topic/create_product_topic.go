package topic

import (
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//CreateProductTopicSample 为指定产品创建产品Topic
func CreateProductTopicSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateCreateProductTopicRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey
	request.TopicShortName = "submit"
	request.Operation = "PUB"
	request.Desc = "sbumit a test topic"

	response, err := client.CreateProductTopic(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
