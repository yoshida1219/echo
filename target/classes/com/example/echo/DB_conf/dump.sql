-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: echo_sns
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `RESPONSE_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `VIEW_USER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `COMMENT_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `COMMENT` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SUBMIT_TIME` datetime DEFAULT CURRENT_TIMESTAMP,
  `RESPONSE_CREATER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`RESPONSE_CREATER`,`RESPONSE_ID`,`VIEW_USER`,`COMMENT_ID`),
  KEY `A_idx` (`RESPONSE_CREATER`),
  KEY `comment_ibfk_2_idx` (`VIEW_USER`),
  KEY `comment_ibfk_1_idx` (`RESPONSE_ID`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`RESPONSE_ID`) REFERENCES `view_response` (`RESPONSE_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`VIEW_USER`) REFERENCES `view_response` (`VIEW_USER`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_3` FOREIGN KEY (`RESPONSE_CREATER`) REFERENCES `view_response` (`RESPONSE_CREATER`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite_movie`
--

DROP TABLE IF EXISTS `favorite_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite_movie` (
  `USER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MOVIE_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`USER_ID`,`MOVIE_ID`),
  KEY `favorite_movie_ibfk_2_idx` (`MOVIE_ID`),
  CONSTRAINT `favorite_movie_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `favorite_movie_ibfk_2` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`MOVIE_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_movie`
--

LOCK TABLES `favorite_movie` WRITE;
/*!40000 ALTER TABLE `favorite_movie` DISABLE KEYS */;
INSERT INTO `favorite_movie` VALUES ('U00000002','M00000000001'),('U00000002','M00000000017'),('U00000001','M00000000039'),('U00000002','M00000000052'),('U00000005','M00000000066'),('U00000001','M00000000083'),('U00000011','M00000000084'),('U00000011','M00000000085'),('U00000015','M00000000145'),('U00000015','M00000000147'),('U00000001','M00000000150'),('U00000016','M00000000156'),('U00000016','M00000000157'),('U00000016','M00000000158');
/*!40000 ALTER TABLE `favorite_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `USER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FOLLOWUSER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FOLLOW_TIME` datetime DEFAULT CURRENT_TIMESTAMP,
  `NOTIFICATION` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`USER_ID`,`FOLLOWUSER_ID`),
  KEY `FOLLOWUSER_ID` (`FOLLOWUSER_ID`),
  CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `follow_ibfk_2` FOREIGN KEY (`FOLLOWUSER_ID`) REFERENCES `user` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES ('U00000001','U00000003','2023-01-30 13:07:31',1),('U00000001','U00000015','2023-02-01 10:52:26',1),('U00000001','U00000018','2023-02-01 13:32:12',1),('U00000001','U00000019','2023-01-30 13:37:42',0),('U00000002','U00000001','2023-01-27 09:52:58',0),('U00000002','U00000003','2022-12-02 00:00:00',1),('U00000002','U00000004','2022-12-04 00:00:00',1),('U00000002','U00000005','2023-01-26 14:01:23',1),('U00000002','U00000006','2023-01-20 14:38:50',1),('U00000002','U00000010','2023-01-27 12:22:13',1),('U00000002','U00000012','2023-01-23 11:25:54',1),('U00000003','U00000002','2022-11-23 00:00:00',0),('U00000005','U00000002','2023-01-18 14:48:57',0),('U00000005','U00000016','2023-01-31 10:54:36',0),('U00000011','U00000002','2023-02-01 10:10:25',0),('U00000012','U00000002','2023-01-23 11:24:09',0),('U00000014','U00000012','2023-01-27 11:21:17',0),('U00000015','U00000002','2023-01-27 11:40:10',1),('U00000019','U00000001','2023-01-30 13:38:48',0),('U00000021','U00000001','2023-01-30 14:01:36',0),('U00000025','U00000001','2023-01-31 12:07:29',0),('U00000025','U00000005','2023-01-31 11:53:58',1);
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow_thread`
--

DROP TABLE IF EXISTS `follow_thread`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow_thread` (
  `USER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `THREAD_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FOLLOW_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`USER_ID`,`THREAD_ID`),
  KEY `follow_thread_ibfk_2_idx` (`THREAD_ID`),
  CONSTRAINT `follow_thread_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`),
  CONSTRAINT `follow_thread_ibfk_2` FOREIGN KEY (`THREAD_ID`) REFERENCES `thread` (`THREAD_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow_thread`
--

LOCK TABLES `follow_thread` WRITE;
/*!40000 ALTER TABLE `follow_thread` DISABLE KEYS */;
INSERT INTO `follow_thread` VALUES ('U00000001','T000001','2023-01-26 06:55:01'),('U00000001','T000002','2023-01-20 06:25:11'),('U00000001','T000022','2023-01-25 02:41:54'),('U00000001','T000027','2023-01-20 04:45:21'),('U00000001','T000031','2023-01-26 06:55:14'),('U00000001','T000039','2023-01-25 02:43:36'),('U00000002','T000001','2022-12-09 15:00:00'),('U00000002','T000002','2022-12-17 15:00:00'),('U00000002','T000004','2022-12-13 15:00:00'),('U00000002','T000006','2022-12-20 01:50:52'),('U00000002','T000022','2023-01-23 05:56:23'),('U00000002','T000027','2023-01-24 00:13:32'),('U00000002','T000036','2023-01-24 00:20:20'),('U00000003','T000022','2023-01-23 06:04:15'),('U00000011','T000022','2023-01-24 00:59:53'),('U00000012','T000022','2023-01-27 02:16:30'),('U00000012','T000043','2023-01-27 02:16:20'),('U00000015','T000001','2023-01-27 02:42:18');
/*!40000 ALTER TABLE `follow_thread` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jenre`
--

DROP TABLE IF EXISTS `jenre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jenre` (
  `JENRE_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JENRE_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`JENRE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jenre`
--

LOCK TABLES `jenre` WRITE;
/*!40000 ALTER TABLE `jenre` DISABLE KEYS */;
INSERT INTO `jenre` VALUES ('J0001','エンタメ'),('J0002','音楽'),('J0003','スポーツ'),('J0004','ゲーム'),('J0005','動物'),('J0006','美容・健康'),('J0007','美術'),('J0008','教育'),('J0009','料理'),('J0010','商品紹介'),('J0011','ニュース'),('J0012','Vlog'),('J0013','自然'),('J0014','機械'),('J0015','その他');
/*!40000 ALTER TABLE `jenre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `join_jenre`
--

DROP TABLE IF EXISTS `join_jenre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `join_jenre` (
  `USER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JENRE_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`USER_ID`,`JENRE_ID`),
  KEY `JENRE_ID` (`JENRE_ID`),
  CONSTRAINT `join_jenre_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `join_jenre_ibfk_2` FOREIGN KEY (`JENRE_ID`) REFERENCES `jenre` (`JENRE_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `join_jenre`
--

LOCK TABLES `join_jenre` WRITE;
/*!40000 ALTER TABLE `join_jenre` DISABLE KEYS */;
INSERT INTO `join_jenre` VALUES ('U00000001','J0001'),('U00000004','J0001'),('U00000005','J0001'),('U00000001','J0002'),('U00000002','J0002'),('U00000003','J0002'),('U00000005','J0002'),('U00000003','J0003'),('U00000004','J0003');
/*!40000 ALTER TABLE `join_jenre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `MOVIE_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MOVIE_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `URL` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `THUMBNAIL` varchar(100) NOT NULL,
  PRIMARY KEY (`MOVIE_ID`),
  UNIQUE KEY `URL_UNIQUE` (`URL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES ('M00000000001','世界の王様・王室　歴史の長さランキング［現存する王朝において］royal family 王家','idI_4iKIku0','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/idI_4iKIku0.jpg'),('M00000000002','Maker Faire Kyoto 2021｜「世界チャンピオンの紙飛行機」を作ろう！（John Collins）｜ World Champion\'s Paper Airplane','wnH97LRzT2A','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/wnH97LRzT2A.jpg'),('M00000000003','【Ado】私は最強 (ウタ from ONE PIECE FILM RED)','sk1Z-Hqwwog','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/sk1Z-Hqwwog.jpg'),('M00000000004','【コメ付き】スペシャルなたこ焼き作ってみた【ハイボール】','CBK_Nx0B1p4','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/CBK_Nx0B1p4.jpg'),('M00000000005','すべての人をガチギレさせる岸田の珍プレー集【あめんぼぷらす】【切り抜き】','WWON7AQXSPo','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/WWON7AQXSPo.jpg'),('M00000000006','240円をバカにするんじゃねえよ','VNNRfyIv1Hg','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/VNNRfyIv1Hg.jpg'),('M00000000007','【コメ付き】南極で出会った紳士的な皇帝ペンギン','TYEmX9CTko4','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/TYEmX9CTko4.jpg'),('M00000000008','怒ってた猫が急に話しかけて来たけど、ネコ語だからわからない、コメ付','Kg44ktmeSu0','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/Kg44ktmeSu0.jpg'),('M00000000009','yee!!','q6EoRBvdVPQ','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/q6EoRBvdVPQ.jpg'),('M00000000010','ワザップジョルノ','TiaYg2pUl_A','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/TiaYg2pUl_A.jpg'),('M00000000011','ブルアカで抜いた生徒を庇うやんくみのモノマネ','TQEH4aULevk','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/TQEH4aULevk.jpg'),('M00000000013','高校数学で分かる秘書問題【最適停止問題】','hUEtN6-kVqk','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/hUEtN6-kVqk.jpg'),('M00000000015','【新形式対応】2023年春の基本情報技術者試験勉強法 | 基本情報技術者試験','AiaZB7Xpdgc','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/AiaZB7Xpdgc.jpg'),('M00000000017','Suisei On Whether She&#39;ll Quit Mario Kart After This Years Victory 【ENG Sub Hololive】','Hsk0_WA-QOw','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/Hsk0_WA-QOw.jpg'),('M00000000018','【１周年記念】ダダダダ天使／湊あくあ【歌ってみた】','O8v5S1nhKzo','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/O8v5S1nhKzo.jpg'),('M00000000021','フォニイ 歌ってみたのはメガテラ・ゼロ','gnfBEGmvrOs','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/gnfBEGmvrOs.jpg'),('M00000000022','4年ぶりにパパがいない日、柴犬のある行動に涙が出ました。','zpGh0es9kG0','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/zpGh0es9kG0.jpg'),('M00000000023','映画『ミッション：インポッシブル／デッドレコニング PART ONE』特別メイキング映像','SE-SNu1l6k0','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/SE-SNu1l6k0.jpg'),('M00000000024','【公式】サンドウィッチマン　コント【カラオケ】2015年','5kmyOfvucp8','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/5kmyOfvucp8.jpg'),('M00000000025','タカアンドトシの傑作漫才　「特殊」','QdVOTplOReE','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/QdVOTplOReE.jpg'),('M00000000026','Acacia','4dnT-kKIO6Y','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/4dnT-kKIO6Y.jpg'),('M00000000028','閉校になる母校にサプライズで登場してみた','eu7EqtVZ-Jo','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/eu7EqtVZ-Jo.jpg'),('M00000000029','2022年版「関優太総まとめ」','dyctK_uECaI','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/dyctK_uECaI.jpg'),('M00000000031','【悪夢再び】伝説となった2022年共通テスト数学IAを解説','mjjCXyYslyY','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/mjjCXyYslyY.jpg'),('M00000000032','ピザラ人狼 2023 supported by AKRacing オーイシ×加藤のピザラジオ 第95回SP','qkk-CKZUuY8','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/qkk-CKZUuY8.jpg'),('M00000000034','タカアンドトシ コント 「受験生」「賃貸物件の案内」 「山で遭難か」「日本一豪華な笑いの祭典!!」','MWU6SIMK_wY','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/MWU6SIMK_wY.jpg'),('M00000000037','【新人警察官】スマホも禁止⁉警察学校での生活とは　新潟　NNNセレクション','mmc0PBDHQRk','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/mmc0PBDHQRk.jpg'),('M00000000038','テレビ故障のおばあちゃん家に緊急で駆けつけるも全く戦力にならずコタツでぬくぬくし始める柴犬','bbw8ENZNMlE','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/bbw8ENZNMlE.jpg'),('M00000000039','怪獣の花唄　歌ってみたのはメガテラ・ゼロ','bc9lcY5nzQQ','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/bc9lcY5nzQQ.jpg'),('M00000000045','【party parrot】数学科に入ると失うもの','m6fXyafOwXg','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/m6fXyafOwXg.jpg'),('M00000000046','TVアニメ「ONE PIECE」麦わらの一味からの感染予防メッセージ　Important message from the &quot;One Piece&quot; Straw Hats','4NMRkuUKmJc','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/4NMRkuUKmJc.jpg'),('M00000000047','はじめてのファイアーエムブレム','usDy8YBesqQ','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/usDy8YBesqQ.jpg'),('M00000000051','苦手なシャワー中に犬語で文句言い続ける柴犬がかわいすぎた。','-8o7ZtUYKQo','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/-8o7ZtUYKQo.jpg'),('M00000000052','2023年最新・エンジニアのトレンドをStackOverflowから読み解こう！','Aqhb2IYtdLA','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/Aqhb2IYtdLA.jpg'),('M00000000053','ヒカキン セイキン『チョコラータとセッコ説』について解説','Wv6qTlmZPug','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/Wv6qTlmZPug.jpg'),('M00000000055','【危険】ヒカキン　自動字幕　による狂言集','rsfb7QwuL0A','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/rsfb7QwuL0A.jpg'),('M00000000056','ツイッターのヒカキンシンメトリーBotが面白すぎて爆笑www','5wnfkIfw0jE','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/5wnfkIfw0jE.jpg'),('M00000000057','自分の顔だけでハンバーガー作ってみた','evty9ZFNIpg','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/evty9ZFNIpg.jpg'),('M00000000058','뉴욕영락교회 새벽기도 [2023.1.17] [삼하 5:1-12]','4caNYLaWjNY','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/4caNYLaWjNY.jpg'),('M00000000059','【生検証】ポケモン金銀249匹図鑑完成まで何時間かかる？ #1｜ポケットモンスター 金・銀','hQU1PlAh5_k','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/hQU1PlAh5_k.jpg'),('M00000000060','【party parrot】絶望の2015年センター試験数学IIBを解説','Zq1AG4jKtzM','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/Zq1AG4jKtzM.jpg'),('M00000000061','エミューの雛と美しい白鳥のワルツ','Q6kE342Lq8k','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/Q6kE342Lq8k.jpg'),('M00000000062','【危険】左手さんの貴重な捕食シーン','EicwvS9xRn0','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/EicwvS9xRn0.jpg'),('M00000000063','[ヴィーガン]ビーガンの食生活#ヴィーガン #ベジタリアン','3Tza5jJNZ8A','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/3Tza5jJNZ8A.jpg'),('M00000000064','汚い海　10分耐久','ECBUeA7xMhk','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/ECBUeA7xMhk.jpg'),('M00000000065','根性焼きと焼けたすね毛とハローキティと','gPORcccl4ZE','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/gPORcccl4ZE.jpg'),('M00000000066','学習発表会～音楽物語「ごんぎつね」から','QDqONMqotFE','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/QDqONMqotFE.jpg'),('M00000000067','見えない前提','JnXlAS4n0NQ','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/JnXlAS4n0NQ.jpg'),('M00000000068','たれぞう VS ヒカキン　ボイパ対決 Bad Apple!! 　(コメ付き)','X9D4F2ouwss','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/X9D4F2ouwss.jpg'),('M00000000070','【ホロライブ】歌ってみた動画の月間ランキング【TOP10】','M2Ek3choWUQ','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/M2Ek3choWUQ.jpg'),('M00000000071','【作業用BGM】人生で1度は聞いておくべきNCS(EDM)メドレー！！[BEST of NCS Mix]','RH24wbgMcqk','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/RH24wbgMcqk.jpg'),('M00000000072','ポテトチップスとフライドポテト作ってみた！','cpiWNMpCwPQ','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/cpiWNMpCwPQ.jpg'),('M00000000074','水素の音　合唱','WhvsXFD0LoA','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/WhvsXFD0LoA.jpg'),('M00000000075','heisenburger','b4XpMTUlorc','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/b4XpMTUlorc.jpg'),('M00000000076','ノリアキ - unstoppable / Noriaki - unstoppable (2006)','ofp_ffk0LM4','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/ofp_ffk0LM4.jpg'),('M00000000077','Me at the zoo','jNQXAC9IVRw','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/jNQXAC9IVRw.jpg'),('M00000000078','sushi','0b75cl4-qRE','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/0b75cl4-qRE.jpg'),('M00000000079','夢喰 - Tani Yuuki【MV】','nudN_HNS8Ls','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/nudN_HNS8Ls.jpg'),('M00000000080','【コメ付き】セロリとベーコンのほがらかスープ作ってみた','K0nYcBDqn1k','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/K0nYcBDqn1k.jpg'),('M00000000081','2021.06.08 階段がいらない女','PGIdwBOujFI','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/PGIdwBOujFI.jpg'),('M00000000082','【コメ付き】鮎の塩焼き食べてみた【アル中カラカラハイボール】','P1YlDc6TUQk','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/P1YlDc6TUQk.jpg'),('M00000000083','メルト　歌ってみたのはメガテラ・ゼロ','4PdVOzuyVmY','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/4PdVOzuyVmY.jpg'),('M00000000084','Orangestar - Surges (feat. IA &amp; 初音ミク) Official Video','rkaNKAvksDE','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/rkaNKAvksDE.jpg'),('M00000000085','Orangestar - Henceforth (feat. IA) Official Video','L13gCEZJVRU','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/L13gCEZJVRU.jpg'),('M00000000086','Orangestar - DAYBREAK FRONTLINE (feat. IA) Official Video','emrt46SRyYs','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/emrt46SRyYs.jpg'),('M00000000087','ﾄﾞｩﾜｧ! ｾﾝﾅﾅﾋｬｸ!!','m2GrCJK3YQM','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/m2GrCJK3YQM.jpg'),('M00000000088','真実に向かおうとする意志　#ジョジョの奇妙な冒険 #音mad #名言 #shorts','giKKcmv_pco','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/giKKcmv_pco.jpg'),('M00000000089','浪人させない犬','oheWrizjwNQ','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/oheWrizjwNQ.jpg'),('M00000000091','大雨','WtQ78OIvnYw','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/WtQ78OIvnYw.jpg'),('M00000000092','Just the Two of Us','Rxc7u9p0VAU','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/Rxc7u9p0VAU.jpg'),('M00000000093','Chill &amp; Relaxing Pokémon Music Mix','YMEblRM4pGc','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/YMEblRM4pGc.jpg'),('M00000000094','住処が伐採されて行方不明になっていたアザラシ猫、新しいお家はパイプの中！？','moFB-j5iY2E','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/moFB-j5iY2E.jpg'),('M00000000095','コードレビューや変数名やメソッド名の命名は AI に任せちゃおう','2Lp_DA9mEqI','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/2Lp_DA9mEqI.jpg'),('M00000000096','【リズム天国×ポケモンSV】リミックス10 【PokemonSV】','8CkRtlDazW4','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/8CkRtlDazW4.jpg'),('M00000000097','Our Pet Call Duck is Swayed Gently.','0Da8ZhKcNKQ','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/0Da8ZhKcNKQ.jpg'),('M00000000098','きれいな雪景色に目もくれず車の中で最も意味のない場所を凝視し続ける柴犬','M3DRY5xQYlk','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/M3DRY5xQYlk.jpg'),('M00000000099','やっぱ監督ってすげぇ！！ 選手より目立つ監督のスーパープレイ集！','v8KlA2cEMiU','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/v8KlA2cEMiU.jpg'),('M00000000100','コウメ太夫さんのネタ２編を映像化しました #shorts','dQZX2uOTC18','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/dQZX2uOTC18.jpg'),('M00000000101','1000℃の鉄球をバターに置いたら大変なことにwww #Shorts','7u5MrZssBYc','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/7u5MrZssBYc.jpg'),('M00000000102','Vídeos que não deram certo com o venom #10','QCnx-KZx8_A','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/QCnx-KZx8_A.jpg'),('M00000000103','3000℃のテルミット反応で風呂は湧くのか？【検証】#shorts','E6mP1WpX6Wk','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/E6mP1WpX6Wk.jpg'),('M00000000104','ヤベードラマーシリーズ7本まとめ','veJg7CoyoL4','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/veJg7CoyoL4.jpg'),('M00000000105','多磨霊園第4踏切　東府中駅　京王線　 #デートスポット','-5xq5wrYDeg','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/-5xq5wrYDeg.jpg'),('M00000000106','fat asian guy laughing','6mzNZIKV-Qs','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/6mzNZIKV-Qs.jpg'),('M00000000107','すごい変化球','PTOWKRfRDGA','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/PTOWKRfRDGA.jpg'),('M00000000108','Vídeos que não deram certo #11','VJ0MEJJ9nco','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/VJ0MEJJ9nco.jpg'),('M00000000109','色々な台パンまとめてみたwww #shorts #台パン','6sK86qBX22U','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/6sK86qBX22U.jpg'),('M00000000131','ファイトソング (Fight Song) - Eve Music Video','2eOg5DoYuwU','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/2eOg5DoYuwU.jpg'),('M00000000132','白雪 (White Snow) - Eve MV','Kvceyq9o0rY','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/Kvceyq9o0rY.jpg'),('M00000000133','ドラマツルギー - Eve  MV','jJzw1h5CR-I','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/jJzw1h5CR-I.jpg'),('M00000000134','お気に召すまま - Eve MV','nROvY9uiYYk','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/nROvY9uiYYk.jpg'),('M00000000135','心予報 - Eve MV','dJf4wCdLU18','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/dJf4wCdLU18.jpg'),('M00000000137','あの娘シークレット - Eve MV','sgdPlDG1-8k','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/sgdPlDG1-8k.jpg'),('M00000000138','ナンセンス文学 - Eve  MV','OskXF3s0UT8','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/OskXF3s0UT8.jpg'),('M00000000139','アウトサイダー - Eve MV','GMPjNA_fCj4','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/GMPjNA_fCj4.jpg'),('M00000000140','ラストダンス - Eve MV','CLdeykXCZX4','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/CLdeykXCZX4.jpg'),('M00000000141','【コメ付き】3300円の、3回払い！【QVC】','xX0x5x2A8PI','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/xX0x5x2A8PI.jpg'),('M00000000142','【最新ニコニココメ付き】QVC福島　ガバガバシーン集1~10','NiYOLcs8_Ks','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/NiYOLcs8_Ks.jpg'),('M00000000143','１億回記念！本気ネタ！『声細い奴と声太い奴』【JARUJARUTOWER】','Wp_TIpFbq6M','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/Wp_TIpFbq6M.jpg'),('M00000000144','大量の串カツで１年を締めくくる。','a6DfE66KSnU','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/a6DfE66KSnU.jpg'),('M00000000145','ザリガニをミキサーにかけてパスタ作ろうぜ！','PSfhKXJhmPI','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/PSfhKXJhmPI.jpg'),('M00000000146','【ゲスト:梵頭】漢 Kitchen ~漢 a.k.a. GAMI の料理番組~','Fk8w2R5tm5M','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/Fk8w2R5tm5M.jpg'),('M00000000147','ザリガニぶっ潰してせんべい作る','yKAvl-j2RUY','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/yKAvl-j2RUY.jpg'),('M00000000148','真・タニシでサイゼのエスカルゴ作ってみた','spKJpTVnafg','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/spKJpTVnafg.jpg'),('M00000000149','FP2級を目指すなら知っておいてほしいこと【難易度・勉強法etc...】','-TqKA_WSatk','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/-TqKA_WSatk.jpg'),('M00000000150','▽▲TRiNITY▲▽「I know “The Unknown World&quot;」Music Video【2022/10/5発売『Δ(DELTA)』収録曲】','o-sXJN3aJxw','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/o-sXJN3aJxw.jpg'),('M00000000152','ジムニー５ドア　純正オプション!?','eQvqw6DN0ig','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/eQvqw6DN0ig.jpg'),('M00000000153','Suzuki Jimny - Brabus G-Wagen Tribute','ksgllae8rSw','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/ksgllae8rSw.jpg'),('M00000000154','【何故売れる?】三菱デリカD:5の秘密に迫る! 特別仕様車カスタムギア内装&amp;外装レポート! 新型ノア･ヴォクシーやステップワゴンより良い?! | MITSUBISHI DELICA D5','PfHu2nEeNww','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/PfHu2nEeNww.jpg'),('M00000000155','寒波で“物流打撃”棚ガラガラ “スリップ事故”も続出 7台玉突き「ビリヤード状態」(2023年1月27日)','CV70UgGVITA','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/CV70UgGVITA.jpg'),('M00000000156','アニメ「日蓮大聖人の御生涯」第１話（誕生～「立正安国論」）｜創価学会公式','fwN0KvCTZn4','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/fwN0KvCTZn4.jpg'),('M00000000157','アニメ「日蓮大聖人の御生涯」第２話（竜の口の法難～佐渡流罪）｜創価学会公式','uWpXf0aHV7s','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/uWpXf0aHV7s.jpg'),('M00000000158','アニメ「日蓮大聖人の御生涯」第３話（身延入山～御入滅）｜創価学会公式','SgeEPx9tOyQ','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/SgeEPx9tOyQ.jpg'),('M00000000167','QVC福島 ガバガバシーン集【その①～その⑮】最新コメ付き (8月5日)','2f6zOYJM2Jk','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/2f6zOYJM2Jk.jpg'),('M00000000172','【歌ってみた】グッバイ宣言 / 百鬼あやめ cover','z1W4c7ym49Y','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/z1W4c7ym49Y.jpg'),('M00000000181','【立体音響】レオニのflywayを立体音響にしてみた！','2GK1B1RshN4','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/2GK1B1RshN4.jpg'),('M00000000182','RADWIMPS - 会心の一撃 [Official Live Video from &quot;RADWIMPS GRAND PRIX 2014 実況生中継&quot;]','8EEKBabOWNk','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/8EEKBabOWNk.jpg'),('M00000000183','【333】[feat. asmi, すりぃ] アイワナムチュー / MAISONdes','AROi9sNCVKs','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/AROi9sNCVKs.jpg'),('M00000000184','【417】[feat. 4na, もっさ(ネクライトーキー)] ダンボールの色 / MAISONdes','9vLxUqDa8aU','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/9vLxUqDa8aU.jpg'),('M00000000185','PEOPLE 1 &quot;常夜燈&quot; （Official Video）','OZpv_AcPCKg','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/OZpv_AcPCKg.jpg'),('M00000000186','三重vlog','5_ucK-pxhQA','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/5_ucK-pxhQA.jpg'),('M00000000187','RADWIMPS - 君と羊と青  [Official Live Video from  &quot;RADWIMPS 絶体延命ツアー&quot;]','MHDe8QL--go','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/MHDe8QL--go.jpg'),('M00000000188','米津玄師 Kenshi Yonezu - KICKBACK','M2cckDmNLMI','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/M2cckDmNLMI.jpg'),('M00000000194','【Git入門】ソースコードのバージョン管理の仕組みを解説！ | VOICEVOX','OjSGYX4XOVE','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/OjSGYX4XOVE.jpg'),('M00000000195','フレデリック「オドループ」Music Video | Frederic &quot;oddloop&quot;','PCp2iXA1uLE','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/PCp2iXA1uLE.jpg'),('M00000000196','下水を市民が飲み干せば水質汚染は完全になくなる説【Cities:Skylines / シティーズスカイライン】','uuPQd0qRZro','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/uuPQd0qRZro.jpg'),('M00000000197','創価学会（本部）の内部映像','ipWPd44O74M','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/ipWPd44O74M.jpg'),('M00000000198','【MV】Rap Guerrilla Reload -Paradox Live All ARTISTS-','-2I7hb7DGk4','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/-2I7hb7DGk4.jpg'),('M00000000199','Roselia×Eve『閃光』アニメーションMV(ゲームサイズver.) 【アーティストタイアップ楽曲】','IL33TNljMz8','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/IL33TNljMz8.jpg'),('M00000000200','『チェンソーマン』第４話ノンクレジットエンディング / CHAINSAW MAN #4 Ending│TOOBOE 「錠剤」','xIKW3NKYBWw','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/xIKW3NKYBWw.jpg'),('M00000000201','騒音おばさん','zAk6T3zAJgc','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/zAk6T3zAJgc.jpg'),('M00000000202','「さよなら、香香」／「水の革命」（作詞・作曲：大川隆法）','S8RD6mlhUFg','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/S8RD6mlhUFg.jpg'),('M00000000203','「ときめきの時」（作詞・作曲：大川隆法）','16mMSiQq4TY','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/16mMSiQq4TY.jpg'),('M00000000204','【公式】『ちいかわ』第43話「きんちょうした/郎にモモンガ」※1週間限定配信＜2/3 (金)AM7:59まで＞','ryOEWuOv4iI','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/ryOEWuOv4iI.jpg'),('M00000000205','「時代」（作詞・作曲：大川隆法）','YIyC68VHXJs','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/YIyC68VHXJs.jpg'),('M00000000206','【替え歌】祖国に嫌われている。【命に嫌われている/ソ連版】','BKHRbVTw6FQ','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/BKHRbVTw6FQ.jpg'),('M00000000207','あるいは最高位人権道徳者を目指しなさい','GjVCYhpkJiA','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/GjVCYhpkJiA.jpg'),('M00000000208','お題目 三万遍 10時間 池田先生「一日三千遍の題目があがると生命の回転が始まって来るのだ。」 南妙法蓮華経 【Daimoku/SGI/NMRK】30000 10hour','Vyr4ViQjr7Y','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/Vyr4ViQjr7Y.jpg'),('M00000000209','炭酸中毒のキックバック','zmHA7L04nd0','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/zmHA7L04nd0.jpg'),('M00000000210','優里『ビリミリオン』Official Music Video','vUsikBoa8i0','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/vUsikBoa8i0.jpg'),('M00000000211','ベテラン母柴犬に戦いを挑み片手で倒される子犬が可愛いw','Rf6TJ-NOaV4','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/Rf6TJ-NOaV4.jpg'),('M00000000212','【子犬　保護犬】倉庫の下が住処だった野犬の子犬　成長日記','LLOO3RRfxTE','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/LLOO3RRfxTE.jpg'),('M00000000213','Owarimonogatari ost Bugaisha','Q7GvkhVChM8','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/Q7GvkhVChM8.jpg'),('M00000000214','【再会】突然ですが猫たちを連れて実家に帰らせていただきます。【幻の実家帰省-総集編】','6--qpft7lNI','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/6--qpft7lNI.jpg'),('M00000000215','目が見えない子猫の保護最初の１ヶ月総集編','N2g8XEkJETY','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/N2g8XEkJETY.jpg'),('M00000000216','爆盛りカツ丼ラッシュ！常連が殺到する行列うどん店の鮮やかな注文さばき丨Udon Restaurant&#39;s Ultimate Egg Rice Bowl','8GZYtTbmivE','https://skpacket.s3.ap-northeast-1.amazonaws.com/thumbnail/8GZYtTbmivE.jpg');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `response`
--

DROP TABLE IF EXISTS `response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `response` (
  `RESPONSE_CREATER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `RESPONSE_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `RESPONSE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `THREAD_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `MOVIE_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `LIKE` int DEFAULT '0',
  `SHARE` int DEFAULT '0',
  `RESPONSE_SUBMIT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ORIGIN_CREATER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ORIGIN_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`RESPONSE_ID`,`RESPONSE_CREATER`),
  KEY `RESPONSE_CREATER` (`RESPONSE_CREATER`),
  KEY `THREAD_ID` (`THREAD_ID`),
  KEY `MOVIE_ID` (`MOVIE_ID`),
  KEY `response_ibfk_4` (`ORIGIN_CREATER`,`ORIGIN_ID`),
  CONSTRAINT `response_ibfk_1` FOREIGN KEY (`RESPONSE_CREATER`) REFERENCES `user` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `response_ibfk_2` FOREIGN KEY (`THREAD_ID`) REFERENCES `thread` (`THREAD_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `response_ibfk_3` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`MOVIE_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `response_ibfk_4` FOREIGN KEY (`ORIGIN_CREATER`, `ORIGIN_ID`) REFERENCES `response` (`RESPONSE_CREATER`, `RESPONSE_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `response`
--

LOCK TABLES `response` WRITE;
/*!40000 ALTER TABLE `response` DISABLE KEYS */;
INSERT INTO `response` VALUES ('U00000007','R000001','まあ、無難に見ときな','T000005','M00000000007',34835954,38,'2022-12-14 03:09:43',NULL,NULL),('U00000002','R000002','こんにちは',NULL,'M00000000047',0,0,'2023-01-26 07:50:07',NULL,NULL),('U00000003','R000002','hi','T000032','M00000000017',0,0,'2023-01-20 06:48:42',NULL,NULL),('U00000004','R000002','jojo','T000022','M00000000088',0,0,'2023-01-19 06:02:05',NULL,NULL),('U00000006','R000002','こんにちは！','T000004','M00000000003',2,33,'2022-12-20 02:36:30',NULL,NULL),('U00000010','R000002','おう','T000001','M00000000017',0,0,'2023-01-18 07:17:48',NULL,NULL),('U00000011','R000002','「Surges」の原曲が本当に好きなのでぜひ聞いてみてほしいです！','T000034','M00000000084',0,0,'2023-01-19 04:20:34',NULL,NULL),('U00000012','R000002','テストしてまーーす','T000001','M00000000017',0,0,'2023-01-20 07:24:41',NULL,NULL),('U00000014','R000002','Japanese Cokking Channel',NULL,'M00000000146',0,0,'2023-01-27 02:30:32',NULL,NULL),('U00000015','R000002','この時期、恋しくなりますよね。',NULL,'M00000000145',0,0,'2023-01-27 02:27:45',NULL,NULL),('U00000016','R000002','この機能いらない','T000001','M00000000155',0,0,'2023-01-27 04:15:34',NULL,NULL),('U00000018','R000002','dadwdw','T000001','M00000000188',0,0,'2023-01-30 01:56:59',NULL,NULL),('U00000021','R000002','これすきなんだけど','T000044','M00000000003',0,0,'2023-01-30 05:03:31',NULL,NULL),('U00000002','R000003','あ',NULL,'M00000000052',0,0,'2023-01-26 07:51:11',NULL,NULL),('U00000003','R000003','ujuh','T000022','M00000000092',0,0,'2023-01-23 04:49:58',NULL,NULL),('U00000011','R000003','Henceforthも好きです...!!','T000034','M00000000085',0,0,'2023-01-19 04:21:40',NULL,NULL),('U00000012','R000003','じゃるじゃる','T000043','M00000000143',0,0,'2023-01-27 02:15:59',NULL,NULL),('U00000015','R000003','いちいちコメント書かなきゃいけないの面倒くね？？',NULL,'M00000000147',0,0,'2023-01-27 02:33:21',NULL,NULL),('U00000025','R000003','ワ……ァ……！','T000047','M00000000204',0,0,'2023-01-31 02:51:02',NULL,NULL),('U00000003','R000004','bfd','T000001','M00000000093',0,0,'2023-01-23 04:52:36',NULL,NULL),('U00000005','R000004','母の愛情を感じます','T000033','M00000000062',0,0,'2023-01-18 03:42:39',NULL,NULL),('U00000012','R000004','怪物君',NULL,'M00000000144',0,0,'2023-01-27 02:18:09',NULL,NULL),('U00000015','R000004','なんでいちいちコメント書かなきゃいけないんですか？','T000001','M00000000148',0,0,'2023-01-27 02:35:16',NULL,NULL),('U00000003','R000005','ada','T000001','M00000000094',0,0,'2023-01-23 04:52:50',NULL,NULL),('U00000015','R000005','DO AS I SAY','T000001','M00000000149',0,0,'2023-01-27 02:36:02',NULL,NULL),('U00000016','R000005','日蓮大聖人の御生涯ーーそれは、全人類の不幸を根絶し、すべての人々に仏の境涯を開かせたいとの誓願と慈悲に貫かれた妙法弘通の御一生でした。そして、民衆の幸福を阻む一切の悪を責め抜き、大難に次ぐ大難の御生涯でもありました。','T000001','M00000000156',0,0,'2023-01-27 04:25:54',NULL,NULL),('U00000001','R000006','犬がかわいいんじゃ','T000001','M00000000038',0,0,'2023-01-16 03:15:26',NULL,NULL),('U00000002','R000006','sdf','T000042','M00000000142',0,0,'2023-01-27 00:13:15',NULL,NULL),('U00000003','R000006','drg','T000001','M00000000094',0,0,'2023-01-23 04:53:48',NULL,NULL),('U00000015','R000006','この投稿は共有されたものです',NULL,'M00000000092',0,0,'2023-01-27 02:40:29','U00000003','R000003'),('U00000016','R000006','日蓮大聖人の御生涯ーーそれは、全人類の不幸を根絶し、すべての人々に仏の境涯を開かせたいとの誓願と慈悲に貫かれた妙法弘通の御一生でした。そして、民衆の幸福を阻む一切の悪を責め抜き、大難に次ぐ大難の御生涯でもありました。','T000001','M00000000157',0,0,'2023-01-27 04:26:27',NULL,NULL),('U00000002','R000007','nfggffn','T000037','M00000000142',0,0,'2023-01-27 00:14:13',NULL,NULL),('U00000003','R000007','htr','T000001','M00000000095',0,0,'2023-01-23 04:53:57',NULL,NULL),('U00000005','R000007','おすすめ','T000033','M00000000065',0,0,'2023-01-18 03:51:15',NULL,NULL),('U00000015','R000007','PV',NULL,'M00000000150',0,0,'2023-01-27 02:44:05',NULL,NULL),('U00000016','R000007','日蓮大聖人の御生涯ーーそれは、全人類の不幸を根絶し、すべての人々に仏の境涯を開かせたいとの誓願と慈悲に貫かれた妙法弘通の御一生でした。そして、民衆の幸福を阻む一切の悪を責め抜き、大難に次ぐ大難の御生涯でもありました','T000001','M00000000158',0,0,'2023-01-27 04:26:52',NULL,NULL),('U00000002','R000008','この投稿は共有されたものです',NULL,'M00000000092',0,0,'2023-01-27 00:47:31','U00000003','R000003'),('U00000003','R000008','kuk','T000001','M00000000095',0,0,'2023-01-23 04:55:45',NULL,NULL),('U00000005','R000008','ノスタルジック','T000033','M00000000066',0,0,'2023-01-18 03:53:09',NULL,NULL),('U00000003','R000009','hmdf','T000001','M00000000096',0,0,'2023-01-23 04:55:52',NULL,NULL),('U00000015','R000009','動画を再生できない動画がございます。','T000044','M00000000198',0,0,'2023-01-31 02:50:08',NULL,NULL),('U00000002','R000010','この投稿は共有されたものです',NULL,'M00000000052',0,0,'2023-01-27 00:53:07','U00000001','R000038'),('U00000003','R000010','ngf','T000001','M00000000096',0,0,'2023-01-23 04:56:13',NULL,NULL),('U00000002','R000011','mietetu','T000001','M00000000141',0,0,'2023-02-01 04:07:32',NULL,NULL),('U00000003','R000011','a','T000008','M00000000092',0,0,'2023-01-24 02:06:38',NULL,NULL),('U00000005','R000011','a','T000033','M00000000074',0,0,'2023-01-19 02:47:47',NULL,NULL),('U00000011','R000011','この投稿は共有されたものです',NULL,'M00000000071',0,0,'2023-01-25 05:41:10','U00000001','R000030'),('U00000002','R000012','htfh','T000001','M00000000210',0,0,'2023-02-01 04:07:48',NULL,NULL),('U00000003','R000012','ddd',NULL,'M00000000209',0,0,'2023-02-01 04:02:55',NULL,NULL),('U00000005','R000012','ああｓ','T000033','M00000000075',0,0,'2023-01-19 02:53:02',NULL,NULL),('U00000001','R000013','あ','T000001','M00000000055',0,0,'2023-01-18 05:02:56',NULL,NULL),('U00000002','R000013','dfgf','T000001','M00000000093',0,0,'2023-02-01 04:10:43',NULL,NULL),('U00000005','R000013','さだｓ','T000033','M00000000076',0,0,'2023-01-19 02:57:03',NULL,NULL),('U00000002','R000014','a','T000037','M00000000211',0,0,'2023-02-01 04:15:30',NULL,NULL),('U00000005','R000014','zoooooooo','T000033','M00000000077',0,0,'2023-01-19 03:00:31',NULL,NULL),('U00000002','R000015','a','T000037','M00000000212',0,0,'2023-02-01 04:16:12',NULL,NULL),('U00000005','R000015','party time','T000033','M00000000078',0,0,'2023-01-19 03:12:20',NULL,NULL),('U00000001','R000016','sssssssssssssss','T000022','M00000000052',0,0,'2023-01-18 05:40:49',NULL,NULL),('U00000002','R000016','q','T000037','M00000000214',0,0,'2023-02-01 04:18:45',NULL,NULL),('U00000005','R000016','挑む','T000033','M00000000079',0,0,'2023-01-19 03:21:48',NULL,NULL),('U00000002','R000017','a','T000037','M00000000215',0,0,'2023-02-01 04:20:14',NULL,NULL),('U00000005','R000017','ふまｎ','T000033','M00000000080',0,0,'2023-01-19 03:24:42',NULL,NULL),('U00000001','R000018','いいぞ','T000001','M00000000039',0,0,'2023-01-18 07:12:22',NULL,NULL),('U00000002','R000018','a','T000037','M00000000216',0,0,'2023-02-01 04:26:26',NULL,NULL),('U00000005','R000018','あささささ','T000033','M00000000081',0,0,'2023-01-19 03:27:12',NULL,NULL),('U00000001','R000019','ねもうす','T000001','M00000000071',0,0,'2023-01-18 07:12:52',NULL,NULL),('U00000005','R000019','ｓ','T000033','M00000000082',0,0,'2023-01-19 03:28:25',NULL,NULL),('U00000016','R000019','感動しました','T000046','M00000000202',0,0,'2023-01-31 02:44:39',NULL,NULL),('U00000005','R000020','せんななひゃく','T000033','M00000000087',0,0,'2023-01-19 04:25:47',NULL,NULL),('U00000016','R000020','最高','T000046','M00000000203',0,0,'2023-01-31 02:46:30',NULL,NULL),('U00000005','R000021','三浪','T000022','M00000000089',0,0,'2023-01-19 06:13:29',NULL,NULL),('U00000016','R000021','時代','T000046','M00000000205',0,0,'2023-01-31 02:52:55',NULL,NULL),('U00000016','R000022','お題目 三万遍','T000033','M00000000208',0,0,'2023-02-01 01:45:31',NULL,NULL),('U00000005','R000023','くやしいです！','T000027','M00000000091',0,0,'2023-01-19 06:38:19',NULL,NULL),('U00000005','R000024','fasfaff','T000001','M00000000101',0,0,'2023-01-23 05:11:09',NULL,NULL),('U00000005','R000025','dadadasf','T000001','M00000000101',0,0,'2023-01-23 05:13:01',NULL,NULL),('U00000005','R000027','あｓふぁせｗ','T000001','M00000000102',0,0,'2023-01-23 05:36:29',NULL,NULL),('U00000005','R000028','ふぁｓｄふぇｆ','T000001','M00000000102',0,0,'2023-01-23 05:37:43',NULL,NULL),('U00000001','R000029','これ聞いておけば間違いない','T000039','M00000000071',0,0,'2023-01-25 02:43:00',NULL,NULL),('U00000005','R000029','だｓだｓ','T000001','M00000000103',0,0,'2023-01-23 05:38:01',NULL,NULL),('U00000001','R000030','a','T000001','M00000000071',0,0,'2023-01-25 03:39:12',NULL,NULL),('U00000005','R000030','だｓｓだｓだ','T000001','M00000000103',0,0,'2023-01-23 05:39:06',NULL,NULL),('U00000005','R000031','だｓｄｑｗで','T000001','M00000000104',0,0,'2023-01-23 05:40:26',NULL,NULL),('U00000005','R000032','だｓだｗ','T000001','M00000000105',0,0,'2023-01-23 05:41:40',NULL,NULL),('U00000005','R000033','dawfefwef','T000001','M00000000107',0,0,'2023-01-23 06:01:02',NULL,NULL),('U00000001','R000034','この投稿は共有されたものです',NULL,'M00000000082',0,0,'2023-01-26 02:24:58','U00000005','R000019'),('U00000005','R000034','asdqawdcs','T000001','M00000000108',0,0,'2023-01-23 06:05:54',NULL,NULL),('U00000005','R000036','だｓだｄ','T000033','M00000000182',0,0,'2023-01-30 01:19:05',NULL,NULL),('U00000001','R000037','この投稿は共有されたものです',NULL,'M00000000087',0,0,'2023-01-26 06:50:06','U00000005','R000020'),('U00000001','R000038','ああああああああ',NULL,'M00000000052',0,0,'2023-01-26 07:47:45',NULL,NULL),('U00000005','R000038','ｓｆｓｄふぇｆ','T000033','M00000000183',0,0,'2023-01-30 01:28:04',NULL,NULL),('U00000001','R000039','この投稿は共有されたものです',NULL,'M00000000052',0,0,'2023-01-27 02:24:02','U00000001','R000038'),('U00000005','R000039','あｓだｗだｄ','T000033','M00000000184',0,0,'2023-01-30 01:29:23',NULL,NULL),('U00000001','R000040','この投稿は共有されたものです',NULL,'M00000000143',0,0,'2023-01-27 02:24:26','U00000012','R000003'),('U00000005','R000040','dasdaref','T000033','M00000000185',0,0,'2023-01-30 01:33:07',NULL,NULL),('U00000001','R000041','この投稿は共有されたものです',NULL,'M00000000150',0,0,'2023-01-27 05:17:11','U00000015','R000007'),('U00000005','R000041','だｓｄｗふぇ','T000033','M00000000186',0,0,'2023-01-30 01:52:15',NULL,NULL),('U00000001','R000042','この投稿は共有されたものです',NULL,'M00000000143',0,0,'2023-01-27 06:20:10','U00000012','R000003'),('U00000005','R000042','だｓだｗ','T000033','M00000000187',0,0,'2023-01-30 01:52:46',NULL,NULL),('U00000005','R000043','dasdw','T000001','M00000000194',0,0,'2023-01-30 05:15:22',NULL,NULL),('U00000005','R000044','dwdesef','T000001','M00000000195',0,0,'2023-01-30 05:18:36',NULL,NULL),('U00000005','R000045','だｗだｄｆ','T000033','M00000000196',0,0,'2023-01-31 00:44:08',NULL,NULL),('U00000001','R000046','あ','T000022','M00000000039',0,0,'2023-01-27 06:25:26',NULL,NULL),('U00000005','R000046','最高すぎてマジあげぽよ東大受験しちゃいますね～！','T000033','M00000000199',0,0,'2023-01-31 01:33:10',NULL,NULL),('U00000005','R000047','あ','T000033','M00000000199',0,0,'2023-01-31 01:42:55',NULL,NULL),('U00000005','R000048','やー！','T000033','M00000000200',0,0,'2023-01-31 02:18:32',NULL,NULL),('U00000001','R000049','a','T000001','M00000000172',0,0,'2023-01-27 06:57:39',NULL,NULL),('U00000005','R000049','引っ越しが先や！！！','T000033','M00000000201',0,0,'2023-01-31 02:31:55',NULL,NULL),('U00000001','R000064','この投稿は共有されたものです',NULL,'M00000000148',0,0,'2023-01-30 04:06:49','U00000015','R000004'),('U00000001','R000069','ｓ',NULL,'M00000000039',0,0,'2023-01-30 05:56:16',NULL,NULL),('U00000001','R000072','この投稿は共有されたものです',NULL,'M00000000198',0,0,'2023-01-31 03:19:05','U00000015','R000009'),('U00000001','R000073','この投稿は共有されたものです',NULL,'M00000000204',0,0,'2023-01-31 03:19:28','U00000025','R000003'),('U00000001','R000074','この投稿は共有されたものです',NULL,'M00000000182',0,0,'2023-01-31 03:23:11','U00000005','R000036'),('U00000001','R000075','この投稿は共有されたものです',NULL,'M00000000093',0,0,'2023-01-31 03:24:13','U00000003','R000004'),('U00000001','R000077','a','T000033','M00000000172',0,0,'2023-02-01 01:51:20',NULL,NULL),('U00000001','R000078','この投稿は共有されたものです',NULL,'M00000000148',0,0,'2023-02-01 02:34:43','U00000015','R000004'),('U00000001','R000079','この投稿は共有されたものです',NULL,'M00000000092',0,0,'2023-02-01 02:35:04','U00000003','R000011'),('U00000001','R000080','この投稿は共有されたものです',NULL,'M00000000096',0,0,'2023-02-01 02:35:49','U00000003','R000009');
/*!40000 ALTER TABLE `response` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `response_tag`
--

DROP TABLE IF EXISTS `response_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `response_tag` (
  `RESPONSE_CREATER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `RESPONSE_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `RESPONSE_TAG` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`RESPONSE_CREATER`,`RESPONSE_ID`,`RESPONSE_TAG`),
  KEY `response_tag_ibfk_2_idx` (`RESPONSE_ID`),
  KEY `response_tag_ibfk_3_idx` (`RESPONSE_TAG`),
  CONSTRAINT `response_tag_ibfk_1` FOREIGN KEY (`RESPONSE_CREATER`) REFERENCES `response` (`RESPONSE_CREATER`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `response_tag_ibfk_2` FOREIGN KEY (`RESPONSE_ID`) REFERENCES `response` (`RESPONSE_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `response_tag_ibfk_3` FOREIGN KEY (`RESPONSE_TAG`) REFERENCES `tag` (`TAG_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `response_tag`
--

LOCK TABLES `response_tag` WRITE;
/*!40000 ALTER TABLE `response_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `response_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `TAG_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TAG_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`TAG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES ('T0000001','最高');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thread`
--

DROP TABLE IF EXISTS `thread`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thread` (
  `THREAD_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `THREAD_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `THREAD_CREATER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JENRE_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `THREAD_SUBMIT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`THREAD_ID`),
  KEY `THREAD_CREATER` (`THREAD_CREATER`),
  KEY `JENRE_ID` (`JENRE_ID`),
  CONSTRAINT `thread_ibfk_1` FOREIGN KEY (`THREAD_CREATER`) REFERENCES `user` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `thread_ibfk_2` FOREIGN KEY (`JENRE_ID`) REFERENCES `jenre` (`JENRE_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thread`
--

LOCK TABLES `thread` WRITE;
/*!40000 ALTER TABLE `thread` DISABLE KEYS */;
INSERT INTO `thread` VALUES ('T000001','最近の流行りについて','U00000001','J0001','2022-11-30 15:00:00'),('T000002','センスある曲を教えてくれ','U00000002','J0002','2022-12-01 15:00:00'),('T000003','最近のJ-popってどうよ','U00000005','J0002','2022-12-02 15:00:00'),('T000004','2022年を代表する曲','U00000002','J0002','2022-12-03 15:00:00'),('T000005','暇なのでたてますた','U00000004','J0003','2022-12-04 15:00:00'),('T000006','日本の教育で一番大事なのは家庭科','U00000006','J0007','2022-12-05 15:00:00'),('T000008','音楽が！しりたい！！！','U00000003','J0002','2022-12-14 05:05:59'),('T000009','おれは敗北を知りたい','U00000002','J0005','2022-12-10 02:03:55'),('T000010','お前ら笑うな!!','U00000002','J0008','2023-01-11 03:25:17'),('T000013','music station','U00000002','J0002','2023-01-12 00:55:10'),('T000016','yee!!','U00000002','J0012','2023-01-12 02:11:18'),('T000017','hey guys , my name is majimasigeki','U00000002','J0013','2023-01-12 02:23:15'),('T000019','結婚したのか、俺以外のやつと','U00000002','J0003','2023-01-12 02:36:35'),('T000020','君はアンシェントロマン知っているか?','U00000002','J0004','2023-01-12 03:05:53'),('T000022','最近面白い動画を見つくしたので募集するわ','U00000002','J0001','2023-01-12 06:43:59'),('T000023','0','U00000002','J0002','2023-01-12 07:45:36'),('T000024','君は、駅伝リングを知っているか？','U00000002','J0004','2023-01-13 05:44:51'),('T000025','最近使っている洗顔フォーム','U00000002','J0006','2023-01-13 07:59:36'),('T000027','aa','U00000002','J0001','2023-01-16 01:01:27'),('T000030','音楽王を決めるスレッド','U00000002','J0002','2023-01-17 01:19:09'),('T000031','アニソン100選を決めようぜ','U00000001','J0002','2023-01-17 01:23:02'),('T000032','スポーツ','U00000003','J0003','2023-01-18 00:49:17'),('T000033','政治について語りましょう','U00000005','J0011','2023-01-18 03:26:06'),('T000034','OrangeStarさんの曲を共有したいです！\r\n気軽に投稿お願いしますー！','U00000011','J0002','2023-01-19 04:18:13'),('T000035','fsdd','U00000002','J0002','2023-01-20 00:47:52'),('T000036','最近のeスポーツ業界について','U00000002','J0003','2023-01-20 05:08:07'),('T000037','だ','U00000002','J0004','2023-01-23 03:13:29'),('T000038','yaayaaa','U00000003','J0006','2023-01-23 03:42:01'),('T000039','最近の音楽の流行り廃りってはやくね','U00000001','J0002','2023-01-25 02:42:34'),('T000042','お客さまぁ','U00000002','J0009','2023-01-26 02:44:40'),('T000043','はじめて作ってみました','U00000012','J0001','2023-01-27 02:15:11'),('T000044','S3A1の好きな曲を集めるスレッド','U00000001','J0001','2023-01-30 04:15:10'),('T000046','大川隆法先生の曲を共有したいです！気軽に投稿お願いします！','U00000016','J0002','2023-01-31 02:41:05'),('T000047','健康にいい動画集','U00000025','J0006','2023-01-31 02:50:37');
/*!40000 ALTER TABLE `thread` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `USER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USER_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PASSWORD` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MAIL` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SEARCH_NAME` varchar(20) NOT NULL,
  `INTRODUCTION` varchar(120) DEFAULT NULL,
  `ICON` varchar(100) DEFAULT '/img/icon/user.png',
  `OPEN_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `MAIL_UNIQUE` (`MAIL`),
  UNIQUE KEY `SEARCH_NAME_UNIQUE` (`SEARCH_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('U00000001','あべ　しゅんや','pass','shunya@fmail.com','@Abemaru_sql',NULL,'https://skpacket.s3.ap-northeast-1.amazonaws.com/icon/U00000001.jpg','2023-01-26 03:24:12'),('U00000002','よしだ　こうき','pass','yosida@famil.com','@yoshida_OvO','あいうえおキングが好きな21歳です。私の座右の銘は,[シンデレラは夜遊びしたから幸せになれた]です','https://skpacket.s3.ap-northeast-1.amazonaws.com/icon/U00000002.jpg','2023-01-26 03:24:12'),('U00000003','あさみ　かずや','pass','kazuya@fmail.com','@asami_pan','','https://skpacket.s3.ap-northeast-1.amazonaws.com/icon/U00000003.jpg','2023-01-26 03:24:12'),('U00000004','こいけ　れい','pass','koike@fmail.com','@Rei_Rei',NULL,'/img\\icon\\user.png','2023-01-26 03:24:12'),('U00000005','天竜人','pass','tuchida@fmail.com','@tuchidakeita','思想が左に傾き気味の腐女子です！/45↑/右翼','https://skpacket.s3.ap-northeast-1.amazonaws.com/icon/U00000005.jpg','2023-01-26 03:24:12'),('U00000006','さとう　ようこ','pass','satou@fmail.com','@satouDeeees',NULL,'/img\\icon\\user.png','2023-01-26 03:24:12'),('U00000007','やまだ　たろう','pass','yamada@fmail.com','@ya___ma',NULL,'/img\\icon\\user.png','2023-01-26 03:24:12'),('U00000008','ぽてちち','pass','aaaaa@fmail.com','@femi','第5好きの陰キャです。一日でもいいからリア充なってみてぇ                                           好きな物は女の子です♡♡オスは滅びろと思っております♡よろしくね〜?','/img\\icon\\user.png','2023-01-26 03:24:12'),('U00000009','name1','pass','id1@mail','@id1',NULL,'/img\\icon\\user.png','2023-01-26 03:24:12'),('U00000010','ツグミ','pass','tugumi@fmail.com','@taka_tsugu',NULL,'/img\\icon\\user.png','2023-01-26 03:24:12'),('U00000011','めろん','pass','melon@fmail.com','@melonP',NULL,'/img\\icon\\user.png','2023-01-26 03:24:12'),('U00000012','テスター','pass','tugu@fmail.com','@test001',NULL,'/img/icon/user.png','2023-01-26 03:24:12'),('U00000013','まさき','pass','masaki@fmail.com','@masaking',NULL,'/img/icon/user.png','2023-01-26 07:02:36'),('U00000014','おけ','pass','fef@u','@rty',NULL,'/img/icon/user.png','2023-01-27 02:19:45'),('U00000015','バブリアス','katukatu','baboo@reearth','@katuretu',NULL,'https://skpacket.s3.ap-northeast-1.amazonaws.com/icon/U00000015.jpg','2023-01-27 02:26:02'),('U00000016','admin','pass','a@a','@admin','','https://skpacket.s3.ap-northeast-1.amazonaws.com/icon/U00000016.jpg','2023-01-27 04:09:43'),('U00000017','ツグミ','password','C@a','@346',NULL,'/img/icon/user.png','2023-01-27 06:31:11'),('U00000018','tanabe','pass','tanabe@gmail.com','@tanabe',NULL,'/img/icon/user.png','2023-01-30 01:54:36'),('U00000019','石田','pass','aaa@aaa','@isida',NULL,'/img/icon/user.png','2023-01-30 04:24:47'),('U00000020','内田','pass','aaa@sss','@uchida',NULL,'/img/icon/user.png','2023-01-30 04:26:03'),('U00000021','渡邉','pass','a@aaaaa','@watanabe',NULL,'/img/icon/user.png','2023-01-30 05:01:11'),('U00000022','root','pass','b@b','@root',NULL,'https://skpacket.s3.ap-northeast-1.amazonaws.com/icon/U00000022.jpg','2023-01-31 01:46:44'),('U00000023','administrator','pass','c@c','@administrator',NULL,'/img/icon/user.png','2023-01-31 01:50:04'),('U00000024','ひらやま','pass','aaa@ssss','@hirayama',NULL,'/img/icon/user.png','2023-01-31 02:44:19'),('U00000025','springno','password','ab@aaa','@springno',NULL,'https://skpacket.s3.ap-northeast-1.amazonaws.com/icon/U00000025.jpg','2023-01-31 02:45:43');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `view_response`
--

DROP TABLE IF EXISTS `view_response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `view_response` (
  `RESPONSE_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `RESPONSE_CREATER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `VIEW_USER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `VIEW_LIKE` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`RESPONSE_ID`,`RESPONSE_CREATER`,`VIEW_USER`),
  KEY `view_response_ibfk_3_idx` (`RESPONSE_CREATER`),
  KEY `view_response_ibfk_2_idx` (`VIEW_USER`),
  CONSTRAINT `view_response_ibfk_1` FOREIGN KEY (`RESPONSE_ID`) REFERENCES `response` (`RESPONSE_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `view_response_ibfk_2` FOREIGN KEY (`VIEW_USER`) REFERENCES `user` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `view_response_ibfk_3` FOREIGN KEY (`RESPONSE_CREATER`) REFERENCES `response` (`RESPONSE_CREATER`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view_response`
--

LOCK TABLES `view_response` WRITE;
/*!40000 ALTER TABLE `view_response` DISABLE KEYS */;
INSERT INTO `view_response` VALUES ('R000002','U00000004','U00000002',0),('R000002','U00000004','U00000024',30),('R000002','U00000015','U00000001',0),('R000002','U00000018','U00000001',0),('R000002','U00000021','U00000016',0),('R000003','U00000002','U00000003',0),('R000003','U00000025','U00000001',0),('R000004','U00000015','U00000001',0),('R000004','U00000015','U00000002',0),('R000005','U00000016','U00000001',0),('R000005','U00000016','U00000003',0),('R000005','U00000016','U00000016',10),('R000006','U00000002','U00000002',0),('R000009','U00000015','U00000001',186),('R000009','U00000015','U00000016',0),('R000010','U00000002','U00000003',0),('R000013','U00000002','U00000001',0),('R000014','U00000002','U00000002',0),('R000018','U00000002','U00000002',0),('R000019','U00000016','U00000005',0),('R000021','U00000016','U00000001',0),('R000022','U00000016','U00000001',59),('R000022','U00000016','U00000016',4134),('R000022','U00000016','U00000023',4),('R000023','U00000005','U00000001',0),('R000036','U00000005','U00000001',0),('R000043','U00000005','U00000002',0),('R000044','U00000005','U00000001',0),('R000044','U00000005','U00000002',0),('R000044','U00000005','U00000005',0),('R000044','U00000005','U00000016',0),('R000047','U00000005','U00000001',0),('R000048','U00000005','U00000002',0),('R000048','U00000005','U00000025',88),('R000049','U00000005','U00000001',11),('R000049','U00000005','U00000002',0),('R000049','U00000005','U00000016',0);
/*!40000 ALTER TABLE `view_response` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-01 13:35:40
