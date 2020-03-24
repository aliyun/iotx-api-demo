#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.acs_exception.exceptions import ClientException
from aliyunsdkcore.acs_exception.exceptions import ServerException
from aliyunsdkiot.request.v20180120.BatchGetDeviceStateRequest import BatchGetDeviceStateRequest

client = AcsClient('<accessKeyId>', '<accessSecret>', 'cn-shanghai')

request = BatchGetDeviceStateRequest()
request.set_accept_format('json')

request.set_ProductKey("ProductKey")
request.set_DeviceNames(["DeviceName1","DeviceName2"])
request.set_IotIds(["IotId1","IotId2"])

response = client.do_action_with_exception(request)
# python2:  print(response) 
print(response)
