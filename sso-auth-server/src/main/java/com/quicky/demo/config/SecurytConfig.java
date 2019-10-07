package com.quicky.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.quicky.demo.handler.MyAuthenFailHandler;
import com.quicky.demo.handler.MyAuthenSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,proxyTargetClass = true)
public class SecurytConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private MyUserDetails myUserDetails;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MyAuthenFailHandler myAuthenFailHandler;
	@Autowired
	private MyAuthenSuccessHandler myAuthenSuccessHandler;
	
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
           .antMatchers("/oauth/**","/login_page","/login/**", "/logout").permitAll()
           .anyRequest().authenticated()   // 其他地址的访问均需验证权限
           .and()
           .formLogin()
           .loginPage("/login_page")//登录页
           .loginProcessingUrl("/login")//登录方法
           .usernameParameter("username")
           .passwordParameter("password")
          //.successHandler(myAuthenFailHandler)
           .failureHandler(myAuthenFailHandler)
           .successHandler(myAuthenSuccessHandler)
           //.and().logout().addLogoutHandler(myLogOutHandler)
           .and().csrf().disable().cors();
	}

	
	
}
