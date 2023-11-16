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
-- Table structure for table `local_file`
--

DROP TABLE IF EXISTS `local_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `local_file`
(
    `id`                 bigint NOT NULL AUTO_INCREMENT,
    `created_date`       datetime(6) DEFAULT NULL,
    `original_file_name` varchar(255) DEFAULT NULL,
    `saved_file_name`    varchar(255) DEFAULT NULL,
    `saved_file_path`    varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `local_file`
--

LOCK
TABLES `local_file` WRITE;
/*!40000 ALTER TABLE `local_file` DISABLE KEYS */;
INSERT INTO `local_file`
VALUES (40, '2023-11-03 10:51:31.285359', 'herehear-dust1.png',
        'https://k9b202.p.ssafy.io/api/file/download/3384276135fb416caf037fcd77d91f5d.png',
        '/app/file/231103/3384276135fb416caf037fcd77d91f5d.png'),
       (41, '2023-11-03 10:51:53.131083', 'herehear-dust2.png',
        'https://k9b202.p.ssafy.io/api/file/download/a78b895e6786476894cfa8616d1875ba.png',
        '/app/file/231103/a78b895e6786476894cfa8616d1875ba.png'),
       (42, '2023-11-03 10:52:10.458512', 'herehear-dust3.png',
        'https://k9b202.p.ssafy.io/api/file/download/9aad9a1f5f4745ad9252ad90f60df420.png',
        '/app/file/231103/9aad9a1f5f4745ad9252ad90f60df420.png'),
       (43, '2023-11-03 10:52:22.372937', 'herehear-dust4.png',
        'https://k9b202.p.ssafy.io/api/file/download/0735acc01ef047fe929668107d0ad1eb.png',
        '/app/file/231103/0735acc01ef047fe929668107d0ad1eb.png'),
       (44, '2023-11-03 10:52:35.546516', 'herehear-dust5.png',
        'https://k9b202.p.ssafy.io/api/file/download/212bb8db683e44a28e48c3582683c539.png',
        '/app/file/231103/212bb8db683e44a28e48c3582683c539.png'),
       (45, '2023-11-03 10:52:51.599818', 'herehear-dust6.png',
        'https://k9b202.p.ssafy.io/api/file/download/893e3c5bcc984f4d9e7bc11549dc3954.png',
        '/app/file/231103/893e3c5bcc984f4d9e7bc11549dc3954.png'),
       (46, '2023-11-03 10:54:32.799638', '씨앗DJ.png',
        'https://k9b202.p.ssafy.io/api/file/download/aeb5bc3fda8047408df700beb00d97c5.png',
        '/app/file/231103/aeb5bc3fda8047408df700beb00d97c5.png'),
       (47, '2023-11-03 10:54:53.256605', '새싹DJ.png',
        'https://k9b202.p.ssafy.io/api/file/download/a7ea50a745544018a8dac7c795af4ec6.png',
        '/app/file/231103/a7ea50a745544018a8dac7c795af4ec6.png'),
       (48, '2023-11-03 10:55:04.875858', '잎새DJ.png',
        'https://k9b202.p.ssafy.io/api/file/download/c59f920da0fd4073a829fa81e44752ac.png',
        '/app/file/231103/c59f920da0fd4073a829fa81e44752ac.png'),
       (49, '2023-11-03 10:55:16.473599', '열매DJ.png',
        'https://k9b202.p.ssafy.io/api/file/download/11daa5f175a641878d2e2bee4dadda84.png',
        '/app/file/231103/11daa5f175a641878d2e2bee4dadda84.png'),
       (50, '2023-11-03 10:55:26.840051', '나무DJ.png',
        'https://k9b202.p.ssafy.io/api/file/download/b4d1cb7e36b14415a4d2504827065300.png',
        '/app/file/231103/b4d1cb7e36b14415a4d2504827065300.png'),
       (51, '2023-11-03 10:55:39.840746', '콩닥콩닥.png',
        'https://k9b202.p.ssafy.io/api/file/download/4479b0dca97443d69f109cd7412e25b7.png',
        '/app/file/231103/4479b0dca97443d69f109cd7412e25b7.png'),
       (52, '2023-11-03 10:55:50.002230', '두근두근.png',
        'https://k9b202.p.ssafy.io/api/file/download/89850c17a335486db16e86e62ebe26c6.png',
        '/app/file/231103/89850c17a335486db16e86e62ebe26c6.png'),
       (53, '2023-11-03 10:56:01.138954', '하트비트.png',
        'https://k9b202.p.ssafy.io/api/file/download/fcc5cf4459f84d96a910d60aeaa54f6a.png',
        '/app/file/231103/fcc5cf4459f84d96a910d60aeaa54f6a.png'),
       (54, '2023-11-03 10:56:12.199451', '옐로우스타.png',
        'https://k9b202.p.ssafy.io/api/file/download/a20f7b2bd8fb43dd97d4cde39fd90827.png',
        '/app/file/231103/a20f7b2bd8fb43dd97d4cde39fd90827.png'),
       (55, '2023-11-03 10:56:23.248182', '핑크스타.png',
        'https://k9b202.p.ssafy.io/api/file/download/69fb677a70524467b87dec5878c4b61f.png',
        '/app/file/231103/69fb677a70524467b87dec5878c4b61f.png'),
       (56, '2023-11-03 10:56:33.836164', '블루스타.png',
        'https://k9b202.p.ssafy.io/api/file/download/86a88f746d3d41288d528480a35f419b.png',
        '/app/file/231103/86a88f746d3d41288d528480a35f419b.png'),
       (57, '2023-11-03 10:56:43.942778', '퍼플스타.png',
        'https://k9b202.p.ssafy.io/api/file/download/e7da3929cc0046e8b46d9536fc80d643.png',
        '/app/file/231103/e7da3929cc0046e8b46d9536fc80d643.png');
/*!40000 ALTER TABLE `local_file` ENABLE KEYS */;
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
