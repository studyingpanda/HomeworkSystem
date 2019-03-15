package action;
import java.util.List;
import java.util.Map;
import operation.WorkOperation;
import util.Constant;
import exception.MyException;
import factory.OperationFactory;
/***
 * 动作：查看作业列表(根据作业名称)
 * @author 猫哥
 * @date 2017.2.15
 * @modify 2017.2.19 to remove context
 */
public class ViewWorkAction extends ViewAction{
	protected int courseId;//所属course的id
	public ViewWorkAction(String entityType,String operationType,int page,int courseId){
		super(entityType,operationType,page);
		this.courseId=courseId;
	}
	@Override
	public Map<String,Object> execute() throws MyException {
		//利用父类起码可以把actionUrl设置了
		Map<String,Object> resultMap=super.execute();
		//获取数据库操作对象
		WorkOperation oper=(WorkOperation)OperationFactory.createOperation(entityType);
		//处理分页
		total=oper.selectCountByCourseId(courseId);
		size=Constant.PageSize.get(entityType);
		maxPage=total/size+((total%size)>0?1:0);
		if(page>maxPage)//最后一页时点击下一页
			page=maxPage;
		if(page<1)//第一页时点击上一页
			page=1;
		offset=(page-1)*size;
		List result=oper.selectPageByCourseId(offset, size,courseId);
		resultMap.put(entityType.toLowerCase()+"s",result);
		resultMap.put("currentPage", page);
		resultMap.put("maxPage", maxPage);
		resultMap.put("byCourseId", courseId);
		return resultMap;
	}
}
