package action;
import java.util.HashMap;
import java.util.Map;
import inter.IOperation;
import exception.MyException;
import factory.OperationFactory;
/***
 * 动作：点击修改按钮后对应动作
 * @author 猫哥
 * @date 2017.2.9
 * @modify 2017.2.19 to remove context
 */
public class EditAction extends Action{
	protected int editEntityId=-1;
	public EditAction(String entityType,String operationType,int editEntityId){
		super(entityType,operationType);
		this.editEntityId=editEntityId;
	}
	@Override
	public Map<String, Object> execute() throws MyException {
		Map<String,Object> resultMap=new HashMap<String,Object>();
		IOperation oper=OperationFactory.createOperation(entityType);
		Object entity=oper.selectById(editEntityId);
		resultMap.put(entityType.toLowerCase(), entity);
		actionUrl=entityType.toLowerCase()+"Update.jsp";
		return resultMap;
	}
}









