package com.chen.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.dao.TeacherDao;
import com.chen.users.Question;
import com.chen.users.Class;
import com.chen.users.Student;

/**
 * Servlet implementation class AddDeleteQuestionToHomework
 */
@WebServlet("/AddDeleteQuestionToHomework")
public class AddDeleteQuestionToHomework extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDeleteQuestionToHomework() {
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
		String homeworkID = request.getParameter("homeworkID");
		String tskID = request.getParameter("tskID");
		Question question = teacherDao.showQuestion(tskID);
		String tskContent = request.getParameter("tskContent");
		String tskDetail = question.getTskDetail();
		List<Class> classes = teacherDao.showClassesHomework(homeworkID);
		
		if (action.equals("add")) {
			teacherDao.addQuestionToHomework(homeworkID, tskID, tskContent, tskDetail);
			for(Class classD:classes){
				List<Student>students = teacherDao.showClassStudents(classD.getClassID());
				for(Student student:students){
					teacherDao.addQuestionToStuAnswer(tskID, tskDetail, homeworkID, student.getStuID());
				}
			}
		} else {
			teacherDao.deleteQuestionFromHomework(homeworkID, tskID);
			for(Class classD:classes){
				List<Student>students = teacherDao.showClassStudents(classD.getClassID());
				for(Student student:students){
					teacherDao.deleteQuestionToStuAnswer(tskID, homeworkID, student.getStuID());
				}
			}

		}
		response.sendRedirect(request.getContextPath() + "/teacher/ex_assign.jsp");
	}

}
