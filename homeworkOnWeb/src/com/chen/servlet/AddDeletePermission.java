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
 * Servlet implementation class AddDeletePermission
 */
@WebServlet("/AddDeletePermission")
public class AddDeletePermission extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDeletePermission() {
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
		String permission = request.getParameter("permission");
		String classID = request.getParameter("classID");
		String assTeacherID = request.getParameter("assTeacherID");
		//String assTeacherName = request.getParameter("assTeacherName");
		
		System.out.println(assTeacherID+permission);
		if (action.equals("add")) {
			teacherDao.setPermission(assTeacherID, classID, permission, 1);
		} else {
			teacherDao.setPermission(assTeacherID, classID, permission, 0);

		}
		response.sendRedirect(request.getContextPath() + "/teacher/tamanage.jsp");

	}

}
