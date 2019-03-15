package action;
import inter.IOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.Constant;
import exception.MyException;
import factory.OperationFactory;
/***
 * �������鿴�û��б�
 * @author è��
 * @date 2017.2.9
 * @modify 2017.2.x(����������) add ��ҳ by è��
 * @modify 2017.2.19 to remove context
 */
public class ViewAction extends Action{
	//�ֱ����Ҫ��ʾ��ҳ�룬ÿҳ��ʾ������ƫ�������ܸ��������ҳ��
	protected int page,size,offset,total,maxPage;
	public ViewAction(String entityType,String operationType,int page){
		super(entityType,operationType);
		this.page=page;
	}
	@Override
	public Map<String,Object> execute() throws MyException {
		//����ֵ����
		Map<String,Object> resultMap=new HashMap<String,Object>();
		//��ȡ���ݿ��������
		IOperation oper=OperationFactory.createOperation(entityType);
		//�����ҳ
		total=oper.selectCount();
		size=Constant.PageSize.get(entityType);
		maxPage=total/size+((total%size)>0?1:0);
		if(page>maxPage)//���һҳʱ�����һҳ
			page=maxPage;
		if(page<1)//��һҳʱ�����һҳ
			page=1;
		offset=(page-1)*size;
		//������ֵ
		actionUrl=entityType.toLowerCase()+"Manage.jsp";
		List result=oper.selectPage(offset, size);
		resultMap.put(entityType.toLowerCase()+"s",result);
		resultMap.put("currentPage", page);
		resultMap.put("maxPage", maxPage);
		return resultMap;
	}
}
