package com.chen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.dao.TeacherDao;
import com.chen.users.Teacher;

/**
 * Servlet implementation class AddDeleteAssTeacherToClass
 */
@WebServlet("/AddDeleteAssTeacherToClass")
public class AddDeleteAssTeacherToClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDeleteAssTeacherToClass() {
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
		TeacherDao teacherDao = new TeacherDao();
		String action = request.getParameter("action");
		String classID = request.getParameter("classID");
		String assTeacherID = request.getParameter("assTeacherID");
		//String assTeacherName = request.getParameter("assTeacherName");
		String assTeacherName = teacherDao.getAssTeacherName(assTeacherID);
		System.out.println(assTeacherID+assTeacherName);
		Teacher teacher = (Teacher)request.getSession().getAttribute("user");
		
		if (action.equals("add")) {
			teacherDao.addAssTeacherToClass(classID, teacher.getTeacherID(), assTeacherID, assTeacherName);
		} else {
			teacherDao.deleteAssTeacherFromClass(classID, assTeacherID);

		}
		response.sendRedirect(request.getContextPath() + "/teacher/tamanage.jsp");

	}

}
