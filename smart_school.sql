
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_admin`
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone` varchar(12) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `portrait_path` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES ('101', 'admin', '男', '21232f297a57a5a743894a0e4a801fc3', '111111@qq.com', '13260161111', '昌平', 'upload/default.jpg');

-- ----------------------------
-- Table structure for `tb_clazz`
-- ----------------------------
DROP TABLE IF EXISTS `tb_clazz`;
CREATE TABLE `tb_clazz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT NULL,
  `number` int(3) DEFAULT NULL,
  `introducation` varchar(200) DEFAULT NULL,
  `headmaster` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone` varchar(12) DEFAULT NULL,
  `grade_name` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_clazz
-- ----------------------------
INSERT INTO `tb_clazz` VALUES ('1', '一年一班', '30', '大圣的一年一班好', '大圣', 'dasheng@163.com', '13260166090', '一年级');
INSERT INTO `tb_clazz` VALUES ('4', '二年二班', '30', '小强的二年二班好', '小强', 'xiaoqiang@163.com', '13260166090', '二年级');
INSERT INTO `tb_clazz` VALUES ('5', '三年一班', '30', '小花的三年一班好', '小花', 'xiaohua@163.com', '13260166090', '三年级');
INSERT INTO `tb_clazz` VALUES ('7', '四年一班', '30', '小赵的三年二班好', '小飞', 'xiaofei@163.com', '13260166090', '四年级');
INSERT INTO `tb_clazz` VALUES ('10', '五年一班', '39', '这个班级的学生很666', '小洪', '126@qq.com', '13666666666', '五年级');

-- ----------------------------
-- Table structure for `tb_grade`
-- ----------------------------
DROP TABLE IF EXISTS `tb_grade`;
CREATE TABLE `tb_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL DEFAULT '',
  `manager` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone` varchar(12) DEFAULT NULL,
  `introducation` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_grade
-- ----------------------------
INSERT INTO `tb_grade` VALUES ('1', '一年级', '大圣', 'dasheng@163.com', '13260166090', '大学一年级');
INSERT INTO `tb_grade` VALUES ('2', '二年级', '小魏', 'xiaowei@163.com', '13260166090', '大学二年级');
INSERT INTO `tb_grade` VALUES ('5', '六年级', '小明', 'xiaoming@666.com', '13666666666', '这个年级的主任是小明');
INSERT INTO `tb_grade` VALUES ('13', '五年级', '小洪', '126@qq.com', '13666666666', '66666666666666');

-- ----------------------------
-- Table structure for `tb_student`
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sno` varchar(20) DEFAULT NULL,
  `name` varchar(15) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone` varchar(12) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `introducation` varchar(200) DEFAULT NULL,
  `portrait_path` varchar(200) DEFAULT NULL,
  `clazz_name` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES ('1', '1001', '张小明', '男', '202cb962ac59075b964b07152d234b70', 'yinyufei@163.com', '13260166090', '北京天通苑', '这个学生学习好', 'upload/default.jpg', '一年一班');
INSERT INTO `tb_student` VALUES ('2', '1002', '郭建超', '男', 'e10adc3949ba59abbe56e057f20f883e', 'guojianchao@163.com', '13260166090', '北京昌平', '这个学生会功夫', 'upload/default.jpg', '一年一班');
INSERT INTO `tb_student` VALUES ('3', '1003', '史汶鑫', '男', 'e10adc3949ba59abbe56e057f20f883e', 'shiwenxin@163.com', '13260166090', '北京昌平', '这个学生酒量好', 'upload/default.jpg', '二年一班');
INSERT INTO `tb_student` VALUES ('4', '1004', '高建军', '男', 'e10adc3949ba59abbe56e057f20f883e', 'gaojianjun@163.com', '13260166090', '北京昌平', '这个学生会做饭', 'upload/default.jpg', '二年一班');
INSERT INTO `tb_student` VALUES ('5', '1005', '邹伟斌', '男', 'e10adc3949ba59abbe56e057f20f883e', 'zouweibin@163.com', '13260166090', '北京昌平', '这个学生能吃辣', 'upload/default.jpg', '三年一班');
INSERT INTO `tb_student` VALUES ('6', '1006', '刘路', '男', 'e10adc3949ba59abbe56e057f20f883e', 'liulu@163.com', '13260166090', '北京昌平', '这个学生是学霸', 'upload/default.jpg', '三年二班');

-- ----------------------------
-- Table structure for `tb_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tno` varchar(20) DEFAULT NULL,
  `name` varchar(15) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone` varchar(12) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `portrait_path` varchar(200) DEFAULT NULL,
  `clazz_name` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_teacher
-- ----------------------------
INSERT INTO `tb_teacher` VALUES ('1', '101', '大圣', '女', '123456', 'dasheng@163.com', '13260166090', '北京昌平', 'upload/default.jpg', '一年一班');
INSERT INTO `tb_teacher` VALUES ('2', '102', '小张', '男', 'e10adc3949ba59abbe56e057f20f883e', 'xiaozhang@163.com', '13260166090', '北京海淀', 'upload/default.jpg', '一年二班');
INSERT INTO `tb_teacher` VALUES ('3', '103', '小韩', '男', 'e10adc3949ba59abbe56e057f20f883e', 'xiaohan@163.com', '13260166090', '北京朝阳', 'upload/default.jpg', '二年一班');
INSERT INTO `tb_teacher` VALUES ('4', '104', '小强', '男', 'e10adc3949ba59abbe56e057f20f883e', 'xiaoqiang@163.com', '13260166090', '北京通州', 'upload/default.jpg', '二年二班');
INSERT INTO `tb_teacher` VALUES ('9', '10001', '小洪', '男', '0e33ee0e1a1fa0fc050830bfdd5cb1fb', '123@qq.com', '13666666666', '深圳', 'upload/d16e820a575640be8a9dc7e7a64211a2.jpeg', '一年一班');
