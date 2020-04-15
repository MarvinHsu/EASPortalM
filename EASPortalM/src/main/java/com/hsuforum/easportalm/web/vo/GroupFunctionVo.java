package com.hsuforum.easportalm.web.vo;

import com.hsuforum.common.web.vo.impl.ValueObjectImpl;
import com.hsuforum.easportalm.entity.GroupFunction;

/**
 * GroupFunction's Value Object Implement
 * 
 */
public class GroupFunctionVo extends ValueObjectImpl<GroupFunction, String> {

	private static final long serialVersionUID = 1L;

	private String selectFunctionId;
	private String selectFunctionItemId;
	private String selectGroupId;

	/**
	 * Constructor
	 * 
	 */
	public GroupFunctionVo() {
		super(new GroupFunction());
	}

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public GroupFunctionVo(GroupFunction entity) {
		super(entity);
	}

	/**
	 * Get selected Function's id
	 *  
	 * @return selectFunctionId
	 */
	public String getSelectFunctionId() {
		if (this.selectFunctionId == null && this.getEntity().getFunction() != null) {
			this.selectFunctionId = this.getEntity().getFunction().getId().toString();
		}
		return this.selectFunctionId;

	}

	/**
	 * Set selected Function's Id
	 * 
	 * @param selectFunctionId
	 */
	public void setSelectFunctionId(String selectFunctionId) {

		this.selectFunctionId = selectFunctionId;
	}

	/**
	 * Get selected FunctionItem's id
	 * @return selectFunctionItemId
	 */
	public String getSelectFunctionItemId() {
		if (this.selectFunctionItemId == null && this.getEntity().getFunctionItem() != null) {
			this.selectFunctionItemId = this.getEntity().getFunctionItem().getId().toString();
		}
		return this.selectFunctionItemId;

	}

	/**
	 * Set selected FunctionItem's id
	 * 
	 * @param selectFunctionItemId
	 */
	public void setSelectFunctionItemId(String selectFunctionItemId) {

		this.selectFunctionItemId = selectFunctionItemId;
	}

	/**
	 * Get selected Group's id
	 * 
	 * @return selectGroupId
	 */
	public String getSelectGroupId() {
		if (this.selectGroupId == null && this.getEntity().getGroup() != null) {
			this.selectGroupId = this.getEntity().getGroup().getId().toString();
		}
		return this.selectGroupId;

	}

	/**
	 * Set selected Group's id
	 * 
	 * @param selectGroupFunctionId
	 */
	public void setSelectGroupId(String selectGroupId) {

		this.selectGroupId = selectGroupId;
	}

}
