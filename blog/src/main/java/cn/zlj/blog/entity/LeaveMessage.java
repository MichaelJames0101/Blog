package cn.zlj.blog.entity;

import java.util.Date;

/**
 * 用户留言实体类
 * @author ZLJ
 *
 */
public class LeaveMessage extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer lmid;
	private Integer uid;
	private String ip;
	private String content;
	private Date addTime;
	public Integer getLmid() {
		return lmid;
	}
	public void setLmid(Integer lmid) {
		this.lmid = lmid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	@Override
	public String toString() {
		return "LeaveMessage [lmid=" + lmid + ", uid=" + uid + ", ip=" + ip + ", content=" + content + ", addTime="
				+ addTime + "]";
	}
	
	
}
