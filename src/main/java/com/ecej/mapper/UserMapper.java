package com.ecej.mapper;

import java.util.List;

import com.ecej.pojo.User;
import com.ecej.util.vo.UserVo;

public interface UserMapper {
	
	public User findByUserName(String username);

	public List getMeduleList();

	public List getuniqueList(User user);

	public int userAddAndGetId(User user);

	public void userAddRole(User user);

	public int getUserCount(UserVo userVo);

	public List getRoleList();

	public List getUserList(UserVo userVo);

	public User getUserByUid(User user);

	public void delRoleByUid(User user);

	public void addRoleByUid(User user);

	public int userUpdate(User user);

	public int userPassUpdate(User user);

	public void userRoleDel(int uid);

	public int userDel(int uid);

}
