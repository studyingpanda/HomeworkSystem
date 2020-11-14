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

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {// ����post����
		// �������������ʽ������
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// ��ȡmethod����
		String method = request.getParameter("method");
		// �û��������ݿ�
		UserDao userDao = new UserDao();
		if (method.equals("userAdd")) {// �����û�
			// ��ȡ�û�����ҳ������û���������
			User user = new User();
			user.setUserName(request.getParameter("userName"));
			user.setUserPassword(request.getParameter("userPassword"));
			user.setUserRole(request.getParameter("userRole"));
			// ���浽���ݿ�
			userDao.add(user);
		} else if (method.equals("userEdit")) {// �༭�û�
			// ��ȡ�û�����ҳ������û���������
			User user = new User();
			user.setUserId(Integer.parseInt(request.getParameter("userId")));
			user.setUserName(request.getParameter("userName"));
			user.setUserPassword(request.getParameter("userPassword"));
			user.setUserRole(request.getParameter("userRole"));
			// ���浽���ݿ�
			userDao.update(user);
		} else if (method.equals("userDelete")) {// ɾ���û�
			userDao.deleteById(Integer.parseInt(request.getParameter("userId")));
		} 
		// Я�������û����ݵ���Ա����ҳ��
		request.setAttribute("users", userDao.getUsers());
		// ��ת�������̨ҳ�棬����ҳ�����û�����
		request.setAttribute("childPage", "userManage.jsp");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}