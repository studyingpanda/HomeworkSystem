/**
 * inter�����ӿڰ������ڱ������нӿ�interface
 * IAction�ӿڣ�����Aciton������Ĺ淶
 * IOperation�ӿڣ�����Operation���ݿ�ʵ�������Ĺ淶
 */
package inter;
import java.util.Map;
import exception.MyException;
public interface IAction {
	//ִ�ж�����������һЩ���
	public Map<String, Object> execute()throws MyException;
}
