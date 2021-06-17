package com.fpshop.model;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter	// lombok 사용
public class UserEntity {
	
	private int user_pk;	//추가
	private String provider; // 로그인 타입(kakao,naver...etc) 
//	private int state;	// 고객분류(일반, 특별, 관리, 정지)
	private String auth; // 상태 (ROLE-user/admin)
	private String name;	// 회원명
	private String user_id;	// 회원아이디
	private String user_pw;	// 회원비밀번호
	private String email;	// 이메일
	private String profileImg; // 프로필 이미지
	private String tel;	// 전화번호
	private String zipcode;	// 우편번호
	private String addr;	// 주소
//	private int point;	// 포인트
	private String registerdate;	// 가입일
//	private String sessionid;	// 세션아이디(자동로그인)
	

	/* 
	추가 항목 3개
	provider, profileimg, auth
	userPk = SEQ
	provider = 추가됨 소셜 로그인 시 가져올 아이디
	uid = id
	upw = pwd
	emial = email
	profileimg = 추가 됨
	nm = name
	auth === 추가 됨 	'ROLE-USER' , 'ROLE-ADMIN' 
	auth => 추후 상황보고 state 로 변경 가능
	*/
	
}