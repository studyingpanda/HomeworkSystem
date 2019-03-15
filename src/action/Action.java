/**
 * action�����������ж�����
 * Action�������ж�����������
 * ���嶯���������AddAction AddWorkAction DeleteAction EditAction EditJobContentAction EditJobScoreAction
 * LoginAction SaveAction SaveJobAction SaveLessonAction SaveWorkAction ViewAction ViewCourseAction
 * ViewJobAction ViewWorkAction Action��ҵ����ģ�ȫ���г�
 */
package action;
import inter.IAction;
/**
 * ���ж����ĳ������Action
 * @author è��
 * @date 2017.2.19
 * @modify 2017.2.19 to remove context
 */
public abstract class Action implements IAction{
	//�����ر�����������protected��������ʹ��
	protected String entityType=null;
	protected String operationType=null;
	protected String actionUrl=null;
	public Action(String entityType,String operationType){
		this.entityType=entityType;
		this.operationType=operationType;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getActionUrl() {
		return actionUrl;
	}
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
}
