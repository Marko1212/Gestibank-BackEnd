-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bank_db_test
-- ------------------------------------------------------
-- Server version	8.0.20

CREATE DATABASE  IF NOT EXISTS `bank_db_test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bank_db_test`;

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id_address` int unsigned NOT NULL AUTO_INCREMENT,
  `street` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `home_number` varchar(255) NOT NULL,
  `zip` int unsigned NOT NULL,
  `additional_info` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_address`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (116,'oFttPH','MmUDOm','CAWtQN','rcg',303657,'vNjWyM'),(117,'NQtybB','BnQZTJ','kveoMt','AgI',675750,'pzzvIw'),(118,'AHqRgU','BmAJGW','uIfVDC','xKm',109386,'nKFsmo'),(119,'jqFKtB','eQKwqV','xJbYLq','5Rr',571016,'LSAOsD');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_account`
--

DROP TABLE IF EXISTS `bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank_account` (
  `id_bank_account` int unsigned NOT NULL AUTO_INCREMENT,
  `bank_rules_id_bank_rules` int unsigned NOT NULL,
  `user_account_id_user_account` int unsigned NOT NULL,
  `bank_account_type_id_bank_account_type` int unsigned NOT NULL,
  `bank_account_number` varchar(255) NOT NULL,
  `bank_account_status` tinyint(1) NOT NULL,
  `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_bank_account`),
  KEY `bank_account_FKIndex1` (`bank_account_type_id_bank_account_type`),
  KEY `bank_account_FKIndex2` (`user_account_id_user_account`),
  KEY `bank_account_FKIndex3` (`bank_rules_id_bank_rules`),
  CONSTRAINT `bank_account_ibfk_1` FOREIGN KEY (`bank_account_type_id_bank_account_type`) REFERENCES `bank_account_type` (`id_bank_account_type`),
  CONSTRAINT `bank_account_ibfk_2` FOREIGN KEY (`user_account_id_user_account`) REFERENCES `user_account` (`id_user_account`),
  CONSTRAINT `bank_account_ibfk_3` FOREIGN KEY (`bank_rules_id_bank_rules`) REFERENCES `bank_rules` (`id_bank_rules`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account`
--

LOCK TABLES `bank_account` WRITE;
/*!40000 ALTER TABLE `bank_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `bank_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_account_type`
--

DROP TABLE IF EXISTS `bank_account_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank_account_type` (
  `id_bank_account_type` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id_bank_account_type`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account_type`
--

LOCK TABLES `bank_account_type` WRITE;
/*!40000 ALTER TABLE `bank_account_type` DISABLE KEYS */;
INSERT INTO `bank_account_type` VALUES (1,'Current'),(2,'Current-LIMIT-1000'),(3,'Current-LIMIT-2000'),(4,'Current-LIMIT-3000'),(5,'Current-LIMIT-5000'),(6,'Current-LIMIT-10000'),(7,'Current-LIMIT-20000'),(8,'Current-LIMIT-50000'),(9,'Saving');
/*!40000 ALTER TABLE `bank_account_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_rules`
--

DROP TABLE IF EXISTS `bank_rules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank_rules` (
  `id_bank_rules` int unsigned NOT NULL AUTO_INCREMENT,
  `rule_name` varchar(255) NOT NULL,
  `percent` float NOT NULL,
  PRIMARY KEY (`id_bank_rules`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_rules`
--

LOCK TABLES `bank_rules` WRITE;
/*!40000 ALTER TABLE `bank_rules` DISABLE KEYS */;
INSERT INTO `bank_rules` VALUES (1,'Current rule',0),(2,'Current-rule-1',1),(3,'Current-rule-2',2),(4,'Current-rule-3',3),(5,'Current-rule-4',4),(6,'Current-rule-5',5),(7,'Current-rule-18',18),(8,'Saving-rule-2',2);
/*!40000 ALTER TABLE `bank_rules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documents`
--

DROP TABLE IF EXISTS `documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `documents` (
  `id_documents` int unsigned NOT NULL AUTO_INCREMENT,
  `user_account_id_user_account` int unsigned NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_documents`),
  KEY `documents_FKIndex1` (`user_account_id_user_account`),
  CONSTRAINT `documents_ibfk_1` FOREIGN KEY (`user_account_id_user_account`) REFERENCES `user_account` (`id_user_account`)
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documents`
--

LOCK TABLES `documents` WRITE;
/*!40000 ALTER TABLE `documents` DISABLE KEYS */;
/*!40000 ALTER TABLE `documents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id_notification` int unsigned NOT NULL AUTO_INCREMENT,
  `user_account_id_user_account` int unsigned NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `transaction_id_transaction` int unsigned DEFAULT NULL,
  PRIMARY KEY (`id_notification`),
  KEY `notification_FKIndex1` (`user_account_id_user_account`),
  KEY `notification_ibfk_2` (`transaction_id_transaction`),
  CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`user_account_id_user_account`) REFERENCES `user_account` (`id_user_account`),
  CONSTRAINT `notification_ibfk_2` FOREIGN KEY (`transaction_id_transaction`) REFERENCES `transaction` (`id_transaction`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `id_request` int unsigned NOT NULL AUTO_INCREMENT,
  `user_account_id_user_account_from` int unsigned NOT NULL,
  `user_account_id_user_account_to` int unsigned DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `time` datetime NOT NULL,
  `request_status` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_request`),
  KEY `request_from` (`user_account_id_user_account_from`),
  KEY `request_to` (`user_account_id_user_account_to`),
  CONSTRAINT `request_ibfk_1` FOREIGN KEY (`user_account_id_user_account_from`) REFERENCES `user_account` (`id_user_account`),
  CONSTRAINT `request_ibfk_2` FOREIGN KEY (`user_account_id_user_account_to`) REFERENCES `user_account` (`id_user_account`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id_role` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin'),(2,'client'),(3,'agent');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id_transaction` int unsigned NOT NULL AUTO_INCREMENT,
  `transaction_type_id_transaction_type` int unsigned NOT NULL,
  `bank_account_id_bank_account_from` int unsigned DEFAULT NULL,
  `bank_account_id_bank_account_to` int unsigned DEFAULT NULL,
  `amount` float NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id_transaction`),
  KEY `transaction_from` (`bank_account_id_bank_account_from`),
  KEY `transaction_to` (`bank_account_id_bank_account_to`),
  KEY `transaction_FKIndex3` (`transaction_type_id_transaction_type`),
  CONSTRAINT `bank_account_from_fk` FOREIGN KEY (`bank_account_id_bank_account_from`) REFERENCES `bank_account` (`id_bank_account`),
  CONSTRAINT `bank_account_to_fk` FOREIGN KEY (`bank_account_id_bank_account_to`) REFERENCES `bank_account` (`id_bank_account`),
  CONSTRAINT `transaction_ibfk_3` FOREIGN KEY (`transaction_type_id_transaction_type`) REFERENCES `transaction_type` (`id_transaction_type`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_type`
--

DROP TABLE IF EXISTS `transaction_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_type` (
  `id_transaction_type` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_transaction_type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_type`
--

LOCK TABLES `transaction_type` WRITE;
/*!40000 ALTER TABLE `transaction_type` DISABLE KEYS */;
INSERT INTO `transaction_type` VALUES (1,'Credit'),(2,'Bankomat'),(3,'Transfer');
/*!40000 ALTER TABLE `transaction_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_account` (
  `id_user_account` int unsigned NOT NULL AUTO_INCREMENT,
  `role_id_role` int unsigned NOT NULL,
  `address_id_address` int unsigned NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `valid` tinyint(1) NOT NULL,
  `marriage_status` varchar(255) DEFAULT NULL,
  `number_of_children` varchar(10) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_user_account`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `token_UNIQUE` (`token`),
  KEY `user_account_FKIndex1` (`address_id_address`),
  KEY `user_account_FKIndex2` (`role_id_role`),
  CONSTRAINT `user_account_ibfk_1` FOREIGN KEY (`address_id_address`) REFERENCES `address` (`id_address`),
  CONSTRAINT `user_account_ibfk_2` FOREIGN KEY (`role_id_role`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (112,3,116,'yGuGiP','DKETYz','qoqsaA','$2a$10$jG7URSqHJQFoR5q.wUGDrOkeO0HcjJ0ewByeCl2FEQqgXkfVK5yYS','JZJDwr@mailinator.com','2777854647','2021-08-09',NULL,1,NULL,NULL,NULL),(113,3,117,'fSVtSI','Sqimjs','qrcgMd','$2a$10$ZlEYPni3FT02la08OR5kZOsxJ4a9hjJpClOt3rashRtk.jUN8hruK','FgcRYQ@mailinator.com','1181743255','2021-08-09',NULL,1,NULL,NULL,NULL),(114,3,118,'wlSRPq','kaJXqV','wNIlmk','$2a$10$pNI0ekQMeeGI.zqVsswKke5VAJNwM2TSVhX8WenmNOTZzdxUFoxYi','zQJcuN@mailinator.com','4593727689','2021-08-09',NULL,1,NULL,NULL,NULL),(115,3,119,'VMpHVt','jDsLYz','MSdIJy','$2a$10$NPf0AIV5VqnedFJB5aN79u4kQCyicTcMHkbmzrEVSEmYc/7YEn2YK','lWPxbz@mailinator.com','2387537770','2021-08-09',NULL,1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-09 18:15:37
