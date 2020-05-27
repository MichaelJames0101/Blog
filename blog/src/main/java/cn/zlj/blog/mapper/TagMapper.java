package cn.zlj.blog.mapper;

import java.util.List;

import cn.zlj.blog.entity.Tag;

public interface TagMapper {
	/**
	 * 通过标签父编码查询标签信息列表
	 * @param parent 标签的父编码
	 * @return 返回标签信息的列表
	 */
	List<Tag> findByParent(String parent);
	
	/**
	 * 通过标签编码查询具体的标签信息
	 * @param code 标签编码
	 * @return 返回具体的标签信息
	 */
	Tag findByCode(String code);
}
