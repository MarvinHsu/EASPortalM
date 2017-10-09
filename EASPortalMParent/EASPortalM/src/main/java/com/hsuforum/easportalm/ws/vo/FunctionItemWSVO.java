package com.hsuforum.easportalm.ws.vo;

import java.io.Serializable;

import com.hsuforum.easportalm.entity.FunctionItem;

public class FunctionItemWSVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private String url;
	
	public FunctionItemWSVO() {
		super();
	}
	
	public FunctionItemWSVO(FunctionItem functionItem) {
		super();
		this.setId(functionItem.getId());
		this.setCode(functionItem.getCode());
		this.setName(functionItem.getName());
		this.setUrl(functionItem.getUrl());
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
