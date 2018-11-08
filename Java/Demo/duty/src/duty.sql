/*
Navicat MySQL Data Transfer

Source Server         : 192.168.3.10
Source Server Version : 80012
Source Host           : 192.168.3.10:3306
Source Database       : duty

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2018-11-08 22:54:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `Id` int(11) NOT NULL,
  `UserName` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `RealName` varchar(80) DEFAULT NULL,
  `Sex` int(11) DEFAULT NULL,
  `Age` int(11) DEFAULT NULL,
  `Birthday` datetime DEFAULT NULL,
  `CreateTime` datetime NOT NULL,
  `UpdateTime` datetime NOT NULL,
  `Remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', 'admin', '123456', '李廷礼', '1', '27', '1991-01-11 00:00:00', '2018-10-28 22:08:50', '2018-10-28 22:08:53', null);
INSERT INTO `userinfo` VALUES ('2', 'zhangsan', '123456', '张三', '1', '26', '1992-01-12 00:00:00', '2018-11-01 00:48:35', '2018-11-01 00:48:37', null);

-- ----------------------------
-- Table structure for workcalendar
-- ----------------------------
DROP TABLE IF EXISTS `workcalendar`;
CREATE TABLE `workcalendar` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `BeginTime` datetime DEFAULT NULL,
  `EndTime` datetime DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL,
  `Remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workcalendar
-- ----------------------------
INSERT INTO `workcalendar` VALUES ('1', '1', '2018-11-01 09:00:52', '2018-11-01 18:00:03', '2018-11-01 02:49:10', null);
INSERT INTO `workcalendar` VALUES ('2', '2', '2018-10-31 09:00:52', '2018-10-31 18:00:03', '2018-10-31 19:48:36', null);
