package cn.zlj.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zlj.blog.entity.LeaveMessage;
import cn.zlj.blog.service.ILeaveMessage;
import cn.zlj.blog.util.ResponseResult;

@RestController
@RequestMapping("leave_message")
public class LeaveMessageController extends BaseController {
	
	@Autowired
	private ILeaveMessage lmService;
	
	/**
	 * 添加留言的控制层方法
	 * @param lm 留言数据
	 * @return 返回ResponseResult数据
	 * 
	 * test---http://localhost:8080/leave_message/add?uid=5&content=控制层测试添加留言
	 */
	@PostMapping("add")
	public ResponseResult<Void> handleLeaveMessage(LeaveMessage lm){
		lmService.leaveMessage(lm);
		return new ResponseResult<Void>(SUCCESS);
	}
}
