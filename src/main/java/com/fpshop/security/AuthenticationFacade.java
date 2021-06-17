package com.fpshop.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fpshop.security.model.UserPrincipal;

@Component
public class AuthenticationFacade implements IAuthenticationFacade{

	// 사용자 정보 퍼사드 패턴 사용
	// 로그인 정보 ?
	@Override
	public UserPrincipal getUserPrincipal() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (UserPrincipal) authentication.getPrincipal();
	}

}





