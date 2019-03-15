package action;
import java.util.Map;
import operation.CourseOperation;
import exception.MyException;
import factory.OperationFactory;
/***
 * ������������ҵ
 * @author è��
 * @date 2017.2.17
 * @modify 2017.2.19 to remove context
 */
public class AddWorkAction extends AddAction{
	//AddWork��ͬ��AddAction�Ĳ���
	protected int loginUserId=-1;
	public AddWorkAction(String entityType,String operationType,int loginUserId){
		super(entityType,operationType);
		this.loginUserId=loginUserId;
	}
	@Override
	public Map<String, Object> execute() throws MyException {
		//ͨ�����෽��������actionUrl
		Map<String,Object> resultMap=super.execute();
		//ע��������ҵʱ��Ҫѡ��γ̣����Է�����Ϣ���溬�н�ʦ���пγ���Ϣ
		CourseOperation CourseOper=(CourseOperation)OperationFactory.createOperation("Course");
		resultMap.put("courses",CourseOper.selectAllByUserId(loginUserId));
		return resultMap;
	}
}
