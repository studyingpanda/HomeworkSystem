package org.maoge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.maoge.model.Course;
import org.maoge.model.User;
import org.maoge.utils.DbUtils;

/**
 * �γ̷�����
 */
public class CourseDao {

	/**
	 * ͨ��id��ȡ
	 */
	public Course getById(String courseId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DbUtils.getConnection();
			String sql = "select c.*,u.user_name from course c left join user u on c.course_user=u.user_id where c.course_id=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, courseId);
			rs = ps.executeQuery();
			if (rs.next()) {
				return makeOneCourse(rs);
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
	 * ����
	 */
	public int add(Course course) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DbUtils.getConnection();
			// ƴװsql��?ΪԤ��ռλ��
			String sql = "insert into course(course_user,course_name)values(?,?)";
			ps = conn.prepareStatement(sql);
			// ���������Բ���sqlԤ��λ��
			ps.setInt(1, course.getCourseUser());
			ps.setString(2, course.getCourseName());
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
	public int deleteById(int courseId) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DbUtils.getConnection();
			String sql = "delete from course where course_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, courseId);
			return ps.executeUpdate();
		} catch (SQLException e) {
			return 0;
		} finally {
			DbUtils.releaseConnection(null, ps, conn);
		}
	}

	/**
	 * ��ȡȫ��
	 */
	public List<Course> getCourses() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Course> courses = new ArrayList<Course>();
		try {
			conn = DbUtils.getConnection();
			String sql = "select c.*,u.user_name from course c left join user u on c.course_user=u.user_id";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				courses.add(makeOneCourse(rs));
			}
		} catch (SQLException e) {
		} finally {
			DbUtils.releaseConnection(rs, ps, conn);
		}
		return courses;
	}

	/**
	 * ͨ��userId��ȡ��Ӧ�γ�
	 */
	public List<Course> getCoursesByUserId(int userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Course> courses = new ArrayList<Course>();
		try {
			conn = DbUtils.getConnection();
			String sql = "select c.*,u.user_name from course c left join user u on c.course_user=u.user_id where c.course_user=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				courses.add(makeOneCourse(rs));
			}
		} catch (SQLException e) {
		} finally {
			DbUtils.releaseConnection(rs, ps, conn);
		}
		return courses;
	}

	/**
	 * ��ȡһ��
	 */
	public Course makeOneCourse(ResultSet rs) throws SQLException {
		Course course = new Course();
		course.setCourseId(rs.getInt("course_id"));
		course.setCourseName(rs.getString("course_name"));
		course.setCourseUser(rs.getInt("course_user"));
		course.setCourseUserName(rs.getString("user_name"));
		return course;
	}

	/**
	 * ����id�޸�������Ϣ
	 */
	public int update(Course course) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DbUtils.getConnection();
			String sql = "update course set course_user=?,course_name=? where course_id=? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, course.getCourseUser());
			ps.setString(2, course.getCourseName());
			ps.setInt(3, course.getCourseId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			return 0;
		} finally {
			DbUtils.releaseConnection(rs, ps, conn);
		}
	}
}
