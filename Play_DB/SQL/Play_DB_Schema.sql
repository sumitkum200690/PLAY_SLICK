/*
SQLyog Community v9.20 Beta2
MySQL - 5.6.21 : Database - play
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`play` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `play`;

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(254) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `department` */

insert  into `department`(`id`,`name`) values (1,'ALM'),(2,'AHALIFE'),(3,'PTPORTAL'),(4,'BLUEHORNET'),(5,'FINDAGRAVE');

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(254) NOT NULL,
  `dept_id` bigint(20) NOT NULL,
  `isManager` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `DEPT_FK` (`dept_id`),
  CONSTRAINT `DEPT_FK` FOREIGN KEY (`dept_id`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `employee` */

insert  into `employee`(`id`,`name`,`dept_id`,`isManager`) values (1,'Sumit',1,0),(2,'Prashant',1,1),(3,'Rucha',2,0),(4,'Varghese',2,1),(5,'Tanya',3,0),(6,'Shreerang',3,1),(7,'Abhijit',4,0),(8,'Manoj',4,1),(9,'Pushpendra',5,0),(10,'JK',5,1),(11,'Priya',1,0),(12,'Sudhanshu',3,0);

/*Table structure for table `manager` */

DROP TABLE IF EXISTS `manager`;

CREATE TABLE `manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emp_id` bigint(20) NOT NULL,
  `reportee_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `DEPT-ID_FK` (`reportee_id`),
  KEY `EMP-ID_FK` (`emp_id`),
  CONSTRAINT `DEPT-ID_FK` FOREIGN KEY (`reportee_id`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `EMP-ID_FK` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `manager` */

insert  into `manager`(`id`,`emp_id`,`reportee_id`) values (1,2,1),(2,2,11),(3,6,5),(4,6,12),(5,1,4);

/*Table structure for table `task` */

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `id` int(100) DEFAULT NULL,
  `label` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `task` */

insert  into `task`(`id`,`label`) values (1,'MyTask1'),(1,'MyTask1'),(1,'CybageTask'),(1,'after setup for database first time'),(1,'asf');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
