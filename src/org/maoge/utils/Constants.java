package org.maoge.utils;

import java.util.HashMap;

public class Constants {
	// ���ڱ����ɫ����Ӧ�Ĳ˵���Ϣ
	public static HashMap<String, String[][]> roleMenuMap = new HashMap<String, String[][]>();
	// ʹ��static������roleMenuMap���г�ʼ��
	static {
		// У��ӵ�еĲ˵�
		roleMenuMap.put("master", new String[][] { { "��Ա����", "userManage.jsp" }, { "�γ̲鿴", "courseView.jsp" } });
		// ��ʦӵ�еĲ˵�
		roleMenuMap.put("teacher", new String[][] { { "�γ̹���", "courseManage.jsp" }, { "��ҵ��Ŀ����", "titleManage.jsp" } });
	}
}