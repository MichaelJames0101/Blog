package cn.zlj.blog.mapper;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.zlj.blog.entity.SiteInfo;

@SpringBootTest
public class SiteInfoMapperTestCase {
	@Autowired
	private SiteInfoMapper siMapper;
	
	@Test
	public void addNewTest() {
		SiteInfo si = new SiteInfo();
		si.setCreator("张李军");
		si.setAdmin("admin");
		si.setAdminPwd("123456");
		si.setVisitNum(0);
		si.setStart(new Date());
		si.setTitle("我的博客站");
		si.setIntroduction("欢迎来到我的博客站，在这里你可以记录发表你的点滴，也可以向我留言哟！");
		Integer rows = siMapper.addNew(si);
		System.err.println("rows: " + rows);
	}
	
	@Test
	public void findSiteInfo() {
		System.err.println(siMapper.findSiteInfo());
	}
}
