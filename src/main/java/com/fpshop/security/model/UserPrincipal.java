package com.fpshop.security.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.fpshop.model.UserEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPrincipal extends UserEntity implements OAuth2User, UserDetails {
	private Collection<? extends GrantedAuthority> authorities;	// authorities 계정이 가지고 있는 권한 목록
    private Map<String, Object> attributes;
    
    // 메서드 가져와서 재정의 하여 정보 담아서 사용
    public UserPrincipal(UserEntity user) {
    	this.setUser_pk(user.getUser_pk());
    	this.setUser_id(user.getUser_id());
    	this.setUser_pw(user.getUser_pw());
    	this.setName(user.getName());
    	this.setEmail(user.getEmail());
    	this.setAuth(user.getAuth());
    	
    	authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getAuth()));
    }
    
    public static UserPrincipal create(UserEntity user,  Map<String, Object> attributes) {
    	 UserPrincipal userPrincipal = UserPrincipal.create(user);
         userPrincipal.setAttributes(attributes);
         return userPrincipal;
    }
   
    public static UserPrincipal create(UserEntity user) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority(user.getAuth()));
        return new UserPrincipal(user);
    }
    
    // UserPrincipal 에 UserDetails 를 implements 해서 메서드를 오버라이딩(재정의) 하여 사용
    // 해당 유저의 권한을 리턴 하는 곳!!
    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(this.getAuth()));
	}
	
	@Override
	public String getPassword() {	// 비밀번호
		return this.getUser_pw();
	}
	@Override
	public String getUsername() {	// id
		return this.getUser_id();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() { 
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {		
		return attributes;
	}

	@Override
	public String getName() {
		return getName();
	}

}
