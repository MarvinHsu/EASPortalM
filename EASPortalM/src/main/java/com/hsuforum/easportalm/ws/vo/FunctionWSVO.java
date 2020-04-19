package com.hsuforum.easportalm.ws.vo;

import java.io.Serializable;
import java.util.Date;

import com.hsuforum.easportalm.entity.Function;

public class FunctionWSVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private Date createDate;
	private String name;
	private String outcome;
	private ModuleWSVO moduleWSVO;
	private Date updateDate;
	private int sequence;
	private Boolean showed;
	

	public FunctionWSVO() {
		super();

	}
	
	public FunctionWSVO(Function function) {
		super();
		this.setId(function.getId());
		this.setCode(function.getCode());
		if(function.getCreateDate()!=null){
			this.setCreateDate(function.getCreateDate());
		}
		this.setName(function.getName());
		this.setOutcome(function.getOutcome());
		if(function.getModule()!=null){
			this.setModuleWSVO(new ModuleWSVO(function.getModule()));
		}
		if(function.getUpdateDate()!=null){
			this.setUpdateDate(function.getUpdateDate());
		}
		this.setSequence(function.getSequence());
		this.setShowed(function.getShowed());
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	
	public ModuleWSVO getModuleWSVO() {
		return moduleWSVO;
	}

	public void setModuleWSVO(ModuleWSVO moduleWSVO) {
		this.moduleWSVO = moduleWSVO;
	}

	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
