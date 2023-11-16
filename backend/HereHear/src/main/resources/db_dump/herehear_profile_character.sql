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
-- Table structure for table `profile_character`
--

DROP TABLE IF EXISTS `profile_character`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile_character`
(
    `profile_character_id` bigint NOT NULL AUTO_INCREMENT,
    `character_image`      varchar(255) DEFAULT NULL,
    `character_name`       varchar(255) DEFAULT NULL,
    PRIMARY KEY (`profile_character_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile_character`
--

LOCK
TABLES `profile_character` WRITE;
/*!40000 ALTER TABLE `profile_character` DISABLE KEYS */;
INSERT INTO `profile_character`
VALUES (1, 'https://k9b202.p.ssafy.io/api/file/download/3384276135fb416caf037fcd77d91f5d.png', '히어먼지'),
       (2, 'https://k9b202.p.ssafy.io/api/file/download/a78b895e6786476894cfa8616d1875ba.png', '힙합먼지'),
       (3, 'https://k9b202.p.ssafy.io/api/file/download/212bb8db683e44a28e48c3582683c539.png', '공부먼지'),
       (4, 'https://k9b202.p.ssafy.io/api/file/download/0735acc01ef047fe929668107d0ad1eb.png', '꿀잠먼지'),
       (5, 'https://k9b202.p.ssafy.io/api/file/download/9aad9a1f5f4745ad9252ad90f60df420.png', '운동먼지'),
       (6, 'https://k9b202.p.ssafy.io/api/file/download/893e3c5bcc984f4d9e7bc11549dc3954.png', '열정먼지');
/*!40000 ALTER TABLE `profile_character` ENABLE KEYS */;
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

-- Dump completed on 2023-11-05 21:20:40
