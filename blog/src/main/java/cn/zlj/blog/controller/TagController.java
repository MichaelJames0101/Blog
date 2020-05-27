package cn.zlj.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zlj.blog.entity.Tag;
import cn.zlj.blog.service.ITagService;
import cn.zlj.blog.util.ResponseResult;

/**
 * 获取标签信息的控制层
 * @author ZLJ
 *
 */

@RestController
@RequestMapping("tag")
public class TagController extends BaseController {
	@Autowired
	private ITagService tagService;
	
	@RequestMapping("/")
	public ResponseResult<List<Tag>> getListByParent(String parent){
		List<Tag> tags = tagService.getByParent(parent);
		return new ResponseResult<List<Tag>>(SUCCESS,tags);
	}
	
	@RequestMapping("{code}")
	public ResponseResult<Tag> getByCode(String code){
		Tag tag = tagService.getByCode(code);
		return new ResponseResult<Tag>(SUCCESS,tag);
	}
	
}
