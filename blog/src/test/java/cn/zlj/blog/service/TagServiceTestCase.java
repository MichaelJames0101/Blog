package cn.zlj.blog.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.zlj.blog.entity.Tag;
import cn.zlj.blog.mapper.TagMapper;

@SpringBootTest
public class TagServiceTestCase {

	@Autowired
	private TagMapper tagMapper;
	
	@Test
	public void getByParent() {
		String  parent = "0000";
		List<Tag> tags = tagMapper.findByParent(parent);
//		tags.stream().forEach(x->System.err.println(x));
		tags.stream().forEach(System.err::println);
	}
	
	@Test
	public void getByCode() {
		String code = "0105";
		Tag tag = tagMapper.findByCode(code);
		System.err.println(tag);
	}
}
