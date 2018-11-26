-- MySQL dump 10.13  Distrib 8.0.12, for macos10.13 (x86_64)
--
-- Host: 127.0.0.1    Database: Booksdatabase
-- ------------------------------------------------------
-- Server version	8.0.12

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
-- Table structure for table `titles`
--

DROP TABLE IF EXISTS `titles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `titles` (
  `isbn` char(10) NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `editionNumber` int(11) DEFAULT NULL,
  `Year` char(4) DEFAULT NULL,
  `publisherID` int(11) DEFAULT NULL,
  `price` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `titles`
--

LOCK TABLES `titles` WRITE;
/*!40000 ALTER TABLE `titles` DISABLE KEYS */;
INSERT INTO `titles` VALUES ('0143035746','The Liars\' Club: A Memoir',2,'2005',1,8.15),('0143039946','Gravity\'s Rainbow',4,'2006',2,14.95),('0143126393','The Shadow of the Wind',2,'2014',1,22.00),('0307276767','A Mercy',1,'2008',14,23.18),('0345339703','The Fellowship of the Ring',6,'2003',7,10.95),('0375756728','Little Women',5,'2004',0,9.59),('0385490818','The Handmaid\'s Tale',1,'1998',3,14.95),('0425232204','The Help',1,'2011',10,8.21),('0440238137','The Golden Compass: His Dark Materials',3,'2003',8,7.19),('0452277507','Animal Farm',5,'2003',12,13.49),('0452284236','1984',4,'2003',10,11.55),('0679437223','Catch-22',3,'1995',9,17.68),('0679783275','Moby Dick',7,'2018',5,12.11),('0812994711','Cloud Atlas',3,'2003',0,15.63),('1400033411','Beloved',3,'1987',13,16.19),('1400033423','Song of Solomon',2,'1998',11,8.29),('1400079985','War and Peace',6,'2008',4,13.59),('9780679722','Ulysses',5,'2016',6,16.99);
/*!40000 ALTER TABLE `titles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-26  2:31:06
