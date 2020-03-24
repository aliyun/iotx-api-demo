#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.acs_exception.exceptions import ClientException
from aliyunsdkcore.acs_exception.exceptions import ServerException
from aliyunsdkiot.request.v20180120.CreateProductRequest import CreateProductRequest

client = AcsClient('<accessKeyId>', '<accessSecret>', 'cn-shanghai')

request = CreateProductRequest()
request.set_accept_format('json')

request.set_NodeType(1)
request.set_ProductName("ProductName")
request.set_DataFormat("DataFormat")
request.set_Description("Description")
request.set_AliyunCommodityCode("AliyunCommodityCode")
request.set_Id2(False)
request.set_CategoryId("CategoryId")
request.set_ProtocolType("ProtocolType")
request.set_NetType("NetType")
request.set_AuthType("AuthType")

response = client.do_action_with_exception(request)
# python2:  print(response) 
print(response)
