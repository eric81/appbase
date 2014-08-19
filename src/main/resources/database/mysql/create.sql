/*
Navicat MySQL Data Transfer

Source Server         : mysql-local
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : webapp

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2013-12-09 15:36:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(32) default NULL COMMENT '用户名',
  `password` varchar(32) default NULL COMMENT '密码',
  `password_encrypt` varchar(32) default NULL COMMENT '加密密码',
  `salt` varchar(32) default NULL COMMENT '认证时使用',
  `roles` varchar(32) default NULL COMMENT '角色',
  `permissions` varchar(32) default NULL COMMENT '权限',
  `register_date` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '注册日期',
  `photo` longblob,
  PRIMARY KEY  (`id`),
  KEY `Index_id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', 'admin', '', 'admin', 'read,create,update,delete', '2013-12-09 10:42:40', null);
INSERT INTO `user` VALUES ('2', 'user', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('3', 'user1', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('4', 'user2', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('5', 'user3', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('6', 'user4', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('7', 'user5', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('8', 'user6', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('9', 'user7', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('10', 'user8', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('11', 'user9', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('12', 'user10', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('13', 'user11', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('14', 'user12', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('15', 'user13', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('16', 'user14', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('17', 'user15', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('18', 'user16', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('19', 'user17', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('20', 'user18', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('21', 'user19', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('22', 'user20', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('23', 'user21', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('24', 'user22', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
INSERT INTO `user` VALUES ('25', 'user23', 'user', 'user', 'user', 'user', 'read', '2013-02-27 11:02:44', null);
