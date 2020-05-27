package cn.zlj.blog.mapper;

import cn.zlj.blog.entity.LeaveMessage;

public interface LeaveMessageMapper {
	
	/**
	 * 用户添加留言的持久层方法
	 * @param leaveMessage 用户留言数据
	 * @return 返回受影响的行数
	 */
	Integer addLeaveMessage(LeaveMessage leaveMessage);
}
