package cn.zlj.blog.controller;
/**
 * 控制类的基类
 * @author ZLJ
 *
 */

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.zlj.blog.controller.ex.FileContentTypeException;
import cn.zlj.blog.controller.ex.FileEmptyException;
import cn.zlj.blog.controller.ex.FileSizeException;
import cn.zlj.blog.controller.ex.FileUploadException;
import cn.zlj.blog.service.ex.InsertException;
import cn.zlj.blog.service.ex.PasswordNotMatchException;
import cn.zlj.blog.service.ex.ServiceException;
import cn.zlj.blog.service.ex.UpdateException;
import cn.zlj.blog.service.ex.UsernameConflictException;
import cn.zlj.blog.service.ex.UserNotFoundException;
import cn.zlj.blog.util.ResponseResult;

public class BaseController {
	
	//为了使得代码的可读性更高，在基类中添加常量表示操作正确时的代码：
	/**
	* 处理请求时，用于表示操作正确的代码
	*/
	public static final int SUCCESS = 200;
	
	/**
	 * 从session中获取当前登录的用户的id
	 * @param session
	 * @return 当前登录的用户的id
	 */
	public Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}
	
	/**
	 * 从session中获取当前登录用户的用户名
	 * @param session
	 * @return 当前登录的用户的用户名
	 */
	public String getUsernameFromSession(HttpSession session) {
		return session.getAttribute("username").toString();
	}
	
	@ExceptionHandler({ServiceException.class,FileUploadException.class})
	public ResponseResult<Void> handleException(Exception e){
		//声明返回对象
		ResponseResult<Void> rr = new ResponseResult<Void>();
		//在返回对象封装错误提示的文字
		rr.setMessage(e.getMessage());
		
		
		//根据异常的不同，返回错误的代码也不同
		if(e instanceof UsernameConflictException) {
			//400-用户名冲突
			rr.setState(400);
		} else if(e instanceof UserNotFoundException) {
			//401-用户数据不存在的异常
			rr.setState(401);
		} else if(e instanceof PasswordNotMatchException) {
			//402-验证用户密码失败的异常
			rr.setState(402);
		} else if(e instanceof InsertException) {
			//500-插入数据异常
			rr.setState(500);
		} else if(e instanceof UpdateException) {
			//501-更新数据异常
			rr.setState(501);
		} else if (e instanceof FileEmptyException) {
			// 601-上传的文件为空的异常
			rr.setState(601);
		} else if (e instanceof FileContentTypeException) {
			// 602-上传的文件类型错误的异常
			rr.setState(602);
		} else if (e instanceof FileSizeException) {
			// 603-上传的文件大小超出限制的异常
			rr.setState(603);
		}
		
		//返回返回对象
		return rr;
	}
}
