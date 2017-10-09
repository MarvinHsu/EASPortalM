package com.hsuforum.easportalm.web.vowrapper;

import com.hsuforum.common.web.vo.impl.VoWrapperImpl;
import com.hsuforum.easportalm.entity.System;
import com.hsuforum.easportalm.web.vo.SystemVo;
/**
 * 轉換System為SystemVo的Wrapper
 * 
 */
public class SystemVoWrapper extends VoWrapperImpl<System, java.lang.String>{
	
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public SystemVo wrap(System bo) {
		return new SystemVo(bo);
	}
}