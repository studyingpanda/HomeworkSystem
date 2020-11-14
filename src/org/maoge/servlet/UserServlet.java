package org.maoge.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.maoge.dao.UserDao;
import org.maoge.model.User;
import org.maoge.service.LoginService;
import org.maoge.utils.Constants;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {// 处理post请求
		// 设置输入输出格式、编码
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 获取method参数
		String method = request.getParameter("method");
		// 用户操作数据库
		UserDao userDao = new UserDao();
		if (method.equals("userAdd")) {// 新增用户
			// 获取用户在网页输入的用户名和密码
			User user = new User();
			user.setUserName(request.getParameter("userName"));
			user.setUserPassword(request.getParameter("userPassword"));
			user.setUserRole(request.getParameter("userRole"));
			// 保存到数据库
			userDao.add(user);
		} else if (method.equals("userEdit")) {// 编辑用户
			// 获取用户在网页输入的用户名和密码
			User user = new User();
			user.setUserId(Integer.parseInt(request.getParameter("userId")));
			user.setUserName(request.getParameter("userName"));
			user.setUserPassword(request.getParameter("userPassword"));
			user.setUserRole(request.getParameter("userRole"));
			// 保存到数据库
			userDao.update(user);
		} else if (method.equals("userDelete")) {// 删除用户
			userDao.deleteById(Integer.parseInt(request.getParameter("userId")));
		} 
		// 携带最新用户数据到人员管理页面
		request.setAttribute("users", userDao.getUsers());
		// 跳转到管理后台页面，且子页面是用户管理
		request.setAttribute("childPage", "userManage.jsp");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}