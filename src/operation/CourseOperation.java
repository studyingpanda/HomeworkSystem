/**
 * operation包：保存数据库实体对象操作类的包
 * 计有CourseOperation JobOperation LessonOperation RoleOperation UserOperation WorkOperation
 * 因代码量太大且逻辑相近，此处只展示了CourseOperation
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
	//学生选课使用begin
	public int selectCountByStudentId(int studentId) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		int re=0;
		try {
			//此处不要写select *，因为数据库将*转换为该表所有列名肯定需要浪费时间
			rs=hand.query("select count(course_id) as count from system_course where course_id"
					+" not in(select lesson_course from student_lesson l where l.lesson_user='"+studentId+"')");
			while(rs.next()){
				re=rs.getInt("count");
			}
			return re;
		} catch (Exception ex) {
			//对于数据库操作层面的异常，此时不予以向外抛出，记录在日志中分析即可
			//在设计的时候就要明确，什么类型异常要外抛，什么异常不抛出只记录
			new MyException(new Date(),ex.getMessage(),"CourseOperation.selectCountByStudentId异常");
			return 0;//注意null和new Course()并不同！
		}finally{
			hand.sayGoodbye();
		}
	}
	public List selectPageByStudentId(int offset, int size,int studentId) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Course> list=new ArrayList<Course>();//返回值
		try {
			rs=hand.query("select Course_id,Course_name,user_id,user_name from "
					+" system_Course c,system_user u where c.Course_user=u.user_id "
					+" and c.course_id not in (select lesson_course from student_lesson l where l.lesson_user='"+studentId+"')"
					+" order by c.Course_id limit "+offset+","+size);
			while(rs.next()){ 
				Course one=new Course();//返回值中的一个
				one.setCourseId(rs.getInt("Course_id"));
				one.setCourseName(rs.getString("Course_name"));		
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setCourseUser(user);
				list.add(one);//添加到列表
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"CourseOperation.selectPageByStudentId异常");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	//学生选课使用end
	@Override
	public int selectCount() {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		int re=0;
		try {
			//此处不要写select *，因为数据库将*转换为该表所有列名肯定需要浪费时间
			rs=hand.query("select count(course_id) as count from system_course");
			while(rs.next()){
				re=rs.getInt("count");
			}
			return re;
		} catch (Exception ex) {
			//对于数据库操作层面的异常，此时不予以向外抛出，记录在日志中分析即可
			//在设计的时候就要明确，什么类型异常要外抛，什么异常不抛出只记录
			new MyException(new Date(),ex.getMessage(),"CourseOperation.selectCount异常");
			return 0;//注意null和new Course()并不同！
		}finally{
			hand.sayGoodbye();
		}
	}
	public int selectCountByUserId(int userId) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		int re=0;
		try {
			//此处不要写select *，因为数据库将*转换为该表所有列名肯定需要浪费时间
			rs=hand.query("select count(course_id) as count from system_course where course_user='"+userId+"'");
			while(rs.next()){
				re=rs.getInt("count");
			}
			return re;
		} catch (Exception ex) {
			//对于数据库操作层面的异常，此时不予以向外抛出，记录在日志中分析即可
			//在设计的时候就要明确，什么类型异常要外抛，什么异常不抛出只记录
			new MyException(new Date(),ex.getMessage(),"CourseOperation.selectCount异常");
			return 0;//注意null和new Course()并不同！
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public Object selectById(int id) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		Course one=new Course();//如果查询内容为空，则通过one.getCourseId()==0来判断即可
		try {
			//此处不要写select *，因为数据库将*转换为该表所有列名肯定需要浪费时间
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
			//对于数据库操作层面的异常，此时不予以向外抛出，记录在日志中分析即可
			//在设计的时候就要明确，什么类型异常要外抛，什么异常不抛出只记录
			new MyException(new Date(),ex.getMessage(),"CourseOperation.selectById异常");
			return null;//注意null和new Course()并不同！
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public List selectAll() {//注意返回值null和list.size()==0的区别
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Course> list=new ArrayList<Course>();//返回值
		try {
			rs=hand.query("select Course_id,Course_name,user_id,user_name from "
					+"system_Course c,system_user u where c.Course_user=u.user_id");
			while(rs.next()){
				Course one=new Course();//返回值中的一个
				one.setCourseId(rs.getInt("Course_id"));
				one.setCourseName(rs.getString("Course_name"));		
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setCourseUser(user);
				list.add(one);//添加到列表
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"CourseOperation.selectAll异常");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	public List selectAllByUserId(int userId) {//注意返回值null和list.size()==0的区别
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Course> list=new ArrayList<Course>();//返回值
		try {
			rs=hand.query("select Course_id,Course_name,user_id,user_name from "
					+"system_Course c,system_user u where c.Course_user=u.user_id"
					+" and u.user_id='"+userId+"'");
			while(rs.next()){
				Course one=new Course();//返回值中的一个
				one.setCourseId(rs.getInt("Course_id"));
				one.setCourseName(rs.getString("Course_name"));		
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setCourseUser(user);
				list.add(one);//添加到列表
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"CourseOperation.selectAll异常");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public List selectPage(int offset, int size) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Course> list=new ArrayList<Course>();//返回值
		try {
			rs=hand.query("select Course_id,Course_name,user_id,user_name from "
					+" system_Course c,system_user u where c.Course_user=u.user_id "
					+" order by c.Course_id limit "+offset+","+size);
			while(rs.next()){ 
				Course one=new Course();//返回值中的一个
				one.setCourseId(rs.getInt("Course_id"));
				one.setCourseName(rs.getString("Course_name"));		
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setCourseUser(user);
				list.add(one);//添加到列表
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"CourseOperation.selectPage异常");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	public List selectPageByUserId(int offset, int size,int userId) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Course> list=new ArrayList<Course>();//返回值
		try {
			rs=hand.query("select Course_id,Course_name,user_id,user_name from "
					+" system_Course c,system_user u where c.Course_user=u.user_id and user_id='"+userId+"'"
					+" order by c.Course_id limit "+offset+","+size);
			while(rs.next()){ 
				Course one=new Course();//返回值中的一个
				one.setCourseId(rs.getInt("Course_id"));
				one.setCourseName(rs.getString("Course_name"));		
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setCourseUser(user);
				list.add(one);//添加到列表
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"CourseOperation.selectPage异常");
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
			new MyException(new Date(),ex.getMessage(),"CourseOperation.add异常");
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
			new MyException(new Date(),ex.getMessage(),"CourseOperation.deleteById异常");
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
			new MyException(new Date(),ex.getMessage(),"CourseOperation.update异常");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
}
