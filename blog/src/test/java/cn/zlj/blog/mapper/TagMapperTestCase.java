package cn.zlj.blog.mapper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.zlj.blog.entity.Tag;

@SpringBootTest
public class TagMapperTestCase {
	@Autowired
	private TagMapper tMapper;
	
	@Test
	public void findByParentTest() {
		String parent = "0000";
		List<Tag> tag = tMapper.findByParent(parent);
		tag.stream().forEach(x->System.err.println(x));
	}
	
	@Test
	public void findByCodeTest() {
		String code = "0101";
		Tag tag = tMapper.findByCode(code);
		System.err.println(tag);
	}
}
