package com.lyren.izhihu.net;


/** 
 * @ClassName: CommandType 
 * @Description: 每条请求的指令类型 
 * @author kaiqi.li 
 * @date 2014年12月11日 下午4:50:18  
 */
public enum CommandType implements CommandEnumInterface{

	
	TEST("Ttest"),
	GET_ALL_SECTIONS("getAllSections"),
	GET_CECTION_CONTENT("getSectionContent"),
	GET_STORY_DETAILS("getStoryDetails");
	
	private final String value ;
	
	CommandType(String value) {
		this.value = value ;
	}
	
	@Override
	public String getEnumValue() {
		return value;
	}
	
}
