package action;
import java.util.HashMap;
import java.util.Map;
import inter.IOperation;
import exception.MyException;
import factory.OperationFactory;
/***
 * 动作：学生做作业
 * @author 猫哥
 * @date 2017.2.20
 */
public class EditJobScoreAction extends Action{
	protected int editEntityId=-1;
	public EditJobScoreAction(String entityType,String operationType,int editEntityId){
		super(entityType,operationType);
		this.editEntityId=editEntityId;
	}
	@Override
	public Map<String, Object> execute() throws MyException {
		Map<String,Object> resultMap=new HashMap<String,Object>();
		IOperation oper=OperationFactory.createOperation(entityType);
		Object entity=oper.selectById(editEntityId);
		resultMap.put(entityType.toLowerCase(), entity);
		actionUrl="jobUpdateByTeacher.jsp";
		return resultMap;
	}
}









