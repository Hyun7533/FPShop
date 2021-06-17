package com.fpshop.security;

import static com.fpshop.security.SocialType.FACEBOOK;
import static com.fpshop.security.SocialType.GOOGLE;
import static com.fpshop.security.SocialType.KAKAO;
import static com.fpshop.security.SocialType.NAVER;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration   // 메모리에 띄우기
@EnableWebSecurity   // 활성화 시키기 (Spring Security 필터가 Spring 필터체인에 등록이 된다. => 즉.SecurityConfig 사용할수 있게 만듬
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	final UserDetailsService userService;
	final CustomOAuth2UserService customOAuth2UserService;



	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/res/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();   // csrf 를 비활성화 한다는 뜻

		http.authorizeRequests()   // url 접근 제어, (57~67) 사용자 인증이 된 요청에 대해서만 요청을 허용한다.
//            .antMatchers("/user/**").hasRole("USER")   // 유저권한 접근가능
//            .antMatchers("/user/**").authenticated()   // 유저 주소로 접근 하는 사람은 인증이 필요해!!
				.antMatchers("/admin/alogin").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")   // 관리자권한 접근가능
				.antMatchers("/**").permitAll()      // 모두 접근 가능
				.antMatchers("/board/**").permitAll()      // 모두 접근 가능

				.antMatchers("/facebook").hasAuthority(FACEBOOK.getRoleType()) //권
				.antMatchers("/google").hasAuthority(GOOGLE.getRoleType())
				.antMatchers("/kakao").hasAuthority(KAKAO.getRoleType())
				.antMatchers("/naver").hasAuthority(NAVER.getRoleType())
				.anyRequest().authenticated()   // 나머지 Requset 들은 인증이 필요하다.
//              .anyRequest().permitAll()   // 위에 주소들을 제외 하고는 전부 들어 갈수 있다.
				.and()
				.oauth2Login()
				.userInfoEndpoint()
				.userService(customOAuth2UserService)
				.and()
				.defaultSuccessUrl("/home")
				.failureUrl("/login")
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/home"));

		// post 폼 로그인
		http.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/home");

		// 로그아웃 처리
		http.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/home")
				.invalidateHttpSession(true);   // 세션 삭제

		http.exceptionHandling()
				.accessDeniedPage("/denied");
	}

	// 로그인 정보 암호화
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public ClientRegistrationRepository clientRegistrationRepository(OAuth2ClientProperties oAuth2ClientProperties,
																	 @Value("${custom.oauth2.kakao.client-id}") String kakaoClientId,
																	 @Value("${custom.oauth2.kakao.client-secret}") String kakaoClientSecret,
																	 @Value("${custom.oauth2.naver.client-id}") String naverClientId,
																	 @Value("${custom.oauth2.naver.client-secret}") String naverClientSecret) {

		List<ClientRegistration> registrations = oAuth2ClientProperties.getRegistration().keySet().stream()
				.map(client -> getRegistration(oAuth2ClientProperties, client))
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
		registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
				.clientId(kakaoClientId)
				.clientSecret(kakaoClientSecret)
				.build()
		);
		registrations.add(CustomOAuth2Provider.NAVER.getBuilder("naver")
				.clientId(naverClientId)
				.clientSecret(naverClientSecret)
				.build()
		);
		return new InMemoryClientRegistrationRepository(registrations);
	}

	private ClientRegistration getRegistration(OAuth2ClientProperties clientProperties, String client) {
		if ("google".equals(client)) {
			OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("google");
			return CommonOAuth2Provider.GOOGLE.getBuilder(client)
					.clientId(registration.getClientId())
					.clientSecret(registration.getClientSecret())
					.scope("email", "profile")
					.build();
		} else if ("facebook".equals(client)) {
			OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("facebook");
			return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
					.clientId(registration.getClientId())
					.clientSecret(registration.getClientSecret())
					.userInfoUri("https://graph.facebook.com/me?fields=id,name,email,link")
					.scope("email")
					.build();
		}
		return null;
	}
}