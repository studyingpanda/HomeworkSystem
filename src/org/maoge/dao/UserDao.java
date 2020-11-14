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
 * �û����ݷ�����
 */
public class UserDao {

	/**
	 * ͨ��id��ȡ�û�
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
	 * ͨ���û����������ȡ�û�
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
	 * ����
	 */
	public int add(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DbUtils.getConnection();
			// ƴװsql��?ΪԤ��ռλ��
			String sql = "insert into user(user_role,user_name,user_password)values(?,?,?)";
			ps = conn.prepareStatement(sql);
			// ���������Բ���sqlԤ��λ��
			ps.setString(1, user.getUserRole());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getUserPassword());
			// ִ��sql
			return ps.executeUpdate();
		} catch (SQLException e) {
			return 0;
		} finally {
			DbUtils.releaseConnection(null, ps, conn);
		}
	}

	/**
	 * ����idɾ��
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
	 * ��ȡȫ���û�
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
	 * ��ȡһ���û�
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
	 * ����id�޸�������Ϣ
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
