package main

import (
	"flag"
	"fmt"
	"os"

	"./device"
	"./group"
	"./product"
	"./pub"
	"./rule"
	"./shadow"
	"./topic"
)

// sampleMap contains all samples
var sampleMap = map[string]interface{}{
	// 产品管理相关 API
	"CreateProductSample":     product.CreateProductSample,
	"UpdateProductSample":     product.UpdateProductSample,
	"QueryProductListSample":  product.QueryProductListSample,
	"QueryProductSample":      product.QueryProductSample,
	"DeleteProductSample":     product.DeleteProductSample,
	"CreateProductTagsSample": product.CreateProductTagsSample,
	"UpdateProductTagsSample": product.UpdateProductTagsSample,
	"DeleteProductTagsSample": product.DeleteProductTagsSample,
	"ListProductTagsSample":   product.ListProductTagsSample,
	"ListProductByTagsSample": product.ListProductByTagsSample,
	// 设备管理相关 API
	"RegisterDeviceSample":                 device.RegisterDeviceSample,
	"QueryDeviceDetailSample":              device.QueryDeviceDetailSample,
	"BatchQueryDeviceDetailSample":         device.BatchQueryDeviceDetailSample,
	"QueryDeviceSample":                    device.QueryDeviceSample,
	"DeleteDeviceSample":                   device.DeleteDeviceSample,
	"GetDeviceStatusSample":                device.GetDeviceStatusSample,
	"BatchGetDeviceStateSample":            device.BatchGetDeviceStateSample,
	"DisableThingSample":                   device.DisableThingSample,
	"EnableThingSample":                    device.EnableThingSample,
	"BatchCheckDeviceNamesSample":          device.BatchCheckDeviceNamesSample,
	"BatchRegisterDeviceWithApplyIdSample": device.BatchRegisterDeviceWithApplyIdSample,
	"BatchRegisterDeviceSample":            device.BatchRegisterDeviceSample,
	"QueryBatchRegisterDeviceStatusSample": device.QueryBatchRegisterDeviceStatusSample,
	"QueryPageByApplyIdSample":             device.QueryPageByApplyIdSample,
	"QueryDeviceEventDataSample":           device.QueryDeviceEventDataSample,
	"QueryDevicePropertyDataSample":        device.QueryDevicePropertyDataSample,
	"QueryDeviceServiceDataSample":         device.QueryDeviceServiceDataSample,
	"InvokeThingServiceSample":             device.InvokeThingServiceSample,
	"QueryDevicePropertyStatusSample":      device.QueryDevicePropertyStatusSample,
	"SetDevicePropertySample":              device.SetDevicePropertySample,
	"SaveDevicePropSample":                 device.SaveDevicePropSample,
	"QueryDevicePropSample":                device.QueryDevicePropSample,
	"DeleteDevicePropSample":               device.DeleteDevicePropSample,
	"GetThingTopoSample":                   device.GetThingTopoSample,
	"NotifyAddThingTopoSample":             device.NotifyAddThingTopoSample,
	"BatchAddThingTopoSample":              device.BatchAddThingTopoSample,
	"RemoveThingTopoSample":                device.RemoveThingTopoSample,
	"QueryDeviceStatisticsSample":          device.QueryDeviceStatisticsSample,
	"GetGatewayBySubDeviceSample":          device.GetGatewayBySubDeviceSample,
	// 分组管理相关API
	"CreateDeviceGroupSample":               group.CreateDeviceGroupSample,
	"DeleteDeviceGroupSample":               group.DeleteDeviceGroupSample,
	"UpdateDeviceGroupSample":               group.UpdateDeviceGroupSample,
	"QueryDeviceGroupInfoSample":            group.QueryDeviceGroupInfoSample,
	"QueryDeviceGroupListSample":            group.QueryDeviceGroupListSample,
	"BatchAddDeviceGroupRelationsSample":    group.BatchAddDeviceGroupRelationsSample,
	"BatchDeleteDeviceGroupRelationsSample": group.BatchDeleteDeviceGroupRelationsSample,
	"SetDeviceGroupTagsSample":              group.SetDeviceGroupTagsSample,
	"QueryDeviceGroupTagListSample":         group.QueryDeviceGroupTagListSample,
	"QueryDeviceGroupByDeviceSample":        group.QueryDeviceGroupByDeviceSample,
	"QuerySuperDeviceGroupSample":           group.QuerySuperDeviceGroupSample,
	"QueryDeviceListByDeviceGroupSample":    group.QueryDeviceListByDeviceGroupSample,
	"QueryDeviceGroupByTagsSample":          group.QueryDeviceGroupByTagsSample,
	// 规则引擎相关 API
	"ListRuleSample":         rule.ListRuleSample,
	"CreateRuleSample":       rule.CreateRuleSample,
	"GetRuleSample":          rule.GetRuleSample,
	"UpdateRuleSample":       rule.UpdateRuleSample,
	"DeleteRuleSample":       rule.DeleteRuleSample,
	"ListRuleActionsSample":  rule.ListRuleActionsSample,
	"GetRuleActionSample":    rule.GetRuleActionSample,
	"CreateRuleActionSample": rule.CreateRuleActionSample,
	"DeleteRuleActionSample": rule.DeleteRuleActionSample,
	"UpdateRuleActionSample": rule.UpdateRuleActionSample,
	"StartRuleSample":        rule.StartRuleSample,
	"StopRuleSample":         rule.StopRuleSample,
	// Topic 管理相关 API
	"QueryProductTopicSample":           topic.QueryProductTopicSample,
	"CreateProductTopicSample":          topic.CreateProductTopicSample,
	"UpdateProductTopicSample":          topic.UpdateProductTopicSample,
	"DeleteProductTopicSample":          topic.DeleteProductTopicSample,
	"CreateTopicRouteTableSample":       topic.CreateTopicRouteTableSample,
	"QueryTopicRouteTableSample":        topic.QueryTopicRouteTableSample,
	"QueryTopicReverseRouteTableSample": topic.QueryTopicReverseRouteTableSample,
	"DeleteTopicRouteTableSample":       topic.DeleteTopicRouteTableSample,
	// 消息通信相关 API
	"PubSample":          pub.PubSample,
	"RRpcSample":         pub.RRpcSample,
	"PubBroadcastSample": pub.PubBroadcastSample,
	// 设备影子相关 API
	"GetDeviceShadowSample":    shadow.GetDeviceShadowSample,
	"UpdateDeviceShadowSample": shadow.UpdateDeviceShadowSample,
}

func main() {
	var name string
	flag.StringVar(&name, "name", "", "Waiting for a sample of execution")

	flag.Parse()

	if len(name) <= 0 {
		fmt.Println("please enter your sample's name. like '-name PubSample'")
		os.Exit(-1)
	} else {
		if sampleMap[name] == nil {
			fmt.Println("the " + name + "is not exist.")
			os.Exit(-1)
		}
		sampleMap[name].(func())()
	}
}
