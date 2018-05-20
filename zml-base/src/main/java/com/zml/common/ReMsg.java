package com.zml.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ReMsg  implements Serializable{
	/**
	 * 序列化唯一标识
	 */
	private static final long serialVersionUID = 7553214414644579886L;
	/**
	 * 成功标识
	 */
	private Boolean success;
	/**
	 * 消息内容
	 */
	private String content;
	/**
	 * 消息对象集
	 */
	private Map<String,Object> map = new HashMap<String,Object>();

	/**
	 * 构造方法
	 * @param content 消息内容
	 * @param success 成功标识
	 */
	public ReMsg(String content,Boolean success){
		this.content = content;
		this.success = success;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    /**
     * 向消息对象集中加入数据
     * <br/>建议value实现序列化
     * @param key
     *            键
     * @param value
     *            值
     */
    public void add(String key,Object value){
        this.map.put(key, value);
    }
}
