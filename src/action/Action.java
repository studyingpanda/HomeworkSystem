/**
 * action包：包含所有动作类
 * Action类是所有动作类抽象基类
 * 具体动作类包含：AddAction AddWorkAction DeleteAction EditAction EditJobContentAction EditJobScoreAction
 * LoginAction SaveAction SaveJobAction SaveLessonAction SaveWorkAction ViewAction ViewCourseAction
 * ViewJobAction ViewWorkAction Action是业务核心，全部列出
 */
package action;
import inter.IAction;
/**
 * 所有动作的抽象基类Action
 * @author 猫哥
 * @date 2017.2.19
 * @modify 2017.2.19 to remove context
 */
public abstract class Action implements IAction{
	//三个必备参数，采用protected便于子类使用
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
