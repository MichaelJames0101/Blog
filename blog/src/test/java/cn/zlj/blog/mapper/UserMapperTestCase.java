package cn.zlj.blog.mapper;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.zlj.blog.entity.User;

/**
 * 	Spring Boot提供了一个@SpringBootTest注释，spring-test @ContextConfiguration当您需要Spring Boot功能时，
 * 它可以用作标准注释的替代方法。注释的工作原理是通过 创建 ApplicationContext测试中使用的SpringApplication。
 * 除了 @SpringBootTest提供许多其他注释之外，还提供了用于 测试应用程序的更具体的切片。
 * 	如果您使用的是JUnit 4，请不要忘记添加@RunWith(SpringRunner.class)到测试中，否则注释将被忽略。
 * 如果您使用的是JUnit 5，则无需添加等效项@RunWith(SpringExtension)，@SpringBootTest并且其他@…Test注释已经使用它进行注释。
 * @author ZLJ
 *
 */
@SpringBootTest
public class UserMapperTestCase {
	
	@Autowired
	private UserMapper mapper;
	
	@Test
	public void addNewTest() {
		User user =new User();
		String username = "zhaoqi";
		String password = "123456";
		user.setUsername(username);
		user.setPassword(password);
		Integer rows = mapper.addNew(user);
		System.err.println("rows: "+rows);
	}
	
	@Test
	public void findByUserNameTest() {
		String username = "wangwu";
		User user = mapper.findByUsername(username);
		System.err.println(user);
	}
	
	@Test
	public void findByUidTestTest() {
		Integer uid = 6;
		User user = mapper.findByUid(uid);
		System.err.println(user);
	}
	
	@Test
	public void updatePasswordTest() {
		Integer uid = 6;
		String password = "12345678";
		String modifiedUser = "zlj";
		Date modifiedTime = new Date();
		Integer rows = mapper.updatePassword(uid, password, modifiedUser, modifiedTime);
		System.err.println(rows);
	}
	
	@Test
	public void updateInfoTest() {
		User user = new User();
		user.setUid(4);
		user.setPhone("888888");
		user.setEmail("zhanglijun_01@163.com");
		user.setGender(1);
		Integer rows = mapper.updateInfo(user);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void updateAvatarTest() {
		Integer uid = 5;
		String avatar = "abc";
		String modifiedUser = "zlj";
		Date modifiedTime = new Date();
		Integer rows = mapper.updateAvatar(uid, avatar, modifiedUser, modifiedTime);
		System.err.println(rows);
	}
}
