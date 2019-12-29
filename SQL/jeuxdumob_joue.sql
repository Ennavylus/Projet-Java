CREATE DATABASE  IF NOT EXISTS `jeuxdumob` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `jeuxdumob`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: jeuxdumob
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `joue`
--

DROP TABLE IF EXISTS `joue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `joue` (
  `id_Utilisateur` int(11) NOT NULL,
  `id_Partie` int(11) NOT NULL,
  `win` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_Utilisateur`,`id_Partie`),
  KEY `joue_Partie0_FK` (`id_Partie`),
  CONSTRAINT `joue_Partie0_FK` FOREIGN KEY (`id_Partie`) REFERENCES `partie` (`id`),
  CONSTRAINT `joue_Utilisateur_FK` FOREIGN KEY (`id_Utilisateur`) REFERENCES `utilisateur` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `joue`
--

LOCK TABLES `joue` WRITE;
/*!40000 ALTER TABLE `joue` DISABLE KEYS */;
INSERT INTO `joue` VALUES (1,25,0),(1,26,1),(1,28,1),(1,29,0),(1,30,0),(26,11,1),(26,22,0),(26,23,1),(26,24,0),(27,12,1),(27,13,0),(27,14,1),(28,15,0),(28,16,1),(28,17,0),(28,18,1),(28,27,0),(29,19,0),(29,20,1),(29,21,1);
/*!40000 ALTER TABLE `joue` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-29 17:48:26
