package com.quicky.demo.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.quicky.demo.interceptor.HttpLoggingInterceptor;

/**
 * 加密解密工具类
 * 
 * @author Administrator
 *
 */
public class EnDeCryptionUtil {

	private static final Logger log = LoggerFactory.getLogger(HttpLoggingInterceptor.class);

	private static final String EMPTY_JSON = "{}";

	public static <T> String enCryption(T t) {
		String json = t == null ? EMPTY_JSON : JSON.toJSONString(t);
		json = encode(json);
		return json;
	}

	private static String encode(String json) {
		if(StringUtils.isBlank(json)) {
			return json;
		}
		try {
			return Base64.encodeBase64String(URLEncoder.encode(json, StandardCharsets.UTF_8.name()).getBytes());
		} catch (UnsupportedEncodingException e) {
			log.error("加密异常",e);
		}
		return json;
	}

	public static <T> T deCryption(String jsonParam, Class<T> clazz) {
		String json = null;
		if (StringUtils.isBlank(jsonParam))
			json = EMPTY_JSON;
		else
			json = decode(jsonParam); 
		return JSON.parseObject(json, clazz);
	}

	private static String decode(String json) {
		if(StringUtils.isBlank(json)) {
			return json;
		}
		try {
			return URLDecoder.decode(new String(Base64.decodeBase64(json)), StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			log.error("解密异常",e);
		}
		return json;
	}

}
