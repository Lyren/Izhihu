/**
 * 
 */
package com.lyren.izhihu.view.activity;

import java.util.List;

import org.litepal.crud.DataSupport;

import com.lyren.izhihu.db.model.Favorite;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

/** 
 * @ClassName: MyFavoriteActivity 
 * @Description: 我的收藏界面
 * @author kaiqi.li 
 * @date 2014年12月18日 下午5:21:07  
 */
public class MyFavoriteActivity extends ActionBarActivity {
	
	private List<Favorite> favoriteStories ;
	private static final String TAG = "MyFavoriteActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		favoriteStories = DataSupport.findAll(Favorite.class) ;
		Log.i(TAG, " have favorite Stories--->"+ favoriteStories.size());
	}

}
