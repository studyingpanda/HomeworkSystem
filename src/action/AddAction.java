package action;
import java.util.HashMap;
import java.util.Map;
import exception.MyException;
/***
 * ���������������ť���Ӧ������ֱ����ת��Ӧʵ�����ҳ��
 * @author è��
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
