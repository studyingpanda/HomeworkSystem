package org.maoge.model;

/**
 * �γ���
 */
public class Course {
	private int courseId;
	private int courseUser;
	private String courseName;

	/**
	 * �����ֶΣ������ڲ�ѯ
	 */
	private String courseUserName;

	public String getCourseUserName() {
		return courseUserName;
	}

	public void setCourseUserName(String courseUserName) {
		this.courseUserName = courseUserName;
	}

	// ʡ��get set����
	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getCourseUser() {
		return courseUser;
	}

	public void setCourseUser(int courseUser) {
		this.courseUser = courseUser;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

}
