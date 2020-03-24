#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.acs_exception.exceptions import ClientException
from aliyunsdkcore.acs_exception.exceptions import ServerException
from aliyunsdkiot.request.v20180120.InvokeThingServiceRequest import InvokeThingServiceRequest

client = AcsClient('<accessKeyId>', '<accessSecret>', 'cn-shanghai')

request = InvokeThingServiceRequest()
request.set_accept_format('json')

request.set_Args("Args")
request.set_Identifier("Identifier")
request.set_IotId("IotId")
request.set_ProductKey("ProductKey")

response = client.do_action_with_exception(request)
# python2:  print(response) 
print(response)
