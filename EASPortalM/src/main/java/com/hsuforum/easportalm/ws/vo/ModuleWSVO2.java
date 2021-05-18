package com.hsuforum.easportalm.ws.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

import com.hsuforum.easportalm.entity.Function;
import com.hsuforum.easportalm.entity.Module;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class ModuleWSVO2 implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private int sequence;
	
	private FunctionWSVO2[] functionWSVO2s;
	// is show the menu
	private Boolean showed = false;
	
	public ModuleWSVO2(Module module){
		super();
		this.setId(module.getId());
		this.setCode(module.getCode());
		this.setName(module.getName());
		this.setSequence(module.getSequence());
		if(module.getFunctions()!=null){
			this.setFunctionWSVO2s(new FunctionWSVO2[module.getFunctions().size()]);
			Iterator<Function> iterator=module.getFunctions().iterator();
			int i=0;
			while(iterator.hasNext()){
				this.getFunctionWSVO2s()[i]=new FunctionWSVO2(iterator.next());
				i++;
			}
		}
		this.setShowed(module.getShowed());
	}
}
