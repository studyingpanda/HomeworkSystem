package org.maoge.model;

/**
 * 作业题目类
 */
public class Title {
	private int titleId;
	private String titleContent;
	private int titleCourse;
	private String titleTime;
	/**
	 * 冗余字段用于显示
	 */
	private String titleCourseName;

	// 省略get set方法
	public int getTitleId() {
		return titleId;
	}

	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}

	public String getTitleContent() {
		return titleContent;
	}

	public void setTitleContent(String titleContent) {
		this.titleContent = titleContent;
	}

	public int getTitleCourse() {
		return titleCourse;
	}

	public void setTitleCourse(int titleCourse) {
		this.titleCourse = titleCourse;
	}

	public String getTitleTime() {
		return titleTime;
	}

	public void setTitleTime(String titleTime) {
		this.titleTime = titleTime;
	}

	public String getTitleCourseName() {
		return titleCourseName;
	}

	public void setTitleCourseName(String titleCourseName) {
		this.titleCourseName = titleCourseName;
	}

}
