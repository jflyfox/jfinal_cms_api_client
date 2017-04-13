package com.jfyfox.test.api;

import com.alibaba.fastjson.JSONObject;
import com.jfyfox.test.api.impl.ConfigApi;
import com.jfyfox.test.api.impl.LoginApi;
import com.jfyfox.test.api.impl.LogoutApi;
import com.jfyfox.test.api.impl.TestApi;

import java.util.Map;

public class TestApiClient {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
	
		JSONObject respJson = null;
		Map<String, Object> resMap = null;

		test(new TestApi());

		test(new LoginApi());

		test(new ConfigApi());

		test(new LogoutApi());
		
		System.out.println("#####client run stop... time:" + (System.currentTimeMillis() - start));
		
	}

	protected static JSONObject test(IApi api) {
		System.out.println("###################" + api.getClass().getSimpleName() + " start...");
		JSONObject resp = api.execute();
		System.out.println("###################" + api.getClass().getSimpleName() + " end.");
		System.out.println();
		return resp;
	}

}
