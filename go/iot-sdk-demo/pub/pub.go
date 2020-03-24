package pub

import (
	"encoding/base64"
	"fmt"
	"strings"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//PubSample 向某一topic发布消息，注意topic，消息内容要提前进行base64编码
func PubSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreatePubRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey
	var builder strings.Builder
	builder.WriteString("/")
	builder.WriteString(request.ProductKey)
	builder.WriteString("/")
	builder.WriteString(deviceName)
	builder.WriteString("/user/update")
	request.TopicFullName = builder.String()
	request.MessageContent = base64.StdEncoding.EncodeToString([]byte("hello world"))
	request.Qos = "0"

	response, err := client.Pub(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
