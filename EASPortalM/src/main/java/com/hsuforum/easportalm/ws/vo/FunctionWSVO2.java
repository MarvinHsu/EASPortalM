package com.hsuforum.easportalm.ws.vo;

import java.io.Serializable;
import java.util.Date;

import com.hsuforum.easportalm.entity.Function;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class FunctionWSVO2 implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private Date createDate;
	private String name;
	private String outcome;
	private Date updateDate;
	private int sequence;
	private Boolean showed;
	
	public FunctionWSVO2(Function function) {
		super();
		this.setId(function.getId());
		this.setCode(function.getCode());
		if(function.getCreateDate()!=null){
			this.setCreateDate(function.getCreateDate());
		}
		this.setName(function.getName());
		this.setOutcome(function.getOutcome());
		
		if(function.getUpdateDate()!=null){
			this.setUpdateDate(function.getUpdateDate());
		}
		this.setSequence(function.getSequence());
		this.setShowed(function.getShowed());
	}

	
}
