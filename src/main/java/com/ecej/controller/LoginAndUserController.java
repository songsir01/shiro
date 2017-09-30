package com.ecej.controller;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecej.pojo.User;
import com.ecej.service.UserService;
import com.ecej.util.Md5Util;
import com.ecej.util.PageUtil;
import com.ecej.util.vo.UserVo;

import net.sf.json.JSONObject;

@Controller
public class LoginAndUserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login() {
		return "jsp/login";
	}

	@RequestMapping("/loginUser")
	public void loginUser(String username, String password, HttpSession session, HttpServletResponse response)
			throws IOException {
		Md5Util md5Util = new Md5Util();
		String mPassword = md5Util.MD5(password);
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, mPassword);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(usernamePasswordToken); // 完成登录
			User user = (User) subject.getPrincipal();
			session.setAttribute("user", user);
			response.getWriter().write("1");
			// return "index";
		} catch (Exception e) {
			// return "login";// 返回登录页面
			response.getWriter().write("0");
		}
		//return "login";

	}

	@RequestMapping("/index")
	public String zhuye(HttpSession session) {
		// List meduleList=userService.getMeduleList();
		// System.out.println(meduleList);
		// session.setAttribute("meduleList", meduleList);
		Object attribute = session.getAttribute("user");
		if (attribute == null) {
			return "jsp/login";
		}
		return "jsp/public/index";
	}

	@RequestMapping("/logOut")
	public void logOut(HttpSession session, HttpServletResponse response) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		try {
			response.getWriter().write("1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * userunique:(验证用户名是否存在). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author SongYapeng
	 * @param user
	 * @param response
	 * @since JDK 1.7
	 */
	@RequestMapping("/userunique")
	public void userunique(User user, HttpServletResponse response) {

		String replaceName = user.getUsername().replace(" ", "");

		user.setUsername(replaceName);
		@SuppressWarnings("rawtypes")
		List useruniqueList = userService.getuniqueList(user);

		if (useruniqueList.isEmpty()) {
			try {
				response.getWriter().write("0");
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().write("1");
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * regNext:(用户注册). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author SongYapeng
	 * @param user
	 * @param model
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping("/regNext")
	public void regNext(User user, Model model, HttpServletResponse response) {
		Matcher matcher = Pattern.compile("[A-Za-z0-9\\-]{3,16}$").matcher(user.getUsername());
		if (!matcher.matches()) {
			try {
				response.getWriter().write("4");// 用户名不合法
			} catch (IOException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		} else {
			@SuppressWarnings("rawtypes")
			List useruniqueList = userService.getuniqueList(user);
			if (useruniqueList.isEmpty()) {
				try {
					int uid = userService.userAddAndGetId(user);
					userService.userAddRole(user);
					response.getWriter().write(uid + "");
				} catch (IOException e) {

					e.printStackTrace();
				}
			} else {
				try {
					response.getWriter().write("3");
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}

	}
	
	/**
	 * 
	 * userManager:(用户列表). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author SongYapeng
	 * @param request
	 * @param userVo
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping("/userManager")
	public String userManager(HttpServletRequest request, UserVo userVo) {

		String userName = null;
		String replaceName = null;
		userName = request.getParameter("keyWord");
		String pageSize = request.getParameter("pageSize");
		if (pageSize == null || pageSize.equals("")) {
			pageSize = "10";
		}
		Integer inPageSize = Integer.valueOf(pageSize);
		
		if (userName != null) {
			replaceName = userName.replace(" ", "");
			userVo.setUserName("%" + replaceName + "%");
		}
		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
		if(page!=null){
			if(Integer.parseInt(page)<=0){
				page="1";
			}
		}
		int userCount = userService.getUserCount(userVo);

		PageUtil pUtil = new PageUtil(page, inPageSize, userCount);
		userVo.setPageSize(pUtil.getPagesize());
		userVo.setStartPage((pUtil.getPageindex() - 1) * pUtil.getPagesize());

		// 角色列表
		@SuppressWarnings("rawtypes")
		List roleList = userService.getRoleList();
		request.setAttribute("roleList", roleList);
		@SuppressWarnings("rawtypes")
		List userList = userService.getUserList(userVo);
		
		//System.out.println(userList);
		
		request.setAttribute("userList", userList);
		request.setAttribute("page", page);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("prevPage", pUtil.getPrevpage());
		request.setAttribute("nextPage", pUtil.getNextpage());
		request.setAttribute("lastPage", pUtil.getLastpage());
		request.setAttribute("keyWord", replaceName);

		return "WEB-INF/pages/userManager";
	}
	
	/**
	 * 
	 * toupd:(数据回显). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author SongYapeng
	 * @param user
	 * @param response
	 * @since JDK 1.7
	 */
	@RequestMapping("/toupd")
	public void toupd(User user, HttpServletResponse response) {
		User userByUid = userService.userByUid(user);
		JSONObject json = JSONObject.fromObject(userByUid);
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {

			e.printStackTrace();

		}
	}
	/**
	 * 
	 * isLogin:(防止通过url直接访问页面，判断是否登录). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author SongYapeng
	 * @param request
	 * @param response
	 * @since JDK 1.7
	 */
	@RequestMapping("/isLogin")
	public void isLogin(HttpServletRequest request, HttpServletResponse response) {

		Object attribute = request.getSession().getAttribute("user");

		if (attribute == null) {
			try {
				response.getWriter().write("1");
			} catch (IOException e) {

				e.printStackTrace();

			}
		}

	}
	
	/**
	 * 
	 * upStart:(更新操作). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author SongYapeng
	 * @param response
	 * @param user
	 * @since JDK 1.7
	 */
	@RequestMapping("/upStart")
	public void upStart(HttpServletResponse response, User user) {
		if (user.getPassword().equals("") || user.getPassword() == null) {
			int i = userService.userUpdate(user);
			try {
				if (i > 0) {
					response.getWriter().write("1");
				} else {
					response.getWriter().write("0");
				}
			} catch (IOException e) {

				e.printStackTrace();

			}
		} else {
			int i = userService.userPassUpdate(user);
			try {
				if (i > 0) {
					response.getWriter().write("1");
				} else {
					response.getWriter().write("0");
				}
			} catch (IOException e) {

				e.printStackTrace();

			}
		}

	}
	/**
	 * 
	 * userDel:(删除用户). <br/>
	 * 
	 * @author SongYapeng
	 * @param response
	 * @since JDK 1.7
	 */
	@RequestMapping("/userDel")
	public void userDel(HttpServletResponse response, HttpServletRequest request) {

		String ids = request.getParameter("ids");
		String[] splitIds = ids.split(",");
		int i = 0;
		for (String uid : splitIds) {
			i = userService.userDel(Integer.parseInt(uid));
		}
		try {
			if (i > 0) {
				response.getWriter().write("1");
			} else {
				response.getWriter().write("0");
			}
		} catch (IOException e) {

			e.printStackTrace();

		}
	}


}