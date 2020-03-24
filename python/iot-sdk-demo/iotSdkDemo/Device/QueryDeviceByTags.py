#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.acs_exception.exceptions import ClientException
from aliyunsdkcore.acs_exception.exceptions import ServerException
from aliyunsdkiot.request.v20180120.QueryDeviceByTagsRequest import QueryDeviceByTagsRequest

client = AcsClient('<accessKeyId>', '<accessSecret>', 'cn-shanghai')

request = QueryDeviceByTagsRequest()
request.set_accept_format('json')

request.set_Tags([
  {
    "TagKey": "TagKey",
    "TagValue": "TagValue"
  }
])
request.set_CurrentPage("CurrentPage")
request.set_PageSize("PageSize")

response = client.do_action_with_exception(request)
# python2:  print(response) 
print(response)
