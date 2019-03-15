package action;
import java.util.List;
import java.util.Map;
import operation.WorkOperation;
import util.Constant;
import exception.MyException;
import factory.OperationFactory;
/***
 * �������鿴��ҵ�б�(������ҵ����)
 * @author è��
 * @date 2017.2.15
 * @modify 2017.2.19 to remove context
 */
public class ViewWorkAction extends ViewAction{
	protected int courseId;//����course��id
	public ViewWorkAction(String entityType,String operationType,int page,int courseId){
		super(entityType,operationType,page);
		this.courseId=courseId;
	}
	@Override
	public Map<String,Object> execute() throws MyException {
		//���ø���������԰�actionUrl������
		Map<String,Object> resultMap=super.execute();
		//��ȡ���ݿ��������
		WorkOperation oper=(WorkOperation)OperationFactory.createOperation(entityType);
		//�����ҳ
		total=oper.selectCountByCourseId(courseId);
		size=Constant.PageSize.get(entityType);
		maxPage=total/size+((total%size)>0?1:0);
		if(page>maxPage)//���һҳʱ�����һҳ
			page=maxPage;
		if(page<1)//��һҳʱ�����һҳ
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
