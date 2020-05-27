package cn.zlj.blog.mapper;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.zlj.blog.entity.Blog;

@SpringBootTest
public class BlogMapperTestCase {
	
	@Autowired
	private BlogMapper blogMapper;
	
	@Test
	public void addNewTest() {
		Blog blog = new Blog();
		blog.setUid(5);
		blog.setCode("0101");
		blog.setTitle("写博客的持久层测试标题");
		blog.setContent("你好，我是写博客持久层测试时的博客内容！");
		blog.setVisitNum(0);
		blog.setCommentNum(0);
		Integer rows = blogMapper.addNew(blog);
		System.err.println("rows: "+rows);
	}
	
	@Test
	public void updateBlogTest() {
		Integer bid = 1;
		Integer uid = 5;
		String code = "1002";
		String title = "修改博客标题的持久层测试";
		String content = "修改博客内容的持久层测试";
		String modifiedUser = "qianliu";
		Date now = new Date();
		Integer rows = blogMapper.updateBlog(bid, uid, code, title, content, modifiedUser, now);
		System.err.println("rows: "+rows);
	}
	
	@Test
	public void deleteBlogTest() {
		Integer bid = 1;
		Integer rows = blogMapper.deleteBlog(bid);
		System.err.println("rows: "+rows);
	}
	
	@Test
	public void findByBidTest() {
		Integer bid = 3;
		Blog blog = blogMapper.findByBid(bid);
		System.err.println("blog: "+blog);
	}
	
	@Test
	public void findListByCode() {
		String code = "1001";
		List<Blog> list = blogMapper.findListByCode(code);
		list.stream().forEach(x->System.err.println(x));
	}
	
	@Test
	public void findListByUid() {
		Integer uid = 5;
		List<Blog> list = blogMapper.findListByUid(uid);
		list.stream().forEach(x->System.err.println(x));
	}
	
	@Test
	public void updateNumTest() {
		Integer bid = 2;
		Integer visitNum = 1;
		Integer commentNum = 1;
		Integer rows = blogMapper.updateNum(bid,visitNum, commentNum);
		System.err.println("rows: "+rows);
	}
}
