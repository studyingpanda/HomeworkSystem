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
		JobDao jobDao = new JobDao();
		if (method.equals("jobCheckEdit")) {// 编辑
			Job job = new Job();
			job.setJobId(Integer.parseInt(request.getParameter("jobId")));
			job.setJobContent(request.getParameter("jobContent"));
			job.setJobScore(request.getParameter("jobScore"));
			job.setJobTime(TimeUtils.getNowSqlTime());
			job.setJobTitle(Integer.parseInt(request.getParameter("jobTitle")));
			job.setJobUser(Integer.parseInt(request.getParameter("jobUser")));
			// 保存到数据库
			jobDao.update(job);
			// 加载教师关联的所有作业题目
			TitleDao titleDao = new TitleDao();
			request.setAttribute("titles", titleDao.getTitlesByUserId(loginUser.getUserId()));
			String selectedTitle = request.getParameter("selectedTitle");
			List<Job> jobs = null;
			if (selectedTitle != null && !"".equals(selectedTitle)) { // 如果有选择的作业题目，则加载该题目下所有作业内容
				jobs = jobDao.getJobsByTitleId(Integer.parseInt(selectedTitle));
			} else {// 否则设置一个空的列表
				jobs = new ArrayList<Job>();
			}
			request.setAttribute("jobs", jobs);
			// 跳转到管理后台页
			request.setAttribute("childPage", "jobCheck.jsp");
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}