package com.lyren.izhihu.view.activity;


import com.lyren.izhihu.view.fragment.StoryDetailFragment;
import com.lyren.izhuhu.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class StoryDetailActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story_detail_layout);
		
		String id = getIntent().getStringExtra("storyId");
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.story_content,
						StoryDetailFragment.newInstance(id)).commit();

	}



}
