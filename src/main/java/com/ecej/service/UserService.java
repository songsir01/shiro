package com.ecej.service;

import java.util.List;

import com.ecej.pojo.User;
import com.ecej.util.vo.UserVo;

public interface UserService {
	
	public User findUserByUserName(String username);

	public List getMeduleList();

	public List getuniqueList(User user);

	public int userAddAndGetId(User user);

	public void userAddRole(User user);

	public int getUserCount(UserVo userVo);

	public List getRoleList();

	public List getUserList(UserVo userVo);

	public User userByUid(User user);

	public int userUpdate(User user);

	public int userPassUpdate(User user);

	public int userDel(int parseInt);

}
