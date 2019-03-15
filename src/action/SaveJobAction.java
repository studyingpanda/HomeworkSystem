package action;
import java.util.Map;
import operation.JobOperation;
import util.Constant;
import entity.Job;
import entity.User;
import exception.MyException;
import factory.OperationFactory;
/***
 * 动作：学生做作业/老师批阅作业
 * @author 猫哥
 * @date 2017.2.20
 */
public class SaveJobAction extends SaveAction{
	public SaveJobAction(String entityType,String operationType,Boolean isAddOrEdit,Object saveEntity,User sessionUser){
		super(entityType,operationType,isAddOrEdit,saveEntity,sessionUser);
	}
	@Override
	public Map<String,Object> execute() throws MyException {
		JobOperation jobOper=(JobOperation)OperationFactory.createOperation(entityType);
		Job inputJob=(Job)saveEntity;
		Job job=(Job)jobOper.selectById(inputJob.getJobId());
		if(sessionUser.getUserRole().getRoleName().equals("学生"))//做作业
		{
			job.setJobScore("-1");//已做完，未阅
			job.setJobContent(inputJob.getJobContent());
			job.setJobTime(Constant.getDate());
		}
		else if(sessionUser.getUserRole().getRoleName().equals("教师"))//批阅
		{
			job.setJobScore(inputJob.getJobScore());//已做完，未阅
		}
		//新增完毕后跳转查看作业页面
		saveEntity=job;
		return super.execute(); 
	}
}
