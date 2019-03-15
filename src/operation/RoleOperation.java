package operation;
import inter.IOperation;
import java.sql.ResultSet;
import java.util.*;
import database.MySQLHandler;
import entity.*;
import exception.MyException;
public class RoleOperation implements IOperation{
	@Override
	public int selectCount() {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		int re=0;
		try {
			//此处不要写select *，因为数据库将*转换为该表所有列名肯定需要浪费时间
			rs=hand.query("select count(role_id) as count  from system_role");
			while(rs.next()){
				re=rs.getInt("count");
			}
			return re;
		} catch (Exception ex) {
			//对于数据库操作层面的异常，此时不予以向外抛出，记录在日志中分析即可
			//在设计的时候就要明确，什么类型异常要外抛，什么异常不抛出只记录
			new MyException(new Date(),ex.getMessage(),"RoleOperation.selectCount异常");
			return 0;//注意null和new Course()并不同！
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public Object selectById(int id) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		Role one=new Role();//如果查询内容为空，则通过one.getRoleId()==0来判断即可
		try {
			//此处不要写select *，因为数据库将*转换为该表所有列名肯定需要浪费时间
			rs=hand.query("select role_id,role_name from system_role r where r.role_id='"+id+"'");
			while(rs.next()){
				one.setRoleId(rs.getInt("role_id"));
				one.setRoleName(rs.getString("role_name"));
			}
			return one;
		} catch (Exception ex) {
			//对于数据库操作层面的异常，此时不予以向外抛出，记录在日志中分析即可
			//在设计的时候就要明确，什么类型异常要外抛，什么异常不抛出只记录
			new MyException(new Date(),ex.getMessage(),"RoleOperation.selectById异常");
			return null;//注意null和new Role()并不同！
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public List selectAll() {//注意返回值null和list.size()==0的区别
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Role> list=new ArrayList<Role>();//返回值
		try {
			rs=hand.query("select role_id,role_name from system_role r");
			while(rs.next()){
				Role one=new Role();//返回值中的一个
				one.setRoleId(rs.getInt("role_id"));
				one.setRoleName(rs.getString("role_name"));
				list.add(one);//添加到列表
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"RoleOperation.selectAll异常");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public List selectPage(int offset,int size) {//注意返回值null和list.size()==0的区别
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Role> list=new ArrayList<Role>();//返回值
		try {
			rs=hand.query("select role_id,role_name from system_role r "
					+" order by r.role_id limit "+offset+","+size);
			while(rs.next()){
				Role one=new Role();//返回值中的一个
				one.setRoleId(rs.getInt("role_id"));
				one.setRoleName(rs.getString("role_name"));
				list.add(one);//添加到列表
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"RoleOperation.selectPage异常");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public int add(Object obj) {
		Role one=(Role)obj;
		MySQLHandler hand=new MySQLHandler();
		try {
			int re=hand.execute("insert into system_role(role_name) values('"+one.getRoleName()+"')");
			return re;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"RoleOperation.add异常");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public int deleteById(int id) {
		MySQLHandler hand=new MySQLHandler();
		try {
			int re=hand.execute("delete from system_role where role_id='"+id+"'");
			return re;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"RoleOperation.deleteById异常");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public int update(Object obj) {
		Role one=(Role)obj;
		MySQLHandler hand=new MySQLHandler();
		try {
			int re=hand.execute("update system_role set role_name='"+one.getRoleName()
					+"' where role_id='"+one.getRoleId()+"'");
			return re;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"RoleOperation.update异常");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
}
