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
$topicId = null;

/**
 * 为指定产品创建产品Topic类
 * @param $productKey
 * @return int|null
 */
function createProductTopicTest($productKey)
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['ProductKey'] = $productKey;
        $query['TopicShortName'] = 'submit';
        $query['Operation'] = 'PUB';
        $query['Desc'] = 'submit a test topic';
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('CreateProductTopic')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "为指定产品创建产品Topic类:".PHP_EOL;
        print_r($result2Array);
        if ($result2Array['Success']) {
            echo '为指定产品创建产品Topic类'.PHP_EOL;
            return $result2Array['TopicId'];
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
    echo '为指定产品创建产品Topic类失败'.PHP_EOL;
    return null;
}

/**
 * 查询指定产品的Topic类
 * @param $productKey
 */
function queryProductTopicTest($productKey)
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['ProductKey'] = $productKey;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryProductTopic')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询指定产品的Topic类:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '查询指定产品的Topic类失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 更新指定的产品Topic类
 * @param $topicId
 */
function updateProductTopicTest($topicId)
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['TopicId'] = $topicId;
        $query['TopicShortName'] = 'resubmit';
        $query['Operation'] = 'PUB';
        $query['Desc'] = 'resubmit a test topic';
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('UpdateProductTopic')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "更新指定的产品Topic类:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '更新指定的产品Topic类失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 删除指定的Topic类
 * @param $topicId
 */
function deleteProductTopicTest($topicId)
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['TopicId'] = $topicId;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('DeleteProductTopic')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "删除指定的Topic类:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '删除指定的Topic类失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 新建Topic间的消息路由关系
 *
 * 限制说明
 * 一个源Topic最多可对应100个目标Topic
 * 源Topic所属的设备必须为已激活设备
 * 源Topic和目标Topic均不能是以 sys 开头的系统 Topic
 */
function createTopicRouteTableTest()
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['SrcTopic'] = '/x7aWKW9****/testDataToDataHub/update';
        $query['DstTopic.1'] = '/x7aWKW9****/deviceNameTest1/add';
        $query['DstTopic.2'] = '/x7aWKW9****/deviceNameTest2/delete';
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('CreateTopicRouteTable')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "新建Topic间的消息路由关系:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '新建Topic间的消息路由关系失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 删除指定的Topic路由关系
 */
function deleteTopicRouteTableTest()
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['SrcTopic'] = '/x7aWKW9****/testDataToDataHub/update';
        $query['DstTopic.1'] = '/x7aWKW9****/deviceNameTest1/add';
        $query['DstTopic.2'] = '/x7aWKW9****/deviceNameTest2/delete';
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('DeleteTopicRouteTable')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "新建Topic间的消息路由关系:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '新建Topic间的消息路由关系失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询指定Topic订阅的源Topic，即反向路由表
 */
function queryTopicReverseRouteTableTest()
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['Topic'] = '/x7aWKW9****/deviceNameTest1/add';
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryTopicReverseRouteTable')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询反向路由表:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '查询反向路由表失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询向指定Topic订阅消息的目标Topic，即指定Topic的路由表。
 * 该接口只支持查询用户的Topic。
 */
function queryTopicRouteTableTest()
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['Topic'] = '/x7aWKW9****/testDataToDataHub/update';
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryTopicRouteTable')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询指定Topic的路由表:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '查询指定Topic的路由表失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

if (!$topicId) {
    $topicId = createProductTopicTest($productKey);
}
queryProductTopicTest($productKey);

updateProductTopicTest($topicId);

queryProductTopicTest($productKey);

deleteProductTopicTest($topicId);

queryProductTopicTest($productKey);

$topicId = createProductTopicTest($productKey);

queryProductTopicTest($productKey);

deleteProductTopicTest($topicId);

queryProductTopicTest($productKey);