package com.chen.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.dao.TeacherDao;
import com.chen.users.Homework;
import com.chen.users.Student;

/**
 * Servlet implementation class AddHomework
 */
@WebServlet("/AddHomework")
public class AddHomework extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddHomework() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		java.util.Date d = null;
		try {
			d = format.parse(request.getParameter("deadline"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date deadline = new java.sql.Date(d.getTime()); ;
		Homework homework = new Homework();
		homework.setHomeworkID(request.getParameter("homeworkID"));
		homework.setHomeworkTitle(request.getParameter("homeworkTitle"));
		homework.setClassID(request.getParameter("classID"));
		homework.setDeadline(deadline);
		homework.setHomeworkState(request.getParameter("homeworkState"));
		TeacherDao teacherDao = new TeacherDao();
		teacherDao.addHomework(homework);
		List<Student>students = teacherDao.showClassStudents(request.getParameter("classID"));
		for(Student student:students){
			teacherDao.addGrade(request.getParameter("classID"), student.getStuID(), request.getParameter("homeworkTitle"));;
		}
		response.sendRedirect(request.getContextPath() + "/teacher/ex_assign.jsp");
	}

}
