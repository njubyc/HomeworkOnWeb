package com.chen.users;

import java.util.List;

public class AssTeacher {

	private String assTeacherID;
	private String name;
	private String password;
	private List<String> permission;
	public List<String> getPermission() {
		return permission;
	}
	public void setPermission(List<String> permission) {
		this.permission = permission;
	}
	private int stuManState;
	private int addQuestion;
	private int addHomework;
	private int correctHomework;
	public int getStuManState() {
		return stuManState;
	}
	public void setStuManState(int stuManState) {
		this.stuManState = stuManState;
	}
	public int getAddQuestion() {
		return addQuestion;
	}
	public void setAddQuestion(int addQuestion) {
		this.addQuestion = addQuestion;
	}
	public int getAddHomework() {
		return addHomework;
	}
	public void setAddHomework(int addHomework) {
		this.addHomework = addHomework;
	}
	public int getCorrectHomework() {
		return correctHomework;
	}
	public void setCorrectHomework(int correctHomework) {
		this.correctHomework = correctHomework;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAssTeacherID() {
		return assTeacherID;
	}
	public void setAssTeacherID(String assTeacherID) {
		this.assTeacherID = assTeacherID;
	}
}
