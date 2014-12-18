/**
 * 
 */
package com.lyren.izhihu.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.lyren.izhihu.IzhihuApplication;
import com.lyren.izhihu.model.Section;
import com.lyren.izhuhu.R;

/**
 * @ClassName: DrawerListAdapter
 * @Description: 抽屉里的listview的adapter
 * @author kaiqi.li
 * @date 2014年12月12日 上午10:47:19
 */
public class DrawerListAdapter extends SimpleBaseAdapter<Section> {

	private ImageLoader mImageLoader = null;
	private int curPos = 0;

	public DrawerListAdapter(Context context, ArrayList<Section> list) {
		super(context, list);
		mImageLoader = IzhihuApplication.getImageLoader(context);
	}

	@Override
	public int getItemResourceId() {
		return R.layout.item_drawer_list;
	}

	@Override
	public View getItemView(int position, View convertView,
			SimpleBaseAdapter<Section>.ViewHolder holder) {
		NetworkImageView img = holder.getView(R.id.drawer_item_img);
		TextView text = holder.getView(R.id.drawer_item_text);
		img.setImageUrl(mDataList.get(position).getThumbnail(), mImageLoader);
		text.setText(mDataList.get(position).getName());
		text.setTextColor(position == curPos ? mContext.getResources()
				.getColor(R.color.navdrawer_text_color_selected) : mContext
				.getResources().getColor(R.color.navdrawer_text_color));
		return convertView;
	}

	public void setCurPos(int Pos) {
		curPos = Pos;
		notifyDataSetChanged();
	}

}
