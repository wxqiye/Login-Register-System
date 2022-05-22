/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.27 : Database - webdong
*********************************************************************
*/



/*!40101 SET NAMES utf8 */;


/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`webdong` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `webdong`;

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `roleCode` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色编码',
  `roleName` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `createdBy` bigint DEFAULT NULL COMMENT '创建者',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint DEFAULT NULL COMMENT '修改者',
  `modifyDate` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

/*Data for the table `role` */

insert  into `role`(`id`,`roleCode`,`roleName`,`createdBy`,`creationDate`,`modifyBy`,`modifyDate`) values (1,'SMBMS_ADMIN','系统管理员',1,'2016-04-13 00:00:00',NULL,NULL),(2,'SMBMS_MANAGER','经理',1,'2016-04-13 00:00:00',NULL,NULL),(3,'SMBMS_EMPLOYEE','普通员工',1,'2016-04-13 00:00:00',NULL,NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `userCode` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户编码',
  `userName` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名称',
  `userPassword` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户密码',
  `gender` int DEFAULT NULL COMMENT '性别（1:女、 2:男）',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机',
  `path` varchar(400) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '头像路径',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '电子邮件',
  `authority` int DEFAULT '3' COMMENT '用户权限',
  `role` varchar(20) COLLATE utf8_unicode_ci DEFAULT '普通用户' COMMENT '用户角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`userCode`,`userName`,`userPassword`,`gender`,`birthday`,`phone`,`path`,`email`,`authority`,`role`) values (1,'admin','系统管理员工','1234567',1,'1983-11-12','12356788544','E:\\JavaWeb\\01.Login-Register-System\\Login-Register-System\\target\\Login-Register-System-1.0.o-SNAPSHOT\\WEB-INF\\upload/fc7e2072-c865-44ec-8c05-678bf8ec8f82\\success.jpg',NULL,1,'系统管理员'),(2,'liming','李明','0000000',2,'1983-12-10','13688884457','',NULL,3,'普通用户'),(5,'hanlubiao','韩路彪','0000000',2,'1984-06-05','18567542321','',NULL,3,'普通用户'),(6,'zhanghua','张华','0000000',1,'1983-06-15','13544561111','',NULL,3,'普通用户'),(7,'wangyang','王洋','0000000',2,'1982-12-31','13444561124','',NULL,3,'普通用户'),(8,'zhaoyan','赵燕','0000000',1,'1986-03-07','18098764545','',NULL,3,'普通用户'),(10,'sunlei','孙磊','0000000',2,'1981-01-04','13387676765','',NULL,3,'普通用户'),(11,'sunxing','孙兴','0000000',2,'1978-03-12','13367890900','',NULL,3,'普通用户'),(12,'zhangchen','张晨','0000000',1,'1986-03-28','18098765434','',NULL,3,'普通用户'),(13,'dengchao','邓超','0000000',2,'1981-11-04','13689674534','',NULL,3,'普通用户'),(14,'yangguo','杨过','0000000',2,'1980-01-01','13388886623','',NULL,3,'普通用户'),(15,'zhaomin','赵敏','0000000',1,'1987-12-04','18099897657','',NULL,3,'普通用户'),(27,'success','成功啦','success',NULL,'2000-01-01','45332266765','C:\\Users\\Administrator\\Desktop\\21\\Login-Register-System\\target\\Login-Register-System-1.0.o-SNAPSHOT\\WEB-INF\\upload/75d34e66-ce4b-4a26-822c-fa9035e9b6d5\\hhh.jpeg','NULL',3,'普通用户'),(28,'success2',NULL,'success',NULL,NULL,NULL,NULL,'NULL',3,'普通用户'),(29,'succ',NULL,'success',NULL,NULL,NULL,NULL,'NULL',3,'普通用户'),(30,'wow',NULL,'wow',NULL,NULL,NULL,NULL,'NULL',3,'普通用户'),(31,'wowow','我啊我','wowow',NULL,'2000-01-01','45332266765','E:\\JavaWeb\\01.Login-Register-System\\Login-Register-System\\target\\Login-Register-System-1.0.o-SNAPSHOT\\WEB-INF\\upload/e97c9e1b-4709-47fd-8e4d-cc4b62c59985\\success.jpg','NULL',3,'普通用户'),(32,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'普通用户'),(33,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'普通用户');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
