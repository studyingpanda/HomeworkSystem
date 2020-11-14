package org.maoge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.maoge.model.Job;
import org.maoge.utils.DbUtils;

/**
 * 作业内容访问类
 */
public class JobDao {
	/**
	 * 新增
	 */
	public int add(Job job) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DbUtils.getConnection();
			String sql = "insert into job(job_title,job_user,job_time,job_content,job_score)values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, job.getJobTitle());
			ps.setInt(2, job.getJobUser());
			ps.setString(3, job.getJobTime());
			ps.setString(4, job.getJobContent());
			ps.setString(5, job.getJobScore());
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		} finally {
			DbUtils.releaseConnection(null, ps, conn);
		}
	}

	/**
	 * 根据id删除
	 */
	public int deleteById(int jobId) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DbUtils.getConnection();
			String sql = "delete from job where job_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, jobId);
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
	public List<Job> getJobs() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Job> jobs = new ArrayList<Job>();
		try {
			conn = DbUtils.getConnection();
			String sql = "select * from job";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				jobs.add(makeOneJob(rs));
			}
		} catch (SQLException e) {
		} finally {
			DbUtils.releaseConnection(rs, ps, conn);
		}
		return jobs;
	}

	/**
	 * 获取一个
	 */
	public Job makeOneJob(ResultSet rs) throws SQLException {
		Job job = new Job();
		job.setJobId(rs.getInt("job_id"));
		job.setJobTitle(rs.getInt("job_title"));
		job.setJobUser(rs.getInt("job_user"));
		job.setJobTime(rs.getString("job_time"));
		job.setJobContent(rs.getString("job_content"));
		job.setJobScore(rs.getString("job_score"));
		return job;
	}

	/**
	 * 根据id修改其他信息
	 */
	public int update(Job job) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DbUtils.getConnection();
			String sql = "update job set job_title=?,job_user=?,job_time=?,job_content=?,job_score=? where job_id=? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, job.getJobTitle());
			ps.setInt(2, job.getJobUser());
			ps.setString(3, job.getJobTime());
			ps.setString(4, job.getJobContent());
			ps.setString(5, job.getJobScore());
			ps.setInt(6, job.getJobId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			return 0;
		} finally {
			DbUtils.releaseConnection(rs, ps, conn);
		}
	}
}
