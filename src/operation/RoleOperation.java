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
			//�˴���Ҫдselect *����Ϊ���ݿ⽫*ת��Ϊ�ñ����������϶���Ҫ�˷�ʱ��
			rs=hand.query("select count(role_id) as count  from system_role");
			while(rs.next()){
				re=rs.getInt("count");
			}
			return re;
		} catch (Exception ex) {
			//�������ݿ����������쳣����ʱ�����������׳�����¼����־�з�������
			//����Ƶ�ʱ���Ҫ��ȷ��ʲô�����쳣Ҫ���ף�ʲô�쳣���׳�ֻ��¼
			new MyException(new Date(),ex.getMessage(),"RoleOperation.selectCount�쳣");
			return 0;//ע��null��new Course()����ͬ��
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public Object selectById(int id) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		Role one=new Role();//�����ѯ����Ϊ�գ���ͨ��one.getRoleId()==0���жϼ���
		try {
			//�˴���Ҫдselect *����Ϊ���ݿ⽫*ת��Ϊ�ñ����������϶���Ҫ�˷�ʱ��
			rs=hand.query("select role_id,role_name from system_role r where r.role_id='"+id+"'");
			while(rs.next()){
				one.setRoleId(rs.getInt("role_id"));
				one.setRoleName(rs.getString("role_name"));
			}
			return one;
		} catch (Exception ex) {
			//�������ݿ����������쳣����ʱ�����������׳�����¼����־�з�������
			//����Ƶ�ʱ���Ҫ��ȷ��ʲô�����쳣Ҫ���ף�ʲô�쳣���׳�ֻ��¼
			new MyException(new Date(),ex.getMessage(),"RoleOperation.selectById�쳣");
			return null;//ע��null��new Role()����ͬ��
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public List selectAll() {//ע�ⷵ��ֵnull��list.size()==0������
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Role> list=new ArrayList<Role>();//����ֵ
		try {
			rs=hand.query("select role_id,role_name from system_role r");
			while(rs.next()){
				Role one=new Role();//����ֵ�е�һ��
				one.setRoleId(rs.getInt("role_id"));
				one.setRoleName(rs.getString("role_name"));
				list.add(one);//��ӵ��б�
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"RoleOperation.selectAll�쳣");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public List selectPage(int offset,int size) {//ע�ⷵ��ֵnull��list.size()==0������
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Role> list=new ArrayList<Role>();//����ֵ
		try {
			rs=hand.query("select role_id,role_name from system_role r "
					+" order by r.role_id limit "+offset+","+size);
			while(rs.next()){
				Role one=new Role();//����ֵ�е�һ��
				one.setRoleId(rs.getInt("role_id"));
				one.setRoleName(rs.getString("role_name"));
				list.add(one);//��ӵ��б�
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"RoleOperation.selectPage�쳣");
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
			new MyException(new Date(),ex.getMessage(),"RoleOperation.add�쳣");
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
			new MyException(new Date(),ex.getMessage(),"RoleOperation.deleteById�쳣");
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
			new MyException(new Date(),ex.getMessage(),"RoleOperation.update�쳣");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
}
