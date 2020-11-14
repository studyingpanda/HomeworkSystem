package org.maoge.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.maoge.dao.JobDao;
import org.maoge.dao.TitleDao;
import org.maoge.model.Job;
import org.maoge.model.User;
import org.maoge.utils.TimeUtils;

@WebServlet("/JobServlet")
public class JobServlet extends HttpServlet {
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
		JobDao jobDao = new JobDao();
		if (method.equals("jobCheckEdit")) {// �༭
			Job job = new Job();
			job.setJobId(Integer.parseInt(request.getParameter("jobId")));
			job.setJobContent(request.getParameter("jobContent"));
			job.setJobScore(request.getParameter("jobScore"));
			job.setJobTime(TimeUtils.getNowSqlTime());
			job.setJobTitle(Integer.parseInt(request.getParameter("jobTitle")));
			job.setJobUser(Integer.parseInt(request.getParameter("jobUser")));
			// ���浽���ݿ�
			jobDao.update(job);
			// ���ؽ�ʦ������������ҵ��Ŀ
			TitleDao titleDao = new TitleDao();
			request.setAttribute("titles", titleDao.getTitlesByUserId(loginUser.getUserId()));
			String selectedTitle = request.getParameter("selectedTitle");
			List<Job> jobs = null;
			if (selectedTitle != null && !"".equals(selectedTitle)) { // �����ѡ�����ҵ��Ŀ������ظ���Ŀ��������ҵ����
				jobs = jobDao.getJobsByTitleId(Integer.parseInt(selectedTitle));
			} else {// ��������һ���յ��б�
				jobs = new ArrayList<Job>();
			}
			request.setAttribute("jobs", jobs);
			// ��ת�������̨ҳ
			request.setAttribute("childPage", "jobCheck.jsp");
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}