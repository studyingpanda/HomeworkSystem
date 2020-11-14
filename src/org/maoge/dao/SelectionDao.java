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
 * ѡ�η�����
 */
public class SelectionDao {

	/**
	 * ͨ��courseId��ȡ
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
	 * ����
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
	 * ����idɾ��
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
	 * ��ȡȫ��
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
	 * ��ȡһ��
	 */
	public Selection makeOneSelection(ResultSet rs) throws SQLException {
		Selection selection = new Selection();
		selection.setSelectionId(rs.getInt("selection_id"));
		selection.setSelectionUser(rs.getInt("selection_user"));
		selection.setSelectionCourse(rs.getInt("selection_course"));
		return selection;
	}

	/**
	 * ����id�޸�������Ϣ
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
