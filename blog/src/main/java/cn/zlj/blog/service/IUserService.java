package cn.zlj.blog.service;

import cn.zlj.blog.entity.User;
import cn.zlj.blog.service.ex.InsertException;
import cn.zlj.blog.service.ex.PasswordNotMatchException;
import cn.zlj.blog.service.ex.UpdateException;
import cn.zlj.blog.service.ex.UsernameConflictException;
import cn.zlj.blog.service.ex.UserNotFoundException;

/**
 * 用户数据的业务层接口
 * 业务层起到承上启下的作用
 * 在业务层中声明的方法是被控制器层（Controller）调用的，且在实现业务层功能时，需要调用持久层对象中的方法
 * 所以，在参数方面，应该是承上启下的，即：能满足调用和被调用的需求。
 * @author ZLJ
 *
 */
public interface IUserService {
	
	/**
	 * 用户注册
	 * @param user 用户数据
	 * @throws UsernameConflictException 用户名冲突异常
	 * @throws InsertException 插入数据失败异常
	 */
	void reg(User user)
			throws UsernameConflictException,InsertException;
	
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @throws UserNotFoundException 用户名不存在异常
	 * @throws PasswordNotMatchException 密码错误异常
	 */
	User login(String username,String password)
			throws UserNotFoundException,PasswordNotMatchException;
	
	/**
	 * 修改密码
	 * @param uid 用户id
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @throws UserNotFoundException 用户不存在异常
	 * @throws PasswordNotMatchException 密码不匹配异常
	 * @throws UpdateException 修改数据异常
	 */
	void changePassword(
			Integer uid,String oldPassword,String newPassword)
					throws UserNotFoundException,PasswordNotMatchException,UpdateException;
	
	/**
	 * 修改用户资料
	 * @param user 用户数据
	 * @throws UserNotFoundException 用户不存在异常
	 * @throws UpdateException 更新数据异常
	 */
	void changeInfo(User user)
			throws UserNotFoundException,UpdateException;
	
	/**
	 * 修改用户的头像
	 * @param uid 用户id
	 * @param avatar 用户头像路径
	 * @throws UpdateException 更新数据异常
	 * @throws UserNotFoundException 用户数据不存在异常
	 */
	void changeAvatar(Integer uid,String avatar) 
			throws UpdateException,UserNotFoundException;
	
	/**
	 * 通过uid获取用户数据
	 * 
	 * findByUid(uid)---这是查询数据的方法，并不关心查询结果如何使用，用于在别的方法中查询数据，相当于在支持别的业务
	 * getByUid(uid)---这是提供数据的方法，是为了在前端修改用户资料的时候显示出该有的用户数据
	 * @param uid
	 */
	User getByUid(Integer uid);
}
