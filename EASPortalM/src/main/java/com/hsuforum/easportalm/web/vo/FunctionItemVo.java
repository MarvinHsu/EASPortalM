package com.hsuforum.easportalm.web.vo;

import com.hsuforum.common.web.vo.impl.ValueObjectImpl;
import com.hsuforum.easportalm.entity.FunctionItem;

/**
 * FunctionItem's Value Object Implement
 * 
 */
public class FunctionItemVo extends ValueObjectImpl<FunctionItem, java.lang.String> {

	private static final long serialVersionUID = 88963792018983908L;
	private String selectFunctionId;
 
	/**
	 * Constructor
	 * 
	 */
	public FunctionItemVo() {
		super(new FunctionItem());
	}

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public FunctionItemVo(FunctionItem entity) {
		super(entity);
	}

	/**
	 * Get selected FunctionItem's id
	 * 
	 * @return FunctionItemId
	 */
	public String getSelectFunctionId() {
		if (this.selectFunctionId == null && this.getEntity().getFunction() != null) {
			this.selectFunctionId = this.getEntity().getFunction().getId().toString();
		}
		return this.selectFunctionId;

	}

	/**
	 * Set selected FunctionItem's id
	 * 
	 * @param selectFunctionItemId
	 */
	public void setSelectFunctionId(String selectFunctionId) {

		this.selectFunctionId = selectFunctionId;
	}

	 

}
