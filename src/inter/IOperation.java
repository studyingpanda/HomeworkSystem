package inter;
import java.util.List;
//规范了用于执行对象对应数据库表基本操作的类
public interface IOperation {
	public int selectCount();//获取个数
	public List selectAll();//选取表中所有数据
	public List selectPage(int offset,int size);//分页显示，第一个参数为起始位置（按id排序），第二个参数为每页显示个数
	public Object selectById(int id);//按id获取一条记录
	public int add(Object obj);//添加一条数据
	public int deleteById(int id);//按id删除一条记录
	public int update(Object obj);//按obj对象的信息修改一条记录(以obj的id标记需要修改的记录)
}