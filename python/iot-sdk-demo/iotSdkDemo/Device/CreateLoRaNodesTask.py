#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.acs_exception.exceptions import ClientException
from aliyunsdkcore.acs_exception.exceptions import ServerException
from aliyunsdkiot.request.v20180120.CreateLoRaNodesTaskRequest import CreateLoRaNodesTaskRequest

client = AcsClient('<accessKeyId>', '<accessSecret>', 'cn-shanghai')

request = CreateLoRaNodesTaskRequest()
request.set_accept_format('json')

request.set_DeviceInfos([
  {
    "DevEui": "DevEui",
    "PinCode": "PinCode"
  }
])
request.set_ProductKey("ProductKey")

response = client.do_action_with_exception(request)
# python2:  print(response) 
print(response)
