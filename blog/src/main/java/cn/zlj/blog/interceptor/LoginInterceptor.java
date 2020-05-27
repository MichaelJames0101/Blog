package cn.zlj.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
/**
 * 自定义的登录拦截器类，实现HandlerInterceptor接口就表示拦截器了
 * @author ZLJ
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	/*
	 * HandlerInterceptor中的抽象方法是实现了JDK8新特性的default方法，
	 * 默认是空实现的，可以写方法体，所以，并不要求重写接口中声明的方法。
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		//当session中存放的uid为null或者不存在时（可能是session过期了，也可能是session中数据丢失了等等），就“重定向”到登陆页面重新登陆。
		if(session.getAttribute("uid") == null) {
			response.sendRedirect("/web/login.html");
			//返回值为false的时候整个请求就结束了
			return false;
		}
		return true;
	}
	
}
