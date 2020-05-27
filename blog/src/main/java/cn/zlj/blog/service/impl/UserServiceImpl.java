package cn.zlj.blog.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.zlj.blog.entity.User;
import cn.zlj.blog.mapper.UserMapper;
import cn.zlj.blog.service.IUserService;
import cn.zlj.blog.service.ex.InsertException;
import cn.zlj.blog.service.ex.PasswordNotMatchException;
import cn.zlj.blog.service.ex.UpdateException;
import cn.zlj.blog.service.ex.UsernameConflictException;
import cn.zlj.blog.service.ex.UserNotFoundException;
/**
 * 用户数据业务层的实现类
 * 具体业务逻辑就是写在这里
 * @author ZLJ
 *
 */
@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserMapper mapper;

	@Override
	public void reg(User user)
			throws UsernameConflictException, InsertException {
		//根据user.getUsername()获取用户名匹配的用户数据
		String username = user.getUsername();
		User data = findByUsername(username);
		//检查用户数据是否为null
		if(data == null) {
			//是：为null,用户名未被占用，则应该补全参数中的属性值
			//-1.密码加密，并封装
			//--UUID:一个表示不可变的全局唯一标识符(UUID)的类。UUID表示一个128位的值。
			String salt = UUID.randomUUID().toString().toUpperCase();
			String md5Password = getMd5Password(user.getPassword(), salt);
			user.setPassword(md5Password);
			//-2.封装salt
			user.setSalt(salt);
			//-3.封装isDelete，固定为0
			user.setIsDelete(0);
			//-4.封装4项日志数据
			Date now = new Date();
			user.setCreatedUser(username);
			user.setCreatedTime(now);
			user.setModifiedUser(username);
			user.setModifiedTime(now);
			//-执行注册：addNew(user)
			addNew(user);
		} else {
			//否：不是null,用户名被占用，抛出UsernameConflictException
			throw new UsernameConflictException(
					"注册失败！您尝试注册的用户名(" + username + ")已经被占用！");
		}
		
	}
	
	@Override
	public User login(String username, String password)
			throws UserNotFoundException, PasswordNotMatchException {
		//根据用户名获取用户数据
		User data = findByUsername(username);
		//判断用户数据是否为null
		if(data == null) {
			//是：为null，即用户数据不存在，则抛出UsernameNotFoundException
			throw new UserNotFoundException("登陆失败！用户数据不存在！");
		}
		//否：非null，即用户数据存在，判断是否已删除：isDelete.eqauls(1)
		if(data.getIsDelete().equals(1)) {
			//是:已删除，则抛出UsernameNotFoundException
			throw new UserNotFoundException("登陆失败！用户数据不存在！");
		}
		//-否：未删除,判断密码是否有问题
		//取出salt
		String salt = data.getSalt();
		//将参数password结合salt执行加密得到newMd5Password
		String newMd5Password = getMd5Password(password, salt);
		// 判断密码是否匹配
		if (data.getPassword().equals(newMd5Password)) {
			// 是：先将查询到的用户数据中的password和salt属性设置为null再返回--为了信息安全？这里是用户获取到的信息被设空了！
			data.setPassword(null);
			data.setSalt(null);
			data.setIsDelete(null);
			return data;
		} else {
			// 否：则抛出PasswordNotMatchException
			throw new PasswordNotMatchException("登录失败！密码错误！");
		}
	}
	
	@Override
	public void changePassword(Integer uid, String oldPassword, String newPassword)
			throws UserNotFoundException, PasswordNotMatchException, UpdateException {
		//根据uid获取用户数据
		User data = findByUid(uid);
		//判断用户数据是否不存在为null
		if(data == null) {
			throw new UserNotFoundException("修改密码失败！尝试访问的用户数据不存在！");
		}
		//判断是否是被删除，即isDelete是否为1
		if(data.getIsDelete().equals(1)) {
			throw new UserNotFoundException("修改密码失败！尝试访问的用户数据不存在！");
		}
		//判断密码是否匹配
		//先获取盐值salt
		String salt = data.getSalt();
		//将oldPassword和salt进行加密，得到oldMd5Password
		String oldMd5Password = getMd5Password(oldPassword, salt);
		//判断oldMd5Password和查询到的密码是否匹配
		if(data.getPassword().equals(oldMd5Password)) {
			//是：原密码正确，就可以将newPassword和salt进行加密，得到newMd5Password
			String newMd5Password = getMd5Password(newPassword, salt);
			// 创建Date对象表示modifiedTime
			Date now = new Date();
			// 执行更新密码
			updatePassword(uid, newMd5Password, data.getUsername(), now);
		} else {
			// 否：原密码错误，抛出PasswordNotMatchException
			throw new PasswordNotMatchException();
		}
	}
	
	@Override
	public void changeInfo(User user)
			throws UserNotFoundException, UpdateException {
		//根据user.getUid()获取用户数据
		User data = findByUid(user.getUid());
		//判断用户数据是否为null
		if(data == null) {
			throw new UserNotFoundException("修改失败！用户数据不存在！");
		}
		//判断是is_Delete是否为1
		if(data.getIsDelete().equals(1)) {
			throw new UserNotFoundException("修改失败！用户数据不存在！");
		}
		//将modifiedUser和modifiedTime封装到user
		user.setModifiedUser(data.getUsername());
		user.setModifiedTime(new Date());
		
		//调用updateInfo()修改用户资料
		updateInfo(user);
	}
	
	/**
	 * findByUid(uid)---这是查询数据的方法，并不关心查询结果如何使用，用于在别的方法中查询数据，相当于在支持别的业务
	 * getByUid(uid)---这是提供数据的方法，是为了在前端修改用户资料的时候显示出该有的用户数据
	 */
	@Override
	public User getByUid(Integer uid) {
		// 查询数据
		User data = findByUid(uid);
		// 判断用户数据是否存在
		if (data == null) {
			throw new UserNotFoundException(
				"尝试访问的用户数据不存在！");
		}
		// 判断用户数据是否被标记为删除
		if (data.getIsDelete().equals(1)) {
			throw new UserNotFoundException(
				"尝试访问的用户数据不存在！");
		}
		// 清除不希望对外暴露的数据
		data.setPassword(null);
		data.setSalt(null);
		data.setIsDelete(null);
		// 返回
		return data;
	}
	
	@Override
	public void changeAvatar(Integer uid, String avatar) 
			throws UpdateException, UserNotFoundException {
		//根据uid查询用户信息：User findByUid(Integer uid)
		User data = findByUid(uid);
		//判断用户数据是否存在
		if(data == null) {
			throw new UserNotFoundException("上传失败！用户数据不存在！");
		}
		//判断用户数据是否被删除
		if(data.getIsDelete().equals(1)) {
			throw new UserNotFoundException("上传失败！用户数据不存在！");
		}
		//调用更新图像的方法
		String modifiedUser = data.getUsername();
		Date modifiedTime = new Date();
		updateAvatar(uid, avatar, modifiedUser, modifiedTime);
	}
	
/*--------------------------------------业务层中提供数据处理的方法（对应持久层）-----------------------------------------*/
	
	/**
	 * 插入用户数据
	 * @param user 用户数据
	 */
	private void addNew(User user) {
		Integer rows = mapper.addNew(user);
		if(rows != 1) {
			throw new InsertException("增加用户数据出现未知错误，请联系系统管理员！");
		}
	}
	
	/**
	 * 修改密码
	 * @param uid 用户id
	 * @param password 用户密码
	 * @param modifiedUser 修改者
	 * @param modifiedTime修改时间
	 */
	private void updatePassword(
			Integer uid,String password,String modifiedUser,Date modifiedTime) {
		Integer rows = mapper.updatePassword(uid, password, modifiedUser, modifiedTime);
		if(rows != 1) {
			throw new UpdateException("修改用户数据出现未知错误，请联系系统管理员！");
		}
	}
	
	/**
	 * 修改用户资料
	 * @param user 用户数据
	 */
	private void updateInfo(User user) {
		Integer rows = mapper.updateInfo(user);
		if(rows != 1) {
			throw new UpdateException("修改数据出现未知错误，请联系系统管理员！");
		}
	}
	
	private void updateAvatar(Integer uid,String avatar,String modifiedUser,Date modifiedTime) {
		Integer rows = mapper.updateAvatar(uid, avatar, modifiedUser, modifiedTime);
		if(rows != 1) {
			throw new UpdateException("修改数据出现未知错误，请联系系统管理员！");
		}
	}
	
	/**
	 * 根据用户id查询用户数据
	 * @param uid 用户id
	 * @return 返回用户数据
	 */
	private User findByUid(Integer uid) {
		User user = mapper.findByUid(uid);
		return user;
	}
	
	/**
	 * 根据用户名查询用户数据
	 * @param username 用户名
	 * @return 返回用户数据
	 */
	private User findByUsername(String username) {
		return mapper.findByUsername(username);
	}
	
/*----------------------------------基本不会改动调整的方法写在最下面---------------------------------------------*/

	/**
	 * MD5加密密码
	 * @param password 原密码
	 * @param salt 盐值
	 * @return 返回经过加密的密码
	 */
	private String getMd5Password(String password,String salt) {
		//自定义加密规则
		//-1.将盐值添加到原文的左侧
		//-2.执行加密十次
		String str = salt + password;
		for(int i = 0;i < 10;i++) {
			str = DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
		}
		return str;
	}

}
