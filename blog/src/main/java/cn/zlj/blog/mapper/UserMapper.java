package cn.zlj.blog.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.zlj.blog.entity.User;
/**
 * 灵魂自问：为什么mybatis的mapper没有实现类？
 * ---动态代理生成了接口的代理对象用于调用，集成于spring的就是以MapperFactoryBean的方式来管理的
 * 1.sqlSession.getMapper(UserMapper.class);
 * 2.MapperProxyFactory
 * protected T newInstance(MapperProxy<T> mapperProxy) {
 * 		return Proxy.newProxyInstance(this.mapperInterface.getClassLoader(), new Class[]{this.mapperInterface}, mapperProxy);
 * }
 * 3.mapperProxy
 * invoke(Object proxy, Method method, Object[] args) 
 * 	return method.invoke(this, args);
 * 	return mapperMethod.execute(this.sqlSession, args);
 */
public interface UserMapper {
	/**
	 * 添加新用户（用户注册）的持久层抽象方法
	 * @param user 用户数据
	 * @return 返回受影响的行数，便于测试和给予反馈
	 */
	Integer addNew(User user);
	
	/**
	 * 修改用户密码
	 * @param uid 用户id
	 * @param password 用户密码--用户输入的新密码
	 * @param modifiedUser 修改者
	 * @param modifiedTime 修改时间
	 * @return 返回受影响的行数
	 */
	Integer updatePassword(
			@Param("uid") Integer uid,
			@Param("password") String password,
			@Param("modifiedUser") String modifiedUser,
			@Param("modifiedTime") Date modifiedTime);
	
	/**
	 * 修改用户资料
	 * @param user 用户数据
	 * @return 返回受影响的行数
	 */
	Integer updateInfo(User user);

	/**
	 * 上传头像
	 * @param uid 用户id
	 * @param avatar 头像的路径
	 * @param modifiedUser 修改人
	 * @param modifiedTime 修改时间
	 * @return 返回受影响的行数
	 */
	Integer updateAvatar(
			@Param("uid") Integer uid,
			@Param("avatar") String avatar,
			@Param("modifiedUser") String modifiedUser,
			@Param("modifiedTime") Date modifiedTime);
	
	/**
	 * 通过uid查询用户数据
	 * @param uid 用户id
	 * @return 返回查询得到的数据
	 */
	User findByUid(Integer uid);
	
	/**
	 * 通过用户名查询用户数据
	 * @param username 用户名
	 * @return 返回用户数据---用于后续满足用户名不可重复性
	 */
	User findByUsername(String username);
	
	
}
