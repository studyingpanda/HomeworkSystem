package org.maoge.utils;

import java.util.HashMap;

public class Constants {
	// 用于保存角色及对应的菜单信息
	public static HashMap<String, String[][]> roleMenuMap = new HashMap<String, String[][]>();
	// 使用static代码块对roleMenuMap进行初始化
	static {
		// 校长拥有的菜单
		roleMenuMap.put("master", new String[][] { { "人员管理", "userManage.jsp" }, { "课程查看", "courseView.jsp" } });
		// 教师拥有的菜单
		roleMenuMap.put("teacher", new String[][] { { "课程管理", "courseManage.jsp" }, { "作业题目管理", "titleManage.jsp" } });
	}
}