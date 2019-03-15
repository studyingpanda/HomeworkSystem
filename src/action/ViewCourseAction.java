package action;
import java.util.HashMap;
import java.util.Map;
import operation.CourseOperation;
import util.Constant;
import entity.User;
import exception.MyException;
import factory.OperationFactory;
/***
 * 动作：查看课程列表(根据登录用户角色不同)
 * @author 猫哥
 * @date 2017.2.15
 * @modify 2017.2.19 to remove context
 */
public class ViewCourseAction extends ViewAction{
	protected User sessionUser=null;
	public ViewCourseAction(String entityType,String operationType,int page,User sessionUser){
		super(entityType,operationType,page);
		this.sessionUser=sessionUser;
	}
	@Override
	public Map<String,Object> execute() throws MyException {
		//返回值对象
		Map<String,Object> resultMap=new HashMap<String,Object>();
		//获取数据库操作对象
		CourseOperation oper=(CourseOperation)OperationFactory.createOperation(entityType);
		if(sessionUser.getUserRole().getRoleName().equals("校长")){//查看全部课程
			return super.execute();
		}else if(sessionUser.getUserRole().getRoleName().equals("教师")){
			//教师特殊之处在于查看课程时，只能看自己创建的课程，注意分页也要重算
			total=oper.selectCountByUserId(sessionUser.getUserId());
			size=Constant.PageSize.get(entityType);
			maxPage=total/size+((total%size)>0?1:0);
			if(page>maxPage)//最后一页时点击下一页
				page=maxPage;
			if(page<1)//第一页时点击上一页
				page=1;
			offset=(page-1)*size;
			resultMap.put(entityType.toLowerCase()+"s",oper.selectPageByUserId(offset, size, sessionUser.getUserId()));
			resultMap.put("currentPage", page);
			resultMap.put("maxPage", maxPage);
			actionUrl="courseManageByTeacher.jsp";
		}
		else if(sessionUser.getUserRole().getRoleName().equals("学生")){//选课
			//教师特殊之处在于查看课程时，只能看自己创建的课程，注意分页也要重算
			total=oper.selectCountByStudentId(sessionUser.getUserId());
			size=Constant.PageSize.get(entityType);
			maxPage=total/size+((total%size)>0?1:0);
			if(page>maxPage)//最后一页时点击下一页
				page=maxPage;
			if(page<1)//第一页时点击上一页
				page=1;
			offset=(page-1)*size;
			resultMap.put(entityType.toLowerCase()+"s",oper.selectPageByStudentId(offset, size, sessionUser.getUserId()));
			resultMap.put("currentPage", page);
			resultMap.put("maxPage", maxPage);
			actionUrl="courseManageByStudent.jsp";
		}
		return resultMap;
	}
}
