package com.hsuforum.easportalm.ws.vo;

import java.io.Serializable;

import com.hsuforum.easportalm.entity.GroupFunction;

public class GroupFunctionWSVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private FunctionWSVO functionWSVO;
	private FunctionItemWSVO functionItemWSVO;
	
	
	public GroupFunctionWSVO() {
		super();
	}
	public GroupFunctionWSVO(GroupFunction groupFunction) {
		super();
		if(groupFunction.getFunctionItem()!=null){
			this.setFunctionItemWSVO(new FunctionItemWSVO(groupFunction.getFunctionItem()));
		}
		if(groupFunction.getFunction()!=null){
			this.setFunctionWSVO(new FunctionWSVO(groupFunction.getFunction()));
		}
		
	}
	public FunctionWSVO getFunctionWSVO() {
		return functionWSVO;
	}
	public void setFunctionWSVO(FunctionWSVO functionWSVO) {
		this.functionWSVO = functionWSVO;
	}
	public FunctionItemWSVO getFunctionItemWSVO() {
		return functionItemWSVO;
	}
	public void setFunctionItemWSVO(FunctionItemWSVO functionItemWSVO) {
		this.functionItemWSVO = functionItemWSVO;
	}


	
	
	
}
