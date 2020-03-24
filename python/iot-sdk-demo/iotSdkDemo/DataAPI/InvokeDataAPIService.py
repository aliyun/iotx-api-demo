#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.acs_exception.exceptions import ClientException
from aliyunsdkcore.acs_exception.exceptions import ServerException
from aliyunsdkiot.request.v20180120.InvokeDataAPIServiceRequest import InvokeDataAPIServiceRequest

client = AcsClient('<accessKeyId>', '<accessSecret>', 'cn-shanghai')

request = InvokeDataAPIServiceRequest()
request.set_accept_format('json')

request.set_ApiSrn("ApiSrn")
request.set_Params([
  {
    "ParamValue": "ParamValue",
    "ParamName": "ParamName",
    "ListParamType": "ListParamType",
    "ListParamValue": [
      "ListParamValue1",
      "ListParamValue2"
    ]
  }
])

response = client.do_action_with_exception(request)
# python2:  print(response)
print(response)
