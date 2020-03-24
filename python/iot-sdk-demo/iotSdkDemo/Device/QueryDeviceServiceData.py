#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.acs_exception.exceptions import ClientException
from aliyunsdkcore.acs_exception.exceptions import ServerException
from aliyunsdkiot.request.v20180120.QueryDeviceServiceDataRequest import QueryDeviceServiceDataRequest

client = AcsClient('<accessKeyId>', '<accessSecret>', 'cn-shanghai')

request = QueryDeviceServiceDataRequest()
request.set_accept_format('json')

request.set_EndTime("EndTime")
request.set_StartTime("StartTime")
request.set_IotId("IotId")
request.set_ProductKey("ProductKey")
request.set_DeviceName("DeviceName")
request.set_Identifier("Identifier")
request.set_Asc("Asc")
request.set_PageSize("PageSize")

response = client.do_action_with_exception(request)
# python2:  print(response) 
print(response)
