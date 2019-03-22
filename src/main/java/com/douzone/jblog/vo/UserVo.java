package com.douzone.jblog.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserVo {

	private int no;
	
	@NotEmpty
	private String id;
	
	@NotEmpty
	@Length(min=2, max=5)
	private String name;
	
	@NotEmpty
	private String password;
	private String Join_date;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getJoin_date() {
		return Join_date;
	}
	public void setJoin_date(String join_date) {
		Join_date = join_date;
	}
	@Override
	public String toString() {
		return "UserVo [no=" + no + ", id=" + id + ", name=" + name + ", password=" + password + ", Join_date="
				+ Join_date + "]";
	}
}
