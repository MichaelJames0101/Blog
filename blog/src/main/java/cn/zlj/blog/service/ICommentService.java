package cn.zlj.blog.service;

import java.util.List;

import cn.zlj.blog.entity.Comment;
import cn.zlj.blog.service.ex.AccessDeniedException;
import cn.zlj.blog.service.ex.CommentNotFoundException;
import cn.zlj.blog.service.ex.DeleteException;
import cn.zlj.blog.service.ex.InsertException;

public interface ICommentService {
	
	/**
	 * 写博客业务层接口中抽象方法
	 * @param comment 评论信息
	 */
	/**
	 * 写博客业务层接口中抽象方法
	 * @param comment
	 * @param uid
	 * @param username
	 * @throws InsertException
	 */
	void writeComment(Comment comment,Integer uid,String username)
			throws InsertException;
	
	/**
	 * 根据评论id删除评论
	 * @param cid 评论id
	 * @param uid 用户id --- 用于判断是否是当前评论的所有者在执行操作
	 * @throws DeleteException 删除数据异常
	 * @throws CommentNotFoundException 评论不存在异常
	 * @throws AccessDeniedException 数据库访问异常
	 */
	void deleteByCid(Integer cid,Integer uid)
			throws DeleteException,CommentNotFoundException,AccessDeniedException;
	
	/**
	 * 获取某用户发表过的评论信息列表
	 * @param uid 用户id
	 * @return 返回评论信息的列表
	 */
	List<Comment> getListByUid(Integer uid);
	
	/**
	 * 获取某篇博客下的评论信息列表
	 * @param bid 博客id
	 * @return 返回评论信息的列表
	 */
	List<Comment> getListByBid(Integer bid);
	
	/**
	 * 根据id查询信息
	 * @param cid
	 * @return
	 */
	Comment getByCid(Integer cid);
}
