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
 * ������������ҵ(ע��������ҵʱ��Ҫ����)
 * @author è��
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
		//��ȡ���work_id����һΪ��id
		WorkOperation workOper=(WorkOperation)OperationFactory.createOperation("Work");
		int maxId=workOper.selectMaxId();
		//��ȡ������work��Ϣ��maxId+1��û����ģ�������ñ��˲���maxId+1�����˾�ʧ����
		Work one=(Work)saveEntity;
		one.setWorkId(maxId+1);
		//��ִ�е�sql�б�
		ArrayList<String> sqlList=new ArrayList<String>();
		//��һ�����system_Work��������ҵ
		sqlList.add("insert into system_Work(work_id,Work_title,work_time,work_course)"
				+" values('"+one.getWorkId()+"','"+one.getWorkTitle()+"','"+one.getWorkTime()+"','"+one.getWorkCourse().getCourseId()+"')");
		//�ڶ������student_job����Ӷ���
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
		//ִ������
		MySQLHandler hand=new MySQLHandler();
		hand.doTransaction(sqlList);
		//������Ϻ���ת�鿴��ҵҳ��
		resultMap.put("tipInfo", "���³ɹ�");
		actionUrl="tip.jsp";
		return resultMap;
	}
}
