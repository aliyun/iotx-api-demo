#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.acs_exception.exceptions import ClientException
from aliyunsdkcore.acs_exception.exceptions import ServerException
from aliyunsdkiot.request.v20180120.GetRuleActionRequest import GetRuleActionRequest

client = AcsClient('<accessKeyId>', '<accessSecret>', 'cn-shanghai')

request = GetRuleActionRequest()
request.set_accept_format('json')

request.set_ActionId("ActionId")

response = client.do_action_with_exception(request)
# python2:  print(response) 
print(response)