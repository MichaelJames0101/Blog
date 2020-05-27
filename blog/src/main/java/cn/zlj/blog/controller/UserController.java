package cn.zlj.blog.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.zlj.blog.controller.ex.FileContentTypeException;
import cn.zlj.blog.controller.ex.FileEmptyException;
import cn.zlj.blog.controller.ex.FileSizeException;
import cn.zlj.blog.controller.ex.FileUploadIOException;
import cn.zlj.blog.entity.User;
import cn.zlj.blog.service.IUserService;
import cn.zlj.blog.util.IpUtils;
import cn.zlj.blog.util.ResponseResult;

/**
 * 处理用户数据请求的控制器类
 * @author ZLJ
 *
 */

/*
 * 由于控制器类中只需要关注“操作正确”，且操作正确时可以不向客户端反馈message，所以，在响应结果的类型中添加新的构造方法用于快速创建“操作正确”时的响应结果
 */

//在Spring中@RestController的作用等同于@Controller + @ResponseBody
//@Controller在一个类上添加@Controller注解，表明了这个类是一个控制器类。
//@ResponseBody表示方法的返回值直接以指定的格式写入Http response body中，而不是解析为跳转路径。
@RestController
@RequestMapping("users")
public class UserController extends BaseController {
	
	@Autowired
	private IUserService service;
	
	/**
	 * 存储上传的文件的文件夹名称
	 */
	public static final String UPLOAD_DIR = "upload";
	
	/**
	 * 上传文件时允许上传的最大大小
	 */
	public static final long UPLOAD_MAX_SIZE 
		= 1 * 1024 * 1024;
	
	/**
	 * 允许上传的文件类型的集合
	 */
	public static final List<String> UPLOAD_CONTENT_TYPES 
		= new ArrayList<String>();

	/**
	 * 添加允许上传的文件类型
	 */
	static {
		UPLOAD_CONTENT_TYPES.add("image/jpeg");
		UPLOAD_CONTENT_TYPES.add("image/png");
		UPLOAD_CONTENT_TYPES.add("image/gif");
		UPLOAD_CONTENT_TYPES.add("image/bmp");
	}
	
	
	/**
	 * 处理用户注册的控制器层方法
	 * @param user 用户数据
	 * @return 返回ResponseResult类型对象--返回给前端的
	 * 
	 * test:浏览器输入--http://localhost:8080/users/reg?username=rest&password=123456
	 */
	//@RequestMapping(value = "reg",method = RequestMethod.GET)
	@PostMapping
	public ResponseResult<Void> handleReg(User user){
		service.reg(user);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	/**
	 * 处理用户登录的控制层方法
	 * @param username 用户名--前端获取到的
	 * @param password 用户密码--前端获取到的
	 * @return 登录完成后返回用户数据供后续业务使用
	 * 
	 * test:浏览器输入：http://localhost:8080/users/login?username=xiaoming&password=123456
	 */
	@GetMapping("login")
	public ResponseResult<User> handleLogin(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpSession session
			){
		User user = service.login(username, password);
		/*
		 * 前面根据uid查询用户数据，那这uid哪来的？？？
		 * ---是在用户登录后存放于HttpSession中的
		 * ---即在控制层的handleLogin中添加HttpSession类型参数
		 * ---由于登录时用到了findByUsername这里修改数据时用到了findByUid，可以判断uid和username可能是常用数据
		 * ---那就将这两个保存在session中呗！常用常取便是了！
		 */
		session.setAttribute("uid", user.getUid());
		session.setAttribute("username", user.getUsername());
		return new ResponseResult<User>(SUCCESS,user);
	}
	
	/**
	 * 修改密码
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @param session
	 * @return 返回操作成功的ResponseResult对象即可
	 * 
	 * test---http://localhost:8080/users/change_password?old_password=12345678&new_password=123456
	 */
	@RequestMapping("change_password")
	public ResponseResult<Void> changePassword(
			@RequestParam("old_password") String oldPassword,
			@RequestParam("new_password") String newPassword,
			HttpSession session){
		Integer uid = getUidFromSession(session);
		service.changePassword(uid, oldPassword, newPassword);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	/**
	 * 修改用户资料
	 * @param user 用户数据
	 * @param session
	 * @return 返回操作成功的ResponseResult对象
	 * 
	 * test---http://localhost:8080/users/change_info?gender=1&phone=12345678&email=rest@users.com
	 */
	@PostMapping("change_info")
	public ResponseResult<Void> changeInfo(User user,HttpSession session){
		Integer uid = getUidFromSession(session);
		user.setUid(uid);
		service.changeInfo(user);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	/**
	 * 上传头像
	 * @param request 请求
	 * @param avatar 头像
	 * @return 返回操作成功的ResponseResult对象
	 */
	@PostMapping("change_avatar")
	public ResponseResult<String> changeAvatar(
			HttpServletRequest request,
			@RequestParam("avatar") MultipartFile avatar){
		//判断上传的文件是否为空：avatar.isEmpty()
		if(avatar.isEmpty()) {
			//是，抛出异常：FileEmptyException
			throw new FileEmptyException("上传文件失败！没有选择文件，或上传的文件为空！");
		}
		
		//判断文件类型是否不在允许的范围内：avatar.getContentType() / list.contains(contentType)
		//允许哪些类型的文件上传呢？--这里可以弄个集合专门存放允许上传的文件类型，这些都可用静态变量来表示
		String contentType = avatar.getContentType();
		if(!UPLOAD_CONTENT_TYPES.contains(contentType)) {
			// 是：抛出异常：FileContentTypeException
			throw new FileContentTypeException("上传文件失败！不支持上传" + contentType + "类型的文件！");
		}
		
		// 判断文件大小是否超出了限制：avatar.getSize()
		long size = avatar.getSize();
		if(size > UPLOAD_MAX_SIZE) {
			// 是：抛出异常：FileSizeException
			throw new FileSizeException("上传文件失败！尝试上传的文件大小超出了限制！仅允许上传不超过" + UPLOAD_MAX_SIZE/1024/1024 + "M的文件！");
		}
		
		//确定上传文件夹
		String parentPath = request.getServletContext().getRealPath(UPLOAD_DIR);
		File parent = new File(parentPath);
		if(!parent.exists()) {
			parent.mkdirs();
		}
		
		//确定上传的文件名
		String orginalFilename = avatar.getOriginalFilename();
		String suffix = "";
		int beginIndex = orginalFilename.lastIndexOf(".");
		if(beginIndex != -1) {
			suffix = orginalFilename.substring(beginIndex);
		}
		String filename = UUID.randomUUID() + suffix;
		
		//执行存储
		File dest = new File(parent,filename);
		try {
			avatar.transferTo(dest);
		} catch (IOException e) {
			throw new FileUploadIOException(
					"上传文件失败！出现读写错误，请联系系统管理员，或稍后再次尝试！");
		}
		
		// 将上传的文件路径存储到数据库：service.changeAvatar(uid, avatar)
		Integer uid = getUidFromSession(request.getSession());
		String avatarUrl = "/" +UPLOAD_DIR+ "/" + filename;
		service.changeAvatar(uid, avatarUrl);
		
		//返回
		ResponseResult<String> rr = new ResponseResult<String>();
		rr.setState(SUCCESS);
		rr.setData(avatarUrl);
		
		return rr;
	}
	
	/**
	 * 获取用户数据
	 * @param session
	 * @return 返回用户数据
	 * 
	 * test---http://localhost:8080/users/get_info
	 */
	@RequestMapping("get_info")
	public ResponseResult<User> getInfo(HttpSession session){
		Integer uid = getUidFromSession(session);
		User user = service.getByUid(uid);
		return new ResponseResult<User>(SUCCESS,user);
	}
	
	/**
	 * 获取用户的IP信息
	 * @param request 用户请求
	 * @return 返回经过工具类处理过后获取的用户IP
	 * 
	 * test---http://localhost:8080/users/get_info
	 */
	@GetMapping("get_ip")
	public ResponseResult<String> getIp(HttpServletRequest request) {
//		UserIP userIP = new UserIP();
//		userIP.setUid(getUidFromSession(session));
//		userIP.setIp(IpUtils.getIpAddr(request));
//		return new ResponseResult<UserIP>(SUCCESS,userIP);
		String ip = IpUtils.getIpAddr(request);
		return new ResponseResult<>(SUCCESS,ip);
	}
}
