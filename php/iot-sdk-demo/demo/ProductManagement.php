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

/**
 * 创建产品
 */
function createProductTest()
{
    try {
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('CreateProduct')
            ->options([
                'query' => [
                    'RegionId' => REGION_ID,
                    'ProductName' => MyUtil\ServiceUtil::productNameGenerator(),
                    'NodeType' => 0,
                    'AliyunCommodityCode' => 'iothub_senior', //若不传入此参数默，则默认为基础版。
                    'DataFormat' => 1, //此参数为高级版产品的特有参数，并且是创建高级版产品的必需参数。
                    'Id2' => false,
//                    'ProtocolType' => 'modbus',
                    'NetType' => "WIFI",
                    'Description' => "PHP demo test create product",
                    'AuthType' => 'secret', //若不传入此参数，默认值为secret
                ],
            ])
            ->request();
        print_r($result->toArray());
        if ($result->toArray()['Success'] == true) {
            return $result->toArray()['Data']['ProductKey'];
        }
        print_r('RequestId:'.$result->toArray()['RequestId']);
        echo PHP_EOL;
        print_r('Code:'.$result->toArray()['Code']);
        echo PHP_EOL;
        print_r('ErrorMessage:'.$result->toArray()['ErrorMessage']);
        echo PHP_EOL;
        echo '创建产品标签失败';
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
    return "";
}


/**
 * 查询产品列表
 */
function queryProductListTest()
{
    try {
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//        ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryProductList')
            ->options([
                'query' => [
                    'RegionId' => REGION_ID,
                    'CurrentPage' => 1,
                    'PageSize' => 10,
                    'AliyunCommodityCode' => "iothub_senior",
                ],
            ])
            ->request();
        print_r($result->toArray());

    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询产品详情
 * @param $productKey
 */
function queryProductTest($productKey)
{
    try {
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('QueryProduct')
            ->options([
                'query' => [
                    'RegionId' => REGION_ID,
                    'ProductKey' => $productKey,
                ],
            ])
            ->request();
        print_r($result->toArray());
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 更新产品名称或描述信息
 * @param $productKey
 */
function updateProductTest($productKey)
{
    try {
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('UpdateProduct')
            ->options([
                'query' => [
                    'RegionId' => REGION_ID,
                    'ProductKey' => $productKey,
                    'ProductName' => MyUtil\ServiceUtil::productNameGenerator(),
                    'Description' => "PHP demo test update product"
                ],
            ])
            ->request();
        print_r($result->toArray());
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 创建产品标签
 * @param $productKey
 */
function createProductTagsTest($productKey)
{
    try {
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('CreateProductTags')
            ->options([
                'query' => [
                    'RegionId' => REGION_ID,
                    'ProductKey' => $productKey,
                    'ProductTag.1.TagKey' => "color",
                    'ProductTag.1.TagValue' => "red",
                    'ProductTag.2.TagKey' => "length",
                    'ProductTag.2.TagValue' => 11
                ],
            ])
            ->request();
        print_r($result->toArray());
        echo PHP_EOL;
        if (!$result->toArray()['Success'] == true) {
            print_r('RequestId:'.$result->toArray()['RequestId']);
            echo PHP_EOL;
            print_r('Code:'.$result->toArray()['Code']);
            echo PHP_EOL;
            print_r('ErrorMessage:'.$result->toArray()['ErrorMessage']);
            echo PHP_EOL;
            echo '创建产品标签失败';
            return;
        }
        echo "创建标签成功";
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 查询产品标签列表
 * @param $productKey
 */
function listProductTagsTest($productKey)
{
    try {
        $result = AlibabaCloud::rpc()
            ->product('Iot')
            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('ListProductTags')
            ->options([
                'query' => [
                    'RegionId' => REGION_ID,
                    'ProductKey' => $productKey,
                ],
            ])
            ->request();
        print_r($result->toArray());
        if ($result->toArray()['Success'] == true) {
            $tags = $result->toArray()['Data']['ProductTag'];
            foreach ($tags as $tag) {
                $tagKey = $tag["TagKey"];
                $tagValue = $tag["TagValue"];
                print_r("tagKey:{$tagKey}");
                print_r("tagValue:{$tagValue}");
            }
        }
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 更新产品标签
 * @param $productKey
 */
function updateProductTagsTest($productKey)
{
    try {
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('UpdateProductTags')
            ->options([
                'query' => [
                    'RegionId' => REGION_ID,
                    'ProductKey' => $productKey,
                    'ProductTag.1.TagKey' => "color",
                    'ProductTag.1.TagValue' => "green",
                    'ProductTag.2.TagKey' => "length",
                    'ProductTag.2.TagValue' => 12
                ],
            ])
            ->request();
        print_r($result->toArray());
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 根据标签查询产品列表
 */
function listProductByTags()
{
    try {
        $result = AlibabaCloud::rpc()
            ->product('Iot')
            ->scheme('https') // https | http
            ->version('2018-01-20')
            ->action('ListProductByTags')
            ->method('POST')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->options([
                'query' => [
                    'RegionId' => REGION_ID,
                    'Page​Size' => 10, //默认为10， 最大值是50
                    'CurrentPage' => 1,
                    'ProductTag.1.TagKey' => "color", //支持按照TagKey和TagValue组合来搜索。
                    'ProductTag.1.TagValue' => "green",
                    'ProductTag.2.TagKey' => "length", //支持只按照TagKey来搜索。
                    'ProductTag.2.TagValue' => 12 //传入多个ProductTag是或的关系。
                ],
            ])
            ->request();
        print_r($result->toArray());
        print_r($result->toArray()['Success']);
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 删除产品标签
 * @param $productKey
 */
function deleteProductTagsTest($productKey)
{
    try {
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('DeleteProductTags')
            ->options([
                'query' => [
                    'RegionId' => REGION_ID,
                    'ProductKey' => $productKey,
                    'ProductTag.1.TagKey' => "color",
                    'ProductTag.2.TagKey' => "length",
                ],
            ])
            ->request();
        print_r($result->toArray());
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

/**
 * 删除产品
 * 必须保证产品下已经不存在设备，否则删除产品失败
 * @param $productKey
 */
function deleteProductTest($productKey)
{
    try {
        $result = AlibabaCloud::rpc()
            ->product('Iot')
//            ->scheme('https') // https | http
            ->method('POST')
            ->version('2018-01-20')
            ->host('iot.cn-shanghai.aliyuncs.com')
            ->action('DeleteProduct')
            ->options([
                'query' => [
                    'RegionId' => REGION_ID,
                    'ProductKey' => $productKey,
                ],
            ])
            ->request();
        print_r($result->toArray());
    } catch (ClientException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    } catch (ServerException $e) {
        echo $e->getErrorMessage() . PHP_EOL;
    }
}

if (empty($productKey)) {
    $productKey = createProductTest();
    if (!$productKey) {
        return;
    }
}

queryProductListTest();

queryProductTest($productKey);

updateProductTest($productKey);

queryProductTest($productKey);

createProductTagsTest($productKey);

listProductTagsTest($productKey);

updateProductTagsTest($productKey);

listProductByTags();

//deleteProductTagsTest($productKey);

//deleteProductTest($productKey);

