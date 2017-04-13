package com.jfyfox.test.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.jflyfox.util.DateUtils;
import com.jfyfox.test.api.IApi;
import com.jfyfox.test.util.ApiUtils;
import com.jfyfox.test.util.ClientParams;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

public class ConfigApi implements IApi {

	public JSONObject execute() {
		TreeMap<String, String> queryParas = new TreeMap<String, String>();
		queryParas.put("version", ClientParams.version);
		queryParas.put("apiNo", System.currentTimeMillis() + "");
		queryParas.put("method", "config");
		queryParas.put("time", DateUtils.getNow("yyyyMMddHHmmss"));
		queryParas.put("apiUser", ClientParams.apiUser);
		JSONObject json = new JSONObject();
		json.put("test", 2);
		json.put("name", "你好");
		String params = json.toJSONString();
		queryParas.put("p", params);
		String checkSum = ApiUtils.getSign(queryParas, ClientParams.key);
		try {
			if (ClientParams.ENCRYPT_FLAG) {
				params = ApiUtils.encode(params);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		queryParas.put("p", params);
		queryParas.put("checkSum", checkSum);

		return ApiUtils.visitApi(queryParas);
	}

}