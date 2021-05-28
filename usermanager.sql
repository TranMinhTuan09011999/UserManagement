CREATE DATABASE  IF NOT EXISTS `usermanager` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `usermanager`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: usermanager
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `user_name` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('aa','aa@gmail.com','aa','aa','{sha256}7c89a90edd18948b6fe6955ef8784017ba1e1005410f6ff8a445f5e444342bdd4cea95066b3ee440'),('aaa','aaa','aaaa','aaa','aaaa'),('admin','admin@gmail.com','Tuan','Tran','{sha256}f3a4f37f6637198e5bb9efa0d9be980019f939a76f54b5af8089bcce5fcc642c10db99562120fcea'),('tan','tan@gmail.com','tan','duy','{sha256}cbce386664b23b1547876f5f566bd60eeb29af85c3854c5e002d0f901ab2e1690266d75a81c4ce7b'),('tuan','tuan@gmail.com','tuan','minh','{sha256}d566d1820acdfdc5e4f3607599447f36d5eb8a7e05b62e3504f1888f1a51c43bca8b9946b2ad6b3a'),('tuan1','tuan1@gmail.com','tuan1','minh1','{sha256}6cc3c58537eb9bff5dc28c91b48adab2ca959cdf9b8600dbc83aa565b9cd7769b17d94affb84ea04'),('tuan10','tuan10@gmail.com','tuan10','minh10','{sha256}256568fc93fb1da018bdc7bfae967ab4661c0c14686bdb0c67dd9405f4d05ec58d209517b040df42'),('tuan11','tuan11@gmail.com','tuan11','minh11','{sha256}13ba04e725c4672bd4f9e35fc26b1863fa9bda8b3d7c044462df8fef3bb171980908e85ef0373d98'),('tuan12','tuan12@gmail.com','tuan12','minh12','{sha256}d4e8e5c96c2fc2dfac802215df886ec195da3fcb8ef8059e5333511eed00852c3db8967640731b56'),('tuan13','tuan13@gmail.com','tuan13','minh13','{sha256}c2531a5e72021076501c37b38846a90ebacf43dc3d88d9f95fc78708fce76f524d4305998b373a87'),('tuan2','tuan2@gmail.com','tuan2','minh2','{sha256}d24ef3c27cad1ad7d2673d9409ae478d2fbb233b47616cfd9b40767082d154dddc0704b29fd4e755'),('tuan3','tuan3@gmail.com','tuan3','minh3','{sha256}38a40494cc60bc9ea9d3195546cd5dbbc590c3e474bcdb48cb7677a5b53dcf26aa8045952d223371'),('tuan4','tuan4@gmail.com','tuan4','minh4','{sha256}0f25a93592f2d75f3f58d8a24cfb5acff3b18f33884a1b50e22aafab3a2c196652c0bdefaa1c86a8'),('tuan5','tuan5@gmail.com','tuan5','minh5','{sha256}5dc669b6c82abc6b0421645f90e86a6608a2e2600c765ef9a9ccca98bcde3806285968d60f6f861a'),('tuan6','tuan6@gmail.com','tuan6','minh6','{sha256}525359a705dfe34ab51afee491c72ee5d7aeac6e37798c9c49d42ff05df9db359d478949bd25aba0'),('tuan7','tuan7@gmail.com','tuan7','minh7','{sha256}ede817e3d2276d26a58377bd22d394cf760326e9d879b892a9a3e79196bafdfda49fb001d5c4a3cd'),('tuan8','tuan8@gmail.com','tuan8','minh8','{sha256}64ccc3fa33f8c4d0addfcf61897cba9577ab5878b7813dbf762001f146224e7363ffb771438406fd'),('tuan9','tuan9@gmail.com','tuan9','minh9','{sha256}554bcc0d1253a4ee2e737753db58f3a8b73b90de1dca5907f2a2e8897a4b44ada509995fe7be47d8');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'usermanager'
--

--
-- Dumping routines for database 'usermanager'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-28 17:22:17
