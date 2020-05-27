package cn.zlj.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zlj.blog.entity.SiteInfo;
import cn.zlj.blog.service.ISiteInfoService;
import cn.zlj.blog.util.ResponseResult;

@RestController
@RequestMapping("siteInfo")
public class SiteInfoController extends BaseController {
	@Autowired
	private ISiteInfoService siService;
	
	@RequestMapping("show_info")
	public ResponseResult<SiteInfo> showInfo(){
		//获取网站信息
		SiteInfo si = siService.getSiteInfo();
		//返回
		return new ResponseResult<SiteInfo>(SUCCESS,si);
	}
}
