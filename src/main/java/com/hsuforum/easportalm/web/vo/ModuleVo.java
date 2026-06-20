package com.hsuforum.easportalm.web.vo;


import com.hsuforum.common.web.vo.impl.ValueObjectImpl;
import com.hsuforum.easportalm.entity.Module;


/**
 * Module's Value Object Implement
 * 
 */
public class ModuleVo extends ValueObjectImpl<Module, java.lang.String>{
	
	private static final long serialVersionUID = 1L;		
	private String selectSystemId;

	/**
	 * Constructor
	 * 
	 */
	public ModuleVo() {
		super(new Module());
	}

	/**
	 * Constructor
	 * @param bo Business Object
	 */
	public ModuleVo (Module bo) {
		super(bo);
	}


	

	/**
	 * Get selected Module's id
	 * 
	 * @return selectSystemId
	 */
	public String getSelectSystemId() {
		if (this.selectSystemId == null && this.getEntity().getSystem() != null) {
			this.selectSystemId = this.getEntity().getSystem().getId().toString();
		}
		return this.selectSystemId;
		
	}

	/**
	 * Set selected Module's id
	 * @param selectModuleId
	 */
	public void setSelectSystemId(String selectSystemId) {
		
		this.selectSystemId = selectSystemId;
	}
	



}
