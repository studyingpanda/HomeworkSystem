package org.maoge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.maoge.model.Course;
import org.maoge.model.Title;
import org.maoge.utils.DbUtils;

/**
 * 作业题目访问类
 */
public class TitleDao {

	/**
	 * 通过userId获取对应作业题目
	 */
	public List<Title> getTitlesByUserId(int userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Title> titles = new ArrayList<Title>();
		try {
			conn = DbUtils.getConnection();
			String sql = "select t.*,c.course_name from title t left join course c on t.title_course=c.course_id left join user u on c.course_user=u.user_id where u.user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				titles.add(makeOneTitle(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DbUtils.releaseConnection(rs, ps, conn);
		}
		return titles;
	}

	/**
	 * 新增，返回新增的id
	 */
	public int add(Title title) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DbUtils.getConnection();
			String sql = "insert into title(title_content,title_course,title_time)values(?,?,?)";
			ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, title.getTitleContent());
			ps.setInt(2, title.getTitleCourse());
			ps.setString(3, title.getTitleTime());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			int id = 0;
			if (rs.next())
				id = rs.getInt(1);
			return id;
		} catch (SQLException e) {
			return 0;
		} finally {
			DbUtils.releaseConnection(null, ps, conn);
		}
	}

	/**
	 * 根据id删除
	 */
	public int deleteById(int titleId) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DbUtils.getConnection();
			String sql = "delete from title where title_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, titleId);
			return ps.executeUpdate();
		} catch (SQLException e) {
			return 0;
		} finally {
			DbUtils.releaseConnection(null, ps, conn);
		}
	}

	/**
	 * 获取全部
	 */
	public List<Title> getTitles() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Title> titles = new ArrayList<Title>();
		try {
			conn = DbUtils.getConnection();
			String sql = "select * from title";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				titles.add(makeOneTitle(rs));
			}
		} catch (SQLException e) {
		} finally {
			DbUtils.releaseConnection(rs, ps, conn);
		}
		return titles;
	}

	/**
	 * 获取一个
	 */
	public Title makeOneTitle(ResultSet rs) throws SQLException {
		Title title = new Title();
		title.setTitleId(rs.getInt("title_id"));
		title.setTitleContent(rs.getString("title_content"));
		title.setTitleCourse(rs.getInt("title_course"));
		title.setTitleTime(rs.getString("title_time"));
		if (rs.getString("course_name") != null) {
			title.setTitleCourseName(rs.getString("course_name"));
		}
		return title;
	}

	/**
	 * 根据id修改其他信息
	 */
	public int update(Title title) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DbUtils.getConnection();
			String sql = "update title set title_content=?,title_course=?,title_time=? where title_id=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, title.getTitleContent());
			ps.setInt(2, title.getTitleCourse());
			ps.setString(3, title.getTitleTime());
			return ps.executeUpdate();
		} catch (SQLException e) {
			return 0;
		} finally {
			DbUtils.releaseConnection(rs, ps, conn);
		}
	}
}
