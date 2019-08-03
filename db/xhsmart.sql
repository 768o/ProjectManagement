/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50522
Source Host           : localhost:3306
Source Database       : xhsmart

Target Server Type    : MYSQL
Target Server Version : 50522
File Encoding         : 65001

Date: 2019-05-05 20:53:18
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `allocation`
-- ----------------------------
DROP TABLE IF EXISTS `allocation`;
CREATE TABLE `allocation` (
  `project_id` int(11) NOT NULL,
  `user_jurisdictionGroup` int(11) DEFAULT '0',
  `user_joinTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_isQuit` tinyint(1) NOT NULL DEFAULT '0',
  `user_task` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `endTime` timestamp NULL DEFAULT NULL,
  `progress` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`project_id`,`user_joinTime`,`user_id`,`name`),
  KEY `fk_project_has_user_project1_idx` (`project_id`),
  KEY `fk_project_has_user_jurisdictionGroup1_idx` (`user_jurisdictionGroup`),
  KEY `fk_user_task1_idx` (`user_task`),
  KEY `fk_project_user_user1_idx` (`user_id`),
  CONSTRAINT `fk_project_has_user_project1` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_project_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_task1` FOREIGN KEY (`user_task`) REFERENCES `task` (`task_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of allocation
-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_loginName` varchar(200) NOT NULL,
  `user_password` varchar(200) NOT NULL,
  `user_name` varchar(200) NOT NULL,
  `post_id` int(11) NOT NULL,
  `department_id` int(11) NOT NULL,
  `user_isdelete` tinyint(1) NOT NULL DEFAULT '0',
  `isadmin` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`user_id`),
  KEY `fk_user_job1_idx` (`post_id`),
  KEY `fk_user_department1_idx` (`department_id`),
  CONSTRAINT `fk_user_department1` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_job1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for `demand`
-- ----------------------------
DROP TABLE IF EXISTS `demand`;
CREATE TABLE `demand` (
  `demand_id` int(11) NOT NULL AUTO_INCREMENT,
  `demand_name` varchar(200) NOT NULL,
  `demand_type` varchar(16) NOT NULL,
  `demand_depict` varchar(1200) NOT NULL,
  `demand_state` varchar(16) NOT NULL DEFAULT '等待立项',
  `demand_submitTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `demand_path` varchar(1000) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `submit_user` int(11) NOT NULL,
  `demand_fatherid` int(11) NOT NULL,
  PRIMARY KEY (`demand_id`),
  KEY `fk_demand_project1_idx` (`project_id`),
  KEY `fk_demand_user1_idx` (`submit_user`),
  KEY `fk_demand_demand` (`demand_fatherid`),
  CONSTRAINT `fk_demand_project1` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_demand_user1` FOREIGN KEY (`submit_user`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of demand
-- ----------------------------
INSERT INTO `demand` VALUES ('1', '默认', '初始', '11', '已立项', '2019-05-04 19:37:54', 'C:\\Users\\10512\\Workspaces\\MyEclipse 10\\111\\.metadata\\.me_tcat\\webapps\\xhSmart\\WEB-INF\\uploadfile\\doc/03993082-4232-4691-b338-776d7ae858f5windows-version.txt', '-1', '13', '0');

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `department_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(200) NOT NULL,
  `department_depict` varchar(1200) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('15', '研发部', '研发部门主要负责研发');
INSERT INTO `department` VALUES ('32', '生产部', '生产部主要负责生产');
INSERT INTO `department` VALUES ('33', 'TC部', 'TC部主要负责技术支持');

-- ----------------------------
-- Table structure for `feedback`
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `feedback_id` int(11) NOT NULL AUTO_INCREMENT,
  `feedback_name` varchar(200) NOT NULL,
  `feedback_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `feedback_depict` varchar(3000) NOT NULL,
  `user_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  PRIMARY KEY (`feedback_id`),
  KEY `fk1` (`user_id`),
  KEY `fk2` (`project_id`),
  CONSTRAINT `fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk2` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of feedback
-- ----------------------------

-- ----------------------------
-- Table structure for `jurisdiction`
-- ----------------------------
DROP TABLE IF EXISTS `jurisdiction`;
CREATE TABLE `jurisdiction` (
  `jurisdiction_id` int(11) NOT NULL AUTO_INCREMENT,
  `jurisdiction_name` varchar(200) NOT NULL,
  `jurisdiction_depict` varchar(1200) DEFAULT NULL,
  PRIMARY KEY (`jurisdiction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of jurisdiction
-- ----------------------------
INSERT INTO `jurisdiction` VALUES ('28', 'admin1', '&#39033;&#30446;&#31649;&#29702;&#21592;');
INSERT INTO `jurisdiction` VALUES ('29', 'admin2', '&#32452;&#32455;&#31649;&#29702;&#21592;');
INSERT INTO `jurisdiction` VALUES ('30', 'demand/updateDemand', '&#38656;&#27714;&#31649;&#29702;');
INSERT INTO `jurisdiction` VALUES ('31', 'product/AddProduct', '&#20135;&#21697;&#31649;&#29702;');

-- ----------------------------
-- Table structure for `jurisdictiongroup`
-- ----------------------------
DROP TABLE IF EXISTS `jurisdictiongroup`;
CREATE TABLE `jurisdictiongroup` (
  `jurisdictionGroup_id` int(11) NOT NULL AUTO_INCREMENT,
  `jurisdictionGroup_name` varchar(200) NOT NULL,
  `jurisdictionGroup_depict` varchar(1200) NOT NULL,
  `jurisdiction` varchar(1000) NOT NULL,
  PRIMARY KEY (`jurisdictionGroup_id`),
  KEY `fk_jurisdictionGroup_jurisdiction1_idx` (`jurisdiction`(383))
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of jurisdictiongroup
-- ----------------------------
INSERT INTO `jurisdictiongroup` VALUES ('10', '&#38656;&#27714;&#36127;&#36131;&#20154;', '&#36127;&#36131;&#38656;&#27714;', '30,');
INSERT INTO `jurisdictiongroup` VALUES ('11', '&#20135;&#21697;&#36127;&#36131;&#20154;', '&#36127;&#36131;&#20135;&#21697;', '31,');
INSERT INTO `jurisdictiongroup` VALUES ('12', '&#31649;&#29702;&#21592;', '&#31649;&#29702;', '28,29,');

-- ----------------------------
-- Table structure for `post`
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_name` varchar(200) NOT NULL,
  `post_depict` varchar(1200) DEFAULT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('2', '&#32769;&#26495;', '&#32769;&#26495;&#26159;&#20844;&#21496;&#26368;&#22823;&#30340;');
INSERT INTO `post` VALUES ('4', '组长', '组长也是不小的');
INSERT INTO `post` VALUES ('5', '员工', '员工是最小的');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(200) NOT NULL,
  `product_depict` varchar(1200) DEFAULT NULL,
  `product_submitTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `product_path` varchar(1000) NOT NULL,
  `product_state` varchar(16) NOT NULL,
  `project_id` int(11) NOT NULL,
  `demand_id` int(11) NOT NULL,
  `submit_user` int(11) NOT NULL,
  PRIMARY KEY (`product_id`),
  KEY `fk_product_project1_idx` (`project_id`),
  KEY `fk_product_demand1_idx` (`demand_id`),
  KEY `fk_product_user1_idx` (`submit_user`),
  CONSTRAINT `fk_product_demand1` FOREIGN KEY (`demand_id`) REFERENCES `demand` (`demand_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_project1` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_user1` FOREIGN KEY (`submit_user`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of product
-- ----------------------------

-- ----------------------------
-- Table structure for `progress`
-- ----------------------------
DROP TABLE IF EXISTS `progress`;
CREATE TABLE `progress` (
  `progress_id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `progress_type` varchar(16) NOT NULL,
  `progress_submitTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `progress_depict` varchar(200) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`progress_id`),
  KEY `fk_Progress_project_idx` (`project_id`),
  KEY `fk_progress_user1_idx` (`user_id`),
  CONSTRAINT `fk_Progress_project` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_progress_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of progress
-- ----------------------------

-- ----------------------------
-- Table structure for `progress1`
-- ----------------------------
DROP TABLE IF EXISTS `progress1`;
CREATE TABLE `progress1` (
  `name` varchar(10) NOT NULL,
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `progress` int(11) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of progress1
-- ----------------------------

-- ----------------------------
-- Table structure for `project`
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `project_id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(200) NOT NULL,
  `project_depict` varchar(1200) NOT NULL,
  `project_createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `project_state` varchar(16) NOT NULL,
  `project_createReason` varchar(1200) NOT NULL,
  `project_endTime` timestamp NULL DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`project_id`),
  KEY `fk` (`user_id`),
  CONSTRAINT `fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('-1', '', '', '2018-05-14 00:17:08', '已结束', '', '2017-08-18 00:00:00', '13');

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(200) NOT NULL,
  `task_depict` varchar(1200) NOT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '产品', '&#36127;&#36131;&#38656;&#27714;&#30340;&#28155;&#21152;&#19982;&#21464;&#26356;');

-- ----------------------------

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('13', 'linwenxian', '123456', '&#26519;&#25991;&#29486;', '2', '15', '0', '1');
INSERT INTO `user` VALUES ('14', 'zhouzhiruo', '123456', '&#21608;&#33463;&#33509;', '5', '32', '0', '0');

-- ----------------------------
-- Table structure for `waitread`
-- ----------------------------
DROP TABLE IF EXISTS `waitread`;
CREATE TABLE `waitread` (
  `progress_progress_id` int(11) NOT NULL,
  `user_user_id` int(11) NOT NULL,
  `isRead` varchar(4) NOT NULL,
  PRIMARY KEY (`progress_progress_id`,`user_user_id`),
  KEY `fk_waitingToRead_progress1_idx` (`progress_progress_id`),
  KEY `fk_waitingToRead_user1_idx` (`user_user_id`),
  CONSTRAINT `fk_waitingToRead_progress1` FOREIGN KEY (`progress_progress_id`) REFERENCES `progress` (`progress_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_waitingToRead_user1` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of waitread
-- ----------------------------
