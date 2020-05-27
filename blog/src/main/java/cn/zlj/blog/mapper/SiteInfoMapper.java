package cn.zlj.blog.mapper;

import cn.zlj.blog.entity.SiteInfo;

public interface SiteInfoMapper {
	
	/**
	 * 设置网站信息
	 * @param siteinfo 网站信息
	 */
	Integer addNew(SiteInfo siteinfo);
	
	/**
	 * 查询网站信息
	 * @return 返回网站信息
	 */
	SiteInfo findSiteInfo();
}
