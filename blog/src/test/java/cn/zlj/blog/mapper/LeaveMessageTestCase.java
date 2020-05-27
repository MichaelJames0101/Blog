package cn.zlj.blog.mapper;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.zlj.blog.entity.LeaveMessage;

@SpringBootTest
public class LeaveMessageTestCase {
	
	@Autowired
	private LeaveMessageMapper lmMapper;
	
	@Test
	public void addLeaveMessageTest() {
		LeaveMessage leaveMessage = new LeaveMessage();
		leaveMessage.setUid(5);
		leaveMessage.setIp("127.0.0.1");
		leaveMessage.setContent("你好！我在做用户留言部分持久层的测试！");
		leaveMessage.setAddTime(new Date());
		Integer rows = lmMapper.addLeaveMessage(leaveMessage);
		System.err.println(rows);
	}
}
