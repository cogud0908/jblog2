package com.douzone.jblog.vo;

public class CategoryVo {

	private int no;
	private String name;
	private String description;
	private String reg_date;
	private int user_no;
	private int postCount;
		
	public int getPostCount() {
		return postCount;
	}
	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", description=" + description + ", reg_date=" + reg_date
				+ ", user_no=" + user_no + ", postCount=" + postCount + "]";
	}
}
