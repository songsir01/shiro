package com.ecej.pojo;

import java.util.Set;

public class Module {
	
	private Integer mid;
	
	private String mname;
	
	private Set<Role> roles;

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Module() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Module(Integer mid, String mname, Set<Role> roles) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Module [mid=" + mid + ", mname=" + mname + ", roles=" + roles + "]";
	}

	
	

}
