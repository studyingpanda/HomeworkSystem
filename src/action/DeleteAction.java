package action;
import inter.IOperation;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import exception.MyException;
import factory.OperationFactory;
/***
 * ������ɾ�����Ӧ����
 * @author è��
 * @date 2017.2.9
 * @modify 2017.2.19 to remove context
 */
public class DeleteAction extends Action{
	protected int deleteEntityId=-1;
	public DeleteAction(String entityType,String operationType,int deleteEntityId){
		super(entityType,operationType);
		this.deleteEntityId=deleteEntityId;
	}
	@Override
	public Map<String, Object> execute() throws MyException {
		Map<String,Object> resultMap=new HashMap<String,Object>();
		//�����ͻ�ȡʵ�������
		IOperation oper=OperationFactory.createOperation(entityType);
		int affectedCount=oper.deleteById(deleteEntityId);
		if(affectedCount<1){//ɾ��ʧ�ܿ�
			throw new MyException(new Date(),"DeleteActionִ��ʧ��,entityType:"+entityType,"ɾ��ʧ��");
		}else{
			resultMap.put("tipInfo", "ɾ���ɹ�");
			actionUrl="tip.jsp";
			return resultMap;
		}
	}
}
