#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.acs_exception.exceptions import ClientException
from aliyunsdkcore.acs_exception.exceptions import ServerException
from aliyunsdkiot.request.v20180120.CreateDataAPIServiceRequest import CreateDataAPIServiceRequest

client = AcsClient('<accessKeyId>', '<accessSecret>', 'cn-shanghai')

request = CreateDataAPIServiceRequest()
request.set_accept_format('json')

request.set_ApiPath("ApiPath")
request.set_OriginSql("OriginSql")
request.set_TemplateSql("TemplateSql")
request.set_DisplayName("DisplayName")
request.set_FolderId("FolderId")
request.set_Desc("Desc")
request.set_RequestParams([
  {
    "Name": "Name1",
    "Type": "Type1",
    "Desc": "Desc1",
    "Example": "Example1",
    "Required": false
  }
])
request.set_ResponseParams([
  {
    "Name": "Name2",
    "Type": "Type2",
    "Desc": "Desc2",
    "Example": "Example3",
    "Required": false
  }
])

response = client.do_action_with_exception(request)
# python2:  print(response)
print(response)
