/*
Navicat MySQL Data Transfer

Source Server         : 阿里云服务器
Source Server Version : 50639
Source Host           : 120.79.205.255:3306
Source Database       : mydb

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-05-10 20:34:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for upfile
-- ----------------------------
DROP TABLE IF EXISTS `upfile`;
CREATE TABLE `upfile` (
  `id` varchar(40) NOT NULL,
  `uuidname` varchar(100) NOT NULL,
  `filename` varchar(100) NOT NULL,
  `savepath` varchar(255) NOT NULL,
  `uptime` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `username` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuidname` (`uuidname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `birthday` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
