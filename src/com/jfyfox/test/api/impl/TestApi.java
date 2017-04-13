package com.jfyfox.test.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.jfyfox.test.api.IApi;
import com.jfyfox.test.util.ClientParams;
import com.jfyfox.test.util.HttpKit;

import java.util.TreeMap;

/**
 * Created by Administrator on 2017/4/13.
 */
public class TestApi implements IApi {

    public JSONObject execute() {
        TreeMap<String, String> queryParas = new TreeMap<String, String>();
        queryParas.put("version", ClientParams.version);

        String resp = HttpKit.get(ClientParams.url_api, queryParas);
        JSONObject respJson = JSONObject.parseObject(resp);
        if (0 == respJson.getInteger("code")) {
            System.out.println("####test api url succuss.");
        } else {
            System.out.println("####test api url fail. ");
        }
        return respJson;

    }
}
