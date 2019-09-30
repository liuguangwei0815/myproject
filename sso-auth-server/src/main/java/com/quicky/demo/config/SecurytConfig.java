package com.quicky.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,proxyTargetClass = true)
public class SecurytConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private MyUserDetails myUserDetails;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetails).passwordEncoder(passwordEncoder);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**","/css/**", "/js/**", "/plugins/**", "/favicon.ico");
	}
	
//	  /**
//     * Spring Boot 2 配置，这里要bean 注入
//     */
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        AuthenticationManager manager = super.authenticationManagerBean();
//        return manager;
//    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		   http.authorizeRequests()
           .antMatchers("/oauth/**","/login/**", "/logout").permitAll()
           .anyRequest().authenticated()   // 其他地址的访问均需验证权限
           .and()
           .formLogin()
           .loginPage("/login")
           //.and().logout().addLogoutHandler(myLogOutHandler)
           .and().csrf().disable().cors();
	}

	
	
}
