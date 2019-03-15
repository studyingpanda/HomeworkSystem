package action;
import inter.IOperation;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import entity.User;
import exception.MyException;
import factory.OperationFactory;
/***
 * 动作：点击保存按钮后对应动作
 * @author 猫哥
 * @date 2017.2.9
 * @modify 2017.2.19 to remove context
 */
public class SaveAction extends Action{
	protected Boolean isAddOrEdit;
	protected Object saveEntity=null;
	protected User sessionUser=null;
	public SaveAction(String entityType,String operationType,Boolean isAddOrEdit,Object saveEntity,User sessionUser){
		super(entityType,operationType);
		this.isAddOrEdit=isAddOrEdit;
		this.saveEntity=saveEntity;
		this.sessionUser=sessionUser;
	}
	@Override
	public Map<String,Object> execute() throws MyException {
		Map<String,Object> resultMap=new HashMap<String,Object>();
		IOperation oper=OperationFactory.createOperation(entityType);
		int affectedCount=-1;
		if(isAddOrEdit){//添加
			affectedCount=oper.add(saveEntity);
		}else{//修改
			affectedCount=oper.update(saveEntity);
		}
		if(affectedCount<1){//失败咯
			throw new MyException(new Date(),"SaveAction执行失败,entityType:"+entityType,"更新失败");
		}else{
			resultMap.put("tipInfo", "更新成功");
			actionUrl="tip.jsp";
			return resultMap;
		}
	}
}
