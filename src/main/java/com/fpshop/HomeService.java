package com.fpshop;

import org.springframework.stereotype.Service;

import com.fpshop.model.UserEntity;
import com.fpshop.security.IAuthenticationFacade;
import com.fpshop.security.UserDetailsServiceImpl;
import com.fpshop.security.model.UserPrincipal;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeService {
	
	final private HomeMapper mapper;
	final private UserDetailsServiceImpl userDetailsService;
    final private IAuthenticationFacade authenticationFacade;
	
	public void home() {
		UserPrincipal user = authenticationFacade.getUserPrincipal();
		System.out.println("userPk(2) : " + user.getUser_pk());
	}
	
	public int mangoJoin(UserEntity p) {
		return userDetailsService.join(p);
	}
	
	public UserEntity selUser(UserEntity p) {
		return mapper.selUser(p);
	}
}
