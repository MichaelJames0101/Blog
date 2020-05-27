package cn.zlj.blog.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.zlj.blog.entity.User;
import cn.zlj.blog.service.ex.ServiceException;


@SpringBootTest
public class UserServiceTestCase {
	@Autowired
	private IUserService service;
	
	@Test
	public void regTest() {
		try {
			User user = new User();
			user.setUsername("xiaohong");
			user.setPassword("123456");
			user.setGender(1);
			user.setAvatar("http://www.tedu.cn/logo.png");
			service.reg(user);
			System.err.println("OK.");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void loginTest() {
		try {
			String username = "xiaoming";
			String password = "123456";
			User user = service.login(username, password);
			System.err.println(user);
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void changePasswordTest() {
		try {
			Integer uid = 5;
			String oldPassword = "123456";
			String newPassword = "12345678";
			service.changePassword(uid, oldPassword, newPassword);
			System.err.println("OK.");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void changeInfoTest() {
		try {
			User user = new User();
			user.setUid(4);
			user.setPhone("666666");
			user.setEmail("www.baidu@163.com");
			user.setGender(0);
			service.changeInfo(user);
			System.err.println("OK.");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void getByUidTest() {
		Integer uid = 4;
		User user = service.getByUid(uid);
		System.err.println(user);
	}
	
	@Test
	public void updateAvatarTest() {
		try {
			Integer uid = 5;
			String avatar = "abcdefg";
			service.changeAvatar(uid, avatar);
			System.err.println("OK.");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
}
