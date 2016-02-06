-- MySQL dump 10.13  Distrib 5.1.36, for Win32 (ia32)
--
-- Host: localhost    Database: inv_ctrl
-- ------------------------------------------------------
-- Server version	5.1.36-community

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
-- Table structure for table `ic_customer`
--

DROP TABLE IF EXISTS `ic_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ic_customer` (
  `CUS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  `EMAIL` varchar(100) DEFAULT NULL,
  `ADDRESS` varchar(100) DEFAULT NULL,
  `TP_OFFICE` varchar(15) DEFAULT NULL,
  `TP_MOBILE` varchar(15) DEFAULT NULL,
  `STATUS` varchar(2) DEFAULT NULL,
  `REG_DATE` date DEFAULT NULL,
  `TIMESTAMP` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`CUS_ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`),
  KEY `fk_status_idx` (`STATUS`),
  KEY `fk_statustab_idx` (`STATUS`),
  CONSTRAINT `fk_statustab` FOREIGN KEY (`STATUS`) REFERENCES `mt_status` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ic_customer`
--

LOCK TABLES `ic_customer` WRITE;
/*!40000 ALTER TABLE `ic_customer` DISABLE KEYS */;
INSERT INTO `ic_customer` VALUES (1,'nolimit','nolimit@gmail.com','22,colombo','0112525145','0777142525','01','2016-02-05','2016-02-06 04:26:05'),(3,'ss','rdthanga@gmail.comd','sss','123333322','4444','02','2016-02-05','2016-02-06 11:09:33'),(7,'gyyg','rdthanga@gmail.com','sss','123','4444','01','2016-02-06','2016-02-06 11:10:40');
/*!40000 ALTER TABLE `ic_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ic_history`
--

DROP TABLE IF EXISTS `ic_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ic_history` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `MODULE_ID` varchar(2) DEFAULT NULL,
  `OPERATION_ID` varchar(2) DEFAULT NULL,
  `MESSAGE` varchar(200) DEFAULT NULL,
  `IP` varchar(15) DEFAULT NULL,
  `TIMESTAMP` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ic_history`
--

LOCK TABLES `ic_history` WRITE;
/*!40000 ALTER TABLE `ic_history` DISABLE KEYS */;
INSERT INTO `ic_history` VALUES (1,1,'01','01','Customer add successful for ss','0:0:0:0:0:0:0:1','2016-02-04 19:14:57'),(2,1,'01','01','Customer add successful for fgf','0:0:0:0:0:0:0:1','2016-02-04 19:28:04'),(3,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-04 20:10:33'),(4,0,'06','07','User logout successful :admin','0:0:0:0:0:0:0:1','2016-02-04 20:10:41'),(5,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-04 20:11:16'),(6,0,'06','07','User logout successful :admin','0:0:0:0:0:0:0:1','2016-02-04 20:11:21'),(7,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-04 20:15:47'),(8,0,'06','07','User logout successful :admin','0:0:0:0:0:0:0:1','2016-02-04 20:16:01'),(9,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-04 20:20:53'),(10,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-04 20:26:03'),(11,1,'05','03','User password update successful for admin','0:0:0:0:0:0:0:1','2016-02-04 20:26:30'),(12,0,'06','07','User logout successful :admin','0:0:0:0:0:0:0:1','2016-02-04 20:26:37'),(13,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-04 20:26:42'),(14,1,'05','03','User password update successful for admin','0:0:0:0:0:0:0:1','2016-02-04 20:26:57'),(15,0,'06','07','User logout successful :admin','0:0:0:0:0:0:0:1','2016-02-04 20:27:09'),(16,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-04 20:27:14'),(17,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-04 20:37:32'),(18,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 03:49:54'),(19,0,'06','07','User logout successful :admin','0:0:0:0:0:0:0:1','2016-02-05 03:50:11'),(20,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 03:52:22'),(21,0,'06','07','User logout successful :admin','0:0:0:0:0:0:0:1','2016-02-05 03:52:28'),(22,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 03:57:01'),(23,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 04:05:13'),(24,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 04:07:21'),(25,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 04:19:35'),(26,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 04:23:32'),(27,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 05:01:02'),(28,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 05:09:00'),(29,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 05:12:21'),(30,1,'01','01','Customer add successful for h','0:0:0:0:0:0:0:1','2016-02-05 05:13:41'),(31,1,'01','01','Customer add successful for hihih','0:0:0:0:0:0:0:1','2016-02-05 05:14:02'),(32,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 05:43:47'),(33,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 05:46:00'),(34,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 05:49:16'),(35,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 09:55:26'),(36,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 10:40:07'),(37,0,'06','07','User logout successful :admin','0:0:0:0:0:0:0:1','2016-02-05 10:40:12'),(38,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 10:40:47'),(39,0,'06','07','User logout successful :admin','0:0:0:0:0:0:0:1','2016-02-05 10:40:58'),(40,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 10:47:22'),(41,0,'06','07','User logout successful :admin','0:0:0:0:0:0:0:1','2016-02-05 10:47:25'),(42,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 10:48:03'),(43,0,'06','07','User logout successful :admin','0:0:0:0:0:0:0:1','2016-02-05 10:48:06'),(44,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 10:54:08'),(45,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 11:00:22'),(46,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 11:07:05'),(47,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 11:11:18'),(48,0,'06','07','User logout successful :admin','0:0:0:0:0:0:0:1','2016-02-05 11:13:10'),(49,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 15:27:31'),(50,0,'06','07','User logout successful :admin','0:0:0:0:0:0:0:1','2016-02-05 15:28:03'),(51,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 15:28:15'),(52,0,'06','07','User logout successful :admin','0:0:0:0:0:0:0:1','2016-02-05 15:31:47'),(53,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 15:31:54'),(54,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-05 15:58:39'),(55,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-06 04:21:01'),(56,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-06 04:23:31'),(57,1,'01','03','Customer update successful:ss','0:0:0:0:0:0:0:1','2016-02-06 04:23:46'),(58,1,'01','03','Customer update successful:ss','0:0:0:0:0:0:0:1','2016-02-06 04:23:54'),(59,1,'01','03','Customer update successful:nolimit2','0:0:0:0:0:0:0:1','2016-02-06 04:24:07'),(60,1,'01','03','Customer update successful:nolimit','0:0:0:0:0:0:0:1','2016-02-06 04:25:43'),(61,1,'01','03','Customer update successful:nolimit','0:0:0:0:0:0:0:1','2016-02-06 04:26:06'),(62,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-06 04:43:04'),(63,1,'01','03','Customer update successful:nolimit2','0:0:0:0:0:0:0:1','2016-02-06 04:47:49'),(64,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-06 05:31:22'),(65,1,'01','02','Customer delete successful Cus ID:6','0:0:0:0:0:0:0:1','2016-02-06 05:37:15'),(66,1,'01','02','Customer delete successful Cus ID:4','0:0:0:0:0:0:0:1','2016-02-06 05:39:30'),(67,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-06 05:43:52'),(68,1,'01','02','Customer delete successful Cus ID:2','0:0:0:0:0:0:0:1','2016-02-06 05:44:08'),(69,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-06 11:09:03'),(70,1,'01','03','Customer update successful:ss','0:0:0:0:0:0:0:1','2016-02-06 11:09:20'),(71,1,'01','03','Customer update successful:ss','0:0:0:0:0:0:0:1','2016-02-06 11:09:33'),(72,1,'01','01','Customer add successful for gyyg','0:0:0:0:0:0:0:1','2016-02-06 11:10:40'),(73,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-06 11:52:14'),(74,1,'06','06','User login successful :admin','0:0:0:0:0:0:0:1','2016-02-06 11:54:14'),(75,0,'06','07','User logout successful :admin','0:0:0:0:0:0:0:1','2016-02-06 11:56:19');
/*!40000 ALTER TABLE `ic_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ic_invoice`
--

DROP TABLE IF EXISTS `ic_invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ic_invoice` (
  `INV_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUS_ID` int(11) DEFAULT NULL,
  `STOR_ID` int(11) DEFAULT NULL,
  `STATUS` varchar(2) DEFAULT NULL,
  `TOTAL` decimal(10,2) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `TIMESTAMP` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`INV_ID`),
  KEY `fk_customertable_idx` (`CUS_ID`),
  KEY `fk_statustab_idx` (`STATUS`),
  KEY `invoicefk_store_idx` (`STOR_ID`),
  CONSTRAINT `invoicefk_custome` FOREIGN KEY (`CUS_ID`) REFERENCES `ic_customer` (`CUS_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `invoicefk_statuss` FOREIGN KEY (`STATUS`) REFERENCES `mt_status` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `invoicefk_store` FOREIGN KEY (`STOR_ID`) REFERENCES `mt_store` (`STOR_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ic_invoice`
--

LOCK TABLES `ic_invoice` WRITE;
/*!40000 ALTER TABLE `ic_invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `ic_invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ic_invoice_details`
--

DROP TABLE IF EXISTS `ic_invoice_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ic_invoice_details` (
  `INV_ID` int(11) DEFAULT NULL,
  `ITEM_NO` varchar(20) DEFAULT NULL,
  `COUNT` decimal(10,2) DEFAULT NULL,
  `UNIT_PRIZE` decimal(10,2) DEFAULT NULL,
  `TOTAL_PRIZE` decimal(10,2) DEFAULT NULL,
  KEY `fk_invoicetab_idx` (`INV_ID`),
  KEY `fk_itemtab_idx` (`ITEM_NO`),
  CONSTRAINT `idfk_items` FOREIGN KEY (`ITEM_NO`) REFERENCES `ic_items` (`ITEM_NO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idfk_invoice` FOREIGN KEY (`INV_ID`) REFERENCES `ic_invoice` (`INV_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ic_invoice_details`
--

LOCK TABLES `ic_invoice_details` WRITE;
/*!40000 ALTER TABLE `ic_invoice_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `ic_invoice_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ic_items`
--

DROP TABLE IF EXISTS `ic_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ic_items` (
  `ITEM_NO` varchar(20) NOT NULL,
  `NAME` varchar(45) DEFAULT NULL,
  `COLOUR` varchar(45) DEFAULT NULL,
  `UNIT_PRIZE` double(10,2) DEFAULT NULL,
  `UNIT_TYPE` varchar(15) DEFAULT NULL,
  `IMG_PATH` varchar(45) DEFAULT NULL,
  `STATUS` varchar(2) DEFAULT NULL,
  `REG_DATE` date DEFAULT NULL,
  `TIMESTAMP` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ITEM_NO`),
  KEY `fk_statustable_idx` (`STATUS`),
  CONSTRAINT `fk_statustable` FOREIGN KEY (`STATUS`) REFERENCES `mt_status` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ic_items`
--

LOCK TABLES `ic_items` WRITE;
/*!40000 ALTER TABLE `ic_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `ic_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ic_purchase`
--

DROP TABLE IF EXISTS `ic_purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ic_purchase` (
  `PUR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SUP_ID` int(11) DEFAULT NULL,
  `STOR_ID` int(11) DEFAULT NULL,
  `STATUS` varchar(2) DEFAULT NULL,
  `TOTAL` decimal(10,2) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `TIMESTAMP` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`PUR_ID`),
  KEY `fk_supplier_idx` (`SUP_ID`),
  KEY `fk_statusss_idx` (`STATUS`),
  KEY `purchesfk_store_idx` (`STOR_ID`),
  CONSTRAINT `purchesfk_statusss` FOREIGN KEY (`STATUS`) REFERENCES `mt_status` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `purchesfk_store` FOREIGN KEY (`STOR_ID`) REFERENCES `mt_store` (`STOR_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `purckesfk_supplier` FOREIGN KEY (`SUP_ID`) REFERENCES `ic_supplier` (`SUP_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ic_purchase`
--

LOCK TABLES `ic_purchase` WRITE;
/*!40000 ALTER TABLE `ic_purchase` DISABLE KEYS */;
/*!40000 ALTER TABLE `ic_purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ic_purchase_details`
--

DROP TABLE IF EXISTS `ic_purchase_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ic_purchase_details` (
  `PUR_ID` int(11) DEFAULT NULL,
  `ITEM_NO` varchar(20) DEFAULT NULL,
  `COUNT` decimal(10,2) DEFAULT NULL,
  `UNIT_PRIZE` decimal(10,2) DEFAULT NULL,
  `TOTAL_PRIZE` decimal(10,2) DEFAULT NULL,
  KEY `fk_purchasetab_idx` (`PUR_ID`),
  KEY `fk_itemstabb_idx` (`ITEM_NO`),
  CONSTRAINT `pdfk_purches` FOREIGN KEY (`PUR_ID`) REFERENCES `ic_purchase` (`PUR_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pdfk_items` FOREIGN KEY (`ITEM_NO`) REFERENCES `ic_items` (`ITEM_NO`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ic_purchase_details`
--

LOCK TABLES `ic_purchase_details` WRITE;
/*!40000 ALTER TABLE `ic_purchase_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `ic_purchase_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ic_stock`
--

DROP TABLE IF EXISTS `ic_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ic_stock` (
  `STOR_ID` int(11) NOT NULL,
  `ITEM_NO` varchar(20) NOT NULL,
  `COUNT` decimal(10,2) DEFAULT NULL,
  KEY `stock_fk_store_idx` (`STOR_ID`),
  KEY `stockfk_items_idx` (`ITEM_NO`),
  CONSTRAINT `stockfk_items` FOREIGN KEY (`ITEM_NO`) REFERENCES `ic_items` (`ITEM_NO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `stock_fk_store` FOREIGN KEY (`STOR_ID`) REFERENCES `mt_store` (`STOR_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ic_stock`
--

LOCK TABLES `ic_stock` WRITE;
/*!40000 ALTER TABLE `ic_stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `ic_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ic_supplier`
--

DROP TABLE IF EXISTS `ic_supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ic_supplier` (
  `SUP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  `EMAIL` varchar(100) DEFAULT NULL,
  `ADDRESS` varchar(100) DEFAULT NULL,
  `TP_OFFICE` varchar(15) DEFAULT NULL,
  `TP_MOBILE` varchar(15) DEFAULT NULL,
  `STATUS` varchar(2) DEFAULT NULL,
  `REG_DATE` date DEFAULT NULL,
  `TIMESTAMP` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`SUP_ID`),
  KEY `fk_status_idx` (`STATUS`),
  CONSTRAINT `fk_status` FOREIGN KEY (`STATUS`) REFERENCES `mt_status` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ic_supplier`
--

LOCK TABLES `ic_supplier` WRITE;
/*!40000 ALTER TABLE `ic_supplier` DISABLE KEYS */;
/*!40000 ALTER TABLE `ic_supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ic_user`
--

DROP TABLE IF EXISTS `ic_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ic_user` (
  `USER_ID` int(11) NOT NULL,
  `USERNAME` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(45) DEFAULT NULL,
  `USER_PROFILE` int(11) DEFAULT NULL,
  `STATUS` varchar(2) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `TIMESTAME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USERNAME_UNIQUE` (`USERNAME`),
  KEY `fk_userprofile_idx` (`USER_PROFILE`),
  KEY `fk_status_idx` (`STATUS`),
  CONSTRAINT `fk_userprofileif` FOREIGN KEY (`USER_PROFILE`) REFERENCES `ic_user_profile` (`PROFILE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ic_user`
--

LOCK TABLES `ic_user` WRITE;
/*!40000 ALTER TABLE `ic_user` DISABLE KEYS */;
INSERT INTO `ic_user` VALUES (1,'admin','5f4dcc3b5aa765d61d8327deb882cf99',1,'01','2010-06-10',NULL);
/*!40000 ALTER TABLE `ic_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ic_user_pf_module`
--

DROP TABLE IF EXISTS `ic_user_pf_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ic_user_pf_module` (
  `PROFILE_ID` int(11) NOT NULL,
  `MODULE_ID` varchar(2) NOT NULL,
  `PAGE_ID` varchar(4) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_model_idx` (`MODULE_ID`),
  KEY `fk_page_idx` (`PAGE_ID`),
  KEY `fk_proidd_idx` (`PROFILE_ID`),
  CONSTRAINT `fk_pageid` FOREIGN KEY (`PAGE_ID`) REFERENCES `mt_page` (`PAGE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_moduledd` FOREIGN KEY (`MODULE_ID`) REFERENCES `mt_modules` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_proidd` FOREIGN KEY (`PROFILE_ID`) REFERENCES `ic_user_profile` (`PROFILE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ic_user_pf_module`
--

LOCK TABLES `ic_user_pf_module` WRITE;
/*!40000 ALTER TABLE `ic_user_pf_module` DISABLE KEYS */;
INSERT INTO `ic_user_pf_module` VALUES (1,'01','0101',1),(1,'01','0102',2),(1,'01','0103',3),(1,'02','0201',4),(1,'02','0202',5),(1,'02','0203',6),(1,'03','0301',7),(1,'03','0302',8),(1,'03','0303',9),(1,'04','0401',10),(1,'04','0402',11),(1,'04','0403',12),(1,'04','0404',13),(1,'05','0501',14);
/*!40000 ALTER TABLE `ic_user_pf_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ic_user_profile`
--

DROP TABLE IF EXISTS `ic_user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ic_user_profile` (
  `PROFILE_ID` int(11) NOT NULL,
  `DESCRIPTION` varchar(45) NOT NULL,
  PRIMARY KEY (`PROFILE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ic_user_profile`
--

LOCK TABLES `ic_user_profile` WRITE;
/*!40000 ALTER TABLE `ic_user_profile` DISABLE KEYS */;
INSERT INTO `ic_user_profile` VALUES (1,'Administrator'),(2,'SecountUser');
/*!40000 ALTER TABLE `ic_user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mt_modules`
--

DROP TABLE IF EXISTS `mt_modules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mt_modules` (
  `CODE` varchar(2) NOT NULL,
  `DESCRIPTION` varchar(45) NOT NULL,
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mt_modules`
--

LOCK TABLES `mt_modules` WRITE;
/*!40000 ALTER TABLE `mt_modules` DISABLE KEYS */;
INSERT INTO `mt_modules` VALUES ('01','Customer Management'),('02','Supplier Management'),('03','Item Management'),('04','Sales & Purches Management'),('05','Configuration Management');
/*!40000 ALTER TABLE `mt_modules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mt_operation`
--

DROP TABLE IF EXISTS `mt_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mt_operation` (
  `CODE` varchar(2) NOT NULL,
  `DESCRIPTION` varchar(45) NOT NULL,
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mt_operation`
--

LOCK TABLES `mt_operation` WRITE;
/*!40000 ALTER TABLE `mt_operation` DISABLE KEYS */;
INSERT INTO `mt_operation` VALUES ('01','Add'),('02','Delete'),('03','Update'),('04','View'),('05','Download'),('06','Login'),('07','Logout');
/*!40000 ALTER TABLE `mt_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mt_page`
--

DROP TABLE IF EXISTS `mt_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mt_page` (
  `PAGE_ID` varchar(4) NOT NULL,
  `MODULE` varchar(2) NOT NULL,
  `PAGE_NAME` varchar(45) NOT NULL,
  `PAGE_URL` varchar(45) NOT NULL,
  PRIMARY KEY (`PAGE_ID`),
  KEY `fk_model_idx` (`MODULE`),
  CONSTRAINT `fk_model` FOREIGN KEY (`MODULE`) REFERENCES `mt_modules` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mt_page`
--

LOCK TABLES `mt_page` WRITE;
/*!40000 ALTER TABLE `mt_page` DISABLE KEYS */;
INSERT INTO `mt_page` VALUES ('0101','01','Add Customer','addCus'),('0102','01','Edit & View Customer','editViewCus'),('0103','01','View Customer','viewCus'),('0201','02','Add Supplier','addSupplier'),('0202','02','Edit & View Supplier','editViewSupplier'),('0203','02','View Supplier','viewSupplier'),('0301','03','Add Item','addItem'),('0302','03','Edit & View Item','editViewItem'),('0303','03','View Item','viewItem'),('0401','04','Add Sale','addSale'),('0402','04','Edit & View Sale','editViewSale'),('0403','04','Add Purches','addPurches'),('0404','04','Edit & View Purches','editViewPurches'),('0501','05','Cgange Password','changePassword');
/*!40000 ALTER TABLE `mt_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mt_status`
--

DROP TABLE IF EXISTS `mt_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mt_status` (
  `CODE` varchar(2) NOT NULL,
  `DESCRIPTION` varchar(10) NOT NULL,
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mt_status`
--

LOCK TABLES `mt_status` WRITE;
/*!40000 ALTER TABLE `mt_status` DISABLE KEYS */;
INSERT INTO `mt_status` VALUES ('01','Active'),('02','Inactive'),('03','Pending'),('04','Return');
/*!40000 ALTER TABLE `mt_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mt_store`
--

DROP TABLE IF EXISTS `mt_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mt_store` (
  `STOR_ID` int(11) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `STATUS` varchar(2) NOT NULL,
  `Version` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`STOR_ID`),
  KEY `store_fk_status_idx` (`STATUS`),
  CONSTRAINT `store_fk_status` FOREIGN KEY (`STATUS`) REFERENCES `mt_status` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mt_store`
--

LOCK TABLES `mt_store` WRITE;
/*!40000 ALTER TABLE `mt_store` DISABLE KEYS */;
INSERT INTO `mt_store` VALUES (1,'SHOP1','01','V1.00-(07022016)');
/*!40000 ALTER TABLE `mt_store` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-07  0:15:01
