package com.chen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chen.jdbc.JdbcUtils;
import com.chen.users.AssTeacher;
import com.chen.users.Homework;
import com.chen.users.Question;
import com.chen.users.Student;
import com.chen.users.Class;
import com.chen.users.Teacher;
import com.chen.users.Users;

public class TeacherDao {

	public Teacher find(Users user) {
		Teacher teacher = new Teacher();
		JdbcUtils jdbc = new JdbcUtils();
		try {
			Connection conn = jdbc.getConection();
			if(conn==null){
				System.out.println("数据库不存在");
			}
			String sql = "select * from teacher where teacherID=? and password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				teacher.setName(rs.getString("name"));
				teacher.setTeacherID(rs.getString("teacherID"));
				teacher.setPassword(rs.getString("password"));
				jdbc.releace(conn, ps, rs);
			}else{
				teacher = null;
			}
			jdbc.releace(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacher;
			
	}
	public boolean teacherLogin(Users user) {
		if (find(user)!=null) {
			return true;
		} else {
			return false;

		}	
	}
	public void modifyPassword(String teacherID,String newPassword) {
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "update teacher set password=? where teacherID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, newPassword);
			ps.setString(2, teacherID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Student> showAllStudents(){
		List<Student> students = new ArrayList<Student>();
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			if(conn==null){
				System.out.println("数据库不存在");
			}
			String sql = "select * from student";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setStuID(rs.getString("stuID"));
				student.setName(rs.getString("name"));
				students.add(student);
			}
			jdbc.releace(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
		
	}
	
	public List<Student> showClassStudents(String classID){
		List<Student> students = new ArrayList<Student>();
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			if(conn==null){
				System.out.println("数据库不存在");
			}
			String sql = "select * from class_student where classID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, classID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setStuID(rs.getString("stuID"));
				student.setName(rs.getString("stuName"));
				students.add(student);
			}
			jdbc.releace(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
		
	}
	public List<Class> showClasses(String teacherID){
		List<Class> classes = new ArrayList<Class>();
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			if(conn==null){
				System.out.println("数据库不存在");
			}
			String sql = "select * from class_teacher where teacherID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, teacherID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Class classD = new Class();
				classD.setClassID(rs.getString("classID"));
				classD.setTeacherName(rs.getString("teacherName"));
				classD.setCourse(rs.getString("course"));
				classes.add(classD);
			}
			jdbc.releace(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classes;		
	}
	public List<Class> showClassesHomework(String homeworkID){
		List<Class> classes = new ArrayList<Class>();
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			if(conn==null){
				System.out.println("数据库不存在");
			}
			String sql = "select * from homework where homeworkID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, homeworkID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Class classD = new Class();
				classD.setClassID(rs.getString("classID"));
				classes.add(classD);
			}
			jdbc.releace(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classes;		
	}
	
	public void addStudent(String stuID,String stuName) {
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "replace into student(stuID,name) values(?,?)";/////待修改///////////////
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, stuID);
			ps.setString(2, stuName);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addStudentToClass(String stuID,String stuName,String classID) {
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "insert into class_student(classID,stuID,stuName) values(?,?,?)";/////待修改///////////////
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, classID);
			ps.setString(2, stuID);
			ps.setString(3, stuName);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addClass(String classID,String course,String teacherID,String teacherName) {
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "insert into class_teacher(teacherID,teacherName,classID,course) values(?,?,?,?)";/////待修改///////////////
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,teacherID);
			ps.setString(2, teacherName);
			ps.setString(3, classID);
			ps.setString(4, course);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addAssTeacherToClass(String classID,String teacherID,String assTeacherID,String assTeacherName) {
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "replace into class_assteacher(classID,teacherID,assTeacherID,assTeacherName) values(?,?,?,?)";/////待修改///////////////
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, classID);
			ps.setString(2, teacherID);
			ps.setString(3, assTeacherID);
			ps.setString(4, assTeacherName);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteStudent(String stuID) {
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "delete from student where stuID=?";/////待修改///////////////
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, stuID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//////////////////////////////////////////////////////////////////////////重点////////////////////////////
	public void deleteStudentFromClass(String stuID,String classID) {
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "delete from class_student where stuID=? and classID=?";/////待修改///////////////
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, stuID);
			ps.setString(2, classID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteClass(String classID,String teacherID) {
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "delete from class_teacher where teacherID=? and classID=?";/////待修改///////////////
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, teacherID);
			ps.setString(2, classID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAssTeacherFromClass(String classID,String assTeacherID) {
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "delete from class_assteacher where assTeacherID=? and classID=?";/////待修改///////////////
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(2, classID);
			ps.setString(1, assTeacherID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<AssTeacher> showAllAssTeachers(){
		List<AssTeacher> assTeachers = new ArrayList<AssTeacher>();
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			if(conn==null){
				System.out.println("数据库不存在");
			}
			String sql = "select * from assteacher";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AssTeacher assTeacher = new AssTeacher();
				assTeacher.setAssTeacherID(rs.getString("assTeacherID"));
				assTeacher.setName(rs.getString("assTeacherName"));
				assTeachers.add(assTeacher);
			}
			jdbc.releace(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return assTeachers;		
	}
	
	public List<AssTeacher> showAssTeachers(String classID){
		List<AssTeacher> assTeachers = new ArrayList<AssTeacher>();
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			if(conn==null){
				System.out.println("数据库不存在");
			}
			String sql = "select * from class_assteacher where classID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, classID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AssTeacher assTeacher = new AssTeacher();
				assTeacher.setAssTeacherID(rs.getString("assTeacherID"));
				assTeacher.setName(rs.getString("assTeacherName"));
				assTeacher.setStuManState(rs.getInt("stuManState"));
				assTeacher.setAddQuestion(rs.getInt("addQuestion"));
				assTeacher.setAddHomework(rs.getInt("addHomework"));
				assTeacher.setCorrectHomework(rs.getInt("correctHomework"));
				assTeachers.add(assTeacher);
			}
			jdbc.releace(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return assTeachers;		
	}
	
	public AssTeacher showAssTeacherOfClass(String classID,String assTeacherID){
		AssTeacher assTeacher = new AssTeacher();
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			if(conn==null){
				System.out.println("数据库不存在");
			}
			String sql = "select * from class_assteacher where classID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, classID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				assTeacher.setAssTeacherID(rs.getString("assTeacherID"));
				assTeacher.setName(rs.getString("assTeacherName"));
				assTeacher.setStuManState(rs.getInt("stuManState"));
				assTeacher.setAddQuestion(rs.getInt("addQuestion"));
				assTeacher.setAddHomework(rs.getInt("addHomework"));
				assTeacher.setCorrectHomework(rs.getInt("correctHomework"));
			}
			jdbc.releace(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return assTeacher;		
	}
	
	public void setPermission(String assTeacherID,String classID,String permission,int tof) {
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = null;
			switch (permission) {
			case "stuManState":
				sql = "update class_assteacher set stuManState=? where assTeacherID=? and classID=?";
				break;
			case "addQuestion":
				sql = "update class_assteacher set addQuestion=? where assTeacherID=? and classID=?";
				break;
			case "addHomework":
				sql = "update class_assteacher set addHomework=? where assTeacherID=? and classID=?";
				break;
			case "correctHomework":
				sql = "update class_assteacher set correctHomework=? where assTeacherID=? and classID=?";
				break;

			default:
				break;
			}
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, tof);
			ps.setString(2, assTeacherID);
			ps.setString(3, classID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addQuestion(Question question){
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "replace into questions(tskID,course,chapter,author,tskDetail) values(?,?,?,?,?)";/////待修改///////////////
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, question.getTskID());
			ps.setString(2, question.getCourse());
			ps.setString(3, question.getChapter());
			ps.setString(4, question.getAuthor());
			ps.setString(5, question.getTskDetail());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateQuestion(String tskDetail,String tskID){
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "update questions set tskDetail=? where tskID=?";/////待修改///////////////
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, tskDetail);
			ps.setString(2, tskID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Question> showQuestions() {
		List<Question> questions = new ArrayList<Question>();
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			if(conn==null){
				System.out.println("数据库不存在");
			}
			String sql = "select * from questions";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Question question = new Question();
				question.setTskID(rs.getString("tskID"));
				question.setAuthor(rs.getString("author"));
				question.setCourse(rs.getString("course"));
				question.setChapter(rs.getString("chapter"));
				question.setTskDetail(rs.getString("tskDetail"));
				questions.add(question);
			}
			jdbc.releace(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questions;
		
	}
	public Question showQuestion(String tskID) {
		Question question = new Question();
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			if(conn==null){
				System.out.println("数据库不存在");
			}
			String sql = "select * from questions where tskID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, tskID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				question.setAuthor(rs.getString("author"));
				question.setCourse(rs.getString("course"));
				question.setChapter(rs.getString("chapter"));
				question.setTskDetail(rs.getString("tskDetail"));
			}
			jdbc.releace(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return question;
		
	}
	
	public void addHomework(Homework homework){
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "replace into homework(homeworkID,classID,homeworkTitle,deadline,homeworkState) values(?,?,?,?,?)";/////待修改///////////////
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, homework.getHomeworkID());
			ps.setString(2, homework.getClassID());
			ps.setString(3, homework.getHomeworkTitle());
			ps.setDate(4, homework.getDeadline());
			ps.setString(5, homework.getHomeworkState());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Homework> showHomeworks(String classID) {
		List<Homework> homeworks = new ArrayList<Homework>();
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			if(conn==null){
				System.out.println("数据库不存在");
			}
			String sql = "select * from homework where classID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, classID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Homework homework = new Homework();
				homework.setClassID(rs.getString("classID"));
				homework.setHomeworkID(rs.getString("homeworkID"));
				homework.setHomeworkTitle(rs.getString("homeworkTitle"));
				homework.setDeadline(rs.getDate("deadline"));
				homework.setHomeworkState(rs.getString("homeworkState"));
				homeworks.add(homework);
			}
			jdbc.releace(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return homeworks;
		
	}
	public List<Question> showQuestionOfHomework(String homeworkID) {
		List<Question> questions = new ArrayList<Question>();
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			if(conn==null){
				System.out.println("数据库不存在");
			}
			String sql = "select * from task_detail where homeworkID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, homeworkID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Question question = new Question();
				question.setTskID(rs.getString("tskID"));
				question.setTskDetail(rs.getString("tskDetail"));
				question.setTskContent(rs.getString("tskContent"));
				questions.add(question);
			}
			jdbc.releace(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questions;
		
	}
	
	public void addQuestionToHomework(String homeworkID,String tskID,String tskContent,String tskDetail) {
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "replace into task_detail(homeworkID,tskID,tskContent,tskDetail) values(?,?,?,?)";/////待修改///////////////
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, homeworkID);
			ps.setString(2, tskID);
			ps.setString(3, tskContent);
			ps.setString(4, tskDetail);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteQuestionFromHomework(String homeworkID,String tskID) {
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "delete from task_detail where homeworkID=? and tskID=?";/////待修改///////////////
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, homeworkID);
			ps.setString(2, tskID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void addQuestionToStuAnswer(String tskID,String tskDetail,String homeworkID,String stuID){
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "replace into task_answerstu(tskID,tskDetail,homeworkID,stuID) values(?,?,?,?)";/////待修改///////////////
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, tskID);
			ps.setString(2, tskDetail);
			ps.setString(3, homeworkID);
			ps.setString(4, stuID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteQuestionToStuAnswer(String tskID,String homeworkID,String stuID){
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "delete from task_answerstu where tskID=? and homeworkID=? and stuID=?";/////待修改///////////////
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, tskID);
			ps.setString(2, homeworkID);
			ps.setString(3, stuID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Question> showStuAnswer(String homeworkID,String stuID) {
		List<Question> questions = new ArrayList<Question>();
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			if(conn==null){
				System.out.println("数据库不存在");
			}
			String sql = "select * from task_answerstu where homeworkID=? and stuID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, homeworkID);
			ps.setString(2, stuID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Question question = new Question();
				question.setTskID(rs.getString("tskID"));
				question.setTskDetail(rs.getString("tskDetail"));
				question.setTskStuAnswer(rs.getString("tskStuAnswer"));
				questions.add(question);
			}
			jdbc.releace(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questions;
		
	}
	
	public void addGrade(String classID,String stuID,String homeworkTitle) {
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "replace into stugrade(classID,stuID,homeworkTitle,subState,grade) values(?,?,?,?,?)";/////待修改///////////////
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, classID);
			ps.setString(2, stuID);
			ps.setString(3, homeworkTitle);
			ps.setString(4, "0");
			ps.setString(5, "无");
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String[] showGrade(String classID,String stuID,String homeworkTitle) {
		String[] grade = new String[2];
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			if(conn==null){
				System.out.println("数据库不存在");
			}
			String sql = "select * from stugrade where classID=? and stuID=? and homeworkTitle=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, classID);
			ps.setString(2, stuID);
			ps.setString(3, homeworkTitle);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				grade[0] = rs.getString("grade");
				grade[1] = rs.getString("subState");
			}
			jdbc.releace(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return grade;
		
	}
	public void submitGrade(String classID,String stuID,String homeworkTitle,String grade) {
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "update stugrade set subState='1', Grade=? where stuID=? and homeworkTitle=? and classID=?";/////待修改///////////////
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, grade);
			ps.setString(2, stuID);
			ps.setString(3, homeworkTitle);
			ps.setString(4, classID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void addAssTeacher(String assTeacherID, String assTeacherName) {
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "replace into assteacher(assTeacherID,assTeacherName) values(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, assTeacherID);
			ps.setString(2, assTeacherName);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	public void deleteAssTeacher(String assTeacherID) {
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "delete from assteacher where assTeacherID=?";/////待修改///////////////
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, assTeacherID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String getAssTeacherName(String assteacherID) {
		String assTeacherName = null;
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			if(conn==null){
				System.out.println("数据库不存在");
			}
			String sql = "select * from assteacher where assTeacherID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, assteacherID);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				assTeacherName=rs.getString("assTeacherName");
			}
			jdbc.releace(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return assTeacherName;
	}

}
