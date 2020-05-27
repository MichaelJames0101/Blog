package cn.zlj.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zlj.blog.entity.Tag;
import cn.zlj.blog.mapper.TagMapper;
import cn.zlj.blog.service.ITagService;
/**
 * 获取标签信息的业务层实现类
 * @author ZLJ
 *
 */
@Service
public class TagServiceImpl implements ITagService {
	
	@Autowired
	private TagMapper tagMapper;

	@Override
	public List<Tag> getByParent(String parent) {
		//通过findByParent()获取信息列表
		//返回给控制层查询得到的信息
		return findByParent(parent);
	}

	@Override
	public Tag getByCode(String code) {
		//通过findByCode()获取具体的标签信息
		//返回给控制层查询得到的信息
		return findByCode(code);
	}
	
	
	/**
	 * 调用持久层方法查询获得标签信息列表
	 * @param parent
	 * @return
	 */
	private List<Tag> findByParent(String parent){
		return tagMapper.findByParent(parent);
	}
	
	/**
	 * 调用持久层方法查询获得具体的标签信息
	 * @param code
	 * @return
	 */
	private Tag findByCode(String code) {
		return tagMapper.findByCode(code);
	}
}
