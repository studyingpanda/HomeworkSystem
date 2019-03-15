package operation;
import inter.IOperation;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import database.MySQLHandler;
import entity.*;
import exception.MyException;
public class UserOperation implements IOperation{
	@Override
	public int selectCount() {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		int re=0;
		try {
			//此处不要写select *，因为数据库将*转换为该表所有列名肯定需要浪费时间
			rs=hand.query("select count(user_id) as count from system_user");
			while(rs.next()){
				re=rs.getInt("count");
			}
			return re;
		} catch (Exception ex) {
			//对于数据库操作层面的异常，此时不予以向外抛出，记录在日志中分析即可
			//在设计的时候就要明确，什么类型异常要外抛，什么异常不抛出只记录
			new MyException(new Date(),ex.getMessage(),"UserOperation.selectCount异常");
			return 0;//注意null和new Course()并不同！
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public Object selectById(int i) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		User one=new User();//如果查询内容为空，则通过one.getUserId()==0来判断即可
		try {
			//此处不要写select *，因为数据库将*转换为该表所有列名肯定需要浪费时间
			rs=hand.query("select user_id,user_name,user_password,role_id,role_name from "
					+"system_user u,system_role r where u.user_id='"+i+"' and u.user_role=r.role_id");
			while(rs.next()){
				one.setUserId(rs.getInt("User_id"));
				one.setUserName(rs.getString("User_name"));
				one.setUserPassword(rs.getString("user_password"));
				Role role=new Role();
				role.setRoleId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));
				one.setUserRole(role);
			}
			return one;
		} catch (Exception ex) {
			//对于数据库操作层面的异常，此时不予以向外抛出，记录在日志中分析即可
			//在设计的时候就要明确，什么类型异常要外抛，什么异常不抛出只记录
			new MyException(new Date(),ex.getMessage(),"UserOperation.selectById异常");
			return null;//注意null和new User()并不同！
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public List selectAll() {//注意返回值null和list.size()==0的区别
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList<User>();//返回值
		try {
			rs=hand.query("select user_id,user_name,user_password,role_id,role_name from "
					+"system_user u,system_role r where u.user_role=r.role_id");
			while(rs.next()){
				User one=new User();//返回值中的一个
				one.setUserId(rs.getInt("User_id"));
				one.setUserName(rs.getString("User_name"));
				one.setUserPassword(rs.getString("user_password"));
				Role role=new Role();
				role.setRoleId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));
				one.setUserRole(role);
				list.add(one);//添加到列表
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"UserOperation.selectAll异常");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public List selectPage(int offset,int size) {//注意返回值null和list.size()==0的区别
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList<User>();//返回值
		try {
			rs=hand.query("select user_id,user_name,user_password,role_id,role_name from "
					+"system_user u,system_role r where u.user_role=r.role_id "
					+" order by u.user_id limit "+offset+","+size);
			while(rs.next()){
				User one=new User();//返回值中的一个
				one.setUserId(rs.getInt("User_id"));
				one.setUserName(rs.getString("User_name"));
				one.setUserPassword(rs.getString("user_password"));
				Role role=new Role();
				role.setRoleId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));
				one.setUserRole(role);
				list.add(one);//添加到列表
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"UserOperation.selectPage异常");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	/*需要注意添加用户时，我们只用到了关联表的id*/
	@Override
	public int add(Object obj) {
		User one=(User)obj;
		MySQLHandler hand=new MySQLHandler();
		try {
			int re=hand.execute("insert into system_User(User_name,user_password,user_role)"
					+" values('"+one.getUserName()+"','"+one.getUserPassword()+"','"+one.getUserRole().getRoleId()+"')");
			return re;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"UserOperation.add异常");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
	/*这个方法我是从RoleOperation中拷贝过来的，然后使用User替换了Role，此时定睛一看，竟无需改变*/
	@Override
	public int deleteById(int id) {
		MySQLHandler hand=new MySQLHandler();
		try {
			int re=hand.execute("delete from system_User where User_id='"+id+"'");
			return re;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"UserOperation.deleteById异常");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
	/*此处需要注意修改user_role的逻辑，如果设计的是修改用户信息时同步修改角色，可以就如下写代码
	 而如果修改用户信息不修改角色，修改角色的功能是单独的菜单，那么可单独增加updateUserRole方法
	 猫哥建议直接在update里都写好，如果有区分的功能菜单，直接在在command命令层写不同的代码即可*/
	@Override
	public int update(Object obj) {
		User one=(User)obj;
		MySQLHandler hand=new MySQLHandler();
		try {
			int re=hand.execute("update system_User set User_name='"+one.getUserName()
					+"',user_password='"+one.getUserPassword()+"',user_role='"+one.getUserRole().getRoleId()
					+"' where User_id='"+one.getUserId()+"'");
			return re;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"UserOperation.update异常");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}

}
