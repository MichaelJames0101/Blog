package cn.zlj.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zlj.blog.entity.SiteInfo;
import cn.zlj.blog.mapper.SiteInfoMapper;
import cn.zlj.blog.service.ISiteInfoService;
import cn.zlj.blog.service.ex.SiteInfoNotFoundException;
@Service
public class SiteInfoServiceImpl implements ISiteInfoService {
	
	@Autowired
	private SiteInfoMapper siMapper;
	
	@Override
	public SiteInfo getSiteInfo() {
		//根据findSiteInfo（）方法获取网站信息
		SiteInfo si = findSiteInfo();
		//判断网站信息是否存在
		if(si == null) {
			throw new SiteInfoNotFoundException("查询信息失败！网站信息不存在！");
		}
		//返回SiteInfo数据信息
		return si;
	}
	
	
	private SiteInfo findSiteInfo() {
		return siMapper.findSiteInfo();
	}

}
