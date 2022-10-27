package com.hsuforum.easportalm.ws.vo;

import java.io.Serializable;

import com.hsuforum.easportalm.entity.FunctionItem;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class FunctionItemWSVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private String url;
	

	
	public FunctionItemWSVO(FunctionItem functionItem) {
		super();
		this.setId(functionItem.getId());
		this.setCode(functionItem.getCode());
		this.setName(functionItem.getName());
		this.setUrl(functionItem.getUrl());
	}	
}
