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
 * Servlet implementation class AddClass
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddClass" })
public class AddDeleteClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDeleteClass() {
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
		String course = request.getParameter("course");
		Teacher teacher = (Teacher)request.getSession().getAttribute("user");
		System.out.println(action+classID+course);
		TeacherDao teacherDao = new TeacherDao();
		if (action.equals("add")) {
			teacherDao.addClass(classID, course, teacher.getTeacherID(), teacher.getName());
		} else {
			teacherDao.deleteClass(classID, teacher.getTeacherID());

		}
		response.sendRedirect(request.getContextPath() + "/teacher/stumanage.jsp");
	}

}
