package com.chen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.dao.TeacherDao;

/**
 * Servlet implementation class AddGrade
 */
@WebServlet("/AddGrade")
public class AddGrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGrade() {
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
		String classID = request.getParameter("classID");
		String stuID = request.getParameter("stuID");
		String homeworkTitle = request.getParameter("homeworkTitle");
		String grade = request.getParameter("grade");
		System.out.println(classID+stuID+homeworkTitle+grade);
		TeacherDao teacherDao = new TeacherDao();
		teacherDao.submitGrade(classID, stuID, homeworkTitle, grade);
	}

}
