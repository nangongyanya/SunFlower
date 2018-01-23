/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.6.24-log : Database - lan
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
