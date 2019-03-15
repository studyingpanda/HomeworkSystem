package action;
import inter.IOperation;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import exception.MyException;
import factory.OperationFactory;
/***
 * 动作：删除后对应动作
 * @author 猫哥
 * @date 2017.2.9
 * @modify 2017.2.19 to remove context
 */
public class DeleteAction extends Action{
	protected int deleteEntityId=-1;
	public DeleteAction(String entityType,String operationType,int deleteEntityId){
		super(entityType,operationType);
		this.deleteEntityId=deleteEntityId;
	}
	@Override
	public Map<String, Object> execute() throws MyException {
		Map<String,Object> resultMap=new HashMap<String,Object>();
		//按类型获取实体操作类
		IOperation oper=OperationFactory.createOperation(entityType);
		int affectedCount=oper.deleteById(deleteEntityId);
		if(affectedCount<1){//删除失败咯
			throw new MyException(new Date(),"DeleteAction执行失败,entityType:"+entityType,"删除失败");
		}else{
			resultMap.put("tipInfo", "删除成功");
			actionUrl="tip.jsp";
			return resultMap;
		}
	}
}
