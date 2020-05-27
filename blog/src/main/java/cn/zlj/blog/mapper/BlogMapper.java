package cn.zlj.blog.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zlj.blog.entity.Blog;

public interface BlogMapper {
	
	/**
	 * 写博客的持久层抽象方法
	 * @param blog 博客信息
	 */
	Integer addNew(Blog blog);
	
	/**
	 * 删除博客
	 * @param bid
	 * @return
	 */
	Integer deleteBlog(Integer bid);
	
	/**
	 * 修改博客的持久层抽象方法
	 * @param bid 博客ID
	 * @param uid 用户ID
	 * @param code 标签
	 * @param title 标题
	 * @param content 内容
	 * @param modifiedUser 修改者
	 * @param now 修改时间
	 * @return 返回受影响的行数
	 */
	Integer updateBlog(
			@Param("bid") Integer bid,
			@Param("uid") Integer uid,
			@Param("code") String code,
			@Param("title") String title,
			@Param("content") String content,
			@Param("modifiedUser") String modifiedUser,
			@Param("modifiedTime") Date modifiedTime);
	
	/**
	 * 更新博客的浏览数和评论数
	 * ---这个设计就放在后台自动更新即可，而且设置一段时间才更新一次，避免数据库交互次数过多增加负担
	 * @param bid 博客id
	 * @param visitNum 浏览数
	 * @param commentNum 评论数
	 * @return 返回受影响的行数
	 */
	Integer updateNum(
			@Param("bid") Integer bid,
			@Param("visitNum") Integer visitNum,
			@Param("commentNum") Integer commentNum);
	
	/**
	 * 通过博客id查询博客信息
	 * @param bid
	 * @return
	 */
	Blog findByBid(Integer bid);
	
	/**
	 * 通过标签号查询同一标签的博客信息列表
	 * @param code
	 * @return
	 */
	List<Blog> findListByCode(String code);
	
	/**
	 * 通过用户id查询同一用户的博客信息列表
	 * @param uid
	 * @return
	 */
	List<Blog> findListByUid(Integer uid);
}
