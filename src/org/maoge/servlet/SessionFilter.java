package org.maoge.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "SessionFilter", urlPatterns = "/*")
public class SessionFilter implements Filter {
	@Override
	public void destroy() {
	}

	/**
	 * ���Ĺ��˷���
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// ��ȡ�����û�������Ӧ���Ự�Ķ���
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession servletSession = servletRequest.getSession();
		// ����û������URI
		String path = servletRequest.getRequestURI();
		// �������login.jsp��tip.jsp�Լ�LoginServlet
		if ((path.indexOf("/login.jsp") > -1) || (path.indexOf("/tip.jsp") > -1)
				|| (path.indexOf("/LoginServlet") > -1)) {
			chain.doFilter(request, response);
			return;

		}
		// �������css�ļ���ͼƬ�ļ�(����������ǾͿ�������ʽ��ͼƬ��)
		else if (path.contains(".css") || path.contains(".jpg") || path.contains(".gif") || path.contains(".png")) {
			chain.doFilter(request, response);
			return;

		}
		// �������js�ļ���ע�ⲻ�������jsp�ļ���
		else if (path.contains(".js") && (!path.contains("jsp"))) {
			chain.doFilter(request, response);
			return;
		}
		// ������������ʵ�֮�⣬���δ��¼����Ȩ�޴���
		if (((HttpServletRequest) request).getSession().getAttribute("loginUser") == null) {
			servletRequest.setAttribute("tipInfo", "��Ȩ�ޣ�");
			request.getRequestDispatcher("/tip.jsp").forward(request, response);
			return;
		}
		// ����ѵ�¼�����������
		else {
			chain.doFilter(request, response);
			return;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
