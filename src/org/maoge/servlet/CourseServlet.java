package org.maoge.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.maoge.dao.CourseDao;
import org.maoge.model.Course;
import org.maoge.model.User;

@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
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
		// 获取登录用户信息
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		// 操作数据库
		CourseDao courseDao = new CourseDao();
		if (method.equals("courseAdd")) {// 新增
			Course course = new Course();
			course.setCourseName(request.getParameter("courseName"));
			course.setCourseUser(loginUser.getUserId());
			courseDao.add(course);
		} else if (method.equals("courseEdit")) {// 编辑用户
			Course course = new Course();
			course.setCourseId(Integer.parseInt(request.getParameter("courseId")));
			course.setCourseUser(loginUser.getUserId());
			course.setCourseName(request.getParameter("courseName"));
			// 保存到数据库
			courseDao.update(course);
		}
		// 携带最新用户数据到人员管理页面
		request.setAttribute("courses", courseDao.getCoursesByUserId(loginUser.getUserId()));
		// 跳转到管理后台页面，且子页面是用户管理
		request.setAttribute("childPage", "courseManage.jsp");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}