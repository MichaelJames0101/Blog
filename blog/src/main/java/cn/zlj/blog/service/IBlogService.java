package cn.zlj.blog.service;

import java.util.List;

import cn.zlj.blog.entity.Blog;
import cn.zlj.blog.service.ex.AccessDeniedException;
import cn.zlj.blog.service.ex.BlogNotFoundException;
import cn.zlj.blog.service.ex.DeleteException;
import cn.zlj.blog.service.ex.InsertException;
import cn.zlj.blog.service.ex.UpdateException;
import cn.zlj.blog.service.ex.UserNotFoundException;

public interface IBlogService {
	
	/**
	 * 写博客业务层抽象方法
	 * @param blog 博客数据
	 * @throws UserNotFoundException 用户数据不存在异常
	 * @throws InsertException 插入数据异常
	 */
	void WriteBlog(Blog blog,Integer uid,String username)
			throws InsertException;
	
	/**
	 * 删除博客
	 * @param bid 博客id
	 * @param uid 用户id
	 * @throws DeleteException 删除数据异常
	 * @throws BlogNotFoundException 博客不存在异常
	 * @throws AccessDeniedException 数据库访问异常
	 */
	void deleteBlog(Integer bid,Integer uid) 
			throws DeleteException,BlogNotFoundException,AccessDeniedException;
	
	/**
	 * 修改博客业务层抽象方法
	 * @param blog 博客数据
	 * @param uid 用户ID
	 * @param username 用户名
	 * @throws BlogNotFoundException 博客不存在异常
	 * @throws UpdateException 更新数据异常
	 */
	void UpdateBlog(Blog blog,Integer uid,String username)
			throws BlogNotFoundException,UpdateException;
	
	/**
	 * 更新博客的浏览数和评论数
	 * @param bid 博客id
	 * @param visitNum 浏览数
	 * @param commentNum 评论数
	 * @throws BlogNotFoundException 博客不存在异常
	 * @throws UpdateException 更新数据异常
	 */
	void UpdateNum(Integer bid,Integer visitNum
			) throws BlogNotFoundException,UpdateException;
	
	/**
	 * 通过博客id查询博客信息
	 * @param bid
	 * @return
	 */
	Blog getByBid(Integer bid);
	
	/**
	 * 通过标签号查询同一标签的博客信息列表
	 * @param code
	 * @return
	 */
	List<Blog> getListByCode(String code);
	
	/**
	 * 通过用户id查询同一用户的博客信息列表
	 * @param uid
	 * @return
	 */
	List<Blog> getListByUid(Integer uid);
	
}
