package com.lyren.izhihu.view.fragment;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.lyren.izhihu.IzhihuApplication;
import com.lyren.izhihu.config.IzhihuConfig;
import com.lyren.izhihu.model.StoryDetail;
import com.lyren.izhihu.net.CommandType;
import com.lyren.izhihu.net.RequestManager;
import com.lyren.izhihu.net.UICallback;
import com.lyren.izhihu.net.UIData;
import com.lyren.izhihu.util.web.AssetsUtils;
import com.lyren.izhihu.util.web.ZhihuUtils;
import com.lyren.izhuhu.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class StoryDetailFragment extends Fragment implements UICallback{
	public static final String BASE_REQUEST_URL = "http://news-at.zhihu.com/api/3/story/" ;
	
	private WebView mWebview ;
	private StoryDetail storyDetail = null ;
	private ArrayList<String> storyImages = new ArrayList<String>();
	private String storyId ;
	
	public static StoryDetailFragment newInstance(String storyId){
		StoryDetailFragment f = new StoryDetailFragment();
		Bundle bundle = new Bundle();
		bundle.putString("storyId", storyId);
		f.setArguments(bundle);
		return f ;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_story_details, container,false);
		mWebview = (WebView) view.findViewById(R.id.webview);
		setUpWebViewDefaults(mWebview);
		
		mWebview.setWebViewClient(mWebViewClient);
		return view;
	}
	
	private WebViewClient mWebViewClient = new WebViewClient(){
		public void onPageFinished(WebView view, String url) {
//			Log.i(TAG + " onPageFinished", storyImages.toString());
//			for (int i = 1;i<storyImages.size();i++) {
//				String javascript = "img_replace_by_url('" + storyImages.get(i) + "')";
//				mWebview.loadUrl("javascript:" + javascript);
//			}
			
		}
		
	};
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (storyDetail == null) {
			storyId = getArguments().getString("storyId");
			RequestManager.setRequestUrl(BASE_REQUEST_URL + storyId);
			RequestQueue mQueue = IzhihuApplication.getRequestQueue(getActivity()) ;
			JsonObjectRequest jor = RequestManager.sendJsonObjectRequest(CommandType.GET_STORY_DETAILS, this) ;
			mQueue.add(jor);
		}
		
	}
	
	@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
	private void setUpWebViewDefaults(WebView mWebView) {
		
		mWebView.addJavascriptInterface(new JavaScriptObject(getActivity()), "injectedObject");
		
		// 设置缓存模式
		mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		mWebView.getSettings().setJavaScriptEnabled(true);
		
		
		// Use WideViewport and Zoom out if there is no viewport defined
		mWebView.getSettings().setUseWideViewPort(true);
		mWebView.getSettings().setLoadWithOverviewMode(true);
				
		mWebView.setVerticalScrollBarEnabled(false);
		mWebView.setHorizontalScrollBarEnabled(false);
		mWebView.getSettings().setLoadsImagesAutomatically(true);
		
		// 支持通过js打开新的窗口
		mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		
		mWebView.setWebChromeClient(new WebChromeClient() {
			 
		    @Override
		    public boolean onJsAlert(WebView view, String url, String message,
		            final JsResult result) {
		    	//Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();  
	            result.cancel();  
	            return true; 
		    }
		 
		    @Override
		    public boolean onJsConfirm(WebView view, String url,
		            String message, final JsResult result) {
		    	
		        return true;
		    }
		});
	}
	
	/**
	 * 设置WebView内容
	 * 
	 * @param content
	 * @param isUpdateMode 是否为 新操作
	 */
	private void setWebView(boolean isUpdateMode) {

		if (!isAdded()) {
			return;
		}
		
		if (storyDetail == null || TextUtils.isEmpty(storyDetail.getBody())) {
			return;
		}
		
		
		String html = AssetsUtils.loadText(getActivity(), IzhihuConfig.TEMPLATE_DEF_URL);
		html = html.replace("{content}", storyDetail.getBody());
		
		SharedPreferences mPerferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
		html = html.replace("{nightTheme}", mPerferences.getBoolean("dark_theme?", false) ? "true" : "false");
		
		
		String headerDef = storyDetail.getImage();
		
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"img-wrap\">")
				.append("<h1 class=\"headline-title\">")
				.append(storyDetail.title).append("</h1>")
				.append("<span class=\"img-source\">")
				.append(storyDetail.image_source).append("</span>")
				.append("<img src=\"").append(headerDef)
				.append("\" alt=\"\">")
				.append("<div class=\"img-mask\"></div>");
		
		html = html.replace("<div class=\"img-place-holder\">", sb.toString());
		String resultHTML = replaceImgTagFromHTML(html);
		
		mWebview.loadDataWithBaseURL(null, resultHTML, "text/html", "UTF-8", null);
	}	
	
	/**
	 * 替换html中的<img标签的属性
	 * 
	 * @param html
	 * @return
	 */
	private String replaceImgTagFromHTML(String html) {
		
		Document doc = Jsoup.parse(html);

		Elements es = doc.getElementsByTag("img");

		for (Element e : es) {
			String imgUrl = e.attr("src");
			storyImages.add(imgUrl);

			String localImgPath = ZhihuUtils.getCacheImgFilePath(getActivity(), imgUrl);
			
			e.attr("src_link", "file://" + localImgPath);
			e.attr("ori_link", imgUrl);
			
			if(!imgUrl.equals(storyImages.get(0))) {
				//设置图片
				e.attr("src", imgUrl);
			}

			if (!imgUrl.equals(storyImages.get(0)) && !e.attr("class").equals("avatar") ) {
				e.attr("onclick", "openImage('" + localImgPath + "')");
			}
		}

		return doc.html();
	}
	
	public static class JavaScriptObject {

//		private Activity mInstance;

		public JavaScriptObject(Activity instance) {
//			mInstance = instance;
		}
		
		 
		public void openImage(String url) {
			
//			if (mInstance != null && !mInstance.isFinishing()) {
//				
//				Intent intent = new Intent(mInstance, NewsDetailImageActivity.class);
//				intent.putExtra("imageUrl", url);
//				
//				mInstance.startActivity(intent);
//			}
		}
	}


	@Override
	public void onSuccess(UIData successResponse) {
		switch (successResponse.getCommandType()) {
		case GET_STORY_DETAILS:
			String data = successResponse.getResponseObj().toString();
			if (data != null && !"".equals(data)) {
				storyDetail = JSONObject.parseObject(data, StoryDetail.class);
				setWebView(true);
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void onFail(UIData failResponse) {
		Toast.makeText(getActivity(), failResponse.getResponseMsg(), Toast.LENGTH_LONG).show();
	}

}
