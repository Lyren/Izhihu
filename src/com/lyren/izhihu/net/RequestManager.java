/**
 * 
 */
package com.lyren.izhihu.net;

import java.util.Map;

import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;

/** 
 * @ClassName: RequestManager 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author kaiqi.li 
 * @date 2014年12月12日 上午9:46:23  
 */
public class RequestManager {
	
	private static String url = null ;
	private static final String DEFAULT_URL = "http://news-at.zhihu.com/api/3/sections" ;
	
	/**
	 * @Description: 发送一条返回结果为JsonObject的请求
	 * @param method (Method.GET | Method.POST...)
	 * @param commandType
	 * @param params
	 * @param headers
	 * @param uiCallback
	 * @return request
	 */
	public static JsonObjectRequest sendJsonObjectRequest(final int method ,
			final CommandType commandType, final Map<String, String> params,
			final Map<String, String> headers, final UICallback uiCallback) {
		JORequest jor = new JORequest() ;
		if(url != null)
			jor.setRequestUrl(url);
		else 
			jor.setRequestUrl(DEFAULT_URL);
		
		
		return jor.sendJsonObjectRequest(method, commandType, params, headers, uiCallback) ;
		
	}
	
	/**
	 * @Description: 发送一条返回结果为JsonObject的请求
	 * @param commandType
	 * @param params
	 * @param headers
	 * @param uiCallback
	 * @return request
	 */
	public static JsonObjectRequest sendJsonObjectRequest(
			final CommandType commandType, final Map<String, String> params,final Map<String, String> headers,
			final UICallback uiCallback) {
		return sendJsonObjectRequest(Method.GET,commandType, params, headers, uiCallback);

	}
	
	/**
	 * @Description: 发送一条返回结果为JsonObject的请求
	 * @param commandType
	 * @param params
	 * @param uiCallback
	 * @return request
	 */
	public static JsonObjectRequest sendJsonObjectRequest(
			final CommandType commandType, final Map<String, String> params,
			final UICallback uiCallback) {
		return sendJsonObjectRequest(commandType, params, null , uiCallback);

	}

	/**
	 * @Description: 发送一条返回结果为JsonObject的请求
	 * @param commandType
	 * @param uiCallback
	 * @return request
	 */
	public  static JsonObjectRequest sendJsonObjectRequest(
			final CommandType commandType, final UICallback uiCallback) {
		return sendJsonObjectRequest(commandType, null, uiCallback);

	}
	
	public static void setRequestUrl(String url){
		RequestManager.url = url;
	}
}
