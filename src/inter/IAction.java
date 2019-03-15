/**
 * inter包：接口包，用于保存所有接口interface
 * IAction接口：定义Aciton动作类的规范
 * IOperation接口：定义Operation数据库实体操作类的规范
 */
package inter;
import java.util.Map;
import exception.MyException;
public interface IAction {
	//执行动作，并返回一些结果
	public Map<String, Object> execute()throws MyException;
}
