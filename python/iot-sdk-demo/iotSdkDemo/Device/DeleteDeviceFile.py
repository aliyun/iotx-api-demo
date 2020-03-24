#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.acs_exception.exceptions import ClientException
from aliyunsdkcore.acs_exception.exceptions import ServerException
from aliyunsdkiot.request.v20180120.DeleteDeviceFileRequest import DeleteDeviceFileRequest

client = AcsClient('<accessKeyId>', '<accessSecret>', 'cn-shanghai')

request = DeleteDeviceFileRequest()
request.set_accept_format('json')

request.set_ProductKey("ProductKey")
request.set_DeviceName("DeviceName")
request.set_FileId("FileId")
request.set_IotId("IotId")

response = client.do_action_with_exception(request)
# python2:  print(response) 
print(response)
