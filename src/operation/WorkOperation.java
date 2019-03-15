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
			//此处不要写select *，因为数据库将*转换为该表所有列名肯定需要浪费时间
			rs=hand.query("select max(work_id) as maxId  from system_work");
			while(rs.next()){
				re=rs.getInt("maxId");
			}
			hand.sayGoodbye();
			return re;
		} catch (Exception ex) {
			//对于数据库操作层面的异常，此时不予以向外抛出，记录在日志中分析即可
			//在设计的时候就要明确，什么类型异常要外抛，什么异常不抛出只记录
			new MyException(new Date(),ex.getMessage(),"WorkOperation.selectMaxId异常");
			return 0;//注意null和new Course()并不同！
		}
	}
	@Override
	public int selectCount() {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		int re=0;
		try {
			//此处不要写select *，因为数据库将*转换为该表所有列名肯定需要浪费时间
			rs=hand.query("select count(work_id) as count  from system_work");
			while(rs.next()){
				re=rs.getInt("count");
			}
			hand.sayGoodbye();
			return re;
		} catch (Exception ex) {
			//对于数据库操作层面的异常，此时不予以向外抛出，记录在日志中分析即可
			//在设计的时候就要明确，什么类型异常要外抛，什么异常不抛出只记录
			new MyException(new Date(),ex.getMessage(),"WorkOperation.selectCount异常");
			return 0;//注意null和new Course()并不同！
		}
	}
	public int selectCountByCourseId(int courseId) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		int re=0;
		try {
			//此处不要写select *，因为数据库将*转换为该表所有列名肯定需要浪费时间
			rs=hand.query("select count(work_id) as count from system_work where work_course='"+courseId+"'");
			while(rs.next()){
				re=rs.getInt("count");
			}
			hand.sayGoodbye();
			return re;
		} catch (Exception ex) {
			//对于数据库操作层面的异常，此时不予以向外抛出，记录在日志中分析即可
			//在设计的时候就要明确，什么类型异常要外抛，什么异常不抛出只记录
			new MyException(new Date(),ex.getMessage(),"WorkOperation.selectCount异常");
			return 0;//注意null和new Course()并不同！
		}
	}
	@Override
	public Object selectById(int id) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		Work one=new Work();//如果查询内容为空，则通过one.getWorkId()==0来判断即可
		try {
			//此处不要写select *，因为数据库将*转换为该表所有列名肯定需要浪费时间
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
			//对于数据库操作层面的异常，此时不予以向外抛出，记录在日志中分析即可
			//在设计的时候就要明确，什么类型异常要外抛，什么异常不抛出只记录
			new MyException(new Date(),ex.getMessage(),"WorkOperation.selectById异常");
			return null;//注意null和new Work()并不同！
		}
	}
	@Override
	public List selectAll() {//注意返回值null和list.size()==0的区别
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Work> list=new ArrayList<Work>();//返回值
		try {
			rs=hand.query("select Work_id,Work_title,work_time,course_id,course_name from "
					+"system_Work w,system_course c where w.Work_course=c.course_id");
			while(rs.next()){
				Work one=new Work();//返回值中的一个
				one.setWorkId(rs.getInt("Work_id"));
				one.setWorkTitle(rs.getString("Work_title"));	
				one.setWorkTime(rs.getString("Work_time"));	
				Course course=new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				one.setWorkCourse(course);
				list.add(one);//添加到列表
			}
			hand.sayGoodbye();//释放资源
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"WorkOperation.selectAll异常");
			return null;
		}
	}
	@Override
	public List selectPage(int offset,int size) {//注意返回值null和list.size()==0的区别
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Work> list=new ArrayList<Work>();//返回值
		try {
			rs=hand.query("select Work_id,Work_title,work_time,course_id,course_name from "
					+"system_Work w,system_course c where w.Work_course=c.course_id "
					+" order by w.work_id limit "+offset+","+size);
			while(rs.next()){
				Work one=new Work();//返回值中的一个
				one.setWorkId(rs.getInt("Work_id"));
				one.setWorkTitle(rs.getString("Work_title"));	
				one.setWorkTime(rs.getString("Work_time"));	
				Course course=new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				one.setWorkCourse(course);
				list.add(one);//添加到列表
			}
			hand.sayGoodbye();//释放资源
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"WorkOperation.selectPage异常");
			return null;
		}
	}
	public List selectPageByCourseId(int offset,int size,int courseId) {//注意返回值null和list.size()==0的区别
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Work> list=new ArrayList<Work>();//返回值
		try {
			rs=hand.query("select Work_id,Work_title,work_time,course_id,course_name from "
					+"system_Work w,system_course c where w.Work_course=c.course_id and c.course_id='"+courseId+"'"
					+" order by w.work_id limit "+offset+","+size);
			while(rs.next()){
				Work one=new Work();//返回值中的一个
				one.setWorkId(rs.getInt("Work_id"));
				one.setWorkTitle(rs.getString("Work_title"));	
				one.setWorkTime(rs.getString("Work_time"));	
				Course course=new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				one.setWorkCourse(course);
				list.add(one);//添加到列表
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"WorkOperation.selectPage异常");
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
			new MyException(new Date(),ex.getMessage(),"WorkOperation.add异常");
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
			new MyException(new Date(),ex.getMessage(),"WorkOperation.deleteById异常");
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
			new MyException(new Date(),ex.getMessage(),"WorkOperation.update异常");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
}
