package com.quicky.demo.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 *  @ClassName IpAddressUtil
 *  @Description 通过解析访问地址
 *  @author liuwei
 *  @version 1.0
 */
@Slf4j
public class IpAddressUtil {


	/**
	 * 获取ip所在地
	 * 
	 * @param IP
	 * @return
	 */
	public static String getAddressByIp(String IP) {
		if (IP == null || "".equals(IP))
			return null;
		if ("127.0.0.1".equals(IP) || "0:0:0:0:0:0:0:1".equals(IP))
			return "未分配或者内网IP";
		String resout = null;
		try {
//			String ipAddressURl = GeneralProperties.getProperty("ipAddressURl",
//					"http://ip.taobao.com/service/getIpInfo.php");
			String ipAddressURl = "http://ip.taobao.com/service/getIpInfo.php";
			String str = getJsonContent(ipAddressURl + "?ip=" + IP);
			if (str == null) {
				return resout;
			}
			if (str.indexOf("<html>") == -1) {
				String code = str.substring(str.indexOf("code\":") + 6, str.indexOf("code\":") + 7);
				str = str.substring(str.indexOf("data\":") + 6, str.length() - 1);
				if (!"0".equals(code)) {// ip info not found.
					return str;
				}
				AddressVo addressVo = JSON.parseObject(str,  AddressVo.class);
				if (addressVo == null) {
					return resout;
				}
				if (addressVo.getIsp() == null || "".equals(addressVo.getIsp())) {
					resout = addressVo.getCountry() + "," + addressVo.getArea() + "," + addressVo.getCity();
				} else {
					resout = addressVo.getCountry() + "," + addressVo.getArea() + "," + addressVo.getCity() + ","
							+ addressVo.getIsp();
				}
			}
		} catch (Exception e) {
			log.error("getAddressByIp exception,ip is :" + IP + ",get address value :" + resout, e);
		}
		return resout;
	}

	/**
	 * 获取用户登录IP
	 * 
	 * @param request
	 * @return String
	 */
	public static String getIpAddr(HttpServletRequest request) {
    	if (request == null) {
			return "unknown";
		}

		String ip = request.getHeader("x-forwarded-for");

		ip = getIpAddr(ip, request, "X-Forwarded-For");
		ip = getIpAddr(ip, request, "Proxy-Client-IP");
		ip = getIpAddr(ip, request, "X-Real-IP");
		ip = getIpAddr(ip, request, "WL-Proxy-Client-IP");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "127.0.0.1";
		}
		
		if (ip.split(",").length > 1) {
			ip = ip.split(",")[0];
		}
		
		return ip;
	}

	private static String getIpAddr(String ip, HttpServletRequest request, String headerParam) {
		String tempIp = request.getHeader(headerParam) == null ? "" : request.getHeader(headerParam);
		log.debug("#######" + headerParam + "#######:" + tempIp);
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = tempIp;
		}
		return ip;
	}


	private static String getJsonContent(String urlStr) {
		String rtnStr = null;
		try {// 获取HttpURLConnection连接对象
			URL url = new URL(urlStr);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			// 设置连接属性
			httpConn.setConnectTimeout(3000);
			httpConn.setDoInput(true);
			httpConn.setRequestMethod("GET");
			// 获取相应码
			int respCode = httpConn.getResponseCode();
			if (respCode == 200) {
				rtnStr = convertStream2Json(httpConn.getInputStream());
			}
			httpConn.disconnect();
		} catch (MalformedURLException e) {
			log.error("getJsonContent MalformedURLException", e);
		} catch (IOException e) {
			log.error("getJsonContent IOException", e);
		}
		return rtnStr;
	}

	private static String convertStream2Json(InputStream inputStream) {
		String jsonStr = null;
		// ByteArrayOutputStream相当于内存输出流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		// 将输入流转移到内存输出流中
		try {
			while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
				out.write(buffer, 0, len);
			}
			// 将内存流转换为字符串
			jsonStr = new String(out.toByteArray());
			out.close();
		} catch (IOException e) {
			log.error("convertStream2Json IOException", e);
		}
		return jsonStr;
	}

	public static String getInternetIp() {
		int tryCount = 2;
		for (int i = 0; i < tryCount; i++) {
			String ret = parseIp138();
			if (!StringUtils.isEmpty(ret))
				return ret;
			ret = parseYY();
			if (!StringUtils.isEmpty(ret))
				return ret;
		}
		return "";
	}

	private static String parseIp138() {
		String url = "http://2017.ip138.com/ic.asp";
		String resp = null;
		try {
			resp = HttpClientHelper.getInstance().sendHttpGet(url, null);
		} catch (HttpClientException e) {
			return null;
		}
		if (StringUtils.isEmpty(resp))
			return null;

		int body = resp.indexOf("<body");
		if (body > 0)
			resp = resp.substring(body);

		// 您的IP是：[xxx] 来自：xx省xx市 联通
		int start = resp.indexOf("[") + 1;
		if (start < 0)
			return null;

		int end = resp.indexOf("]", start);
		return resp.substring(start, end);
	}

	private static String parseYY() {
		String url = "https://ipip.yy.com/get_ip_info.php";
		String resp = null;
		try {
			resp = HttpClientHelper.getInstance().sendHttpGet(url, null);
		} catch (Exception e) {
			return null;
		}
		if (StringUtils.isEmpty(resp))
			return null;
		// returnInfo={"cip":"xx","cname":"xx","country":"xx","province":"xx","city":"xx","isp":"xx"};
		int start = resp.indexOf("cip\":") + 6;
		if (start < 0)
			return null;
		int end = resp.indexOf("\"", start);
		return resp.substring(start, end);
	}
}
