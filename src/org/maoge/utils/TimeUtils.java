package org.maoge.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
	public static String getNowSqlTime() {
		// ��ʽ����ʽ��
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		// ����ʱ��ת�ַ���
		LocalDateTime now = LocalDateTime.now();
		return now.format(formatter);
	}
}
