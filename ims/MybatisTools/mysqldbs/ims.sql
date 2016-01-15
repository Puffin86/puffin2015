/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.12 : Database - ims
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ims` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ims`;

/*Table structure for table `part_t` */

DROP TABLE IF EXISTS `part_t`;

CREATE TABLE `part_t` (
  `name` varchar(100) DEFAULT NULL,
  `code` varchar(20) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `part_t` */

insert  into `part_t`(`name`,`code`) values ('a','a'),('b','b'),('c','c'),('d','d');

/*Table structure for table `sale_t` */

DROP TABLE IF EXISTS `sale_t`;

CREATE TABLE `sale_t` (
  `code` varchar(200) NOT NULL,
  `sales` int(11) DEFAULT '0',
  PRIMARY KEY (`code`),
  CONSTRAINT `sale_t_ibfk_1` FOREIGN KEY (`code`) REFERENCES `part_t` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sale_t` */

insert  into `sale_t`(`code`,`sales`) values ('a',100),('b',200),('c',300),('d',400);

/*Table structure for table `user_t` */

DROP TABLE IF EXISTS `user_t`;

CREATE TABLE `user_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(40) NOT NULL,
  `password` varchar(255) NOT NULL,
  `age` int(4) NOT NULL,
  `part` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `part` (`part`),
  CONSTRAINT `user_t_ibfk_1` FOREIGN KEY (`part`) REFERENCES `part_t` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `user_t` */

insert  into `user_t`(`id`,`user_name`,`password`,`age`,`part`) values (1,'测试12345','sfasgfaf',24,'a'),(2,'qwe','asd',21,'b'),(3,'ads','sadf',11,'a'),(4,'asdf','asdf',56,'c'),(5,'23','asdf',14,'b');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
