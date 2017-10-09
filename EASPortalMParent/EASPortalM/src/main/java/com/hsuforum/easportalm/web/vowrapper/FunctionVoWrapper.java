package com.hsuforum.easportalm.web.vowrapper;

import com.hsuforum.common.web.vo.impl.VoWrapperImpl;
import com.hsuforum.easportalm.entity.Function;
import com.hsuforum.easportalm.web.vo.FunctionVo;

/**
 * The wrapper transfer Function to FunctionVo
 * @author Marvin
 */
public class FunctionVoWrapper extends VoWrapperImpl<Function, java.lang.String> {

	private static final long serialVersionUID = 1L;

	/**
	 * @see com.hsuforum.common.web.vo.impl.VoWrapperImpl#wrap(com.hsuforum.common.entity.BaseEntity)
	 */
	@Override
	public FunctionVo wrap(Function entity) {
		return new FunctionVo(entity);
	}
}