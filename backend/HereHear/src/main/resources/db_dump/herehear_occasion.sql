-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        8.0.32 - MySQL Community Server - GPL
-- 서버 OS:                        Win64
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
CREATE DATABASE IF NOT EXISTS `herehear` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `herehear`;

-- 테이블 herehear.occasion 구조 내보내기
CREATE TABLE IF NOT EXISTS `occasion` (
  `occasion_code` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(20) COLLATE utf8mb3_unicode_ci NOT NULL,
  `occasion_name` varchar(20) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`occasion_code`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

-- 테이블 데이터 herehear.occasion:~27 rows (대략적) 내보내기
INSERT INTO `occasion` (`occasion_code`, `category`, `occasion_name`) VALUES
	(1, 'occasion', '출근'),
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

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
