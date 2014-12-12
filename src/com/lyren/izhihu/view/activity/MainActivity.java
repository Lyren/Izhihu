package com.lyren.izhihu.view.activity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSONArray;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.lyren.izhihu.adapter.DrawerListAdapter;
import com.lyren.izhihu.model.Section;
import com.lyren.izhihu.net.CommandType;
import com.lyren.izhihu.net.RequestManager;
import com.lyren.izhihu.net.UICallback;
import com.lyren.izhihu.net.UIData;
import com.lyren.izhihu.view.fragment.SectionFragment;
import com.lyren.izhuhu.IzhihuApplication;
import com.lyren.izhuhu.R;
import com.lyren.izhuhu.config.IzhihuConfig;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements UICallback , OnItemClickListener{

	private static String TAG  ;
	private DrawerLayout mDrawerLayout ;
	private ActionBarDrawerToggle mDrawerToggle ;
	private ListView mDrawerList ;
	private ArrayList<Section> sections ;
	private DrawerListAdapter adapter ;
	private ProgressDialog dialog ;
	
//	private int curPos = 0 ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TAG = getClass().getSimpleName() ;
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
		mDrawerList = (ListView) findViewById(R.id.drawer_view);
		toolbar.setTitle("你知我知");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_closed);
		mDrawerToggle.syncState();
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		dialog = new ProgressDialog(this);
		dialog.setTitle(R.string.loading);
		RequestQueue mQueue = IzhihuApplication.getRequestQueue(this) ;
		JsonObjectRequest jor = RequestManager.sendJsonObjectRequest(CommandType.GET_ALL_SECTIONS, this) ;
		mQueue.add(jor);
		dialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onSuccess(UIData successResponse) {
		Log.i("MainActivity onSuccess", successResponse.getCommandType().getEnumValue());
		switch (successResponse.getCommandType()) {
		case GET_ALL_SECTIONS:
			dialog.dismiss();
			JSONObject jo = successResponse.getResponseObj() ;
			try {
				String data = jo.getString(IzhihuConfig.KEY_DATA) ;
				sections = (ArrayList<Section>) JSONArray.parseArray(data, Section.class);
				adapter = new DrawerListAdapter(this, sections);
				mDrawerList.setAdapter(adapter);
				mDrawerList.setSelected(true);
				mDrawerList.setOnItemClickListener(this);
				setFragment(0);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void onFail(UIData failResponse) {
		switch (failResponse.getCommandType()) {
		case GET_ALL_SECTIONS:
			Log.i(TAG, failResponse.getResponseMsg());
			break;

		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("MainActivity", "onResume");
	}
	@SuppressLint("InlinedApi")
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		mDrawerLayout.closeDrawer(Gravity.START);
		adapter.setCurPos(position);
//		curPos = position ;
		mDrawerList.setSelection(position);
		setFragment(position);
		
	}
	private void setFragment(int position){
		FragmentManager fm = getSupportFragmentManager() ;
		FragmentTransaction transaction = fm.beginTransaction() ;
		SectionFragment f = SectionFragment.newInstance(sections.get(position).getId(), 
				sections.get(position).getDescription(), 
				sections.get(position).getThumbnail());
		transaction.replace(R.id.frag_container, f).commit();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitBy2Click();
		}
		return false;
	}
	private static Boolean isExit = false;  
	  
	private void exitBy2Click() {  
	    Timer tExit = null;  
	    if (isExit == false) {  
	        isExit = true; // 准备退出  
	        Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();  
	        tExit = new Timer();  
	        tExit.schedule(new TimerTask() {  
	            @Override  
	            public void run() {  
	                isExit = false; // 取消退出  
	            }  
	        }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务  
	  
	    } else {  
	        finish();  
	        System.exit(0);  
	    }  
	}
}
