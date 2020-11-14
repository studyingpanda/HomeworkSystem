package org.maoge.service;

import java.util.List;

import org.maoge.dao.UserDao;
import org.maoge.model.User;

public class LoginService {
	public User checkLogin(String userName, String userPassword) throws Exception {
		if (userName.equals("") || userPassword.equals("")) {
			// �׳�������Ϣ�쳣
			throw new Exception("�û��������벻��Ϊ��");
		}
		User user = null;
		try {
			UserDao userDao = new UserDao();
			List<User> list = userDao.getUser(userName, userPassword);
			if (list.size() == 1) {// ֻ��ƥ���һ���û�ʱ�����ǺϷ���¼
				user = list.get(0);
			}
		} catch (Exception e) {
			// �׳����ݿ��쳣
			throw new Exception("���ݿ�����쳣:" + e.getMessage());
		}
		return user;// ���ز�ѯ���
	}
}
