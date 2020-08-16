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
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Rue Arago','Paris','France','7a',75002,'residence'),(2,'Rue Saint-Jacques','Paris','France','8',75005,'Residence des Mimosas'),(4,'Rue de Belgrade','Belgrade','Serbia','62',11060,'Dedinje'),(5,'Dr Djordja Joanovica','Novi Sad','Serbia','13',11060,'Detelinar'),(28,'Nevskiy prospekt','Saint Petersburg','Federation de Russie','453',445859,'Neva'),(29,'Knez Mihajlova','Beograd','Serbia','99',11000,'Terazije'),(30,'Melrose Avenue','Los Angeles','United States','458',4578521,'Hollywood'),(31,'Norastrasse','Zurich','Switzerland','756',756985,'Sihlfeld'),(32,'rue de Paris','Paris','France','85',75005,'Bois de Boulogne'),(33,'rue des Oliviers','Maubeuge','France','55',59300,'Résidence spéciale'),(34,'Mallorca','Mallorca','Espagne','45',458669,'Roland Garros'),(35,'Monte Carlo','Belgrade','Monaco','22',11000,'Residence of tennis'),(36,'Roma','Roma','Italia','45',256333,'Venice beach'),(37,'Wien','Wien','Austria','22',4569,'Wien'),(39,'Pere Perica','Beograd','Srbija','55',11035,'Beograd'),(40,'Paris','Paris','France','62',75005,'Champs Elysées'),(41,'Via Dolorosa','Madrid','Spain','22',4563998,'Mallorca'),(42,'New Your','New York','USA','47',85651651,'Manhattan'),(43,'Tverskaya','Moscow','Russia','44a',209333,'Moscow'),(44,'Espana','Barcelona','Spain','45c',47555,'Catalogne'),(45,'Mitchourinskiy prospekt','Moscow','Russia','741b',458552,'Moscow'),(46,'Sretenskiy bulvar','Moscou','Russie','45a',412555,'Moscou'),(47,'Novi Sad','Novi Sad','Srbija','457a',75222,'Detelinar'),(48,'Sheinkin','Tel Aviv','Israel','45d',458663,'Tel Aviv'),(49,'Kalemegdan','Beograd','Serbie','26',11000,'Belgrade'),(50,'Hollywood avenue','Seattle','USA','45',258999,'Beverly Hills'),(53,'rue de Buenos Aires','Buenos Aires','Argentina','25',258933,'Tango'),(54,'Cuba','Havana','Cuba','258',654945,'Salsa la Cucaracha'),(55,'Rue Gérard de Perfontaine','Valenciennes','France','11',59300,'Résidence Verley'),(56,'Beogradska ulica','Novi Sad','Serbie','17',125869,'Detelinar'),(57,'Tverskaya street','Moscow','Russia','145',258634,'Mayakovskaya'),(58,'Novoslobodskaya street','Moscow','Russia','58',445859,'Novoslobodskaya'),(59,'Jaffa Road','Jerusalem','Israel','77',458699,'Old City'),(60,'Novodievichi','Kiev','Ukraine','45a',45689,'Ukraine'),(61,'rue de Paris','Paris','France','147',59300,'Guadeloupe'),(62,'rue de Bordeaux','Bordeaux','France','98A',74369,'Bordeaux'),(63,'rue des Inconnus','Paris','France','0',75001,'Paris'),(64,'Guiliarovskogo','Saint Petersburg','Russia','26A',741258,'Piter'),(65,'Zica','Zica','Srbija','53',85369,'Manastir Zica'),(66,'rue des Fleurs','Paris','France','44c',75016,'Paris'),(67,'street','Moscow','Russia','56856',206322,'Russia'),(68,'Terazije','Beograd','Srbija','45',11000,'Beograd'),(69,'Ganina Yama','Yekaterinburg','Russia','44',254789,'Russia'),(70,'Pere Perica','Beograd','Srbija','44',589663,'Beograd'),(71,'Ulica Dr Djordja Joanovica','Beograd','Srbija','44',11060,'Karaburma'),(72,'Nevski','Belgrade','Srbija','10',541515,'Palilula'),(73,'Tverskaya','Zelenograd','Russia','44',453245,'Moscow'),(74,'r','sdq','Rusija','45',549651,'Beograd'),(75,'ds','sd','ds','ds',58932,'sd'),(76,'cvb','vb','vb','vb',201999,'vb'),(78,'Nevskiy prospekt','Saint Petersburg','Russia','88888888',445856,'xc'),(79,'4','fd','Russia','52',445856,''),(80,'ju','ju','France','ju',59300,''),(81,'qsdsdsq','Saint Petersburg','Russia','qsdds',445856,'Neva'),(82,'Afon','Afon','Afon','44',444444,'panteleimon'),(83,'dfs','dsf','Russia','df',445856,'rue de Paris'),(88,'dfgf','dfdf','France','qsd',59300,''),(90,'ghj','Saint Petersburg','Russia','ghj',445859,''),(92,'gfd','gfd','Russia','hg',445856,''),(93,'jg','hjg','Russia','jkh',445856,'');
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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account`
--

LOCK TABLES `bank_account` WRITE;
/*!40000 ALTER TABLE `bank_account` DISABLE KEYS */;
INSERT INTO `bank_account` VALUES (1,1,55,1,'5279105144',1,'2020-07-31 17:00:03'),(2,1,65,1,'0901757339',1,'2020-07-31 17:00:03'),(3,1,66,1,'1431378541',1,'2020-07-31 17:00:03'),(4,1,67,1,'7449191496',1,'2020-07-31 17:00:03'),(6,1,68,1,'3398368471',1,'2020-07-31 17:00:03'),(8,1,69,1,'1566178953',1,'2020-07-31 17:00:03'),(10,1,64,1,'8424066000',1,'2020-08-01 20:01:00'),(11,1,60,1,'5417708981',1,'2020-08-01 20:01:58'),(12,1,59,1,'7116946807',1,'2020-08-01 20:02:20'),(13,1,57,1,'4590301382',1,'2020-08-01 21:32:05'),(14,1,58,1,'6092297287',1,'2020-08-01 21:32:05'),(15,1,70,1,'4713635046',1,'2020-08-01 21:32:05'),(16,1,42,1,'7365575045',1,'2020-08-02 07:38:08'),(17,1,31,1,'4685025818',1,'2020-08-02 07:39:26'),(18,1,29,4,'3824607950',1,'2020-08-02 09:36:33'),(20,1,54,1,'9594449934',1,'2020-08-05 08:33:35'),(22,1,2,1,'6254639599',1,'2020-08-10 08:30:46'),(23,5,43,4,'1228397474',1,'2020-08-10 08:30:47'),(24,1,90,4,'0152870169',1,'2020-08-12 16:30:39'),(28,8,90,9,'3908228078',0,'2020-08-15 20:41:13'),(29,8,90,9,'0311121120',0,'2020-08-16 07:44:39'),(30,8,90,9,'0562828249',0,'2020-08-16 07:50:42'),(31,8,90,9,'3171010592',0,'2020-08-16 07:59:54'),(32,8,90,9,'1725943138',0,'2020-08-16 08:00:55'),(33,8,90,9,'4370934555',0,'2020-08-16 08:02:46'),(34,8,90,9,'3765164994',0,'2020-08-16 08:08:15'),(35,8,90,9,'0473139307',0,'2020-08-16 08:09:28'),(36,8,90,9,'3645246676',0,'2020-08-16 08:12:13'),(37,8,90,9,'6787306345',0,'2020-08-16 08:14:06'),(38,8,90,9,'2213133401',1,'2020-08-16 08:51:45'),(39,8,66,9,'9611447050',0,'2020-08-16 10:32:20'),(40,8,66,9,'0937645538',0,'2020-08-16 10:33:39'),(41,8,66,9,'1185989319',0,'2020-08-16 10:35:58'),(42,8,66,9,'4866840385',0,'2020-08-16 10:36:39'),(43,8,67,9,'1962206436',0,'2020-08-16 10:39:51'),(44,8,67,9,'3204781589',0,'2020-08-16 10:41:25'),(45,8,67,9,'9328779369',1,'2020-08-16 10:42:37'),(46,8,66,9,'0491860648',0,'2020-08-16 10:44:37'),(47,8,59,9,'2559675805',0,'2020-08-16 10:45:30'),(48,8,59,9,'3431137942',0,'2020-08-16 10:49:30'),(49,8,42,9,'9579471197',1,'2020-08-16 10:54:21'),(50,8,66,9,'6631731609',1,'2020-08-16 10:56:56');
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
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documents`
--

LOCK TABLES `documents` WRITE;
/*!40000 ALTER TABLE `documents` DISABLE KEYS */;
INSERT INTO `documents` VALUES (40,55,'uploads\\55\\forest.jpg','Identification document'),(41,55,'uploads\\55\\nature.webp','Home proof document'),(42,55,'uploads\\55\\landscape.jpg','Salary proof document'),(43,56,'uploads\\56\\forest.jpg','Identification document'),(44,56,'uploads\\56\\landscape.jpg','Home proof document'),(45,56,'uploads\\56\\nature.webp','Salary proof document'),(46,57,'uploads\\57\\nature.webp','Identification document'),(47,57,'uploads\\57\\landscape.jpg','Home proof document'),(48,57,'uploads\\57\\forest.jpg','Salary proof document'),(49,58,'uploads\\58\\landscape.jpg','Identification document'),(50,58,'uploads\\58\\nature.webp','Home proof document'),(51,58,'uploads\\58\\forest.jpg','Salary proof document'),(52,59,'uploads\\59\\Front-end developer task - Food blog.pdf','Identification document'),(53,59,'uploads\\59\\forest.jpg','Home proof document'),(54,59,'uploads\\59\\nature.webp','Salary proof document'),(55,60,'uploads\\60\\landscape.jpg','Identification document'),(56,60,'uploads\\60\\nature.webp','Home proof document'),(57,60,'uploads\\60\\Front-end developer task - Food blog.pdf','Salary proof document'),(58,61,'uploads\\61\\nature.webp','Identification document'),(59,61,'uploads\\61\\forest.jpg','Home proof document'),(60,61,'uploads\\61\\landscape.jpg','Salary proof document'),(61,62,'uploads\\62\\forest.jpg','Identification document'),(62,62,'uploads\\62\\landscape.jpg','Home proof document'),(63,62,'uploads\\62\\nature.webp','Salary proof document'),(64,64,'uploads\\64\\forest.jpg','Identification document'),(65,64,'uploads\\64\\landscape.jpg','Home proof document'),(66,64,'uploads\\64\\nature.webp','Salary proof document'),(67,65,'uploads\\65\\forest.jpg','Identification document'),(68,65,'uploads\\65\\landscape.jpg','Home proof document'),(69,65,'uploads\\65\\nature.webp','Salary proof document'),(70,66,'uploads\\66\\forest.jpg','Identification document'),(71,66,'uploads\\66\\landscape.jpg','Home proof document'),(72,66,'uploads\\66\\nature.webp','Salary proof document'),(73,67,'uploads\\67\\forest.jpg','Identification document'),(74,67,'uploads\\67\\landscape.jpg','Home proof document'),(75,67,'uploads\\67\\nature.webp','Salary proof document'),(76,68,'uploads\\68\\forest.jpg','Identification document'),(77,68,'uploads\\68\\nature.webp','Home proof document'),(78,68,'uploads\\68\\landscape.jpg','Salary proof document'),(79,69,'uploads\\69\\forest.jpg','Identification document'),(80,69,'uploads\\69\\landscape.jpg','Home proof document'),(81,69,'uploads\\69\\nature.webp','Salary proof document'),(82,70,'uploads\\70\\forest.jpg','Identification document'),(83,70,'uploads\\70\\landscape.jpg','Home proof document'),(84,70,'uploads\\70\\nature.webp','Salary proof document'),(85,71,'uploads\\71\\forest.jpg','Identification document'),(86,71,'uploads\\71\\Front-end developer task - Food blog.pdf','Home proof document'),(87,71,'uploads\\71\\landscape.jpg','Salary proof document'),(88,72,'uploads\\72\\forest.jpg','Identification document'),(89,72,'uploads\\72\\landscape.jpg','Home proof document'),(90,72,'uploads\\72\\nature.webp','Salary proof document'),(91,73,'uploads\\73\\forest.jpg','Identification document'),(92,73,'uploads\\73\\landscape.jpg','Home proof document'),(93,73,'uploads\\73\\nature.webp','Salary proof document'),(94,75,'uploads\\75\\landscape.jpg','Identification document'),(95,75,'uploads\\75\\nature.webp','Home proof document'),(96,75,'uploads\\75\\forest.jpg','Salary proof document'),(97,77,'uploads\\77\\forest.jpg','Identification document'),(98,77,'uploads\\77\\landscape.jpg','Home proof document'),(99,77,'uploads\\77\\nature.webp','Salary proof document'),(100,78,'uploads\\78\\landscape.jpg','Identification document'),(101,78,'uploads\\78\\forest.jpg','Home proof document'),(102,78,'uploads\\78\\nature.webp','Salary proof document'),(103,80,'uploads\\80\\forest.jpg','Identification document'),(104,80,'uploads\\80\\nature.webp','Home proof document'),(105,80,'uploads\\80\\landscape.jpg','Salary proof document'),(112,90,'uploads\\90\\forest.jpg','Identification document'),(113,90,'uploads\\90\\forest.jpg','Home proof document'),(114,90,'uploads\\90\\landscape.jpg','Salary proof document');
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (11,67,'Hi there, you have received transfer of 100.00€ from Nikolay Romanov. Description : Miroslavu od Sergija.',59),(12,66,'Hi there, you have received transfer of 200.00€ from Miroslav Ilic. Description : prenos sergiiu od miroslava.',63);
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
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,1,26,'CREATE_ACCOUNT','I want to open a new account','2020-05-17 12:12:12',0),(2,2,27,'CREATE_ACCOUNT','I want to open a new account','2020-06-23 10:08:42',1),(3,29,46,'CREATE_ACCOUNT','I want to open a new account','2020-06-23 10:11:36',1),(4,43,27,'CREATE_ACCOUNT','I want to open a new account','2020-06-21 15:44:15',1),(5,44,27,'CREATE_ACCOUNT','I want to open a new account','2020-06-21 17:56:24',0),(6,45,63,'CREATE_ACCOUNT','I want to open a new account','2020-07-21 09:43:27',0),(7,30,27,'CREATE_ACCOUNT','I want to open a new account','2020-06-21 16:02:59',0),(8,31,27,'CREATE_ACCOUNT','I want to open a new account','2020-06-21 15:31:12',1),(9,41,25,'CREATE_ACCOUNT','I want to open a new account','2020-06-21 15:32:55',0),(10,42,27,'CREATE_ACCOUNT','I want to open a new account','2020-06-21 15:26:19',1),(11,40,25,'CREATE_ACCOUNT','I want to open a new account','2020-06-21 15:32:55',0),(12,32,25,'CREATE_ACCOUNT','I want to open a new account','2020-06-21 17:57:17',0),(13,47,25,'CREATE_ACCOUNT','I want to open a new account','2020-07-20 10:22:04',0),(14,55,51,'CREATE_ACCOUNT','I want to open a new account','2020-06-26 08:06:28',1),(15,56,51,'CREATE_ACCOUNT','I want to open a new account','2020-07-05 06:21:47',0),(16,57,46,'CREATE_ACCOUNT','I want to open a new account','2020-07-05 10:26:43',1),(17,58,46,'CREATE_ACCOUNT','I want to open a new account','2020-07-05 10:26:43',1),(18,59,27,'CREATE_ACCOUNT','I want to open a new account','2020-07-05 11:19:27',1),(19,60,27,'CREATE_ACCOUNT','I want to open a new account','2020-07-05 11:19:28',1),(20,61,63,'CREATE_ACCOUNT','I want to open a new account','2020-07-05 11:46:59',0),(21,62,63,'CREATE_ACCOUNT','I want to open a new account','2020-07-05 11:46:59',0),(22,64,27,'CREATE_ACCOUNT','I want to open a new account','2020-07-08 12:52:53',1),(23,65,46,'CREATE_ACCOUNT','I want to open a new account','2020-07-15 17:00:32',1),(24,66,27,'CREATE_ACCOUNT','I want to open a new account','2020-07-16 15:30:34',1),(25,67,27,'CREATE_ACCOUNT','I want to open a new account','2020-07-18 09:22:07',1),(26,53,25,'CREATE_ACCOUNT','I want to open a new account','2020-07-22 08:46:51',0),(27,68,46,'CREATE_ACCOUNT','I want to open a new account','2020-07-31 13:29:33',1),(28,69,26,'CREATE_ACCOUNT','I want to open a new account','2020-07-31 14:59:45',1),(29,70,46,'CREATE_ACCOUNT','I want to open a new account','2020-08-01 12:55:43',1),(30,54,28,'CREATE_ACCOUNT','I want to open a new account','2020-08-05 08:31:55',1),(31,66,27,'CREATE_SAVING_ACCOUNT','I need to save some money','2020-08-05 09:30:09',1),(32,67,27,'CREATE_SAVING_ACCOUNT','I need to save some money','2020-08-05 09:35:32',1),(33,71,27,'CREATE_ACCOUNT','I want to open a new account','2020-08-05 09:53:56',0),(34,66,27,'CHANGE_TYPE_FOR_CURRENT_ACCOUNT','promeni tip racuna','2020-08-05 12:15:27',1),(35,66,27,'CREATE_SAVING_ACCOUNT','otvori mi stedni racun','2020-08-05 12:18:31',1),(36,66,27,'CREATE_CHEQUE_BOOK','otvori mi cek','2020-08-05 12:23:06',1),(37,66,27,'CHANGE_RULE_FOR_CURRENT_ACCOUNT','promeni pravilo za racun broj taj i taj','2020-08-05 12:27:30',0),(38,66,27,'CHANGE_TYPE_FOR_CURRENT_ACCOUNT','jos jedna promena tipa racuna ako moze','2020-08-05 12:29:24',1),(39,67,27,'CREATE_SAVING_ACCOUNT','otvori mi racun za stednju','2020-08-05 12:33:07',0),(40,67,27,'CREATE_CHEQUE_BOOK','otvori mi cek','2020-08-05 12:34:42',0),(41,67,27,'CHANGE_RULE_FOR_CURRENT_ACCOUNT','pitanje','2020-08-05 13:00:00',0),(42,67,27,'CHANGE_TYPE_FOR_CURRENT_ACCOUNT','promeni mi tip racuna','2020-08-05 13:00:33',1),(43,66,27,'CHANGE_RULE_FOR_CURRENT_ACCOUNT','promeni rule za racun','2020-08-06 09:06:51',1),(44,72,51,'CREATE_ACCOUNT','I want to open a new account','2020-08-09 10:27:55',0),(45,73,51,'CREATE_ACCOUNT','I want to open a new account','2020-08-09 10:27:55',0),(46,75,46,'CREATE_ACCOUNT','I want to open a new account','2020-08-09 13:14:27',0),(47,76,28,'CREATE_ACCOUNT','I want to open a new account','2020-08-09 13:27:26',0),(48,77,28,'CREATE_ACCOUNT','I want to open a new account','2020-08-09 13:30:32',0),(49,78,51,'CREATE_ACCOUNT','I want to open a new account','2020-08-09 13:36:48',0),(54,85,46,'CREATE_ACCOUNT','I want to open a new account','2020-08-12 09:09:47',0),(56,90,46,'CREATE_ACCOUNT','I want to open a new account','2020-08-12 15:30:19',1),(57,90,46,'CREATE_CHEQUE_BOOK',NULL,'2020-08-15 09:52:14',1),(58,90,46,'CHANGE_TYPE_FOR_CURRENT_ACCOUNT',NULL,'2020-08-15 10:11:23',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,1,NULL,3,10,'Transaction','2020-07-24 16:52:13'),(2,1,NULL,3,10.3,NULL,'2020-07-24 16:55:23'),(3,1,NULL,3,900,'kredit na racun','2020-07-25 14:39:50'),(4,1,NULL,3,200,'jos malo para na racun','2020-07-25 15:02:16'),(5,1,NULL,3,25,'jos malo love','2020-07-25 15:05:23'),(6,1,NULL,3,58,'Prenos','2020-07-25 15:18:08'),(7,1,NULL,3,258,'prenos','2020-07-25 15:35:44'),(8,1,NULL,3,255,'racun','2020-07-25 15:38:07'),(9,1,NULL,1,1250,'prenos','2020-07-29 08:13:02'),(10,1,NULL,3,850,'kesh na racun','2020-07-29 13:24:16'),(11,1,NULL,3,100,'kesh','2020-07-29 15:22:46'),(12,3,3,4,1000,'transfer','2020-07-29 16:10:51'),(13,3,3,1,1000,'transfer','2020-07-29 16:14:55'),(14,2,3,NULL,1000,'bankomat','2020-07-29 16:25:28'),(15,2,3,NULL,1000,'bankomat','2020-07-29 16:46:22'),(16,1,NULL,3,2000,'prenos','2020-07-29 16:48:02'),(17,1,NULL,3,1000,'credit','2020-07-29 17:57:45'),(18,2,3,NULL,500,'bankomat sergii','2020-07-30 07:08:19'),(19,2,3,NULL,100,'sergijev bankomat','2020-07-30 07:14:21'),(20,1,NULL,3,250,'kesh na racun','2020-07-30 07:15:22'),(21,2,3,NULL,250,'sergii ATM','2020-07-30 07:15:38'),(22,3,3,4,500,'transfer sergija miroslavu','2020-07-30 07:22:58'),(23,3,3,2,100,'Prenos na racun drugog klijenta Gestibanka','2020-07-30 07:36:38'),(24,3,3,3,100,'prenos','2020-07-30 07:37:50'),(25,3,4,1,9647,'transfer prenos','2020-07-30 07:56:04'),(26,3,3,3,100,'prebacivanje sa svog racuna sebi','2020-07-30 08:47:10'),(27,3,4,3,1000,'Transfer Sergiju','2020-07-31 08:46:46'),(28,3,3,4,10000,'prenos Miroslavu','2020-07-31 09:19:09'),(29,3,4,4,10000,'prenos sebi','2020-07-31 09:37:23'),(30,1,NULL,4,10000,'kesh na racun','2020-07-31 09:38:00'),(31,2,4,NULL,500,'200','2020-07-31 09:38:25'),(32,2,4,NULL,100,'bankomat','2020-07-31 09:39:15'),(33,3,4,3,10000,'prenos Sergiju','2020-07-31 09:40:05'),(34,2,3,NULL,2000,'2000','2020-07-31 09:44:58'),(35,1,NULL,3,600,'kredit','2020-07-31 09:46:10'),(36,1,NULL,6,100,'kredit','2020-07-31 13:33:47'),(38,1,NULL,3,100,'158','2020-08-01 10:18:02'),(42,2,3,NULL,2000,'bankomat','2020-08-02 08:07:38'),(43,3,3,17,1000,'transfer','2020-08-02 08:11:56'),(44,3,3,3,100,'kesh na racun','2020-08-02 08:26:09'),(45,3,3,3,2,'sam sebi','2020-08-02 08:32:25'),(46,3,3,4,2,'prenos miroslavu od sergija','2020-08-02 08:33:36'),(47,3,3,4,3,' ','2020-08-02 08:36:14'),(48,3,3,4,1,' ','2020-08-02 08:37:38'),(49,3,3,4,2,'poruka','2020-08-02 08:38:51'),(50,3,3,3,1431380000,'sam sebi na isti racun','2020-08-02 09:24:29'),(51,1,NULL,3,3000,'kesh na svoj racun','2020-08-02 09:28:28'),(52,3,3,3,1000000,'prenos','2020-08-02 10:02:12'),(53,3,3,3,100,' ','2020-08-02 10:04:09'),(54,3,4,3,100,' ','2020-08-02 10:10:58'),(55,3,4,3,50,' ','2020-08-02 15:57:19'),(56,3,4,3,11,' ','2020-08-02 16:06:49'),(57,3,4,4,1000000000000,'sam sebi','2020-08-02 16:32:20'),(59,3,3,4,100,'Miroslavu od Sergija','2020-08-04 09:31:02'),(60,2,4,NULL,100,'banka','2020-08-04 09:35:11'),(61,1,NULL,4,1000,'kredit','2020-08-04 09:35:29'),(62,3,4,4,100,'sam sebi','2020-08-04 09:35:55'),(63,3,4,3,200,'prenos sergiiu od miroslava','2020-08-04 11:42:04');
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
  `number_of_children` int unsigned DEFAULT NULL,
  `token` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id_user_account`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `user_account_FKIndex1` (`address_id_address`),
  KEY `user_account_FKIndex2` (`role_id_role`),
  CONSTRAINT `user_account_ibfk_1` FOREIGN KEY (`address_id_address`) REFERENCES `address` (`id_address`),
  CONSTRAINT `user_account_ibfk_2` FOREIGN KEY (`role_id_role`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,2,1,'Jack','Borrow','jackb','password123','jack@mailinator.com','555333','2020-05-17',NULL,0,'single',0,NULL),(2,2,2,'Zika','Zikic','zika','password1234','zika@zikic.com','0123456789','2020-05-22',NULL,1,'married',5,NULL),(4,1,4,'Marko','Markovic','admin','admin','admin@admin.ru','045215698','2020-05-24',NULL,1,NULL,0,NULL),(25,3,28,'Mika','Zika','mikica','mikica','mika@mila.ru','+78125698637','2020-06-05',NULL,1,NULL,0,NULL),(26,3,29,'Dejan','Savicevic','dejo','dejo','savicevic@mail.rs','0649645538','2020-06-07',NULL,1,NULL,0,NULL),(27,3,30,'Leia','Princess','leia','leia','leia@starwars.com','+1586325699874','2020-06-07',NULL,1,NULL,0,NULL),(28,3,31,'Roger','Federer','roger','roger','federer@mail.ch','984586526','2020-06-11',NULL,1,NULL,0,NULL),(29,2,32,'Blaise','Pascal','pascal','pascal','pascal@pascal.fr','0152365988','2020-06-12',NULL,1,'Married',8,NULL),(30,2,33,'Marko','Askovic','marko','marko','marko@marko.fr','0355696637','2020-06-12',NULL,0,'single',5,NULL),(31,2,34,'Rafael','Nadal','rafael','rafael','rafael@mallorca.es','56988635','2020-06-12',NULL,1,'Married',0,NULL),(32,2,35,'Novak','Djokovic','novak','novak','novak@mail.sr','5146453154','2020-06-13',NULL,0,'Married',2,NULL),(33,2,36,'Veronica','Bertolucci','veronica','veronica','veronica@italia.it','+3466555888666','2020-06-13',NULL,0,'married',3,NULL),(34,2,37,'Wolfgang','Amadeus','amadeus','amadeus','amadeux@wien.ar','+25365856','2020-06-13',NULL,0,'single',47,NULL),(36,2,39,'milan','milan','milan','milan','milan@sr.sr','25688','2020-06-13',NULL,0,'married',5,NULL),(37,2,40,'Emmanuel','Macron','manu','manu','manu@paris.fr','0122556699','2020-06-13',NULL,0,'married',2,NULL),(38,2,41,'picasso','Picasso','picasso','pikaso','picasso@spain.es','5465341','2020-06-13',NULL,0,'single',10,NULL),(39,2,42,'Stefan','Milenkovic','violina','violina','violina@mail.rs','+125666598','2020-06-13',NULL,0,'married',1,NULL),(40,2,43,'Monte','Cristo','cristo','cristo','cristo@cristo.fr','6561544','2020-06-13',NULL,0,'single',0,NULL),(41,2,44,'Don','Kihot','kihot','kihot','kihot@mail.es','515643','2020-06-13',NULL,0,'single',4,NULL),(42,2,45,'Victoria','Victoria','victoria','victoria','victoria@vica.ru','+7499556885','2020-06-13',NULL,1,'single',0,NULL),(43,2,46,'Anatoliy','Karpov','karpov','karpov','karpov@chess.ru','+74958863322','2020-06-13',NULL,1,'married',3,NULL),(44,2,47,'Milovan','Milovanovic','milovan','milovan','milovan@srbija.rs','0114756889','2020-06-13',NULL,0,'single',0,NULL),(45,2,48,'Neil','Horowicz','neil','neil','neil@neil.il','+97255682234','2020-06-13',NULL,0,'married',3,NULL),(46,3,49,'Novak','Djokovic','nole','nole','nole@nole.rs','+381112556388','2020-06-23',NULL,1,NULL,0,NULL),(47,2,50,'Bill','Gates','gates','gates','gates@microsoft.com','+12258236666','2020-06-24',NULL,0,'married',3,NULL),(50,2,53,'Che','Guevara','guevara','guevara','guevara@che.ar','6453455','2020-06-26',NULL,0,'single',5,NULL),(51,3,54,'Fidel','Castro','castro','castro','castro@fidel.cuba','6454163','2020-06-26',NULL,1,NULL,0,NULL),(52,2,55,'Andjelija','Askovic','andja','andja','andja@andja.rs','0366956687','2020-06-26',NULL,0,'veuve',2,NULL),(53,2,56,'Vuk','Jeremic','vuk','vuk','vuk@vuk.rs','5165435485415','2020-06-26',NULL,0,'single',0,NULL),(54,2,57,'Игорь','Акинфеев','akinfeyev','akinfeyev','akinfeyev@mail.ru','256986332547','2020-06-26',NULL,1,'married',5,NULL),(55,2,58,'Федор Михайлович','Достоевский','fedor','fedor','fedor@fedor.ru','258963214785','2020-06-26',NULL,1,'married',5,NULL),(56,2,59,'Maria','Magdalina','magdalina','magdalina','magdalina@maria.ru','639874656','2020-07-05',NULL,0,'single',7,NULL),(57,2,60,'Sergey','Bubka','bubka','bubka','bubka@donbass.ru','58963325785','2020-07-05',NULL,1,'married',5,NULL),(58,2,61,'Teddy','Riener','riener','riener','riener@teddy.fr','0125889631','2020-07-05',NULL,1,'single',7,NULL),(59,2,62,'Hubert','Canon','canon','canon','canon@hubert.fr','0258963574','2020-07-05',NULL,1,'married',3,NULL),(60,2,63,'Capitaine','Nemo','nemo','nemo','nemo@nautilus.fr','0125896321','2020-07-05',NULL,1,'single',0,NULL),(61,2,64,'Alexander','Karelin','karelin','karelin','karelin@karelin.ru','+78125698874','2020-07-05',NULL,0,'married',2,NULL),(62,2,65,'Stefan','Boca','vladika','vladika','boca@zica.rs','147852369','2020-07-05',NULL,0,'single',0,NULL),(63,3,66,'Gilles','Simon','simon','simon','simon@tennis.fr','0625897741','2020-07-05',NULL,1,NULL,0,NULL),(64,2,67,'Marko','Marko','marko1212','marko','legkozapomnit@mail.ru','523893','2020-07-08',NULL,1,'married',5,NULL),(65,2,68,'Sasa','Radulovic','radulovic','radulovic','jovaisha@hotmail.com','0112452368542','2020-07-15',NULL,1,'married',10,NULL),(66,2,69,'Nikolay','Romanov','sergii','sergij','askmarko@hotmail.com','5987633246','2020-07-16',NULL,1,'single',0,NULL),(67,2,70,'Miroslav','Ilic','miroslav','miroslav','miroslav@ilic.rs','53698746','2020-07-18',NULL,1,'married',4,NULL),(68,2,71,'Djoka','Djokovic','djoka','djoka','djoka@mailinator.com','5413554','2020-07-31',NULL,1,'single',0,NULL),(69,2,72,'Lik','Likovic','lik','lik','lik@lik.ru','694834151','2020-07-31',NULL,1,'maried',10,NULL),(70,2,73,'Olga','Markova','olga','olga','olga@mailinator.com','654857422','2020-08-01',NULL,1,'single',5,NULL),(71,2,74,'Ljudmila','Ljudmila','ljudmila','ljudmila','ljudmila@mail.ru','55874','2020-08-05',NULL,0,'single',5,NULL),(72,2,75,'sd','ds','ds','sdd','sd@mail.ru','ds','2020-08-06',NULL,0,'qds',5,NULL),(73,2,76,'j','df','hj','vbb','jh@mail.ru','bv','2020-08-06',NULL,0,'df',5,NULL),(75,2,78,'trgdf','rt','tpmlcxxcxxccc','ezrzerez','mika@mila.rs','+78125698638','2020-08-09',NULL,0,'xc',3,NULL),(76,2,79,'za','dsf','trrrrrrrrrrrrrrrrrrrrrrrrrr','dfdfs','trrrerrrrr@rrrrrr.rr','dfdf','2020-08-09',NULL,0,'rrr',10,NULL),(77,2,80,'lo','l','loolollolloloollo','ujju','ollollolo@lo.tf','uj','2020-08-09',NULL,0,'uu',3,NULL),(78,2,81,'ujhgf','ikuj','ujyhtgfhgfdsgfd','sqddsds','sqdqsdqsqs@dsd.ru','+78125698638','2020-08-09',NULL,0,'sqdsd',3,NULL),(79,3,82,'Panteleimon','Panteleimonovich','panteleimon','panteleimon','panteleimon@afon.ru','4444','2020-08-09',NULL,1,NULL,0,NULL),(80,2,83,'sdffdfds','dsfdfs','sdffdsfds','dfv','sdf@ggg.tu','+78125698638','2020-08-09',NULL,0,'dfs',7,NULL),(85,2,88,'df','fd','dfsfddfsf','sergiiiiiiiiiii','sergiiiiiiiiiii@mailinator.com','+78125698638','2020-08-12',NULL,0,'sdsd',5,NULL),(87,2,90,'vukvuk','hj','vukvuk','vukvuk','vukvuk@mailinator.com','ghjghj','2020-08-12',NULL,0,'dfg',7,NULL),(89,2,92,'markomarkomarko','markomarkomarko','markomarkomarko','markomarkomarko','markomarkomarko@mailinator.com','jkjhg','2020-08-12',NULL,0,'fg',3,NULL),(90,2,93,'marko','markomarkomarkomarko','markomarkomarkomarko','marko','markomarkomarkomarko@mailinator.com','ghjjk','2020-08-12',NULL,1,'sdfghj',2,NULL);
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

-- Dump completed on 2020-08-16 18:50:46
