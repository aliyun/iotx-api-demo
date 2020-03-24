#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.acs_exception.exceptions import ClientException
from aliyunsdkcore.acs_exception.exceptions import ServerException
from aliyunsdkiot.request.v20180120.SetDevicesPropertyRequest import SetDevicesPropertyRequest

client = AcsClient('<accessKeyId>', '<accessSecret>', 'cn-shanghai')

request = SetDevicesPropertyRequest()
request.set_accept_format('json')

request.set_Items("Items")
request.set_DeviceNames(["DeviceName1","DeviceName2"])

response = client.do_action_with_exception(request)
# python2:  print(response) 
print(response)
