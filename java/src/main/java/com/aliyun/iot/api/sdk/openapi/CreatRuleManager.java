package com.aliyun.iot.api.sdk.openapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.iot.util.LogUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20180120.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zengxg
 * @date: 2019/3/11 16:22
 * @description:
 */
public class CreatRuleManager extends AbstractManager {

    private final static Logger LOGGER = LoggerFactory.getLogger(CreatRuleManager.class);

    private static DefaultAcsClient client = AbstractManager.getClient();

    /**
     * 创建简单规则，只包含规则名称，规则类型，和规则描述
     */
    public void createSimpleRule() {
        CreateRuleRequest createRuleRequest = new CreateRuleRequest();
        createRuleRequest.setName("Linux_Mint_19");
        createRuleRequest.setDataType("BINARY");
        createRuleRequest.setRuleDesc("Linux_Mint_19");
        CreateRuleResponse response = (CreateRuleResponse)executeTest(createRuleRequest);
        if (response != null) {
            showResult(response);
            System.out.println("ruleId: " + response.getRuleId());
        }
    }

    /**
     * 创建规则，并配置规则的详情（设置sql，接受自定义Topic的消息）
     */
    public void createRuleWithTopic() {
        CreateRuleRequest createRuleRequest = new CreateRuleRequest();
        String productKey = "a1chu6VZiBC";
        createRuleRequest.setName("sunshine_0304pm");
        createRuleRequest.setProductKey(productKey);
        createRuleRequest.setDataType("BINARY");
        createRuleRequest.setSelect("*");
        createRuleRequest.setTopicType(1);
        createRuleRequest.setShortTopic("Edge001/user/get");
        createRuleRequest.setRuleDesc("Today is sunshine.");
        createRuleRequest.setWhere("2 > 1");
        CreateRuleResponse response = (CreateRuleResponse)executeTest(createRuleRequest);
        if (response != null) {
            showResult(response);
            System.out.println("ruleId: " + response.getRuleId());
        }
    }

    /**
     * 创建规则，并配置规则的详情（设置sql, 接受设备状态变化Topic的消息）
     */
    public void createRuleAboutDeviceStatusChange() {
        String productKey = "a1JURgCWZks";
        String deviceName = "array_094";
        CreateRuleRequest createRuleRequest = new CreateRuleRequest();
        createRuleRequest.setName("happy_0303");
        createRuleRequest.setDataType("BINARY");
        createRuleRequest.setProductKey(productKey);
        createRuleRequest.setSelect("*");
        createRuleRequest.setTopicType(2);
        createRuleRequest.setShortTopic(deviceName);
        createRuleRequest.setRuleDesc("/as/mqtt/status/${productKey}/${deviceName}");
        CreateRuleResponse response = (CreateRuleResponse)executeTest(createRuleRequest);
        if (response != null) {
            showResult(response);
            System.out.println("ruleId: " + response.getRuleId());
        }
    }

    /**
     * 如果创建时，没有指定规则的主题，则可以通过更新来再次指定规则的主题
     */
    public void updateRule() {
        Long ruleId = 156180L;
        String productKey = "a1EcdGCYfHo";
        UpdateRuleRequest updateRuleRequest = new UpdateRuleRequest();
        updateRuleRequest.setRuleId(ruleId);
        updateRuleRequest.setProductKey(productKey);
        updateRuleRequest.setSelect("*");
        updateRuleRequest.setShortTopic("gw_device0918/thing/event/BeanFactory/post");
        updateRuleRequest.setTopicType(0);

        UpdateRuleResponse response = (UpdateRuleResponse)executeTest(updateRuleRequest);
        if (response != null) {
            showResult(response);
            System.out.println("Error: " + response.getErrorMessage());
        }
    }

    /**
     * 获取规则的详细信息
     */
    public void getRuleDetail() {
        Long ruleId = 0L;
        GetRuleRequest getRequest = new GetRuleRequest();
        getRequest.setRuleId(ruleId);

        GetRuleResponse getResponse = (GetRuleResponse)executeTest(getRequest);
        if (getResponse != null) {
            showResult(getResponse);
            GetRuleResponse.RuleInfo rule = getResponse.getRuleInfo();
            System.out.println(ReflectionToStringBuilder.toString(rule, ToStringStyle.MULTI_LINE_STYLE));
        }
    }

    /**
     * 分页获取规则列表
     */
    public void listRule() {
        ListRuleRequest request = new ListRuleRequest();
        request.setCurrentPage(1);
        request.setPageSize(10);
        ListRuleResponse response = (ListRuleResponse)executeTest(request);
        if (response != null) {
            showResult(response);
            List<ListRuleResponse.RuleInfo> data = response.getData();
            data.stream().forEach((r) -> {
                System.out.println(ReflectionToStringBuilder.toString(r, ToStringStyle.MULTI_LINE_STYLE));
            });
        }
    }

    /**
     * 删除某一规则，前提是规则下面没有转发目的地
     */
    public void deleteRule() {
        Long ruleId = 0L;
        DeleteRuleRequest deleteRuleRequest = new DeleteRuleRequest();
        deleteRuleRequest.setRuleId(ruleId);

        DeleteRuleResponse deleteRuleResponse = (DeleteRuleResponse)executeTest(deleteRuleRequest);
        if (deleteRuleResponse != null) {
            showResult(deleteRuleResponse);
        }
    }

    /**
     * 创建规则的转发目的地，将消息转发到其他Topic上
     */
    public void createRepublishAction() {
        Long ruleId = 156180L;
        CreateRuleActionRequest request = new CreateRuleActionRequest();
        request.setRuleId(ruleId);
        request.setType("REPUBLISH");
        request.setErrorActionFlag(false);
        Map<String, Object> configuationMap = new HashMap<>(4);
        configuationMap.put("topicType", 0);
        configuationMap.put("topic", "/sys/a1chu6VZiBC/Edge001/thing/service/property/set");
        JSONObject configJsonObj = new JSONObject(configuationMap);
        request.setConfiguration(configJsonObj.toString());

        CreateRuleActionResponse response = (CreateRuleActionResponse)executeTest(request);
        if (response != null) {
            showResult(response);
            System.out.println("actionId: " + response.getActionId());
        }

    }

    /**
     * 创建规则的转发目的地，将消息转发到表格存储Ots中
     */
    public void createOtsAction() {
        String currentRegionId = "cn-shanghai";
        String targetRegionId = currentRegionId;
        JSONObject configuration = new JSONObject();
        configuration.put("instanceName", "other-table");
        configuration.put("tableName", "rule_message");

        Map<String, Object> key1 = new HashMap<String, Object>(4);
        key1.put("columnType", "STRING");
        key1.put("columnName", "firstKey");
        key1.put("columnValue", "${message}");
        key1.put("option", "");
        JSONObject keyJson = new JSONObject(key1);
        JSONArray primaryKeys = new JSONArray();
        primaryKeys.add(keyJson);
        configuration.put("primaryKeys", primaryKeys);
        configuration.put("regionName", targetRegionId);
        Map<String, Object> role = new HashMap<String, Object>(2);
        role.put("roleName", "AliyunIOTAccessingOTSRole");
        role.put("roleArn", "acs:ram::<userId>:role/aliyuniotaccessingotsrole");
        JSONObject roleJson = new JSONObject(role);
        configuration.put("role", roleJson);
        CreateRuleActionRequest otsRequest = new CreateRuleActionRequest();
        otsRequest.setRuleId(67943L);
        otsRequest.setType("OTS");
        otsRequest.setConfiguration(configuration.toString());

        CreateRuleActionResponse response = (CreateRuleActionResponse)executeTest(otsRequest);
        if (response != null) {
            showResult(response);
            System.out.println("ActionId: " + response.getActionId());
        }
    }

    /**
     * 创建规则的转发目的地，将消息转发到消息队列ONS云产品中
     */
    public void createMqAction() {
        JSONObject configuration = new JSONObject();
        configuration.put("instanceId", "MQ_INST_<userId>_BaLTEqxA");
        configuration.put("topic", "test_shangxing_data");

        configuration.put("tag", "<tag>");
        configuration.put("regionName", "cn-shanghai");
        Map<String, Object> role = new HashMap<String, Object>(2);
        role.put("roleName", "AliyunIOTAccessingMQRole");
        role.put("roleArn", "acs:ram::<userId>:role/aliyuniotaccessingmqrole");
        JSONObject roleJson = new JSONObject(role);
        configuration.put("role", roleJson);

        Long ruleId = 152476L;
        CreateRuleActionRequest onsRequest = new CreateRuleActionRequest();
        onsRequest.setRuleId(ruleId);
        onsRequest.setType("ONS");
        onsRequest.setConfiguration(configuration.toString());
        onsRequest.setErrorActionFlag(false);

        CreateRuleActionResponse response = (CreateRuleActionResponse)executeTest(onsRequest);
        if (response != null) {
            showResult(response);
            System.out.println("ActionId: " + response.getActionId());
        }
    }

    /**
     * 更新规则的转发目的地信息
     */
    public void updateRdsAction() {
        String currentRegionId = "cn-shanghai";
        String targetRegionId = currentRegionId;
        JSONObject configuration = new JSONObject();
        configuration.put("instanceName", "rm-uf6n21nc43cpbkza2");
        configuration.put("databaseName", "iot-test");
        configuration.put("tableName", "erp_mail");
        configuration.put("accountName", "<userName>");
        configuration.put("accountPwd", "<password>");

        Map<String, Object> key1 = new HashMap<String, Object>(4);
        key1.put("name", "content");
        key1.put("value", "${content}");
        JSONObject key1Json = new JSONObject(key1);
        Map<String, Object> key2 = new HashMap<String, Object>(4);
        key2.put("name", "remark");
        key2.put("value", "${remark}");
        JSONObject key2Json = new JSONObject(key2);
        JSONArray fieldVals = new JSONArray();
        fieldVals.add(key1Json);
        fieldVals.add(key2Json);
        configuration.put("fieldVals", fieldVals);
        configuration.put("regionName", targetRegionId);
        Map<String, Object> role = new HashMap<String, Object>(2);
        role.put("roleName", "AliyunIOTAccessingRDSRole");
        role.put("roleArn", "acs:ram::<userId>:role/aliyuniotaccessingrdsrole");
        JSONObject roleJson = new JSONObject(role);
        configuration.put("role", roleJson);
        UpdateRuleActionRequest rdsRequest = new UpdateRuleActionRequest();
        rdsRequest.setActionId(70896L);
        rdsRequest.setType("RDS");
        rdsRequest.setConfiguration(configuration.toString());

        UpdateRuleActionResponse response = (UpdateRuleActionResponse)executeTest(rdsRequest);
        if (response != null) {
            showResult(response);
        }
    }

    /**
     * 获取某一规则下的数据转发目的地，每个规则最多设置10个转发目的地
     */
    public void listRuleAction() {
        Long RuleId = 156180L;
        ListRuleActionsRequest request = new ListRuleActionsRequest();
        request.setRuleId(RuleId);

        ListRuleActionsResponse response = (ListRuleActionsResponse)executeTest(request);
        if (response != null) {
            showResult(response);
            List<ListRuleActionsResponse.RuleActionInfo> ruleActionList = response.getRuleActionList();
            System.out.println("success: " + response.getSuccess());
            ruleActionList.stream().forEach((actionInfo) -> {
                System.out.println(actionInfo);
            });
        }

    }

    /**
     * 删除规则下的某一转发目的地
     */
    public void deleteRuleAction() {
        Long ruleActionId = 80637L;
        DeleteRuleActionRequest request = new DeleteRuleActionRequest();
        request.setActionId(ruleActionId);

        DeleteRuleActionResponse response = (DeleteRuleActionResponse)executeTest(request);
        if (response != null) {
            showResult(response);
        }
    }

    /**
     * 在正确设置了规则sql和规则转发的目的地后，只有启动规则成功，数据才能正常流转到目的地中
     */
    public void startRule() {
        Long ruleId = 80637L;
        StartRuleRequest request = new StartRuleRequest();
        request.setRuleId(ruleId);

        StartRuleResponse response = (StartRuleResponse)executeTest(request);
        if (response != null) {
            showResult(response);
        }
    }

    /**
     * 停止规则后，数据停止流转
     */
    public void stopRule() {
        Long ruleId = 80637L;
        StopRuleRequest request = new StopRuleRequest();
        request.setRuleId(ruleId);

        StopRuleResponse response = (StopRuleResponse)executeTest(request);
        if (response != null) {
            showResult(response);
        }
    }

    /**
     * 查询规则动作信息。
     *
     * @param ActionId 要查询的规则动作ID。 必需
     */
    public static void GetRuleAction(Long ActionId) {

        GetRuleActionResponse response = null;

        GetRuleActionRequest request = new GetRuleActionRequest();
        request.setActionId(ActionId);

        try {
            response = client.getAcsResponse(request);

            if (response.getSuccess() != null && response.getSuccess()) {
                LogUtil.print("查询规则动作信息成功");
                LogUtil.print(JSON.toJSONString(response));
            } else {
                LogUtil.print("查询规则动作信息失败");
                LogUtil.error(JSON.toJSONString(response));
            }

        } catch (ClientException e) {
            e.printStackTrace();
            LogUtil.error("查询规则动作信息失败！" + JSON.toJSONString(response.getErrorMessage()));
        }

    }

}
