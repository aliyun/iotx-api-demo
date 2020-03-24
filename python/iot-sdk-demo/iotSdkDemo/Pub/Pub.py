#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.acs_exception.exceptions import ClientException
from aliyunsdkcore.acs_exception.exceptions import ServerException
from aliyunsdkiot.request.v20180120.PubRequest import PubRequest

client = AcsClient('<accessKeyId>', '<accessSecret>', 'cn-shanghai')

request = PubRequest()
request.set_accept_format('json')

request.set_TopicFullName("TopicFullName")
request.set_MessageContent("MessageContent")
request.set_ProductKey("ProductKey")
request.set_Qos("Qos")

response = client.do_action_with_exception(request)
# python2:  print(response)
print(response)
