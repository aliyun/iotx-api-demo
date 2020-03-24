#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.acs_exception.exceptions import ClientException
from aliyunsdkcore.acs_exception.exceptions import ServerException
from aliyunsdkiot.request.v20180120.CreateRuleRequest import CreateRuleRequest

client = AcsClient('<accessKeyId>', '<accessSecret>', 'cn-shanghai')

request = CreateRuleRequest()
request.set_accept_format('json')

request.set_Name("Name")
request.set_Select("Select")
request.set_ShortTopic("ShortTopic")
request.set_Where("Where")
request.set_ProductKey("ProductKey")
request.set_RuleDesc("RuleDesc")
request.set_DataType("DataType")
request.set_TopicType("TopicType")

response = client.do_action_with_exception(request)
# python2:  print(response) 
print(response)
