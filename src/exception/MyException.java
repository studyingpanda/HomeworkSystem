/**
 * exception包：包含自定义异常类的包
 */
package exception;
import java.util.Date;
import util.ExceptionLogger;
public class MyException extends Exception{
	private Date time;//记录异常发生时间
	private String message;//原始异常信息，给程序员看，记录日志
	private String info;//自定义信息，给用户看
	public MyException(){
		super();
	}
	public MyException(Date time,String msg,String info){
		super();
		this.time=time;
		this.message=msg;
		this.info=info;
		//日志
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
