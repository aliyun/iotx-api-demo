#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.acs_exception.exceptions import ClientException
from aliyunsdkcore.acs_exception.exceptions import ServerException
from aliyunsdkiot.request.v20180120.DeleteTopicRouteTableRequest import DeleteTopicRouteTableRequest

client = AcsClient('<accessKeyId>', '<accessSecret>', 'cn-hangzhou')

request = DeleteTopicRouteTableRequest()
request.set_accept_format('json')

request.set_DstTopics(["DstTopic1","DstTopic2"])
request.set_SrcTopic("SrcTopic")

response = client.do_action_with_exception(request)
# python2:  print(response)
print(str(response, encoding='utf-8'))
