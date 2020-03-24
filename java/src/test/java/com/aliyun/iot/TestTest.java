package com.aliyun.iot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestTest {

    public static DefaultAcsClient client;
    private String  accessKeyId = "*****";
    private String  secret = "*******";
    private String  regionId = "cn-shanghai";


    @BeforeClass
    public void initTestEnv() {
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
        client = new DefaultAcsClient(profile); //初始化SDK客户端
    }

    //线上的
    private String domain = "iot.cn-shanghai.aliyuncs.com"; //host: http://popunify-share.cn-shanghai.aliyuncs.com
    private String version = "2018-01-20";
    private String action = "BatchAddThingTopo";

    private String productKey1, productKey2;
    private String device1, device2, detDeviceSecret;
    private String Timestamp;
    private String ClientId;

    /**参数事咧*/
    @BeforeClass
    public void init() {
        //网光设备的pk
        productKey1 = "a12j4xHNXxb***********";
        //子设备的pk
        productKey2 = "a14bFeMkfW7***********";
        /**设备需要上线*/
        //网关设备名称
        device1 = "david_gateway01***********";
        //子设备名称
        device2 = "abcd***********";
        //子设备秘匙
        detDeviceSecret = "vNWmZe77wxpkLKJCnzchOllJ9B7oRkCT***********";
        //子设备秘匙
        Timestamp = "***********";
        //子设备秘匙
        ClientId = "***********";
    }

    @Test
    public void test() throws Exception {

        CommonRequest request = new CommonRequest();
        request.setDomain(domain);
        request.setMethod(MethodType.POST);
        request.setVersion(version);
        request.setAction(action);

        request.putQueryParameter("GwProductKey", productKey1);
        request.putQueryParameter("GwDeviceName", device1);
        Map<String, Object> ext = new HashMap();
        ext.put("TOPO_TYPE", "0");
        request.putQueryParameter("Ext", JSON.toJSONString(ext));
        request.putQueryParameter("TopoAddItem.1.ProductKey",productKey2);
        request.putQueryParameter("TopoAddItem.1.DeviceName", device2);

        request.putQueryParameter("TopoAddItem.1.Sign", "");
        request.putQueryParameter("TopoAddItem.1.SignMethod", "hmacsha1");
        request.putQueryParameter("TopoAddItem.1.Timestamp", Timestamp);
        request.putQueryParameter("TopoAddItem.1.ClientId", ClientId);

        /**添加多个*/
        //request.putQueryParameter("TopoAddItem.2.ProductKey",productKey2);
        //request.putQueryParameter("TopoAddItem.2.DeviceName", device2);
        //String sign = getSignHMAC_SHA_1(productKey2, device2, detDeviceSecret);
        //request.putQueryParameter("TopoAddItem.2.Sign", sign);
        //request.putQueryParameter("TopoAddItem.2.SignMethod", "hmacsha1");
        //request.putQueryParameter("TopoAddItem.2.Timestamp", Timestamp);
        //request.putQueryParameter("TopoAddItem.2.ClientId", ClientId);

        CommonResponse response = client.getCommonResponse(request);
        System.out.println(JSON.toJSONString(response));

    }




}
