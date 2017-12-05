#!/usr/bin/python
# -*- coding: utf-8 -*-
import base64
import uuid
import string
import random


class ServiceUtil:


    # 生成num个随机设备名称，名称为uuid的base64编码，保证唯一性
    @staticmethod
    def deviceNameGenerator(num):
        nameList = []
        while num > 0:
            nameList.append(base64.urlsafe_b64encode(uuid.uuid4().bytes)[:-2])
            num -= 1
        nameList = list(set(nameList))
        print "***********namelist cout :", len(nameList)
        # print nameList
        return nameList

    @staticmethod
    def productNameGenerator():
        return 'product' + id_generator()

def id_generator(size=6, chars=string.ascii_uppercase + string.ascii_lowercase + string.digits + '_+'):
    return ''.join(random.choice(chars) for _ in range(size))

