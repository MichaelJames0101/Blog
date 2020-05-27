package cn.zlj.blog.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.zlj.blog.entity.SiteInfo;

@SpringBootTest
public class SiteInfoServiceTestCase {
	@Autowired
	private ISiteInfoService siService;
	
	@Test
	public void findSiteInfo() {
		SiteInfo si = siService.getSiteInfo();
		System.err.println("SiteInfo: " + si);
	}
}
