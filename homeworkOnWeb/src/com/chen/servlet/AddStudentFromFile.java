package com.chen.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.chen.dao.TeacherDao;

/**
 * Servlet implementation class AddStudentFromFile
 */
@WebServlet("/AddStudentFromFile")
public class AddStudentFromFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentFromFile() {
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
		//request.setCharacterEncoding("UTF-8");
		TeacherDao teacherDao = new TeacherDao();
		BufferedReader reader = null;
		String tmpString = null;
		 request.setCharacterEncoding("utf-8");  
	        response.setCharacterEncoding("utf-8");  
	        response.setContentType("text/html;charset=utf-8");//更改响应字符流使用的编码，还能告知浏览器用什么编码进行显示  
	          
	        //从request中获取文本输入流信息  
	        InputStream fileSourceStream = request.getInputStream();  
	        String tempFileName = "E:/tempFile.txt";  
	  
	        //设置临时文件，保存待上传的文本输入流  
	        File tempFile = new File(tempFileName);  
	          
	        //outputStram文件输出流指向这个tempFile  
	        FileOutputStream outputStream = new FileOutputStream(tempFile);  
	          
	        //读取文件流  
	        byte temp[] = new byte[1024];  
	        int n;  
	        while(( n = fileSourceStream.read(temp)) != -1){  
	            outputStream.write(temp, 0, n);  
	        }  
	        outputStream.close();  
	        fileSourceStream.close();  
	          
	        //获取上传文件的名称   
	        RandomAccessFile randomFile = new RandomAccessFile(tempFile,"r");  
	        randomFile.readLine();    
	        String str = randomFile.readLine();  
	        int start = str.lastIndexOf("=") + 2;  
	        int end = str.lastIndexOf("\"");  
	        String filename = str.substring(start, end);  
	          
	        //定位文件指针到文件头  
	        randomFile.seek(0);  
	        long startIndex = 0;  
	        int i = 1;  
	        //获取文件内容的开始位置  
	        while(( n = randomFile.readByte()) != -1 && i <=4){  
	            if(n == '\n'){  
	                startIndex = randomFile.getFilePointer();  
	                i ++;  
	            }  
	        }  
	        startIndex = startIndex -1; //这里一定要减1，因为前面多读了一个，这里很容易忽略  
	        //获取文件内容结束位置  
	        randomFile.seek(randomFile.length());  
	        long endIndex = randomFile.getFilePointer();  
	        int j = 1;  
	        while(endIndex >=0 && j<=2){  
	            endIndex--;  
	            randomFile.seek(endIndex);  
	            if(randomFile.readByte() == '\n'){  
	                j++;  
	            }  
	        }  
	          
	        //设置保存上传文件的路径  
	        File saveFile = new File(filename);  
	        RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile,"rw");  
	        //根据起止位置从临时文件中读取文件内容  
	        randomFile.seek(startIndex);  
	        while(startIndex < endIndex){  
	            randomAccessFile.write(randomFile.readByte());  
	            startIndex = randomFile.getFilePointer();  
	        }  
	        //关闭输入输出流并 删除临时文件  
	        randomAccessFile.close();  
	        randomFile.close();  
	        tempFile.delete();  
	          
	       System.out.println("success");
	     

	        
		try {
			File file = new File(filename); 
			reader = new BufferedReader(new FileReader(file));
			
			int l = 1;
			// 一行一行的读取文件里面的内容
			while ((tmpString = reader.readLine()) != null) {
				//String utfString=new String(tmpString.getBytes(),"utf-8");
				String[] arr = tmpString.split("\\s+");//使用正则表达式将字符串分割 “\\s+”表示多个空格  
				System.out.println(tmpString);
				teacherDao.addStudent(arr[0], arr[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				response.sendRedirect(request.getContextPath() + "/teacher/stumanage.jsp");
			}
		}
		
} 
}	


