-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bank_db
-- ------------------------------------------------------
-- Server version	8.0.20

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
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Rue Arago','Paris','France','7a',75002,'residence'),(2,'Rue Saint-Jacques','Paris','France','8',75005,'Residence des Mimosas'),(4,'Rue de Belgrade','Belgrade','Serbia','62',11060,'Dedinje'),(5,'Dr Djordja Joanovica','Novi Sad','Serbia','13',11060,'Detelinar'),(28,'Nevskiy prospekt','Saint Petersburg','Russia','453',445859,'Neva'),(29,'Knez Mihajlova','Beograd','Srbija','99',11000,'Terazije'),(30,'Melrose Avenue','Los Angeles','USA','458',4578521,'Hollywood'),(31,'Norastrasse','Zurich','Switzerland','756',756985,'Sihlfeld'),(32,'rue de Paris','Paris','France','85',75005,'Bois de Boulogne'),(33,'rue des Oliviers','Maubeuge','France','55',59300,'Résidence spéciale'),(34,'Mallorca','Mallorca','Espagne','45',458669,'Roland Garros'),(35,'Monte Carlo','Belgrade','Monaco','22',11000,'Residence of tennis'),(36,'Roma','Roma','Italia','45',256333,'Venice beach'),(37,'Wien','Wien','Austria','22',4569,'Wien'),(39,'Pere Perica','Beograd','Srbija','55',11035,'Beograd'),(40,'Paris','Paris','France','62',75005,'Champs Elysées'),(41,'Via Dolorosa','Madrid','Spain','22',4563998,'Mallorca'),(42,'New Your','New York','USA','47',85651651,'Manhattan'),(43,'Tverskaya','Moscow','Russia','44a',209333,'Moscow'),(44,'Espana','Barcelona','Spain','45c',47555,'Catalogne'),(45,'Mitchourinskiy prospekt','Moscow','Russia','741b',458552,'Moscow'),(46,'Sretenskiy bulvar','Moscou','Russie','45a',412555,'Moscou'),(47,'Novi Sad','Novi Sad','Srbija','457a',75222,'Detelinar'),(48,'Sheinkin','Tel Aviv','Israel','45d',458663,'Tel Aviv');
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
  PRIMARY KEY (`id_bank_account`),
  KEY `bank_account_FKIndex1` (`bank_account_type_id_bank_account_type`),
  KEY `bank_account_FKIndex2` (`user_account_id_user_account`),
  KEY `bank_account_FKIndex3` (`bank_rules_id_bank_rules`),
  CONSTRAINT `bank_account_ibfk_1` FOREIGN KEY (`bank_account_type_id_bank_account_type`) REFERENCES `bank_account_type` (`id_bank_account_type`),
  CONSTRAINT `bank_account_ibfk_2` FOREIGN KEY (`user_account_id_user_account`) REFERENCES `user_account` (`id_user_account`),
  CONSTRAINT `bank_account_ibfk_3` FOREIGN KEY (`bank_rules_id_bank_rules`) REFERENCES `bank_rules` (`id_bank_rules`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account_type`
--

LOCK TABLES `bank_account_type` WRITE;
/*!40000 ALTER TABLE `bank_account_type` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_rules`
--

LOCK TABLES `bank_rules` WRITE;
/*!40000 ALTER TABLE `bank_rules` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documents`
--

LOCK TABLES `documents` WRITE;
/*!40000 ALTER TABLE `documents` DISABLE KEYS */;
INSERT INTO `documents` VALUES (1,38,'uploads\\3882877075.jpg','82877075.jpg'),(2,38,'uploads\\38images.jpg','images.jpg'),(3,38,'uploads\\38nature-3294681_960_720.jpg.webp','nature-3294681_960_720.jpg.webp'),(4,39,'uploads\\3982877075.jpg','Identification document'),(5,39,'uploads\\39images.jpg','Home proof document'),(6,39,'uploads\\39nature-3294681_960_720.jpg.webp','Salary proof document'),(7,40,'uploads\\4082877075.jpg','Identification document'),(8,40,'uploads\\40images.jpg','Home proof document'),(9,40,'uploads\\40nature-3294681_960_720.jpg.webp','Salary proof document'),(10,41,'uploads\\4182877075.jpg','Identification document'),(11,41,'uploads\\41images.jpg','Home proof document'),(12,41,'uploads\\41nature-3294681_960_720.jpg.webp','Salary proof document'),(13,42,'uploads\\4282877075.jpg','Identification document'),(14,42,'uploads\\42images.jpg','Home proof document'),(15,42,'uploads\\42nature-3294681_960_720.jpg.webp','Salary proof document'),(16,43,'uploads\\4382877075.jpg','Identification document'),(17,43,'uploads\\43images.jpg','Home proof document'),(18,43,'uploads\\43nature-3294681_960_720.jpg.webp','Salary proof document'),(19,44,'uploads\\4482877075.jpg','Identification document'),(20,44,'uploads\\44images.jpg','Home proof document'),(21,44,'uploads\\44nature-3294681_960_720.jpg.webp','Salary proof document'),(22,45,'uploads\\4582877075.jpg','Identification document'),(23,45,'uploads\\45images.jpg','Home proof document'),(24,45,'uploads\\45nature-3294681_960_720.jpg.webp','Salary proof document');
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
  PRIMARY KEY (`id_notification`),
  KEY `notification_FKIndex1` (`user_account_id_user_account`),
  CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`user_account_id_user_account`) REFERENCES `user_account` (`id_user_account`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,5,'Welcome');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `bank_account_id_bank_account_from` int unsigned NOT NULL,
  `bank_account_id_bank_account_to` int unsigned NOT NULL,
  `amount` float NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id_transaction`),
  KEY `transaction_from` (`bank_account_id_bank_account_from`),
  KEY `transaction_to` (`bank_account_id_bank_account_to`),
  KEY `transaction_FKIndex3` (`transaction_type_id_transaction_type`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`bank_account_id_bank_account_from`) REFERENCES `bank_account` (`id_bank_account`),
  CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`bank_account_id_bank_account_to`) REFERENCES `bank_account` (`id_bank_account`),
  CONSTRAINT `transaction_ibfk_3` FOREIGN KEY (`transaction_type_id_transaction_type`) REFERENCES `transaction_type` (`id_transaction_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_type`
--

LOCK TABLES `transaction_type` WRITE;
/*!40000 ALTER TABLE `transaction_type` DISABLE KEYS */;
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
  `number_of_children` int unsigned DEFAULT NULL,
  PRIMARY KEY (`id_user_account`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `user_account_FKIndex1` (`address_id_address`),
  KEY `user_account_FKIndex2` (`role_id_role`),
  CONSTRAINT `user_account_ibfk_1` FOREIGN KEY (`address_id_address`) REFERENCES `address` (`id_address`),
  CONSTRAINT `user_account_ibfk_2` FOREIGN KEY (`role_id_role`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,2,1,'Jack','Borrow','jackb','password123','jack@mailinator.com','555333','2020-05-17',NULL,0,'single',0),(2,2,2,'Zika','Zikic','zika','password1234','zika@zikic.com','0123456789','2020-05-22',NULL,0,'married',5),(4,1,4,'Marko','Markovic','admin','admin','admin@admin.ru','045215698','2020-05-24',NULL,1,NULL,0),(5,3,5,'Marko','Jovanovic','agent007','pass007','agent007@agent.com','012345678','2020-05-24','2020-06-06',1,NULL,0),(25,3,28,'Zikan','Mika','mikica','mikica','mika@mila.rs','+78125698638','2020-06-05',NULL,1,NULL,0),(26,3,29,'Dejan','Savicevic','dejo','dejo','savicevic@mail.sr','0649645538','2020-06-07',NULL,1,NULL,0),(27,3,30,'Leia','Princess','leia','leia','leia@starwars.com','+1586325699874','2020-06-07',NULL,1,NULL,0),(28,3,31,'Roger','Federer','roger','roger','federer@mail.ch','984586526','2020-06-11',NULL,1,NULL,0),(29,2,32,'Blaise','Pascal','pascal','pascal','pascal@pascal.fr','0152365988','2020-06-12',NULL,0,'Married',8),(30,2,33,'Marko','Askovic','marko','marko','marko@marko.fr','0355696637','2020-06-12',NULL,0,'single',5),(31,2,34,'Nadal','Rafael','rafael','rafael','rafael@mallorca.es','56988635','2020-06-12',NULL,0,'Married',0),(32,2,35,'Djokovic','Novak','novak','novak','novak@mail.sr','5146453154','2020-06-13',NULL,0,'Married',2),(33,2,36,'Veronica','Bertolucci','veronica','veronica','veronica@italia.it','+3466555888666','2020-06-13',NULL,0,'married',3),(34,2,37,'Amadeus','Mocart','amadeus','amadeus','amadeux@wien.ar','+25365856','2020-06-13',NULL,0,'single',47),(36,2,39,'milan','milan','milan','milan','milan@sr.sr','25688','2020-06-13',NULL,0,'married',5),(37,2,40,'Emmanuel','Macron','manu','manu','manu@paris.fr','0122556699','2020-06-13',NULL,0,'married',2),(38,2,41,'picasso','Picasso','picasso','pikaso','picasso@spain.es','5465341','2020-06-13',NULL,0,'single',10),(39,2,42,'Stefan','Milenkovic','violina','violina','violina@mail.rs','+125666598','2020-06-13',NULL,0,'married',1),(40,2,43,'Monte','Cristo','cristo','cristo','cristo@cristo.fr','6561544','2020-06-13',NULL,0,'single',0),(41,2,44,'Don','Kihot','kihot','kihot','kihot@mail.es','515643','2020-06-13',NULL,0,'single',4),(42,2,45,'Victoria','Victoria','victoria','victoria','victoria@vica.ru','+7499556885','2020-06-13',NULL,0,'single',0),(43,2,46,'Anatoliy','Karpov','karpov','karpov','karpov@chess.ru','+74958863322','2020-06-13',NULL,0,'married',3),(44,2,47,'Milovanovic','Milovan','milovan','milovan','milovan@srbija.rs','0114756889','2020-06-13',NULL,0,'single',0),(45,2,48,'Neil','Horowicz','neil','neil','neil@neil.il','+97255682234','2020-06-13',NULL,0,'married',3);
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

-- Dump completed on 2020-06-14  9:25:22
