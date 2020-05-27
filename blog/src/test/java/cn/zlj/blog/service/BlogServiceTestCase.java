package cn.zlj.blog.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.zlj.blog.entity.Blog;
import cn.zlj.blog.service.ex.ServiceException;

@SpringBootTest
public class BlogServiceTestCase {
	@Autowired
	private IBlogService blogService;
	
	@Test
	public void addNewTest() {
		Blog blog = new Blog();
		Integer uid = 10;
		String username = "adi";
		blog.setCode("1010");
		blog.setTitle("业务层测试写博客的标题");
		blog.setContent("你好！这是业务层写博客的博客内容。");
		blogService.WriteBlog(blog, uid, username);
		System.err.println("OK.");
	}
	
	@Test
	public void updateBlogTest() {
		Blog blog = new Blog();
		Integer uid = 10;
		String username = "adi";
		blog.setBid(3);
		blog.setCode("1001");
		blog.setTitle("业务层测试修改博客的标题");
		blog.setContent("你好！这是测试业务层修改博客的博客内容。");
		blogService.UpdateBlog(blog, uid, username);
		System.err.println("OK.");
	}
	
	@Test
	public void deleteBlogTest() {
		try {
			Integer bid = 3;
			Integer uid = 10;
			blogService.deleteBlog(bid, uid);
			System.err.println("ok.");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void getByBidTest() {
		Integer bid = 3;
		Blog blog = blogService.getByBid(bid);
		System.err.println("blog: "+blog);
	}
	
	@Test
	public void getListByCode() {
		String code = "1010";
		List<Blog> list = blogService.getListByCode(code);
		list.stream().forEach(x->System.err.println(x));
	}
	
	@Test
	public void getListByUid() {
		Integer uid = 10;
		List<Blog> list = blogService.getListByUid(uid);
		list.stream().forEach(x->System.err.println(x));
	}
	
	@Test
	public void updateNumTest() {
		try {
			Integer bid = 5;
			Integer visitNum = 10;
			blogService.UpdateNum(bid, visitNum);
			System.err.println("OK.");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
}
