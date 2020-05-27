package cn.zlj.blog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zlj.blog.entity.Comment;
import cn.zlj.blog.mapper.CommentMapper;
import cn.zlj.blog.service.ICommentService;
import cn.zlj.blog.service.ex.AccessDeniedException;
import cn.zlj.blog.service.ex.CommentNotFoundException;
import cn.zlj.blog.service.ex.DeleteException;
import cn.zlj.blog.service.ex.InsertException;

@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public void writeComment(Comment comment,Integer uid,String username) {
		//封装uid
		comment.setUid(uid);
		//封装评论用户和评论时间
		comment.setCreatedUser(username);
		comment.setCreatedTime(new Date());
		//执行写评论
		addNewComment(comment);
	}
	
	@Override
	public void deleteByCid(Integer cid, Integer uid)
			throws DeleteException, CommentNotFoundException, AccessDeniedException {
		//根据cid获取评论数据
		Comment data = findByCid(cid);
		//判断评论数据是否存在
		if(data == null) {
			throw new CommentNotFoundException("删除失败！评论数据不存在！可能已经被删除！");
		}
		//判断用户是否匹配---即获取到的用户数据的uid是否于参数中的uid不同
		if(!data.getUid().equals(uid)) {
			throw new AccessDeniedException("删除评论失败！访问权限不足！");
		}
		//执行删除
		deleteByCid(cid);
	}
	
	@Override
	public List<Comment> getListByUid(Integer uid) {
		return findListByUid(uid);
	}

	@Override
	public List<Comment> getListByBid(Integer bid) {
		return findListByBid(bid);
	}

	@Override
	public Comment getByCid(Integer cid) {
		return findByCid(cid);
	}
	
	private void addNewComment(Comment comment) {
		Integer rows = commentMapper.addNewComment(comment);
		if(rows != 1) {
			throw new InsertException("添加评论失败！请联系系统管理员！");
		}
	}
	
	
	
	private void deleteByCid(Integer cid) {
		Integer rows = commentMapper.deleteBycid(cid);
		if(rows != 1) {
			throw new DeleteException("删除数据出现未知异常！请联系系统管理员！");
		}
	}
	
	private List<Comment> findListByUid(Integer uid){
		return commentMapper.findListByUid(uid);
	}
	
	private List<Comment> findListByBid(Integer bid){
		return commentMapper.findListByBid(bid);
	}
	
	private Comment findByCid(Integer cid) {
		return commentMapper.findByCid(cid);
	}

}
