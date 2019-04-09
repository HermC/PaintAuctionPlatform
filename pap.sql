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
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `src` varchar(100) NOT NULL,
  `available` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,'6f243c74-977e-4e72-beb7-2dc5edf8d34c_7P8A7966_副本.jpg',1),(2,'8c7b5738-1fc2-44fe-8762-0cfe8068cdb0_7P8A7977_副本.jpg',1),(3,'324e6618-3d3f-42ad-9db1-1e38ca852df8_7P8A7969_副本.jpg',0),(4,'ddf35b5c-e355-4a34-9695-9b406d1b0b0a_7P8A7971_副本.jpg',0),(5,'af29e8a7-82d9-4e09-b9d1-560008105735_7P8A7975_副本.jpg',0),(6,'a7b4af45-9334-4c9a-a18a-9dd04df3a8fa_7P8A7972_副本.jpg',0),(7,'3295fd18-acdb-459f-896a-61cbe8adf51f_7P8A7984_副本.jpg',0),(8,'080bef82-5238-441a-892d-b85ee9bd9234_7P8A7979_副本.jpg',0),(9,'5a1ab1c2-4e51-4518-b580-6073c3966317_7P8A7995_副本.jpg',0),(10,'b4f1ad33-aca6-494f-98a4-c3d2a7103b11_7P8A7987_副本.jpg',0),(11,'7493f95f-6ede-431a-a2b6-1927bf6f07de_7P8A7998_副本.jpg',0),(12,'839c6f2b-c1ce-485f-8c87-2aa9327bdd3a_7P8A7990_副本.jpg',0),(13,'455dc5f3-7b81-48c8-aadd-8d82eaa69016_7P8A7996_副本.jpg',0),(14,'2b8977e8-5e6c-4d2d-91d6-8e242f145366_7P8A7992_副本.jpg',0),(15,'af0056c0-1b1d-4f17-8066-4ca6b5a3001f_7P8A7999_副本.jpg',0),(16,'ddb59c3f-e3c9-4107-b58e-0a630f68f5e6_7P8A7981_副本.jpg',0),(17,'aaef2ce6-fb76-4a6b-8c53-28deb96918a9_7P8A8006_副本.jpg',0),(18,'7b255f14-e6f7-4bd7-adc7-07c2bd9c3410_7P8A8004_副本.jpg',0),(19,'5f1f78e1-193f-4835-a418-38eaf1ddfb4c_7P8A8008_副本.jpg',0),(20,'cc5d83f5-ec15-4832-9d80-02191275bf2e_7P8A8019_副本.jpg',0),(21,'0b86b162-3137-4cb4-983f-21331057923f_7P8A8021_副本.jpg',0),(22,'689b876e-2901-4458-8d83-43546ca62c28_7P8A8002_副本.jpg',0),(23,'b4f2c7cb-d44e-4d73-b204-4cbb1bf99e76_7P8A8025_副本.jpg',0),(24,'1882280e-a8bd-4574-a8d5-6fe26ab5dc55_7P8A8018_副本.jpg',0),(25,'d75dc033-7e09-45c0-91d5-b54ba5e67bc4_7P8A8023_副本.jpg',0),(26,'7d0257e6-e17d-4b0b-9279-fb43ce192625_7P8A8029_副本.jpg',0),(27,'85715bd8-1153-4cd6-82f8-04ed2c3d7c14_7P8A8032_副本.jpg',0),(28,'d9487df8-b91e-4024-bee3-e4ce7f3534d9_7P8A8036_副本.png',0),(29,'f1cf9d4d-1b82-410e-9aa4-f545607153e7_7P8A8043_副本.jpg',0),(30,'37cc0fe6-3d7d-4869-ab0d-e570a5df8d41_7P8A8039_副本.jpg',0),(31,'e80cb0aa-4305-40a9-b3c2-3f1e0e8547af_7P8A8011_副本.png',0),(32,'5b6393be-3809-4360-899b-2697c0e90816_7P8A8059_副本.jpg',0),(33,'bee9a077-d895-4f5c-8e96-942c9d622aa9_7P8A8009_副本.jpg',0),(34,'59f5d7bf-294b-457e-9221-4c99ded8cdb1_7P8A8061_副本.jpg',0),(35,'06ebf3be-4ec7-4596-ab75-9ef0b6753ede_7P8A8051_副本.jpg',0),(36,'a8205a0a-a73d-416c-9ce8-247b3c4d84e8_7P8A8012_副本.jpg',0),(37,'b81db11a-08f7-4c23-9954-2a5692591b2f_7P8A8069_副本.jpg',0),(38,'9656fd58-612c-4592-b384-a55c85ee8704_7P8A8065_副本.jpg',0),(39,'e365cd10-7182-4707-9eb5-a4e970d58666_7P8A8074_副本.jpg',0),(40,'46e25cfc-b327-4543-bd1c-ae1cccad9677_7P8A8076_副本.jpg',0),(41,'0477edc3-0c1a-4d4b-bb15-3177326defd8_7P8A8078_副本.jpg',0),(42,'3ce97f48-b9c4-43d5-95ac-10d7c632aa46_7P8A8100_副本.jpg',0),(43,'0cc56bc1-ee3e-4f9d-8c8d-296aaa239c11_7P8A8096_副本.jpg',0),(44,'4c373603-18dc-490c-91e5-f6dabcff8fd0_7P8A8098_副本.jpg',0),(45,'49ece5c1-19a3-4858-a5c7-11d7da84f40d_7P8A8072_副本.jpg',0),(46,'5e8f866a-beb5-43f8-b828-f6abb3fe0160_7P8A8111_副本.jpg',0),(47,'6b2108dc-5859-40e3-96ad-f94ccda734cd_7P8A8063_副本.jpg',0),(48,'f1707622-2107-48d5-b49e-db4fd6f6394a_7P8A8112_副本.jpg',0),(49,'3d8a8dba-7ffd-4fe8-aee4-7587328732f6_7P8A8117_副本.png',0),(50,'235ddbcd-43db-44c5-b256-2bb56349a864_BodyForCrop1.png',0);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image_order`
--

DROP TABLE IF EXISTS `image_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `imageid` int(11) NOT NULL,
  `recipient` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `address` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image_order`
--

LOCK TABLES `image_order` WRITE;
/*!40000 ALTER TABLE `image_order` DISABLE KEYS */;
INSERT INTO `image_order` VALUES (1,1,1,'yzy','13813375770','test'),(2,1,2,'yzy','123456789012','xzx');
/*!40000 ALTER TABLE `image_order` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,1,'ADMIN'),(2,2,'USER'),(3,3,'USER');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','Pychen_insert@gmail.com',NULL,'b15de6edcf6e07d121b439fe901be97e','cdace9b1f73d9ef4906f',0,'2019-02-01 00:14:03','2019-02-01 00:14:03'),(2,'user_test','user_test@126.com',NULL,'295e293a3b46d1e40e7b119100c65750','75cb2153c766f915d4c4',0,'2019-02-01 06:55:07','2019-02-01 06:55:07'),(3,'user_test2','yzy627@126.com',NULL,'94b2382e2ce8db68d704177f02b6580b','e6d322725e4359dc8c5e',0,'2019-02-02 22:19:24','2019-02-02 22:19:25');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-09 15:05:29
