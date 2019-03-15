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
			//�˴���Ҫдselect *����Ϊ���ݿ⽫*ת��Ϊ�ñ����������϶���Ҫ�˷�ʱ��
			rs=hand.query("select count(user_id) as count from system_user");
			while(rs.next()){
				re=rs.getInt("count");
			}
			return re;
		} catch (Exception ex) {
			//�������ݿ����������쳣����ʱ�����������׳�����¼����־�з�������
			//����Ƶ�ʱ���Ҫ��ȷ��ʲô�����쳣Ҫ���ף�ʲô�쳣���׳�ֻ��¼
			new MyException(new Date(),ex.getMessage(),"UserOperation.selectCount�쳣");
			return 0;//ע��null��new Course()����ͬ��
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public Object selectById(int i) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		User one=new User();//�����ѯ����Ϊ�գ���ͨ��one.getUserId()==0���жϼ���
		try {
			//�˴���Ҫдselect *����Ϊ���ݿ⽫*ת��Ϊ�ñ����������϶���Ҫ�˷�ʱ��
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
			//�������ݿ����������쳣����ʱ�����������׳�����¼����־�з�������
			//����Ƶ�ʱ���Ҫ��ȷ��ʲô�����쳣Ҫ���ף�ʲô�쳣���׳�ֻ��¼
			new MyException(new Date(),ex.getMessage(),"UserOperation.selectById�쳣");
			return null;//ע��null��new User()����ͬ��
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public List selectAll() {//ע�ⷵ��ֵnull��list.size()==0������
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList<User>();//����ֵ
		try {
			rs=hand.query("select user_id,user_name,user_password,role_id,role_name from "
					+"system_user u,system_role r where u.user_role=r.role_id");
			while(rs.next()){
				User one=new User();//����ֵ�е�һ��
				one.setUserId(rs.getInt("User_id"));
				one.setUserName(rs.getString("User_name"));
				one.setUserPassword(rs.getString("user_password"));
				Role role=new Role();
				role.setRoleId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));
				one.setUserRole(role);
				list.add(one);//��ӵ��б�
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"UserOperation.selectAll�쳣");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public List selectPage(int offset,int size) {//ע�ⷵ��ֵnull��list.size()==0������
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList<User>();//����ֵ
		try {
			rs=hand.query("select user_id,user_name,user_password,role_id,role_name from "
					+"system_user u,system_role r where u.user_role=r.role_id "
					+" order by u.user_id limit "+offset+","+size);
			while(rs.next()){
				User one=new User();//����ֵ�е�һ��
				one.setUserId(rs.getInt("User_id"));
				one.setUserName(rs.getString("User_name"));
				one.setUserPassword(rs.getString("user_password"));
				Role role=new Role();
				role.setRoleId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));
				one.setUserRole(role);
				list.add(one);//��ӵ��б�
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"UserOperation.selectPage�쳣");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	/*��Ҫע������û�ʱ������ֻ�õ��˹������id*/
	@Override
	public int add(Object obj) {
		User one=(User)obj;
		MySQLHandler hand=new MySQLHandler();
		try {
			int re=hand.execute("insert into system_User(User_name,user_password,user_role)"
					+" values('"+one.getUserName()+"','"+one.getUserPassword()+"','"+one.getUserRole().getRoleId()+"')");
			return re;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"UserOperation.add�쳣");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
	/*����������Ǵ�RoleOperation�п��������ģ�Ȼ��ʹ��User�滻��Role����ʱ����һ����������ı�*/
	@Override
	public int deleteById(int id) {
		MySQLHandler hand=new MySQLHandler();
		try {
			int re=hand.execute("delete from system_User where User_id='"+id+"'");
			return re;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"UserOperation.deleteById�쳣");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
	/*�˴���Ҫע���޸�user_role���߼��������Ƶ����޸��û���Ϣʱͬ���޸Ľ�ɫ�����Ծ�����д����
	 ������޸��û���Ϣ���޸Ľ�ɫ���޸Ľ�ɫ�Ĺ����ǵ����Ĳ˵�����ô�ɵ�������updateUserRole����
	 è�罨��ֱ����update�ﶼд�ã���������ֵĹ��ܲ˵���ֱ������command�����д��ͬ�Ĵ��뼴��*/
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
			new MyException(new Date(),ex.getMessage(),"UserOperation.update�쳣");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}

}
