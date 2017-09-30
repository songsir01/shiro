package com.ecej.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecej.mapper.UserMapper;
import com.ecej.pojo.User;
import com.ecej.service.UserService;
import com.ecej.util.Md5Util;
import com.ecej.util.vo.UserVo;
@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findUserByUserName(String username) {
		// TODO Auto-generated method stub
		return userMapper.findByUserName(username);
	}

	@Override
	public List getMeduleList() {
		// TODO Auto-generated method stub
		return userMapper.getMeduleList();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getuniqueList(User user) {
		
		// TODO Auto-generated method stub
		return userMapper.getuniqueList(user);
	}
	/**
	 * 
	 * TODO 注册用户.
	 * @see com.test.service.UserServiceI#userAdd(com.test.pojo.User)
	 */
	@Override
	public int userAddAndGetId(User user) {
		
		// TODO Auto-generated method stub
		Md5Util md5Util = new Md5Util();
		
		@SuppressWarnings("static-access")
		String md5Password = md5Util.MD5(user.getPassword()); //密码md5加密
		
		user.setPassword(md5Password);
		
		return userMapper.userAddAndGetId(user);
		
	}

	@Override
	public void userAddRole(User user) {
		// TODO Auto-generated method stub
		userMapper.userAddRole(user);
	}

	@Override
	public int getUserCount(UserVo userVo) {
		// TODO Auto-generated method stub
		return userMapper.getUserCount(userVo);
	}

	@Override
	public List getRoleList() {
		// TODO Auto-generated method stub
		return userMapper.getRoleList();
	}

	@Override
	public List getUserList(UserVo userVo) {
		// TODO Auto-generated method stub
		return userMapper.getUserList(userVo);
	}

	@Override
	public User userByUid(User user) {
		// TODO Auto-generated method stub
		return userMapper.getUserByUid(user);
	}

	@Override
	public int userUpdate(User user) {
		// TODO Auto-generated method stub
		userMapper.delRoleByUid(user);
		userMapper.addRoleByUid(user);
		
		return userMapper.userUpdate(user);
	}

	@Override
	public int userPassUpdate(User user) {
		// TODO Auto-generated method stub
		userMapper.delRoleByUid(user);
		userMapper.addRoleByUid(user);
		Md5Util md5Util = new Md5Util();
		String md5Password = md5Util.MD5(user.getPassword());
		user.setPassword(md5Password);
		return userMapper.userPassUpdate(user);
	}

	@Override
	public int userDel(int uid) {
		// TODO Auto-generated method stub
		userMapper.userRoleDel(uid);
		return userMapper.userDel(uid);
	}

}
