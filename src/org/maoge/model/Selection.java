package org.maoge.model;

/**
 * 学生选课类
 */
public class Selection {
	private int selectionId;
	private int selectionUser;
	private int selectionCourse;

	// 省略get set方法
	public int getSelectionId() {
		return selectionId;
	}

	public void setSelectionId(int selectionId) {
		this.selectionId = selectionId;
	}

	public int getSelectionUser() {
		return selectionUser;
	}

	public void setSelectionUser(int selectionUser) {
		this.selectionUser = selectionUser;
	}

	public int getSelectionCourse() {
		return selectionCourse;
	}

	public void setSelectionCourse(int selectionCourse) {
		this.selectionCourse = selectionCourse;
	}

}
