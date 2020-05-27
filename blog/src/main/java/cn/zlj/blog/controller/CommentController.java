package cn.zlj.blog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zlj.blog.entity.Comment;
import cn.zlj.blog.service.ICommentService;
import cn.zlj.blog.util.ResponseResult;

@RestController
@RequestMapping("comments")
public class CommentController extends BaseController {
	@Autowired
	private ICommentService commentService;
	
	@RequestMapping("write")
	public ResponseResult<Void> handleWriteComment(Comment comment,HttpSession session){
		//从session中获取用户ID和用户名
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		//执行添加评论的操作
		commentService.writeComment(comment, uid, username);
		//返回
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@RequestMapping("delete")
	public ResponseResult<Void> handleDelete(Integer cid,HttpSession session){
		//从session中获取uid
		Integer uid = getUidFromSession(session);
		//执行删除操作
		commentService.deleteByCid(cid, uid);
		//返回
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@RequestMapping("user_comment_list")
	public ResponseResult<List<Comment>> handleGetListByUid(HttpSession session){
		Integer uid = getUidFromSession(session);
		List<Comment> data = commentService.getListByUid(uid);
		return new ResponseResult<List<Comment>>(SUCCESS,data);
	}
	
	@RequestMapping("blog_comment_list")
	public ResponseResult<List<Comment>> handleGetListByBid(Integer bid){
		List<Comment> data = commentService.getListByBid(bid);
		return new ResponseResult<List<Comment>>(SUCCESS,data);
	}
}
