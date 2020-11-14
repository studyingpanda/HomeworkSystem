package org.maoge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.maoge.model.Selection;
import org.maoge.utils.DbUtils;

/**
 * 选课访问类
 */
public class SelectionDao {

	/**
	 * 通过courseId获取
	 */
	public List<Selection> getSelectionsByCourseId(int courseId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Selection> selections = new ArrayList<Selection>();
		try {
			conn = DbUtils.getConnection();
			String sql = "select * from selection where selection_course=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, courseId);
			rs = ps.executeQuery();
			while (rs.next()) {
				selections.add(makeOneSelection(rs));
			}
		} catch (SQLException e) {
		} finally {
			DbUtils.releaseConnection(rs, ps, conn);
		}
		return selections;
	}

	/**
	 * 新增
	 */
	public int add(Selection selection) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DbUtils.getConnection();
			String sql = "insert into selection(selection_user,selection_course)values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, selection.getSelectionUser());
			ps.setInt(2, selection.getSelectionCourse());
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
	public int deleteById(int selectionId) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DbUtils.getConnection();
			String sql = "delete from selection where selection_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, selectionId);
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
	public List<Selection> getSelections() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Selection> selections = new ArrayList<Selection>();
		try {
			conn = DbUtils.getConnection();
			String sql = "select * from selection";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				selections.add(makeOneSelection(rs));
			}
		} catch (SQLException e) {
		} finally {
			DbUtils.releaseConnection(rs, ps, conn);
		}
		return selections;
	}

	/**
	 * 获取一个
	 */
	public Selection makeOneSelection(ResultSet rs) throws SQLException {
		Selection selection = new Selection();
		selection.setSelectionId(rs.getInt("selection_id"));
		selection.setSelectionUser(rs.getInt("selection_user"));
		selection.setSelectionCourse(rs.getInt("selection_course"));
		return selection;
	}

	/**
	 * 根据id修改其他信息
	 */
	public int update(Selection selection) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DbUtils.getConnection();
			String sql = "update selection set selection_user=?,selection_course=? where selection_id=? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, selection.getSelectionUser());
			ps.setInt(2, selection.getSelectionCourse());
			ps.setInt(3, selection.getSelectionId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			return 0;
		} finally {
			DbUtils.releaseConnection(rs, ps, conn);
		}
	}
}
