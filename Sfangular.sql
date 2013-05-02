-- MySQL dump 10.13  Distrib 5.5.31, for Win64 (x86)
--
-- Host: localhost    Database: SFANGULAR
-- ------------------------------------------------------
-- Server version	5.5.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `SFANGULAR`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `sfangular` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `SFANGULAR`;

--
-- Table structure for table `rights`
--

DROP TABLE IF EXISTS `rights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rights` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LABEL` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rights`
--

LOCK TABLES `rights` WRITE;
/*!40000 ALTER TABLE `rights` DISABLE KEYS */;
INSERT INTO `rights` VALUES (1,'Access front door'),(2,'Access desk'),(3,'Access back door'),(4,'Access balcony'),(5,'Access elevator'),(6,'Access basement');
/*!40000 ALTER TABLE `rights` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LASTNAME` varchar(50) NOT NULL,
  `FIRSTNAME` varchar(50) NOT NULL,
  `LOGIN` varchar(50) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `CREATING_DATETIME` datetime NOT NULL,
  `UPDATING_DATETIME` datetime NOT NULL,
  `LEAVING_DATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `id_UNIQUE` (`ID`),
  UNIQUE KEY `email_UNIQUE` (`EMAIL`),
  UNIQUE KEY `login_UNIQUE` (`LOGIN`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Georges','Alexandre','AGEO','alexandre.georges@test.com','2013-04-30 19:32:12','2013-04-30 19:32:12',NULL),(2,'Arditi','Pierre','PARD','pierre.arditi@test.com','2012-12-31 18:19:00','2013-04-30 18:23:35',NULL),(3,'Belmondo','Jean-Paul','JBEL','jeanpaul.belmondo@test.com','2013-04-30 18:20:32','2013-04-30 18:20:32',NULL),(4,'Blanc','Michel','MBLA','michel.blanc@test.com','2013-04-30 18:21:00','2013-04-30 18:21:00',NULL),(5,'Bruel','Patrick','PBRU','patrick.bruel@test.com','2013-04-30 18:21:28','2013-04-30 18:21:28',NULL),(6,'Canet','Guillaume','GCAN','guillaume.canet@test.com','2013-04-30 18:22:02','2013-04-30 18:22:02',NULL),(7,'Cassel','Vincent','VCAS','vincent.cassel@test.com','2013-04-30 18:22:24','2013-04-30 18:22:24',NULL),(10,'Castelli','Philippe','PCAS','philippe.castelli@test.com','2013-04-30 18:23:27','2013-04-30 18:23:27',NULL),(11,'Chabat','Alain','ACHA','alain.chabat@test.com','2013-04-30 18:24:04','2013-04-30 18:24:04',NULL),(12,'Chabrol','Claude','CCHA','claude.chabrol@test.com','2013-04-30 18:24:27','2013-04-30 18:24:27',NULL),(13,'Clavier','Christian','CCLA','christian.clavier@test.com','2013-04-30 18:24:58','2013-04-30 18:24:58',NULL),(14,'Cluzet','François','FCLU','francois.cluzet@test.com','2013-04-30 18:25:28','2013-04-30 18:25:28',NULL),(15,'Cremer','Bruno','BCRE','bruno.cremer@test.com','2013-04-30 18:25:59','2013-04-30 18:25:59',NULL),(16,'Dac','Pierre','PDAC','pierre.dac@test.com','2013-04-30 18:26:46','2013-04-30 18:26:46',NULL),(17,'Darmon','Gérard','GDAR','gerard.darmon@test.com','2013-04-30 18:27:11','2013-04-30 18:27:11',NULL),(18,'De Funès','Louis','LDEF','louis.defunes@test.com','2013-04-30 18:27:33','2013-04-30 18:27:33',NULL),(19,'Delon','Alain','ADEL','alain.delon@test.com','2013-04-30 18:28:04','2013-04-30 18:28:04',NULL),(20,'Dubosc','Frank','FDUB','frank.dubosc@test.com','2013-04-30 18:28:47','2013-04-30 18:28:47',NULL),(21,'Duchaussoy','Michel','MDUC','michel.duchaussoy@test.com','2013-04-30 18:29:23','2013-04-30 18:29:23',NULL),(22,'Dujardin','Jean','JDUJ','jean.dujardin@test.com','2013-04-30 18:29:43','2013-04-30 18:29:43',NULL),(23,'Dupontel','Albert','ADUP','albert.dupontel@test.com','2013-04-30 18:30:09','2013-04-30 18:30:09',NULL),(24,'Duquenne','Pascal','PDUQ','pascal.duquenne@test.com','2013-04-30 18:30:33','2013-04-30 18:30:33',NULL),(25,'Gabin','Jean','JGAB','jean.gabin@test.com','2013-04-30 18:31:06','2013-04-30 18:31:06',NULL),(26,'Galabru','Michel','MGAL','michel.galabru@test.com','2013-04-30 18:31:29','2013-04-30 18:31:29',NULL),(27,'Herrand','Marcel','MHER','marcel.herrand@test.com','2013-04-30 18:32:09','2013-04-30 18:32:09',NULL),(28,'Hossein','Robert','RHOS','robert.hossein@test.com','2013-04-30 18:32:34','2013-04-30 18:32:34',NULL),(29,'Jolivet','Pierre','PJOL','pierre.jolivet@test.com','2013-04-30 18:33:01','2013-04-30 18:33:01',NULL),(30,'Jouvet','Louis','LJOU','louis.jouvet@test.com','2013-04-30 18:33:37','2013-04-30 18:33:37',NULL),(31,'Kassovitz','Mathieu','MKAS','mathieu.kassovitz@test.com','2013-04-30 18:34:13','2013-04-30 18:34:13',NULL),(32,'Lamoureux','Robert','RLAM','robert.lamoureux@test.com','2013-04-30 18:34:34','2013-04-30 18:34:34',NULL),(33,'Le Guay','Philippe','PLEG','philippe.leguay@test.com','2013-04-30 18:35:02','2013-04-30 18:35:02',NULL),(34,'Lefebvre','Jean','JLEF','jean.lefebvre@test.com','2013-04-30 18:35:26','2013-04-30 18:35:26',NULL),(35,'Lellouche','Philippe','PLEL','philippe.lellouche@test.com','2013-04-30 18:35:50','2013-04-30 18:35:50',NULL),(36,'Lhermitte','Thierry','TLHE','thierry.lhermitte@test.com','2013-04-30 18:36:13','2013-04-30 18:36:13',NULL),(37,'Maguelon','Pierre','PMAG','pierre.maguelon@test.com','2013-04-30 18:36:36','2013-04-30 18:36:36',NULL),(38,'Marais','Jean','JMAR','jean.marais@test.com','2013-04-30 18:36:53','2013-04-30 18:36:53',NULL),(39,'Marchand','Guy','GMAR','guy.marchand@test.com','2013-04-30 18:37:09','2013-04-30 18:37:09',NULL),(40,'Merad','Kad','KMER','kad.merad@test.com','2013-04-30 18:37:24','2013-04-30 18:37:24',NULL),(41,'Mondy','Pierre','PMON','pierre.mondy@test.com','2013-04-30 18:40:23','2013-04-30 18:40:23',NULL),(42,'Noiret','Philippe','PNOI','philippe.noiret@test.com','2013-04-30 18:40:44','2013-04-30 18:40:44',NULL),(43,'Palmade','Pierre','PPAL','pierre.palmade@test.com','2013-04-30 18:41:00','2013-04-30 18:41:00',NULL),(44,'Piat','Jean','JPIA','jean.piat@test.com','2013-04-30 18:41:16','2013-04-30 18:41:16',NULL),(45,'Piccoli','Michel','MPIC','michel.piccoli@test.com','2013-04-30 18:41:36','2013-04-30 18:41:36',NULL),(46,'Pierre','Roger','RPIE','roger.pierre@test.com','2013-04-30 18:41:53','2013-04-30 18:41:53',NULL),(47,'Renoir','Jean','JREN','jean.renoir@test.com','2013-04-30 18:42:12','2013-04-30 18:42:12',NULL),(48,'Richard','Pierre','PRIC','pierre.richard@test.com','2013-04-30 18:42:26','2013-04-30 18:42:26',NULL),(49,'Rochefort','Jean','JROC','jean.rochefort@test.com','2013-04-30 18:43:16','2013-04-30 18:43:16',NULL),(50,'Roux','Michel','MROU','michel.roux@test.com','2013-04-30 18:43:31','2013-04-30 18:43:31',NULL),(51,'Salomone','Bruno','BSAL','bruno.salomone@test.com','2013-04-30 18:43:48','2013-04-30 18:43:48',NULL),(52,'Sardou','Michel','MSAR','michel.sardou@test.com','2013-04-30 18:44:10','2013-04-30 18:44:10',NULL),(65,'Savary','Jérôme','JSAV','jerome.savary@test.com','2013-04-30 18:45:12','2013-04-30 18:45:12',NULL),(66,'Serrault','Michel','MER','michel.serrault@test.com','2013-04-30 18:45:34','2013-04-30 18:45:34',NULL),(67,'Tati','Jacques','JTAT','jacques.tati@test.com','2013-04-30 18:45:57','2013-04-30 18:45:57',NULL),(68,'Thibault','Jean-Marc','JTHI','jeanmarc.thibault@test.com','2013-04-30 18:46:19','2013-04-30 18:46:19',NULL),(69,'Timsit','Patrick','PTIM','patrick.timsit@test.com','2013-04-30 18:46:40','2013-04-30 18:46:40',NULL),(70,'Topaloff','Patrick','PTOP','patrick.topaloff@test.com','2013-04-30 18:47:00','2013-04-30 18:47:00',NULL),(71,'Truffault','François','FTRU','francois.truffault@test.com','2013-04-30 18:47:28','2013-04-30 18:47:28',NULL),(72,'Vilar','Jean','JVIL','jean.vilar@test.com','2013-04-30 18:47:45','2013-04-30 18:47:45',NULL),(74,'Villeret','Jacques','JVILL','jacques.villeret@test.com','2013-04-30 18:48:37','2013-04-30 18:48:37',NULL),(75,'Yann','Jean','JYAN','jean.yann@test.com','2013-04-30 18:48:59','2013-04-30 18:48:59',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_rights`
--

DROP TABLE IF EXISTS `users_rights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_rights` (
  `ID_USER` int(11) NOT NULL,
  `ID_RIGHT` int(11) NOT NULL,
  PRIMARY KEY (`ID_USER`,`ID_RIGHT`),
  KEY `FK1_idx` (`ID_USER`),
  KEY `FK2_idx` (`ID_RIGHT`),
  CONSTRAINT `FK1` FOREIGN KEY (`ID_USER`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK2` FOREIGN KEY (`ID_RIGHT`) REFERENCES `rights` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_rights`
--

LOCK TABLES `users_rights` WRITE;
/*!40000 ALTER TABLE `users_rights` DISABLE KEYS */;
INSERT INTO `users_rights` VALUES (2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(3,1),(3,6),(5,5),(11,1),(11,2),(11,3),(11,4),(11,5),(11,6),(17,3),(18,6),(19,1),(19,2),(19,3),(19,4),(19,5),(19,6),(20,2),(20,6),(21,6),(22,1),(23,2),(24,3),(25,3),(26,2),(27,6),(28,2),(29,6),(34,1),(49,2),(49,6),(51,2),(51,5),(52,2),(52,5),(65,2),(65,5),(66,3),(68,1),(69,6),(70,6),(71,5),(74,2),(74,5),(74,6),(75,6);
/*!40000 ALTER TABLE `users_rights` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-05-02 12:14:59
