package com.jfyfox.test.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.jflyfox.util.DateUtils;
import com.jfyfox.test.api.IApi;
import com.jfyfox.test.util.ApiUtils;
import com.jfyfox.test.util.ClientParams;

import java.util.TreeMap;

public class LogoutApi implements IApi {

	public JSONObject execute() {
		TreeMap<String, String> queryParas = new TreeMap<String, String>();
		queryParas.put("version", ClientParams.version);
		queryParas.put("apiNo", System.currentTimeMillis() + "");
		queryParas.put("method", "logout");
		queryParas.put("time", DateUtils.getNow("yyyyMMddHHmmss"));
		queryParas.put("apiUser", ClientParams.apiUser);
		
		queryParas.put("p", "");
		String checkSum = ApiUtils.getSign(queryParas, ClientParams.key);
		queryParas.put("checkSum", checkSum);

		return ApiUtils.visitApi(queryParas);
	}

}
