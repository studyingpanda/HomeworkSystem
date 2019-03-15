/**
 * exception���������Զ����쳣��İ�
 */
package exception;
import java.util.Date;
import util.ExceptionLogger;
public class MyException extends Exception{
	private Date time;//��¼�쳣����ʱ��
	private String message;//ԭʼ�쳣��Ϣ��������Ա������¼��־
	private String info;//�Զ�����Ϣ�����û���
	public MyException(){
		super();
	}
	public MyException(Date time,String msg,String info){
		super();
		this.time=time;
		this.message=msg;
		this.info=info;
		//��־
		ExceptionLogger.logInfo(msg+"##"+info);
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
}
