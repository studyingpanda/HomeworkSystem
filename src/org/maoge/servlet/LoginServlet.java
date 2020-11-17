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
public class LoginServlet extends HttpServlet {// ���ڴ����¼�����LoginServlet
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);// ֱ�ӵ���doPost��������get����
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {// ����post����
		// �������������ʽ������
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// ��ȡ�û�����ҳ������û���������
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");

		LoginService lc = new LoginService();
		User user = null;
		String tipInfo = "";// ��ʾ����
		String page = "";// ��תҳ��
		try {
			user = lc.checkLogin(userName, userPassword);
			if (user == null) {
				// ��ת��������ʾҳ�棬����ʾ�û�������
				tipInfo = "�û�������";
				page = "tip.jsp";
			} else {
				// ��¼�ɹ�����¼�û���Ϣ��Session��ͬʱ��ת�����̨ҳ��
				// ��¼��־
				LogUtils.writeLog(Level.INFO, "��¼�ɹ�:"+user.getUserName());
				request.getSession().setAttribute("loginUser", user);
				page = "index.jsp";
				// ���ò˵�
				String[][] loginMenus = Constants.roleMenuMap.get(user.getUserRole());
				request.getSession().setAttribute("loginMenus", loginMenus);
			}
			// �����û���ɫ��ʾ��ͬ����
		} catch (Exception e) {
			// ��ת��������ʾҳ�棬����ʾ��Ӧ������Ϣ
			tipInfo = e.getMessage();
			page = "tip.jsp";
		}
		request.setAttribute("tipInfo", tipInfo);// ����ͬ����Ϣ
		request.getRequestDispatcher("/" + page).forward(request, response);// ��ת��pageҳ��
	}
}