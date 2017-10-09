package com.hsuforum.easportalm.ws.vo;

import java.io.Serializable;
import java.util.Iterator;

import com.hsuforum.easportalm.entity.Function;
import com.hsuforum.easportalm.entity.Module;

public class ModuleWSVO2 implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private int sequence;
	
	private FunctionWSVO2[] functionWSVO2s;
	// 是否在選單顯示
	private Boolean showed = false;
	public ModuleWSVO2(){
		super();
	}
	
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

	public FunctionWSVO2[] getFunctionWSVO2s() {
		return functionWSVO2s;
	}

	public void setFunctionWSVO2s(FunctionWSVO2[] functionWSVO2s) {
		this.functionWSVO2s = functionWSVO2s;
	}

	


	
	
	
}
