/**
 * 
 */
package com.lyren.izhihu.net;

/** 
 * @ClassName: UICallback 
 * @Description: UI界面回调接口
 * @author kaiqi.li 
 * @date 2014年12月11日 下午4:35:10  
 */
public interface UICallback {
	
	public void onSuccess(UIData successResponse);
	
	public void onFail(UIData failResponse);

}
