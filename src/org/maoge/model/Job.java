package org.maoge.model;

/**
 * ��ҵ�ύ��
 */
public class Job {
	private int jobId;
	private int jobTitle;
	private int jobUser;
	private String jobTime;
	private String jobContent;
	private String jobScore;
	/**
	 * �����ֶ�
	 */
	private String jobUserName;

	public String getJobUserName() {
		return jobUserName;
	}

	public void setJobUserName(String jobUserName) {
		this.jobUserName = jobUserName;
	}

	// ʡ��get set����
	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(int jobTitle) {
		this.jobTitle = jobTitle;
	}

	public int getJobUser() {
		return jobUser;
	}

	public void setJobUser(int jobUser) {
		this.jobUser = jobUser;
	}

	public String getJobTime() {
		return jobTime;
	}

	public void setJobTime(String jobTime) {
		this.jobTime = jobTime;
	}

	public String getJobContent() {
		return jobContent;
	}

	public void setJobContent(String jobContent) {
		this.jobContent = jobContent;
	}

	public String getJobScore() {
		return jobScore;
	}

	public void setJobScore(String jobScore) {
		this.jobScore = jobScore;
	}

}
