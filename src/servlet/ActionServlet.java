/**
 * servlet包包含ActionServlet：项目唯一控制器
 * ActionController:控制逻辑类
 */
package servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import exception.MyException;
import java.util.Iterator;
import java.util.Map;
//ActionServlet作为整个项目唯一的Servlet
public class ActionServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);//doGet与doPost一样处理
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//输入输出格式设置
	
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");	
		//根据不同的ActionContext创建不懂的action动作
		Action action=ActionController.assemblyAction(request);
		Map<String,Object> map=null;
		try {
			map=action.execute();//执行动作并返回结果
		} catch (MyException ex) {//如果有异常，跳转异常提示页面
			request.setAttribute("tipInfo", ex.getInfo());
			request.getRequestDispatcher("/tip.jsp").forward(request,response);
			return;
		}
		//设置返回页面
		request.setAttribute("actionUrl",action.getActionUrl());
		//取上下文中的返回值		
		if(map!=null){
			Iterator iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				if(key.toString().startsWith("session")){
					request.getSession().setAttribute(key.toString(), val);
				}else{
					request.setAttribute(key.toString(), val);
				}
			}
		}
		//跳转到index.jsp主页面，注意 actionUrl指向页面显示在index.jsp右边内容区域
		request.getRequestDispatcher("/index.jsp").forward(request,response);
	}
}
