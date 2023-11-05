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
-- Table structure for table `badge_code`
--

DROP TABLE IF EXISTS `badge_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `badge_code` (
  `badge_code` bigint NOT NULL AUTO_INCREMENT,
  `badge_name` varchar(50) NOT NULL,
  `badge_img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`badge_code`),
  UNIQUE KEY `UK_t5qey2100mv41hgd1x7whvpvm` (`badge_name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `badge_code`
--

LOCK TABLES `badge_code` WRITE;
/*!40000 ALTER TABLE `badge_code` DISABLE KEYS */;
INSERT INTO `badge_code` VALUES (1,'씨앗 DJ 뱃지','https://k9b202.p.ssafy.io/api/file/download/aeb5bc3fda8047408df700beb00d97c5.png'),(2,'새싹 DJ 뱃지','https://k9b202.p.ssafy.io/api/file/download/a7ea50a745544018a8dac7c795af4ec6.png'),(3,'잎새 DJ 뱃지','https://k9b202.p.ssafy.io/api/file/download/c59f920da0fd4073a829fa81e44752ac.png'),(4,'열매 DJ 뱃지','https://k9b202.p.ssafy.io/api/file/download/11daa5f175a641878d2e2bee4dadda84.png'),(5,'나무 DJ 뱃지','https://k9b202.p.ssafy.io/api/file/download/b4d1cb7e36b14415a4d2504827065300.png'),(6,'콩닥콩닥 뱃지','https://k9b202.p.ssafy.io/api/file/download/4479b0dca97443d69f109cd7412e25b7.png'),(7,'두근두근 뱃지','https://k9b202.p.ssafy.io/api/file/download/89850c17a335486db16e86e62ebe26c6.png'),(8,'하트비트 뱃지','https://k9b202.p.ssafy.io/api/file/download/fcc5cf4459f84d96a910d60aeaa54f6a.png'),(9,'Here? Hear! 뱃지',''),(10,'옐로우스타 뱃지','https://k9b202.p.ssafy.io/api/file/download/a20f7b2bd8fb43dd97d4cde39fd90827.png'),(11,'핑크스타 뱃지','https://k9b202.p.ssafy.io/api/file/download/69fb677a70524467b87dec5878c4b61f.png'),(12,'블루스타 뱃지','https://k9b202.p.ssafy.io/api/file/download/86a88f746d3d41288d528480a35f419b.png'),(13,'퍼플스타 뱃지','https://k9b202.p.ssafy.io/api/file/download/e7da3929cc0046e8b46d9536fc80d643.png');
/*!40000 ALTER TABLE `badge_code` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-05 21:20:38
