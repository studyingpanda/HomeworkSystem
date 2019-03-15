package operation;
import inter.IOperation;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import database.MySQLHandler;
import entity.*;
import exception.MyException;
public class JobOperation implements IOperation{
	//教师查看未批阅作业使用的两个方法begin
	public int selectCountByTeacherId(int teacherId,String jobScore){
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		int re=0;
		try {
			//此处不要写select *，因为数据库将*转换为该表所有列名肯定需要浪费时间
			rs=hand.query("select count(job_id) as count from student_job j,system_work w,system_course c"
					+" where c.course_user='"+teacherId+"' and c.course_id=w.work_course"
					+" and w.work_id=j.job_work "
					+" and job_score='"+jobScore+"'");
			while(rs.next()){
				re=rs.getInt("count");
			}
			return re;
		} catch (Exception ex) {
			//对于数据库操作层面的异常，此时不予以向外抛出，记录在日志中分析即可
			//在设计的时候就要明确，什么类型异常要外抛，什么异常不抛出只记录
			new MyException(new Date(),ex.getMessage(),"JobOperation.selectCount异常");
			return 0;//注意null和new Course()并不同！
		}finally{
			hand.sayGoodbye();
		}
	}
	public List selectPageByTeacherId(int offset,int size,int teacherId,String jobScore){
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Job> list=new ArrayList<Job>();//返回值
		try {
			rs=hand.query("select Job_id,job_content,job_score,job_time,user_id,user_name,work_id,work_title from "
					+" student_Job j,system_user u,system_work w,system_course c"
					+" where j.Job_user=u.user_id and j.job_work=w.work_id "
					+" and w.work_course=c.course_id and c.course_user='"+teacherId+"'"
					+" and j.job_score='"+jobScore+"'"
					+" order by j.job_id limit "+offset+","+size);
			while(rs.next()){
				Job one=new Job();//返回值中的一个
				one.setJobId(rs.getInt("Job_id"));
				one.setJobContent(rs.getString("job_content"));
				one.setJobScore(rs.getString("job_score"));
				one.setJobTime(rs.getString("job_time"));
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setJobUser(user);
				Work work=new Work();
				work.setWorkId(rs.getInt("work_id"));
				work.setWorkTitle(rs.getString("work_title"));
				one.setJobWork(work);
				list.add(one);//添加到列表
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"JobOperation.selectPage异常");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	//教师查看未批阅作业使用的两个方法end
	//学生查看未做作业使用的两个方法begin
	public int selectCountByStudentId(int studentId,String jobScore){
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		int re=0;
		try {
			//此处不要写select *，因为数据库将*转换为该表所有列名肯定需要浪费时间
			rs=hand.query("select count(job_id) as count from student_job where job_user='"+studentId+"'"
					+" and job_score='"+jobScore+"'");
			while(rs.next()){
				re=rs.getInt("count");
			}
			return re;
		} catch (Exception ex) {
			//对于数据库操作层面的异常，此时不予以向外抛出，记录在日志中分析即可
			//在设计的时候就要明确，什么类型异常要外抛，什么异常不抛出只记录
			new MyException(new Date(),ex.getMessage(),"JobOperation.selectCount异常");
			return 0;//注意null和new Course()并不同！
		}finally{
			hand.sayGoodbye();
		}
	}
	public List selectPageByStudentId(int offset,int size,int studentId,String jobScore){
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Job> list=new ArrayList<Job>();//返回值
		try {
			rs=hand.query("select Job_id,job_content,job_score,job_time,user_id,user_name,work_id,work_title from "
					+" student_Job j,system_user u,system_work w where j.Job_user=u.user_id and j.job_work=w.work_id "
					+" and u.user_id='"+studentId+"' and j.job_score='"+jobScore+"'"
					+" order by j.job_id limit "+offset+","+size);
			while(rs.next()){
				Job one=new Job();//返回值中的一个
				one.setJobId(rs.getInt("Job_id"));
				one.setJobContent(rs.getString("job_content"));
				one.setJobScore(rs.getString("job_score"));
				one.setJobTime(rs.getString("job_time"));
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setJobUser(user);
				Work work=new Work();
				work.setWorkId(rs.getInt("work_id"));
				work.setWorkTitle(rs.getString("work_title"));
				one.setJobWork(work);
				list.add(one);//添加到列表
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"JobOperation.selectPage异常");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	//学生查看未做作业使用的两个方法end
	@Override
	public int selectCount() {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		int re=0;
		try {
			//此处不要写select *，因为数据库将*转换为该表所有列名肯定需要浪费时间
			rs=hand.query("select count(job_id) as count  from student_job");
			while(rs.next()){
				re=rs.getInt("count");
			}
			return re;
		} catch (Exception ex) {
			//对于数据库操作层面的异常，此时不予以向外抛出，记录在日志中分析即可
			//在设计的时候就要明确，什么类型异常要外抛，什么异常不抛出只记录
			new MyException(new Date(),ex.getMessage(),"JobOperation.selectCount异常");
			return 0;//注意null和new Course()并不同！
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public Object selectById(int id) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		Job one=new Job();//如果查询内容为空，则通过one.getJobId()==0来判断即可
		try {
			//此处不要写select *，因为数据库将*转换为该表所有列名肯定需要浪费时间
			rs=hand.query("select Job_id,job_content,job_score,job_time,user_id,user_name,work_id,work_title from "
					+"student_Job j,system_user u,system_work w where j.Job_id='"+id+"' and j.Job_user=u.user_id and j.job_work=w.work_id");
			while(rs.next()){
				one.setJobId(rs.getInt("Job_id"));
				one.setJobContent(rs.getString("job_content"));
				one.setJobScore(rs.getString("job_score"));
				one.setJobTime(rs.getString("job_time"));
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setJobUser(user);
				Work work=new Work();
				work.setWorkId(rs.getInt("work_id"));
				work.setWorkTitle(rs.getString("work_title"));
				one.setJobWork(work);
			}
			return one;
		} catch (Exception ex) {
			//对于数据库操作层面的异常，此时不予以向外抛出，记录在日志中分析即可
			//在设计的时候就要明确，什么类型异常要外抛，什么异常不抛出只记录
			new MyException(new Date(),ex.getMessage(),"JobOperation.selectById异常");
			return null;//注意null和new Job()并不同！
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public List selectAll() {//注意返回值null和list.size()==0的区别
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Job> list=new ArrayList<Job>();//返回值
		try {
			rs=hand.query("select Job_id,job_content,job_score,job_time,user_id,user_name,work_id,work_title from "
					+"student_Job j,system_user u,system_work w where j.Job_user=u.user_id and j.job_work=w.work_id");
			while(rs.next()){
				Job one=new Job();//返回值中的一个
				one.setJobId(rs.getInt("Job_id"));
				one.setJobContent(rs.getString("job_content"));
				one.setJobScore(rs.getString("job_score"));
				one.setJobTime(rs.getString("job_time"));
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setJobUser(user);
				Work work=new Work();
				work.setWorkId(rs.getInt("work_id"));
				work.setWorkTitle(rs.getString("work_title"));
				one.setJobWork(work);
				list.add(one);//添加到列表
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"JobOperation.selectAll异常");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public List selectPage(int offset,int size) {//注意返回值null和list.size()==0的区别
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Job> list=new ArrayList<Job>();//返回值
		try {
			rs=hand.query("select Job_id,job_content,job_score,job_time,user_id,user_name,work_id,work_title from "
					+" student_Job j,system_user u,system_work w where j.Job_user=u.user_id and j.job_work=w.work_id "
					+" order by j.job_id limit "+offset+","+size);
			while(rs.next()){
				Job one=new Job();//返回值中的一个
				one.setJobId(rs.getInt("Job_id"));
				one.setJobContent(rs.getString("job_content"));
				one.setJobScore(rs.getString("job_score"));
				one.setJobTime(rs.getString("job_time"));
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				one.setJobUser(user);
				Work work=new Work();
				work.setWorkId(rs.getInt("work_id"));
				work.setWorkTitle(rs.getString("work_title"));
				one.setJobWork(work);
				list.add(one);//添加到列表
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"JobOperation.selectPage异常");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public int add(Object obj) {
		Job one=(Job)obj;
		MySQLHandler hand=new MySQLHandler();
		try {
			int re=hand.execute("insert into student_Job(Job_content,Job_score,job_time,job_user,job_work)"
					+" values('"+one.getJobContent()+"','"+one.getJobScore()+"','"+
					one.getJobTime()+"','"+one.getJobUser().getUserId()+"','"+one.getJobWork().getWorkId()+"')");
			return re;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"JobOperation.add异常");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public int deleteById(int id) {
		MySQLHandler hand=new MySQLHandler();
		try {
			int re=hand.execute("delete from student_Job where Job_id='"+id+"'");
			return re;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"JobOperation.deleteById异常");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public int update(Object obj) {
		Job one=(Job)obj;
		MySQLHandler hand=new MySQLHandler();
		try {
			int re=hand.execute("update student_Job set Job_content='"+one.getJobContent()
					+"',Job_score='"+one.getJobScore()
					+"',Job_time='"+one.getJobTime()
					+"',Job_user='"+one.getJobUser().getUserId()
					+"',Job_work='"+one.getJobWork().getWorkId()
					+"' where Job_id='"+one.getJobId()+"'");
			return re;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"JobOperation.update异常");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
	
}
