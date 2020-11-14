package org.maoge.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
	public static String getNowSqlTime() {
		// 格式化样式类
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		// 日期时间转字符串
		LocalDateTime now = LocalDateTime.now();
		return now.format(formatter);
	}
}
