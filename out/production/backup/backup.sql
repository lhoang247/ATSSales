-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: atsdb
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `atsdb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `atsdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `atsdb`;

--
-- Table structure for table `blanks`
--

DROP TABLE IF EXISTS `blanks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blanks` (
  `ticketnumber` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `blanktype` int DEFAULT NULL,
  `idstaff` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `bundle` varchar(45) DEFAULT NULL,
  `receivedDate` date DEFAULT NULL,
  PRIMARY KEY (`ticketnumber`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blanks`
--

LOCK TABLES `blanks` WRITE;
/*!40000 ALTER TABLE `blanks` DISABLE KEYS */;
INSERT INTO `blanks` VALUES (00000001,444,'001','sold','00000001 - 00000003','2019-10-31'),(00000002,444,'001','sold','00000001 - 00000003','2019-10-31'),(00000003,444,'001','assigned','00000001 - 00000003','2019-10-31'),(00000004,201,'002','sold','00000004 - 00000005','2020-01-03'),(00000005,201,'002','assigned','00000004 - 00000005','2020-01-03'),(00000006,101,'001','sold','00000006 - 00000007','2020-02-05'),(00000007,101,'001','assigned','00000006 - 00000007','2020-02-05'),(00000008,440,'','stock','00000008 - 00000012','2020-02-06'),(00000009,440,'','stock','00000008 - 00000012','2020-02-06'),(00000010,440,'','stock','00000008 - 00000012','2020-02-06'),(00000011,440,'','stock','00000008 - 00000012','2020-02-06'),(00000012,440,'','stock','00000008 - 00000012','2020-02-06'),(00000013,440,'','stock','00000013 - 00000015','2020-03-07'),(00000014,440,'','stock','00000013 - 00000015','2020-03-07'),(00000015,440,'','stock','00000013 - 00000015','2020-03-07'),(00000036,444,NULL,'stock','00000036 - 00000039','2012-03-20'),(00000037,444,NULL,'stock','00000036 - 00000039','2012-03-20'),(00000038,444,NULL,'stock','00000036 - 00000039','2012-03-20'),(00000039,444,NULL,'stock','00000036 - 00000039','2012-03-20');
/*!40000 ALTER TABLE `blanks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commissions`
--

DROP TABLE IF EXISTS `commissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commissions` (
  `idcommissions` int NOT NULL,
  `blanktype` varchar(3) DEFAULT NULL,
  `commissionrate` decimal(4,2) DEFAULT NULL,
  `idtravelagents` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcommissions`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commissions`
--

LOCK TABLES `commissions` WRITE;
/*!40000 ALTER TABLE `commissions` DISABLE KEYS */;
INSERT INTO `commissions` VALUES (1,'444',6.00,'1'),(2,'440',7.50,'1'),(3,'420',10.00,'1'),(4,'201',12.50,'1'),(5,'101',8.00,'1'),(6,'451',15.00,'1'),(7,'452',5.00,'1');
/*!40000 ALTER TABLE `commissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditcard`
--

DROP TABLE IF EXISTS `creditcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creditcard` (
  `email` varchar(45) NOT NULL,
  `cardnumber` varchar(45) DEFAULT NULL,
  `ticketnumber` int(8) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditcard`
--

LOCK TABLES `creditcard` WRITE;
/*!40000 ALTER TABLE `creditcard` DISABLE KEYS */;
INSERT INTO `creditcard` VALUES ('lee@gmail.com','93285',00000004);
/*!40000 ALTER TABLE `creditcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customerdetails`
--

DROP TABLE IF EXISTS `customerdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customerdetails` (
  `email` varchar(45) NOT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `customerType` varchar(45) DEFAULT NULL,
  `iddiscount` int DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customerdetails`
--

LOCK TABLES `customerdetails` WRITE;
/*!40000 ALTER TABLE `customerdetails` DISABLE KEYS */;
INSERT INTO `customerdetails` VALUES ('a','a','a','valued',3),('Bob.Bobby@hotmail.co.uk','Bob','Bobby','valued',1),('example@example.com','ex','ample','regular',NULL),('lee.hoang@gmail.com','Lee','Hoang','regular',NULL),('q','q','q','regular',NULL);
/*!40000 ALTER TABLE `customerdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `iddiscount` int NOT NULL AUTO_INCREMENT,
  `type` int DEFAULT NULL,
  PRIMARY KEY (`iddiscount`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,1),(3,2),(4,2),(5,2),(6,1),(7,1),(8,1);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fband`
--

DROP TABLE IF EXISTS `fband`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fband` (
  `idfband` int NOT NULL AUTO_INCREMENT,
  `fromBand` int DEFAULT NULL,
  `toBand` int DEFAULT NULL,
  PRIMARY KEY (`idfband`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fband`
--

LOCK TABLES `fband` WRITE;
/*!40000 ALTER TABLE `fband` DISABLE KEYS */;
INSERT INTO `fband` VALUES (1,0,5000),(2,5000,10000),(3,10000,15000),(4,15000,20000),(5,20000,999999);
/*!40000 ALTER TABLE `fband` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fixed`
--

DROP TABLE IF EXISTS `fixed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fixed` (
  `idfixed` int NOT NULL AUTO_INCREMENT,
  `iddiscount` int DEFAULT NULL,
  `fixed_rate` decimal(4,2) DEFAULT NULL,
  PRIMARY KEY (`idfixed`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixed`
--

LOCK TABLES `fixed` WRITE;
/*!40000 ALTER TABLE `fixed` DISABLE KEYS */;
INSERT INTO `fixed` VALUES (1,1,10.00),(2,6,20.00),(3,7,6.00),(4,8,5.00);
/*!40000 ALTER TABLE `fixed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flexdiscband`
--

DROP TABLE IF EXISTS `flexdiscband`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flexdiscband` (
  `idflexdiscband` int NOT NULL AUTO_INCREMENT,
  `idflexible` int DEFAULT NULL,
  `idfband` int DEFAULT NULL,
  `discount` decimal(4,2) DEFAULT NULL,
  PRIMARY KEY (`idflexdiscband`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flexdiscband`
--

LOCK TABLES `flexdiscband` WRITE;
/*!40000 ALTER TABLE `flexdiscband` DISABLE KEYS */;
INSERT INTO `flexdiscband` VALUES (1,1,1,0.00),(2,1,2,1.00),(3,1,3,2.00),(4,1,4,3.00),(5,1,5,4.00),(6,2,1,10.00),(7,2,2,11.00),(8,2,3,12.00),(9,3,2,10.00),(10,3,3,12.00),(11,3,4,14.00),(12,3,5,16.00);
/*!40000 ALTER TABLE `flexdiscband` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flexible`
--

DROP TABLE IF EXISTS `flexible`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flexible` (
  `idflexible` int NOT NULL AUTO_INCREMENT,
  `iddiscount` int DEFAULT NULL,
  PRIMARY KEY (`idflexible`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flexible`
--

LOCK TABLES `flexible` WRITE;
/*!40000 ALTER TABLE `flexible` DISABLE KEYS */;
INSERT INTO `flexible` VALUES (1,3),(2,4),(3,5);
/*!40000 ALTER TABLE `flexible` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refunds`
--

DROP TABLE IF EXISTS `refunds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refunds` (
  `idrefunds` int NOT NULL AUTO_INCREMENT,
  `ticketnumber` varchar(45) DEFAULT NULL,
  `amountRefunded` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idrefunds`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refunds`
--

LOCK TABLES `refunds` WRITE;
/*!40000 ALTER TABLE `refunds` DISABLE KEYS */;
/*!40000 ALTER TABLE `refunds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales` (
  `ticketnumber` int(8) unsigned zerofill NOT NULL,
  `blanktype` int DEFAULT NULL,
  `salesamount` int DEFAULT NULL,
  `paid` varchar(4) DEFAULT NULL,
  `refunded` varchar(4) DEFAULT NULL,
  `tax` int DEFAULT NULL,
  `exchangerate` int DEFAULT NULL,
  `customeremail` varchar(45) DEFAULT NULL,
  `amountPaid` int DEFAULT NULL,
  `paymentmethod` varchar(4) DEFAULT NULL,
  `commissionrate` decimal(4,2) DEFAULT NULL,
  `dateRecorded` date DEFAULT NULL,
  PRIMARY KEY (`ticketnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES (00000001,440,75000,'y','n',400,150,'bob@gmail.com',75400,'cash',10.00,'2020-02-15'),(00000002,440,67500,'y','n',400,150,'bob@gmail.com',67900,'cash',10.00,'2020-02-15'),(00000004,440,22500,'y','n',400,150,'lee@gmail.com',22900,'card',8.00,'2020-02-15'),(00000006,101,45000,'y','n',400,150,'lee@gmail.com',45400,'cash',10.00,'2020-02-15');
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `idstaff` int(3) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `commission` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idstaff`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (001,'Bob','Build','qwe','123','m','emailhere',NULL),(002,'Mike','John','abc','xyz','t','example',NULL),(003,'Lee','Hoang','hello','world','a','o.gmail.com',NULL),(004,'New','Account','asd','qwe','m','na@new.com',NULL),(005,'f','j','h',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `travelagents`
--

DROP TABLE IF EXISTS `travelagents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `travelagents` (
  `idtravelagents` int NOT NULL AUTO_INCREMENT,
  `travelagentsName` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtravelagents`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travelagents`
--

LOCK TABLES `travelagents` WRITE;
/*!40000 ALTER TABLE `travelagents` DISABLE KEYS */;
INSERT INTO `travelagents` VALUES (1,'AIR LINK','addresshere');
/*!40000 ALTER TABLE `travelagents` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-12 23:34:55
