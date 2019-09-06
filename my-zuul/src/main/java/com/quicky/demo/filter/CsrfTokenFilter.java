package com.quicky.demo.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * form中添加CSRF的hidden字段
 * 
 * <input name="${(_csrf.parameterName)!}" value="${(_csrf.token)!}" type=
 * "hidden"> ajax中添加CSRF的头
 * 
 * xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
 * 
 * 仿跨域攻击
 * 
 * @author Administrator
 *
 */
@Configuration
public class CsrfTokenFilter extends WebMvcConfigurationSupport {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean csrfFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new CsrfFilter(new HttpSessionCsrfTokenRepository()));
		registration.addUrlPatterns("/*");
		return registration;
	}

}
