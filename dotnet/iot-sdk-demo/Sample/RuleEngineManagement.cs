using Aliyun.Acs.Core;
using Aliyun.Acs.Core.Exceptions;
using Aliyun.Acs.Core.Profile;
using Aliyun.Acs.Iot.Model.V20180120;
using Newtonsoft.Json;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RuleEngine
{
    class RuleEngineManagement
    {

        //创建一个简单的规则，不设置处理规则（不指定规则订阅的topic）
        public void TestCreateSimpleRule()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            CreateRuleRequest request = new CreateRuleRequest();
            request.Name = "Rule_JSON_0826";
            request.DataType = "JSON";
            request.RuleDesc = "This is a test.";

            CreateRuleResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine("Create Rule: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                return;
            }
            Console.WriteLine("Rule: " + response.RuleId);
        }


        //创建一个完整的规则，订阅的Topic是自定义类型（自己创建的）
        public void TestCreateRuleAboutUserTopic()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            CreateRuleRequest request = new CreateRuleRequest();
            //Topic名称不能同名
            request.Name = "Rule_User_Topic_0826";
            request.ProductKey = "<productKey>";

            request.ShortTopic = "firstDevice/user/update";
            //自定义Topic
            request.TopicType = 1;
            request.Select = "*";
            request._Where = " 1=1 ";
            request.DataType = "JSON";
            request.RuleDesc = "This is a test.";

            CreateRuleResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine("Create Rule: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                return;
            }
            Console.WriteLine("Rule: " + response.RuleId);
        }


        //创建一个完整的规则，订阅的Topic是系统类型（平台内置的）
        public void TestCreateRuleAboutSystemTopic()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            CreateRuleRequest request = new CreateRuleRequest();
            //Topic名称不能同名
            request.Name = "Rule_Property_Topic_0826";
            request.ProductKey = "<productKey>";
            //使用通配符，订阅全部设备的
            request.ShortTopic = "+/thing/event/property/post";
            //自定义Topic
            request.TopicType = 0;
            request.Select = "*";
            request._Where = " 1=1 ";
            request.DataType = "JSON";
            request.RuleDesc = "This is a test.";

            CreateRuleResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine("Create Rule: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                return;
            }
            Console.WriteLine("Rule: " + response.RuleId);
        }


        //获取规则的详细信息
        public void TestGetRule()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            long ruleId = 321103;

            GetRuleRequest request = new GetRuleRequest();
            request.RuleId = ruleId;

            GetRuleResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Get Rule: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                return;
            }

            GetRuleResponse.GetRule_RuleInfo RuleInfo = response.RuleInfo;
            Console.WriteLine("Rule ID: " + RuleInfo.Id);
            Console.WriteLine("Rule Name: " + RuleInfo.Name);
            Console.WriteLine("Rule DataType: " + RuleInfo.DataType);
            Console.WriteLine("Rule ProductKey: " + RuleInfo.ProductKey);
            Console.WriteLine("Rule ShortTopic: " + RuleInfo.ShortTopic);
            Console.WriteLine("Rule Topic: " + RuleInfo.Topic);
            Console.WriteLine("Rule Select: " + RuleInfo.Select);
            Console.WriteLine("Rule Where: " + RuleInfo._Where);
            Console.WriteLine("Rule UtcCreated: " + RuleInfo.UtcCreated);
        }


        public void TestListRule()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();

            ListRuleRequest request = new ListRuleRequest();
            request.CurrentPage = 1;
            request.PageSize = 10;

            ListRuleResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine(response.Success);
            Console.WriteLine(response.ErrorMessage); Console.WriteLine(response.Code);
            Console.WriteLine("CurrentPage" + response.Page);
            Console.WriteLine("PageSize: " + response.PageSize);
            Console.WriteLine("Total" + response.Total);

            List<ListRuleResponse.ListRule_RuleInfo> RuleInfos = response.Data;
            for (int i = 0; i < RuleInfos.Count; i += 1)
            {
                ListRuleResponse.ListRule_RuleInfo RuleInfo = RuleInfos[i];
                Console.WriteLine("Rule ID: " + RuleInfo.Id);
                Console.WriteLine("Rule Name: " + RuleInfo.Name);
                Console.WriteLine("Rule DataType: " + RuleInfo.DataType);
                Console.WriteLine("Rule ProductKey: " + RuleInfo.ProductKey);
                Console.WriteLine("Rule ShortTopic: " + RuleInfo.ShortTopic);
                Console.WriteLine("Rule Topic: " + RuleInfo.Topic);
                Console.WriteLine("Rule Select: " + RuleInfo.Select);
                Console.WriteLine("Rule Where: " + RuleInfo._Where);
                Console.WriteLine("Rule UtcCreated: " + RuleInfo.UtcCreated);
                Console.WriteLine();
            }
        }


        //更新规则的名称和描述
        public void TestSimpleUpdateRule()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            long ruleId = 321103;

            UpdateRuleRequest request = new UpdateRuleRequest();
            request.RuleId = ruleId;
            request.Name = "Rule_Test_0826";

            request.RuleDesc = "This is a test.";
            UpdateRuleResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine("Update Rule: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                return;
            }
        }


        //更新规则的名称和描述
        public void TestUpdateRule()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            long ruleId = 321103;

            UpdateRuleRequest request = new UpdateRuleRequest();
            request.RuleId = ruleId;
            request.Name = "Rule_Test_082601";
            request.ProductKey = "a163Bcy1oyR";
            request.ShortTopic = "firstDevice/thing/event/property/post";
            request.Select = " a, b, c";
            request._Where = " a > 2";

            request.RuleDesc = "This is a test.";
            UpdateRuleResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine("Update Rule: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                return;
            }
            GetRuleRequest request2 = new GetRuleRequest();
            request2.RuleId = ruleId;

            GetRuleResponse response2 = acsClient.GetAcsResponse(request2);
            Console.WriteLine("Get Rule: " + response.Success);
            if (!(bool)response2.Success)
            {
                Console.WriteLine(response2.Code + ", " + response2.ErrorMessage);
                return;
            }

            GetRuleResponse.GetRule_RuleInfo RuleInfo = response2.RuleInfo;
            Console.WriteLine("Rule ID: " + RuleInfo.Id);
            Console.WriteLine("Rule Name: " + RuleInfo.Name);
            Console.WriteLine("Rule DataType: " + RuleInfo.DataType);
            Console.WriteLine("Rule ProductKey: " + RuleInfo.ProductKey);
            Console.WriteLine("Rule ShortTopic: " + RuleInfo.ShortTopic);
            Console.WriteLine("Rule Topic: " + RuleInfo.Topic);
            Console.WriteLine("Rule Select: " + RuleInfo.Select);
            Console.WriteLine("Rule Where: " + RuleInfo._Where);
            Console.WriteLine("Rule UtcCreated: " + RuleInfo.UtcCreated);
        }


        //删除已经创建好的规则
        public void TestDeleteRule()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            long ruleId = 106844;

            DeleteRuleRequest request = new DeleteRuleRequest();
            request.RuleId = ruleId;

            DeleteRuleResponse response = acsClient.GetAcsResponse(request);
            Console.WriteLine("Delete Rule: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                return;
            }
        }


        //创建规则转发目的地，转发到其他Topic
        public void TestCreateRepublishRuleAction()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            long ruleId = 321103;

            var Configuration = new { topic = "/a163Bcy1oyR/secondDevice/user/update", topicType = 1 };
            String configJson = JsonConvert.SerializeObject(Configuration);

            CreateRuleActionRequest request = new CreateRuleActionRequest();
            request.RuleId = ruleId;
            request.Type = "REPUBLISH";
            request.Configuration = configJson;

            CreateRuleActionResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine("Create Rule Action: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                return;
            }
            Console.WriteLine("Rule ActionId: " + response.ActionId);

        }


        //创建转发目的地，流转到流式计算产品中，前提是要授权物联网平台访问自己的云产品Datahub
        //        {
        //	        "regionName": "cn-shanghai",
        //	        "projectName": "yanglv_test",
        //	        "topicName": "test",
        //	        "role": {
        //		        "roleName": "AliyunIOTAccessingOTSRole",
        //		        "roleArn": "acs:ram::1232303971449186:role/aliyuniotaccessingotsrole"
        //	        },
        //      	"schemaVals": [{
        //		    "type": "STRING",
        //		    "name": "notice",
        //		    "value": "${message}"
        //          }]
        //        }
        public void TestCreateDatahubRuleAction()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            long ruleId = 321103;

            var roleObj = new
            {
                roleName = "AliyunIOTAccessingDataHubRole",
                roleArn = "acs:ram::123230397144xxxx:role/aliyuniotaccessingdatahubrole"
            };

            var schemaValObj = new
            {
                type = "STRING",
                name = "notice",
                value = "${message}"
            };
            List<Object> schemaValList = new List<Object>();
            schemaValList.Add(schemaValObj);

            var Configuration = new
            {
                regionName = "cn-shanghai",
                projectName = "yanglv_test",
                topicName = "test",
                role = roleObj,
                schemaVals = schemaValList
            };
            String configJson = JsonConvert.SerializeObject(Configuration);

            Console.WriteLine(configJson);

            CreateRuleActionRequest request = new CreateRuleActionRequest();
            request.RuleId = ruleId;
            request.Type = "DATAHUB";
            request.Configuration = configJson;

            CreateRuleActionResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine("Create Rule Action: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
            Console.WriteLine("Rule ActionId: " + response.ActionId);
        }


        //创建转发目的地，流转消息数据到消息服务中，必须要授权物联网平台访问自己的消息服务云产品
        public void TestCreateMnsRuleAction()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            long ruleId = 321103;

            var roleObj = new
            {
                roleName = "AliyunIOTAccessingMNSRole",
                roleArn = "acs:ram::12323039714xxxxx:role/aliyuniotaccessingmnsrole"
            };

            var Configuration = new
            {
                regionName = "cn-shanghai",
                themeName = "yanglv_test_provider",
                role = roleObj
            };
            String configJson = JsonConvert.SerializeObject(Configuration);

            Console.WriteLine(configJson);

            CreateRuleActionRequest request = new CreateRuleActionRequest();
            request.RuleId = ruleId;
            request.Type = "MNS";
            request.Configuration = configJson;

            CreateRuleActionResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine("Create Rule Action: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
            Console.WriteLine("Rule ActionId: " + response.ActionId);
        }


        //创建转发目的地，消息数据转发到函数计算云产品FC中
        public void TestCreateFcRuleAction()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            long ruleId = 321103;

            var roleObj = new
            {
                roleName = "AliyunIOTAccessingFCRole",
                roleArn = "acs:ram::12323039714xxxxx:role/aliyuniotaccessingfcrole"
            };

            var Configuration = new
            {
                regionName = "cn-shanghai",
                serviceName = "xizitest",
                functionName = "junit",
                role = roleObj
            };
            String configJson = JsonConvert.SerializeObject(Configuration);

            Console.WriteLine(configJson);

            CreateRuleActionRequest request = new CreateRuleActionRequest();
            request.RuleId = ruleId;
            request.Type = "FC";
            request.Configuration = configJson;

            CreateRuleActionResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine("Create Rule Action: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
            Console.WriteLine("Rule ActionId: " + response.ActionId);
        }


        //{
        //   "primaryKeys": [{
        //      "columnType": "STRING",
        //      "columnValue": "${province}",
        //      "columnName": "province",
        //      "option": ""
        //    }],
        // "role": {
        //      "roleName": "AliyunIOTAccessingOTSRole",
        //      "roleArn": "acs:ram::1371643086916874:role/aliyuniotaccessingotsrole"
        // },
        // "instanceName": "zengxg-ots0620",
        // "regionName": "us-east-1",
        // "tableName": "china"
        //}创建转发目的地，流转消息数据到存储表格云产品OTS中
        public void TestCreateOtsRuleAction()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            long ruleId = 321103;

            var roleObj = new
            {
                roleName = "AliyunIOTAccessingOTSRole",
                roleArn = "acs:ram::13716430869xxxx:role/aliyuniotaccessingotsrole"
            };

            var primaryKeyObj = new
            {
                columnType = "STRING",
                columnName = "province",
                columnValue = "${province}",
                option = ""
            };
            List<Object> primaryKeyList = new List<Object>();
            primaryKeyList.Add(primaryKeyObj);

            var Configuration = new
            {
                regionName = "cn-shanghai",
                instanceName = "instance0826",
                tableName = "china",
                role = roleObj,
                primaryKeys = primaryKeyList
            };
            String configJson = JsonConvert.SerializeObject(Configuration);

            Console.WriteLine(configJson);

            CreateRuleActionRequest request = new CreateRuleActionRequest();
            request.RuleId = ruleId;
            request.Type = "OTS";
            request.Configuration = configJson;

            CreateRuleActionResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine("Create Rule Action: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
            Console.WriteLine("Rule ActionId: " + response.ActionId);
        }


        //创建转发目的地，转发消息数据到消息队列云产品ONS中
        public void TestCreateOnsRuleAction()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            long ruleId = 321103;

            var roleObj = new
            {
                roleName = "AliyunIOTAccessingMQRole",
                roleArn = "acs:ram::1232303971449186:role/aliyuniotaccessingmqrole"
            };

            var Configuration = new
            {
                regionName = "cn-shanghai",
                topicName = "yanglv_test",
                role = roleObj
            };
            String configJson = JsonConvert.SerializeObject(Configuration);

            Console.WriteLine(configJson);

            CreateRuleActionRequest request = new CreateRuleActionRequest();
            request.RuleId = ruleId;
            request.Type = "ONS";
            request.Configuration = configJson;

            CreateRuleActionResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine("Create Rule Action: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
            Console.WriteLine("Rule ActionId: " + response.ActionId);
        }


        //创建规则转发错误数据目的地，转发到其他Topic
        public void TestCreateErrorRuleAction()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            long ruleId = 321103;

            var Configuration = new
            {
                topic = "/a163Bcy1oyR/secondDevice/user/update",
                topicType = 1
            };
            String configJson = JsonConvert.SerializeObject(Configuration);

            CreateRuleActionRequest request = new CreateRuleActionRequest();
            request.RuleId = ruleId;
            request.Type = "REPUBLISH";
            request.Configuration = configJson;
            request.ErrorActionFlag = true;

            CreateRuleActionResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine("Create Rule Action: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                return;
            }
            Console.WriteLine("Rule ActionId: " + response.ActionId);
        }


        //更新转发目的地配置信息，具体参数格式同CreateRuleAction
        public void TestUpdateRuleAction()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            long ruleActionId = 309386;

            var Configuration = new { topic = "/a163Bcy1oyR/secondDevice/user/update", topicType = 1 };
            String configJson = JsonConvert.SerializeObject(Configuration);

            UpdateRuleActionRequest request = new UpdateRuleActionRequest();
            request.ActionId = ruleActionId;
            request.Type = "REPUBLISH";
            request.Configuration = configJson;

            UpdateRuleActionResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine("Update Rule Action: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
        }


        //获取某一规则的详细信息
        public void TestGetRuleAction()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            long ruleActionId = 309386;

            GetRuleActionRequest request = new GetRuleActionRequest();
            request.ActionId = ruleActionId;

            GetRuleActionResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine("Get Rule Action: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
            GetRuleActionResponse.GetRuleAction_RuleActionInfo ActionInfo = response.RuleActionInfo;
            Console.WriteLine("Rule Action ID: " + ActionInfo.Id);
            Console.WriteLine("Rule Action RuleId: " + ActionInfo.RuleId);
            Console.WriteLine("Rule Action Type: " + ActionInfo.Type);
            Console.WriteLine("Rule Action Configuration: " + ActionInfo.Configuration);
            Console.WriteLine("Rule Action ErrorActionFlag: " + ActionInfo.ErrorActionFlag);
        }


        //获取某一规则下的全部转发目的地，包括错误数据转发目的地
        public void TestListRuleActions()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            long ruleId = 321103;

            ListRuleActionsRequest request = new ListRuleActionsRequest();
            request.RuleId = ruleId;

            ListRuleActionsResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine("List Rule Action: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
            List<ListRuleActionsResponse.ListRuleActions_RuleActionInfo> ActionInfos = response.RuleActionList;
            for (int i = 0; i < ActionInfos.Count; i += 1)
            {
                ListRuleActionsResponse.ListRuleActions_RuleActionInfo ActionInfo = ActionInfos[i];
                Console.WriteLine("Rule Action ID: " + ActionInfo.Id);
                Console.WriteLine("Rule Action RuleId: " + ActionInfo.RuleId);
                Console.WriteLine("Rule Action Type: " + ActionInfo.Type);
                Console.WriteLine("Rule Action Configuration: " + ActionInfo.Configuration);
                Console.WriteLine("Rule Action ErrorActionFlag: " + ActionInfo.ErrorActionFlag);
                Console.WriteLine();
            }
        }


        //删除数据转发目的地
        public void TestDeleteRuleAction()
        {
            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            long ruleActionId = 309386;

            DeleteRuleActionRequest request = new DeleteRuleActionRequest();
            request.ActionId = ruleActionId;

            DeleteRuleActionResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine("Delete Rule Action: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
        }


        //启动规则之前，确保设置了正确的Topic过滤条件，以及转发目的地
        public void TestStartRule()
        {

            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            long ruleActionId = 321103;

            StartRuleRequest request = new StartRuleRequest();
            request.RuleId = ruleActionId;

            StartRuleResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine("Start Rule Action: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
        }


        //停止规则
        public void TestStopRule()
        {

            DefaultAcsClient acsClient = Demo.IotClient.GetClient();
            long ruleActionId = 321103;

            StopRuleRequest request = new StopRuleRequest();
            request.RuleId = ruleActionId;

            StopRuleResponse response = acsClient.GetAcsResponse(request);

            Console.WriteLine("Stop Rule Action: " + response.Success);
            if (!(bool)response.Success)
            {
                Console.WriteLine(response.Code + ", " + response.ErrorMessage);
                Console.ReadKey();
                return;
            }
        }


    }
}
