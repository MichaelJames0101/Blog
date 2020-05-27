package cn.zlj.blog.service.impl;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zlj.blog.entity.LeaveMessage;
import cn.zlj.blog.entity.User;
import cn.zlj.blog.mapper.LeaveMessageMapper;
import cn.zlj.blog.mapper.UserMapper;
import cn.zlj.blog.service.ILeaveMessage;
import cn.zlj.blog.service.ex.InsertException;
import cn.zlj.blog.service.ex.UserNotFoundException;

@Service
public class LeaveMessageImpl implements ILeaveMessage {

	@Autowired
	private LeaveMessageMapper lmMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void leaveMessage(LeaveMessage lm) throws UserNotFoundException, InsertException {
		//通过留言信息获取用户id
		Integer uid = lm.getUid();
		//通过uid获取用户数据
		User data = userMapper.findByUid(uid);
		//判断用户数据是否为空
		if(data == null) {
			throw new UserNotFoundException("留言失败！用户数据不存在！");
		}
		//判断用户数据是否被删除
		if(data.getIsDelete().equals(1)) {
			throw new UserNotFoundException("留言失败！用户数据不存在！");
		}
		//封装4项日志
		lm.setCreatedUser(data.getUsername());
		lm.setCreatedTime(new Date());
		lm.setModifiedUser(data.getUsername());
		lm.setModifiedTime(new Date());
		
		//执行留言
		addLeaveMessage(lm);
	}
	
	private void addLeaveMessage(LeaveMessage lm) {
		Integer rows = lmMapper.addLeaveMessage(lm);
		if(rows != 1) {
			throw new InsertException("添加留言失败！请联系系统管理员！");
		}
	}

}
