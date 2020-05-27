package cn.zlj.blog.mapper;

import java.util.List;

import cn.zlj.blog.entity.Comment;

public interface CommentMapper {
	
	/**
	 * 写评论
	 * @param comment 评论信息
	 * @return 返回受影响的行数
	 */
	Integer addNewComment(Comment comment);
	
	/**
	 * 根据评论id（cid）删除评论
	 * @param cid
	 */
	Integer deleteBycid(Integer cid);
	
	/**
	 * 根据博客id获取该博客下的评论总数
	 * @return
	 */
	Integer getCountByBid(Integer bid);
	
	/**
	 * 根据用户id获取该用户发表的评论列表
	 * @param uid
	 * @return
	 */
	List<Comment> findListByUid(Integer uid);
	
	/**
	 * 根据博客id获取该博客的评论列表
	 * @param bid
	 * @return
	 */
	List<Comment> findListByBid(Integer bid);
	
	/**
	 * 根据评论id获取具体评论数据
	 * @param cid
	 * @return
	 */
	Comment findByCid(Integer cid);
}
