#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.acs_exception.exceptions import ClientException
from aliyunsdkcore.acs_exception.exceptions import ServerException
from aliyunsdkiot.request.v20180120.QueryDeviceGroupListRequest import QueryDeviceGroupListRequest

client = AcsClient('<accessKeyId>', '<accessSecret>', 'cn-shanghai')

request = QueryDeviceGroupListRequest()
request.set_accept_format('json')

request.set_SuperGroupId("SuperGroupId")
request.set_GroupName("GroupName")
request.set_CurrentPage("CurrentPage")
request.set_PageSize("PageSize")

response = client.do_action_with_exception(request)
# python2:  print(response) 
print(response)