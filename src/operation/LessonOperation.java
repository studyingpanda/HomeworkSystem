package operation;
import inter.IOperation;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import database.MySQLHandler;
import entity.*;
import exception.MyException;
public class LessonOperation implements IOperation{
	@Override
	public int selectCount() {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		int re=0;
		try {
			//�˴���Ҫдselect *����Ϊ���ݿ⽫*ת��Ϊ�ñ����������϶���Ҫ�˷�ʱ��
			rs=hand.query("select count(lesson_id) as count  from student_lesson");
			while(rs.next()){
				re=rs.getInt("count");
			}
			return re;
		} catch (Exception ex) {
			//�������ݿ����������쳣����ʱ�����������׳�����¼����־�з�������
			//����Ƶ�ʱ���Ҫ��ȷ��ʲô�����쳣Ҫ���ף�ʲô�쳣���׳�ֻ��¼
			new MyException(new Date(),ex.getMessage(),"LessonOperation.selectCount�쳣");
			return 0;//ע��null��new Course()����ͬ��
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public Object selectById(int id) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		Lesson one=new Lesson();//�����ѯ����Ϊ�գ���ͨ��one.getLessonId()==0���жϼ���
		try {
			//�˴���Ҫдselect *����Ϊ���ݿ⽫*ת��Ϊ�ñ����������϶���Ҫ�˷�ʱ��
			rs=hand.query("select Lesson_id,user_id,user_name,course_id,course_name from "
					+" student_Lesson l,system_user u,system_course c"
					+" where l.Lesson_id='"+id+"' and l.Lesson_user=u.user_id and l.Lesson_course=c.course_id");
			while(rs.next()){
				one.setLessonId(rs.getInt("Lesson_id"));
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setLessonUser(user);
				Course course=new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				one.setLessonCourse(course);
			}
			return one;
		} catch (Exception ex) {
			//�������ݿ����������쳣����ʱ�����������׳�����¼����־�з�������
			//����Ƶ�ʱ���Ҫ��ȷ��ʲô�����쳣Ҫ���ף�ʲô�쳣���׳�ֻ��¼
			new MyException(new Date(),ex.getMessage(),"LessonOperation.selectById�쳣");
			return null;//ע��null��new Lesson()����ͬ��
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public List selectAll() {//ע�ⷵ��ֵnull��list.size()==0������
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Lesson> list=new ArrayList<Lesson>();//����ֵ
		try {
			rs=hand.query("select Lesson_id,user_id,user_name,course_id,course_name from "
					+" student_Lesson l,system_user u,system_course c"
					+" where l.Lesson_user=u.user_id and l.Lesson_course=c.course_id");
			while(rs.next()){
				Lesson one=new Lesson();//����ֵ�е�һ��
				one.setLessonId(rs.getInt("Lesson_id"));
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setLessonUser(user);
				Course course=new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				one.setLessonCourse(course);
				list.add(one);//��ӵ��б�
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"LessonOperation.selectAll�쳣");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	public List selectByCourseId(int courseId) {//ע�ⷵ��ֵnull��list.size()==0������
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Lesson> list=new ArrayList<Lesson>();//����ֵ
		try {
			rs=hand.query("select Lesson_id,user_id,user_name,course_id,course_name from "
					+" student_Lesson l,system_user u,system_course c"
					+" where l.Lesson_user=u.user_id and l.Lesson_course=c.course_id"
					+" and c.course_id='"+courseId+"'");
			while(rs.next()){
				Lesson one=new Lesson();//����ֵ�е�һ��
				one.setLessonId(rs.getInt("Lesson_id"));
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setLessonUser(user);
				Course course=new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				one.setLessonCourse(course);
				list.add(one);//��ӵ��б�
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"LessonOperation.selectAll�쳣");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public List selectPage(int offset,int size) {//ע�ⷵ��ֵnull��list.size()==0������
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Lesson> list=new ArrayList<Lesson>();//����ֵ
		try {
			rs=hand.query("select Lesson_id,user_id,user_name,course_id,course_name from "
					+" student_Lesson l,system_user u,system_course c"
					+" where l.Lesson_user=u.user_id and l.Lesson_course=c.course_id"
					+" order by l.lesson_id limit "+offset+","+size);
			while(rs.next()){
				Lesson one=new Lesson();//����ֵ�е�һ��
				one.setLessonId(rs.getInt("Lesson_id"));
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setLessonUser(user);
				Course course=new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				one.setLessonCourse(course);
				list.add(one);//��ӵ��б�
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"LessonOperation.selectPage�쳣");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public int add(Object obj) {
		Lesson one=(Lesson)obj;
		MySQLHandler hand=new MySQLHandler();
		try {
			int re=hand.execute("insert into student_Lesson(Lesson_user,Lesson_course)"
					+" values('"+one.getLessonUser().getUserId()+"','"+one.getLessonCourse().getCourseId()+"')");
			return re;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"LessonOperation.add�쳣");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public int deleteById(int id) {
		MySQLHandler hand=new MySQLHandler();
		try {
			int re=hand.execute("delete from system_Lesson where Lesson_id='"+id+"'");
			return re;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"LessonOperation.deleteById�쳣");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public int update(Object obj) {
		Lesson one=(Lesson)obj;
		MySQLHandler hand=new MySQLHandler();
		try {
			int re=hand.execute("update student_Lesson set "
					+"'Lesson_user='"+one.getLessonUser().getUserId()
					+"',Lesson_course='"+one.getLessonCourse().getCourseId()
					+"' where Lesson_id='"+one.getLessonId()+"'");
			return re;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"LessonOperation.update�쳣");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
}
