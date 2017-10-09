package com.hsuforum.easportalm.web.vowrapper;

import com.hsuforum.common.web.vo.impl.VoWrapperImpl;
import com.hsuforum.easportalm.entity.GroupFunction;
import com.hsuforum.easportalm.web.vo.GroupFunctionVo;

/**
 * The wrapper transfer GroupFunction to GroupFunctionVo
 * @author Marvin
 */
public class GroupFunctionVoWrapper extends VoWrapperImpl<GroupFunction, com.hsuforum.easportalm.entity.GroupFunctionPK> {

	private static final long serialVersionUID = 1L;

	/**
	 * @see com.hsuforum.common.web.vo.impl.VoWrapperImpl#wrap(com.hsuforum.common.entity.BaseEntity)
	 */
	@Override
	public GroupFunctionVo wrap(GroupFunction entity) {
		return new GroupFunctionVo(entity);
	}
}