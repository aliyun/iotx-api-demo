
#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.acs_exception.exceptions import ClientException
from aliyunsdkcore.acs_exception.exceptions import ServerException
from aliyunsdkiot.request.v20180120.CreateProductRequest import CreateProductRequest

client = AcsClient('*************', '************', 'cn-shanghai')

request = CreateProductRequest()
request.set_accept_format('json')

request.set_NodeType(1)
request.set_ProductName("ProductName33433")

response = client.do_action_with_exception(request)
# python2:  print(response)
print(response)
