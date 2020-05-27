package cn.zlj.blog.mapper;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.zlj.blog.entity.Comment;

@SpringBootTest
public class CommentMapperTestCase {
	@Autowired
	private CommentMapper commentMapper;
	
	@Test
	public void addNewCommentTest() {
		Comment comment = new Comment();
		comment.setUid(2);
		comment.setBid(4);
		comment.setContent("写评论的持久层测试！");
		Integer rows = commentMapper.addNewComment(comment);
		System.err.println("rows: "+rows);
	}
	
	@Test
	public void deleteByCidTest() {
		Integer cid = 2;
		commentMapper.deleteBycid(cid);
		System.err.println("OK.");
	}
	
	@Test
	public void getCountByBidTest() {
		Integer bid = 3;
		Integer count = commentMapper.getCountByBid(bid);
		System.err.println("count: "+count);
	}
	
	@Test
	public void findListByUidTest() {
		Integer uid = 5;
		List<Comment> comments = commentMapper.findListByUid(uid);
		comments.stream().forEach(System.err::println);
	}
	
	@Test
	public void findListByBidTest() {
		Integer bid = 2;
		List<Comment> comments = commentMapper.findListByBid(bid);
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
	public void findByCidTest() {
		Integer cid = 3;
		Comment comment = commentMapper.findByCid(cid);
		System.err.println(comment.getCreatedUser());
		System.err.println(comment);
	}
}
