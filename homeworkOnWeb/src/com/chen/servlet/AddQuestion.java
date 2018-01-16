package com.chen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chen.dao.TeacherDao;
import com.chen.users.Question;
import com.chen.users.Teacher;

/**
 * Servlet implementation class AddQuestion
 */
@WebServlet("/AddQuestion")
public class AddQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuestion() {
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
		HttpSession session = request.getSession();
		String author = (String) (((Teacher) session.getAttribute("user")).getName());
		Question question = new Question();
		question.setAuthor(author);
		question.setChapter(request.getParameter("chapter"));
		question.setCourse(request.getParameter("course"));
		question.setTskDetail(request.getParameter("tskDetail"));
		question.setTskID(request.getParameter("tskID"));
		System.out.println(request.getParameter("tskID"));
		TeacherDao teacherDao = new TeacherDao();
		teacherDao.addQuestion(question);
	}

}
