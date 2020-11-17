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
	 * 核心过滤方法
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 获取代表用户请求、响应、会话的对象
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession servletSession = servletRequest.getSession();
		// 获得用户请求的URI
		String path = servletRequest.getRequestURI();
		// 允许访问login.jsp和tip.jsp以及LoginServlet
		if ((path.indexOf("/login.jsp") > -1) || (path.indexOf("/tip.jsp") > -1)
				|| (path.indexOf("/LoginServlet") > -1)) {
			chain.doFilter(request, response);
			return;

		}
		// 允许访问css文件和图片文件(如果不允许，那就看不到样式和图片了)
		else if (path.contains(".css") || path.contains(".jpg") || path.contains(".gif") || path.contains(".png")) {
			chain.doFilter(request, response);
			return;

		}
		// 允许访问js文件，注意不允许访问jsp文件！
		else if (path.contains(".js") && (!path.contains("jsp"))) {
			chain.doFilter(request, response);
			return;
		}
		// 除上述允许访问的之外，如果未登录报无权限错误
		if (((HttpServletRequest) request).getSession().getAttribute("loginUser") == null) {
			servletRequest.setAttribute("tipInfo", "无权限！");
			request.getRequestDispatcher("/tip.jsp").forward(request, response);
			return;
		}
		// 如果已登录，则允许访问
		else {
			chain.doFilter(request, response);
			return;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
