package action;
import java.util.Map;
import operation.CourseOperation;
import exception.MyException;
import factory.OperationFactory;
/***
 * 动作：新增作业
 * @author 猫哥
 * @date 2017.2.17
 * @modify 2017.2.19 to remove context
 */
public class AddWorkAction extends AddAction{
	//AddWork不同与AddAction的参数
	protected int loginUserId=-1;
	public AddWorkAction(String entityType,String operationType,int loginUserId){
		super(entityType,operationType);
		this.loginUserId=loginUserId;
	}
	@Override
	public Map<String, Object> execute() throws MyException {
		//通过父类方法设置了actionUrl
		Map<String,Object> resultMap=super.execute();
		//注意新增作业时需要选择课程，所以返回信息里面含有教师所有课程信息
		CourseOperation CourseOper=(CourseOperation)OperationFactory.createOperation("Course");
		resultMap.put("courses",CourseOper.selectAllByUserId(loginUserId));
		return resultMap;
	}
}
