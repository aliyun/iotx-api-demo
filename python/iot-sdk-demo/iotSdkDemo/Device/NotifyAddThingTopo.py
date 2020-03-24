#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.acs_exception.exceptions import ClientException
from aliyunsdkcore.acs_exception.exceptions import ServerException
from aliyunsdkiot.request.v20180120.NotifyAddThingTopoRequest import NotifyAddThingTopoRequest

client = AcsClient('<accessKeyId>', '<accessSecret>', 'cn-shanghai')

request = NotifyAddThingTopoRequest()
request.set_accept_format('json')

request.set_DeviceListStr("DeviceListStr")
request.set_GwIotId("GwIotId")
request.set_GwProductKey("GwProductKey")
request.set_GwDeviceName("GwDeviceName")

response = client.do_action_with_exception(request)
# python2:  print(response) 
print(response)
