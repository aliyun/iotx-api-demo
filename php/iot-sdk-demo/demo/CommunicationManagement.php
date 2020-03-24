<?php

require __DIR__ . '/vendor/autoload.php';

use AlibabaCloud\Client\AlibabaCloud;
use AlibabaCloud\Client\Exception\ClientException;
use AlibabaCloud\Client\Exception\ServerException;

$accessKeyId = "<your accessKey>";
$accessSecret = "<your accessSecret>";
define("PRODUCT", "Iot");
define("VERSION", "2018-01-20");
define("METHOD", "POST");
/**
 * 以下示例以华东2地域及其服务接入地址为例。您在设置时，需使用您的物联网平台地域和对应的服务接入地址。
 */
define("REGION_ID", "cn-shanghai");
define("HOST", "iot.".REGION_ID.".aliyuncs.com");

// 设置一个全局客户端
try {
    AlibabaCloud::accessKeyClient($accessKeyId, $accessSecret)
        ->regionId('cn-shanghai') // replace regionId as you need
        ->asDefaultClient();
} catch (ClientException $e) {
    echo $e->getErrorMessage() . PHP_EOL;
}

$productKey = 'a1xu*****';

/**
 * 向指定Topic发布消息
 * @param $productKey
 */
function pubTest($productKey)
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['ProductKey'] = $productKey;
        $query['TopicFullName'] = '/a1Q5XoY****/device1/user/update';
        $query['MessageContent'] = 'aGVsbG8gd29ybGQ';
        $query['Qos'] = 1;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('Pub')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "向指定Topic发布消息:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '向指定Topic发布消息失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 向订阅了指定Topic的所有设备发布广播消息
 * @param $productKey
 */
function pubBroadcastTest($productKey)
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['ProductKey'] = $productKey;
        $query['TopicFullName'] = '/broadcast/a1Q5XoY****/say';
        $query['MessageContent'] = 'aGVsbG93b3JsZA==';
        $query['Qos'] = 1;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('PubBroadcast')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "向订阅了指定Topic的所有设备发布广播消息:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '向订阅了指定Topic的所有设备发布广播消息失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 向指定设备发送请求消息，并同步返回响应
 * @param $productKey
 */
function rrpcTest($productKey)
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['ProductKey'] = $productKey;
        $query['DeviceName'] = 'device1';
        $query['RequestBase64Byte'] = 'aGVsbG8gd29ybGQ';
        $query['Timeout'] = 1000;
//        $query['Topic'] = '';//不传入此参数，则使用系统默认的RRPC Topic。
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('RRpc')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "向指定设备发送请求消息，并同步返回响应:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '向指定设备发送请求消息，并同步返回响应失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}