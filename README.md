# Blog
我的博客系统
该系统现阶段后端代码基本完成，后续会进行前端开发。准备先用jquery（熟练度高些），后面再考虑用vue。
安全性考虑：
代码中控制层，能不用get就不用，毕竟安全系数很低，可能导致sql注入问题；
部署应用时可申请Let't Encrypt免费SSL证书部署，实现https。
并发性考虑：
数据库方面，通过分布式实现读写分离功能，另做好主备；
web服务器方面，用Nginx实现负载均衡。

功能描述：
网站信息管理模块，实现网站运行时间的记录，网站标题、介绍等内容与前端交互显示。
标签管理模块，标签同用户模块里的用户兴趣选择，用户注册时选择兴趣（标签）后，首页会推送相应标签下的博客。
用户管理模块，实现了用户注册、用户登录、用户信息更改（更改密码、更改头像、更改信息等）、显示管理该用户下的评论和留言。
用户留言模块，实现了登录用户在线留言，可显示留言数，留言用户，留言时间，留言内容等。
博客信息管理模块，实现博客信息的增删改查，另包含用户评论显示和用户查看显示。
用户评论模块，基于博客管理在博客下评论，显示评论用户、评论时间、评论内容等。

拓展思路：
后续可通过获取用户IP增加未登录用户（游客）留言、评论等功能。
后续也会将代码中的一些静态常量数据（可能会改变的数据）放到配置文件中，再从文件中提取。

数据库设计：
/*
Navicat MySQL Data Transfer

Source Server         : localhost:3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : zlj_blog

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-05-28 17:41:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zlj_blog
-- ----------------------------
DROP TABLE IF EXISTS `zlj_blog`;
CREATE TABLE `zlj_blog` (
  `bid` int(11) NOT NULL AUTO_INCREMENT COMMENT '博客id',
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `code` char(4) DEFAULT NULL COMMENT '标签编码',
  `title` varchar(30) DEFAULT NULL COMMENT '博客标题',
  `content` varchar(5000) DEFAULT NULL COMMENT '博客内容',
  `visit_num` int(11) DEFAULT NULL COMMENT '博客访问量',
  `comment_num` int(11) DEFAULT NULL COMMENT '评论数量',
  `created_user` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_user` varchar(20) DEFAULT NULL COMMENT '修改者',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zlj_comment
-- ----------------------------
DROP TABLE IF EXISTS `zlj_comment`;
CREATE TABLE `zlj_comment` (
  `cid` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `uid` int(11) DEFAULT NULL COMMENT '评论用户id',
  `bid` int(11) DEFAULT NULL COMMENT '博客id',
  `content` varchar(500) DEFAULT NULL COMMENT '评论内容',
  `created_user` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_user` varchar(20) DEFAULT NULL COMMENT '修改者',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zlj_leave_message
-- ----------------------------
DROP TABLE IF EXISTS `zlj_leave_message`;
CREATE TABLE `zlj_leave_message` (
  `lmid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户留言id',
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `ip` varchar(20) DEFAULT NULL COMMENT '用户IP',
  `content` varchar(2000) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL COMMENT '留言时间',
  `create_user` varchar(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(20) DEFAULT NULL COMMENT '修改者',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`lmid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zlj_siteinfo
-- ----------------------------
DROP TABLE IF EXISTS `zlj_siteinfo`;
CREATE TABLE `zlj_siteinfo` (
  `siid` int(11) NOT NULL AUTO_INCREMENT COMMENT '站点id',
  `creator` varchar(20) DEFAULT NULL COMMENT '创建者',
  `admin` varchar(20) DEFAULT NULL COMMENT '管理员',
  `admin_pwd` varchar(20) DEFAULT NULL COMMENT '管理员密码',
  `visit_num` int(11) DEFAULT NULL COMMENT '网站访问量',
  `start` datetime DEFAULT NULL COMMENT '创建时间',
  `title` varchar(30) DEFAULT NULL COMMENT '网站标题',
  `introduction` varchar(1000) DEFAULT NULL COMMENT '介绍',
  PRIMARY KEY (`siid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zlj_tag
-- ----------------------------
DROP TABLE IF EXISTS `zlj_tag`;
CREATE TABLE `zlj_tag` (
  `tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `parent` char(4) DEFAULT NULL COMMENT '父标签编码',
  `code` char(4) DEFAULT NULL COMMENT '标签编码',
  `name` varchar(16) DEFAULT NULL COMMENT '标签名',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zlj_user
-- ----------------------------
DROP TABLE IF EXISTS `zlj_user`;
CREATE TABLE `zlj_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` char(32) NOT NULL COMMENT '用户密码',
  `salt` char(36) DEFAULT NULL COMMENT '盐值',
  `avatar` varchar(50) DEFAULT NULL COMMENT '头像',
  `interest` varchar(20) DEFAULT NULL COMMENT '兴趣',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `gender` int(11) DEFAULT NULL COMMENT '性别,0-女,1-男',
  `is_delete` int(11) DEFAULT NULL COMMENT '是否删除，0-未删除，1-已删除',
  `created_user` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_user` varchar(20) DEFAULT NULL COMMENT '修改者',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;