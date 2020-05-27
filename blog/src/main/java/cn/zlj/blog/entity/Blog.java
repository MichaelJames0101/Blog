package cn.zlj.blog.entity;

import java.io.Serializable;
/**
 * 博客信息实体类
 * @author ZLJ
 *
 */
public class Blog extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer bid;//博客id
	private Integer uid;//用户id
	private String code;//标签编码
	private String title;//博客标题
	private String content;//博客内容
	private Integer visitNum;//博客访问量
	private Integer commentNum;//博客评论量
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getVisitNum() {
		return visitNum;
	}
	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}
	public Integer getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}
	@Override
	public String toString() {
		return "Blog [bid=" + bid + ", uid=" + uid + ", code=" + code + ", title=" + title + ", content=" + content
				+ ", visitNum=" + visitNum + ", commentNum=" + commentNum + "]";
	}
	
}
