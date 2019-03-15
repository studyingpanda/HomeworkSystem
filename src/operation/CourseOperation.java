/**
 * operation�����������ݿ�ʵ����������İ�
 * ����CourseOperation JobOperation LessonOperation RoleOperation UserOperation WorkOperation
 * �������̫�����߼�������˴�ֻչʾ��CourseOperation
 */
package operation;
import inter.IOperation;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import database.MySQLHandler;
import entity.*;
import exception.MyException;
public class CourseOperation implements IOperation{
	//ѧ��ѡ��ʹ��begin
	public int selectCountByStudentId(int studentId) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		int re=0;
		try {
			//�˴���Ҫдselect *����Ϊ���ݿ⽫*ת��Ϊ�ñ����������϶���Ҫ�˷�ʱ��
			rs=hand.query("select count(course_id) as count from system_course where course_id"
					+" not in(select lesson_course from student_lesson l where l.lesson_user='"+studentId+"')");
			while(rs.next()){
				re=rs.getInt("count");
			}
			return re;
		} catch (Exception ex) {
			//�������ݿ����������쳣����ʱ�����������׳�����¼����־�з�������
			//����Ƶ�ʱ���Ҫ��ȷ��ʲô�����쳣Ҫ���ף�ʲô�쳣���׳�ֻ��¼
			new MyException(new Date(),ex.getMessage(),"CourseOperation.selectCountByStudentId�쳣");
			return 0;//ע��null��new Course()����ͬ��
		}finally{
			hand.sayGoodbye();
		}
	}
	public List selectPageByStudentId(int offset, int size,int studentId) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Course> list=new ArrayList<Course>();//����ֵ
		try {
			rs=hand.query("select Course_id,Course_name,user_id,user_name from "
					+" system_Course c,system_user u where c.Course_user=u.user_id "
					+" and c.course_id not in (select lesson_course from student_lesson l where l.lesson_user='"+studentId+"')"
					+" order by c.Course_id limit "+offset+","+size);
			while(rs.next()){ 
				Course one=new Course();//����ֵ�е�һ��
				one.setCourseId(rs.getInt("Course_id"));
				one.setCourseName(rs.getString("Course_name"));		
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setCourseUser(user);
				list.add(one);//��ӵ��б�
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"CourseOperation.selectPageByStudentId�쳣");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	//ѧ��ѡ��ʹ��end
	@Override
	public int selectCount() {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		int re=0;
		try {
			//�˴���Ҫдselect *����Ϊ���ݿ⽫*ת��Ϊ�ñ����������϶���Ҫ�˷�ʱ��
			rs=hand.query("select count(course_id) as count from system_course");
			while(rs.next()){
				re=rs.getInt("count");
			}
			return re;
		} catch (Exception ex) {
			//�������ݿ����������쳣����ʱ�����������׳�����¼����־�з�������
			//����Ƶ�ʱ���Ҫ��ȷ��ʲô�����쳣Ҫ���ף�ʲô�쳣���׳�ֻ��¼
			new MyException(new Date(),ex.getMessage(),"CourseOperation.selectCount�쳣");
			return 0;//ע��null��new Course()����ͬ��
		}finally{
			hand.sayGoodbye();
		}
	}
	public int selectCountByUserId(int userId) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		int re=0;
		try {
			//�˴���Ҫдselect *����Ϊ���ݿ⽫*ת��Ϊ�ñ����������϶���Ҫ�˷�ʱ��
			rs=hand.query("select count(course_id) as count from system_course where course_user='"+userId+"'");
			while(rs.next()){
				re=rs.getInt("count");
			}
			return re;
		} catch (Exception ex) {
			//�������ݿ����������쳣����ʱ�����������׳�����¼����־�з�������
			//����Ƶ�ʱ���Ҫ��ȷ��ʲô�����쳣Ҫ���ף�ʲô�쳣���׳�ֻ��¼
			new MyException(new Date(),ex.getMessage(),"CourseOperation.selectCount�쳣");
			return 0;//ע��null��new Course()����ͬ��
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public Object selectById(int id) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		Course one=new Course();//�����ѯ����Ϊ�գ���ͨ��one.getCourseId()==0���жϼ���
		try {
			//�˴���Ҫдselect *����Ϊ���ݿ⽫*ת��Ϊ�ñ����������϶���Ҫ�˷�ʱ��
			rs=hand.query("select Course_id,Course_name,user_id,user_name from "
					+"system_Course c,system_user u where c.Course_id='"+id+"' and c.Course_user=u.user_id");
			while(rs.next()){
				one.setCourseId(rs.getInt("Course_id"));
				one.setCourseName(rs.getString("Course_name"));		
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setCourseUser(user);
			}
			return one;
		} catch (Exception ex) {
			//�������ݿ����������쳣����ʱ�����������׳�����¼����־�з�������
			//����Ƶ�ʱ���Ҫ��ȷ��ʲô�����쳣Ҫ���ף�ʲô�쳣���׳�ֻ��¼
			new MyException(new Date(),ex.getMessage(),"CourseOperation.selectById�쳣");
			return null;//ע��null��new Course()����ͬ��
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public List selectAll() {//ע�ⷵ��ֵnull��list.size()==0������
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Course> list=new ArrayList<Course>();//����ֵ
		try {
			rs=hand.query("select Course_id,Course_name,user_id,user_name from "
					+"system_Course c,system_user u where c.Course_user=u.user_id");
			while(rs.next()){
				Course one=new Course();//����ֵ�е�һ��
				one.setCourseId(rs.getInt("Course_id"));
				one.setCourseName(rs.getString("Course_name"));		
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setCourseUser(user);
				list.add(one);//��ӵ��б�
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"CourseOperation.selectAll�쳣");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	public List selectAllByUserId(int userId) {//ע�ⷵ��ֵnull��list.size()==0������
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Course> list=new ArrayList<Course>();//����ֵ
		try {
			rs=hand.query("select Course_id,Course_name,user_id,user_name from "
					+"system_Course c,system_user u where c.Course_user=u.user_id"
					+" and u.user_id='"+userId+"'");
			while(rs.next()){
				Course one=new Course();//����ֵ�е�һ��
				one.setCourseId(rs.getInt("Course_id"));
				one.setCourseName(rs.getString("Course_name"));		
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setCourseUser(user);
				list.add(one);//��ӵ��б�
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"CourseOperation.selectAll�쳣");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public List selectPage(int offset, int size) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Course> list=new ArrayList<Course>();//����ֵ
		try {
			rs=hand.query("select Course_id,Course_name,user_id,user_name from "
					+" system_Course c,system_user u where c.Course_user=u.user_id "
					+" order by c.Course_id limit "+offset+","+size);
			while(rs.next()){ 
				Course one=new Course();//����ֵ�е�һ��
				one.setCourseId(rs.getInt("Course_id"));
				one.setCourseName(rs.getString("Course_name"));		
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setCourseUser(user);
				list.add(one);//��ӵ��б�
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"CourseOperation.selectPage�쳣");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	public List selectPageByUserId(int offset, int size,int userId) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Course> list=new ArrayList<Course>();//����ֵ
		try {
			rs=hand.query("select Course_id,Course_name,user_id,user_name from "
					+" system_Course c,system_user u where c.Course_user=u.user_id and user_id='"+userId+"'"
					+" order by c.Course_id limit "+offset+","+size);
			while(rs.next()){ 
				Course one=new Course();//����ֵ�е�һ��
				one.setCourseId(rs.getInt("Course_id"));
				one.setCourseName(rs.getString("Course_name"));		
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setCourseUser(user);
				list.add(one);//��ӵ��б�
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"CourseOperation.selectPage�쳣");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public int add(Object obj) {
		Course one=(Course)obj;
		MySQLHandler hand=new MySQLHandler();
		try {
			int re=hand.execute("insert into system_Course(Course_name,Course_user)"
					+" values('"+one.getCourseName()+"','"+one.getCourseUser().getUserId()+"')");
			return re;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"CourseOperation.add�쳣");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public int deleteById(int id) {
		MySQLHandler hand=new MySQLHandler();
		try {
			int re=hand.execute("delete from system_Course where Course_id='"+id+"'");
			return re;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"CourseOperation.deleteById�쳣");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public int update(Object obj) {
		Course one=(Course)obj;
		MySQLHandler hand=new MySQLHandler();
		try {
			int re=hand.execute("update system_Course set Course_name='"+one.getCourseName()
					+"',Course_user='"+one.getCourseUser().getUserId()
					+"' where Course_id='"+one.getCourseId()+"'");
			return re;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"CourseOperation.update�쳣");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
}
