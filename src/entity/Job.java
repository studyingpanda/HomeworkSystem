package entity;
public class Job{//∂‘”¶work_job
	private int jobId;	
	private String jobTime;
	private String jobContent;
	private String jobScore;
	private Work jobWork;
	private User jobUser;
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
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
	public Work getJobWork() {
		return jobWork;
	}
	public void setJobWork(Work jobWork) {
		this.jobWork = jobWork;
	}
	public User getJobUser() {
		return jobUser;
	}
	public void setJobUser(User jobUser) {
		this.jobUser = jobUser;
	}
}
