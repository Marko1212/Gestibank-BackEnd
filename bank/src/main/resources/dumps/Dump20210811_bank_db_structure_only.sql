-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bank_db
-- ------------------------------------------------------
-- Server version	8.0.20

CREATE DATABASE  IF NOT EXISTS `bank_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bank_db`;

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
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-11 14:25:22
