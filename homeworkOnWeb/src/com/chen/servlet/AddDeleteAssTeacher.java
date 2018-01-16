package com.chen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.dao.TeacherDao;

/**
 * Servlet implementation class AddDeleteAssTeacher
 */
@WebServlet("/AddDeleteAssTeacher")
public class AddDeleteAssTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDeleteAssTeacher() {
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
		String assTeacherID = request.getParameter("assTeacherID");
		String assTeacherName = request.getParameter("assTeacherName");
		TeacherDao teacherDao = new TeacherDao();
		if (action.equals("add")) {
			teacherDao.addAssTeacher(assTeacherID, assTeacherName);
		} else {
			teacherDao.deleteAssTeacher(assTeacherID);

		}
		response.sendRedirect(request.getContextPath() + "/teacher/tamanage.jsp");
	}

}
