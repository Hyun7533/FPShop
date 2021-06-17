package com.fpshop.security;


import com.fpshop.security.model.UserPrincipal;

public interface IAuthenticationFacade {
	UserPrincipal getUserPrincipal();
}
