# Host: localhost  (Version 5.5.32)
# Date: 2016-08-22 10:36:03
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

INSERT INTO `dashboard` VALUES ('140ea0af-d4df-4cc0-9fc4-82a8dc5ae9e3','faris','2016-08-20 20:29:20','AHPDashboard/BillableHours','a983c0d3-927c-45a5-965f-442e68545882','167c0b37-7f1a-47f4-b4e3-06dabd7fbdde'),('3256f6b7-060c-4e1e-81a6-973b97c7578b','faris','2016-08-20 20:29:09','AHPDashboard/CoverPage','a983c0d3-927c-45a5-965f-442e68545882','167c0b37-7f1a-47f4-b4e3-06dabd7fbdde'),('8e700267-a9b9-475d-8d01-5958355365b2','faris','2016-08-20 04:54:48','AcquisitionMidi/EOSMIDI','b2421bcb-ae2f-4d0c-ad19-9de2d68c1f9e','167c0b37-7f1a-47f4-b4e3-06dabd7fbdde'),('fad4be39-c811-45a7-97b6-f026bea6dd85','faris','2016-08-20 04:54:26','AcquisitionIPhone/EOSSSTIphone','5a6d36c9-fc6f-4bc6-a1be-9937b3f3666e','167c0b37-7f1a-47f4-b4e3-06dabd7fbdde');

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

INSERT INTO `menu` VALUES ('0',NULL,NULL,NULL,'fa fa-black-tie',0,'Tableau Content',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin'),('1',NULL,'faris','2016-06-17 22:55:02','fa fa-sitemap',1,'Menu Management','faris','2016-06-17 22:55:02',NULL,NULL,NULL,NULL,NULL,'admin'),('10','menuList.cbi',NULL,NULL,'fa fa-list',3,'Menu List','faris','2016-06-22 10:06:26',NULL,NULL,'1',NULL,NULL,'admin'),('11','roleForm.cbi','faris','2016-06-17 13:24:42','fa fa-plus-square-o',0,'Create Role','faris','2016-06-17 13:24:42',NULL,NULL,'3',NULL,NULL,'admin'),('12','tableauSite.cbi','faris','2016-06-18 11:03:37','fa fa-globe',0,'Site','faris','2016-06-18 11:03:37',NULL,NULL,'0',NULL,NULL,'admin'),('13','project.cbi','faris','2016-06-18 11:04:38','fa fa-file-code-o',2,'Project','faris','2016-06-18 11:04:38',NULL,NULL,'0',NULL,NULL,'admin'),('14',NULL,'faris','2016-06-18 12:24:57','fa fa-user',4,'User Management','faris','2016-06-21 16:36:33',NULL,NULL,NULL,NULL,NULL,'admin'),('1471699858869','coba_induk_menu.cbi','1','2016-08-20 20:30:58','fa fa-ambulance',0,'Coba Induk Menu','1','2016-08-20 20:30:58',NULL,NULL,NULL,NULL,'167c0b37-7f1a-47f4-b4e3-06dabd7fbdde',NULL),('1471699975499','coba_static_page.cbi','1','2016-08-20 20:32:55','fa fa-align-left',0,'Coba Static Page','1','2016-08-20 20:32:55','Cobaaa isiiiii',NULL,NULL,NULL,'167c0b37-7f1a-47f4-b4e3-06dabd7fbdde','page'),('1471700061002','coba_tableau_menu.cbi','1','2016-08-20 20:34:21','fa fa-ambulance',0,'Coba Tableau Menu','1','2016-08-20 20:34:21','AcquisitionMidi/EOSMIDI','b2421bcb-ae2f-4d0c-ad19-9de2d68c1f9e','1471699858869','8e700267-a9b9-475d-8d01-5958355365b2','167c0b37-7f1a-47f4-b4e3-06dabd7fbdde','tableau'),('1471726194629','tesssss.cbi','faris','2016-08-21 03:49:54','glyphicon glyphicon-arrow-left',1,'Tesssss','faris','2016-08-21 03:49:54','Ini cuma coba-cobapada suatu hari ',NULL,NULL,NULL,'167c0b37-7f1a-47f4-b4e3-06dabd7fbdde','page'),('1471726611672','sadsadsadsad.cbi','faris','2016-08-21 03:56:51',' ',0,'sadsadsadsad','faris','2016-08-21 03:56:51','',NULL,'1471699858869',NULL,'167c0b37-7f1a-47f4-b4e3-06dabd7fbdde','page'),('1471726712283','asdsadsadsad.cbi','faris','2016-08-21 03:58:32',' ',0,'asdsadsadsad','faris','2016-08-21 03:58:32','<div style=\"text-align: center;\"><b style=\"line-height: 1.471;\">sadasdsfsdfsdfsdfdfgdfgdfgf</b></div><div style=\"text-align: center;\"><b style=\"line-height: 1.471;\"><br></b></div><div style=\"text-align: center;\"><b style=\"line-height: 1.471;\"><font face=\"georgia\">dsfsdfdsfsdfds<font size=\"6\">sdfsdfsdffasfasfd</font></font></b></div>',NULL,'1471699858869',NULL,'167c0b37-7f1a-47f4-b4e3-06dabd7fbdde','page'),('15','userForm.cbi','faris','2016-06-18 12:34:13','fa fa-user-plus',0,'Create User','faris','2016-06-18 12:34:13',NULL,NULL,'14',NULL,NULL,'admin'),('2',NULL,'faris','2016-06-17 23:07:27','fa fa-plus-square-o',0,'Create Menu','faris','2016-06-17 23:07:27',NULL,NULL,'1',NULL,NULL,'admin'),('3',NULL,'faris','2016-06-18 08:59:14','fa fa-user-secret',1,'Role Management','faris','2016-06-18 08:59:14',NULL,NULL,NULL,NULL,NULL,'admin'),('4','roleList.cbi','faris','2016-06-18 09:04:32','fa fa-list',2,'Role List','faris','2016-06-18 09:04:32',NULL,NULL,'3',NULL,NULL,'admin'),('5','userList.cbi','faris','2016-06-18 12:35:23','fa fa-list',1,'User List','faris','2016-06-18 12:35:23',NULL,NULL,'14',NULL,NULL,'admin'),('6','workbook.cbi',NULL,NULL,'fa fa-bar-chart',1,'Workbook',NULL,NULL,NULL,NULL,'0',NULL,NULL,'admin'),('7','parentMenuForm.cbi',NULL,NULL,'fa fa-folder-o',0,'Parent Menu',NULL,NULL,NULL,NULL,'2',NULL,NULL,'admin'),('8','pageMenuForm.cbi',NULL,NULL,'fa fa-file-text-o',1,'Static Page',NULL,NULL,NULL,NULL,'2',NULL,NULL,'admin'),('9','tableauMenuForm.cbi',NULL,NULL,'fa fa-dashboard',2,'Tableau Dashboard',NULL,NULL,NULL,NULL,'2',NULL,NULL,'admin');

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

INSERT INTO `role` VALUES ('0','Administrator',NULL,'0',NULL,'0',NULL,NULL),('1466482543690','fdsfdsf','sdfsdfsd','faris','2016-06-21 11:15:43','faris','2016-06-21 11:15:43','b4126fe9-d7ee-4083-88f9-a5eea1f40416'),('1466484188553','dsfdsfds','sdfdsfdsf','faris','2016-06-21 11:43:08','faris','2016-06-21 11:43:08','b4126fe9-d7ee-4083-88f9-a5eea1f40416'),('1469126111889','Coba','Ada','faris','2016-07-22 01:35:11','faris','2016-07-22 01:35:11','b4126fe9-d7ee-4083-88f9-a5eea1f40416'),('1471640576066','Hallo','Coba aja kok','1','2016-08-20 04:02:56','1','2016-08-20 04:02:56','b4126fe9-d7ee-4083-88f9-a5eea1f40416'),('1471644046961','User Biasa','Cuma user biasa','1','2016-08-20 05:00:46','1','2016-08-20 05:00:46','167c0b37-7f1a-47f4-b4e3-06dabd7fbdde'),('1471700112518','Coba Role 1','Tessssssssstttt','1','2016-08-20 20:35:12','1','2016-08-20 20:35:12','167c0b37-7f1a-47f4-b4e3-06dabd7fbdde');

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

INSERT INTO `rolemenu` VALUES ('1471757901410','faris','2016-08-21 12:38:21',NULL,'2016-08-21 12:38:21','1471700112518','1471699858869',0),('1471757902766','faris','2016-08-21 12:38:22',NULL,'2016-08-21 12:38:22','1471700112518','1471699975499',0),('1471757903940','faris','2016-08-21 12:38:23',NULL,'2016-08-21 12:38:23','1471700112518','1471726194629',1),('2',NULL,NULL,NULL,NULL,'0','1',1),('3',NULL,NULL,NULL,NULL,'0','0',0),('4',NULL,NULL,NULL,NULL,'0','3',2),('5',NULL,NULL,NULL,NULL,'0','14',3);

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

INSERT INTO `user` VALUES ('1','1','2016-08-18 16:18:08','1','2016-08-18 16:18:08','faris','faris','','','','','','',NULL,'','167c0b37-7f1a-47f4-b4e3-06dabd7fbdde','0','3','1'),('1471532664027','1','2016-08-18 22:04:24','1','2016-08-18 22:04:24','admin','admin','','','','','','',NULL,'','b4126fe9-d7ee-4083-88f9-a5eea1f40416','0','1','1'),('1471644144372','1','2016-08-20 05:02:24','1','2016-08-20 05:02:24','abisaad','abisaad','','','','','','',NULL,'','167c0b37-7f1a-47f4-b4e3-06dabd7fbdde','1471644046961','2','1'),('1471700165779','1','2016-08-20 20:36:05','1','2016-08-20 20:36:05','amel','amel','','','','','','',NULL,'','167c0b37-7f1a-47f4-b4e3-06dabd7fbdde','1471700112518','2','2');

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
