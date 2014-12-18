package com.lyren.izhihu.model;

import com.alibaba.fastjson.annotation.JSONField;

public class Story {
	
	@JSONField(name = "display_date")
	private String display_date;
	
	@JSONField(name = "title")
	private String title ;
	
	@JSONField(name = "url")
	private String url ;
	
	@JSONField(name = "date")
	private String date ;
	
	@JSONField(name = "images")
	private String[] images ;
	
	@JSONField(name = "id")
	private String id ;
	
	private boolean isRead = false ;
	private boolean isFavorite = false ;

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public String getDisplay_date() {
		return display_date;
	}

	public void setDisplay_date(String display_date) {
		this.display_date = display_date;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
