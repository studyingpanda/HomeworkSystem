package action;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import operation.UserOperation;
import util.Constant;
import entity.User;
import exception.MyException;
import factory.OperationFactory;
/**
 * 登录动作
 * @author Administrator
 * @modify 2017.2.19 to remove context
 */
public class LoginAction extends Action{
	protected String inputUserId=null;
	protected String inputUserPassword=null;
	public LoginAction(String entityType,String operationType,String inputUserId,String inputUserPassword){
		super(entityType,operationType);
		this.inputUserId=inputUserId;
		this.inputUserPassword=inputUserPassword;
	}
	@Override
	public Map<String, Object> execute() throws MyException {
		UserOperation oper=(UserOperation)OperationFactory.createOperation(entityType);
		int userId=-1;
		try{
			userId=Integer.parseInt(inputUserId);
		}catch(Exception ex){
			throw new MyException(new Date(),ex.toString(),"登录失败！");
		}
		User realUser=(User)oper.selectById(userId);
		if(realUser.getUserId()!=0&&realUser.getUserPassword().equals(inputUserPassword)){
			//设置返回参数
			Map<String,Object> resultMap=new HashMap<String,Object>();
			//如果需要Servlet将返回值放入session作用域，则此处命名时以session开头即可
			resultMap.put("sessionUser", realUser);
			//登录用户角色对应菜单
			resultMap.put("sessionRoleMenu", Constant.RoleMenu.get(realUser.getUserRole().getRoleName()));
			actionUrl="tip.jsp";
			return resultMap;
		}
		else{
			throw new MyException(new Date(),"LoginAction Error","登录失败！");
		}
	}
}
