package cn.zlj.blog.entity;

import java.io.Serializable;
/**
 * 博客标签实体类（也就是用户兴趣）
 * @author ZLJ
 *
 */
public class Tag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer tid;//标签
	private String parent;//标签父编码
	private String code;//标签编码
	private String name;//标签名
	
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Tag [tid=" + tid + ", parent=" + parent + ", code=" + code + ", name=" + name + "]";
	}
	
	
}
