package com.ecej.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecej.mapper.RoleMapper;
import com.ecej.service.RoleServiceI;

@Service
public class RoleServiceImpl implements RoleServiceI {

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List getRoleList() {
		// TODO Auto-generated method stub
		return roleMapper.getRoleList();
	}

}
