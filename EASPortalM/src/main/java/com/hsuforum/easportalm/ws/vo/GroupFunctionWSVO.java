package com.hsuforum.easportalm.ws.vo;

import java.io.Serializable;

import com.hsuforum.easportalm.entity.GroupFunction;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class GroupFunctionWSVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private FunctionWSVO functionWSVO;
	private FunctionItemWSVO functionItemWSVO;
	private GroupWSVO2 groupWSVO2;
	
	public GroupFunctionWSVO(GroupFunction groupFunction) {
		super();
		if(groupFunction.getFunctionItem()!=null){
			this.setFunctionItemWSVO(new FunctionItemWSVO(groupFunction.getFunctionItem()));
		}
		if(groupFunction.getFunction()!=null){
			this.setFunctionWSVO(new FunctionWSVO(groupFunction.getFunction()));
		}
		if(groupFunction.getGroup()!=null){
			this.setGroupWSVO2(new GroupWSVO2(groupFunction.getGroup()));
		}
	}
	
}
