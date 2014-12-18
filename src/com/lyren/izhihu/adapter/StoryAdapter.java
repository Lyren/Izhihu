package com.lyren.izhihu.adapter;

import java.util.ArrayList;

import org.litepal.crud.DataSupport;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.lyren.izhihu.IzhihuApplication;
import com.lyren.izhihu.db.model.Favorite;
import com.lyren.izhihu.model.Story;
import com.lyren.izhuhu.R;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StoryAdapter extends SimpleBaseAdapter<Story> {

	private ImageLoader mImageLoader = null;
	private int lastOpenPos = -1;
	private OpenToolTip callback;

	public StoryAdapter(Context context, ArrayList<Story> list,
			OpenToolTip callback) {
		super(context, list);
		// TODO Auto-generated constructor stub
		mImageLoader = IzhihuApplication.getImageLoader(context);
		this.callback = callback;
	}

	@Override
	public int getItemResourceId() {
		// TODO Auto-generated method stub
		return R.layout.item_story;
	}

	@Override
	public View getItemView(final int position, View convertView,
			SimpleBaseAdapter<Story>.ViewHolder holder) {

		final LinearLayout tooltip = holder.getView(R.id.ll_story_tooltip);
		final Button share = holder.getView(R.id.story_share);
		final Button addToFavorite = holder.getView(R.id.story_add);

		share.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("image/*");
				intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
				intent.putExtra(Intent.EXTRA_TEXT, mDataList.get(position).getUrl());
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				mContext.startActivity(Intent.createChooser(intent,"haha"));
			}
		});
		addToFavorite.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//从收藏列表中移除
				if (mDataList.get(position).isFavorite()) {
					int row = DataSupport.deleteAll(Favorite.class, "storyId = ?",mDataList.get(position).getId());
					if (row >= 1 ) {
						mDataList.get(position).setFavorite(false);
						notifyDataSetChanged();
					}
				}else {//添加到收藏列表
					Favorite favoriteStory = new Favorite();
					favoriteStory
							.setImageUrl(mDataList.get(position).getImages()[0]);
					favoriteStory.setStoryId(mDataList.get(position).getId());
					favoriteStory.setTitle(mDataList.get(position).getTitle());
					favoriteStory.setUrl(mDataList.get(position).getUrl());
					boolean isSaved = favoriteStory.save();

					mDataList.get(position).setFavorite(isSaved);
					if (isSaved) {
						notifyDataSetChanged();
					}
				}
			}
		});
		addToFavorite.setText(mDataList.get(position).isFavorite() ? mContext
				.getResources().getString(R.string.added) : mContext
				.getResources().getString(R.string.add));

		NetworkImageView image = holder.getView(R.id.story_item_image);
		TextView title = holder.getView(R.id.story_item_title);
		TextView date = holder.getView(R.id.story_item_date);
		image.setImageUrl(mDataList.get(position).getImages()[0], mImageLoader);
		title.setText(mDataList.get(position).getTitle());
		title.setTextColor(mDataList.get(position).isRead() ? mContext
				.getResources().getColor(R.color.text_color_readed) : mContext
				.getResources().getColor(R.color.text_color_not_readed));
		date.setText(mDataList.get(position).getDate());
		date.setTextColor(mDataList.get(position).isRead() ? mContext
				.getResources().getColor(R.color.text_color_readed) : mContext
				.getResources().getColor(R.color.text_color_not_readed));

		image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (lastOpenPos != -1 && position != lastOpenPos) {
					callback.onClickToolTip(lastOpenPos);
				}
				if (lastOpenPos == position) {
					tooltip.setVisibility(View.GONE);
					lastOpenPos = -1;
				} else {
					tooltip.setVisibility(View.VISIBLE);
					lastOpenPos = position;
				}
			}
		});
		if (position == lastOpenPos) {
			tooltip.setVisibility(View.VISIBLE);
		} else {
			tooltip.setVisibility(View.GONE);
		}
		image.setTag(position);
		return convertView;
	}

	public interface OpenToolTip {
		public void onClickToolTip(int position);
	}

}
