package entity;
//Ñ§ÉúÑ¡¿Î
public class Lesson {
	private int lessonId;
	private User lessonUser;
	private Course lessonCourse;
	public int getLessonId() {
		return lessonId;
	}
	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}
	public User getLessonUser() {
		return lessonUser;
	}
	public void setLessonUser(User lessonUser) {
		this.lessonUser = lessonUser;
	}
	public Course getLessonCourse() {
		return lessonCourse;
	}
	public void setLessonCourse(Course lessonCourse) {
		this.lessonCourse = lessonCourse;
	}
}
