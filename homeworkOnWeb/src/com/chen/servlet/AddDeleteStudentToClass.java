package com.chen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.dao.TeacherDao;
/**
 * Servlet implementation class AddDeleteStudentToClass
 */
@WebServlet("/AddDeleteStudentToClass")
public class AddDeleteStudentToClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDeleteStudentToClass() {
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
		String action = request.getParameter("action");
		String classID = request.getParameter("classID");
		String stuID = request.getParameter("stuID");
		String stuName = request.getParameter("stuName");
		TeacherDao teacherDao = new TeacherDao();
		if (action.equals("add")) {
			teacherDao.addStudentToClass(stuID, stuName, classID);
		} else {
			teacherDao.deleteStudentFromClass(stuID, classID);

		}
		response.sendRedirect(request.getContextPath() + "/teacher/stumanage.jsp");
	}

}
