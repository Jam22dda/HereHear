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

-- 테이블 herehear.registered_music 구조 내보내기
CREATE TABLE IF NOT EXISTS `registered_music` (
  `is_deleted` bit(1) DEFAULT NULL,
  `lat` double NOT NULL,
  `lng` double NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `member_id` bigint NOT NULL,
  `registered_music_id` bigint NOT NULL AUTO_INCREMENT,
  `release_time` varchar(10) DEFAULT NULL,
  `singer` varchar(50) NOT NULL,
  `comment` varchar(100) DEFAULT NULL,
  `subject` varchar(100) NOT NULL,
  `album_img` varchar(255) NOT NULL,
  PRIMARY KEY (`registered_music_id`),
  KEY `FK9txh4mkxgismxwhl8q7oe4pnj` (`member_id`),
  CONSTRAINT `FK9txh4mkxgismxwhl8q7oe4pnj` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 herehear.registered_music:~44 rows (대략적) 내보내기
INSERT INTO `registered_music` (`is_deleted`, `lat`, `lng`, `create_time`, `member_id`, `registered_music_id`, `release_time`, `singer`, `comment`, `subject`, `album_img`) VALUES
	(NULL, 37.5518911, 126.9917937, '2023-10-09 09:23:19.066541', 1, 2, '2007-06-08', 'FTISLAND', '그때.. 그녀.. 보고싶다..', 'Love Sick', 'https://i.scdn.co/image/ab67616d0000b27346d0274f74c94c04aa4c9474'),
	(NULL, 37.545012, 126.980487, '2023-10-19 10:33:35.217981', 1, 3, '2019-08-12', 'Paul Kim', '안녕 이제는 안녕 그대가 있는 삶..', '안녕', 'https://i.scdn.co/image/ab67616d0000b273a00e368468a3598608c27215'),
	(NULL, 37.5502, 126.9893, '2023-10-08 11:37:06.627996', 2, 4, '2017-03-24', 'IU', '아름다운 야경과 함께 밤을', 'Through the Night', 'https://i.scdn.co/image/ab67616d0000b273582ef1f9cf429f07ee914a89'),
	(NULL, 37.5341, 126.9822, '2023-10-24 12:40:07.155676', 2, 5, '2017-09-02', '10cm', '맛있는 음식과 함께 음악을 즐기며 특별한 순간을 만끽하는 중', 'Phonecert', 'https://i.scdn.co/image/ab67616d0000b273752a6acc4dda7b0defca3d6f'),
	(NULL, 37.5758, 126.9762, '2023-10-20 13:44:56.386104', 1, 6, '2019-04-02', 'BOL4', '설렘설렘 봄이 오는 소리인가', 'Bom', 'https://i.scdn.co/image/ab67616d0000b273f7020ce7883a6f09b81ae8e1'),
	(NULL, 37.5693, 126.9707, '2023-10-01 14:48:49.656424', 2, 7, '2008-04-17', 'Epik High', ' 비 오는 날에 듣기 좋은 노래', '우산 (feat. 윤하)', 'https://i.scdn.co/image/ab67616d0000b27338e8263b51bec04b91143b76'),
	(NULL, 35.2056016, 126.8080098, '2023-10-10 15:49:53.706673', 6, 8, '2022-08-01', 'NewJeans', '신나는 노래 !! 뉴진스 노래 들으며 모두 목요팅 !', 'Hype Boy', 'https://i.scdn.co/image/ab67616d0000b2739d28fd01859073a3ae6ea209'),
	(NULL, 36.3679, 127.3845, '2023-10-28 16:52:54.077318', 6, 9, '2016-05-27', 'Urban Zakapa', '아름다운 자연 환경을 보는데 왜 눈물이 나지..', 'I Don\'t Love You', 'https://i.scdn.co/image/ab67616d0000b2735ec745a0233c62f0e3b71964'),
	(NULL, 36.35, 127.3847, '2023-11-09 17:54:01.554421', 4, 10, '2016-03-10', 'Gummy', '태양의 후예 볼때 듣고 진짜 감동한 노래 여기서 들으니 그때 생각난다', 'You Are My Everything', 'https://i.scdn.co/image/ab67616d0000b273f0a6aae96d2d73108b16092e'),
	(NULL, 36.3689, 127.3944, '2023-11-10 18:54:48.066919', 4, 11, '2017-09-29', 'Punch', ' 편안한 분위기에서 노래를 즐기기 좋은 곡', 'When Night Is Falling', 'https://i.scdn.co/image/ab67616d0000b2735d53ca1d0051cb7b16e7beed'),
	(NULL, 36.367, 127.3815, '2023-11-15 19:03:44.294256', 3, 12, '2018-12-16', '안녕? 난 건이라고해~ 독수리 건', '아직 못 해준게 많은데.. 왜 넌 없는거니..', '가지마 가지마 (안녕? 난 건이라고해~ 독수리 건)', 'https://i.scdn.co/image/ab67616d0000b273a839d7b5f3ae55923d405961'),
	(NULL, 37.5147, 127.0585, '2023-10-19 15:07:34.845906', 3, 13, '2017-04-21', 'IU', '이 밤 그날에 당신을 떠오르게 하는 노래 ', 'Through the Night', 'https://i.scdn.co/image/ab67616d0000b273c06f0e8b33ac2d246158253e'),
	(NULL, 37.513, 127.0527, '2023-10-20 14:08:44.357953', 3, 14, '2007-01-29', 'Park Hyo Shin', '너무 아픈 사랑은 사랑이 아니었음을.. 너무 고단한 사랑은 사랑이 아니었음을..', '추억은 사랑을 닮아', 'https://i.scdn.co/image/ab67616d0000b27364a3d3330efb392beae949aa'),
	(NULL, 37.4988, 127.0383, '2023-10-26 13:09:58.315106', 1, 15, '2014-02-12', 'Sung Si Kyung', '너의 모든 순간, 너의 눈부신 순간, 그 모든 순간, 나의 행복이었어', 'Every moment of you', 'https://i.scdn.co/image/ab67616d0000b2736d164a3bd7d2abc11aedbbf8'),
	(NULL, 37.5007616, 127.0382454, '2023-10-22 12:14:48.079229', 3, 16, '2011-08-29', 'DAVICHI', '이별과 그리움을 담은 감성적인 곡 감성터지는 밤', '안녕이라고 말하지마', 'https://i.scdn.co/image/ab67616d0000b273da243fb0cf640656e4b7d2d2'),
	(NULL, 37.5014656, 127.0395271, '2023-09-19 08:16:53.597447', 6, 17, '2014-04-08', 'HIGH4', '봄에 취한다..', 'Not Spring, Love, or Cherry Blossoms', 'https://i.scdn.co/image/ab67616d0000b273762badbc5b89a2fa65d13f67'),
	(NULL, 37.5013068, 127.0396597, '2023-09-29 09:18:04.487474', 6, 18, '2014-11-19', '10cm', '이건 세상에서 제일 감동적인 노래!!', '스토커 Stalker', 'https://i.scdn.co/image/ab67616d0000b273b4ccee7db9489e654726e884'),
	(NULL, 36.3528192, 127.30368, '2023-10-09 10:41:14.973650', 6, 19, '2005-04-19', 'Izi', '응급실', '응급실', 'https://i.scdn.co/image/ab67616d0000b2733188f838e26ff0d3fc10009c'),
	(NULL, 36.3654, 127.3475, '2023-10-10 11:43:21.895747', 3, 20, '2021-07-26', 'AKMU', '그러니까 네 눈을 감아 충남대학교 캠퍼스를 거닐며..', 'NAKKA (with IU)', 'https://i.scdn.co/image/ab67616d0000b27355d0265cc488deebe40d79a6'),
	(NULL, 35.0941899, 128.8579432, '2023-10-15 12:47:12.163739', 3, 21, '2017-03-07', 'Brave Girls', '에너지 넘치게!! 시작하는길!!!', 'Rollin\'', 'https://i.scdn.co/image/ab67616d0000b27373d59cc449e4592026c3997a'),
	(NULL, 36.1081844, 128.4139686, '2023-10-17 13:47:57.571579', 3, 22, '2021-05-14', 'Lee Mujin', '붉은색 푸른색~ 때마침 내 앞에 신호등이!!', 'Traffic light', 'https://i.scdn.co/image/ab67616d0000b273aae78727e396da9f03032eda'),
	(NULL, 36.1099766, 128.4149416, '2023-11-19 14:48:23.563455', 3, 23, '2020-03-27', 'CHO JUNG SEOK', '슬기로운 의사생활 띵작이었지..', 'Aloha', 'https://i.scdn.co/image/ab67616d0000b2736fa3ae4086a3822771daec6d'),
	(NULL, 36.1083819, 128.4168822, '2023-10-21 15:49:02.800677', 3, 24, '2021-06-04', 'BTS', 'BTS는 언제 들어도 씐나', 'Butter', 'https://i.scdn.co/image/ab67616d0000b273240447f2da1433d8f4303596'),
	(NULL, 36.1084839, 128.4116766, '2023-10-26 16:51:14.230503', 4, 25, '2017-03-09', 'Jukjae', '감수성에 젖어든다..', '별 보러 가자', 'https://i.scdn.co/image/ab67616d0000b27394ae7adcaa0a26f4c7acba86'),
	(NULL, 36.1079753, 128.418512, '2023-10-23 17:51:57.487735', 4, 26, '2021-11-11', 'Bruno Mars', 'R&B 음악은 진짜 이거 강력 추천', 'Leave The Door Open', 'https://i.scdn.co/image/ab67616d0000b273fcf75ead8a32ac0020d2ce86'),
	(NULL, 36.355306, 127.2983061, '2023-10-24 18:52:34.008679', 2, 27, '2021-05-10', 'OH MY GIRL', '상큼 상큼 둠칫둠칫!', 'Dun Dun Dance', 'https://i.scdn.co/image/ab67616d0000b27304d1fa0ab8be50437e6bad1d'),
	(NULL, 36.3549777, 127.2983403, '2023-10-28 19:54:16.838258', 2, 28, '2020-10-02', 'BLACKPINK', '강렬한 비트에 흥이 절로 납니다.', 'How You Like That', 'https://i.scdn.co/image/ab67616d0000b2737dd8f95320e8ef08aa121dfe'),
	(NULL, 36.3534478, 127.2997103, '2023-10-15 20:54:33.118108', 2, 29, '2021-06-11', 'TWICE', '여름에 어울리는 상큼하고 경쾌한 곡', 'Alcohol-Free', 'https://i.scdn.co/image/ab67616d0000b273feede28e85bb57807a272a2b'),
	(NULL, 35.2042682, 126.808448, '2023-11-08 21:55:09.692288', 2, 30, '2021-03-25', 'IU', '아이유의 독보적인 음색과 긍정적인 메시지를 담고 있는 곡', 'Celebrity', 'https://i.scdn.co/image/ab67616d0000b2734ed058b71650a6ca2c04adff'),
	(NULL, 35.2054898, 126.8058527, '2023-11-09 09:56:32.907640', 4, 31, '2005-02-07', 'Michael Bublé', '감성과 에너지를 모두 담은 곡', 'Feeling Good', 'https://i.scdn.co/image/ab67616d0000b2735d7d966732bd44ac2a13b614'),
	(NULL, 35.203079, 126.805799, '2023-11-07 10:56:57.895710', 4, 32, '2021-08-16', 'Red Velvet', '강렬하면서도 세련된 사운드가 인상적입니다', 'Queendom', 'https://i.scdn.co/image/ab67616d0000b273830de2e836036f181df598d0'),
	(NULL, 35.2018184, 126.8078785, '2023-11-06 11:57:25.015705', 1, 33, '2021-09-17', 'NCT 127', '힙하고 실험적인 사운드가 돋보이는 개성있는 곡입니다', 'Sticker', 'https://i.scdn.co/image/ab67616d0000b27373e21d92fa8c70ce6aba72d0'),
	(NULL, 35.2025332, 126.8112192, '2023-11-05 12:58:06.376698', 1, 34, '2021-04-30', 'ITZY', '중독성 강한 훅과 멤버들의 강렬한 퍼포먼스가 매력적인 곡입니다', 'In the morning', 'https://i.scdn.co/image/ab67616d0000b273131cf6fcb170cda7a7956227'),
	(NULL, 35.0957058, 128.8559867, '2023-11-04 13:58:27.387169', 3, 35, '2021-06-18', 'SEVENTEEN', '세븐틴 특유의 에너지와 멜로디가 돋보이는 곡 입니다.', 'Ready to love', 'https://i.scdn.co/image/ab67616d0000b273c1a20972c9a083d5cece9ce5'),
	(NULL, 35.0963466, 128.853798, '2023-11-02 14:58:53.548454', 3, 36, '2021-02-23', 'SUNMI', '\'선미만의 섹시하고 몽환적인 분위기가 잘 드러나는 곡으로, 강렬한 퍼포먼스가 돋보입니다.', 'TAIL', 'https://i.scdn.co/image/ab67616d0000b27373b185444325e1d394d65660'),
	(NULL, 35.0957497, 128.8577355, '2023-11-01 15:00:00.795792', 3, 37, '2019-03-24', 'TAEYEON', '모든 사계절이 올 것 같은 느낌.', 'Four Seasons', 'https://i.scdn.co/image/ab67616d0000b2737de8f56a39ec67a091457d04'),
	(NULL, 35.0955741, 128.8527788, '2023-10-10 16:00:24.796737', 3, 38, '2020-01-13', 'ZICO', '경쾌한 비트와 중독성 있는 멜로디', 'Any song', 'https://i.scdn.co/image/ab67616d0000b2735f9608114e13bb4e5e6b0399'),
	(NULL, 35.094329, 128.855047, '2023-11-11 17:02:08.245576', 2, 39, '2017-06-26', 'HEIZE', '감성적인 목소리와 비 오는 날의 분위기를 담은 가사가 잘 어우러진 R&B 곡', 'You, Clouds, Rain', 'https://i.scdn.co/image/ab67616d0000b273053f6d4ad49f49131dbd3bfc'),
	(NULL, 35.0966341, 128.853435, '2023-11-12 18:02:39.885901', 2, 40, '2018-05-24', 'BOL4', '아 여행가고싶다', 'Travel', 'https://i.scdn.co/image/ab67616d0000b273e692edf07a63f385a795d5ea'),
	(NULL, 36.1064263, 128.4164677, '2023-11-08 19:02:58.344637', 2, 41, '2019-11-14', 'MAMAMOO', '자신감 넘치는 가사와 중독성 있는 훅이 매력적입니다', 'HIP', 'https://i.scdn.co/image/ab67616d0000b2739d650d0d98caf3f54b842a0b'),
	(NULL, 36.1071497, 128.4164045, '2023-11-07 20:08:03.836690', 6, 42, '2019-11-20', 'Layone', '출근할 때 이 노래를 들으면 심장이 빠운스 빵운스 합니다 오늘도 화이팅 !!!!', 'Slow Heartbeat (feat. Ja Mezz)', 'https://i.scdn.co/image/ab67616d0000b273c232f39111889b96c5c7c1c3'),
	(NULL, 36.3511013, 127.2977215, '2023-11-06 09:29:00.772574', 3, 43, '2021-05-14', 'Lee Mujin', '붉은색 푸르색~ 마침 신호등 기다리는 타이밍! 룰루', 'Traffic light', 'https://i.scdn.co/image/ab67616d0000b273aae78727e396da9f03032eda'),
	(NULL, 36.3528192, 127.30368, '2023-11-05 10:33:14.344320', 4, 44, '2023-04-20', 'Dj Mofak', '모두들 파이팅!', '파이팅 해야지 - Remix KR', 'https://i.scdn.co/image/ab67616d0000b2734c2752f41e36d3d2b983f8b1'),
	(NULL, 37.2416285709816, 131.86520078153654, '2023-11-09 11:56:21.471749', 3, 45, '1992-07-03', '정광태', '독도는 우리땅!! 넘보지마라!!! 국뽕이 차오른다', '독도는 우리땅', 'https://i.scdn.co/image/ab67616d0000b273ca45d463ee86b93f583e8d35');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
