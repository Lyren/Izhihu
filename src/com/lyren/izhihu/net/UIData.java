/**
 * 
 */
package com.lyren.izhihu.net;

import org.json.JSONObject;

/** 
 * @ClassName: UIData 
 * @Description:返回到UI界面处理的数据（主要在数据上添加一个Command type的标记）
 * @author kaiqi.li 
 * @date 2014年12月11日 下午7:38:11  
 */
public class UIData {
	private CommandType commandType ;
	private JSONObject responseObj ;
	private String ResponseMsg ;
	
	
	public UIData(){};
	
	public UIData(CommandType commandType){
		this.commandType = commandType ;
	}
	
	public String getResponseMsg() {
		return ResponseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		ResponseMsg = responseMsg;
	}

	public CommandType getCommandType() {
		return commandType;
	}
	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}
	public JSONObject getResponseObj() {
		return responseObj;
	}
	public void setResponseObj(JSONObject responseObj) {
		this.responseObj = responseObj;
	}
	

}
