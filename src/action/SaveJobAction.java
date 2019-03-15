package action;
import java.util.Map;
import operation.JobOperation;
import util.Constant;
import entity.Job;
import entity.User;
import exception.MyException;
import factory.OperationFactory;
/***
 * ������ѧ������ҵ/��ʦ������ҵ
 * @author è��
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
		if(sessionUser.getUserRole().getRoleName().equals("ѧ��"))//����ҵ
		{
			job.setJobScore("-1");//�����꣬δ��
			job.setJobContent(inputJob.getJobContent());
			job.setJobTime(Constant.getDate());
		}
		else if(sessionUser.getUserRole().getRoleName().equals("��ʦ"))//����
		{
			job.setJobScore(inputJob.getJobScore());//�����꣬δ��
		}
		//������Ϻ���ת�鿴��ҵҳ��
		saveEntity=job;
		return super.execute(); 
	}
}
