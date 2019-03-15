package servlet;
import javax.servlet.http.HttpServletRequest;
import entity.User;
import factory.EntityFactory;
import action.*;
/**
 * ʵ�ʵĿ�����
 * @author è��
 * @date 2017.2.11
 * @modify 2017.2.19 ȥ��context����Action���о�����Ҫ�����б� 
 * ActionController��ʱֻ�踺�����Action�����ṩ��������
 */
public class ActionController {
	//ֱ��װ���Action
	public static Action assemblyAction(HttpServletRequest request){
		//�����ر�����
		String operationType=request.getParameter("method");
		String entityType=request.getParameter("entityType");
		//����Action��Ҫװ��
		if(operationType.equals("delete")){
			String entityId=request.getParameter("entityId");
			//����������null��ʾ����Actionȥ����
			return new DeleteAction(entityType,operationType,Integer.parseInt(entityId));
		}
		else if(operationType.equals("view")){
			//����view����ҳ
			String inputPage=request.getParameter("page");
			int page=-1;
			if(inputPage==null||"".equals(inputPage)){
				page=1;
			}else{
				page=Integer.parseInt(inputPage);
			}
			//����鿴�߼�
			if(entityType.equals("Work")){//�鿴��ҵ����Ҫ�γ̱��
				return new ViewWorkAction(entityType,operationType,page,Integer.parseInt(request.getParameter("byCourseId")));
			}else if(entityType.equals("Course")){//�鿴�γ̣���Ҫ��¼��Ա��Ϣ
				return new ViewCourseAction(entityType,operationType,page,(User)request.getSession().getAttribute("sessionUser"));
			}else if(entityType.equals("Job")){//�鿴��ҵ��ѧ���鿴δ���ģ���ʦ�鿴������δ���ĵ�
				return new ViewJobAction(entityType,operationType,page,(User)request.getSession().getAttribute("sessionUser"));
			}
			//�����鿴
			return new ViewAction(entityType,operationType,page);
		}
		else if(operationType.equals("add")){
			if(entityType.equals("Work")){//�����ҵʱ����ҪЯ����ʦ�����Ŀγ��б��˴�ֻ����Ҫ��¼�û���ݼ���
				User user=(User)request.getSession().getAttribute("sessionUser");
				return new AddWorkAction(entityType,operationType,user.getUserId());
			}
			return new AddAction(entityType,operationType);
		}
		else if(operationType.equals("edit")){
			//����༭����Ҫ��ʾ���༭�˵���Ϣ������editҪЯ��id
			String entityId=request.getParameter("entityId");
			return new EditAction(entityType,operationType,Integer.parseInt(entityId));
		}
		else if(operationType.equals("editJobContent")){//����ҵ
			String entityId=request.getParameter("entityId");
			return new EditJobContentAction(entityType,operationType,Integer.parseInt(entityId));
		}
		else if(operationType.equals("editJobScore")){//������ҵ
			String entityId=request.getParameter("entityId");
			return new EditJobScoreAction(entityType,operationType,Integer.parseInt(entityId));
		}
		else if(operationType.equals("save")){
			//ע�⣬�������󣬲�ͬ��ʵ����ҪЯ����ͬ�Ĳ����б����Բ���createEntityFromRequest������ʵ��
			Object saveEntity=EntityFactory.createEntityFromRequest(entityType, request);
			Boolean isAddOrEdit=null;
			//��Ҫ����entityId�ж������������޸�
			String id=request.getParameter("entityId");
			if(id==null||"".equals(id)){
				isAddOrEdit=true;
			}else{
				isAddOrEdit=false;
			}
			//��ΪSave֮��Ҫ�����б���Щ�б���Ҫ���ݵ�¼�û������ʾ������
			User sessionUser=(User)request.getSession().getAttribute("sessionUser");
			//ע����Ϊwork�Ǹ��γ���صģ��޸�work��Ҫ��ת����ǰ�γ�������ҵ�б�������Ӹ�������
			if(entityType.equals("Work")){
				return new SaveWorkAction(entityType,operationType,isAddOrEdit,saveEntity,sessionUser);
			}else if (entityType.equals("Job")){
				return new SaveJobAction(entityType,operationType,isAddOrEdit,saveEntity,sessionUser);
			}else if (entityType.equals("Lesson")){
				return new SaveLessonAction(entityType,operationType,isAddOrEdit,saveEntity,sessionUser);
			}
			return new SaveAction(entityType,operationType,isAddOrEdit,saveEntity,sessionUser);
		}
		else if(operationType.equals("login")){
			return new LoginAction(entityType,operationType,request.getParameter("userId"),request.getParameter("userPassword"));
		}
		return null;
	}
}
