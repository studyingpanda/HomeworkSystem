package org.maoge.model;

/**
 * 课程类
 */
public class Course {
	private int courseId;
	private int courseUser;
	private String courseName;

	/**
	 * 冗余字段，仅用于查询
	 */
	private String courseUserName;

	public String getCourseUserName() {
		return courseUserName;
	}

	public void setCourseUserName(String courseUserName) {
		this.courseUserName = courseUserName;
	}

	// 省略get set方法
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
