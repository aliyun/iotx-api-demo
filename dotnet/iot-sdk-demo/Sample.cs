using Aliyun.Acs.Core;
using Aliyun.Acs.Core.Exceptions;
using Aliyun.Acs.Core.Profile;
using Aliyun.Acs.Iot.Model.V20180120;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace demo
{

    class Sample
    {
        //主入口程序
        static void Main(string[] args)
        {
            Console.WriteLine("Iot SDK for .NET Samples!");

            try
            {
                //Product();
                //Device();
                //RuleEngine();
                //TopicManagement();
                DeviceGroupManagement();
            }
            catch (ServerException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }
            catch (ClientException e)
            {
                Console.WriteLine(e.ErrorCode);
                Console.WriteLine(e.ErrorMessage);
            }

            Console.ReadKey(true);
        }


        //产品管理的API调用测试
        private static void Product()
        {

            Product.ProductManagement productManagement = new Product.ProductManagement();

            //productManagement.TestCreateProduct();

            //productManagement.TestUpdateProduct();

            //productManagement.TestQueryProduct();

            productManagement.TestQueryProductList();

            //productManagement.TestDeleteProduct();

            //productManagement.TestCreateProductTags();

            //productManagement.TestUpdateProductTags();

            //productManagement.TestListProductTags();

            //productManagement.TestListProductByTags();

            //productManagement.TestDeleteProductTags();

        }


        //设备管理相关的，主要是设备注册
        private static void Device()
        {

            Device.DeviceManagement deviceManagement = new Device.DeviceManagement();

            //deviceManagement.TestRegisterDevice();

            //deviceManagement.TestQueryDeviceByName();

            //deviceManagement.TestQueryDeviceByIotId();

            //deviceManagement.TestBatchQueryDeviceDetail();

            //deviceManagement.TestApplyDeviceWithNames();

            //deviceManagement.TestQueryApplyStatus();

            //deviceManagement.TestQueryDevice();

            //deviceManagement.TestGetDeviceStatus();

            //deviceManagement.TestBatchGetDeviceState();

            //deviceManagement.TestDisableThing();

            //deviceManagement.TestEnableThing();

            //deviceManagement.TestBatchRegisterDevice();

            //deviceManagement.TestBatchRegisterDeviceWithNames();

            //deviceManagement.TestBatchUpdateDeviceNickname();

            //deviceManagement.TestSaveDeviceProp();

            //deviceManagement.TestQueryDeviceByTags();

            //deviceManagement.TestDeleteDeviceProp();

            //deviceManagement.TestNotifyAddThingTopo();

            //deviceManagement.TestGetThingTopo();

            //deviceManagement.TestRemoveThingTopo();

            deviceManagement.TestQueryDeviceStatistics();

            //deviceManagement.TestGetGatewayBySubDevice();

            //deviceManagement.TestQueryDeviceFileList();

            //deviceManagement.TestQueryDeviceFile();

            //deviceManagement.TestDeleteDeviceFile();
        }


        //规则引擎相关的
        private static void RuleEngine()
        {

            RuleEngine.RuleEngineManagement ruleEngineManagement = new RuleEngine.RuleEngineManagement();


            //ruleEngineManagement.TestCreateSimpleRule();

            //ruleEngineManagement.TestCreateRuleAboutUserTopic();

            //ruleEngineManagement.TestCreateRuleAboutSystemTopic();

            //ruleEngineManagement.TestGetRule();

            ruleEngineManagement.TestListRule();

            //ruleEngineManagement.TestSimpleUpdateRule();

            //ruleEngineManagement.TestUpdateRule();

            //ruleEngineManagement.TestDeleteRule();

            //ruleEngineManagement.TestCreateRepublishRuleAction();

            //ruleEngineManagement.TestCreateOtsRuleAction();

            //ruleEngineManagement.TestCreateDatahubRuleAction();

            //ruleEngineManagement.TestCreateMnsRuleAction();

            //ruleEngineManagement.TestCreateFcRuleAction();

            //ruleEngineManagement.TestCreateOnsRuleAction();

            //ruleEngineManagement.TestCreateErrorRuleAction();

            //ruleEngineManagement.TestUpdateRuleAction();

            //ruleEngineManagement.TestGetRuleAction();

            //ruleEngineManagement.TestListRuleActions();

            //ruleEngineManagement.TestDeleteRuleAction();

            //ruleEngineManagement.TestStartRule();

            //ruleEngineManagement.TestStopRule();
        }


        //Topic类管理相关的
        private static void TopicManagement()
        {
            TopicManagement.TopicManagement topicManagement = new TopicManagement.TopicManagement();

            topicManagement.TestQueryProductTopic();

            //topicManagement.TestCreateProductTopic();

            //topicManagement.TestUpdateProductTopic();

            //topicManagement.TestDeleteProductTopic();

            //topicManagement.TestCreateTopicRouteTable();

            //topicManagement.TestQueryTopicRouteTable();

            //topicManagement.TestQueryTopicReverseRouteTable();

            //topicManagement.TestDeleteTopicRouteTable();

            //topicManagement.TestPub();

            //topicManagement.TestPubBroadcast();

            //topicManagement.TestRrpc();

            //topicManagement.TestUpdateDeviceShadow();

            //topicManagement.TestGetDeviceShadow();
        }


        //分组管理相关的
        private static void DeviceGroupManagement()
        {

            DeviceGroupManagement.DeviceGroupManagement deviceGroupManagement = new DeviceGroupManagement.DeviceGroupManagement();

            //deviceGroupManagement.TestCreateLevel1DeviceGroup();

            //deviceGroupManagement.TestCreateLevel2DeviceGroup();

            //deviceGroupManagement.TestUpdateDeviceGroup();

            //deviceGroupManagement.TestQueryDeviceGroupInfo();

            deviceGroupManagement.TestQueryDeviceGroupList();

            //deviceGroupManagement.TestDeleteDeviceGroup();

            //deviceGroupManagement.TestBatchAddDeviceGroupRelations();

            //deviceGroupManagement.TestBatchDeleteDeviceGroupRelations();

            //deviceGroupManagement.TestSetDeviceGroupTags();

            //deviceGroupManagement.TestQueryDeviceGroupTagList();

            //deviceGroupManagement.TestQueryDeviceGroupByDevice();

            //deviceGroupManagement.TestQuerySuperDeviceGroup();

            //deviceGroupManagement.TestQueryDeviceListByDeviceGroup();

            //deviceGroupManagement.TestQueryDeviceGroupByTags1();

            //deviceGroupManagement.TestQueryDeviceGroupByTags2();

        }
    }
}
