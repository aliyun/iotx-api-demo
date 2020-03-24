package topic

import (
	"fmt"
	"strings"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//CreateTopicRouteTableSample 添加Topic路由表
func CreateTopicRouteTableSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateCreateTopicRouteTableRequest()
	request.AcceptFormat = "json"
	var srcBuilder strings.Builder
	srcBuilder.WriteString("/")
	srcBuilder.WriteString(productKey)
	srcBuilder.WriteString("/")
	srcBuilder.WriteString(deviceName)
	srcBuilder.WriteString("/user/update")
	request.SrcTopic = srcBuilder.String()
	var destBuilder1 strings.Builder
	destBuilder1.WriteString("/")
	destBuilder1.WriteString(productKey)
	destBuilder1.WriteString("/")
	destBuilder1.WriteString("otherDevice1")
	destBuilder1.WriteString("/user/update")
	var destBuilder2 strings.Builder
	destBuilder2.WriteString("/")
	destBuilder2.WriteString(productKey)
	destBuilder2.WriteString("/")
	destBuilder2.WriteString("otherDevice2")
	destBuilder2.WriteString("/user/update")
	destBuilders := []string{destBuilder1.String(), destBuilder2.String()}
	request.DstTopic = &destBuilders

	response, err := client.CreateTopicRouteTable(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
