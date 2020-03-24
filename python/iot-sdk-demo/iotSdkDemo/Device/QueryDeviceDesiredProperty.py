#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.acs_exception.exceptions import ClientException
from aliyunsdkcore.acs_exception.exceptions import ServerException
from aliyunsdkiot.request.v20180120.QueryDeviceDesiredPropertyRequest import QueryDeviceDesiredPropertyRequest

client = AcsClient('<accessKeyId>', '<accessSecret>', 'cn-shanghai')

request = QueryDeviceDesiredPropertyRequest()
request.set_accept_format('json')

request.set_IotId("IotId")
request.set_ProductKey("ProductKey")
request.set_DeviceName("DeviceName")
request.set_Identifiers(["Identifier1","Identifier2"])

response = client.do_action_with_exception(request)
# python2:  print(response) 
print(response)
