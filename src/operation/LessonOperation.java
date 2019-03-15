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
			//此处不要写select *，因为数据库将*转换为该表所有列名肯定需要浪费时间
			rs=hand.query("select count(lesson_id) as count  from student_lesson");
			while(rs.next()){
				re=rs.getInt("count");
			}
			return re;
		} catch (Exception ex) {
			//对于数据库操作层面的异常，此时不予以向外抛出，记录在日志中分析即可
			//在设计的时候就要明确，什么类型异常要外抛，什么异常不抛出只记录
			new MyException(new Date(),ex.getMessage(),"LessonOperation.selectCount异常");
			return 0;//注意null和new Course()并不同！
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public Object selectById(int id) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		Lesson one=new Lesson();//如果查询内容为空，则通过one.getLessonId()==0来判断即可
		try {
			//此处不要写select *，因为数据库将*转换为该表所有列名肯定需要浪费时间
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
			//对于数据库操作层面的异常，此时不予以向外抛出，记录在日志中分析即可
			//在设计的时候就要明确，什么类型异常要外抛，什么异常不抛出只记录
			new MyException(new Date(),ex.getMessage(),"LessonOperation.selectById异常");
			return null;//注意null和new Lesson()并不同！
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public List selectAll() {//注意返回值null和list.size()==0的区别
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Lesson> list=new ArrayList<Lesson>();//返回值
		try {
			rs=hand.query("select Lesson_id,user_id,user_name,course_id,course_name from "
					+" student_Lesson l,system_user u,system_course c"
					+" where l.Lesson_user=u.user_id and l.Lesson_course=c.course_id");
			while(rs.next()){
				Lesson one=new Lesson();//返回值中的一个
				one.setLessonId(rs.getInt("Lesson_id"));
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setLessonUser(user);
				Course course=new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				one.setLessonCourse(course);
				list.add(one);//添加到列表
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"LessonOperation.selectAll异常");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	public List selectByCourseId(int courseId) {//注意返回值null和list.size()==0的区别
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Lesson> list=new ArrayList<Lesson>();//返回值
		try {
			rs=hand.query("select Lesson_id,user_id,user_name,course_id,course_name from "
					+" student_Lesson l,system_user u,system_course c"
					+" where l.Lesson_user=u.user_id and l.Lesson_course=c.course_id"
					+" and c.course_id='"+courseId+"'");
			while(rs.next()){
				Lesson one=new Lesson();//返回值中的一个
				one.setLessonId(rs.getInt("Lesson_id"));
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setLessonUser(user);
				Course course=new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				one.setLessonCourse(course);
				list.add(one);//添加到列表
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"LessonOperation.selectAll异常");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public List selectPage(int offset,int size) {//注意返回值null和list.size()==0的区别
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Lesson> list=new ArrayList<Lesson>();//返回值
		try {
			rs=hand.query("select Lesson_id,user_id,user_name,course_id,course_name from "
					+" student_Lesson l,system_user u,system_course c"
					+" where l.Lesson_user=u.user_id and l.Lesson_course=c.course_id"
					+" order by l.lesson_id limit "+offset+","+size);
			while(rs.next()){
				Lesson one=new Lesson();//返回值中的一个
				one.setLessonId(rs.getInt("Lesson_id"));
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setLessonUser(user);
				Course course=new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				one.setLessonCourse(course);
				list.add(one);//添加到列表
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"LessonOperation.selectPage异常");
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
			new MyException(new Date(),ex.getMessage(),"LessonOperation.add异常");
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
			new MyException(new Date(),ex.getMessage(),"LessonOperation.deleteById异常");
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
			new MyException(new Date(),ex.getMessage(),"LessonOperation.update异常");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
}
