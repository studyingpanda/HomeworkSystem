package action;
import inter.IOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.Constant;
import exception.MyException;
import factory.OperationFactory;
/***
 * 动作：查看用户列表
 * @author 猫哥
 * @date 2017.2.9
 * @modify 2017.2.x(忘记了哪天) add 分页 by 猫哥
 * @modify 2017.2.19 to remove context
 */
public class ViewAction extends Action{
	//分别代表要显示的页码，每页显示个数，偏移数，总个数，最大页数
	protected int page,size,offset,total,maxPage;
	public ViewAction(String entityType,String operationType,int page){
		super(entityType,operationType);
		this.page=page;
	}
	@Override
	public Map<String,Object> execute() throws MyException {
		//返回值对象
		Map<String,Object> resultMap=new HashMap<String,Object>();
		//获取数据库操作对象
		IOperation oper=OperationFactory.createOperation(entityType);
		//处理分页
		total=oper.selectCount();
		size=Constant.PageSize.get(entityType);
		maxPage=total/size+((total%size)>0?1:0);
		if(page>maxPage)//最后一页时点击下一页
			page=maxPage;
		if(page<1)//第一页时点击上一页
			page=1;
		offset=(page-1)*size;
		//处理返回值
		actionUrl=entityType.toLowerCase()+"Manage.jsp";
		List result=oper.selectPage(offset, size);
		resultMap.put(entityType.toLowerCase()+"s",result);
		resultMap.put("currentPage", page);
		resultMap.put("maxPage", maxPage);
		return resultMap;
	}
}
