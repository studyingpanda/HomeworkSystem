package action;
import java.util.Map;
import entity.Lesson;
import entity.User;
import exception.MyException;
/***
 * ������ѡ��
 * @author è��
 * @date 2017.2.20
 */
public class SaveLessonAction extends SaveAction{
	public SaveLessonAction(String entityType,String operationType,Boolean isAddOrEdit,Object saveEntity,User sessionUser){
		super(entityType,operationType,isAddOrEdit,saveEntity,sessionUser);
	}
	@Override
	public Map<String,Object> execute() throws MyException {
		Lesson lesson=(Lesson)saveEntity;
		lesson.setLessonUser(sessionUser);
		saveEntity=lesson;
		return super.execute(); 
	}
}
