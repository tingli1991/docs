/*
Navicat MySQL Data Transfer

Source Server         : 192.168.3.10
Source Server Version : 80012
Source Host           : 192.168.3.10:3306
Source Database       : keepwatch

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2018-11-08 22:54:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `RealName` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Sex` int(11) DEFAULT NULL,
  `Age` int(11) DEFAULT NULL,
  `AccessToken` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Birthday` datetime DEFAULT NULL,
  `CreateTime` datetime NOT NULL,
  `UpdateTime` datetime NOT NULL,
  `Remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', 'admin', '123456', '李廷礼', '1', '27', 'a6a35209f9a1279747190310d3dd1480', '1991-01-11 09:19:27', '2018-11-04 09:19:50', '2018-11-06 13:46:40', '');
INSERT INTO `userinfo` VALUES ('2', 'zhangsan', '123456', '张三', '1', '28', null, '1990-01-26 10:06:50', '2018-11-05 10:07:16', '2018-11-05 10:07:20', null);

-- ----------------------------
-- Table structure for workcalendar
-- ----------------------------
DROP TABLE IF EXISTS `workcalendar`;
CREATE TABLE `workcalendar` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `BeginTime` datetime DEFAULT NULL,
  `EndTime` datetime DEFAULT NULL,
  `UpdateTime` datetime NOT NULL,
  `CreateTime` datetime NOT NULL,
  `Remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workcalendar
-- ----------------------------
INSERT INTO `workcalendar` VALUES ('1', '1', '2018-11-01 09:00:52', '2018-11-01 18:00:03', '2018-11-01 02:49:10', '2018-11-01 02:49:10', '');
INSERT INTO `workcalendar` VALUES ('2', '2', '2018-10-31 09:00:52', '2018-10-31 18:00:03', '2018-11-01 02:49:10', '2018-10-31 19:48:36', '');
INSERT INTO `workcalendar` VALUES ('3', '1', '2018-11-08 09:37:37', '2018-11-08 09:37:37', '2018-11-08 09:55:27', '2018-11-08 09:55:27', null);
