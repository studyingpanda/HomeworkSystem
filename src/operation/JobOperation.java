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
	//��ʦ�鿴δ������ҵʹ�õ���������begin
	public int selectCountByTeacherId(int teacherId,String jobScore){
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		int re=0;
		try {
			//�˴���Ҫдselect *����Ϊ���ݿ⽫*ת��Ϊ�ñ����������϶���Ҫ�˷�ʱ��
			rs=hand.query("select count(job_id) as count from student_job j,system_work w,system_course c"
					+" where c.course_user='"+teacherId+"' and c.course_id=w.work_course"
					+" and w.work_id=j.job_work "
					+" and job_score='"+jobScore+"'");
			while(rs.next()){
				re=rs.getInt("count");
			}
			return re;
		} catch (Exception ex) {
			//�������ݿ����������쳣����ʱ�����������׳�����¼����־�з�������
			//����Ƶ�ʱ���Ҫ��ȷ��ʲô�����쳣Ҫ���ף�ʲô�쳣���׳�ֻ��¼
			new MyException(new Date(),ex.getMessage(),"JobOperation.selectCount�쳣");
			return 0;//ע��null��new Course()����ͬ��
		}finally{
			hand.sayGoodbye();
		}
	}
	public List selectPageByTeacherId(int offset,int size,int teacherId,String jobScore){
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Job> list=new ArrayList<Job>();//����ֵ
		try {
			rs=hand.query("select Job_id,job_content,job_score,job_time,user_id,user_name,work_id,work_title from "
					+" student_Job j,system_user u,system_work w,system_course c"
					+" where j.Job_user=u.user_id and j.job_work=w.work_id "
					+" and w.work_course=c.course_id and c.course_user='"+teacherId+"'"
					+" and j.job_score='"+jobScore+"'"
					+" order by j.job_id limit "+offset+","+size);
			while(rs.next()){
				Job one=new Job();//����ֵ�е�һ��
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
				list.add(one);//��ӵ��б�
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"JobOperation.selectPage�쳣");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	//��ʦ�鿴δ������ҵʹ�õ���������end
	//ѧ���鿴δ����ҵʹ�õ���������begin
	public int selectCountByStudentId(int studentId,String jobScore){
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		int re=0;
		try {
			//�˴���Ҫдselect *����Ϊ���ݿ⽫*ת��Ϊ�ñ����������϶���Ҫ�˷�ʱ��
			rs=hand.query("select count(job_id) as count from student_job where job_user='"+studentId+"'"
					+" and job_score='"+jobScore+"'");
			while(rs.next()){
				re=rs.getInt("count");
			}
			return re;
		} catch (Exception ex) {
			//�������ݿ����������쳣����ʱ�����������׳�����¼����־�з�������
			//����Ƶ�ʱ���Ҫ��ȷ��ʲô�����쳣Ҫ���ף�ʲô�쳣���׳�ֻ��¼
			new MyException(new Date(),ex.getMessage(),"JobOperation.selectCount�쳣");
			return 0;//ע��null��new Course()����ͬ��
		}finally{
			hand.sayGoodbye();
		}
	}
	public List selectPageByStudentId(int offset,int size,int studentId,String jobScore){
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Job> list=new ArrayList<Job>();//����ֵ
		try {
			rs=hand.query("select Job_id,job_content,job_score,job_time,user_id,user_name,work_id,work_title from "
					+" student_Job j,system_user u,system_work w where j.Job_user=u.user_id and j.job_work=w.work_id "
					+" and u.user_id='"+studentId+"' and j.job_score='"+jobScore+"'"
					+" order by j.job_id limit "+offset+","+size);
			while(rs.next()){
				Job one=new Job();//����ֵ�е�һ��
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
				list.add(one);//��ӵ��б�
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"JobOperation.selectPage�쳣");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	//ѧ���鿴δ����ҵʹ�õ���������end
	@Override
	public int selectCount() {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		int re=0;
		try {
			//�˴���Ҫдselect *����Ϊ���ݿ⽫*ת��Ϊ�ñ����������϶���Ҫ�˷�ʱ��
			rs=hand.query("select count(job_id) as count  from student_job");
			while(rs.next()){
				re=rs.getInt("count");
			}
			return re;
		} catch (Exception ex) {
			//�������ݿ����������쳣����ʱ�����������׳�����¼����־�з�������
			//����Ƶ�ʱ���Ҫ��ȷ��ʲô�����쳣Ҫ���ף�ʲô�쳣���׳�ֻ��¼
			new MyException(new Date(),ex.getMessage(),"JobOperation.selectCount�쳣");
			return 0;//ע��null��new Course()����ͬ��
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public Object selectById(int id) {
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		Job one=new Job();//�����ѯ����Ϊ�գ���ͨ��one.getJobId()==0���жϼ���
		try {
			//�˴���Ҫдselect *����Ϊ���ݿ⽫*ת��Ϊ�ñ����������϶���Ҫ�˷�ʱ��
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
			//�������ݿ����������쳣����ʱ�����������׳�����¼����־�з�������
			//����Ƶ�ʱ���Ҫ��ȷ��ʲô�����쳣Ҫ���ף�ʲô�쳣���׳�ֻ��¼
			new MyException(new Date(),ex.getMessage(),"JobOperation.selectById�쳣");
			return null;//ע��null��new Job()����ͬ��
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public List selectAll() {//ע�ⷵ��ֵnull��list.size()==0������
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Job> list=new ArrayList<Job>();//����ֵ
		try {
			rs=hand.query("select Job_id,job_content,job_score,job_time,user_id,user_name,work_id,work_title from "
					+"student_Job j,system_user u,system_work w where j.Job_user=u.user_id and j.job_work=w.work_id");
			while(rs.next()){
				Job one=new Job();//����ֵ�е�һ��
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
				list.add(one);//��ӵ��б�
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"JobOperation.selectAll�쳣");
			return null;
		}finally{
			hand.sayGoodbye();
		}
	}
	@Override
	public List selectPage(int offset,int size) {//ע�ⷵ��ֵnull��list.size()==0������
		MySQLHandler hand=new MySQLHandler();
		ResultSet rs=null;
		ArrayList<Job> list=new ArrayList<Job>();//����ֵ
		try {
			rs=hand.query("select Job_id,job_content,job_score,job_time,user_id,user_name,work_id,work_title from "
					+" student_Job j,system_user u,system_work w where j.Job_user=u.user_id and j.job_work=w.work_id "
					+" order by j.job_id limit "+offset+","+size);
			while(rs.next()){
				Job one=new Job();//����ֵ�е�һ��
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
				list.add(one);//��ӵ��б�
			}
			return list;
		} catch (Exception ex) {
			new MyException(new Date(),ex.getMessage(),"JobOperation.selectPage�쳣");
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
			new MyException(new Date(),ex.getMessage(),"JobOperation.add�쳣");
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
			new MyException(new Date(),ex.getMessage(),"JobOperation.deleteById�쳣");
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
			new MyException(new Date(),ex.getMessage(),"JobOperation.update�쳣");
			return 0;
		}finally{
			hand.sayGoodbye();
		}
	}
	
}
