package action;
import java.util.HashMap;
import java.util.Map;
import exception.MyException;
/***
 * 动作：点击新增按钮后对应动作，直接跳转对应实体更新页面
 * @author 猫哥
 * @date 2017.2.9
 * @modify  2017.2.19 to remove context
 */
public class AddAction extends Action{
	public AddAction(String entityType,String operationType){
		super(entityType,operationType);
	}
	@Override
	public Map<String, Object> execute() throws MyException {
		Map<String,Object> resultMap=new HashMap<String,Object>();
		actionUrl=entityType.toLowerCase()+"Update.jsp";
		return resultMap;
	}
}
