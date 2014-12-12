package com.lyren.izhihu.adapter;

import java.util.ArrayList;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.lyren.izhihu.model.Story;
import com.lyren.izhuhu.IzhihuApplication;
import com.lyren.izhuhu.R;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class SectionAdapter extends SimpleBaseAdapter<Story> {

	private ImageLoader mImageLoader = null ;

	public SectionAdapter(Context context, ArrayList<Story> list) {
		super(context, list);
		// TODO Auto-generated constructor stub
		mImageLoader = IzhihuApplication.getImageLoader(context);
	}

	@Override
	public int getItemResourceId() {
		// TODO Auto-generated method stub
		return R.layout.item_story;
	}

	@Override
	public View getItemView(int position, View convertView,
			SimpleBaseAdapter<Story>.ViewHolder holder) {

		NetworkImageView image = holder.getView(R.id.story_item_image);
		TextView title = holder.getView(R.id.story_item_title);
		TextView date = holder.getView(R.id.story_item_date);
		image.setImageUrl(mDataList.get(position).getImages()[0], mImageLoader);
		title.setText(mDataList.get(position).getTitle());
		date.setText(mDataList.get(position).getDate());
		return convertView;
	}

}
