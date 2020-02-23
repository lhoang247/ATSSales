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
-- Table structure for table `blanks`
--

DROP TABLE IF EXISTS `blanks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blanks` (
  `ticketnumber` int(8) unsigned zerofill NOT NULL,
  `blanktype` int DEFAULT NULL,
  `idstaff` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `bundle` varchar(45) DEFAULT NULL,
  `receivedDate` date DEFAULT NULL,
  PRIMARY KEY (`ticketnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blanks`
--

LOCK TABLES `blanks` WRITE;
/*!40000 ALTER TABLE `blanks` DISABLE KEYS */;
INSERT INTO `blanks` VALUES (00000001,444,'001','sold','00000001 - 00000003','2019-10-31'),(00000002,444,'001','assigned','00000001 - 00000003','2019-10-31'),(00000003,444,'001','assigned','00000001 - 00000003','2019-10-31'),(00000004,201,'002','assigned','00000004 - 00000005','2020-01-03'),(00000005,201,'002','assigned','00000004 - 00000005','2020-01-03'),(00000006,101,'001','sold','00000006 - 00000007','2020-02-05'),(00000007,101,'001','assigned','00000006 - 00000007','2020-02-05'),(00000008,440,NULL,'stock','00000008 - 00000012','2020-02-06'),(00000009,440,NULL,'stock','00000008 - 00000012','2020-02-06'),(00000010,440,NULL,'stock','00000008 - 00000012','2020-02-06'),(00000011,440,NULL,'stock','00000008 - 00000012','2020-02-06'),(00000012,440,NULL,'stock','00000008 - 00000012','2020-02-06');
/*!40000 ALTER TABLE `blanks` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-23 13:51:16
