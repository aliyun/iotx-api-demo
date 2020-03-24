<?php

require __DIR__ . '/vendor/autoload.php';
require __DIR__ . "/ServiceUtil.php";

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

$productKey = "a1xu*****";
$device = array(
    "DeviceName" => "device_ldKji",
    "ProductKey" => "a1xu*****",
    "DeviceSecret" => "DTaGohN9H******************Shzcg",
    "IotId" => "inrng***************00",
);

$deviceList = array();
$deviceNameList = array();

$gwProductKey = "a18P5*******";
$gwDevice = array(
    "DeviceName" => "device_ldKjl",
    "ProductKey" => $gwProductKey,
    "DeviceSecret" => "vX9Gyh*******************N7PcDQ",
    "IotId" => "y3u0b***************00",
);

/**
 * 删除设备
 * @param $device
 */
function deleteDeviceTest($device)
{
    try {
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('DeleteDevice')
            ->options([
                'query' => [
                    'RegionId' => REGION_ID,
//                    'IotId' => $device['IotId'], //如果传入该参数，则无需传入 ProductKey和 DeviceName。如果您同时传入 IotId和 ProductKey与 DeviceName组合，则以 IotId为准。
                    'ProductKey' => $device['ProductKey'],
                    'DeviceName' => $device['DeviceName'],
                ],
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "删除设备:". PHP_EOL;
        print_r($result2Array);
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 注册设备
 * @param $productKey
 * @return string
 */
function registerDeviceTest($productKey)
{
    try {
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('RegisterDevice')
            ->options([
                'query' => [
                    'RegionId' => REGION_ID,
                    'ProductKey' => $productKey,
                    'DeviceName' => MyUtil\ServiceUtil::deviceNameGenertor(5),
                ],
            ])
            ->request();

        $result2Array = $result->toArray();
        echo "注册设备:". PHP_EOL;
        print_r($result2Array);
        if ($result2Array['Success']) {
            return $result2Array['Data'];
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
    echo "注册设备失败";
    return null;
}

/**
 * 查询设备列表
 * @param $productKey
 * @return array|null
 */
function queryDeviceTest($productKey)
{
    try {
//        $deviceName = MyUtil\ServiceUtil::deviceNameGenertor(4);
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryDevice')
            ->options([
                'query' => [
                    'RegionId' => REGION_ID,
                    'ProductKey' => $productKey,
                    'PageSize' => 10,
                    'CurrentPage' => 1,
                ],
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询设备列表:" . PHP_EOL;
        print_r($result2Array);
        if ($result2Array['Success'] ) {
            if ($result2Array['Total']) {
                return $result2Array['Data']['DeviceInfo'];
            }
            echo "设备列表为空";
            return null;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
    echo "查询设备列表失败";
    return null;
}

/**
 * 批量查询设备详情
 *
 * @param $productKey
 * @param $deviceNameList
 */
function batchQueryDeviceDetailTest($productKey, $deviceNameList)
{
    try {
        $query = [];
        $query['RegionId'] = REGION_ID;
        $query['ProductKey'] = $productKey;
        $query = array_merge($query, $deviceNameList);
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('BatchQueryDeviceDetail')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "批量查询设备详情:" . PHP_EOL;
        print_r($result2Array);
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询设备详情
 *
 * @param $device
 */
function queryDeviceDetailTest($device)
{
    try {
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryDeviceDetail')
            ->options([
                'query' => [
                    'RegionId' => REGION_ID,
//                    'IotId' => $device['IotId'], //如果传入该参数，则无需传入 ProductKey和 DeviceName。如果您同时传入 IotId和 ProductKey与 DeviceName组合，则以 IotId为准。
                    'ProductKey' => $device['ProductKey'],
                    'DeviceName' => $device['DeviceName'],
                ],
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询设备详情:".PHP_EOL;
        print_r($result2Array);
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询设备运行状态
 *
 * @param $device
 */
function getDeviceStatusTest($device)
{
    try {
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('GetDeviceStatus')
            ->options([
                'query' => [
                    'RegionId' => REGION_ID,
                    'IotId' => $device['IotId'], //如果传入该参数，则无需传入 ProductKey和 DeviceName。如果您同时传入 IotId和 ProductKey与 DeviceName组合，则以 IotId为准。
//                    'ProductKey' => $device['ProductKey'],
//                    'DeviceName' => $device['DeviceName'],
                ],
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询设备运行状态:".PHP_EOL;
        print_r($result2Array);
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 批量查看同一产品下指定设备的运行状态。
 *
 * @param $productKey
 * @param $deviceNameList
 */
function batchGetDeviceStateTest($productKey, $deviceNameList)
{
    try {
        $query = [];
        $query['RegionId'] = REGION_ID;
        $query['ProductKey'] = $productKey;
        $query = array_merge($query, $deviceNameList);
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('BatchGetDeviceState')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "批量查看同一产品下指定设备的运行状态:".PHP_EOL;
        print_r($result2Array);
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 禁用设备
 *
 * @param $device
 */
function disableThingTest($device)
{
    try {
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('DisableThing')
            ->options([
                'query' => [
                    'RegionId' => REGION_ID,
                    'IotId' => $device['IotId'], //如果传入该参数，则无需传入 ProductKey和 DeviceName。如果您同时传入 IotId和 ProductKey与 DeviceName组合，则以 IotId为准。
//                    'ProductKey' => $device['ProductKey'],
//                    'DeviceName' => $device['DeviceName'],
                ],
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "禁用设备:".PHP_EOL;
        print_r($result2Array);
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 启用被禁用的设备
 *
 * @param $device
 */
function enableThingTest($device)
{
    try {
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('EnableThing')
            ->options([
                'query' => [
                    'RegionId' => REGION_ID,
                    'IotId' => $device['IotId'], //如果传入该参数，则无需传入 ProductKey和 DeviceName。如果您同时传入 IotId和 ProductKey与 DeviceName组合，则以 IotId为准。
//                    'ProductKey' => $device['ProductKey'],
//                    'DeviceName' => $device['DeviceName'],
                ],
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "启用被禁用的设备:".PHP_EOL;
        print_r($result2Array);
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 批量检查设备名称合法性
 *
 * @param $productKey
 * @param $deviceNameList
 * @return int|null
 */
function batchCheckDeviceNamesTest($productKey, $deviceNameList)
{
    try {
        // step 1.批量检查设备名称合法性，并生成申请批次ID，这个过程是异步的过程，可能需要等待一定时间检查任务才能完成
        $query = [];
        $query['RegionId'] = REGION_ID;
        $query['ProductKey'] = $productKey;
        $query = array_merge($query, $deviceNameList);
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('BatchCheckDeviceNames')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "批量检查设备名称合法性:".PHP_EOL;
        print_r($result2Array);
        $applyId = null;
        if ($result2Array['Success'] && $result2Array['Data']) {
            $applyId = $result2Array['Data']['ApplyId'];
        }
        return $applyId;

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
    return null;
}

/**
 * 查询批量注册设备申请批次的处理状态和结果。
 * @param $productKey
 * @param $applyId
 * @return string|null
 */
function queryBatchRegisterDeviceStatusTest($productKey, $applyId)
{
    try {
        // step 2.查询批量注册设备申请的处理状态和结果。
        $query = [];
        $query['RegionId'] = REGION_ID;
        $query['ProductKey'] = $productKey;
        $query['ApplyId'] = $applyId;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryBatchRegisterDeviceStatus')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询设备名称合法性检查的处理状态和结果:".PHP_EOL;
        print_r($result2Array);
        if ($result2Array['Success'] && $result2Array['Data']) {
            return $result2Array['Data']['Status'];
        } else {
            echo "查询批量注册设备申请的处理状态和结果失败。applyId:".$applyId.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
    return null;
}

/**
 * 根据申请批次ID（ApplyId）批量注册设备。
 * @param $productKey
 * @param $applyId
 * @return int|null
 */
function batchRegisterDeviceWithApplyIdTest($productKey, $applyId)
{
    try {
        // step 2.查询批量注册设备申请的处理状态和结果。
        $query = [];
        $query['RegionId'] = REGION_ID;
        $query['ProductKey'] = $productKey;
        $query['ApplyId'] = $applyId;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('BatchRegisterDeviceWithApplyId')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "根据申请批次ID（ApplyId）批量注册设备:".PHP_EOL;
        print_r($result2Array);
        if ($result2Array['Success'] && $result2Array['Data']) {
            return $result2Array['Data']['ApplyId'];
        } else {
            echo "根据申请批次ID（ApplyId）批量注册设备。applyId:".$applyId;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
    return null;
}

/**
 * 查询批量注册的设备信息
 * @param $applyId
 */
function queryPageByApplyIdTest($applyId)
{
    try {
        // step 2.查询批量注册设备申请的处理状态和结果。
        $query = [];
        $query['RegionId'] = REGION_ID;
        $query['ApplyId'] = $applyId;
        $query['PageSize'] = 50;
        $query['CurrentPage'] = 1;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryPageByApplyId')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询批量注册的设备信息:".PHP_EOL;
        print_r($result2Array);
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 在指定产品下批量注册多个随机设备名称的设备。
 * @param $productKey
 * @param $count
 * @return int|null
 */
function batchRegisterDeviceTest($productKey, $count)
{
    try {
        // step 1.在一个产品下批量注册多个设备，并且随机生成设备名，这个过程是异步的过程，可能需要等待一定时间检查任务才能完成
        $query = [];
        $query['RegionId'] = REGION_ID;
        $query['ProductKey'] = $productKey;
        $query['Count'] = $count;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('BatchRegisterDevice')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "在指定产品下批量注册多个随机设备名称的设备:".PHP_EOL;
        print_r($result2Array);
        $applyId = null;
        if ($result2Array['Success'] && $result2Array['Data']) {
            $applyId = $result2Array['Data']['ApplyId'];
        }
        return $applyId;

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
    return null;
}

/**
 * 批量注册自定义名称设备流程示意
 * @param $productKey
 */
function batchRegisterDeviceByApplyIdDemo($productKey)
{
    // step 1.批量检查设备名称合法性，并生成申请批次ID，这个过程是异步的过程，需要等待一定时间
    $deviceNameArray = MyUtil\ServiceUtil::deviceNameListGenertor(10);
    $deviceNameList = [];
    for ($i = 0; $i < 10; $i++) {
        $deviceNameList['DeviceName.' . ($i+1)] = $deviceNameArray[$i];
    }
    $applyId = batchCheckDeviceNamesTest($productKey, $deviceNameList);
    sleep(10);

    // step 2.查询批量注册设备申请的处理状态和结果。
    $checkStatus = queryBatchRegisterDeviceStatusTest($productKey, $applyId);

    for ($i = 0;$i<3;$i++) {
        if ($checkStatus == "CHECK") {
            sleep(3);
            $checkStatus = queryBatchRegisterDeviceStatusTest($productKey, $applyId);
        } else {
            if ($checkStatus == "CHECK_FAILED") {
                echo "查询批量注册设备申请的处理状态和结果失败。applyId:".$applyId.PHP_EOL;
            }
            break;
        }
    }

    // step 3.根据申请批次ID批量注册设备。这个过程是异步的过程，需要等待一定时间
    batchRegisterDeviceWithApplyIdTest($productKey, $applyId);
    sleep(10);

    // step 4.查询批量注册的设备信息
    queryPageByApplyIdTest($applyId);
}

/**
 * 批量注册自定义名称设备流程示意
 * @param $productKey
 */
function batchRegisterDeviceDemo($productKey)
{
    // step 1.批量检查设备名称合法性，并生成申请批次ID，这个过程是异步的过程，需要等待一定时间
    $applyId = batchRegisterDeviceTest($productKey, 10);
    sleep(10);

    // step 2.查询批量注册设备申请的处理状态和结果。
    $checkStatus = queryBatchRegisterDeviceStatusTest($productKey, $applyId);

    for ($i = 0;$i<3;$i++) {
        if ($checkStatus == "CREATE") {
            sleep(3);
            $checkStatus = queryBatchRegisterDeviceStatusTest($productKey, $applyId);
        } else {
            if ($checkStatus == "CREATE_FAILED") {
                echo "查询批量注册设备申请的处理状态和结果失败。applyId:".$applyId.PHP_EOL;
            }
            break;
        }
    }
    sleep(10);

    // step 3.查询批量注册的设备信息
    queryPageByApplyIdTest($applyId);
}

/**
 * 批量修改设备备注名称。
 * @param $deviceList
 */
function batchUpdateDeviceNicknameTest($deviceList)
{
    try {
        $deviceNameArray = MyUtil\ServiceUtil::deviceNameListGenertor(count($deviceList));
        $deviceNicknameInfo	 = [];
        for ($i = 0; $i < count($deviceList); $i++) {
            $deviceNicknameInfo['DeviceNicknameInfo.' . ($i+1).'.ProductKey'] = $deviceList[$i]['ProductKey'];
            $deviceNicknameInfo['DeviceNicknameInfo.' . ($i+1).'.DeviceName'] = $deviceList[$i]['DeviceName'];
            $deviceNicknameInfo['DeviceNicknameInfo.' . ($i+1).'.Nickname'] = $deviceNameArray[$i];//如果不设置nickname表示删除nickname
        }
        $query = [];
        $query['RegionId'] = REGION_ID;
        $query = array_merge($query, $deviceNicknameInfo);
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('BatchUpdateDeviceNickname')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "批量修改设备备注名称:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo "批量修改设备备注名称失败" . PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询指定设备的事件记录
 * @param $device
 */
function queryDeviceEventDataTest($device)
{
    try {
        $query = [];
        $query['RegionId'] = REGION_ID;
        $query['ProductKey'] = $device['ProductKey'];
        $query['DeviceName'] = $device['DeviceName'];
        $query['EventType'] = "alert"; //可选参数
        $query['Identifier'] = "hotAlarm";
        $query['StartTime'] = ((time() - 7 * 24 * 60 * 60) * 1000);
        $query['EndTime'] = (time()*1000);
        $query['PageSize'] = 10;
        $query['Asc'] = 0;

        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryDeviceEventData')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询指定设备的事件记录:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo "查询指定设备的事件记录失败" . PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询指定设备的属性记录
 * @param $device
 */
function queryDevicePropertyDataTest($device)
{
    try {
        $query = [];
        $query['RegionId'] = REGION_ID;
        $query['ProductKey'] = $device['ProductKey'];
        $query['DeviceName'] = $device['DeviceName'];
        $query['Identifier'] = "humidity";
        $query['StartTime'] = ((time() - 7 * 24 * 60 * 60) * 1000);
        $query['EndTime'] = (time()*1000);
        $query['PageSize'] = 10;
        $query['Asc'] = 0;

        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryDevicePropertyData')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询指定设备的属性记录:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo "查询指定设备的属性记录失败" . PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询指定设备的服务记录
 * @param $device
 */
function queryDeviceServiceDataTest($device)
{
    try {
        $query = [];
        $query['RegionId'] = REGION_ID;
        $query['ProductKey'] = $device['ProductKey'];
        $query['DeviceName'] = $device['DeviceName'];
        $query['Identifier'] = "switch";
        $query['StartTime'] = ((time() - 7 * 24 * 60 * 60) * 1000);
        $query['EndTime'] = (time()*1000);
        $query['PageSize'] = 10;
        $query['Asc'] = 0;

        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryDeviceServiceData')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询指定设备的服务记录:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo "查询指定设备的服务记录失败" . PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 批量查询指定设备的属性记录
 *
 * 注意事项：
 * 0：倒序。倒序查询时，StartTime必须大于EndTime。
 * 1：正序。正序查询时，StartTime必须小于EndTime。
 * @param $device
 */
function queryDevicePropertiesDataTest($device)
{
    try {
        $query = [];
        $query['RegionId'] = REGION_ID;
        $query['ProductKey'] = $device['ProductKey'];
        $query['DeviceName'] = $device['DeviceName'];
        $query['Identifier.1'] = "temperature";
        $query['Identifier.2'] = "humidity";
        $query['StartTime'] = ((time() - 7 * 24 * 60 * 60) * 1000);
        $query['EndTime'] = (time()*1000);
        $query['PageSize'] = 10;
        $query['Asc'] = 1;

        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryDevicePropertiesData')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "批量查询指定设备的属性记录:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo "批量查询指定设备的属性记录失败" . PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 调用设备的指定服务
 * @param $device
 */
function invokeThingServiceTest($device)
{
    try {
        $query = [];
        $query['RegionId'] = REGION_ID;
        $query['ProductKey'] = $device['ProductKey'];
        $query['DeviceName'] = $device['DeviceName'];
        $query['Identifier'] = "switch";
        $query['Args'] = '{"status":"开"}';

        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('InvokeThingService')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "调用设备的指定服务:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo "调用设备的指定服务失败" . PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 批量调用设备服务
 * @param $productKey
 * @param $deviceNameList
 */
function invokeThingsServiceTest($productKey, $deviceNameList)
{
    try {
        $query = [];
        $query['RegionId'] = REGION_ID;
        $query['ProductKey'] = $productKey;
        $query['Identifier'] = "switch";
        $query['Args'] = '{"status":"开"}';
        $query = array_merge($query, $deviceNameList);

        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('InvokeThingsService')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "批量调用设备服务:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo "批量调用设备服务失败" . PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询指定设备的属性快照
 * @param $device
 */
function queryDevicePropertyStatusTest($device)
{
    try {
        $query = [];
        $query['RegionId'] = REGION_ID;
//        $query['IotId'] = $device['IotId'];
        $query['ProductKey'] = $device['ProductKey'];
        $query['DeviceName'] = $device['DeviceName'];

        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryDevicePropertyStatus')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询指定设备的属性快照:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo "查询指定设备的属性快照失败" . PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 为指定设备设置属性值
 * @param $device
 */
function setDevicePropertyTest($device)
{
    try {
        $query = [];
        $query['RegionId'] = REGION_ID;
//        $query['IotId'] = $device['IotId'];
        $query['ProductKey'] = $device['ProductKey'];
        $query['DeviceName'] = $device['DeviceName'];
        $query['Items'] = '{"temperature":30,"humidity":31}';//只有读写类型属性可以设置成功

        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('SetDeviceProperty')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "为指定设备设置属性值:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo "为指定设备设置属性值失败" . PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 批量设置设备属性值。
 * @param $productKey
 * @param $deviceNameList
 */
function setDevicesPropertyTest($productKey, $deviceNameList)
{
    try {
        $query = [];
        $query['RegionId'] = REGION_ID;
//        $query['IotId'] = $device['IotId'];
        $query['ProductKey'] = $productKey;
        $query = array_merge($query, $deviceNameList);
        $query['Items'] = '{"temperature":30,"humidity":31}';//只有读写类型属性可以设置成功

        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('SetDevicesProperty')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "批量设置设备属性值:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo "批量设置设备属性值失败" . PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 为指定设备设置标签
 * @param $device
 */
function saveDevicePropTest($device)
{
    try {
        $query['RegionId'] = REGION_ID;
//        $query['IotId'] = $device['IotId'];
        $query['ProductKey'] = $device['ProductKey'];
        $query['DeviceName'] = $device['DeviceName'];
        $query['Props'] = '{"color":"green","length":12}';//如果标签已存在，则覆盖；如果标签不存在，则新增
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('SaveDeviceProp')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "为指定设备设置标签:".PHP_EOL;
        print_r($result2Array);
        if (!$result->toArray()['Success'] == true) {
            echo '为指定设备设置标签失败'.PHP_EOL;
            return;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询指定设备的标签列表。
 * @param $device
 */
function queryDevicePropTest($device)
{
    try {
        $query = [];
        $query['RegionId'] = REGION_ID;
//        $query['IotId'] = $device['IotId'];
        $query['ProductKey'] = $device['ProductKey'];
        $query['DeviceName'] = $device['DeviceName'];

        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryDeviceProp')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询指定设备的标签列表:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo "查询指定设备的标签列表失败" . PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 删除设备下的指定标签
 * @param $device
 */
function deleteDevicePropTest($device)
{
    try {
        $query['RegionId'] = REGION_ID;
//        $query['IotId'] = $device['IotId'];
        $query['ProductKey'] = $device['ProductKey'];
        $query['DeviceName'] = $device['DeviceName'];
        $query['PropKey'] = 'length';//如果标签已存在，则覆盖；如果标签不存在，则新增
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('DeleteDeviceProp')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "删除设备下的指定标签:".PHP_EOL;
        print_r($result2Array);
        if (!$result->toArray()['Success'] == true) {
            echo '删除设备下的指定标签失败'.PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 通过标签查询设备。
 */
function queryDeviceByTagsTest()
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['PageSize'] = 10;
        $query['CurrentPage'] = 1;
        $query['Tag.1.TagKey'] = 'length'; //多个标签之间是或的关系
        $query['Tag.2.TagKey'] = 'color';
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryDeviceByTags')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "通过标签查询设备:".PHP_EOL;
        print_r($result2Array);
        if (!$result->toArray()['Success'] == true) {
            echo '通过标签查询设备失败'.PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询指定设备的拓扑关系
 * @param $device
 */
function getThingTopoTest($device)
{
    try {
        $query['RegionId'] = REGION_ID;
//        $query['IotId'] = $device['IotId'];
        $query['ProductKey'] = $device['ProductKey'];
        $query['DeviceName'] = $device['DeviceName'];
        $query['PageSize'] = 10;
        $query['PageNo'] = 1;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('GetThingTopo')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询指定设备的拓扑关系:".PHP_EOL;
        print_r($result2Array);
        if (!$result->toArray()['Success'] == true) {
            echo '查询指定设备的拓扑关系失败'.PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 通知网关设备增加拓扑关系
 *
 * 返回的成功结果只表示添加拓扑关系的指令成功下发给网关，但并不表示网关成功添加拓扑关系。
 * 开发网关设备端时，需订阅通知添加拓扑关系消息的Topic。
 * @param $gwDevice
 * @param $deviceList
 */
function notifyAddThingTopoTest($gwDevice, $deviceList)
{
    try {
        $deviceArray = [];
        for ($i = 0; $i < count($deviceList); $i++) {
//            $deviceInfo['IotId'] = $deviceList[$i]['IotId'];
            $deviceInfo = [];
            $deviceInfo['productKey'] = $deviceList[$i]['ProductKey'];
            $deviceInfo['deviceName'] = $deviceList[$i]['DeviceName'];
//            echo $deviceInfo;
            array_push($deviceArray, $deviceInfo);
        }

        $query['RegionId'] = REGION_ID;
//        $query['GwIotId'] = $gwDevice['IotId'];
        $query['GwProductKey'] = $gwDevice['ProductKey'];
        $query['GwDeviceName'] = $gwDevice['DeviceName'];
        $query['DeviceListStr'] = json_encode($deviceArray);
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('NotifyAddThingTopo')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "通知网关设备增加拓扑关系:".PHP_EOL;
        print_r($result2Array);
        if (!$result->toArray()['Success'] == true) {
            echo '通知网关设备增加拓扑关系失败'.PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 移除网关与子设备的拓扑关系
 * @param $device
 */
function removeThingTopoTest($device)
{
    try {

        $query['RegionId'] = REGION_ID;
//        $query['GwIotId'] = $gwDevice['IotId'];
        $query['ProductKey'] = $device['ProductKey'];
        $query['DeviceName'] = $device['DeviceName'];
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('RemoveThingTopo')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "移除网关与子设备的拓扑关系:".PHP_EOL;
        print_r($result2Array);
        if (!$result->toArray()['Success'] == true) {
            echo '移除网关与子设备的拓扑关系失败'.PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询设备统计数据
 * @param $productKey
 */
function queryDeviceStatisticsTest($productKey)
{
    try {

        $query['RegionId'] = REGION_ID;
//        $query['GwIotId'] = $gwDevice['IotId'];
        $query['ProductKey'] = $productKey;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryDeviceStatistics')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询设备统计数据:".PHP_EOL;
        print_r($result2Array);
        if (!$result->toArray()['Success'] == true) {
            echo '查询设备统计数据失败'.PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 为指定设备批量设置期望属性值
 * @param $device
 */
function setDeviceDesiredPropertyTest($device)
{
    try {
        $query = [];
        $query['RegionId'] = REGION_ID;
//        $query['IotId'] = $device['IotId'];
        $query['ProductKey'] = $device['ProductKey'];
        $query['DeviceName'] = $device['DeviceName'];
        $query['Items'] = '{"temperature":25,"humidity":28}';//只读属性不支持设置期望属性值。
        $query['Versions'] = '{}';//首次设置期望属性值时，如果指定Version参数，则需指定Version值为0。若不确定当前期望值的版本号，可以不传入版本号，但仍需传入有效的JSON，即传入{}。

        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('SetDeviceDesiredProperty')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "为指定设备批量设置期望属性值:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo "为指定设备批量设置期望属性值失败" . PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询指定设备的期望属性值
 * @param $device
 */
function queryDeviceDesiredPropertyTest($device)
{
    try {
        $query = [];
        $query['RegionId'] = REGION_ID;
//        $query['IotId'] = $device['IotId'];
        $query['ProductKey'] = $device['ProductKey'];
        $query['DeviceName'] = $device['DeviceName'];
        $query['Identifiers.1'] = 'temperature';
        $query['Identifiers.2'] = 'humidity';

        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryDeviceDesiredProperty')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询指定设备的期望属性值:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo "查询指定设备的期望属性值失败" . PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询指定设备上传到物联网平台的所有文件列表
 * @param $device
 */
function queryDeviceFileListTest($device)
{
    try {
        $query['RegionId'] = REGION_ID;
//        $query['IotId'] = $device['IotId'];
        $query['ProductKey'] = $device['ProductKey'];
        $query['DeviceName'] = $device['DeviceName'];
        $query['PageSize'] = 10;
        $query['CurrentPage'] = 1;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryDeviceFileList')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询指定设备上传到物联网平台的所有文件列表:".PHP_EOL;
        print_r($result2Array);
        if (!$result->toArray()['Success'] == true) {
            echo '查询指定设备上传到物联网平台的所有文件列表失败'.PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询指定设备上传到物联网平台的指定文件信息
 * @param $device
 */
function queryDeviceFileTest($device)
{
    try {
        $query['RegionId'] = REGION_ID;
//        $query['IotId'] = $device['IotId'];
        $query['ProductKey'] = $device['ProductKey'];
        $query['DeviceName'] = $device['DeviceName'];
        $query['FileId'] = '6UCo1SqbqnQEoh9aKqD******';
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryDeviceFile')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询指定设备上传到物联网平台的指定文件信息:".PHP_EOL;
        print_r($result2Array);
        if (!$result->toArray()['Success'] == true) {
            echo '查询指定设备上传到物联网平台的指定文件信息失败'.PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询指定设备上传到物联网平台的指定文件信息
 * @param $device
 */
function deleteDeviceFileTest($device)
{
    try {
        $query['RegionId'] = REGION_ID;
//        $query['IotId'] = $device['IotId'];
        $query['ProductKey'] = $device['ProductKey'];
        $query['DeviceName'] = $device['DeviceName'];
        $query['FileId'] = '6UCo1SqbqnQEoh9aKqD******';
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('DeleteDeviceFile')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询指定设备上传到物联网平台的指定文件信息:".PHP_EOL;
        print_r($result2Array);
        if (!$result->toArray()['Success'] == true) {
            echo '查询指定设备上传到物联网平台的指定文件信息失败'.PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 根据挂载的子设备信息，查询对应的网关设备信息
 * @param $device
 */
function getGatewayBySubDeviceTest($device)
{
    try {
        $query['RegionId'] = REGION_ID;
//        $query['IotId'] = $device['IotId'];
        $query['ProductKey'] = $device['ProductKey'];
        $query['DeviceName'] = $device['DeviceName'];
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('GetGatewayBySubDevice')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "根据挂载的子设备信息，查询对应的网关设备信息:".PHP_EOL;
        print_r($result2Array);
        if (!$result->toArray()['Success'] == true) {
            echo '根据挂载的子设备信息，查询对应的网关设备信息失败'.PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询LoRaWAN入网凭证列表
 */
function queryLoRaJoinPermissionsTest()
{
    try {
        $query['RegionId'] = REGION_ID;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryLoRaJoinPermissions')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询LoRaWAN入网凭证列表:".PHP_EOL;
        print_r($result2Array);
        if (!$result->toArray()['Success'] == true) {
            echo '查询LoRaWAN入网凭证列表失败'.PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 生成批量注册LoRaWAN设备的任务
 * @param $productKey
 */
function CreateLoRaNodesTaskTest($productKey)
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['ProductKey'] = $productKey;
        $query['DeviceInfo.1.DevEui'] = 'd896e0ffffet****';
        $query['DeviceInfo.1.PinCode'] = 562959;
        $query['DeviceInfo.2.DevEui'] = 'd896e0ffffer****';
        $query['DeviceInfo.2.PinCode'] = 573091;

        $result = AlibabaCloud::rpc()
            ->product('Iot')
//            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('CreateLoRaNodesTask')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "生成批量注册LoRaWAN设备的任务:".PHP_EOL;
        print_r($result2Array);
        if (!$result->toArray()['Success'] == true) {
            echo '生成批量注册LoRaWAN设备的任务失败'.PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询批量注册LoRaWAN设备任务的状态
 * @param $taskId
 */
function getLoraNodesTaskTest($taskId)
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['TaskId'] = $taskId;

        $result = AlibabaCloud::rpc()
            ->product('Iot')
//            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('GetLoraNodesTask')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询批量注册LoRaWAN设备任务的状态:".PHP_EOL;
        print_r($result2Array);
        if (!$result->toArray()['Success'] == true) {
            echo '查询批量注册LoRaWAN设备任务的状态失败'.PHP_EOL;
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

if (!$device) {
    $device = registerDeviceTest($productKey);
    sleep(3);
    if (!$device) {
        return;
    }
}
echo $device['ProductKey'].PHP_EOL;
echo $device['DeviceName'].PHP_EOL;
echo $device['DeviceSecret'].PHP_EOL;
echo $device['IotId'].PHP_EOL;

queryDeviceStatisticsTest($productKey);

$deviceList = queryDeviceTest($productKey);

if (!$deviceList) {
    return;
}

for ($i=0;$i<count($deviceList);$i++) {
    $deviceNameList['DeviceName.' . ($i + 1)] = $deviceList[$i]['DeviceName'];
}

print_r($deviceNameList);

batchQueryDeviceDetailTest($productKey, $deviceNameList);

queryDeviceDetailTest($device);

getDeviceStatusTest($device);

batchGetDeviceStateTest($productKey, $deviceNameList);

disableThingTest($device);

enableThingTest($device);
//
//batchUpdateDeviceNicknameTest($deviceList);

//setDevicePropertyTest($device);

queryDevicePropertyStatusTest($device);

//setDeviceDesiredPropertyTest($device);

queryDeviceDesiredPropertyTest($device);

//setDevicesPropertyTest($productKey, $deviceNameList);

//invokeThingServiceTest($device);
//
//invokeThingsServiceTest($productKey, $deviceNameList);
//
queryDeviceEventDataTest($device);

queryDevicePropertyDataTest($device);

queryDeviceServiceDataTest($device);

queryDevicePropertiesDataTest($device);

saveDevicePropTest($device);

queryDevicePropTest($device);

deleteDevicePropTest($device);

queryDevicePropTest($device);

queryDeviceByTagsTest();

getThingTopoTest($device);

//notifyAddThingTopoTest($gwDevice, $deviceList);

getThingTopoTest($gwDevice);

//removeThingTopoTest($device);

//batchRegisterDeviceDemo($productKey);

//batchRegisterDeviceByApplyIdDemo($productKey);

queryDeviceFileListTest($device);

//queryDeviceFileTest($device);

//deleteDeviceFileTest($device);

//deleteDeviceTest($device);

exit();
