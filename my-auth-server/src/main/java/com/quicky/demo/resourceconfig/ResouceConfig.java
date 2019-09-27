package com.quicky.demo.resourceconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源保护配置
 * 
 * @author Administrator
 *
 */
@Configuration
@EnableResourceServer
public class ResouceConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		// TODO Auto-generated method stub
		super.configure(resources);
	}

	/**
	 * 用户配置访问资源的规则
	 * 默认/oauth/**都需要保护
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		/**
		 * 允许指定将在哪个httpservletrequest实例上调用此httpsecurity。此方法允许为多个不同的requestmatcher实例轻松调用httpsecurity。
		 * 如果只需要一个requestmatcher，
		 * 请考虑使用mvcmatcher（string）、antmatcher（string）、regexmatcher（string）或requestmatcher（requestmatcher）。
		 */
		http.requestMatchers().antMatchers("/haha/*").and().authorizeRequests().anyRequest().authenticated();
	}

}
