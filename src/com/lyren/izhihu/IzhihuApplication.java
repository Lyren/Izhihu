/**
 * 
 */
package com.lyren.izhihu;

import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.lyren.izhihu.util.volley.LruBitmapCache;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/** 
 * @ClassName: IzhihuApplication 
 * @author kaiqi.li 
 * @date 2014年12月11日 下午4:19:21  
 */
public class IzhihuApplication extends LitePalApplication {
	public static String TAG ;
	
	private static RequestQueue mRequestQueue = null ;
	private static ImageLoader mImageLoader = null ;
	private static IzhihuApplication mInstense = null ;
	private static SQLiteDatabase db = null ;
	
	@Override
	public void onCreate() {
		super.onCreate();
		db = Connector.getDatabase() ;
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
	
	public static SQLiteDatabase getDB(){
		return db ;
	}
	
	
}
