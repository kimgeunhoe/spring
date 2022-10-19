package com.myweb.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.myweb.www.security.CustomAuthMemberService;
import com.myweb.www.security.LoginFailureHandler;
import com.myweb.www.security.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Bean
	public PasswordEncoder bcPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable(); //Cross_site Request Forgery
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		http.authorizeRequests().antMatchers("/member/list").hasRole("ADMIN") //"Role_ADMIN[STAFF, USER, GUEST]"
		.antMatchers("/product/register").hasRole("USER")
		.antMatchers("/","/product/list", "/product/detail", "/resources/**", "/member/register", "/member/login").permitAll()
		.anyRequest().authenticated();
		
		//원래 시큐리티가 제공하는 기본 로그인 페이지가 존재. 하지만 디자인이 전혀 되어있지 않은 html만으로 이루어져있다보니 보통 커스터마이징을 함.
		//아래의 설정은 커스텀 로그인 페이지를 구성해서 security적용을 하기위한 설정이며, controller에 주소요청 매핑도 같이 해주어야 한다.
		http.formLogin().usernameParameter("email").passwordParameter("pwd").loginPage("/member/login")
		.successHandler(AuthSuccessHandler()).failureHandler(authFailureHandler());
		
		//로그아웃은 반드시 post방식으로 진행
		http.logout().logoutUrl("/member/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/");
	}

	@Bean
	public AuthenticationFailureHandler authFailureHandler() {
		return new LoginFailureHandler();
	}

	@Bean
	public AuthenticationSuccessHandler AuthSuccessHandler() {
		return new LoginSuccessHandler();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService()).passwordEncoder(bcPasswordEncoder());
	}

	@Bean
	public UserDetailsService customUserService() {
		return new CustomAuthMemberService();
	}
	
}
