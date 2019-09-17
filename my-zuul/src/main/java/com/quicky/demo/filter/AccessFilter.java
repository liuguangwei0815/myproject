//package com.quicky.demo.filter;
//
//import java.io.IOException;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import com.quicky.demo.sso.SsoFeign;
///**
// * 单点登录1
// * @author Administrator
// *
// */
//@Component
//public class AccessFilter extends ZuulFilter {
//
//	private Logger logger = LoggerFactory.getLogger(AccessFilter.class);
//
//	@Autowired
//	private SsoFeign ssoFeign;
//
//	// 过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型：
//	public static final String ERROR_TYPE = "error";
//	public static final String POST_TYPE = "post";
//	public static final String PRE_TYPE = "pre";
//	public static final String ROUTE_TYPE = "route";
//
//	/**
//	 * 都经过校验，false不经过
//	 */
//	@Override
//	public boolean shouldFilter() {
//		return true;
//	}
//
//	@Override
//	public Object run() throws ZuulException {
//		try {
//			
//			RequestContext ctx = RequestContext.getCurrentContext();
//			HttpServletRequest request = ctx.getRequest();
//			HttpServletResponse response = ctx.getResponse();
//			// 访问路径
//			String url = request.getRequestURL().toString();
//			logger.info(String.format("%s AccessFilter request to %s", request.getMethod(),
//					request.getRequestURL().toString()));
//			// 从cookie里面取值（Zuul丢失Cookie的解决方案：https://blog.csdn.net/lindan1984/article/details/79308396）
//			String accessToken = request.getParameter("accessToken");
//			for (Cookie cookie : request.getCookies()) {
//				if ("accessToken".equals(cookie.getName())) {
//					accessToken = cookie.getValue();
//				}
//			}
//			// 过滤规则：cookie有令牌且存在于Redis，或者访问的是登录页面、登录请求则放行
//			if (url.contains("sso-server/sso/loginPage") || url.contains("sso-server/sso/login")
//					|| (!StringUtils.isEmpty(accessToken)  && ssoFeign.hasKey(accessToken) )) {
//				ctx.setSendZuulResponse(true);
//				ctx.setResponseStatusCode(200);
//				return null;
//			} else {
//				ctx.setSendZuulResponse(false);
//				ctx.setResponseStatusCode(401);
//				// 重定向到登录页面
//				try {
//					response.sendRedirect("https://localhost/sso-server/sso/loginPage?url=" + url);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				return null;
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Override
//	public String filterType() {
//		return PRE_TYPE;
//	}
//
//	@Override
//	public int filterOrder() {
//		return -1;
//	}
//
//}
