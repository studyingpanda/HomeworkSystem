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

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {// ����post����
		// �������������ʽ������
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// ��ȡmethod����
		String method = request.getParameter("method");
		// ��ȡ��¼�û���Ϣ
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		// �������ݿ�
		CourseDao courseDao = new CourseDao();
		if (method.equals("courseAdd")) {// ����
			Course course = new Course();
			course.setCourseName(request.getParameter("courseName"));
			course.setCourseUser(loginUser.getUserId());
			courseDao.add(course);
		} else if (method.equals("courseEdit")) {// �༭�û�
			Course course = new Course();
			course.setCourseId(Integer.parseInt(request.getParameter("courseId")));
			course.setCourseUser(loginUser.getUserId());
			course.setCourseName(request.getParameter("courseName"));
			// ���浽���ݿ�
			courseDao.update(course);
		}
		// Я�������û����ݵ���Ա����ҳ��
		request.setAttribute("courses", courseDao.getCoursesByUserId(loginUser.getUserId()));
		// ��ת�������̨ҳ�棬����ҳ�����û�����
		request.setAttribute("childPage", "courseManage.jsp");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}