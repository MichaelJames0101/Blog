package cn.zlj.blog.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.zlj.blog.interceptor.LoginInterceptor;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
	
	int visitNum;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		visitNum++;
		
		//创建拦截器对象---接口引用指向实现类对象，一对多，降低耦合度
		HandlerInterceptor interceptor = new LoginInterceptor();
		
		//白名单
		List<String> list = new ArrayList<String>();
		//静态资源放在白名单能够直接访问到
		list.add("/bootstrap3/**");
		list.add("/images/**");
		list.add("/css/**");
		list.add("/js/**");
		
		list.add("/users/reg");
		list.add("/users/login");
		list.add("/web/register.html");
		list.add("/web/login.html");
		
		//添加拦截器
		registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(list);
		
	}
	
}
