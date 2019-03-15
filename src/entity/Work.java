package entity;
public class Work {//∂‘”¶system_work
	private int workId;
	private String workTitle;
	private String workTime;
	private Course workCourse;
	public int getWorkId() {
		return workId;
	}
	public void setWorkId(int workId) {
		this.workId = workId;
	}
	public String getWorkTitle() {
		return workTitle;
	}
	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public Course getWorkCourse() {
		return workCourse;
	}
	public void setWorkCourse(Course workCourse) {
		this.workCourse = workCourse;
	}
}
