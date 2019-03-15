/**
 * factory�����������
 * EntityFactory�����ڴ�HttpServletRequest����ʵ�������Ĺ���
 * OperationFactory:��������Operation��Ĺ���
 */
package factory;
import javax.servlet.http.HttpServletRequest;
import util.Constant;
import entity.*;
public class EntityFactory {
	//��װ�Ĺ�������entityType+��װ�Ĳ�����Դrequest
	public static Object createEntityFromRequest(String entityType,HttpServletRequest request){
		String id=request.getParameter("entityId");
		if(entityType.equals("User")){
			User user=new User();
			if(id==null||"".equals(id)){//����add
				user.setUserId(-1);
			}else{//����edit
				user.setUserId(Integer.parseInt(id));
			}
			user.setUserPassword(request.getParameter("userPassword"));
			user.setUserName(request.getParameter("userName"));
			Role role=new Role();
			role.setRoleId(Integer.parseInt(request.getParameter("userRole")));
			user.setUserRole(role);
			return user;
		}
		else if(entityType.equals("Course")){
			Course course=new Course();
			if(id==null||"".equals(id)){//����add
				course.setCourseId(-1);
			}else{//����edit
				course.setCourseId(Integer.parseInt(id));
			}
			course.setCourseName(request.getParameter("courseName"));
			course.setCourseUser((User)request.getSession().getAttribute("sessionUser"));
			return course;
		}
		else if(entityType.equals("Work")){
			Work work=new Work();
			if(id==null||"".equals(id)){//����add
				work.setWorkId(-1);
			}else{//����edit
				work.setWorkId(Integer.parseInt(id));
			}
			Course course=new Course();
			String temp=request.getParameter("workCourse");
			course.setCourseId(Integer.parseInt(temp));
			work.setWorkCourse(course);
			work.setWorkTitle(request.getParameter("workTitle"));
			work.setWorkTime(Constant.getDate());
			return work;
		}
		else if(entityType.equals("Job")){
			//��������ʦ���ģ�����ѧ������ҵ����Ϊ�޸�
			Job job=new Job();
			job.setJobId(Integer.parseInt(id));
			//ֻ���õ�jobContent��jobScore
			job.setJobContent(request.getParameter("jobContent"));
			job.setJobScore(request.getParameter("jobScore"));
			return job;
		}
		else if(entityType.equals("Lesson")){//ѡ�Σ���������
			Lesson lesson=new Lesson();
			lesson.setLessonId(-1);
			Course course=new Course();
			course.setCourseId(Integer.parseInt(request.getParameter("courseId")));
			lesson.setLessonCourse(course);
			return lesson;
		}
		return null;
	}
}
