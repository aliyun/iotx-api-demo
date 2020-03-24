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

$groupList = array();
$group = array('GroupName' => 'group1', 'GroupId' => '8Hvhy*************0200',);

/**
 * 分页查询分组列表
 * @return array|null
 */
function queryDeviceGroupListTest()
{
    try {
        $query['RegionId'] = REGION_ID;
//        支持模糊查询，若不传进行全量查询都
//        $query['GroupName'] = "group1";
//        父组ID。查询某父组下的子分组列表时，需传入此参数。
//        $query['SuperGroupId'] = '';
        $query['PageSize'] = 10;
        $query['CurrentPage'] = 1;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryDeviceGroupList')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "分页查询分组列表:" . PHP_EOL;
        print_r($result2Array);
        if ($result2Array['Success'] ) {
            if ($result2Array['Total']) {
                return $result2Array['Data']['GroupInfo'];
            }
            echo "分组列表为空";
            return null;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
    echo "查询分组列表失败";
    return null;
}

/**
 * 新建分组
 */
function createDeviceGroupTest()
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['GroupName'] = "group1";
//        若要创建的是一级分组，则不传入此参数。
//        $query['SuperGroupId'] = '';
        $query['GroupDesc'] = '设备分组';
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('CreateDeviceGroup')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "新建分组:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo "新建分组失败" . PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 修改分组信息
 * @param $group
 */
function updateDeviceGroupTest($group)
{
    try {
        $query['RegionId'] = REGION_ID;
//        若要创建的是一级分组，则不传入此参数。
        $query['GroupId'] = $group['GroupId'];
        $query['GroupDesc'] = '设备分组修改';
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('UpdateDeviceGroup')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "修改分组信息:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo "修改分组信息失败" . PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询分组详情
 * @param $group
 */
function queryDeviceGroupInfoTest($group)
{
    try {
        $query['RegionId'] = REGION_ID;
//        若要创建的是一级分组，则不传入此参数。
        $query['GroupId'] = $group['GroupId'];
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryDeviceGroupInfo')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询分组详情:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo "查询分组详情失败" . PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询分组标签列表
 * @param $group
 */
function queryDeviceGroupTagListTest($group)
{
    try {
        $query['RegionId'] = REGION_ID;
//        若要创建的是一级分组，则不传入此参数。
        $query['GroupId'] = $group['GroupId'];
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryDeviceGroupTagList')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询分组标签列表:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo "查询分组标签列表失败" . PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 添加、更新、或删除分组标签
 * @param $group
 */
function setDeviceGroupTagsTest($group)
{
    try {
        $tagArray = [];

        $tag = [];
        $tag['tagKey'] = 'gateway';
        $tag['tagValue'] = 'wifi';
        array_push($tagArray, $tag);

        $query['RegionId'] = REGION_ID;
//        若要创建的是一级分组，则不传入此参数。
        $query['GroupId'] = $group['GroupId'];
        $query['TagString'] = json_encode($tagArray);
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('SetDeviceGroupTags')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "添加、更新、或删除分组标签:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo "添加、更新、或删除分组标签失败" . PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询分组中的设备列表
 * @param $group
 * @return array|null
 */
function queryDeviceListByDeviceGroupTest($group)
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['GroupId'] = $group['GroupId'];
        $query['PageSize'] = 10;
        $query['CurrentPage'] = 1;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryDeviceListByDeviceGroup')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询分组中的设备列表:" . PHP_EOL;
        print_r($result2Array);
        if ($result2Array['Success'] ) {
            if ($result2Array['Total']) {
                return $result2Array['Data']['SimpleDeviceInfo'];
            }
            echo "分组中的设备列表为空";
            return null;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
    echo "查询分组中的设备列表失败";
    return null;
}

/**
 * 根据子分组ID查询父分组信息
 * @param $group
 */
function querySuperDeviceGroupTest($group)
{
    try {
        $query['RegionId'] = REGION_ID;
//        若要创建的是一级分组，则不传入此参数。
        $query['GroupId'] = $group['GroupId'];
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QuerySuperDeviceGroup')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "根据子分组ID查询父分组信息:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo "根据子分组ID查询父分组信息失败" . PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 根据标签查询设备分组
 */
function queryDeviceGroupByTagsTest()
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['PageSize'] = 10;
        $query['CurrentPage'] = 1;
        $query['Tag.1.TagKey'] = 'gateway';
//        也支持只输入TagKey进行查询。
//        $query['Tag.1.TagValue'] = 'wifi';
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryDeviceGroupByTags')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "根据标签查询设备分组:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo "根据标签查询设备分组失败" . PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

//createDeviceGroupTest();

$groupList = queryDeviceGroupListTest();

updateDeviceGroupTest($group);

queryDeviceGroupInfoTest($group);

setDeviceGroupTagsTest($group);

queryDeviceGroupTagListTest($group);

queryDeviceListByDeviceGroupTest($group);

querySuperDeviceGroupTest($group);

queryDeviceGroupByTagsTest();




