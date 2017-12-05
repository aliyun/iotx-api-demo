<?php
/*
 * Copyright (c) 2014-2016 Alibaba Group. All rights reserved.
 * License-Identifier: Apache-2.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
require_once '../util/ClientUtil.php';
require_once '../util/ServiceUtil.php';
include_once '../sdk/aliyun-php-sdk-core/Config.php';
use \Iot\Request\V20170420 as Iot;
class Test
{
    private $client;

    public function __construct()
    {
        $this->client = ClientUtil::createClient();
    }

    /**
     * 创建产品
     * @return mixed pk
     */
    public function createProductTest()
    {
        $request = new Iot\CreateProductRequest();
        $request->setName(ServiceUtil::productNameGenerator());
        $request->setDesc('iot php sdk create');
        $response = $this->client->getAcsResponse($request);
        print_r($response);
        $responseArray = ServiceUtil::object2array($response);
        if ($responseArray['Success'])
        {
            $product = ServiceUtil::object2array($responseArray['ProductInfo']);
            return $product['ProductKey'];
        }

    }

    /**
     * 修改产品
     * @param $productKey
     */
    public function updateProductTest($productKey)
    {
        $request = new Iot\UpdateProductRequest();
        $request->setProductName('productCreateByPhp');
        $request->setProductDesc('iotProduct');
        $request->setProductKey($productKey);
        $response = $this->client->getAcsResponse($request);
        print_r($response);
    }

    /**
     * 注册设备
     * @param $productKey
     * @param $deviceName
     */
    public function registDeviceTest($productKey,$deviceName)
    {
        $request = new Iot\RegistDeviceRequest();
        $request->setProductKey($productKey);
        $request->setDeviceName($deviceName);
        $response = $this->client->getAcsResponse($request);
        print_r($response);
    }

    /**
     * 根据devicename查询设备
     * @param $productKey
     * @param $deviceName
     */
    public function queryDeviceByNameTest($productKey,$deviceName)
    {
        $request = new Iot\QueryDeviceByNameRequest();
        $request->setProductKey($productKey);
        $request->setDeviceName($deviceName);
        $response = $this->client->getAcsResponse($request);
        print_r($response);
    }

    /**
     * 分页查询deivce
     * @param $productKey
     */
    public function queryDeviceTest($productKey)
    {
        $request = new Iot\QueryDeviceRequest();
        $request->setProductKey($productKey);
        $request->setPageSize(10);
        $request->setCurrentPage(0);
        $response = $this->client->getAcsResponse($request);
        print_r($response);
    }

    /**
     * 批量申请设备
     * @param $productKey
     * @param $deviceNames
     * @return mixed
     */
    public function applyDeviceWithNamesTest($productKey, $deviceNames)
    {
        $request = new Iot\ApplyDeviceWithNamesRequest();
        $request->setProductKey($productKey);
        $request->setDeviceNames($deviceNames);
        $response = $this->client->getAcsResponse($request);
        print_r($response);
        $responseArray = ServiceUtil::object2array($response);
        if ($responseArray['Success'])
        {
            return $responseArray['ApplyId'];
        }
    }

    /**
     * 查询申请单执行状态
     * @param $applyId
     */
    public function queryApplyStatusTest($applyId)
    {
        $request = new Iot\QueryApplyStatusRequest();
        $request->setApplyId($applyId);
        $response = $this->client->getAcsResponse($request);
        print_r($response);
    }

    /**
     * 分页查询申请的设备
     * @param $applyId
     */
    public function queryPageByApplyIdTest($applyId)
    {
        $request = new Iot\QueryPageByApplyIdRequest();
        $request->setApplyId($applyId);
        $request->setCurrentPage(0);
        $request->setPageSize(10);
        $response = $this->client->getAcsResponse($request);
        print_r($response);
    }

    /**
     * 批量查询设备状态
     * @param $productKey
     * @param $deviceNames
     */
    public function batchGetDeviceStatusTest($productKey, $deviceNames)
    {
        $request = new Iot\BatchGetDeviceStateRequest();
        $request->setProductKey($productKey);
        $request->setDeviceNames($deviceNames);
        $response = $this->client->getAcsResponse($request);
        print_r($response);
    }

    /**
     * 更新设备影子
     * @param $productKey
     * @param $deviceName
     */
    public function updateDeviceShadowTest($productKey, $deviceName)
    {
        $request = new Iot\UpdateDeviceShadowRequest();
        $request->setProductKey($productKey);
        $request->setDeviceName($deviceName);
        $request->setShadowMessage('{"method":"update","state":{"desired":{"window":"open"}},"version":1}');
        $response = $this->client->getAcsResponse($request);
        print_r($response);
    }


    /**
     * 获取设备影子
     * @param $productKey
     * @param $deviceName
     */
    public function getDeviceShadowTest($productKey, $deviceName)
    {
        $request = new Iot\GetDeviceShadowRequest();
        $request->setProductKey($productKey);
        $request->setDeviceName($deviceName);
        $response = $this->client->getAcsResponse($request);
        print_r($response);
    }

    /**
     * 发送消息
     * @param $productKey
     * @param $deviceName
     */
    public function pubTest($productKey, $deviceName)
    {
        $request = new Iot\PubRequest();
        $topic = "/". $productKey ."/" . $deviceName ."/get";
        print_r ($topic);
        #hello world Base64 String
        $messageContent = 'aGVsbG8gd29ybGQ=';
        $request->setProductKey($productKey);
        $request->setTopicFullName($topic);
        $request->setMessageContent($messageContent);
        $request->setQos(0);
        $response = $this->client->getAcsResponse($request);
        print_r($response);
    }


    /**
     * 发送广播消息
     * @param $productKey
     */
    public function pubBroadcastTest($productKey)
    {
        $request = new Iot\PubBroadcastRequest();
        $topic = "/broadcast/" . $productKey . "/test";
        print ($topic);
        #hello world Base64 String
        $messageContent = 'aGVsbG8gd29ybGQ=';
        $request->setProductKey($productKey);
        $request->setTopicFullName($topic);
        $request->setMessageContent($messageContent);
        $response = $this->client->getAcsResponse($request);
        print_r($response);
    }


    /**
     * rrpc请求 需要配合设备端一同使用才会成功
     * @param $productKey
     * @param $deviceName
     */
    public function rrpcTest($productKey, $deviceName)
    {
        $request = new Iot\RRpcRequest();
        $request->setProductKey($productKey);
        $request->setDeviceName($deviceName);
        $request->setRequestBase64Byte("aGVsbG8gd29ybGQ=");
        $request->setTimeout(5000);
        $response = $this->client->getAcsResponse($request);
        print_r($response);
    }

}
$t = new Test();
$productKey = $t->createProductTest();
$t->updateProductTest($productKey);
$deviceName = 'device';
$deviceNames = ServiceUtil::deviceNameGenertor(6);
$t->registDeviceTest($productKey,$deviceName);
$t->queryDeviceByNameTest($productKey,$deviceName);
$t->queryDeviceTest($productKey);
$applyId = $t->applyDeviceWithNamesTest($productKey,$deviceNames);
sleep(2);
$t->queryApplyStatusTest($applyId);
$t->queryPageByApplyIdTest($applyId);
$t->batchGetDeviceStatusTest($productKey,$deviceNames);
$t->updateDeviceShadowTest($productKey,$deviceName);
$t->getDeviceShadowTest($productKey,$deviceName);
$t->pubTest($productKey, $deviceName);
$t->pubBroadcastTest($productKey);
$t->rrpcTest($productKey, $deviceName);

