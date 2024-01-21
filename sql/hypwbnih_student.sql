-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
-- ------------------------------------------------------
-- Server version	5.7.28-log

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
-- Table structure for table `mark`
--

DROP TABLE IF EXISTS `mark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mark` (
  `subject_id` varchar(45) NOT NULL,
  `student_id` varchar(45) NOT NULL,
  `mark` float NOT NULL,
  `input_date` datetime NOT NULL,
  `note` varchar(200) DEFAULT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`subject_id`,`student_id`),
  KEY `subjectIndex` (`subject_id`),
  KEY `studentFK_idx` (`student_id`),
  CONSTRAINT `studentFK` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `subjectFK` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mark`
--

LOCK TABLES `mark` WRITE;
/*!40000 ALTER TABLE `mark` DISABLE KEYS */;
INSERT INTO `mark` VALUES ('NÅ\'i§KÞ†Rzk|»','è›A,ùåCº½Ú°‡	¼3»',9,'2024-01-21 15:28:16',NULL,'2024-01-21 15:28:16'),('Ò]ÆáO6†;-™µ	\0','\n`twËKúšä(zÞ©`ß',10,'2024-01-21 15:29:00','Awesome','2024-01-21 19:56:45'),('Ò]ÆáO6†;-™µ	\0','è›A,ùåCº½Ú°‡	¼3»',10,'2024-01-21 15:28:32',NULL,'2024-01-21 15:28:32'),('Ò]ÆáO6†;-™µ	\0','†ÿ‚Ïª¯@è›á=Ò|Ì\r¬',8.5,'2024-01-21 15:28:45',NULL,'2024-01-21 15:28:45');
/*!40000 ALTER TABLE `mark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `student_id` varchar(45) CHARACTER SET latin1 NOT NULL,
  `full_name` varchar(100) CHARACTER SET latin1 NOT NULL,
  `roll_number` varchar(20) CHARACTER SET latin1 NOT NULL,
  `gender` varchar(6) CHARACTER SET latin1 NOT NULL,
  `date_of_birth` date NOT NULL,
  `id_card` varchar(50) CHARACTER SET latin1 NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `email` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `status` varchar(10) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `id_card_UNIQUE` (`id_card`),
  UNIQUE KEY `roll_number_UNIQUE` (`roll_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('\n`twËKúšä(zÞ©`ß','Pham Van Chi','SE171365','MALE','1995-06-20','002567890123','101 Duong Cach Mang Thang Tam, Quan 3, TP.Ho Chi Minh','0847890123','pham.van.chi@hdang09.tech','ACTIVE'),('UÛL‹(B¢¾»©¤','Dang Van Duc','SE171367','MALE','1998-02-08','001901234567','303 Duong Tran Phu, Quan 10, TP.Ho Chi Minh','0849013456','dang.van.duc@hdang09.tech','ACTIVE'),('Â}H	£G\"±B-ÛÁ','Bob Johnson','SE171363','MALE','1992-03-12','074203852147','789 Pine Avenue, Villagetown','+84555789025','bob.johnson@hdang09.tech','ACTIVE'),('è›A,ùåCº½Ú°‡	¼3»','Nguyen Thi Huong','SE171368','FEMALE','1987-07-03','074567890123','404 Duong Nguyen Trai, Quan 5, TP.Ho Chi Minh','0854789012','nguyen.thi.huong@hdang09.tech','ACTIVE'),('qd¿¸ÚN Tëf†%³','Evan Williams','SE171364','FEMALE','1988-12-05','074209874563','101 Maple Lane, Hamletville','+84912345675','eva.williams@hdang09.tech','ACTIVE'),('†ÿ‚Ïª¯@è›á=Ò|Ì\r¬','Nguyen Thi Mai','SE171366','FEMALE','1983-11-15','012345678903','202 Duong Ba Trieu, Quan 1, TP.Ho Chi Minh','0845346789','nguyen.thi.mai@hdang09.tech','ACTIVE'),('•ÌuÎKIß6û”õ3','Alice Smith','SE171361','FEMALE','1985-09-28','012345678901','456 Oak Street, Townsville','+84345678901','alice.smith@hdang09.tech','ACTIVE');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `subject_id` varchar(45) CHARACTER SET latin1 NOT NULL,
  `subject_code` varchar(45) CHARACTER SET latin1 NOT NULL,
  `subject_name` varchar(100) CHARACTER SET latin1 NOT NULL,
  `description` varchar(500) CHARACTER SET latin1 DEFAULT NULL,
  `credit_number` int(10) NOT NULL,
  `status` varchar(8) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`subject_id`),
  UNIQUE KEY `subject_code_UNIQUE` (`subject_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES ('jáåG\Z©@|÷âŸ§_','MAE101','Mathematics for Engineering','',3,'INACTIVE'),('0{…‹ËNÿ¥hœß£äíD','SWP391','Software Development Project','',3,'ACTIVE'),('Hòø%\"ÖOË¨É\"÷qe','CSI104','Introduction to computing','',3,'ACTIVE'),('NÅ\'i§KÞ†Rzk|»','MAS291','Probability & statistics','',3,'ACTIVE'),('Ò]ÆáO6†;-™µ	\0','CEA201','Computer Organization and Architecture','',3,'ACTIVE'),('^i{cWG@Ñ˜xÔÊÑ®´','PRF192','Programming Fundamentals','',3,'ACTIVE');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-21 20:00:40
