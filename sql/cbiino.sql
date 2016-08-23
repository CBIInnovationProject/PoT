# Host: localhost  (Version 5.5.32)
# Date: 2016-08-24 04:26:25
# Generator: MySQL-Front 5.3  (Build 8.10)

/*!40101 SET NAMES latin1 */;

#
# Structure for table "dashboard"
#

DROP TABLE IF EXISTS `dashboard`;
CREATE TABLE `dashboard` (
  `Id` varchar(255) NOT NULL DEFAULT '',
  `createBy` varchar(255) DEFAULT NULL,
  `createDate` timestamp NULL DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `workbookId` varchar(255) DEFAULT NULL,
  `siteId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "dashboard"
#


#
# Structure for table "menu"
#

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(50) NOT NULL DEFAULT '0',
  `action` varchar(255) DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `menuOrder` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updateBy` varchar(255) DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `content` longtext,
  `workbookId` varchar(255) DEFAULT NULL,
  `parentId` varchar(50) DEFAULT NULL,
  `viewId` varchar(255) DEFAULT NULL,
  `siteId` varchar(255) DEFAULT NULL,
  `contentType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK33155FE1B69350` (`parentId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "menu"
#

INSERT INTO `menu` VALUES ('0',NULL,NULL,NULL,'fa fa-black-tie',0,'Tableau Content',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin'),('1',NULL,'faris','2016-06-17 22:55:02','fa fa-sitemap',1,'Menu Management','faris','2016-06-17 22:55:02',NULL,NULL,NULL,NULL,NULL,'admin'),('10','menuList.cbi',NULL,NULL,'fa fa-list',3,'Menu List','faris','2016-06-22 10:06:26',NULL,NULL,'1',NULL,NULL,'admin'),('11','roleForm.cbi','faris','2016-06-17 13:24:42','fa fa-plus-square-o',0,'Create Role','faris','2016-06-17 13:24:42',NULL,NULL,'3',NULL,NULL,'admin'),('12','tableauSite.cbi','faris','2016-06-18 11:03:37','fa fa-globe',0,'Site','faris','2016-06-18 11:03:37',NULL,NULL,'0',NULL,NULL,'admin'),('13','project.cbi','faris','2016-06-18 11:04:38','fa fa-file-code-o',2,'Project','faris','2016-06-18 11:04:38',NULL,NULL,'0',NULL,NULL,'admin'),('14',NULL,'faris','2016-06-18 12:24:57','fa fa-user',4,'User Management','faris','2016-06-21 16:36:33',NULL,NULL,NULL,NULL,NULL,'admin'),('15','userForm.cbi','faris','2016-06-18 12:34:13','fa fa-user-plus',0,'Create User','faris','2016-06-18 12:34:13',NULL,NULL,'14',NULL,NULL,'admin'),('2',NULL,'faris','2016-06-17 23:07:27','fa fa-plus-square-o',0,'Create Menu','faris','2016-06-17 23:07:27',NULL,NULL,'1',NULL,NULL,'admin'),('3',NULL,'faris','2016-06-18 08:59:14','fa fa-user-secret',1,'Role Management','faris','2016-06-18 08:59:14',NULL,NULL,NULL,NULL,NULL,'admin'),('4','roleList.cbi','faris','2016-06-18 09:04:32','fa fa-list',2,'Role List','faris','2016-06-18 09:04:32',NULL,NULL,'3',NULL,NULL,'admin'),('5','userList.cbi','faris','2016-06-18 12:35:23','fa fa-list',1,'User List','faris','2016-06-18 12:35:23',NULL,NULL,'14',NULL,NULL,'admin'),('6','workbook.cbi',NULL,NULL,'fa fa-bar-chart',1,'Workbook',NULL,NULL,NULL,NULL,'0',NULL,NULL,'admin'),('7','parentMenuForm.cbi',NULL,NULL,'fa fa-folder-o',0,'Parent Menu',NULL,NULL,NULL,NULL,'2',NULL,NULL,'admin'),('8','pageMenuForm.cbi',NULL,NULL,'fa fa-file-text-o',1,'Static Page',NULL,NULL,NULL,NULL,'2',NULL,NULL,'admin'),('9','tableauMenuForm.cbi',NULL,NULL,'fa fa-dashboard',2,'Tableau Dashboard',NULL,NULL,NULL,NULL,'2',NULL,NULL,'admin');

#
# Structure for table "properties"
#

DROP TABLE IF EXISTS `properties`;
CREATE TABLE `properties` (
  `name` varchar(255) NOT NULL DEFAULT '',
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "properties"
#

INSERT INTO `properties` VALUES ('proxy.name',NULL),('proxy.port',NULL),('tableau.api.version','2.2'),('tableau.projects.max','500'),('tableau.server.host','http://tableau.cybertrend-intra.com'),('tableau.server.schema.url','http://localhost:8081/PoT/ts-api_2_2.xsd'),('tableau.sites.max','500'),('tableau.users.max','500'),('tableau.views.max','500'),('tableau.workbooks.max','500');

#
# Structure for table "restrict_menu"
#

DROP TABLE IF EXISTS `restrict_menu`;
CREATE TABLE `restrict_menu` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `createDate` timestamp NULL DEFAULT NULL,
  `menu` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "restrict_menu"
#


#
# Structure for table "role"
#

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `Id` varchar(50) NOT NULL DEFAULT '',
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `createDate` timestamp NULL DEFAULT NULL,
  `updateBy` varchar(255) DEFAULT NULL,
  `updateDate` timestamp NULL DEFAULT NULL,
  `siteId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "role"
#

INSERT INTO `role` VALUES ('0','Administrator',NULL,'0',NULL,'0',NULL,NULL);

#
# Structure for table "rolemenu"
#

DROP TABLE IF EXISTS `rolemenu`;
CREATE TABLE `rolemenu` (
  `Id` varchar(50) NOT NULL DEFAULT '',
  `createBy` varchar(255) DEFAULT NULL,
  `createDate` timestamp NULL DEFAULT NULL,
  `updateBy` varchar(255) DEFAULT NULL,
  `updateDate` timestamp NULL DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `menu` varchar(255) DEFAULT NULL,
  `menuOrder` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "rolemenu"
#

INSERT INTO `rolemenu` VALUES ('2',NULL,NULL,NULL,NULL,'0','1',1),('3',NULL,NULL,NULL,NULL,'0','0',0),('4',NULL,NULL,NULL,NULL,'0','3',2),('5',NULL,NULL,NULL,NULL,'0','14',3);

#
# Structure for table "roleuser"
#

DROP TABLE IF EXISTS `roleuser`;
CREATE TABLE `roleuser` (
  `Id` varchar(50) NOT NULL DEFAULT '',
  `user` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `createDate` timestamp NULL DEFAULT NULL,
  `updateBy` varchar(255) DEFAULT NULL,
  `updateDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "roleuser"
#

INSERT INTO `roleuser` VALUES ('1','1','0',NULL,NULL,NULL,NULL);

#
# Structure for table "themes"
#

DROP TABLE IF EXISTS `themes`;
CREATE TABLE `themes` (
  `Id` varchar(11) NOT NULL DEFAULT '',
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `ul` longtext,
  `li` longtext,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "themes"
#

INSERT INTO `themes` VALUES ('1','Default','/views','<li><a><i class=\"%s\"></i>%s<span class=\"fa fa-chevron-down\"></span></a><ul class=\"nav child_menu\">','<li><a class=\"dropdown\" href=\"%s\" target=\"mainframe\"><i class=\"%s\"></i>&nbsp;%s</a></li>'),('2','Bootstrap_Navbar','/themes/Bootstrap_Navbar','<li class=\"dropdown\"><a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"%s\"></i>&nbsp;%s</a><ul class=\"dropdown-menu\">','<li><a class=\"dropdown\" href=\"%s\" target=\"mainframe\"><i class=\"%s\"></i>&nbsp;%s</a></li>');

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Id` varchar(50) NOT NULL DEFAULT '',
  `createBy` varchar(255) DEFAULT NULL,
  `createDate` timestamp NULL DEFAULT NULL,
  `updateBy` varchar(255) DEFAULT NULL,
  `updateDate` timestamp NULL DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `address1` varchar(255) DEFAULT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `address3` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `siteId` varchar(255) DEFAULT NULL,
  `roleId` varchar(255) DEFAULT NULL,
  `userTableauId` varchar(255) DEFAULT NULL,
  `themes` varchar(255) DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "user"
#

INSERT INTO `user` VALUES ('1','1','2016-08-18 16:18:08','1','2016-08-18 16:18:08','dwbi','dwbi','','','','','','',NULL,'','167c0b37-7f1a-47f4-b4e3-06dabd7fbdde','0','3','1'),('1471532664027','1','2016-08-18 22:04:24','1','2016-08-18 22:04:24','admin','admin','','','','','','',NULL,'','b4126fe9-d7ee-4083-88f9-a5eea1f40416','0','1','1');

#
# Structure for table "usertableau"
#

DROP TABLE IF EXISTS `usertableau`;
CREATE TABLE `usertableau` (
  `Id` varchar(50) NOT NULL DEFAULT '',
  `createBy` varchar(255) DEFAULT NULL,
  `createDate` timestamp NULL DEFAULT NULL,
  `updateBy` varchar(255) DEFAULT NULL,
  `updateDate` timestamp NULL DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `inUse` int(11) DEFAULT '0',
  `capacity` int(11) DEFAULT '0',
  `contentUrl` varchar(255) DEFAULT '',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "usertableau"
#

INSERT INTO `usertableau` VALUES ('1',NULL,NULL,NULL,NULL,'Cybertrend','passcbi2015',0,0,''),('2',NULL,NULL,NULL,NULL,'DEVBI','tableaubi2014',0,0,'DWBI'),('3',NULL,NULL,NULL,NULL,'Cybertrend','passcbi2015',0,0,'DWBI');
