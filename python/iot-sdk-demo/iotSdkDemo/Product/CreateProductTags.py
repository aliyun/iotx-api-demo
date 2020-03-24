#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.acs_exception.exceptions import ClientException
from aliyunsdkcore.acs_exception.exceptions import ServerException
from aliyunsdkiot.request.v20180120.CreateProductTagsRequest import CreateProductTagsRequest

client = AcsClient('<accessKeyId>', '<accessSecret>', 'cn-shanghai')

request = CreateProductTagsRequest()
request.set_accept_format('json')

request.set_ProductTags([
  {
    "TagKey": "TagKey",
    "TagValue": "TagValue"
  }
])
request.set_ProductKey("ProductKey")

response = client.do_action_with_exception(request)
# python2:  print(response) 
print(response)
