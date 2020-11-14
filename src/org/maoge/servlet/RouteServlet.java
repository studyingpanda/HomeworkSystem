package org.maoge.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.maoge.dao.CourseDao;
import org.maoge.dao.TitleDao;
import org.maoge.dao.UserDao;
import org.maoge.model.Course;
import org.maoge.model.User;

@WebServlet("/RouteServlet")
public class RouteServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �������������ʽ������
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// ��ȡ�û�����ҳ������û���������
		String childPage = request.getParameter("childPage");
		request.setAttribute("childPage", childPage);
		if (childPage.equals("userManage.jsp")) { // ������Ա����ҳ����ҪЯ����Ա�б���Ϣ
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
			// ��ȡ��ǰ��¼�û�
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if (loginUser != null) {// �ѵ�¼
				CourseDao courseDao = new CourseDao();
				// ����ֵΪ��¼�û�����Ӧ�Ŀγ�
				request.setAttribute("courses", courseDao.getCoursesByUserId(loginUser.getUserId()));
			}
		} else if (childPage.equals("courseEdit.jsp")) {
			CourseDao courseDao = new CourseDao();
			String courseId = request.getParameter("courseId");
			Course course = courseDao.getById(courseId);
			request.setAttribute("course", course);
		} else if (childPage.equals("titleManage.jsp")) {
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			if (loginUser != null) {// �ѵ�¼
				TitleDao titleDao = new TitleDao();
				// ����ֵΪ��¼�û�����Ӧ�Ŀγ�
				request.setAttribute("titles", titleDao.getTitlesByUserId(loginUser.getUserId()));
			}
		} else if (childPage.equals("titleAdd.jsp")) {// �γ�����ҳ�棬��Ҫ��ʦ����Ŀγ��б�
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			CourseDao courseDao = new CourseDao();
			request.setAttribute("courses", courseDao.getCoursesByUserId(loginUser.getUserId()));
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);// ��ת��index.jsp
	}
}