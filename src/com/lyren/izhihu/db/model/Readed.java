package com.lyren.izhihu.db.model;

import org.litepal.crud.DataSupport;

/** 
 * @ClassName: Readed 
 * @Description: 已读News
 * @author kaiqi.li 
 * @date 2014年12月18日 下午12:51:36  
 */
public class Readed extends DataSupport {
	
	private int id ;
	private String storyId ;
	
	
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
	
}
