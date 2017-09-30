package com.ecej.pojo;

import java.util.HashSet;
import java.util.Set;

public class User {
	
	private Integer uid;
	
	private String username;
	
	private String password;
	
	private String sex;
	
	private String description;
	
	private int rid;
	
	private Set<Role> roles=new HashSet<>();

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Integer uid, String username, String password, String sex, String description, int rid,
			Set<Role> roles) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.description = description;
		this.rid = rid;
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", sex=" + sex
				+ ", description=" + description + ", rid=" + rid + ", roles=" + roles + "]";
	}
	
	

	
}
