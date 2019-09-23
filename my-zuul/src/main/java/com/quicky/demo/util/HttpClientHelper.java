package com.quicky.demo.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.SerializableEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("deprecation")
@Slf4j
public class HttpClientHelper {

	public final int MAX_FETCHSIZE = 500000;


	static class SingletonHolder {
		static HttpClientHelper instance = new HttpClientHelper();
	}

	public static HttpClientHelper getInstance() {
		return SingletonHolder.instance;
	}

	private HttpClientHelper() {
	}

	/**
	 * 发送httpPost请求，请内容为map集合（需指定key-value）
	 * 
	 * @param url
	 *            请求地址
	 * @param charset
	 *            请求/响应字符编码
	 * @param map
	 *            参数列表
	 * @return 响应结果
	 */
	public String sendHttpPost(String url, Charset charset, Map<String, String> map) throws HttpClientException {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			if (log.isDebugEnabled())
				log.debug(" send http start ");
			if (StringUtils.isBlank(url))
				throw new HttpClientException(" sendHttpPost exception,url is blank.");
			if (charset == null)
				charset = Charset.forName("UTF-8");
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url.trim());
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Entry<String, String> entry : map.entrySet())
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nvps, charset);
			httpPost.setConfig(this.getCustomConfig());
			httpPost.setEntity(urlEncodedFormEntity);
			// 执行请求，得到响应对象
			response = httpclient.execute(httpPost);
			if (log.isDebugEnabled())
				log.debug(" send http execute status is: " + response.getStatusLine());
			// 得到响应实体
			HttpEntity entity = response.getEntity();
			// 转换成字符串
			String resultXml = EntityUtils.toString(entity, charset);
			// 检查是否读取完毕
			EntityUtils.consume(entity);
			if (log.isDebugEnabled())
				log.debug(" send http end ");
			return resultXml != null && !"".equals(resultXml) ? resultXml : "";
		} catch (Exception e) {
			log.error("sendHttpPost exception:", e);
			throw new HttpClientException(" sendHttpPost exception: ", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					log.error("sendHttpPost response close IOException:", e);
				}
			}
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					log.error("sendHttpPost httpclient close IOException:", e);
				}
			}
		}
	}
	
	/**post打开流,用于下载文件*/
	public InputStream openHttpPostStream(String url, Charset charset, Map<String, String> map) throws HttpClientException {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			if (log.isDebugEnabled())
				log.debug(" send http start ");
			if (StringUtils.isBlank(url))
				throw new HttpClientException(" sendHttpPost exception,url is blank.");
			if (charset == null)
				charset = Charset.forName("UTF-8");
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url.trim());
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Entry<String, String> entry : map.entrySet())
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nvps, charset);
			httpPost.setConfig(this.getCustomConfig());
			httpPost.setEntity(urlEncodedFormEntity);
			// 执行请求，得到响应对象
			response = httpclient.execute(httpPost);
			if (log.isDebugEnabled())
				log.debug(" send http execute status is: " + response.getStatusLine());
			// 得到响应实体
			return response.getEntity().getContent();
		} catch (Exception e) {
			log.error("sendHttpPost exception:", e);
			throw new HttpClientException(" sendHttpPost exception: ", e);
		}
	}

	/**
	 * 发送httpPost,application/json格式请求，请内容为map集合（需指定key-value）
	 * 
	 * @param url
	 *            请求地址
	 * @param charset
	 *            请求/响应字符编码
	 * @param jsonStr
	 *            json字符串
	 * @return 响应结果
	 */
	public String sendHttpPostByJson(String url, Charset charset, String jsonStr) throws HttpClientException {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			if (log.isDebugEnabled())
				log.debug(" send http start ");
			if (StringUtils.isBlank(url))
				throw new HttpClientException(" sendHttpPost exception,url is blank.");
			if (charset == null)
				charset = Charset.forName("UTF-8");
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url.trim());

			httpPost.setConfig(this.getCustomConfig());
			StringEntity se = new StringEntity(jsonStr, charset);
			httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
			httpPost.setEntity(se);
			// 执行请求，得到响应对象
			response = httpclient.execute(httpPost);
			if (log.isDebugEnabled())
				log.debug(" send http execute status is: " + response.getStatusLine());
			// 得到响应实体
			HttpEntity entity = response.getEntity();
			// 转换成字符串
			String resultXml = EntityUtils.toString(entity, charset);
			// 检查是否读取完毕
			EntityUtils.consume(entity);
			if (log.isDebugEnabled())
				log.debug(" send http end ");
			return resultXml != null && !"".equals(resultXml) ? resultXml : "";
		} catch (Exception e) {
			log.error("sendHttpPost exception:", e);
			throw new HttpClientException(" sendHttpPost exception: ", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					log.error("sendHttpPost response close IOException:", e);
				}
			}
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					log.error("sendHttpPost httpclient close IOException:", e);
				}
			}
		}
	}

	/**
	 * 发送httpPost请求，请求内容为字符串
	 * 
	 * @param url
	 *            请求地址
	 * @param charset
	 *            请求/响应字符编码
	 * @param str
	 *            传递内容
	 * @return 响应结果
	 */
	public String sendHttpPost(String url, Charset charset, String str) throws HttpClientException {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			if (log.isDebugEnabled())
				log.debug(" send http start ");
			if (StringUtils.isBlank(url))
				throw new HttpClientException(" sendHttpPost exception,url is blank.");
			if (charset == null)
				charset = Charset.forName("UTF-8");
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url.trim());
			StringEntity stringEntity = new StringEntity(str, charset);
			httpPost.setConfig(this.getCustomConfig());
			httpPost.setEntity(stringEntity);
			response = httpclient.execute(httpPost);
			if (log.isDebugEnabled())
				log.debug(" send http execute status is: " + response.getStatusLine());
			HttpEntity entity = response.getEntity();
			String resultXml = EntityUtils.toString(entity, charset);
			EntityUtils.consume(entity);
			if (log.isDebugEnabled())
				log.debug(" send request end ");
			return resultXml != null && !"".equals(resultXml) ? resultXml : "";
		} catch (Exception e) {
			log.error("sendHttpPost exception:", e);
			throw new HttpClientException(" sendHttpPost exception:", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					log.error("sendHttpPost response close IOException:", e);
				}
			}
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					log.error("sendHttpPost httpclient close IOException:", e);
				}
			}
		}
	}

	/**
	 * 发送http get请求
	 * 
	 * @param url
	 *            请求地址
	 * @param charset
	 *            请求/响应字符编码
	 * @return 响应结果
	 */
	public String sendHttpGet(String url, Charset charset) throws HttpClientException {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			if (log.isDebugEnabled())
				log.debug(" send http start ");
			if (StringUtils.isBlank(url))
				throw new HttpClientException(" sendHttpGet exception,url is blank.");
			if (charset == null)
				charset = Charset.forName("UTF-8");
			httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url.trim());
			httpget.setConfig(this.getCustomConfig());
			response = httpclient.execute(httpget);
			if (log.isDebugEnabled())
				log.debug(" send http execute status is: " + response.getStatusLine());
			HttpEntity entity = response.getEntity();
			String resultXml = EntityUtils.toString(entity, charset);
			EntityUtils.consume(entity);
			if (log.isDebugEnabled())
				log.debug(" send request end ");
			return resultXml != null && !"".equals(resultXml) ? resultXml : "";
		} catch (Exception e) {
			log.error("sendHttpGet exception:", e);
			throw new HttpClientException(" sendHttpGet exception:", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					log.error("sendHttpGet response close IOException:", e);
				}
			}
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					log.error("sendHttpGet httpclient close IOException:", e);
				}
			}
		}
	}

	/**
	 * 发送httpPost请求，请求内容为流对象
	 * 
	 * @param url
	 *            请求地址
	 * @param charset
	 *            响应字符编码
	 * @param inputStream
	 *            流对象
	 * @return 响应结果
	 */
	public String sendHttpPost(String url, Charset charset, InputStream inputStream) throws HttpClientException {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			if (log.isDebugEnabled())
				log.debug(" send http start ");
			if (StringUtils.isBlank(url))
				throw new HttpClientException(" sendHttpPost exception,url is blank.");
			if (charset == null)
				charset = Charset.forName("UTF-8");
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url.trim());
			InputStreamEntity inputStreamEntity = new InputStreamEntity(inputStream, -1);
			inputStreamEntity.setContentType("binary/octet-stream");
			inputStreamEntity.setChunked(true);
			httpPost.setConfig(this.getCustomConfig());
			httpPost.setEntity(inputStreamEntity);
			response = httpclient.execute(httpPost);
			if (log.isDebugEnabled())
				log.debug(" send http execute status is: " + response.getStatusLine());
			HttpEntity entity = response.getEntity();
			String resultXml = EntityUtils.toString(entity, charset);
			EntityUtils.consume(entity);
			if (log.isDebugEnabled())
				log.debug(" send http end ");
			return resultXml != null && !"".equals(resultXml) ? resultXml : "";
		} catch (Exception e) {
			log.error("sendHttpPost exception:", e);
			throw new HttpClientException(" sendHttpPost exception:", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					log.error("sendHttpPost response close IOException:", e);
				}
			}
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					log.error("sendHttpPost httpclient close IOException:", e);
				}
			}
		}
	}

	/**
	 * 发送httpPost请求，请求内容为序列化对象
	 * 
	 * @param url
	 *            请求地址
	 * @param charset
	 *            响应字符编码
	 * @param serializable
	 *            序列化对象
	 * @return 响应结果
	 */
	public String sendHttpPost(String url, Charset charset, Serializable serializable) throws HttpClientException {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			if (log.isDebugEnabled())
				log.debug(" send http start ");
			if (StringUtils.isBlank(url))
				throw new HttpClientException(" sendHttpPost exception,url is blank.");
			if (charset == null)
				charset = Charset.forName("UTF-8");
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			SerializableEntity serializableEntity = new SerializableEntity(serializable);
			httpPost.setConfig(this.getCustomConfig());
			httpPost.setEntity(serializableEntity);
			response = httpclient.execute(httpPost);
			if (log.isDebugEnabled())
				log.debug(" send http execute status is: " + response.getStatusLine());
			HttpEntity entity = response.getEntity();
			String resultXml = EntityUtils.toString(entity, charset);
			EntityUtils.consume(entity);
			if (log.isDebugEnabled())
				log.debug(" send http end ");
			return resultXml != null && !"".equals(resultXml) ? resultXml : "";
		} catch (Exception e) {
			log.error("sendHttpPost exception:", e);
			throw new HttpClientException(" sendHttpPost exception:", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					log.error("sendHttpPost response close IOException:", e);
				}
			}
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					log.error("sendHttpPost httpclient close IOException:", e);
				}
			}
		}
	}

	// 获取自定义请求配置
	private RequestConfig getCustomConfig() {
		// 三个参数依次为 请求超时时间 链接超时时间 数据响应超时时间
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(90000).setConnectTimeout(90000).setSocketTimeout(90000).build();
		return requestConfig;
	}

	/**
	 * 将request请求中的参数转换字符串
	 * 
	 * @param request
	 *            请求源
	 * @param encode
	 *            字符编码
	 * @return String
	 * @throws IOException
	 */
	public String getJsonStrFromRequest(HttpServletRequest request, String encode) {
		StringBuffer buffer = new StringBuffer();
		try {
			request.setCharacterEncoding(encode);
			BufferedReader br = request.getReader();
			String temp = null;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
				if (buffer.length() > MAX_FETCHSIZE) {
					break;
				}
			}
		} catch (UnsupportedEncodingException e) {
			log.error(" getJsonStrFromRequest UnsupportedEncodingException:", e);
			return null;
		} catch (IOException e) {
			log.error("getJsonStrFromRequest IOException", e);
			return null;
		} catch (Exception e) {
			log.error("getJsonStrFromRequest Exception", e);
			return null;
		}
		return buffer.toString();
	}

	public String doBillWebService(String param, String url) {
		String responseXML = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			// 创建URL
			URL urlString = new URL(url);
			URLConnection urlConn = urlString.openConnection();
			urlConn.setRequestProperty("content-type", "text/xml;charset=utf-8");
			urlConn.setDoOutput(true);
			urlConn.setReadTimeout(1200000);
			PrintWriter out = new PrintWriter(urlConn.getOutputStream());
			out.print(param);
			out.close();
			urlConn.connect();
			/* 获取服务器端返回信息 */
			isr = new InputStreamReader(urlConn.getInputStream(), "utf-8"); // 解决乱码错配合61行
			StringBuffer sb = new StringBuffer();
			if (isr != null) {
				br = new BufferedReader(isr);
				String inputLine = "";
				while ((inputLine = br.readLine()) != null) {
					sb.append(inputLine);
				}
			}
			responseXML = new String(sb.toString().getBytes());
		} catch (MalformedURLException e) {
			log.error("doBillWebService MalformedURLException:", e);
		} catch (UnsupportedEncodingException e) {
			log.error("doBillWebService UnsupportedEncodingException:", e);
		} catch (IOException e) {
			log.error("doBillWebService IOException:", e);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (isr != null) {
					isr.close();
				}
			} catch (IOException e) {
				br = null;
				isr = null;
				log.error("doBillWebService IOException:", e);
			}
		}
		return responseXML;
	}

	public String sendBaofoo(String url, Map<String, String> map) throws HttpClientException {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			if (log.isDebugEnabled())
				log.debug(" send http start ");

			Charset charset = Charset.forName("UTF-8");
			SSLContext sslContext = SSLContext.getInstance("TLSv1.2");// SSLContexts.createSystemDefault();
			X509TrustManager tm = new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			sslContext.init(null, new TrustManager[] { tm }, null);

			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			Registry<ConnectionSocketFactory> r = RegistryBuilder.<ConnectionSocketFactory>create().register("https", sslsf).build();

			HttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(r);
			httpclient = HttpClients.custom().setConnectionManager(cm).build();
			HttpPost httpPost = new HttpPost(url.trim());

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Entry<String, String> entry : map.entrySet())
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nvps, charset);

			// 三个参数依次为 请求超时时间 链接超时时间 数据响应超时时间
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(10000).setConnectTimeout(10000).setSocketTimeout(10000).build();
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(urlEncodedFormEntity);
			// 执行请求，得到响应对象
			response = httpclient.execute(httpPost);
			if (log.isDebugEnabled())
				log.debug(" send http execute status is: " + response.getStatusLine());
			// 得到响应实体
			HttpEntity entity = response.getEntity();
			// 转换成字符串
			String resultXml = EntityUtils.toString(entity, charset);
			// 检查是否读取完毕
			EntityUtils.consume(entity);
			if (log.isDebugEnabled())
				log.debug(" send http end ");
			return resultXml != null && !"".equals(resultXml) ? resultXml : "";
		} catch (Exception e) {
			log.error("sendHttpPost exception:", e);
			throw new HttpClientException(" sendHttpPost exception: ", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					log.error("sendHttpPost response close IOException:", e);
				}
			}
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					log.error("sendHttpPost httpclient close IOException:", e);
				}
			}
		}
	}

	/**
	 * 发送http get请求
	 * 
	 * @param url
	 *            请求地址
	 * @param charset
	 *            请求/响应字符编码
	 * @return 响应结果
	 */
	public String sendHttpGetReturnJson(String url, Charset charset) throws HttpClientException {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			if (log.isDebugEnabled())
				log.debug(" send http start ");
			if (StringUtils.isBlank(url))
				throw new HttpClientException(" sendHttpGet exception,url is blank.");
			if (charset == null)
				charset = Charset.forName("UTF-8");
			httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url.trim());
			httpget.setConfig(this.getCustomConfig());
			response = httpclient.execute(httpget);
			if (log.isDebugEnabled())
				log.debug(" send http execute status is: " + response.getStatusLine());
			HttpEntity entity = response.getEntity();
			String resultXml = EntityUtils.toString(entity, charset);
			EntityUtils.consume(entity);
			if (log.isDebugEnabled())
				log.debug(" send request end ");
			return resultXml != null && !"".equals(resultXml) ? resultXml : "";
		} catch (Exception e) {
			log.error("sendHttpGet exception:", e);
			throw new HttpClientException(" sendHttpGet exception:", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					log.error("sendHttpGet response close IOException:", e);
				}
			}
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					log.error("sendHttpGet httpclient close IOException:", e);
				}
			}
		}
	}

	/**
	 * 
	 * @param urlPath
	 *            下载路径
	 * @param downloadDir
	 *            下载存放目录
	 * @return 返回下载文件
	 */
	public File downloadFile(String urlPath, String downloadDir,String fileName) {
		File file = null;
		try {
			// 统一资源
			URL url = new URL(urlPath);
			// 连接类的父类，抽象类
			URLConnection urlConnection = url.openConnection();
			// http的连接类
			HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
			// 设定请求的方法，默认是GET
			httpURLConnection.setRequestMethod("POST");
			// 设置字符编码
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			// 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
			httpURLConnection.connect();
			// 文件大小
			int fileLength = httpURLConnection.getContentLength();
			System.out.println("file length---->" + fileLength);
			BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
			String path = downloadDir + File.separatorChar + fileName;
			file = new File(path);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			OutputStream out = new FileOutputStream(file);
			int size = 0;
			int len = 0;
			byte[] buf = new byte[1024];
			while ((size = bin.read(buf)) != -1) {
				len += size;
				out.write(buf, 0, size);
				// 打印下载百分比
				log.debug("下载进度-------> " + len * 100 / fileLength + "%\n");
			}
			bin.close();
			out.close();
		} catch (Exception e) {
			log.error("", e);
		}
		return file;

	}

	public static void main(String[] args) {
		String queryUrl = "http://order.yiqiandai.com/allyrcd/api/order/queryOrderRcaResult?borrowNid=1808070552";
		String result = HttpClientHelper.getInstance().sendHttpGetReturnJson(queryUrl, Charset.forName("UTF-8"));
		System.out.println(result);
	}
}
