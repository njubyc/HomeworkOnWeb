<%@page import="com.chen.users.AssTeacher"%>
<%@page import="com.chen.users.Class"%>
<%@page import="org.apache.catalina.User"%>
<%@page import="com.chen.users.Question"%>
<%@page import="com.chen.users.Homework"%>
<%@page import="com.chen.users.Student"%>
<%@page import="com.chen.jdbc.JdbcUtils"%>
<%@page import="com.chen.users.Teacher"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:useBean id="teacherDao" class="com.chen.dao.TeacherDao"/>
<jsp:useBean id="assteacherDao" class="com.chen.dao.AssTeacherDao"/>
<%
AssTeacher assteacher = (AssTeacher)request.getSession().getAttribute("user");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-focus">
<head>

        <meta charset="utf-8">

        <title>南京大学作业提交系统</title>

        <meta name="description" content="南京大学作业提交系统">
        <meta name="author" content="nju">
        <meta name="robots" content="南京大学">
        <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0">
		<!-- 公式编辑器 -->
		
		<script type="text/javascript" src="jmeditor/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="jmeditor/JMEditor.js"></script>
        <!-- Icons -->
        <!-- The following icons can be replaced with your own, they are used by desktop and mobile browsers -->
        <link rel="shortcut icon" href="assets/img/favicons/favicon.png">

        <link rel="icon" type="image/png" href="assets/img/favicons/favicon-16x16.png" sizes="16x16">
        <link rel="icon" type="image/png" href="assets/img/favicons/favicon-32x32.png" sizes="32x32">
        <link rel="icon" type="image/png" href="assets/img/favicons/favicon-96x96.png" sizes="96x96">
        <link rel="icon" type="image/png" href="assets/img/favicons/favicon-160x160.png" sizes="160x160">
        <link rel="icon" type="image/png" href="assets/img/favicons/favicon-192x192.png" sizes="192x192">

        <link rel="apple-touch-icon" sizes="57x57" href="assets/img/favicons/apple-touch-icon-57x57.png">
        <link rel="apple-touch-icon" sizes="60x60" href="assets/img/favicons/apple-touch-icon-60x60.png">
        <link rel="apple-touch-icon" sizes="72x72" href="assets/img/favicons/apple-touch-icon-72x72.png">
        <link rel="apple-touch-icon" sizes="76x76" href="assets/img/favicons/apple-touch-icon-76x76.png">
        <link rel="apple-touch-icon" sizes="114x114" href="assets/img/favicons/apple-touch-icon-114x114.png">
        <link rel="apple-touch-icon" sizes="120x120" href="assets/img/favicons/apple-touch-icon-120x120.png">
        <link rel="apple-touch-icon" sizes="144x144" href="assets/img/favicons/apple-touch-icon-144x144.png">
        <link rel="apple-touch-icon" sizes="152x152" href="assets/img/favicons/apple-touch-icon-152x152.png">
        <link rel="apple-touch-icon" sizes="180x180" href="assets/img/favicons/apple-touch-icon-180x180.png">
        <!-- END Icons -->

        <!-- Stylesheets -->
        <!-- Web fonts -->
         <!--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400italic,600,700%7COpen+Sans:300,400,400italic,600,700">-->

        <!-- Page JS Plugins CSS -->
        <link rel="stylesheet" href="assets/js/plugins/slick/slick.min.css">
        <link rel="stylesheet" href="assets/js/plugins/slick/slick-theme.min.css">

        <link rel="stylesheet" href="assets/js/plugins/datatables/jquery.dataTables.min.css">

        <!-- OneUI CSS framework -->
        <link rel="stylesheet" id="css-main" href="assets/css/oneui.css">

        <!-- You can include a specific file from css/themes/ folder to alter the default color theme of the template. eg: -->
        <!-- <link rel="stylesheet" id="css-theme" href="assets/css/themes/flat.min.css"> -->
        <!-- END Stylesheets -->
    </head>
    <body>
        <!-- Page Container -->
        
        <div id="page-container" class="sidebar-l sidebar-o side-scroll header-navbar-fixed">
            <!-- Side Overlay-->
            
            <!-- END Side Overlay -->

            <!-- Sidebar  侧边栏-->
            <nav id="sidebar">
                <!-- Sidebar Scroll Container -->
                <div id="sidebar-scroll">
                    <!-- Sidebar Content -->
                    <!-- Adding .sidebar-mini-hide to an element will hide it when the sidebar is in mini mode -->
                    <div class="sidebar-content">
                        <!-- Side Header 侧边栏颜色 -->
                        <div class="side-header side-content bg-white-op">
                            <!-- Layout API, functionality initialized in App() -> uiLayoutApi() -->
                  
                            <button class="btn btn-link text-gray pull-right hidden-md hidden-lg" type="button" data-toggle="layout" data-action="sidebar_close">
                                <i class="fa fa-times"></i>
                            </button>
                            
                            
                            <a class="h5 text-white" href="https://www.nju.edu.cn/">
                                <span class="h4 font-w600 sidebar-mini-hide">南京大学</span>
                            </a>
                        </div>
                        <!-- END Side Header 侧边栏颜色 -->

                        <!-- Side Content -->
                        <div class="side-content left-menu-wrap">
                            <ul class="nav-main">
                                <li>
                                    <a class="active" href="index.jsp"><i class="glyphicon glyphicon-home"></i><span class="sidebar-mini-hide">首页</span></a>
                                </li>
                                <li class="nav-main-heading"><span class="sidebar-mini-hide">学生管理</span></li>
                                <li>
                                    <a class="nav-submenu" data-toggle="nav-submenu" href="stumanage.jsp"><i class="glyphicon glyphicon-user"></i><span class="sidebar-mini-hide">学生管理</span></a>
                                    
                                </li>
                            
                                <li class="nav-main-heading"><span class="sidebar-mini-hide">作业管理</span></li>
                                <li>
                                    <a class="nav-submenu" data-toggle="nav-submenu" href="ex_assign.jsp"><i class="glyphicon glyphicon-pencil"></i><span class="sidebar-mini-hide">布置作业</span></a>
                                    
                                </li>
                                <li>
                                    <a class="nav-submenu" data-toggle="nav-submenu" href="ex_manage.jsp"><i class="glyphicon glyphicon-th"></i><span class="sidebar-mini-hide">题目管理</span></a>
                                    
                                </li>
                                <li>
                                    <a class="nav-submenu" data-toggle="nav-submenu" href="ex_check.jsp"><i class="glyphicon glyphicon-ok"></i><span class="sidebar-mini-hide">批改作业</span></a>
                                    
                                </li>
                                
                                
                                
                                <li class="nav-main-heading"><span class="sidebar-mini-hide">信息管理</span></li>
                                <li>
                                    <a class="nav-submenu" data-toggle="nav-submenu" href="alterstudent.jsp"><i class="glyphicon glyphicon-cog"></i><span class="sidebar-mini-hide">修改密码</span></a>
                                    
                                </li>
                               
                            </ul>
                        </div>
                        <!-- END Side Content -->
                        <div class="side-header side-content bg-white-op left-menu_account">
                            <!-- Layout API, functionality initialized in App() -> uiLayoutApi() -->
                            <!-- <button class="btn btn-link text-gray pull-right hidden-md hidden-lg" type="button" data-toggle="layout" data-action="sidebar_close">
                                <i class="fa fa-times"></i>
                            </button> -->
                            <!-- Themes functionality initialized in App() -> 登录人员名称 -->
                            <div class="btn-group pull-right">
                                
                                    <i class="glyphicon glyphicon-user"><a href="../index.jsp">退出</a></i>
                                
                                
                            </div>
                            <a class="h5 text-white" href="index.jsp">
                                <span class="h4 font-w600 sidebar-mini-hide">${user.getName()}
								</span>
                            </a>
                        </div>
                    </div>
                    <!-- Sidebar Content -->
                </div>
                <!-- END Sidebar Scroll Container -->
            </nav>
            <!-- END Sidebar 侧边栏 -->

            <!-- Header -->
            <header id="header-navbar" class="content-mini content-mini-full">
                <!-- Header Navigation Right -->
               
                <!-- END Header Navigation Right -->

                <!-- Header Navigation Left -->
                <ul class="nav-header">
                    <li class="hidden-md hidden-lg pull-left">
                        <!-- Layout API, functionality initialized in App() -> uiLayoutApi() -->
                        <button class="btn btn-default" data-toggle="layout" data-action="sidebar_toggle" type="button">
                            <i class="glyphicon glyphicon-th-list"></i>
                        </button>
                    </li>
                    <li class="hidden-xs hidden-sm pull-left">
                        <!-- Layout API, functionality initialized in App() -> uiLayoutApi() -->
                        <button class="btn btn-default" data-toggle="layout" data-action="sidebar_mini_toggle" type="button">
                            <i class="glyphicon glyphicon-th-list"></i>
                        </button>
                    </li>
                    
                    <li class="js-header-search header-search pull-right">
                        <form class="form-horizontal" action="#" method="post">
                            <div class="form-material form-material-primary input-group remove-margin-t remove-margin-b">
                                <input class="form-control" type="text" id="base-material-text" name="base-material-text" placeholder="Search..">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
                            </div>
                        </form>
                    </li>
                </ul>
                <!-- END Header Navigation Left -->
            </header>
            <!-- END Header -->

            <!-- Main Container -->
            <main id="main-container">
                <!-- Page Header -->
                <div class="content bg-image overflow-hidden" style="background-image: url('assets/img/photos/photo12@2x.jpg');">
                    <div class="push-50-t push-15">
                        <h1 class="h2 text-white animated zoomIn">首页</h1>
                        <h2 class="h5 text-white-op animated zoomIn">Welcome 助教</h2>
                    </div>
                </div>
                <!-- END Page Header -->

               

                <!-- Page Content -->
                <div class="content">
                    <div class="block">                       
                		<div class="block-content">
                   			 <div class="row">
                        		
                       			
                       			<div class="col-xs-2">
									<ul class="nav nav-tabs nav-stacked">
									<li>班级列表</li>
                				 <%                                 		                                 
                                 List<Class> classes =assteacherDao.showClasses(assteacher.getAssTeacherID(),"addHomework");
                                 int j = 1;
                                 for(Class classD:classes)
                                 {
                                	 
                                 %>
									<li><a href="#<%=classD.getClassID() %>" data-toggle="tab"><%=classD.getClassID() %></a></li>
                                 <%
                                 List<Homework> homeworks = teacherDao.showHomeworks(classD.getClassID());
									for(Homework homework:homeworks)
									{
                                    j++;
									}
                                 }
                                 %>
                                 	</ul>
                       			</div>
                       			
                       			<div class="col-xs-3 tab-content">
                       													
								<%                                 		                                 
                                 
                                 int i = 1;
                                 for(Class classD:classes)
                                 {
                                	 
                                 %>
									<div class="tab-pane fade" id="<%=classD.getClassID() %>">
									<table class="table table-bordered table-striped js-datatable-full " >
                               			 <thead>
                                   			 <tr>
                                       			<th >作业标题</th>
                                       			<th >截止日期</th>
												<th >作业状态</th>
												<th >查看</th>
                                     		 </tr>									
                                		</thead>
                                		<%
										List<Homework> homeworks = teacherDao.showHomeworks(classD.getClassID());
										for(Homework homework:homeworks)
										{
										%>
										<tbody >
										
											<tr >
												<td><%=homework.getHomeworkTitle() %></td>
												<td><%=homework.getDeadline() %></td>
												<td><%=homework.getHomeworkState() %></td>
												<td class="nav-tabs "><a href="#<%=homework.getHomeworkID() %>" data-toggle="tab" class="btn btn-default">查看</a></td>
												<!-- td里面各班学生学号 -->
											</tr>
										
										</tbody>
										<%
										}
										%>
										<form >
                           				<div class="form-group form-horizontal">
                           					<label for="homeworkname" class=" control-label"></label>
      										<div >
        										 <input type="text" class="form-control" id="homeworkTile<%=i %>" placeholder="请输入作业标题">
   									   		</div>
   									  		 <label for="deadline" class=" control-label"></label>
      										<div >
        									 	<input type="text" class="form-control" id="deadline<%=i %>" placeholder="请输入截止时间">
   									  		</div>  									   	
                           				</div>
                           				<button type="submit" class="btn btn-default" id="addhomework" onclick="postAddHomework
                           				('homeworkTile<%=i %>','deadline<%=i %>','/homeworkOnWeb/servlet/AddHomework',{homeworkID:'<%=j %>',classID:'<%=classD.getClassID()  %>',homeworkState:'未截止'});">新建作业</button>

                           			</form
                           			>
                                	</table>
                                	</div>
                                 <%
                                    i++;
                                 }
                                 %>
                            
                               
                       			</div>
                       			
                       			<div class="col-xs-2">
									
                           			<div class="tab-content">
										 	<%       
										 	int homeworkID =1 ;
                                  for(Class classD:classes)
                                 {
                                	  
                                	 List<Homework> homeworks = teacherDao.showHomeworks(classD.getClassID());
										for(Homework homework:homeworks)
										{
                                 %>
									<div class="tab-pane fade" id="<%=homework.getHomeworkID() %>">
									<table class="table table-bordered table-striped js-datatable-full " >
                               			 <thead>
                                   			 <tr>
                                       			<th >题目ID</th>
                                     		 </tr>									
                                		</thead>
                                		<%
                                		
										List<Question> questions = teacherDao.showQuestionOfHomework(homework.getHomeworkID());
										int tsksize = questions.size()+1;
										String tskContent = "第"+tsksize+"题";
                                		for(Question question:questions)
										{
										%>
										<tbody >
										
											<tr >
												<td><%=question.getTskID() %></td>
												
												<!-- td里面各班学生学号 -->
											</tr>
										
										</tbody>
										
										<%
										}
                                		%>
                                		<form >
                           				<div class="form-group form-horizontal">
                           					<label for="tskid" class=" control-label"></label>
      										<div >
        										 <input type="text" class="form-control" id="h<%=homeworkID %>" placeholder="请输入题目ID">
   									   		</div>								   	
                           				</div>
                           				<button type="submit" class="btn btn-default" id="addhomework" onclick="postAddDeleteQuestionToHomework
                           				('h<%=homeworkID %>','/homeworkOnWeb/servlet/AddDeleteQuestionToHomework',{homeworkID:'<%=homework.getHomeworkID() %>',action:'add',tskContent:'<%=tskContent %>'});">添加题目</button>
                           				<button type="submit" class="btn btn-default" id="addhomework" onclick="postAddDeleteQuestionToHomework
                           				('h<%=homeworkID %>','/homeworkOnWeb/servlet/AddDeleteQuestionToHomework',{homeworkID:'<%=homework.getHomeworkID() %>',action:'delete',tskContent:'<%=tskContent %>'});">删除题目</button>

                           				</form>
                                    	</table>
                                    	</div>
                                     <%
										homeworkID++;

										}
                                 }
                                 %>
								</div>
                       			</div>
                       			 
                       			 
                       			 
                       			 <div class="col-xs-3">
                       			 	<table class="table table-bordered table-striped js-datatable-full">
                               			 <thead>
                                   			 <tr>
                                       			<th >题目ID</th>
                                       			<th >所属课程</th>
												<th >所属章节</th>
												<th >查看</th>
                                     		 </tr>

                                		</thead>
                                		
                     	 				<tbody>
                     	 				                                     		
                                <% 
                                
                                  
                                 List<Question> questions =teacherDao.showQuestions();
                                 int b = 1;
                                 for(Question question:questions)
                                 {
                                 %>
								  <tr>
                                        <td class="font-w600"><%=question.getTskID() %></td>
                                        <td class="font-w600"><%=question.getCourse() %></td>
                                        <td class="font-w600"><%=question.getChapter() %></td>
										<td class="nav-tabs"><a href="#question<%=question.getTskID() %>" class="btn btn-default" data-toggle="tab">查看</a></td>
                                        
                                    </tr> 
                                    <%
                                    b++;
                                 }
                                 %>
                     	 				</tbody>
                     				</table>
                     			

                       			 
                       			 </div>
                       			 
                       			 <div class="col-xs-2">								
										
                           				
                           				<div  class="tab-content" >                   						
                     			 		<%                                  
                                 		int c = 1;
                                 		for(Question question:questions)
                                 		{
                                 		%>
								 		 <div class="tab-pane fade" id="question<%=question.getTskID() %>">
								  			<p ><%=question.getTskDetail() %></p>
                                  		</div>  
                                   		 <%
                                   		 c++;
                                 		}
                                		 %>
                     						
                     					</div>
                       			 </div>
                    		</div>
                   		</div>
                    </div>
                </div>



            </main>
            <!-- END Main Container -->

            <!-- Footer -->
            <footer id="page-footer" class="content-mini content-mini-full font-s12 bg-gray-lighter clearfix">
                <div class="pull-right">
                    Crafted by <a class="font-w600" href="#" target="_blank">Chen.Liu.Bao</a>
                </div>
                <div class="pull-left">
                    <a class="font-w600" href="https://www.nju.edu.cn" target="_blank">南京大学</a> &copy; <span >2017</span>
                </div>
            </footer>
            <!-- END Footer -->
        </div>
        <!-- END Page Container -->


        
 <script type="text/javascript">  

function postAddHomework(ID1,ID2,URL, PARAMS) {        
    var temp = document.createElement("form");        
    temp.action = URL;        
    temp.method = "post";        
    temp.style.display = "none";     
    for (var x in PARAMS) {        
        var opt = document.createElement("textarea");        
        opt.name = x;        
        opt.value = PARAMS[x];        
        // alert(opt.name)        
        temp.appendChild(opt);        
    }

    var opt = document.createElement("textarea");
    opt.name = "homeworkTitle";        
    opt.value = document.getElementById(ID1).value;     
    // alert(opt.name)        
    temp.appendChild(opt);
    document.body.appendChild(temp);  
    var opt = document.createElement("textarea");
    opt.name = "deadline";        
    opt.value = document.getElementById(ID2).value;     
    // alert(opt.name)        
    temp.appendChild(opt);
    document.body.appendChild(temp); 
    temp.submit();        
    return temp;        
}
function postAddDeleteQuestionToHomework(ID,URL, PARAMS) {        
    var temp = document.createElement("form");        
    temp.action = URL;        
    temp.method = "post";        
    temp.style.display = "none";        
    for (var x in PARAMS) {        
        var opt = document.createElement("textarea");        
        opt.name = x;        
        opt.value = PARAMS[x];        
        // alert(opt.name)        
        temp.appendChild(opt);        
    }
    var opt = document.createElement("textarea");
    opt.name = "tskID";        
    opt.value = document.getElementById(ID).value;     
    // alert(opt.name)        
    temp.appendChild(opt);
    document.body.appendChild(temp);        
    temp.submit();        
    return temp;        
}




 </script> 



        <!-- OneUI Core JS: jQuery, Bootstrap, slimScroll, scrollLock, Appear, CountTo, Placeholder, Cookie and App.js -->
        <script src="assets/js/core/jquery.min.js"></script>
        <script src="assets/js/core/bootstrap.min.js"></script>
        <script src="assets/js/core/jquery.slimscroll.min.js"></script>
        <script src="assets/js/core/jquery.scrollLock.min.js"></script>
        <script src="assets/js/core/jquery.appear.min.js"></script>
        <script src="assets/js/core/jquery.countTo.min.js"></script>
        <script src="assets/js/core/jquery.placeholder.min.js"></script>
        <script src="assets/js/core/js.cookie.min.js"></script>
        <script src="assets/js/app.js"></script>

        <!-- Page Plugins -->
        <script src="assets/js/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="assets/js/pages/base_tables_datatables.js"></script>
        <!-- <script src="assets/js/plugins/slick/slick.min.js"></script>
        <script src="assets/js/plugins/chartjs/Chart.min.js"></script> -->

        <!-- Page JS Code -->
        <script src="assets/js/pages/base_pages_dashboard.js"></script>

        
        <script>
            $(function () {
                // Init page helpers (Slick Slider plugin)
                App.initHelpers('slick');
            });
        </script>
    </body>

</html>