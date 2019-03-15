package factory;
import inter.IOperation;
import operation.*;
public class OperationFactory {
	public static IOperation createOperation(String entityType){
		if(entityType.equals("User")){
			return new UserOperation();
		}else if(entityType.equals("Role")){
			return new RoleOperation();
		}else if(entityType.equals("Work")){
			return new WorkOperation();
		}else if(entityType.equals("Job")){
			return new JobOperation();
		}else if(entityType.equals("Course")){
			return new CourseOperation();
		}else if(entityType.equals("Lesson")){
			return new LessonOperation();
		}
		return null;
	}
}
