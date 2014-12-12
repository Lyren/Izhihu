package com.lyren.izhihu.view.fragment;

import java.util.ArrayList;

import org.json.JSONException;

import com.alibaba.fastjson.JSONArray;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.lyren.izhihu.adapter.SectionAdapter;
import com.lyren.izhihu.model.Story;
import com.lyren.izhihu.net.CommandType;
import com.lyren.izhihu.net.RequestManager;
import com.lyren.izhihu.net.UICallback;
import com.lyren.izhihu.net.UIData;
import com.lyren.izhihu.view.activity.StoryDetailActivity;
import com.lyren.izhuhu.IzhihuApplication;
import com.lyren.izhuhu.R;
import com.lyren.izhuhu.config.IzhihuConfig;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class SectionFragment extends Fragment implements OnItemClickListener,UICallback{
	
	public static final String REQUEST_URL = "http://news-at.zhihu.com/api/3/section/" ;
	private static final String ARG_SECTION_ID = "sectionId";
	private static final String ARG_DESCRIPTION = "description" ;
	private static final String ARG_THUMBNAIL = "thumbnail";
	
	private String sectionId ;
	private String description ;
	private String thumbnail ;
	
	private ListView lisView ;
	private SwipeRefreshLayout srl ;
	private ArrayList<Story> stories = null ;
	private SectionAdapter adapter = null ;
	private ImageLoader mImageLoader = null ;
	
	public static SectionFragment newInstance(String sectionId,String description, String thumbnail){
		SectionFragment f = new SectionFragment();
		Bundle bundle = new Bundle() ;
		bundle.putString(ARG_SECTION_ID, sectionId);
		bundle.putString(ARG_DESCRIPTION, description);
		bundle.putString(ARG_THUMBNAIL, thumbnail);
		f.setArguments(bundle);
		return f ;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		sectionId = getArguments().getString(ARG_SECTION_ID);
		description = getArguments().getString(ARG_DESCRIPTION);
		thumbnail = getArguments().getString(ARG_THUMBNAIL);
		mImageLoader = IzhihuApplication.getImageLoader(getActivity());
		RequestManager.setRequestUrl(REQUEST_URL + sectionId);
		RequestQueue mQueue = IzhihuApplication.getRequestQueue(getActivity()) ;
		JsonObjectRequest jor = RequestManager.sendJsonObjectRequest(CommandType.GET_CECTION_CONTENT, this) ;
		mQueue.add(jor);
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}
	
	
	@SuppressLint("InflateParams")
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		
		View view = inflater.inflate(R.layout.fragment_section_layout, container, false);
		srl = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
		lisView = (ListView) view.findViewById(R.id.lv_section);
		srl.setColorSchemeColors(Color.BLACK,Color.RED,Color.DKGRAY,Color.GREEN);
		srl.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				srl.setRefreshing(false);
			}
		});
		srl.setRefreshing(true);
		View header = inflater.inflate(R.layout.section_list_view_header, null);
		NetworkImageView image = (NetworkImageView) header.findViewById(R.id.section_image);
		image.setImageUrl(thumbnail, mImageLoader);
		TextView desc = (TextView) header.findViewById(R.id.section_desc);
		desc.setText(description);
		
		lisView.addHeaderView(header);
		
		return view;
	}
	

	private void updateListView() {
		// TODO Auto-generated method stub
		if (adapter == null) {
			adapter = new SectionAdapter(getActivity(), stories);
		}
		lisView.setAdapter(adapter);
		lisView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (position >0 ) {
			Intent intent = new Intent(getActivity(),StoryDetailActivity.class);
			intent.putExtra("storyId", stories.get(position-1).getId());
			startActivity(intent);
		}
		
	}

	@Override
	public void onSuccess(UIData successResponse) {
		switch (successResponse.getCommandType()) {
		case GET_CECTION_CONTENT:
			srl.setRefreshing(false);
			try {
				String data = successResponse.getResponseObj().getString(IzhihuConfig.KEY_STORIES);
				stories = (ArrayList<Story>) JSONArray.parseArray(data, Story.class);
				updateListView();
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
		// TODO Auto-generated method stub
		
	}

}
