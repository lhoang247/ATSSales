-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: atsdb
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales` (
  `ticketnumber` int(8) unsigned zerofill DEFAULT NULL,
  `blanktype` int DEFAULT NULL,
  `salesamount` int DEFAULT NULL,
  `paid` varchar(4) DEFAULT NULL,
  `refunded` varchar(4) DEFAULT NULL,
  `tax` int DEFAULT NULL,
  `exchangerate` decimal(4,2) DEFAULT NULL,
  `customeremail` varchar(45) DEFAULT NULL,
  `amountPaid` int DEFAULT NULL,
  `paymentmethod` varchar(4) DEFAULT NULL,
  `commissionrate` decimal(4,2) DEFAULT NULL,
  `dateRecorded` date DEFAULT NULL,
  `idsales` int unsigned NOT NULL AUTO_INCREMENT,
  `tid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idsales`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES (00000001,444,220,'y','n',58,0.54,'SarahB',272,'cash',9.00,'2020-01-02',7,'523'),(00000002,444,230,'y','y',98,0.54,'',328,'card',9.00,'2020-01-02',8,'524'),(00000001,201,86,'y','n',16,NULL,'',102,'cash',5.00,'2020-01-02',9,'723'),(00000003,444,63,'y','n',75,0.43,'DaveD',138,'card',9.00,'2020-03-25',10,'525'),(00000004,444,230,'x','n',58,0.43,'Chris',0,'cash',9.00,'2020-02-02',11,'526'),(00000002,201,75,'y','y',14,NULL,'',89,'card',5.00,'2020-02-02',12,'724'),(00000021,444,250,'y','n',60,0.43,'SarahB',304,'card',9.00,'2020-03-25',13,'543'),(00000022,444,300,'y','n',65,0.43,'',365,'card',9.00,'2020-02-03',14,'544'),(00000011,201,75,'y','y',14,NULL,'',89,'cash',5.00,'2020-02-03',15,'733');
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-25 23:10:56
