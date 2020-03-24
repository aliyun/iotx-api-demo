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

/**
 * 分页查询所有规则列表
 */
function listRuleTest()
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['PageSize'] = 10;
        $query['CurrentPage'] = 1;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('ListRule')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "分页查询所有规则列表:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '分页查询所有规则列表失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 对指定Topic新建一个规则
 */
function createRuleTest()
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['Name'] = 'rule_test1';
        $query['ProductKey'] = 'al*********';
        $query['ShortTopic'] = 'open_api_dev/get';
        $query['Select'] = '*';
        $query['RuleDesc'] = 'rule test';
        $query['DataType'] = 'JSON';
        $query['Where'] = 'a>10';
        $query['TopicType'] = 1;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('CreateRule')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "对指定Topic新建一个规则:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '对指定Topic新建一个规则失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 在指定的规则下创建一个规则动作
 */
function createRuleActionTest()
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['RuleId'] = 0;
        $query['Type'] = 'REPUBLISH';
        $query['Configuration'] = '{"topic":"/a1TXXXXXWSN/xxx_cache001/user/update","topicType":1}';
//        $query['ErrorActionFlag'] = '';
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('CreateRuleAction')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "对指定Topic新建一个规则:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '对指定Topic新建一个规则失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询指定规则的详细信息
 */
function getRuleTest()
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['RuleId'] = 0;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('GetRule')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询指定规则的详细信息:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '查询指定规则的详细信息失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询指定规则动作的详细信息
 */
function getRuleActionTest()
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['ActionId'] = 0;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('GetRuleAction')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询指定规则的详细信息:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '查询指定规则的详细信息失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询指定规则下的所有规则动作列表
 */
function ListRuleActionsTest()
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['RuleId'] = 0;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('ListRuleActions')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "查询指定规则下的所有规则动作列表:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '查询指定规则下的所有规则动作列表失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 删除指定的规则
 */
function deleteRuleTest()
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['RuleId'] = 0;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('DeleteRule')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "删除指定的规则:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '删除指定的规则失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 删除指定的规则动作
 */
function deleteRuleActionTest()
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['ActionId'] = 0;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('DeleteRuleAction')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "删除指定的规则动作:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '删除指定的规则动作失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 启动指定的规则
 */
function startRuleTest()
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['RuleId'] = 0;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('StartRule')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "启动指定的规则:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '启动指定的规则失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 停止指定的规则
 */
function stopRuleTest()
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['RuleId'] = 0;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('StopRule')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "停止指定的规则:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '停止指定的规则失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 修改指定的规则
 */
function updateRuleTest()
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['RuleId'] = 0;
        $query['Name'] = 'rule_test2';
        $query['ProductKey'] = 'al*********';
        $query['ShortTopic'] = 'open_api_dev/get';
        $query['Select'] = '*';
        $query['RuleDesc'] = 'rule test';
        $query['Where'] = 'a>9';
        $query['TopicType'] = 1;
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('UpdateRule')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "修改指定的规则:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '修改指定的规则失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 修改指定的规则动作
 */
function UpdateRuleActionTest()
{
    try {
        $query['RegionId'] = REGION_ID;
        $query['ActionId'] = 0;
        $query['Type'] = 'REPUBLISH';
        $query['Configuration'] = '{"topic":"/a1TXXXXXWSN/xxx_cache001/user/update","topicType":1}';
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('UpdateRuleAction')
            ->options([
                'query' => $query,
            ])
            ->request();
        $result2Array = $result->toArray();
        echo "修改指定的规则动作:".PHP_EOL;
        print_r($result2Array);
        if (!$result2Array['Success']) {
            echo '修改指定的规则动作失败'.PHP_EOL;
        }

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

