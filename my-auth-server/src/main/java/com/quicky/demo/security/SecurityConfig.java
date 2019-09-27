package com.quicky.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,proxyTargetClass = true)//开启认证注解@
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private MyUserDetails myUserDetails;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests().anyRequest().permitAll();
		// TODO Auto-generated method stub
		super.configure(http);
	}
	
	

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/plugins/**", "/favicon.ico");
	}



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetails).passwordEncoder(passwordEncoder);
	}
	
//	@Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        User.UserBuilder builder = User.builder();
//        UserDetails user = builder.username("zhangsan").password("$2a$10$GStfEJEyoSHiSxnoP3SbD.R8XRowP1QKOdi.N6/iFEwEJWTQqlSba").roles("USER").build();
//        UserDetails admin = builder.username("lisi").password("$2a$10$GStfEJEyoSHiSxnoP3SbD.R8XRowP1QKOdi.N6/iFEwEJWTQqlSba").roles("USER", "ADMIN").build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
	

}
