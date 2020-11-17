package org.maoge.servlet;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.maoge.model.User;
import org.maoge.service.LoginService;
import org.maoge.utils.Constants;
import org.maoge.utils.LogUtils;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {// 用于处理登录请求的LoginServlet
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);// 直接调用doPost方法处理get请求
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {// 处理post请求
		// 设置输入输出格式、编码
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 获取用户在网页输入的用户名和密码
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");

		LoginService lc = new LoginService();
		User user = null;
		String tipInfo = "";// 提示内容
		String page = "";// 跳转页面
		try {
			user = lc.checkLogin(userName, userPassword);
			if (user == null) {
				// 跳转到错误提示页面，并提示用户不存在
				tipInfo = "用户不存在";
				page = "tip.jsp";
			} else {
				// 登录成功，记录用户信息到Session，同时跳转管理后台页面
				// 记录日志
				LogUtils.writeLog(Level.INFO, "登录成功:"+user.getUserName());
				request.getSession().setAttribute("loginUser", user);
				page = "index.jsp";
				// 设置菜单
				String[][] loginMenus = Constants.roleMenuMap.get(user.getUserRole());
				request.getSession().setAttribute("loginMenus", loginMenus);
			}
			// 根据用户角色显示不同内容
		} catch (Exception e) {
			// 跳转到错误提示页面，并提示相应错误信息
			tipInfo = e.getMessage();
			page = "tip.jsp";
		}
		request.setAttribute("tipInfo", tipInfo);// 设置同手信息
		request.getRequestDispatcher("/" + page).forward(request, response);// 跳转到page页面
	}
}