package action;
import java.util.HashMap;
import java.util.Map;
import operation.JobOperation;
import util.Constant;
import entity.User;
import exception.MyException;
import factory.OperationFactory;
/***
 * 动作：查看作业列表(根据登录用户角色不同)
 * @author 猫哥
 * @date 2017.2.20
 */
public class ViewJobAction extends ViewAction{
	protected User sessionUser=null;
	public ViewJobAction(String entityType,String operationType,int page,User sessionUser){
		super(entityType,operationType,page);
		this.sessionUser=sessionUser;
	}
	@Override
	public Map<String,Object> execute() throws MyException {
		//返回值对象
		Map<String,Object> resultMap=new HashMap<String,Object>();
		//获取数据库操作对象
		JobOperation oper=(JobOperation)OperationFactory.createOperation(entityType);
		if(sessionUser.getUserRole().getRoleName().equals("校长")){//查看全部作业
			return super.execute();
		}else if(sessionUser.getUserRole().getRoleName().equals("教师")){
			//教师负责查看自己课程已完成、未批阅的作业
			total=oper.selectCountByTeacherId(sessionUser.getUserId(),"-1");
			size=Constant.PageSize.get(entityType);
			maxPage=total/size+((total%size)>0?1:0);
			if(page>maxPage)//最后一页时点击下一页
				page=maxPage;
			if(page<1)//第一页时点击上一页
				page=1;
			offset=(page-1)*size;
			resultMap.put(entityType.toLowerCase()+"s",oper.selectPageByTeacherId(offset, size, sessionUser.getUserId(),"-1"));
			resultMap.put("currentPage", page);
			resultMap.put("maxPage", maxPage);
			actionUrl="jobManageByTeacher.jsp";
		}
		else if(sessionUser.getUserRole().getRoleName().equals("学生")){
			//学生查看未做的作业
			total=oper.selectCountByStudentId(sessionUser.getUserId(),"-2");
			size=Constant.PageSize.get(entityType);
			maxPage=total/size+((total%size)>0?1:0);
			if(page>maxPage)//最后一页时点击下一页
				page=maxPage;
			if(page<1)//第一页时点击上一页
				page=1;
			offset=(page-1)*size;
			resultMap.put(entityType.toLowerCase()+"s",oper.selectPageByStudentId(offset, size, sessionUser.getUserId(),"-2"));
			resultMap.put("currentPage", page);
			resultMap.put("maxPage", maxPage);
			actionUrl="jobManageByStudent.jsp";
		}
		return resultMap;
	}
}
