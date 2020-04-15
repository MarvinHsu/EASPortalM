package com.hsuforum.easportalm.web.vowrapper;

import com.hsuforum.common.web.vo.impl.VoWrapperImpl;
import com.hsuforum.easportalm.entity.Category;
import com.hsuforum.easportalm.web.vo.CategoryVo;
/**
 * 轉換Category為CategoryVo的Wrapper
 * 
 */
public class CategoryVoWrapper extends VoWrapperImpl<Category, java.lang.String>{
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @see com.hsuforum.common.web.vo.impl.VoWrapperImpl#wrap(com.hsuforum.common.entity.BaseEntity)
	 */
	@Override
	public CategoryVo wrap(Category bo) {
		return new CategoryVo(bo);
	}
}