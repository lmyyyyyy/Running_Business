package com.running.business.pojo;

/**
 * 实时消息
 * @author Administrator
 *
 */
public class RunInfo {
	//消息id
    private Integer id;
    //消息内容
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}