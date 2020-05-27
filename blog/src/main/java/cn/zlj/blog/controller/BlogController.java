package cn.zlj.blog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zlj.blog.entity.Blog;
import cn.zlj.blog.service.IBlogService;
import cn.zlj.blog.util.ResponseResult;

@RestController
@RequestMapping("blogs")
public class BlogController extends BaseController {
	@Autowired
	private IBlogService blogService;
	
	@RequestMapping("write")
	public ResponseResult<Void> handleWriteBlog(Blog blog,HttpSession session) {
		//从session中获取用户ID和用户名
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		//执行写博客操作
		blogService.WriteBlog(blog, uid, username);
		//返回
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@RequestMapping("delete")
	public ResponseResult<Void> handleDeleteBlog(Integer bid,HttpSession session){
		//从session中获取uid
		Integer uid = getUidFromSession(session);
		//执行删除操作
		blogService.deleteBlog(bid, uid);
		//返回
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@RequestMapping("update_blog")
	public ResponseResult<Void> handleUpdateBlog(Blog blog,HttpSession session){
		//从session中获取用户ID和用户名
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		//执行修改博客操作
		blogService.UpdateBlog(blog, uid, username);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@RequestMapping("update_num")
	public ResponseResult<Void> handleUpdateNum(Integer bid,Integer visitNum){
		//执行更新操作
		blogService.UpdateNum(bid, visitNum);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@RequestMapping("user_blog_list")
	public ResponseResult<List<Blog>> handleGetListByUid(HttpSession session){
		Integer uid = getUidFromSession(session);
		List<Blog> data = blogService.getListByUid(uid);
		return new ResponseResult<List<Blog>>(SUCCESS,data);
	}
	
	@RequestMapping("code_blog_list")
	public ResponseResult<List<Blog>> handleGetListByCode(String code){
		List<Blog> data = blogService.getListByCode(code);
		return new ResponseResult<List<Blog>>(SUCCESS,data);
	}
}
