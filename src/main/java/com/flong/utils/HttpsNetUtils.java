package com.flong.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

/***
 * 
 * @author liangjl
 *
 */
public class HttpsNetUtils {
	
	
	/**
	 * 检测SSL安全========================
	 */
	public static void trustEveryoneSSL() {   
        try {    
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {    
                public boolean verify(String hostname, SSLSession session) {    
                    return true;    
                }    
            });    
    
            SSLContext context = SSLContext.getInstance("TLS");    
            context.init(null, new X509TrustManager[] { new X509TrustManager() {    
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {    
                }    
    
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {    
                }    
    
                public X509Certificate[] getAcceptedIssuers() {    
                    return new X509Certificate[0];    
                }    
            } }, new SecureRandom());    
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());    
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
    } 
	
	/**
	 * 发送请求.
	 * @param urlStr 请求连接
	 * @param method 提交方法POST|GET
	 * @return
	 */
	public static String requestHttpURLConnection(String urlStr, String method) {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(urlStr);//请求连接
			connection = (HttpURLConnection) url.openConnection();// 新建连接实例
			connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
			connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
			connection.setDoOutput(true);// 是否打开输出流 true|false
			connection.setDoInput(true);// 是否打开输入流true|false
			connection.setRequestMethod(method);// 提交方法POST|GET
			connection.setUseCaches(false);// 是否缓存true|false
			connection.connect();// 打开连接端口
		    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));// 往对端写完数据对端服务器返回数据
			// ,以BufferedReader流来读取
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();// 关闭连接
			}
		}
		return null;
	}

}
