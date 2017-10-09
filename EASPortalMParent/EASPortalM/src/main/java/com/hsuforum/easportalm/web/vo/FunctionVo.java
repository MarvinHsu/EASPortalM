package com.hsuforum.easportalm.web.vo;


import java.util.ArrayList;
import java.util.List;

import com.hsuforum.common.web.vo.impl.ValueObjectImpl;
import com.hsuforum.easportalm.entity.Function;
import com.hsuforum.easportalm.entity.FunctionItem;


/**
 * Function's Value Object Implement
 * 
 */
public class FunctionVo extends ValueObjectImpl<Function, java.lang.String>{
	
	private static final long serialVersionUID = 1L;		
	private String selectSystemId;
	private String selectModuleId;
	private List<FunctionItem> functionItemList = null;
	private List<FunctionItem> functionItemSelectedRowKeys;
	private String[] functionItemChecked;
	/**
	 * Constructor
	 * 
	 */
	public FunctionVo() {
		super(new Function());
	}

	/**
	 * Constructor
	 * @param bo Business Object
	 */
	public FunctionVo (Function bo) {
		super(bo);
	}


	

	/**
	 * Get selected Function's id
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
	 * Set selected Function's id
	 * @param selectFunctionId
	 */
	public void setSelectSystemId(String selectSystemId) {
		
		this.selectSystemId = selectSystemId;
	}
	

	/**
	 * Get selected Function's id
	 * 
	 * @return selectModuleId
	 */
	public String getSelectModuleId() {
		if (this.selectModuleId == null && this.getEntity().getModule() != null) {
			this.selectModuleId = this.getEntity().getModule().getId().toString();
		}
		return this.selectModuleId;
		
	}

	/**
	 * Set selected Function's id
	 * @param selectFunctionId
	 */
	public void setSelectModuleId(String selectModuleId) {
		
		this.selectModuleId = selectModuleId;
	}
	

	public List<FunctionItem> getFunctionItemList() {
		return functionItemList;
	}

	public void setFunctionItemList(List<FunctionItem> functionItemList) {
		this.functionItemList = functionItemList;
	}

	public List<FunctionItem> getFunctionItemSelectedRowKeys() {
		if (this.functionItemSelectedRowKeys == null) {
			this.functionItemSelectedRowKeys = new ArrayList<FunctionItem>();
		}
		return functionItemSelectedRowKeys;
	}

	public void setFunctionItemSelectedRowKeys(List<FunctionItem> functionItemSelectedRowKeys) {
		this.functionItemSelectedRowKeys = functionItemSelectedRowKeys;
	}

	public String[] getFunctionItemChecked() {
		return functionItemChecked;
	}

	public void setFunctionItemChecked(String[] functionItemChecked) {
		this.functionItemChecked = functionItemChecked;
	}

}
