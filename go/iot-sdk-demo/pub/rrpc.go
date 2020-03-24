package pub

import (
	"encoding/base64"
	"fmt"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/iot"
)

//RRpcSample 向指定设备发送请求消息，并同步返回响应，timeout是同步等待最大时间，注意单位是毫秒，不能超过8000
func RRpcSample() {
	client, err := iot.NewClientWithAccessKey(endpoint, accessKeyID, accessKeySecret)

	request := iot.CreateRRpcRequest()
	request.AcceptFormat = "json"
	request.ProductKey = productKey
	request.DeviceName = deviceName
	request.Timeout = "1000"
	request.RequestBase64Byte = base64.StdEncoding.EncodeToString([]byte("hello world"))

	response, err := client.RRpc(request)
	if err != nil {
		fmt.Print(err.Error())
	}
	fmt.Printf("response is %#v\n", response)
}
