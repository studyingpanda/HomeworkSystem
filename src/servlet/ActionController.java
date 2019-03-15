package servlet;
import javax.servlet.http.HttpServletRequest;
import entity.User;
import factory.EntityFactory;
import action.*;
/**
 * 实质的控制器
 * @author 猫哥
 * @date 2017.2.11
 * @modify 2017.2.19 去除context改由Action自行决定需要参数列表 
 * ActionController此时只需负责根据Action需求提供参数即可
 */
public class ActionController {
	//直接装配出Action
	public static Action assemblyAction(HttpServletRequest request){
		//两个必备参数
		String operationType=request.getParameter("method");
		String entityType=request.getParameter("entityType");
		//根据Action需要装配
		if(operationType.equals("delete")){
			String entityId=request.getParameter("entityId");
			//第三个参数null表示交给Action去设置
			return new DeleteAction(entityType,operationType,Integer.parseInt(entityId));
		}
		else if(operationType.equals("view")){
			//所有view都分页
			String inputPage=request.getParameter("page");
			int page=-1;
			if(inputPage==null||"".equals(inputPage)){
				page=1;
			}else{
				page=Integer.parseInt(inputPage);
			}
			//特殊查看逻辑
			if(entityType.equals("Work")){//查看作业，需要课程编号
				return new ViewWorkAction(entityType,operationType,page,Integer.parseInt(request.getParameter("byCourseId")));
			}else if(entityType.equals("Course")){//查看课程，需要登录人员信息
				return new ViewCourseAction(entityType,operationType,page,(User)request.getSession().getAttribute("sessionUser"));
			}else if(entityType.equals("Job")){//查看作业，学生查看未做的，教师查看已做了未批阅的
				return new ViewJobAction(entityType,operationType,page,(User)request.getSession().getAttribute("sessionUser"));
			}
			//正常查看
			return new ViewAction(entityType,operationType,page);
		}
		else if(operationType.equals("add")){
			if(entityType.equals("Work")){//添加作业时，需要携带教师创建的课程列表，此处只是需要登录用户身份即可
				User user=(User)request.getSession().getAttribute("sessionUser");
				return new AddWorkAction(entityType,operationType,user.getUserId());
			}
			return new AddAction(entityType,operationType);
		}
		else if(operationType.equals("edit")){
			//点击编辑后，需要显示被编辑人的信息，所以edit要携带id
			String entityId=request.getParameter("entityId");
			return new EditAction(entityType,operationType,Integer.parseInt(entityId));
		}
		else if(operationType.equals("editJobContent")){//做作业
			String entityId=request.getParameter("entityId");
			return new EditJobContentAction(entityType,operationType,Integer.parseInt(entityId));
		}
		else if(operationType.equals("editJobScore")){//批阅作业
			String entityId=request.getParameter("entityId");
			return new EditJobScoreAction(entityType,operationType,Integer.parseInt(entityId));
		}
		else if(operationType.equals("save")){
			//注意，点击保存后，不同的实体需要携带不同的参数列表，所以采用createEntityFromRequest来创建实体
			Object saveEntity=EntityFactory.createEntityFromRequest(entityType, request);
			Boolean isAddOrEdit=null;
			//需要根据entityId判断是新增还是修改
			String id=request.getParameter("entityId");
			if(id==null||"".equals(id)){
				isAddOrEdit=true;
			}else{
				isAddOrEdit=false;
			}
			//因为Save之后要跳到列表，有些列表需要根据登录用户身份显示，所以
			User sessionUser=(User)request.getSession().getAttribute("sessionUser");
			//注意因为work是跟课程相关的，修改work后要跳转到当前课程所有作业列表，所以添加该输入项
			if(entityType.equals("Work")){
				return new SaveWorkAction(entityType,operationType,isAddOrEdit,saveEntity,sessionUser);
			}else if (entityType.equals("Job")){
				return new SaveJobAction(entityType,operationType,isAddOrEdit,saveEntity,sessionUser);
			}else if (entityType.equals("Lesson")){
				return new SaveLessonAction(entityType,operationType,isAddOrEdit,saveEntity,sessionUser);
			}
			return new SaveAction(entityType,operationType,isAddOrEdit,saveEntity,sessionUser);
		}
		else if(operationType.equals("login")){
			return new LoginAction(entityType,operationType,request.getParameter("userId"),request.getParameter("userPassword"));
		}
		return null;
	}
}
