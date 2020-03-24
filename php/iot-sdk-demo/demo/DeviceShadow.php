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
$deviceName = "device1";

/**
 * 查询指定设备的影子信息
 * @param $productKey
 * @param $deviceName
 * @return void
 */
function getDeviceShadowTest($productKey,$deviceName)
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['ProductKey'] = $productKey;
        $query['DeviceName'] = $deviceName;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('GetDeviceShadow')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询指定设备的影子信息:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '查询指定设备的影子信息失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询指定设备的影子信息
 * @param $productKey
 * @param $deviceName
 * @return void
 */
function updateDeviceShadowTest($productKey,$deviceName)
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['ProductKey'] = $productKey;
        $query['DeviceName'] = $deviceName;
        $query['ShadowMessage'] = '{"method":"update","state":{"desired":{"color":"green"}},"version":2}';
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('UpdateDeviceShadow')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询指定设备的影子信息:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '查询指定设备的影子信息失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

getDeviceShadowTest($productKey, $deviceName);

updateDeviceShadowTest($productKey, $deviceName);

getDeviceShadowTest($productKey, $deviceName);