package com.hsuforum.easportalm.ws.vo;

import java.io.Serializable;

import com.hsuforum.easportalm.entity.Module;

public class ModuleWSVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private int sequence;
	
	// 是否在選單顯示
	private Boolean showed = false;
	public ModuleWSVO(){
		super();
	}
	
	public ModuleWSVO(Module module){
		super();
		this.setId(module.getId());
		this.setCode(module.getCode());
		this.setName(module.getName());
		this.setSequence(module.getSequence());
		
		this.setShowed(module.getShowed());
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
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	

	public Boolean getShowed() {
		return showed;
	}

	public void setShowed(Boolean showed) {
		this.showed = showed;
	}

	


	
	
	
}
