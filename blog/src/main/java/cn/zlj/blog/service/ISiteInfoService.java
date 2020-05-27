package cn.zlj.blog.service;

import cn.zlj.blog.entity.SiteInfo;

public interface ISiteInfoService {
	
	/**
	 * 业务层获取网站信息
	 * @return 返回网站信息
	 */
	SiteInfo getSiteInfo();
}
