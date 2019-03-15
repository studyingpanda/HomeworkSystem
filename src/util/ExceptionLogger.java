package util;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/**
 * 异常日志类ExceptionLogger
 * @author 猫哥
 * @date 2017.2.24
 */
public class ExceptionLogger {
	//日志记录器
	public static Logger logger;
	static{//初始化
		try{
			//给日志记录器起名
			logger=Logger.getLogger("HomeworkSystem");
			//日志相关的文件处理器
			FileHandler fileHandler;
			fileHandler = new FileHandler("d:\\log.txt");
			//文件处理器相关的格式
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
			//日志记录器使用fileHandler管理文件
			logger.addHandler(fileHandler);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//记录一条日志
	public static void logInfo(String info){
		//设置日志级别，内容
		LogRecord logRecord = new LogRecord(Level.INFO, info);
		//记录一条日志
		logger.log(logRecord);
	}
}
