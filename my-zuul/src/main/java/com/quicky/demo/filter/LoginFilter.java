package com.quicky.demo.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 请求拦截
 * 
 * @author Administrator
 *
 */
@Component
public class LoginFilter extends ZuulFilter {

	private static final Logger log = LoggerFactory.getLogger(LoginFilter.class);

	/**
	 * 是否开启过滤器
	 */
	@Override
	public boolean shouldFilter() {

		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		String uri = request.getRequestURI();
		log.info("当前请求地址：" + uri);
		// 需要权限校验URL
		if ("/userapp/post".equalsIgnoreCase(request.getRequestURI())) {
			//return true;
		}
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// JWT
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		// token对象,有可能在请求头传递过来，也有可能是通过参数传过来，实际开发一般都是请求头方式
		String token = request.getHeader("token");
		if (StringUtils.isBlank((token))) {
			token = request.getParameter("token");
		}
		log.info("当前请求token：" + token);
		if (StringUtils.isBlank(token)) {
			// 过滤该请求，不对其进行路由
			requestContext.setSendZuulResponse(false);
			// 返回错误代码
			requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
		}
		return null;
	}

	/**
	 * filter类型,分为pre、error、post、 route
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 4;
	}

}
