/**
 * entity������������ʵ����İ�
 * ʵ������У�Course Job Lesson Role User Work
 */
package entity;
public class Course{//��Ӧsystem_course
	private int courseId;
	private String courseName;
	private User courseUser;
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public User getCourseUser() {
		return courseUser;
	}
	public void setCourseUser(User courseUser) {
		this.courseUser = courseUser;
	}
}
