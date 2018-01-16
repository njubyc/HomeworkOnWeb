package com.chen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chen.jdbc.JdbcUtils;
import com.chen.users.AssTeacher;
import com.chen.users.Class;
import com.chen.users.Users;

public class AssTeacherDao {

	public AssTeacher find(Users user) {
		AssTeacher assTeacher = new AssTeacher();
		JdbcUtils jdbc = new JdbcUtils();
		try {
			Connection conn = jdbc.getConection();
			if(conn==null){
				System.out.println("数据库不存在");
			}
			String sql = "select * from assteacher where assTeacherID=? and assTeacherPwd=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				assTeacher.setName(rs.getString("assTeacherName"));
				assTeacher.setAssTeacherID(rs.getString("assTeacherID"));
				assTeacher.setPassword(rs.getString("assTeacherPwd"));
				jdbc.releace(conn, ps, rs);
			}else{
				assTeacher = null;
			}
			jdbc.releace(conn, ps, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return assTeacher;
			
	}
	public boolean assTeacherLogin(Users user) {
		if (find(user)!=null) {
			return true;
		} else {
			return false;

		}	
	}
	public void modifyPassword(String assTeacherID,String newPassword) {
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			String sql = "update assteacher set assTeacherPwd=? where assteacherID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, newPassword);
			ps.setString(2, assTeacherID);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public AssTeacher getPermission(String assTeacherID,String classID) {
		AssTeacher assTeacher = new AssTeacher();
		List<String> permission = new ArrayList<String>();
		JdbcUtils jdbc = new JdbcUtils();
		try {
			Connection conn = jdbc.getConection();
			if(conn==null){
				System.out.println("数据库不存在");
			}
			String sql = "select * from class_assteacher where assTeacherID=? and classID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, assTeacherID);
			ps.setString(2, classID);
			ResultSet rs = ps.executeQuery();
			while (rs.next())  {
				assTeacher.setAssTeacherID(assTeacherID);
				assTeacher.setStuManState(rs.getInt("stuManState"));
				assTeacher.setAddQuestion(rs.getInt("addQuestion"));
				assTeacher.setAddHomework(rs.getInt("addHomework"));
				assTeacher.setCorrectHomework(rs.getInt("correctHomework"));
				if (assTeacher.getAddHomework()==1) {
					permission.add("添加作业");
				}
				if (assTeacher.getStuManState()==1) {
					permission.add("学生管理");
				}
				if (assTeacher.getAddQuestion()==1) {
					permission.add("添加题目");
				}
				if (assTeacher.getCorrectHomework()==1) {
					permission.add("批改作业");
				}
				assTeacher.setPermission(permission);
			}
			jdbc.releace(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return assTeacher;
		
	}
	public int getAddQuestionPermission(String assTeacherID) {
		JdbcUtils jdbc = new JdbcUtils();
		int addQuestion=0;
		try {
			Connection conn = jdbc.getConection();
			if(conn==null){
				System.out.println("数据库不存在");
			}
			String sql = "select * from class_assteacher where assTeacherID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, assTeacherID);
			ResultSet rs = ps.executeQuery();
			while (rs.next())  {
				addQuestion=rs.getInt("addQuestion");
			}
			jdbc.releace(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addQuestion;
		
	}
	public Class getClass(String classID) {
		Class classD = new Class();
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			if(conn==null){
				System.out.println("数据库不存在");
			}
			String sql = "select * from class_teacher where classID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, classID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				classD.setClassID(rs.getString("classID"));
				classD.setTeacherName(rs.getString("teacherName"));
				classD.setCourse(rs.getString("course"));
			}
			jdbc.releace(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classD;	
	}
	public List<Class> showClasses(String assTeacherID,String permission){
		List<Class> classes = new ArrayList<Class>();
		try {
			JdbcUtils jdbc = new JdbcUtils();
			Connection conn = jdbc.getConection();
			if(conn==null){
				System.out.println("数据库不存在");
			}
			String sql = null;
			switch (permission) {
			case "stuManState":
				sql = "select * from class_assteacher where assTeacherID=? and stuManState=1";
				break;
			case "addQuestion":
				sql = "select * from class_assteacher where assTeacherID=? and addQuestion=1";
				break;
			case "addHomework":
				sql = "select * from class_assteacher where assTeacherID=? and addHomework=1";
				break;
			case "correctHomework":
				sql = "select * from class_assteacher where assTeacherID=? and correctHomework=1";
				break;
			case "all":
				sql = "select * from class_assteacher where assTeacherID=?";
				break;

			default:
				break;
			}
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, assTeacherID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Class classD = getClass(rs.getString("classID"));
				classes.add(classD);
			}
			jdbc.releace(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classes;		
	}
}
