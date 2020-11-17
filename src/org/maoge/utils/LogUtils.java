package org.maoge.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * ��־������
 */
public class LogUtils {
	// ��־��¼��
	public static Logger logger;
	static {// ��ʼ��
		try {
			// ����־��¼������
			logger = Logger.getLogger("HomeworkSystem");
			// ��־��ص��ļ�������
			FileHandler fileHandler;
			fileHandler = new FileHandler("d:\\log.txt");
			// �ļ���������صĸ�ʽ
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
			// ��־��¼��ʹ��fileHandler�����ļ�
			logger.addHandler(fileHandler);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ��¼һ����־
	public static void writeLog(Level level, String content) {
		// ������־��������
		LogRecord logRecord = new LogRecord(level, content);
		// ��¼һ����־
		logger.log(logRecord);
	}
}