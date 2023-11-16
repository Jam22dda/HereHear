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
-- Table structure for table `occasion`
--

DROP TABLE IF EXISTS `occasion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `occasion`
(
    `occasion_code` bigint      NOT NULL AUTO_INCREMENT,
    `category`      varchar(20) NOT NULL,
    `occasion_name` varchar(20) NOT NULL,
    PRIMARY KEY (`occasion_code`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `occasion`
--

LOCK
TABLES `occasion` WRITE;
/*!40000 ALTER TABLE `occasion` DISABLE KEYS */;
INSERT INTO `occasion`
VALUES (1, 'occasion', '출근'),
       (2, 'occasion', '퇴근'),
       (3, 'occasion', '주말'),
       (4, 'occasion', '청량'),
       (5, 'occasion', '감성'),
       (6, 'occasion', '집중'),
       (7, 'occasion', '신나는'),
       (8, 'occasion', '우울'),
       (9, 'occasion', '이별'),
       (10, 'occasion', '힐링'),
       (11, 'occasion', '열정'),
       (12, 'environment', '봄'),
       (13, 'environment', '여름'),
       (14, 'environment', '가을'),
       (15, 'environment', '겨울'),
       (16, 'environment', '눈'),
       (17, 'environment', '비'),
       (18, 'environment', '맑음'),
       (19, 'activity', '운동'),
       (20, 'activity', '산책'),
       (21, 'activity', '수면'),
       (22, 'activity', '독서'),
       (23, 'activity', '공부'),
       (24, 'activity', '운전'),
       (25, 'activity', '샤워'),
       (26, 'activity', '여행'),
       (27, 'activity', '업무');
/*!40000 ALTER TABLE `occasion` ENABLE KEYS */;
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

-- Dump completed on 2023-11-05 21:20:37
