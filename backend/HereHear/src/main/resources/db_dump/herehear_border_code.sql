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
-- Table structure for table `border_code`
--

DROP TABLE IF EXISTS `border_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `border_code` (
  `border_code` bigint NOT NULL AUTO_INCREMENT,
  `border_name` varchar(50) NOT NULL,
  `border_img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`border_code`),
  UNIQUE KEY `UK_n4vs4yhgucqamg2lc7lrdno35` (`border_name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `border_code`
--

LOCK TABLES `border_code` WRITE;
/*!40000 ALTER TABLE `border_code` DISABLE KEYS */;
INSERT INTO `border_code` VALUES (1,'씨앗 DJ 테두리',''),(2,'새싹 DJ 테두리',''),(3,'잎새 DJ 테두리',''),(4,'열매 DJ 테두리',''),(5,'나무 DJ 테두리',''),(6,'콩닥콩닥 테두리',''),(7,'하트비트 테두리',''),(8,'Here ? Hear! 테두리',''),(9,'00스타(색깔)-연핑크 테두리',''),(10,'00스타(색깔)-연빨강 테두리',''),(11,'00스타(색깔)-연블루 테두리',''),(12,'00스타(색깔)-연보라 테두리','');
/*!40000 ALTER TABLE `border_code` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-25 20:42:56
