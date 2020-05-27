package cn.zlj.blog.service;

import java.util.List;

import cn.zlj.blog.entity.Tag;
/**
 * 获取标签信息的业务层接口
 * @author ZLJ
 *
 */
public interface ITagService {
	
	/**
	 * 通过标签父编码查询标签信息列表
	 * @param parent 标签的父编码
	 * @return 返回标签信息的列表
	 */
	List<Tag> getByParent(String parent);
	
	/**
	 * 通过标签编码查询具体的标签信息
	 * @param code 标签编码
	 * @return 返回具体的标签信息
	 */
	Tag getByCode(String code);
}
