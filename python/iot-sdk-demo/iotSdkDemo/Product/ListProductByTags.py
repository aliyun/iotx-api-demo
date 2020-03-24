#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.acs_exception.exceptions import ClientException
from aliyunsdkcore.acs_exception.exceptions import ServerException
from aliyunsdkiot.request.v20180120.ListProductByTagsRequest import ListProductByTagsRequest

client = AcsClient('<accessKeyId>', '<accessSecret>', 'cn-shanghai')

request = ListProductByTagsRequest()
request.set_accept_format('json')

request.set_PageSize("PageSize")
request.set_ProductTags([
  {
    "TagKey": "TagKey",
    "TagValue": "TagValue"
  }
])

response = client.do_action_with_exception(request)
# python2:  print(response) 
print(response)
