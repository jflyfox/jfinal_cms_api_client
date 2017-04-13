package com.jfyfox.test.util;

import com.alibaba.fastjson.JSONObject;
import com.jflyfox.util.encrypt.Base64;
import com.jflyfox.util.encrypt.Md5Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

public class ApiUtils {

	/**
	 * 接口调用
	 * 
	 * 2017年3月23日 下午10:57:02 flyfox 369191470@qq.com
	 * 
	 * @param queryParas
	 */
	public static JSONObject visitApi(TreeMap<String, String> queryParas) {
		if (ClientParams.LOG)
			System.out.println("####request:" + queryParas);
		String resp = HttpKit.get(ClientParams.url, queryParas);
		JSONObject respJson = JSONObject.parseObject(resp);
		if (0 == respJson.getInteger("code")) {
			if ("login".equals(queryParas.get("method"))) {
				ClientParams.key = respJson.getJSONObject("data").getString("key");
			}
			System.out.println("####visit succuss. key:" + ClientParams.key);
			if ("logout".equals(queryParas.get("method"))) {
				ClientParams.key = "";
			}
		} else {
			System.out.println("####visit fail. ");
		}
		if (ClientParams.LOG)
			System.out.println("####response:" + respJson.toJSONString());
		
		return respJson;
	}

	/**
	 * 解码
	 * 
	 * 2017年3月15日 下午1:49:09 flyfox 330627517@qq.com
	 * 
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decode(String params) throws UnsupportedEncodingException {
		if (params.contains("%")) {
			params = URLDecoder.decode(params, "utf-8");
		}
		params = new String(Base64.decodeBase64(params.getBytes("utf-8")), "utf-8");
		return params;
	}

	/**
	 * 编码
	 * 
	 * 2017年3月15日 下午1:49:09 flyfox 330627517@qq.com
	 * 
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encode(String params) throws UnsupportedEncodingException {
		params = new String(Base64.encodeBase64(params.getBytes("utf-8")), "utf-8");
		params = URLEncoder.encode(params, "utf-8");
		return params;
	}

	/**
	 * 获取验证sign
	 * 
	 * 2017年3月15日 下午3:14:27 flyfox 330627517@qq.com
	 * 
	 * @param paramMaps
	 * @param key
	 * @return
	 */
	public static String getSign(TreeMap<String, String> paramMaps, String key) {
		// 原始请求串
		StringBuffer src = new StringBuffer();
		for (Map.Entry<String, String> entry : paramMaps.entrySet()) {
			src.append(entry.getKey() + "=" + entry.getValue() + "&");
		}
		// 待加密串
		src.append("key=").append(key == null ? "" : key);
		if (ClientParams.LOG) {
			System.out.println("####sign str:" + src.toString());
		}
		// 生成签名
		String serverSign = new Md5Utils().getMD5(src.toString()).toUpperCase();
		return serverSign;
	}
}
