package action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import database.MySQLHandler;
import operation.LessonOperation;
import operation.WorkOperation;
import entity.Lesson;
import entity.User;
import entity.Work;
import exception.MyException;
import factory.OperationFactory;
/***
 * 动作：保存作业(注意新增作业时需要事务)
 * @author 猫哥
 * @date 2017.2.17
 * @modify 2017.2.19 to remove context
 */
public class SaveWorkAction extends SaveAction{
	public SaveWorkAction(String entityType,String operationType,Boolean isAddOrEdit,Object saveEntity,User sessionUser){
		super(entityType,operationType,isAddOrEdit,saveEntity,sessionUser);
	}
	@Override
	public Map<String,Object> execute() throws MyException {
		Map<String,Object> resultMap=new HashMap<String,Object>();
		//获取最大work_id，加一为新id
		WorkOperation workOper=(WorkOperation)OperationFactory.createOperation("Work");
		int maxId=workOper.selectMaxId();
		//获取新增的work信息，maxId+1是没问题的，如果正好别人插入maxId+1，大不了就失败了
		Work one=(Work)saveEntity;
		one.setWorkId(maxId+1);
		//待执行的sql列表
		ArrayList<String> sqlList=new ArrayList<String>();
		//第一条语句system_Work中新增作业
		sqlList.add("insert into system_Work(work_id,Work_title,work_time,work_course)"
				+" values('"+one.getWorkId()+"','"+one.getWorkTitle()+"','"+one.getWorkTime()+"','"+one.getWorkCourse().getCourseId()+"')");
		//第二条语句student_job中添加多条
		LessonOperation lessonOper=(LessonOperation)OperationFactory.createOperation("Lesson");
		ArrayList<Lesson> lessonList=(ArrayList<Lesson>)lessonOper.selectByCourseId(one.getWorkCourse().getCourseId());
		StringBuilder sqlLesson=new StringBuilder();
		sqlLesson.append("insert into student_Job(Job_score,job_user,job_work) values");
		for(Lesson lesson:lessonList){
			String sql="('-2','"+lesson.getLessonUser().getUserId()+"','"+one.getWorkId()+"'),";
			sqlLesson.append(sql);
		}
		sqlLesson.deleteCharAt(sqlLesson.length()-1);
		if(lessonList.size()>0)
			sqlList.add(sqlLesson.toString());
		//执行事务
		MySQLHandler hand=new MySQLHandler();
		hand.doTransaction(sqlList);
		//新增完毕后跳转查看作业页面
		resultMap.put("tipInfo", "更新成功");
		actionUrl="tip.jsp";
		return resultMap;
	}
}
