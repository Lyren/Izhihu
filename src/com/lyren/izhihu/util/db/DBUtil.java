/**
 * 
 */
package com.lyren.izhihu.util.db;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.lyren.izhihu.db.model.Favorite;
import com.lyren.izhihu.db.model.Readed;
import com.lyren.izhihu.model.Story;

/** 
 * @ClassName: DBUtil 
 * @Description: 数据库工具类
 * @author kaiqi.li 
 * @date 2014年12月18日 下午1:26:06  
 */
public class DBUtil {
	
	public static void setIsReadedTag4Story(ArrayList<Story> list){
		
		if (list == null || list.size() <= 0) {
			return ;
		}
		List<Readed> readedNewsIds = DataSupport.select("storyId").find(Readed.class);
		List<String> ids = new ArrayList<String>();
		for (Readed item : readedNewsIds) {
			ids.add(item.getStoryId());
		}
		for (Story story : list) {
			story.setRead(ids.contains(story.getId()));
		}
	}
	
	
	public static void setIsFavoriteTag4Story(ArrayList<Story> list){
		
		if (list == null || list.size() <= 0) {
			return ;
		}
		List<Favorite> favoriteNewsIds = DataSupport.select("storyId").find(Favorite.class);
		List<String> ids = new ArrayList<String>();
		for (Favorite item : favoriteNewsIds) {
			ids.add(item.getStoryId());
		}
		for (Story story : list) {
			story.setFavorite(ids.contains(story.getId()));
		}
	}

}
