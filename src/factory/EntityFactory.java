/**
 * factory包，工厂类包
 * EntityFactory：用于从HttpServletRequest生成实体类对象的工厂
 * OperationFactory:用于生成Operation类的工厂
 */
package factory;
import javax.servlet.http.HttpServletRequest;
import util.Constant;
import entity.*;
public class EntityFactory {
	//组装的工艺类型entityType+组装的材料来源request
	public static Object createEntityFromRequest(String entityType,HttpServletRequest request){
		String id=request.getParameter("entityId");
		if(entityType.equals("User")){
			User user=new User();
			if(id==null||"".equals(id)){//属于add
				user.setUserId(-1);
			}else{//属于edit
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
			if(id==null||"".equals(id)){//属于add
				course.setCourseId(-1);
			}else{//属于edit
				course.setCourseId(Integer.parseInt(id));
			}
			course.setCourseName(request.getParameter("courseName"));
			course.setCourseUser((User)request.getSession().getAttribute("sessionUser"));
			return course;
		}
		else if(entityType.equals("Work")){
			Work work=new Work();
			if(id==null||"".equals(id)){//属于add
				work.setWorkId(-1);
			}else{//属于edit
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
			//不管是老师批阅，还是学生做作业，均为修改
			Job job=new Job();
			job.setJobId(Integer.parseInt(id));
			//只会用到jobContent、jobScore
			job.setJobContent(request.getParameter("jobContent"));
			job.setJobScore(request.getParameter("jobScore"));
			return job;
		}
		else if(entityType.equals("Lesson")){//选课，都是新增
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
