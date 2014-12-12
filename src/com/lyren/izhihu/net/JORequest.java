package com.lyren.izhihu.net;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.util.Log;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

/**
 * @ClassName: RequestManager
 * @Description: 返回JsonObject的请求
 * @author kaiqi.li
 * @date 2014年12月11日 下午5:02:34
 */
public class JORequest {
	private String url = "";
	UIData mUiData ;
	/**
	 * @Description: 发送一条返回结果为JsonObject的请求
	 * @param method (Method.GET | Method.POST...)
	 * @param commandType
	 * @param params
	 * @param headers
	 * @param uiCallback
	 * @return request
	 */
	public JsonObjectRequest sendJsonObjectRequest(final int method ,
			final CommandType commandType, final Map<String, String> params,
			final Map<String, String> headers, final UICallback uiCallback) {
			mUiData = new UIData(commandType);
			Log.i("Request Type", commandType.getEnumValue());
		return new JsonObjectRequest(method ,url, null, new Listener<JSONObject>() {
			
			@Override
			public void onResponse(JSONObject response) {
				Log.i("Response" , commandType.getEnumValue() +":"+response.toString());
				mUiData.setResponseObj(response);
				uiCallback.onSuccess(mUiData);
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.i("Response" , commandType.getEnumValue() +":"+error.getMessage());
				mUiData.setResponseMsg(error.getMessage());
				uiCallback.onFail(mUiData);
			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				
				return params;
			}

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				if (headers == null) {
					return getDefaultHeaders();
				}
				return headers;
			}
		};
	}
	
	/**
	 * @Description: 发送一条返回结果为JsonObject的请求
	 * @param commandType
	 * @param params
	 * @param headers
	 * @param uiCallback
	 * @return request
	 */
	public JsonObjectRequest sendJsonObjectRequest(
			final CommandType commandType, final Map<String, String> params,final Map<String, String> headers,
			final UICallback uiCallback) {

		return sendJsonObjectRequest(Method.GET, commandType, params, headers, uiCallback);

	}

	/**
	 * @Description: 发送一条返回结果为JsonObject的请求
	 * @param commandType
	 * @param params
	 * @param uiCallback
	 * @return request
	 */
	public JsonObjectRequest sendJsonObjectRequest(
			final CommandType commandType, final Map<String, String> params,
			final UICallback uiCallback) {

		return sendJsonObjectRequest(commandType, params, null, uiCallback);

	}

	/**
	 * @Description: 发送一条返回结果为JsonObject的请求
	 * @param commandType
	 * @param uiCallback
	 * @return request
	 */
	public JsonObjectRequest sendJsonObjectRequest(
			final CommandType commandType, final UICallback uiCallback) {

		return sendJsonObjectRequest(commandType, null, uiCallback);

	}

	/**
	 * @Description 获取默认的请求头
	 */
	private Map<String, String> getDefaultHeaders() {
		// TODO set default headers for request
		Map<String, String> headers = new HashMap<String, String>();
		return headers;
	}
	
	public void setRequestUrl(String url){
		this.url = url ;
	}

}
