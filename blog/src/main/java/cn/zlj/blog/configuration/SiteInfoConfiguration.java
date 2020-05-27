package cn.zlj.blog.configuration;

import java.util.Date;

public class SiteInfoConfiguration {
	public String creator;
	public String admin;
	public String adminPwd;
	public int visitNum;
	public Date start;
	public String title;
	public String introduction;
	public SiteInfoConfiguration(String creator, String admin, String adminPwd, int visitNum, Date start, String title,
			String introduction) {
		super();
		this.creator = creator;
		this.admin = admin;
		this.adminPwd = adminPwd;
		this.visitNum = visitNum;
		this.start = start;
		this.title = title;
		this.introduction = introduction;
	}
	
}
