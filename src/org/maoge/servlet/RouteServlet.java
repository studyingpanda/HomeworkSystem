package org.maoge.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.maoge.dao.CourseDao;
import org.maoge.dao.JobDao;
import org.maoge.dao.TitleDao;
import org.maoge.dao.UserDao;
import org.maoge.model.Course;
import org.maoge.model.Job;
import org.maoge.model.Title;
import org.maoge.model.User;

@WebServlet("/RouteServlet")
public class RouteServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置输入输出格式、编码
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 获取用户在网页输入的用户名和密码
		String childPage = request.getParameter("childPage");
		request.setAttribute("childPage", childPage);
		if (childPage.equals("userManage.jsp")) { // 进入人员管理页面需要携带人员列表信息
			UserDao userDao = new UserDao();
			List<User> users = userDao.getUsers();
			request.setAttribute("users", users);
		} else if (childPage.equals("userEdit.jsp")) {
			UserDao userDao = new UserDao();
			String userId = request.getParameter("userId");
			User user = userDao.getById(userId);
			request.setAttribute("user", user);
		} else if (childPage.equals("courseView.jsp")) {
			CourseDao courseDao = new CourseDao();
			request.setAttribute("courses", courseDao.getCourses());
		} else if (childPage.equals("courseManage.jsp")) {
			// 获取当前登录用户
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if (loginUser != null) {// 已登录
				CourseDao courseDao = new CourseDao();
				// 返回值为登录用户所对应的课程
				request.setAttribute("courses", courseDao.getCoursesByUserId(loginUser.getUserId()));
			}
		} else if (childPage.equals("courseEdit.jsp")) {
			CourseDao courseDao = new CourseDao();
			String courseId = request.getParameter("courseId");
			Course course = courseDao.getById(courseId);
			request.setAttribute("course", course);
		} else if (childPage.equals("titleManage.jsp")) {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if (loginUser != null) {// 已登录
				TitleDao titleDao = new TitleDao();
				// 返回值为登录用户所对应的作业题目
				request.setAttribute("titles", titleDao.getTitlesByUserId(loginUser.getUserId()));
			}
		} else if (childPage.equals("titleAdd.jsp")) {// 课程新增页面，需要教师管理的课程列表
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			CourseDao courseDao = new CourseDao();
			request.setAttribute("courses", courseDao.getCoursesByUserId(loginUser.getUserId()));
		} else if (childPage.equals("titleEdit.jsp")) {
			TitleDao titleDao = new TitleDao();
			String titleId = request.getParameter("titleId");
			Title title = titleDao.getById(titleId);
			request.setAttribute("title", title);
		} else if (childPage.equals("jobCheck.jsp")) {// 作业批阅
			// 加载教师关联的所有作业题目
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			TitleDao titleDao = new TitleDao();
			request.setAttribute("titles", titleDao.getTitlesByUserId(loginUser.getUserId()));
			String selectedTitle = request.getParameter("selectedTitle");
			JobDao jobDao = new JobDao();
			List<Job> jobs = null;
			if (selectedTitle != null && !"".equals(selectedTitle)) { // 如果有选择的作业题目，则加载该题目下所有作业内容
				jobs = jobDao.getJobsByTitleId(Integer.parseInt(selectedTitle));
			} else {// 否则设置一个空的列表
				jobs = new ArrayList<Job>();
			}
			request.setAttribute("jobs", jobs);
		} else if (childPage.equals("jobCheckEdit.jsp")) {// 批阅具体一个作业
			JobDao jobDao = new JobDao();
			String jobId = request.getParameter("jobId");
			Job job = jobDao.getById(Integer.parseInt(jobId));
			request.setAttribute("job", job);
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);// 跳转到index.jsp
	}
}