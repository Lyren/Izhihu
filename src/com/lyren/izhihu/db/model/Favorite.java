package com.lyren.izhihu.db.model;

import org.litepal.crud.DataSupport;

/** 
 * @ClassName: Favorite 
 * @Description: 收藏的News
 * @author kaiqi.li 
 * @date 2014年12月18日 下午2:03:01  
 */
public class Favorite extends DataSupport {
	
	private int id ;
	private String storyId ;
	private String title ;
	private String url ;
	private String imageUrl ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStoryId() {
		return storyId;
	}
	public void setStoryId(String storyId) {
		this.storyId = storyId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	

}
