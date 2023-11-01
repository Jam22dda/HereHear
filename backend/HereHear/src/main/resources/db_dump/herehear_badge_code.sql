-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
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
CREATE TABLE `badge_code`
(
    `badge_code` bigint      NOT NULL AUTO_INCREMENT,
    `badge_name` varchar(50) NOT NULL,
    `badge_img`  varchar(255) DEFAULT NULL,
    PRIMARY KEY (`badge_code`),
    UNIQUE KEY `UK_t5qey2100mv41hgd1x7whvpvm` (`badge_name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `badge_code`
--

LOCK
TABLES `badge_code` WRITE;
/*!40000 ALTER TABLE `badge_code` DISABLE KEYS */;
INSERT INTO `badge_code`
VALUES (1, '씨앗 DJ 뱃지', ''),
       (2, '새싹 DJ 뱃지', ''),
       (3, '잎새 DJ 뱃지', ''),
       (4, '열매 DJ 뱃지', ''),
       (5, '나무 DJ 뱃지', ''),
       (6, '콩닥콩닥 뱃지', ''),
       (7, '두근두근 뱃지', ''),
       (8, '하트비트 뱃지', ''),
       (9, 'Here? Hear! 뱃지', ''),
       (10, '00스타(색깔)-연핑크 뱃지', ''),
       (11, '00스타(색깔)-연빨강 뱃지', ''),
       (12, '00스타(색깔)-연블루 뱃지', ''),
       (13, '00스타(색깔)-퍼플 뱃지', '');
/*!40000 ALTER TABLE `badge_code` ENABLE KEYS */;
UNLOCK
TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-01  9:56:21
