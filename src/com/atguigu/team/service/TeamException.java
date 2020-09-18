package com.atguigu.team.service;
/**
 * 
 * @Dsecription 自定义异常率 
 * @author luozhuishuai Email:luozuishuai@126.com
 * @version 
 * @date 2020年9月17日下午6:26:38
 *
 */
public class TeamException extends Exception{
    static final long serialVersionUID = -7034897190711566939L;	
    public TeamException(){
    	super();
    }
    public TeamException(String msg){
    	super(msg);
    }
}
