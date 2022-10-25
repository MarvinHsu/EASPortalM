package com.hsuforum.easportalm.ws.vo;

import java.io.Serializable;

import com.hsuforum.easportalm.entity.Module;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class ModuleWSVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private int sequence;	
	// is show the module
	private Boolean showed = false;
	
	public ModuleWSVO(Module module){
		super();
		this.setId(module.getId());
		this.setCode(module.getCode());
		this.setName(module.getName());
		this.setSequence(module.getSequence());
		
		this.setShowed(module.getShowed());
	}
	
}
