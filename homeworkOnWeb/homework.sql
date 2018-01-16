/*
MySQL Data Transfer
Source Host: localhost
Source Database: homework
Target Host: localhost
Target Database: homework
Date: 2018/1/15 20:08:18
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for assteacher
-- ----------------------------
DROP TABLE IF EXISTS `assteacher`;
CREATE TABLE `assteacher` (
  `assTeacherID` varchar(50) NOT NULL DEFAULT '1',
  `assTeacherPwd` varchar(50) DEFAULT '1',
  `assTeacherName` varchar(50) DEFAULT '1',
  PRIMARY KEY (`assTeacherID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for class_assteacher
-- ----------------------------
DROP TABLE IF EXISTS `class_assteacher`;
CREATE TABLE `class_assteacher` (
  `classID` varchar(50) DEFAULT NULL,
  `teacherID` varchar(50) DEFAULT NULL,
  `assTeacherID` varchar(50) DEFAULT NULL,
  `assTeacherName` varchar(50) DEFAULT NULL,
  `stuManState` int(5) NOT NULL DEFAULT '0',
  `addQuestion` int(5) NOT NULL DEFAULT '0',
  `addHomework` int(5) NOT NULL DEFAULT '0',
  `correctHomework` int(5) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for class_student
-- ----------------------------
DROP TABLE IF EXISTS `class_student`;
CREATE TABLE `class_student` (
  `classID` varchar(50) DEFAULT NULL,
  `stuID` varchar(50) DEFAULT NULL,
  `stuName` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for class_teacher
-- ----------------------------
DROP TABLE IF EXISTS `class_teacher`;
CREATE TABLE `class_teacher` (
  `classID` varchar(50) DEFAULT NULL,
  `course` varchar(50) DEFAULT NULL,
  `teacherID` varchar(50) DEFAULT NULL,
  `teacherName` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework` (
  `homeworkID` varchar(50) NOT NULL DEFAULT '1',
  `classID` varchar(50) DEFAULT NULL,
  `homeworkTitle` varchar(21000) DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  `homeworkState` varchar(50) NOT NULL DEFAULT '未截止',
  PRIMARY KEY (`homeworkID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for questions
-- ----------------------------
DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions` (
  `tskID` varchar(50) DEFAULT NULL,
  `course` varchar(50) DEFAULT NULL,
  `chapter` varchar(50) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `tskDetail` varchar(21000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `stuID` varchar(20) NOT NULL DEFAULT '0000',
  `password` varchar(20) NOT NULL DEFAULT '123456',
  `name` varchar(10) NOT NULL DEFAULT '无名',
  PRIMARY KEY (`stuID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for stugrade
-- ----------------------------
DROP TABLE IF EXISTS `stugrade`;
CREATE TABLE `stugrade` (
  `classID` varchar(50) DEFAULT NULL,
  `stuID` varchar(50) DEFAULT NULL,
  `homeworkTitle` varchar(50) DEFAULT NULL,
  `grade` varchar(50) DEFAULT NULL,
  `subState` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for task_answerstu
-- ----------------------------
DROP TABLE IF EXISTS `task_answerstu`;
CREATE TABLE `task_answerstu` (
  `tskID` varchar(50) NOT NULL DEFAULT '1',
  `tskDetail` varchar(5000) DEFAULT NULL,
  `homeworkID` varchar(50) NOT NULL DEFAULT '1',
  `stuID` varchar(50) NOT NULL DEFAULT '123456',
  `tskStuAnswer` varchar(15000) DEFAULT NULL,
  `tskState` varchar(50) NOT NULL DEFAULT '已保存'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for task_detail
-- ----------------------------
DROP TABLE IF EXISTS `task_detail`;
CREATE TABLE `task_detail` (
  `homeworkID` varchar(50) DEFAULT NULL,
  `tskID` varchar(50) DEFAULT NULL,
  `tskContent` varchar(210) DEFAULT NULL,
  `tskDetail` varchar(21000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacherID` varchar(20) NOT NULL DEFAULT '0000',
  `name` varchar(10) NOT NULL DEFAULT '无名',
  `password` varchar(20) NOT NULL DEFAULT '123456',
  PRIMARY KEY (`teacherID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `assteacher` VALUES ('TA1', '1', '李博');
INSERT INTO `assteacher` VALUES ('TA2', '2', '徐博');
INSERT INTO `assteacher` VALUES ('TA3', '1', '吴博');
INSERT INTO `class_assteacher` VALUES ('软件一班', 'T1', 'TA1', '李博', '1', '0', '0', '0');
INSERT INTO `class_assteacher` VALUES ('软件二班', 'T1', 'TA2', '徐博', '0', '1', '0', '0');
INSERT INTO `class_student` VALUES ('软件一班', 'S1', '陈旭');
INSERT INTO `class_student` VALUES ('软件一班', 'S2', ' 包玉成 ');
INSERT INTO `class_student` VALUES ('软件二班', 'S3', '刘炫邑');
INSERT INTO `class_teacher` VALUES ('软件一班', '软件工程', 'T1', '朱老师');
INSERT INTO `class_teacher` VALUES ('软件二班', '软件工程', 'T1', '朱老师');
INSERT INTO `homework` VALUES ('1', '软件一班', '第一次作业', '2018-01-30 00:00:00', '未截止');
INSERT INTO `homework` VALUES ('2', '软件一班', '第二次作业', '2018-01-30 00:00:00', '未截止');
INSERT INTO `questions` VALUES ('1', '数学', '第一章', '朱老师', '1+1=？');
INSERT INTO `questions` VALUES ('2', '数学', '第二章', '朱老师', '2*2=？');
INSERT INTO `questions` VALUES ('3', '数据结构', '第一章', '朱老师', '冒泡排序');
INSERT INTO `student` VALUES ('S1', '1', '陈旭');
INSERT INTO `student` VALUES ('S2', '2', '包玉成');
INSERT INTO `student` VALUES ('S3', '3', '刘炫邑');
INSERT INTO `stugrade` VALUES ('软件一班', 'S1', '第一次作业', '无', '0');
INSERT INTO `stugrade` VALUES ('软件一班', 'S2', '第一次作业', '无', '0');
INSERT INTO `stugrade` VALUES ('软件一班', 'S1', '第二次作业', '无', '0');
INSERT INTO `stugrade` VALUES ('软件一班', 'S2', '第二次作业', '无', '0');
INSERT INTO `task_answerstu` VALUES ('1', '1+1=？', '1', 'S1', '<p>2<span class=\"mathquill-rendered-math\" style=\"font-size: 20px;\"><span class=\"textarea\"><textarea contenteditable=\"false\" data-cke-editable=\"1\"></textarea></span><span class=\"fraction non-leaf\" mathquill-command-id=\"4\"><span class=\"numerator\" mathquill-block-id=\"5\"><span mathquill-command-id=\"8\">1</span></span><span class=\"denominator\" mathquill-block-id=\"6\"><span mathquill-command-id=\"9\">2</span></span><span style=\"width: 0px; display: inline-block;\">&nbsp;</span></span></span><span>&nbsp;</span><br></p>', '已保存');
INSERT INTO `task_answerstu` VALUES ('1', '1+1=？', '1', 'S2', null, '已保存');
INSERT INTO `task_answerstu` VALUES ('2', '2*2=？', '1', 'S1', '<p>4<br type=\"_moz\"></p>', '已提交');
INSERT INTO `task_answerstu` VALUES ('2', '2*2=？', '1', 'S2', null, '已保存');
INSERT INTO `task_answerstu` VALUES ('3', '冒泡排序', '2', 'S1', null, '已保存');
INSERT INTO `task_answerstu` VALUES ('3', '冒泡排序', '2', 'S2', null, '已保存');
INSERT INTO `task_detail` VALUES ('1', '1', '第1题', '1+1=？');
INSERT INTO `task_detail` VALUES ('1', '2', '第2题', '2*2=？');
INSERT INTO `task_detail` VALUES ('2', '3', '第1题', '冒泡排序');
INSERT INTO `teacher` VALUES ('T1', '朱老师', '1');
INSERT INTO `teacher` VALUES ('T2', '王老师', '2');
