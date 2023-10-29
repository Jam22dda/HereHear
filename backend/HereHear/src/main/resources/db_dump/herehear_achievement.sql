-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: k9b202.p.ssafy.io    Database: herehear
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `achievement`
--

DROP TABLE IF EXISTS `achievement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `achievement` (
  `achievement_id` bigint NOT NULL AUTO_INCREMENT,
  `badge_code` bigint DEFAULT NULL,
  `title_code` bigint DEFAULT NULL,
  `mission` text,
  PRIMARY KEY (`achievement_id`),
  UNIQUE KEY `UK_qfymwe7ow9ttl6g4tslagcx72` (`badge_code`),
  UNIQUE KEY `UK_a4rqla4g9erquq57ipom3vww9` (`title_code`),
  CONSTRAINT `FK30qlvyojsgtab6qp7hbub44ma` FOREIGN KEY (`title_code`) REFERENCES `title_code` (`title_code`),
  CONSTRAINT `FK6ljp5u4l7fjjnj06hnwjhh5ho` FOREIGN KEY (`badge_code`) REFERENCES `badge_code` (`badge_code`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achievement`
--

LOCK TABLES `achievement` WRITE;
/*!40000 ALTER TABLE `achievement` DISABLE KEYS */;
INSERT INTO `achievement` VALUES (1,1,1,'사용자가 최초로 음악을 등록 했을 때'),(2,2,2,'사용자가 10곡을 등록했을 때'),(3,3,3,'사용자가 50곡을 등록했을 때'),(4,4,4,'사용자가 100곡을 등록했을 때'),(5,5,5,'사용자가 200곡을 등록했을 때'),(6,6,6,'사용자가 등록한 음악이 좋아요를 10개 받았을 때'),(7,7,7,'사용자가 등록한 음악이 좋아요를 100개 받았을 때'),(8,8,8,'사용자가 등록한 음악이 좋아요를 500개 받았을 때'),(9,9,9,'팔로워가 10명 돌파 했을 때'),(10,10,10,'팔로워가 50명 돌파 했을 때'),(11,11,11,'팔로워가 100명 돌파 했을 때'),(12,12,12,'팔로워가 500명 돌파 했을 때'),(13,13,13,'내가 등록한 음악의 누적합 최초 100개 좋아요를 받았을 때 ');
/*!40000 ALTER TABLE `achievement` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-29 19:38:38
