package cn.zlj.blog.service;


import cn.zlj.blog.entity.LeaveMessage;
import cn.zlj.blog.service.ex.InsertException;
import cn.zlj.blog.service.ex.UserNotFoundException;

public interface ILeaveMessage {
	
	/**
	 * 用户留言业务层抽象方法
	 * @param lm 留言数据
	 * @throws UserNotFoundException 用户数据没有找到异常
	 * @throws InsertException 插入数据异常
	 */
	void leaveMessage(LeaveMessage lm)
			throws UserNotFoundException,InsertException;
}
