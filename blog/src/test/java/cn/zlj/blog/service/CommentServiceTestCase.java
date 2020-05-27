package cn.zlj.blog.service;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.zlj.blog.entity.Comment;
import cn.zlj.blog.service.ex.ServiceException;

@SpringBootTest
public class CommentServiceTestCase {
	@Autowired
	private ICommentService commentService;
	
	@Test
	public void writeCommentTest() {
		Comment comment = new Comment();
		Integer uid = 10;
		String username = "adi";
		comment.setBid(3);
		comment.setContent("写博客的业务层测试");
		comment.setCreatedTime(new Date());
		commentService.writeComment(comment, uid, username);
		System.err.println("OK.");
	}
	
	@Test
	public void deleteByCidTest() {
		try {
			Integer cid = 5;
			Integer uid = 2;
			commentService.deleteByCid(cid, uid);
			System.err.println("OK.");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void getListByUidTest() {
		Integer uid = 5;
		List<Comment> comments = commentService.getListByUid(uid);
		comments.stream().forEach(System.err::println);
	}
	
	@Test
	public void getListByBidTest() {
		Integer bid = 2;
		List<Comment> comments = commentService.getListByBid(bid);
//		System.err.println("BEGIN:");
//		for(Comment comment : comments) {
//			System.err.println(comment.getCreatedUser());
//			System.err.println(comment.getCreatedTime());
//			System.err.println(comment);
//		}
//		System.err.println("END.");
		comments.stream().forEach(System.err::println);
	}
	
	@Test
	public void getByCidTest() {
		Integer cid = 3;
		Comment comment = commentService.getByCid(cid);
		System.err.println(comment.getCreatedUser());
		System.err.println(comment);
	}
}
