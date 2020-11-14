package org.maoge.service;

import java.util.List;

import org.maoge.dao.UserDao;
import org.maoge.model.User;

public class LoginService {
	public User checkLogin(String userName, String userPassword) throws Exception {
		if (userName.equals("") || userPassword.equals("")) {
			// 抛出输入信息异常
			throw new Exception("用户名和密码不能为空");
		}
		User user = null;
		try {
			UserDao userDao = new UserDao();
			List<User> list = userDao.getUser(userName, userPassword);
			if (list.size() == 1) {// 只有匹配出一个用户时，才是合法登录
				user = list.get(0);
			}
		} catch (Exception e) {
			// 抛出数据库异常
			throw new Exception("数据库操作异常:" + e.getMessage());
		}
		return user;// 返回查询结果
	}
}
