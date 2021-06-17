package com.fpshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fpshop.HomeMapper;
import com.fpshop.model.UserEntity;
import com.fpshop.security.model.UserPrincipal;

import lombok.RequiredArgsConstructor;
// 로그인 폼 에서 날린 유저 아이디 비밀번호 UserDetails 에서 비교


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private HomeMapper mapper;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {

		return loadUserByUsername("fp_shop", user_id);   // 기본 저장
	}

	// 회원 가입시 저장 되는 것
	public UserDetails loadUserByUsername(String provider, String user_id) throws UsernameNotFoundException {
		UserEntity p = new UserEntity();
		p.setProvider(provider);
		p.setUser_id(user_id);
		UserEntity ue = mapper.selUser(p);
		if(ue == null) {   // 유저가 가 있으면 null 반환
			return null;
		}
		return new UserPrincipal(ue);   // 유저가 없으면 Security Session(Authentication(userDetails) 이렇게 됨
	}

	public int join(UserEntity p) {
		if(p.getUser_pw() != null && !"".equals(p.getUser_pw())) {
			p.setUser_pw(encoder.encode(p.getUser_pw()));
		}
		return mapper.insUser(p);
	}
}