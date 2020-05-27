package cn.zlj.blog.entity;

import java.io.Serializable;
/**
 * 评论的实体类
 * @author ZLJ
 *
 */
public class Comment extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer cid;//评论id
	private Integer uid;//用户id
	private Integer bid;//博客id
	private String content;//评论内容
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Comment [cid=" + cid + ", uid=" + uid + ", bid=" + bid + ", content=" + content + "]";
	}
	
	

}
