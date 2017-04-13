package com.jfyfox.test.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.jflyfox.util.DateUtils;
import com.jfyfox.test.api.IApi;
import com.jfyfox.test.util.ApiUtils;
import com.jfyfox.test.util.ClientParams;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

public class LoginApi implements IApi {

	public JSONObject execute() {
		TreeMap<String, String> queryParas = new TreeMap<String, String>();
		queryParas.put("version", ClientParams.version);
		queryParas.put("apiNo", System.currentTimeMillis() + "");
		queryParas.put("time", DateUtils.getNow("yyyyMMddHHmmss"));
		queryParas.put("apiUser", ClientParams.apiUser);
		queryParas.put("method", "login");
		queryParas.put("checkSum", "");
		JSONObject json = new JSONObject();
		json.put("username", ClientParams.apiUser);
		json.put("password", ClientParams.password);
		String params = json.toJSONString();
		try {
			if (ClientParams.ENCRYPT_FLAG) {
				params = ApiUtils.encode(params);
			}
			if (ClientParams.LOG) {
				System.out.println("####params:" + params);
				String param2 = ApiUtils.decode(params);
				System.out.println("####param2:" + param2);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		queryParas.put("p", params);

		return ApiUtils.visitApi(queryParas);
	}

}
