/**
 * servlet������ActionServlet����ĿΨһ������
 * ActionController:�����߼���
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
//ActionServlet��Ϊ������ĿΨһ��Servlet
public class ActionServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);//doGet��doPostһ������
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//���������ʽ����
	
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");	
		//���ݲ�ͬ��ActionContext����������action����
		Action action=ActionController.assemblyAction(request);
		Map<String,Object> map=null;
		try {
			map=action.execute();//ִ�ж��������ؽ��
		} catch (MyException ex) {//������쳣����ת�쳣��ʾҳ��
			request.setAttribute("tipInfo", ex.getInfo());
			request.getRequestDispatcher("/tip.jsp").forward(request,response);
			return;
		}
		//���÷���ҳ��
		request.setAttribute("actionUrl",action.getActionUrl());
		//ȡ�������еķ���ֵ		
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
		//��ת��index.jsp��ҳ�棬ע�� actionUrlָ��ҳ����ʾ��index.jsp�ұ���������
		request.getRequestDispatcher("/index.jsp").forward(request,response);
	}
}
