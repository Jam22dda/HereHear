-- --------------------------------------------------------
-- 호스트:                          k9b202.p.ssafy.io
-- 서버 버전:                        8.1.0 - MySQL Community Server - GPL
-- 서버 OS:                        Linux
-- HeidiSQL 버전:                  12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- herehear 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `herehear` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `herehear`;

-- 테이블 herehear.music_occasion 구조 내보내기
CREATE TABLE IF NOT EXISTS `music_occasion` (
  `music_occasion_id` bigint NOT NULL AUTO_INCREMENT,
  `occasion_code` bigint DEFAULT NULL,
  `registered_music_id` bigint DEFAULT NULL,
  PRIMARY KEY (`music_occasion_id`),
  KEY `FKkeigf8j8n93gg7v68c5vgef0n` (`occasion_code`),
  KEY `FKofb3hl0hhba1fm11ps7qg12ju` (`registered_music_id`),
  CONSTRAINT `FKkeigf8j8n93gg7v68c5vgef0n` FOREIGN KEY (`occasion_code`) REFERENCES `occasion` (`occasion_code`),
  CONSTRAINT `FKofb3hl0hhba1fm11ps7qg12ju` FOREIGN KEY (`registered_music_id`) REFERENCES `registered_music` (`registered_music_id`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 herehear.music_occasion:~119 rows (대략적) 내보내기
INSERT INTO `music_occasion` (`music_occasion_id`, `occasion_code`, `registered_music_id`) VALUES
	(1, 9, 2),
	(2, 17, 2),
	(3, 5, 3),
	(4, 14, 3),
	(5, 20, 3),
	(6, 5, 4),
	(7, 13, 4),
	(8, 18, 5),
	(9, 7, 5),
	(10, 3, 5),
	(11, 12, 6),
	(12, 7, 6),
	(13, 10, 6),
	(14, 10, 7),
	(15, 17, 7),
	(16, 1, 7),
	(17, 15, 8),
	(18, 11, 8),
	(19, 10, 8),
	(20, 5, 9),
	(21, 9, 9),
	(22, 14, 9),
	(23, 5, 10),
	(24, 3, 10),
	(25, 15, 10),
	(26, 10, 11),
	(27, 9, 12),
	(28, 5, 12),
	(29, 8, 12),
	(30, 5, 13),
	(31, 14, 13),
	(32, 5, 14),
	(33, 14, 14),
	(34, 18, 15),
	(35, 5, 15),
	(36, 1, 15),
	(37, 5, 16),
	(38, 17, 16),
	(39, 12, 17),
	(40, 4, 17),
	(41, 23, 17),
	(42, 5, 18),
	(43, 4, 18),
	(44, 13, 18),
	(45, 7, 19),
	(46, 11, 19),
	(47, 3, 19),
	(48, 20, 20),
	(49, 14, 20),
	(50, 4, 20),
	(51, 2, 21),
	(52, 13, 21),
	(53, 11, 21),
	(54, 7, 22),
	(55, 18, 22),
	(56, 4, 22),
	(57, 5, 23),
	(58, 10, 23),
	(59, 13, 23),
	(60, 13, 24),
	(61, 4, 24),
	(62, 7, 24),
	(63, 5, 25),
	(64, 5, 26),
	(65, 7, 26),
	(66, 14, 26),
	(67, 5, 27),
	(68, 7, 27),
	(69, 14, 27),
	(70, 7, 28),
	(71, 11, 28),
	(72, 18, 28),
	(73, 7, 29),
	(74, 11, 29),
	(75, 18, 29),
	(76, 7, 30),
	(77, 10, 30),
	(78, 13, 30),
	(79, 5, 31),
	(80, 7, 31),
	(81, 17, 31),
	(82, 7, 32),
	(83, 11, 32),
	(84, 19, 32),
	(85, 11, 33),
	(86, 7, 33),
	(87, 15, 33),
	(88, 7, 34),
	(89, 15, 34),
	(90, 25, 34),
	(91, 7, 35),
	(92, 15, 35),
	(93, 25, 35),
	(94, 7, 36),
	(95, 2, 36),
	(96, 17, 36),
	(97, 10, 37),
	(98, 13, 37),
	(99, 21, 37),
	(100, 7, 38),
	(101, 14, 38),
	(102, 18, 38),
	(103, 5, 39),
	(104, 17, 39),
	(105, 2, 40),
	(106, 7, 40),
	(107, 18, 40),
	(108, 2, 41),
	(109, 7, 41),
	(110, 18, 41),
	(111, 1, 42),
	(112, 11, 42),
	(113, 7, 42),
	(114, 1, 43),
	(115, 7, 43),
	(116, 18, 43),
	(117, 18, 45),
	(118, 7, 45),
	(119, 26, 45);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
