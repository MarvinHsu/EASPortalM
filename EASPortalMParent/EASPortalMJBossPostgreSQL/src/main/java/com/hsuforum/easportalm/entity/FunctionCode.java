package com.hsuforum.easportalm.entity;

/**
 * Function code enum
 * @author Marvin
 *
 */
public enum FunctionCode {
	
	/** FunctionItemManagedBean UC1.1 Systme function item config */
	UC1_1("UC1.1", "functionItemManagedBean"),
	/** FunctionManagedBean UC1.2 System function config */
	UC1_2("UC1.2", "functionManagedBean"),
	/** GroupManagedBean UC1.3 System group config */
	UC1_3("UC1.3", "groupManagedBean"),
	/** UserManagedBean UC1.4 System user config */
	UC1_4("UC1.4", "userManagedBean"),
	/** ModuleManagedBean UC1.5 System module config */
	UC1_5("UC1.5", "moduleManagedBean");


	private String code;

	private String managedBean;

	FunctionCode(String code, String managedBean) {
		this.code = code;
		this.managedBean = managedBean;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * ManagedBena id in jsf
	 *
	 * @return managedBean id
	 */
	public String getManagedBean() {
		return this.managedBean;
	}

}
