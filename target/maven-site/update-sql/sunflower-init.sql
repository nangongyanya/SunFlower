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

/*Table structure for table `admin_log` */

DROP TABLE IF EXISTS `admin_log`;

CREATE TABLE `admin_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_method` varchar(150) DEFAULT NULL COMMENT '操作类',
  `opt_date` datetime DEFAULT NULL COMMENT '操作时间',
  `opt_desc` text COMMENT '操作描述',
  `remake` text COMMENT '备注',
  `admin_name` varchar(50) DEFAULT NULL COMMENT '管理员名称',
  `admin_id` int(11) DEFAULT NULL COMMENT '管理员id',
  `opt_type` varchar(10) DEFAULT NULL COMMENT '操作类型：update、delete、save',
  `opt_ip` varchar(15) DEFAULT NULL COMMENT '操作ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='系统日志表';

/*Data for the table `admin_log` */

insert  into `admin_log`(`id`,`class_method`,`opt_date`,`opt_desc`,`remake`,`admin_name`,`admin_id`,`opt_type`,`opt_ip`) values (1,'SystemLoginLogout','2018-02-07 20:09:12','登陆成功==>admin/e00cf25ad42683b3df678c61f42c6bda','','超级管理员[admin]',1,'login','192.168.1.5'),(2,'AdminUserController','2018-02-07 20:11:16','添加管理员:甘振隆[ganzhenlong]','','超级管理员[admin]',1,'save','192.168.1.5'),(3,'AdminUserController','2018-02-07 20:16:03','添加管理员:雷方庆[leifangqing]','','超级管理员[admin]',1,'save','192.168.1.5'),(4,'SystemLoginLogout','2018-02-07 20:18:33','登陆成功==>admin/e00cf25ad42683b3df678c61f42c6bda','','超级管理员[admin]',1,'login','192.168.1.5'),(5,'AdminUserController','2018-02-07 20:19:02','添加管理员:陈君浩[chenjunhao]','','超级管理员[admin]',1,'save','192.168.1.5'),(6,'SystemLoginLogout','2018-02-07 20:21:11','登陆成功==>admin/e00cf25ad42683b3df678c61f42c6bda','','超级管理员[admin]',1,'login','192.168.1.5'),(7,'AdminUserController','2018-02-07 20:21:48','添加管理员:黄丽娟[huanglijuan]','','超级管理员[admin]',1,'save','192.168.1.5'),(8,'SystemLoginLogout','2018-02-15 06:55:52','登陆成功==>admin/e00cf25ad42683b3df678c61f42c6bda','','超级管理员[admin]',1,'login','192.168.1.8'),(9,'McCommonDataController','2018-02-15 07:20:22','添加基础数据:80','','超级管理员[admin]',1,'save','192.168.1.8'),(10,'McCommonDataController','2018-02-15 07:20:35','添加基础数据:81','','超级管理员[admin]',1,'save','192.168.1.8'),(11,'McCommonDataController','2018-02-15 07:20:44','添加基础数据:82','','超级管理员[admin]',1,'save','192.168.1.8'),(12,'SystemLoginLogout','2018-02-15 07:41:55','登陆成功==>admin/e00cf25ad42683b3df678c61f42c6bda','','超级管理员[admin]',1,'login','192.168.1.8'),(13,'McCommonFaqController','2018-02-15 08:11:56','添加常见问题:1','','超级管理员[admin]',1,'save','192.168.1.8'),(14,'SystemLoginLogout','2018-02-15 08:15:49','登陆成功==>admin/e00cf25ad42683b3df678c61f42c6bda','','超级管理员[admin]',1,'login','192.168.1.8'),(15,'McCommonFaqController','2018-02-15 08:19:51','添加常见问题:2','','超级管理员[admin]',1,'save','192.168.1.8'),(16,'McCommonFaqController','2018-02-15 08:46:19','修改常见问题:2','','超级管理员[admin]',1,'update','192.168.1.8'),(17,'McCommonFaqController','2018-02-15 08:48:46','修改常见问题:1','','超级管理员[admin]',1,'update','192.168.1.8'),(18,'McCommonFaqController','2018-02-15 08:49:00','添加常见问题:3','','超级管理员[admin]',1,'save','192.168.1.8'),(19,'McCommonFaqController','2018-02-15 08:49:07','添加常见问题:4','','超级管理员[admin]',1,'save','192.168.1.8'),(20,'McCommonFaqController','2018-02-15 08:52:39','删除常见问题:1','','超级管理员[admin]',1,'delete','192.168.1.8'),(21,'McCommonFaqController','2018-02-15 08:53:48','删除常见问题:4,3','','超级管理员[admin]',1,'delete','192.168.1.8'),(22,'SystemLoginLogout','2018-02-15 09:04:29','登陆成功==>admin/e00cf25ad42683b3df678c61f42c6bda','','超级管理员[admin]',1,'login','192.168.1.8');

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='栏目表';

/*Data for the table `admin_menus` */

insert  into `admin_menus`(`id`,`name`,`url`,`sort`,`description`,`parent_id`,`status`) values (1,'Dashboard','/system/index.h',1,'Dashboard',NULL,NULL),(6,'基础设置','/',2,'基础设置',NULL,NULL),(10,'基础数据管理','/',1,'基础数据管理',6,NULL),(11,'数据类型','/system/cms/mcCommonDataType_list.h',1,'数据类型',10,NULL),(12,'基础数据','/system/cms/mcCommonData_list.h',2,'基础数据',10,NULL),(13,'系统功能','/',3,'系统功能',NULL,NULL),(14,'权限管理','/',1,'权限管理',13,NULL),(15,'管理员管理','/system/admin/adminUser_list.h',1,'管理员管理',14,NULL),(16,'角色管理','/system/admin/adminRole_list.h',2,'角色管理',14,NULL),(17,'功能菜单管理','/system/admin/adminMenu_index.h',3,'功能菜单管理',14,NULL);

/*Table structure for table `admin_role` */

DROP TABLE IF EXISTS `admin_role`;

CREATE TABLE `admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(50) DEFAULT NULL COMMENT '角色描述',
  `role_code` varchar(50) DEFAULT NULL COMMENT '角色码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_role` (`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='管理员角色表';

/*Data for the table `admin_role` */

insert  into `admin_role`(`id`,`role_name`,`role_desc`,`role_code`) values (1,'超级管理员','超级管理员','SystemAdmin'),(6,'二级管理员','二级管理员','S-Admin'),(7,'三级管理员','三级管理员','T-Admin');

/*Table structure for table `admin_role_url` */

DROP TABLE IF EXISTS `admin_role_url`;

CREATE TABLE `admin_role_url` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色id',
  `menu_id` varchar(50) DEFAULT NULL COMMENT '栏目id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='角色栏目关联关系表';

/*Data for the table `admin_role_url` */

insert  into `admin_role_url`(`id`,`role_id`,`menu_id`) values (11,'6','1');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='管理员表';

/*Data for the table `admin_user` */

insert  into `admin_user`(`id`,`nickname`,`username`,`psw`,`date_added`,`last_login_date`,`login_count`,`last_login_ip`,`status`,`group_id`) values (1,'超级管理员','admin','e00cf25ad42683b3df678c61f42c6bda','2018-01-21 16:11:37','2018-02-15 09:04:29',36,'192.168.1.8',0,NULL),(2,'李明','liming','6b0477bed0d8393dd412fd1b45f46c6f','2018-01-29 19:45:21','2018-02-05 22:33:25',5,'192.168.1.5',0,NULL),(3,'甘振隆','ganzhenlong','abed42f5987cdbd1bb16e5a66770ec49','2018-02-07 20:11:16',NULL,NULL,NULL,0,NULL),(4,'雷方庆','leifangqing','916f4aca76e836639e956bbab2eea7c2','2018-02-07 20:16:03',NULL,NULL,NULL,0,NULL),(5,'陈君浩','chenjunhao','4f0342798accb08c9ce894db5836597c','2018-02-07 20:19:02',NULL,NULL,NULL,0,NULL),(6,'黄丽娟','huanglijuan','016d3714d68af359fbd79344ce0030d5','2018-02-07 20:21:48',NULL,NULL,NULL,0,NULL);

/*Table structure for table `admin_user_to_role` */

DROP TABLE IF EXISTS `admin_user_to_role`;

CREATE TABLE `admin_user_to_role` (
  `role_id` int(11) NOT NULL,
  `admin_id` int(11) NOT NULL,
  `date_added` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`role_id`,`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与管理员关系表';

/*Data for the table `admin_user_to_role` */

insert  into `admin_user_to_role`(`role_id`,`admin_id`,`date_added`) values (1,1,'2018-01-21 16:27:27'),(6,2,'2018-02-05 12:05:27'),(6,3,'2018-01-28 19:28:13');

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
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8 COMMENT='基础字典表';

/*Data for the table `mc_common_data` */

insert  into `mc_common_data`(`id`,`name`,`sort`,`status`,`type`,`date_added`,`last_modified`) values (49,'星期一',1,1,45,'2018-01-19 00:28:26','2018-01-19 10:45:47'),(65,'星期二',1,1,45,'2018-01-19 00:28:26','2018-01-19 10:45:56'),(66,'星期三',1,1,45,'2018-01-19 00:28:26','2018-01-19 10:46:06'),(67,'星期四',1,1,45,'2018-01-19 00:28:26','2018-01-19 10:46:14'),(68,'星期五',1,1,45,'2018-01-19 00:28:26','2018-01-19 10:46:25'),(69,'星期六',1,1,45,'2018-01-19 00:28:26','2018-01-19 10:46:39'),(75,'星期日',1,1,45,'2018-01-19 10:47:00','2018-01-19 10:47:00'),(76,'文档文件',1,1,46,'2018-02-07 19:56:23','2018-02-07 19:56:23'),(77,'图片文件',2,1,46,'2018-02-07 19:59:34','2018-02-07 19:59:34'),(78,'音频文件',3,1,46,'2018-02-07 20:01:34','2018-02-07 20:01:34'),(79,'视频文件',4,1,46,'2018-02-07 20:04:06','2018-02-07 20:04:06'),(80,'综合',1,1,47,'2018-02-15 07:20:22','2018-02-15 07:20:22'),(81,'账号',2,1,47,'2018-02-15 07:20:35','2018-02-15 07:20:35'),(82,'支付',3,1,47,'2018-02-15 07:20:44','2018-02-15 07:20:44');

/*Table structure for table `mc_common_data_type` */

DROP TABLE IF EXISTS `mc_common_data_type`;

CREATE TABLE `mc_common_data_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `date_added` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modified` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COMMENT='基础字典类型表';

/*Data for the table `mc_common_data_type` */

insert  into `mc_common_data_type`(`id`,`name`,`date_added`,`last_modified`) values (45,'星期','2018-01-18 18:01:13','2018-01-18 18:01:13'),(46,'文件类型','2018-02-07 19:55:48','2018-02-07 19:55:48'),(47,'常见问答类型','2018-02-15 07:19:03','2018-02-15 07:19:03');

/*Table structure for table `mc_common_faq` */

DROP TABLE IF EXISTS `mc_common_faq`;

CREATE TABLE `mc_common_faq` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_added` datetime DEFAULT NULL COMMENT '添加时间',
  `last_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `faq_question` varchar(200) DEFAULT NULL COMMENT '常见问题',
  `faq_answer` text COMMENT '常见问题解答',
  `faq_type` tinyint(1) DEFAULT NULL COMMENT '常见问题分类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='常见问答表';

/*Data for the table `mc_common_faq` */

insert  into `mc_common_faq`(`id`,`date_added`,`last_modified`,`faq_question`,`faq_answer`,`faq_type`) values (2,'2018-02-15 08:19:51','2018-02-15 08:46:19','中国短道4次犯规','<div class=\"img_wrapper\" style=\"text-align: center; color: rgb(77, 79, 83); font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, SimSun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\"><img alt=\"中国队首先被罚是任子威\" src=\"http://n.sinaimg.cn/sports/transform/w650h433/20180213/_Os7-fyrpeie3860162.jpg\" style=\"border-style: none; display: block; margin: 0px auto; max-width: 640px;\"><span class=\"img_descr\" style=\"line-height: 20px; padding: 6px 0px; font-size: 16px; margin: 5px auto; display: inline-block; zoom: 1; text-align: left; font-weight: bold;\">中国队首先被罚是任子威</span></div><p style=\"margin-bottom: 30px; padding: 0px; text-size-adjust: 100%; color: rgb(77, 79, 83); font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, SimSun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\">　　北京时间2月13日消息，短道速滑应该算是非裁判打分项目中，裁判扮演角色最重的项目。<a href=\"http://sports.sina.com.cn/pyeongchang2018/\" target=\"_blank\" style=\"color: rgb(221, 0, 0); outline: 0px; -webkit-tap-highlight-color: transparent;\">2018年平昌冬奥会</a>短道速滑的第二比赛日，中国队在半个小时内有4人被判犯规，这让中国观众再次认识到裁判在短道比赛中所扮演的重要角色。</p><p style=\"margin-bottom: 30px; padding: 0px; text-size-adjust: 100%; color: rgb(77, 79, 83); font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, SimSun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\">　　中国队首先被罚是任子威，他在1000米资格赛第4小组拿到第二，可最后却被判犯规取消了成绩。赛后转播镜头多次回放的是任子威在倒数第二圈成功超越领先的两位选手的画面，在任子威越过荷兰名将科内格特的时候，他的手上有些动作，人们不禁觉得裁判是吹了他这个动作的犯规。可当你仔细看看这一小组的成绩单时，你就会惊奇地发现，任子威犯规被罚的受益者是拉脱维亚选手兹维涅克斯，原本排在小组第4的他被判直接晋级，这说明是他被任子威犯规了。</p><p style=\"margin-bottom: 30px; padding: 0px; text-size-adjust: 100%; color: rgb(77, 79, 83); font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, SimSun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\">　　回想一下比赛过程，任子威和兹维涅克斯直接比拼并不多，最可能的就是比赛还剩下5圈时，兹维涅克斯超越任子威时两人有过身体接触。观看比赛时，人们更直观的感受是兹维涅克斯干扰了任子威的滑行，可最终的判罚结果却是任子威犯规被取消成绩。</p><div class=\"img_wrapper\" style=\"text-align: center; color: rgb(77, 79, 83); font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, SimSun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\"><img src=\"http://n.sinaimg.cn/sports/transform/w650h433/20180213/u4_y-fyrpeie3541684.jpg\" alt=\"韩天宇\" data-link=\"\" style=\"border-style: none; display: block; margin: 0px auto; max-width: 640px;\"><span class=\"img_descr\" style=\"line-height: 20px; padding: 6px 0px; font-size: 16px; margin: 5px auto; display: inline-block; zoom: 1; text-align: left; font-weight: bold;\">韩天宇</span></div><p style=\"margin-bottom: 30px; padding: 0px; text-size-adjust: 100%; color: rgb(77, 79, 83); font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, SimSun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\">　　第二个被判犯规的是韩天宇。1000米第6小组出场的韩天宇表现得很好，在比赛还有4圈结束时他抓住一个机会从内道超越了原本领先的韩国选手徐一拉，这时两人略有身体接触。为了向裁判证明自己的超越是干净的，韩天宇特意在这个过程中举起右手，表示他在手上没有任何动作。可即便如此，裁判回看录像后还是判罚了韩天宇犯规，这让位于教练席的李琰都感觉难以理解，愤然转身离开。套用球类运动的一个常用词，韩天宇这次应该是“体毛级”的犯规动作，对常有身体接触的短道比赛来说，这真的就是毛毛雨，他应该是四位被罚的中国运动员里最冤的那一个。</p><div class=\"img_wrapper\" style=\"text-align: center; color: rgb(77, 79, 83); font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, SimSun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\"><img src=\"http://n.sinaimg.cn/sports/transform/w650h433/20180213/J2LO-fyrpeie3539726.jpg\" alt=\"范可新\" data-link=\"\" style=\"border-style: none; display: block; margin: 0px auto; max-width: 640px;\"><span class=\"img_descr\" style=\"line-height: 20px; padding: 6px 0px; font-size: 16px; margin: 5px auto; display: inline-block; zoom: 1; text-align: left; font-weight: bold;\">范可新</span></div><p style=\"margin-bottom: 30px; padding: 0px; text-size-adjust: 100%; color: rgb(77, 79, 83); font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, SimSun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\">　　随后进行的女子500米半决赛，中国队的两位运动员先后被判犯规。半决赛第1组，第4道出发的范可新起步不算理想，落在最后的她想要超越俄罗斯的奥运选手普洛斯维诺娃，两人发生了身体接触，从比赛过程来看，受到影响更大的应该是范可新，她追赶的步伐因此被拖慢，始终无法真正威胁到排名前两位的冯塔纳和崔敏静。赛后裁判对范可新身体接触的判罚是她犯规了。</p><div class=\"img_wrapper\" style=\"text-align: center; color: rgb(77, 79, 83); font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, SimSun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\"><img src=\"http://n.sinaimg.cn/sports/transform/w650h351/20180213/1aKk-fyrpeie3481717.jpg\" alt=\"曲春雨\" data-link=\"\" style=\"border-style: none; display: block; margin: 0px auto; max-width: 640px;\"><span class=\"img_descr\" style=\"line-height: 20px; padding: 6px 0px; font-size: 16px; margin: 5px auto; display: inline-block; zoom: 1; text-align: left; font-weight: bold;\">曲春雨</span></div><p style=\"margin-bottom: 30px; padding: 0px; text-size-adjust: 100%; color: rgb(77, 79, 83); font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, SimSun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\">　　半决赛第2组，曲春雨起步后排在第三位，当两位领先者克里斯蒂和鲍汀就第一位展开激烈拼争时，曲春雨得到反超的良机，她从内道准备超越，可惜她的起步慢了一点，这时被鲍汀挥手打到，失去平衡摔出赛道。从赛后的慢镜头重放来看，曲春雨在失去平衡后有一个伸手动作，这碰到了滑行中的鲍汀。可能就是这个动作，在赛后被裁判判为犯规，所以曲春雨接受采访时才会说：“可能是我摔倒耽误她们了吧。”</p><p style=\"margin-bottom: 30px; padding: 0px; text-size-adjust: 100%; color: rgb(77, 79, 83); font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, SimSun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\">　　两个项目，四次颇具争议的判罚，受害者无一例外的都是中国运动员，这不禁让人感叹，我们的“运气”真的有些差！而裁判，真是短道赛场上人们不能忽略的重要角色。</p>',80);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
