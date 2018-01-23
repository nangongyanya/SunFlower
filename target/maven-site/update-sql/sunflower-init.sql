/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.6.24-log : Database - sunflower
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sunflower` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `sunflower`;

/*Table structure for table `admin_menus` */

DROP TABLE IF EXISTS `admin_menus`;

CREATE TABLE `admin_menus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '栏目名称',
  `url` varchar(500) DEFAULT NULL COMMENT '栏目地址',
  `sort` int(11) DEFAULT NULL COMMENT '栏目排序',
  `description` varchar(500) DEFAULT NULL COMMENT '栏目描述',
  `parent_id` int(11) DEFAULT NULL COMMENT '栏目父id',
  `status` int(11) DEFAULT NULL COMMENT '是否有效 1 有效 0 无效',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='栏目表';

/*Data for the table `admin_menus` */

/*Table structure for table `admin_role` */

DROP TABLE IF EXISTS `admin_role`;

CREATE TABLE `admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(50) DEFAULT NULL COMMENT '角色描述',
  `role_code` varchar(50) DEFAULT NULL COMMENT '角色码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_role` (`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员角色表';

/*Data for the table `admin_role` */

insert  into `admin_role`(`id`,`role_name`,`role_desc`,`role_code`) values (1,'超级管理员','超级管理员','SystemAdmin');

/*Table structure for table `admin_role_url` */

DROP TABLE IF EXISTS `admin_role_url`;

CREATE TABLE `admin_role_url` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色id',
  `menu_id` varchar(50) DEFAULT NULL COMMENT '栏目id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色栏目关联关系表';

/*Data for the table `admin_role_url` */

/*Table structure for table `admin_user` */

DROP TABLE IF EXISTS `admin_user`;

CREATE TABLE `admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(50) NOT NULL COMMENT '昵称',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `psw` varchar(50) NOT NULL COMMENT '登录密码',
  `date_added` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_date` datetime DEFAULT NULL COMMENT '最近登录时间',
  `login_count` int(11) DEFAULT NULL COMMENT '登录次数',
  `last_login_ip` varchar(20) DEFAULT NULL COMMENT '登录IP',
  `status` tinyint(1) NOT NULL COMMENT '账号状态',
  `group_id` int(11) DEFAULT NULL COMMENT '组织部门id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='管理员表';

/*Data for the table `admin_user` */

insert  into `admin_user`(`id`,`nickname`,`username`,`psw`,`date_added`,`last_login_date`,`login_count`,`last_login_ip`,`status`,`group_id`) values (1,'超级管理员','admin','e00cf25ad42683b3df678c61f42c6bda','2018-01-21 16:11:37','2018-01-23 23:45:03',10,'192.168.1.5',0,NULL),(2,'轩滨','xuanbin','e398790ca93988ecff877c935db6c243','2018-01-23 23:39:59',NULL,NULL,NULL,0,NULL);

/*Table structure for table `admin_user_to_role` */

DROP TABLE IF EXISTS `admin_user_to_role`;

CREATE TABLE `admin_user_to_role` (
  `role_id` int(11) NOT NULL,
  `admin_id` int(11) NOT NULL,
  `date_added` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`role_id`,`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与管理员关系表';

/*Data for the table `admin_user_to_role` */

insert  into `admin_user_to_role`(`role_id`,`admin_id`,`date_added`) values (1,1,'2018-01-21 16:27:27');

/*Table structure for table `mc_common_data` */

DROP TABLE IF EXISTS `mc_common_data`;

CREATE TABLE `mc_common_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `sort` int(5) DEFAULT NULL COMMENT '排序',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态：1 有效， 0 无效',
  `type` tinyint(1) DEFAULT NULL COMMENT '数据类型',
  `date_added` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modified` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mc_common_data_u` (`name`,`type`),
  KEY `idx_sort` (`sort`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8 COMMENT='基础字典表';

/*Data for the table `mc_common_data` */

insert  into `mc_common_data`(`id`,`name`,`sort`,`status`,`type`,`date_added`,`last_modified`) values (6,'散文',1,1,1,'2018-01-19 00:28:26','2018-01-19 00:28:29'),(49,'星期一',1,1,45,'2018-01-19 00:28:26','2018-01-19 10:45:47'),(65,'星期二',1,1,45,'2018-01-19 00:28:26','2018-01-19 10:45:56'),(66,'星期三',1,1,45,'2018-01-19 00:28:26','2018-01-19 10:46:06'),(67,'星期四',1,1,45,'2018-01-19 00:28:26','2018-01-19 10:46:14'),(68,'星期五',1,1,45,'2018-01-19 00:28:26','2018-01-19 10:46:25'),(69,'星期六',1,1,45,'2018-01-19 00:28:26','2018-01-19 10:46:39'),(75,'星期日',1,1,45,'2018-01-19 10:47:00','2018-01-19 10:47:00');

/*Table structure for table `mc_common_data_type` */

DROP TABLE IF EXISTS `mc_common_data_type`;

CREATE TABLE `mc_common_data_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `date_added` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modified` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT='基础字典类型表';

/*Data for the table `mc_common_data_type` */

insert  into `mc_common_data_type`(`id`,`name`,`date_added`,`last_modified`) values (1,'标签','2018-01-18 15:08:11','2018-01-18 19:10:51'),(45,'星期','2018-01-18 18:01:13','2018-01-18 18:01:13'),(46,'月份','2018-01-18 18:12:27','2018-01-18 18:12:27');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
