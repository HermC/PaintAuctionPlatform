-- MySQL dump 10.13  Distrib 5.7.23, for macos10.13 (x86_64)
--
-- Host: localhost    Database: PaintAuctionPlatform
-- ------------------------------------------------------
-- Server version	5.7.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `lot`
--

DROP TABLE IF EXISTS `lot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lot` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `type` varchar(100) DEFAULT NULL,
  `description` text,
  `starting_price` double NOT NULL,
  `starting_time` datetime NOT NULL,
  `ending_time` datetime NOT NULL,
  `ending_price` double DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lot`
--

LOCK TABLES `lot` WRITE;
/*!40000 ALTER TABLE `lot` DISABLE KEYS */;
INSERT INTO `lot` VALUES (1,'测试拍品1',NULL,NULL,100,'2019-01-19 18:00:00','2019-01-19 20:00:00',NULL,'2019-02-01 00:22:31','2019-02-01 00:22:31'),(2,'测试拍品2',NULL,NULL,100,'2019-02-04 18:00:00','2019-02-04 20:00:00',NULL,'2019-02-01 00:22:31','2019-02-01 00:22:31'),(6,'测试提交',NULL,'测试',100,'2019-02-14 10:00:00','2019-02-14 11:00:01',NULL,'2019-02-02 01:54:44','2019-02-02 01:54:44'),(7,'测试提交2',NULL,NULL,100,'2019-02-19 10:00:00','2019-02-19 10:00:01',NULL,'2019-02-02 01:58:11','2019-02-02 01:58:11'),(8,'测试3',NULL,'测试',100,'2018-02-19 10:00:00','2018-02-19 11:00:00',NULL,'2019-02-02 02:04:12','2019-02-02 02:04:12'),(9,'测试拍品4',NULL,'测试',200,'2019-02-22 10:00:00','2019-02-22 11:00:00',NULL,'2019-02-02 02:05:09','2019-02-02 02:05:09');
/*!40000 ALTER TABLE `lot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lot_img`
--

DROP TABLE IF EXISTS `lot_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lot_img` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `lotid` int(10) NOT NULL,
  `img_src` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lot_img`
--

LOCK TABLES `lot_img` WRITE;
/*!40000 ALTER TABLE `lot_img` DISABLE KEYS */;
INSERT INTO `lot_img` VALUES (1,1,'WechatIMG369.jpeg'),(2,1,'WechatIMG369.jpeg'),(3,2,'WechatIMG369.jpeg'),(4,2,'WechatIMG369.jpeg'),(10,6,'66c2984f-2b14-4ada-8bbb-633d234fb3b1_7_1_4c29a21c-d84f-11e8-b8e6-acde48001122.jpg'),(11,7,'fec2460c-e23b-4089-b5e7-9e1d393a0b24_7_1_0edae92c-d84d-11e8-b26c-acde48001122.jpg'),(12,8,'37b2e6f5-88f5-4717-8403-01382bb637d8_7_1_8d0f561c-d84c-11e8-94cf-acde48001122.jpg'),(13,9,'eb10610e-bfb9-45ed-8e88-d9be9e9a4f85_7_1_2fa985ac-d84b-11e8-b259-acde48001122.jpg'),(14,9,'633b4e4b-d0ea-4908-ab8b-d58f01c5a1e9_7_1_8caeea4a-d84f-11e8-8bb3-acde48001122.jpg');
/*!40000 ALTER TABLE `lot_img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userid` int(10) NOT NULL COMMENT '用户表用户ID',
  `role` enum('USER','ADMIN') NOT NULL DEFAULT 'USER' COMMENT '用户角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,1,'ADMIN'),(2,2,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(128) NOT NULL COMMENT '用户名',
  `email` varchar(128) NOT NULL COMMENT '邮箱',
  `avatar` varchar(128) DEFAULT NULL,
  `password` varchar(256) NOT NULL COMMENT '密码',
  `salt` varchar(64) NOT NULL COMMENT '加密salt',
  `ban` int(11) NOT NULL COMMENT '是否废弃',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','Pychen_insert@gmail.com',NULL,'b15de6edcf6e07d121b439fe901be97e','cdace9b1f73d9ef4906f',0,'2019-02-01 00:14:03','2019-02-01 00:14:03'),(2,'user_test','user_test@126.com',NULL,'295e293a3b46d1e40e7b119100c65750','75cb2153c766f915d4c4',0,'2019-02-01 06:55:07','2019-02-01 06:55:07');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_lot`
--

DROP TABLE IF EXISTS `user_lot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_lot` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userid` int(10) NOT NULL,
  `lotid` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_lot`
--

LOCK TABLES `user_lot` WRITE;
/*!40000 ALTER TABLE `user_lot` DISABLE KEYS */;
INSERT INTO `user_lot` VALUES (1,1,1),(2,2,6),(3,2,7),(4,2,8),(5,2,9);
/*!40000 ALTER TABLE `user_lot` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-02 19:37:55
