package action;
import java.util.HashMap;
import java.util.Map;
import operation.CourseOperation;
import util.Constant;
import entity.User;
import exception.MyException;
import factory.OperationFactory;
/***
 * �������鿴�γ��б�(���ݵ�¼�û���ɫ��ͬ)
 * @author è��
 * @date 2017.2.15
 * @modify 2017.2.19 to remove context
 */
public class ViewCourseAction extends ViewAction{
	protected User sessionUser=null;
	public ViewCourseAction(String entityType,String operationType,int page,User sessionUser){
		super(entityType,operationType,page);
		this.sessionUser=sessionUser;
	}
	@Override
	public Map<String,Object> execute() throws MyException {
		//����ֵ����
		Map<String,Object> resultMap=new HashMap<String,Object>();
		//��ȡ���ݿ��������
		CourseOperation oper=(CourseOperation)OperationFactory.createOperation(entityType);
		if(sessionUser.getUserRole().getRoleName().equals("У��")){//�鿴ȫ���γ�
			return super.execute();
		}else if(sessionUser.getUserRole().getRoleName().equals("��ʦ")){
			//��ʦ����֮�����ڲ鿴�γ�ʱ��ֻ�ܿ��Լ������Ŀγ̣�ע���ҳҲҪ����
			total=oper.selectCountByUserId(sessionUser.getUserId());
			size=Constant.PageSize.get(entityType);
			maxPage=total/size+((total%size)>0?1:0);
			if(page>maxPage)//���һҳʱ�����һҳ
				page=maxPage;
			if(page<1)//��һҳʱ�����һҳ
				page=1;
			offset=(page-1)*size;
			resultMap.put(entityType.toLowerCase()+"s",oper.selectPageByUserId(offset, size, sessionUser.getUserId()));
			resultMap.put("currentPage", page);
			resultMap.put("maxPage", maxPage);
			actionUrl="courseManageByTeacher.jsp";
		}
		else if(sessionUser.getUserRole().getRoleName().equals("ѧ��")){//ѡ��
			//��ʦ����֮�����ڲ鿴�γ�ʱ��ֻ�ܿ��Լ������Ŀγ̣�ע���ҳҲҪ����
			total=oper.selectCountByStudentId(sessionUser.getUserId());
			size=Constant.PageSize.get(entityType);
			maxPage=total/size+((total%size)>0?1:0);
			if(page>maxPage)//���һҳʱ�����һҳ
				page=maxPage;
			if(page<1)//��һҳʱ�����һҳ
				page=1;
			offset=(page-1)*size;
			resultMap.put(entityType.toLowerCase()+"s",oper.selectPageByStudentId(offset, size, sessionUser.getUserId()));
			resultMap.put("currentPage", page);
			resultMap.put("maxPage", maxPage);
			actionUrl="courseManageByStudent.jsp";
		}
		return resultMap;
	}
}
