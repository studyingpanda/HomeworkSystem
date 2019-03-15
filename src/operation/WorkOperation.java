package operation;
import inter.IOperation;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import database.MySQLHandler;
import entity.*;
import exception.MyException;
public class WorkOperation implements IOperation{
	public int selectMaxId() {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		int re=0;
		try {
			//�˴���Ҫдselect *����Ϊ���ݿ⽫*ת��Ϊ�ñ����������϶���Ҫ�˷�ʱ��
			rs=hand.query("select max(work_id) as maxId  from system_work");
			while(rs.next()){
				re=rs.getInt("maxId");
			}
			hand.sayGoodbye();
			return re;
		} catch (Exception ex) {
			//�������ݿ����������쳣����ʱ�����������׳�����¼����־�з�������
			//����Ƶ�ʱ���Ҫ��ȷ��ʲô�����쳣Ҫ���ף�ʲô�쳣���׳�ֻ��¼
			new MyException(new Date(),ex.getMessage(),"WorkOperation.selectMaxId�쳣");
			return 0;//ע��null��new Course()����ͬ��
		}
	}
	@Override
	public int selectCount() {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		int re=0;
		try {
			//�˴���Ҫдselect *����Ϊ���ݿ⽫*ת��Ϊ�ñ����������϶���Ҫ�˷�ʱ��
			rs=hand.query("select count(work_id) as count  from system_work");
			while(rs.next()){
				re=rs.getInt("count");
			}
			hand.sayGoodbye();
			return re;
		} catch (Exception ex) {
			//�������ݿ����������쳣����ʱ�����������׳�����¼����־�з�������
			//����Ƶ�ʱ���Ҫ��ȷ��ʲô�����쳣Ҫ���ף�ʲô�쳣���׳�ֻ��¼
			new MyException(new Date(),ex.getMessage(),"WorkOperation.selectCount�쳣");
			return 0;//ע��null��new Course()����ͬ��
		}
	}
	public int selectCountByCourseId(int courseId) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		int re=0;
		try {
			//�˴���Ҫдselect *����Ϊ���ݿ⽫*ת��Ϊ�ñ����������϶���Ҫ�˷�ʱ��
			rs=hand.query("select count(work_id) as count from system_work where work_course='"+courseId+"'");
			while(rs.next()){
				re=rs.getInt("count");
			}
			hand.sayGoodbye();
			return re;
		} catch (Exception ex) {
			//�������ݿ����������쳣����ʱ�����������׳�����¼����־�з�������
			//����Ƶ�ʱ���Ҫ��ȷ��ʲô�����쳣Ҫ���ף�ʲô�쳣���׳�ֻ��¼
			new MyException(new Date(),ex.getMessage(),"WorkOperation.selectCount�쳣");
			return 0;//ע��null��new Course()����ͬ��
		}
	}
	@Override
	public Object selectById(int id) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		Work one=new Work();//�����ѯ����Ϊ�գ���ͨ��one.getWorkId()==0���жϼ���
		try {
			//�˴���Ҫдselect *����Ϊ���ݿ⽫*ת��Ϊ�ñ����������϶���Ҫ�˷�ʱ��
			rs=hand.query("select Work_id,Work_title,work_time,course_id,course_name from "
					+"system_Work w,system_course c where w.Work_id='"+id+"' and w.Work_course=c.course_id");
			while(rs.next()){
				one.setWorkId(rs.getInt("Work_id"));
				one.setWorkTitle(rs.getString("Work_title"));	
				one.setWorkTime(rs.getString("Work_time"));	
				Course course=new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				one.setWorkCourse(course);
			}
			hand.sayGoodbye();
			return one;
		} catch (Exception ex) {
			//�������ݿ����������쳣����ʱ�����������׳�����¼����־�з�������
			//����Ƶ�ʱ���Ҫ��ȷ��ʲô�����쳣Ҫ���ף�ʲô�쳣���׳�ֻ��¼
			new MyException(new Date(),ex.getMessage(),"WorkOperation.selectById�쳣");
			return null;//ע��null��new Work()����ͬ��
		}
	}
	@Override
	public List selectAll() {//ע�ⷵ��ֵnull��list.size()==0������
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Work> list=new ArrayList<Work>();//����ֵ
		try {
			rs=hand.query("select Work_id,Work_title,work_time,course_id,course_name from "
					+"system_Work w,system_course c where w.Work_course=c.course_id");
			while(rs.next()){
				Work one=new Work();//����ֵ�е�һ��
				one.setWorkId(rs.getInt("Work_id"));
				one.setWorkTitle(rs.getString("Work_title"));	
				one.setWorkTime(rs.getString("Work_time"));	
				Course course=new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				one.setWorkCourse(course);
				list.add(one);//��ӵ��б�
			}
			hand.sayGoodbye();//�ͷ���Դ
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"WorkOperation.selectAll�쳣");
			return null;
		}
	}
	@Override
	public List selectPage(int offset,int size) {//ע�ⷵ��ֵnull��list.size()==0������
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Work> list=new ArrayList<Work>();//����ֵ
		try {
			rs=hand.query("select Work_id,Work_title,work_time,course_id,course_name from "
					+"system_Work w,system_course c where w.Work_course=c.course_id "
					+" order by w.work_id limit "+offset+","+size);
			while(rs.next()){
				Work one=new Work();//����ֵ�е�һ��
				one.setWorkId(rs.getInt("Work_id"));
				one.setWorkTitle(rs.getString("Work_title"));	
				one.setWorkTime(rs.getString("Work_time"));	
				Course course=new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				one.setWorkCourse(course);
				list.add(one);//��ӵ��б�
			}
			hand.sayGoodbye();//�ͷ���Դ
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"WorkOperation.selectPage�쳣");
			return null;
		}
	}
	public List selectPageByCourseId(int offset,int size,int courseId) {//ע�ⷵ��ֵnull��list.size()==0������
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Work> list=new ArrayList<Work>();//����ֵ
		try {
			rs=hand.query("select Work_id,Work_title,work_time,course_id,course_name from "
					+"system_Work w,system_course c where w.Work_course=c.course_id and c.course_id='"+courseId+"'"
					+" order by w.work_id limit "+offset+","+size);
			while(rs.next()){
				Work one=new Work();//����ֵ�е�һ��
				one.setWorkId(rs.getInt("Work_id"));
				one.setWorkTitle(rs.getString("Work_title"));	
				one.setWorkTime(rs.getString("Work_time"));	
				Course course=new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				one.setWorkCourse(course);
				list.add(one);//��ӵ��б�
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"WorkOperation.selectPage�쳣");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public int add(Object obj) {
		Work one=(Work)obj;
		MySQLHandler hand=new MySQLHandler();
		try {
			int re=hand.execute("insert into system_Work(Work_title,work_time,work_course)"
					+" values('"+one.getWorkTitle()+"','"+one.getWorkTime()+"','"+one.getWorkCourse().getCourseId()+"')");
			return re;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"WorkOperation.add�쳣");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public int deleteById(int id) {
		MySQLHandler hand=new MySQLHandler();
		try {
			int re=hand.execute("delete from system_Work where Work_id='"+id+"'");
			return re;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"WorkOperation.deleteById�쳣");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public int update(Object obj) {
		Work one=(Work)obj;
		MySQLHandler hand=new MySQLHandler();
		try {
			int re=hand.execute("update system_Work set Work_title='"+one.getWorkTitle()
					+"',Work_time='"+one.getWorkTime()
					+"',Work_course='"+one.getWorkCourse().getCourseId()
					+"' where Work_id='"+one.getWorkId()+"'");
			return re;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"WorkOperation.update�쳣");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
}
