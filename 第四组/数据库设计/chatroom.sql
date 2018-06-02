/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : chatroom

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-03-12 08:09:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for userlist
-- ----------------------------
DROP TABLE IF EXISTS `userlist`;
CREATE TABLE `userlist` (
  `Uid` int(10) NOT NULL,
  `UserName` varchar(15) NOT NULL,
  `PassWord` varchar(15) NOT NULL,
  `NickName` varchar(50) DEFAULT NULL,
  `Sign` varchar(100) DEFAULT NULL,
  `QQ` int(12) DEFAULT NULL,
  `Socket` varchar(40) DEFAULT NULL,
  `IsOnline` int(1) NOT NULL,
  `Group` varchar(20) DEFAULT NULL,
  `FriendGroup` varchar(20) DEFAULT NULL,
  `QQImg` int(1) DEFAULT NULL,
  PRIMARY KEY (`Uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userlist
-- ----------------------------
INSERT INTO `userlist` VALUES ('100000000', 'abin', 't123', '123', 'hello world', '1506376703', null, '1', null, null, '1');
INSERT INTO `userlist` VALUES ('100000001', 'abintest', '123', '123', '123', '1234567', null, '0', null, null, null);
INSERT INTO `userlist` VALUES ('100000002', 'b', 'c123', '123', 'heheheh', '1234', null, '0', null, null, '1');
INSERT INTO `userlist` VALUES ('100000003', '1234', '1234', '1234', '1234', '1234455667', null, '1', null, null, null);
INSERT INTO `userlist` VALUES ('100000004', 'liyue', 'liyue', 'abcd', '', '123456789', null, '0', null, null, null);
INSERT INTO `userlist` VALUES ('100000005', 'lp', 'lp', '1234', '', '12345678', null, '0', null, null, null);
INSERT INTO `userlist` VALUES ('100000006', '2345', '2345', '2345', '', '12345', null, '1', null, null, null);
INSERT INTO `userlist` VALUES ('100000007', 'jr', 'jr', '1234', '', '1234567890', null, '1', null, null, null);
INSERT INTO `userlist` VALUES ('100000009', 'abinpro', '阿斌', '123', '测试测试', '1234567890', null, '0', null, null, null);
INSERT INTO `userlist` VALUES ('100000011', 'abin2', '123', '123', '123', '123', null, '0', null, null, null);
INSERT INTO `userlist` VALUES ('100000010', 'test', 'adb', '123', '123', '12345678', null, '0', null, null, null);
INSERT INTO `userlist` VALUES ('100000012', 'abin3', '123', '123', '123', '123', null, '0', null, null, null);
INSERT INTO `userlist` VALUES ('100000013', '123456', '123', '123', '123', '123', null, '0', null, null, null);
INSERT INTO `userlist` VALUES ('100000014', '123', '123', '123', '123', '123', null, '0', null, null, null);
INSERT INTO `userlist` VALUES ('100000015', '123', '123', '123', '123', '123', null, '0', null, null, null);
INSERT INTO `userlist` VALUES ('100000016', '123', '123', '123', '123', '123', null, '0', null, null, null);
INSERT INTO `userlist` VALUES ('100000017', '123', '123', '123', '123', '123', null, '0', null, null, null);
INSERT INTO `userlist` VALUES ('100000018', '123', '123', '123', '123', '123', null, '0', null, null, null);
INSERT INTO `userlist` VALUES ('100000019', '123', '123', '123', '123', '123', null, '0', null, null, null);

-- ----------------------------
-- Table structure for userrelation
-- ----------------------------
DROP TABLE IF EXISTS `userrelation`;
CREATE TABLE `userrelation` (
  `Uid` int(10) NOT NULL,
  `FriendUid` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userrelation
-- ----------------------------
INSERT INTO `userrelation` VALUES ('100000003', '100000000');
INSERT INTO `userrelation` VALUES ('100000000', '100000003');
INSERT INTO `userrelation` VALUES ('100000000', '100000001');
INSERT INTO `userrelation` VALUES ('100000000', '100000002');
INSERT INTO `userrelation` VALUES ('100000000', '100000004');
INSERT INTO `userrelation` VALUES ('100000006', '100000000');
