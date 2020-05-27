package cn.zlj.blog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zlj.blog.entity.Blog;
import cn.zlj.blog.mapper.BlogMapper;
import cn.zlj.blog.mapper.CommentMapper;
import cn.zlj.blog.service.IBlogService;
import cn.zlj.blog.service.ex.AccessDeniedException;
import cn.zlj.blog.service.ex.BlogNotFoundException;
import cn.zlj.blog.service.ex.DeleteException;
import cn.zlj.blog.service.ex.InsertException;
import cn.zlj.blog.service.ex.UpdateException;
/**
 * 写博客的业务层实现类
 * @author ZLJ
 *
 */
@Service
public class BlogServiceImpl implements IBlogService {

	@Autowired
	private BlogMapper blogMapper;
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public void WriteBlog(Blog blog, Integer uid, String username)
			throws InsertException {
		//code,title,content这几项数据通过前端写入到数据库
		//封装用户ID
		blog.setUid(uid);
		
		//封装4项日志
		//获取当前时间---这就是博客创建时间
		Date now = new Date();
		
		//这就是博客作者（创建者）
		blog.setCreatedUser(username);
		blog.setCreatedTime(now);
		blog.setModifiedUser(username);
		blog.setModifiedTime(now);
		
		//执行写博客
		addNew(blog);
	}
	
	@Override
	public void deleteBlog(Integer bid, Integer uid)
			throws DeleteException, BlogNotFoundException, AccessDeniedException {
		//根据bid获取博客数据信息
		Blog data = findByBid(bid);
		//判断博客数据是否存在
		if(data == null) {
			throw new BlogNotFoundException("删除博客失败！博客数据不存在！或许已经被删除！");
		}
		//判断用户是否匹配---即获取到的用户数据的uid是否于参数中的uid不同
		if(!data.getUid().equals(uid)) {
			throw new AccessDeniedException("删除博客失败！访问权限不足！");
		}
		//执行删除
		deleteBlog(bid);
	}
	
	@Override
	public void UpdateBlog(Blog blog, Integer uid, String username)
			throws BlogNotFoundException, UpdateException {
		//code,title,content这几项数据通过前端写入到数据库
				
		//获取当前时间---这就是博客修改时间
		Date now = new Date();
				
		//执行修改博客
		updateBlog(
				blog.getBid(), uid, 
				blog.getCode(), blog.getTitle(), 
				blog.getContent(), username, 
				now);
	}
	
	@Override
	public void UpdateNum(Integer bid, Integer visitNum)
			throws BlogNotFoundException, UpdateException {
		//visitNum和commentNum都是哪来的？
		//---visitNum(访问量)：在拦截器中定义了一个变量专门用来存放访问到该路径的次数，便是该博客的浏览量，取出来存放到数据库即可！
		//---commentNum(评论数)：在用户评论模块有getCountByBid方法可以获得一篇博客下面的评论总数
		//多久更新一次？
		//---如果不设置更新时间，那当数据量增加的情况下会出现十分频繁的数据库交互，这会给数据库带来很大的压力。
		//---暂且设置成一天一次吧！---具体设置得在控制层设置多久调用一次该方法
		Integer commentNum = commentMapper.getCountByBid(bid);
		//执行数据更新
		updateNum(bid, visitNum, commentNum);
		
	}
	
	@Override
	public Blog getByBid(Integer bid) {
		return findByBid(bid);
	}

	@Override
	public List<Blog> getListByCode(String code) {
		return findListByCode(code);
	}

	@Override
	public List<Blog> getListByUid(Integer uid) {
		return findListByUid(uid);
	}
	
	
	
	private void addNew(Blog blog) {
		Integer rows = blogMapper.addNew(blog);
		if(rows != 1) {
			throw new InsertException("写博客失败！请联系系统管理员！");
		}
	}

	private void updateBlog(
			Integer bid,Integer uid,
			String code,String title,
			String content,String modifiedUser,
			Date modifiedTime) {
		Integer rows = blogMapper.updateBlog(bid, uid, code, title, content, modifiedUser, modifiedTime);
		if(rows != 1) {
			throw new UpdateException("修改博客失败！请联系系统管理员！");
		}
	}
	
	private void updateNum(Integer bid, Integer visitNum, Integer commentNum) {
		Integer rows = blogMapper.updateNum(bid, visitNum, commentNum);
		if(rows != 1) {
			throw new UpdateException("修改博客失败！请联系系统管理员！");
		}
	}

	private void deleteBlog(Integer bid) {
		Integer rows = blogMapper.deleteBlog(bid);
		if(rows != null) {
			throw new DeleteException("删除数据出现异常！请联系系统管理员！");
		}
	}
	
	private Blog findByBid(Integer bid) {
		return blogMapper.findByBid(bid);
	}
	
	private List<Blog> findListByCode(String code) {
		return blogMapper.findListByCode(code);
	}
	
	private List<Blog> findListByUid(Integer uid) {
		return blogMapper.findListByUid(uid);
	}

}
