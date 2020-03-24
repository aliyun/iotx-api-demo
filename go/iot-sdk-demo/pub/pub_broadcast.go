package pub

import (
	"encoding/base64"
	"fmt"
	"strings"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//PubBroadcastSample 发布广播消息，注意topic要以/broadcast开头
func PubBroadcastSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreatePubBroadcastRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey
	var builder strings.Builder
	builder.WriteString("/broadcast/")
	builder.WriteString(request.ProductKey)
	builder.WriteString("/userDefine")
	request.TopicFullName = builder.String()
	request.MessageContent = base64.StdEncoding.EncodeToString([]byte("hello world"))

	response, err := client.PubBroadcast(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
