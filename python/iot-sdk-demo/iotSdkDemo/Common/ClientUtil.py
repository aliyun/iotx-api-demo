#!/usr/bin/python
# -*- coding: utf-8 -*-
from aliyunsdkcore import  client

class ClientUtil:
    @staticmethod
    def createClient():
        #阿里云账号
        accessKeyId = '*****'
        accessKeySecret = '********'
        regionId = 'cn-shanghai'
        clt = client.AcsClient(accessKeyId, accessKeySecret, regionId)
        return clt