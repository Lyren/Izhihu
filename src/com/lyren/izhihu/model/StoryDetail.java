package com.lyren.izhihu.model;

import com.alibaba.fastjson.annotation.JSONField;


public class StoryDetail {
	
	@JSONField(name = "body")
	public String body;
	@JSONField(name = "image_source")
	public String image_source;
	@JSONField(name = "title")
	public String title;
	@JSONField(name = "image")
	public String image;
	@JSONField(name = "share_url")
	public String share_url;
	@JSONField(name = "js")
	public String[] js;
	@JSONField(name = "type")
	public int type;
	@JSONField(name = "ga_prefix")
	public String ga_prefix;
	@JSONField(name = "id")
	public long id;
	@JSONField(name = "css")
	public String[] css;
	
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getImage_source() {
		return image_source;
	}
	public void setImage_source(String image_source) {
		this.image_source = image_source;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getShare_url() {
		return share_url;
	}
	public void setShare_url(String share_url) {
		this.share_url = share_url;
	}
	public String[] getJs() {
		return js;
	}
	public void setJs(String[] js) {
		this.js = js;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getGa_prefix() {
		return ga_prefix;
	}
	public void setGa_prefix(String ga_prefix) {
		this.ga_prefix = ga_prefix;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String[] getCss() {
		return css;
	}
	public void setCss(String[] css) {
		this.css = css;
	}

	
}
