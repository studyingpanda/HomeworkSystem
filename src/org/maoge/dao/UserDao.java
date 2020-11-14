package org.maoge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.maoge.model.User;
import org.maoge.utils.DbUtils;

/**
 * 用户数据访问类
 */
public class UserDao {

	/**
	 * 通过id获取用户
	 */
	public User getById(String userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DbUtils.getConnection();
			String sql = "select * from user where user_id=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				return makeOneUser(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
			return null;
		} finally {
			DbUtils.releaseConnection(rs, ps, conn);
		}
	}

	/**
	 * 通过用户名、密码获取用户
	 */
	public List<User> getUser(String name, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		try {
			conn = DbUtils.getConnection();
			String sql = "select * from user where user_name=? and user_password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				users.add(makeOneUser(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DbUtils.releaseConnection(rs, ps, conn);
		}
		return users;
	}

	/**
	 * 新增
	 */
	public int add(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DbUtils.getConnection();
			// 拼装sql，?为预留占位符
			String sql = "insert into user(user_role,user_name,user_password)values(?,?,?)";
			ps = conn.prepareStatement(sql);
			// 将对象属性插入sql预留位置
			ps.setString(1, user.getUserRole());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getUserPassword());
			// 执行sql
			return ps.executeUpdate();
		} catch (SQLException e) {
			return 0;
		} finally {
			DbUtils.releaseConnection(null, ps, conn);
		}
	}

	/**
	 * 根据id删除
	 */
	public int deleteById(int userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DbUtils.getConnection();
			String sql = "delete from user where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			return ps.executeUpdate();
		} catch (SQLException e) {
			return 0;
		} finally {
			DbUtils.releaseConnection(null, ps, conn);
		}
	}

	/**
	 * 获取全部用户
	 */
	public List<User> getUsers() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		try {
			conn = DbUtils.getConnection();
			String sql = "select * from user";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				users.add(makeOneUser(rs));
			}
		} catch (SQLException e) {
		} finally {
			DbUtils.releaseConnection(rs, ps, conn);
		}
		return users;
	}

	/**
	 * 获取一个用户
	 */
	public User makeOneUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUserId(rs.getInt("user_id"));
		user.setUserName(rs.getString("user_name"));
		user.setUserPassword(rs.getString("user_password"));
		user.setUserRole(rs.getString("user_role"));
		return user;
	}

	/**
	 * 根据id修改其他信息
	 */
	public int update(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DbUtils.getConnection();
			String sql = "update user set user_role=?,user_name=?, user_password=? where user_id=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserRole());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getUserPassword());
			ps.setInt(4, user.getUserId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			return 0;
		} finally {
			DbUtils.releaseConnection(rs, ps, conn);
		}
	}
}
