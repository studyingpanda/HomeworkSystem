package org.maoge.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.maoge.dao.JobDao;
import org.maoge.dao.SelectionDao;
import org.maoge.dao.TitleDao;
import org.maoge.model.Job;
import org.maoge.model.Selection;
import org.maoge.model.Title;
import org.maoge.model.User;
import org.maoge.utils.TimeUtils;

@WebServlet("/TitleServlet")
public class TitleServlet extends HttpServlet {
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
		TitleDao titleDao = new TitleDao();
		SelectionDao selectionDao = new SelectionDao();
		JobDao jobDao = new JobDao();
		if (method.equals("titleAdd")) {
			// ������ҵ��Ŀ
			Title title = new Title();
			title.setTitleContent(request.getParameter("titleContent"));
			title.setTitleCourse(Integer.parseInt(request.getParameter("titleCourse")));
			title.setTitleTime(TimeUtils.getNowSqlTime());
			int newId = titleDao.add(title);
			// ��ѯѡ����ſε��û�id
			List<Selection> selections = selectionDao.getSelectionsByCourseId(title.getTitleCourse());
			// Ϊÿ��ѡ��ÿε�ѧ��������ҵ���ݼ�¼
			for (Selection selection : selections) {
				Job job = new Job();
				job.setJobTitle(newId);
				job.setJobUser(selection.getSelectionUser());
				job.setJobTime(TimeUtils.getNowSqlTime());
				jobDao.add(job);
			}
		} else if (method.equals("titleEdit")) {// �༭
			Title title = new Title();
			title.setTitleId(Integer.parseInt(request.getParameter("titleId")));
			title.setTitleContent(request.getParameter("titleContent"));
			title.setTitleCourse(Integer.parseInt(request.getParameter("titleCourse")));
			title.setTitleTime(request.getParameter("titleTime"));
			// ���浽���ݿ�
			titleDao.update(title);
		}
		// Я�������û����ݵ���Ա����ҳ��
		request.setAttribute("titles", titleDao.getTitlesByUserId(loginUser.getUserId()));
		// ��ת�������̨ҳ
		request.setAttribute("childPage", "titleManage.jsp");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}