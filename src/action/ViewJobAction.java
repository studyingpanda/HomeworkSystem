package action;
import java.util.HashMap;
import java.util.Map;
import operation.JobOperation;
import util.Constant;
import entity.User;
import exception.MyException;
import factory.OperationFactory;
/***
 * �������鿴��ҵ�б�(���ݵ�¼�û���ɫ��ͬ)
 * @author è��
 * @date 2017.2.20
 */
public class ViewJobAction extends ViewAction{
	protected User sessionUser=null;
	public ViewJobAction(String entityType,String operationType,int page,User sessionUser){
		super(entityType,operationType,page);
		this.sessionUser=sessionUser;
	}
	@Override
	public Map<String,Object> execute() throws MyException {
		//����ֵ����
		Map<String,Object> resultMap=new HashMap<String,Object>();
		//��ȡ���ݿ��������
		JobOperation oper=(JobOperation)OperationFactory.createOperation(entityType);
		if(sessionUser.getUserRole().getRoleName().equals("У��")){//�鿴ȫ����ҵ
			return super.execute();
		}else if(sessionUser.getUserRole().getRoleName().equals("��ʦ")){
			//��ʦ����鿴�Լ��γ�����ɡ�δ���ĵ���ҵ
			total=oper.selectCountByTeacherId(sessionUser.getUserId(),"-1");
			size=Constant.PageSize.get(entityType);
			maxPage=total/size+((total%size)>0?1:0);
			if(page>maxPage)//���һҳʱ�����һҳ
				page=maxPage;
			if(page<1)//��һҳʱ�����һҳ
				page=1;
			offset=(page-1)*size;
			resultMap.put(entityType.toLowerCase()+"s",oper.selectPageByTeacherId(offset, size, sessionUser.getUserId(),"-1"));
			resultMap.put("currentPage", page);
			resultMap.put("maxPage", maxPage);
			actionUrl="jobManageByTeacher.jsp";
		}
		else if(sessionUser.getUserRole().getRoleName().equals("ѧ��")){
			//ѧ���鿴δ������ҵ
			total=oper.selectCountByStudentId(sessionUser.getUserId(),"-2");
			size=Constant.PageSize.get(entityType);
			maxPage=total/size+((total%size)>0?1:0);
			if(page>maxPage)//���һҳʱ�����һҳ
				page=maxPage;
			if(page<1)//��һҳʱ�����һҳ
				page=1;
			offset=(page-1)*size;
			resultMap.put(entityType.toLowerCase()+"s",oper.selectPageByStudentId(offset, size, sessionUser.getUserId(),"-2"));
			resultMap.put("currentPage", page);
			resultMap.put("maxPage", maxPage);
			actionUrl="jobManageByStudent.jsp";
		}
		return resultMap;
	}
}
