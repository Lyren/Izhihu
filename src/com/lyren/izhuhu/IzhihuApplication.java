/**
 * 
 */
package com.lyren.izhuhu;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.lyren.izhuhu.util.volley.LruBitmapCache;

import android.app.Application;
import android.content.Context;

/** 
 * @ClassName: IzhihuApplication 
 * @author kaiqi.li 
 * @date 2014年12月11日 下午4:19:21  
 */
public class IzhihuApplication extends Application {
	public static String TAG ;
	
	private static RequestQueue mRequestQueue = null ;
	private static ImageLoader mImageLoader = null ;
	private static IzhihuApplication mInstense = null ;
	
	@Override
	public void onCreate() {
		super.onCreate();
		TAG = getClass().getSimpleName() ;
		mInstense = this ;
	}
	
	public static synchronized IzhihuApplication getInstense(){
		return mInstense ;
	}
	
	public static RequestQueue getRequestQueue(Context context){
		if(mRequestQueue == null){
			mRequestQueue = Volley.newRequestQueue(context) ;
			return mRequestQueue ;
		}
		return mRequestQueue ;
	}
	
	public static ImageLoader getImageLoader(Context context){
		getRequestQueue(context);
		if(mImageLoader == null ){
			mImageLoader = new ImageLoader(mRequestQueue, new LruBitmapCache()) ;
		}
		return mImageLoader;
	}
	
	
	
}
