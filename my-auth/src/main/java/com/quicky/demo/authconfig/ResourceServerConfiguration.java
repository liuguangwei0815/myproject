package com.quicky.demo.authconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 
 * * 问：请教一下根据用户角色不同访问不同请求，这个怎么搞呢？
 * 
 * 答：在 BaseUserDetailService.java 里面 第24、28行，表示对当前登录的账户增加一个角色，角色名称“admin_role”
 * 
 * 1 List<GrantedAuthority> list =
 * AuthorityUtils.createAuthorityList("admin_role"); 2
 * user.setAuthorities(list); 方式1：然后针对URL请求，设置对应的可以访问的权限，在
 * ResourceServerConfiguration.java 第31行
 * 
 * 1
 * .antMatchers("/order/**").hasAuthority("admin_role");//配置访问控制，必须具有admin_role权限才可以访问资源
 * 方式2：上面这种通过配置的方式，有时不是很灵活，一般我是通过注解方式来设置URL请求所需要的权限，下面这个代码就表示在这整个控制器内的所有请求都是需要“admin_role”权限。
 * 
 * 1 @RestController 2 @RequestMapping(value="/order/demo")
 * 3 @PreAuthorize("hasAnyAuthority('admin_role')") 4 public class
 * CustBomController { 5 } @PreAuthorize这个注解，除了类注解，还可以对方法体进行注解，注解还可以通过 and or
 * 进行多个角色权限进行控制。具体你查询网上资料。 自定义用户认证Service
 * 
 * OAuth 资源服务器配置
 * 
 * @author wunaozai
 * @date 2018-05-29
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String DEMO_RESOURCE_ID = "order";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// Since we want the protected resources to be accessible in the UI as well we
		// need
		// session creation to be allowed (it's disabled by default in 2.0.6)
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and().requestMatchers()
				.anyRequest().and().anonymous().and()
//            .authorizeRequests()
//            .antMatchers("/order/**").authenticated();//配置order访问控制，必须认证过后才可以访问
				.authorizeRequests().antMatchers("/order/**").hasAuthority("admin_role");// 配置访问控制，必须具有admin_role权限才可以访问资源
//            .antMatchers("/order/**").hasAnyRole("admin");
	}

}