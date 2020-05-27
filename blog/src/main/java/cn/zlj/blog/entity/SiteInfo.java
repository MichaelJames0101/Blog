package cn.zlj.blog.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 网站信息的实体类
 * @author ZLJ
 *
 */
public class SiteInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String creator;
	private String admin;
	private String adminPwd;
	private int visitNum;
	private Date start;
	private String title;
	private String introduction;
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getAdminPwd() {
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	public int getVisitNum() {
		return visitNum;
	}
	public void setVisitNum(int visitNum) {
		this.visitNum = visitNum;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	@Override
	public String toString() {
		return "SiteInfo [creator=" + creator + ", admin=" + admin + ", adminPwd=" + adminPwd + ", visitNum=" + visitNum
				+ ", start=" + start + ", title=" + title + ", introduction=" + introduction + "]";
	}
	
}
