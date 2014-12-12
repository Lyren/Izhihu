package com.lyren.izhihu.model;

import com.alibaba.fastjson.annotation.JSONField;

public class Section {
	@JSONField(name = "id")
	private String id ;
	
	@JSONField(name = "thumbnail")
	private String thumbnail ;
	
	@JSONField(name = "name")
	private String name ;
	
	@JSONField(name = "description")
	private String description ;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
