package com.quicky.demo.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@EnableOAuth2Sso
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecuriytConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		 http.antMatcher("/**")
//         .authorizeRequests()
//         .antMatchers("/", "/login**").permitAll()
//         .anyRequest()
//         .authenticated().and()
//         .csrf().disable();
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		http.formLogin().loginPage("/login_page");
		http.logout().logoutSuccessUrl("http://localhost:7015/logout");
		http.logout().deleteCookies("JSESSIONID");
		http.csrf().disable();//因为是多个域名下跨域， 所以需要先要禁止掉，
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/error/401");
		//super.configure(web);
	}
	
	

}
