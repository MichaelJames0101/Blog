package cn.zlj.blog.service;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.zlj.blog.entity.LeaveMessage;
import cn.zlj.blog.service.ex.ServiceException;

@SpringBootTest
public class LeaveMessageTestCase {
	@Autowired
	private ILeaveMessage lmService;
	
	@Test
	public void LeaveMessageTest() {
		try {
			LeaveMessage lm = new LeaveMessage();
			lm.setUid(5);
			lm.setIp("127.0.0.2");
			lm.setContent("你好!测试业务层用户留言！");
			lm.setAddTime(new Date());
			lmService.leaveMessage(lm);
			System.err.println("OK.");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
}
