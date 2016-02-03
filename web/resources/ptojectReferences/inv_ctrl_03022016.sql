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
  `ADDRESS` varchar(45) DEFAULT NULL,
  `TP_MOBILE` varchar(15) DEFAULT NULL,
  `TP_OFFICE` varchar(15) DEFAULT NULL,
  `STATUS` varchar(2) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `TIMESTAMP` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`CUS_ID`),
  KEY `fk_status_idx` (`STATUS`),
  KEY `fk_statustab_idx` (`STATUS`),
  CONSTRAINT `fk_statustab` FOREIGN KEY (`STATUS`) REFERENCES `mt_status` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ic_customer`
--

LOCK TABLES `ic_customer` WRITE;
/*!40000 ALTER TABLE `ic_customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `ic_customer` ENABLE KEYS */;
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
  `TOTAL` double DEFAULT NULL,
  `STATUS` varchar(2) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `TIMESTAMP` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`INV_ID`),
  KEY `fk_customertable_idx` (`CUS_ID`),
  KEY `fk_statustab_idx` (`STATUS`),
  CONSTRAINT `fk_customertable` FOREIGN KEY (`CUS_ID`) REFERENCES `ic_customer` (`CUS_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_statuss` FOREIGN KEY (`STATUS`) REFERENCES `mt_status` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `priK` int(11) NOT NULL AUTO_INCREMENT,
  `INV_ID` int(11) DEFAULT NULL,
  `ITEM_NO` int(11) DEFAULT NULL,
  `COUNT` varchar(45) DEFAULT NULL,
  `PRIZE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`priK`),
  KEY `fk_invoicetab_idx` (`INV_ID`),
  KEY `fk_itemtab_idx` (`ITEM_NO`),
  CONSTRAINT `fk_invoicetab` FOREIGN KEY (`INV_ID`) REFERENCES `ic_invoice` (`INV_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_itemtab` FOREIGN KEY (`ITEM_NO`) REFERENCES `ic_items` (`ITEM_NO`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
-- Table structure for table `ic_item_types`
--

DROP TABLE IF EXISTS `ic_item_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ic_item_types` (
  `CODE` varchar(2) NOT NULL,
  `DESCRIPTION` varchar(45) NOT NULL,
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ic_item_types`
--

LOCK TABLES `ic_item_types` WRITE;
/*!40000 ALTER TABLE `ic_item_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `ic_item_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ic_items`
--

DROP TABLE IF EXISTS `ic_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ic_items` (
  `ITEM_NO` int(11) NOT NULL AUTO_INCREMENT,
  `TYPE` varchar(2) DEFAULT NULL,
  `NAME` varchar(45) DEFAULT NULL,
  `COLOUR` varchar(45) DEFAULT NULL,
  `LENTH` varchar(45) DEFAULT NULL,
  `COUNT` varchar(45) DEFAULT NULL,
  `PRIZE` double DEFAULT NULL,
  `IMG_PATH` varchar(45) DEFAULT NULL,
  `STATUS` varchar(2) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `TIMESTAMP` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ITEM_NO`),
  KEY `fk_itemtypes_idx` (`TYPE`),
  KEY `fk_statustable_idx` (`STATUS`),
  CONSTRAINT `fk_itemtypes` FOREIGN KEY (`TYPE`) REFERENCES `ic_item_types` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
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
  `TOTAL` varchar(45) DEFAULT NULL,
  `STATUS` varchar(2) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `TIMESTAMP` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`PUR_ID`),
  KEY `fk_supplier_idx` (`SUP_ID`),
  KEY `fk_statusss_idx` (`STATUS`),
  CONSTRAINT `fk_statusss` FOREIGN KEY (`STATUS`) REFERENCES `mt_status` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_supplier` FOREIGN KEY (`SUP_ID`) REFERENCES `ic_supplier` (`SUP_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `pkpd` int(11) NOT NULL AUTO_INCREMENT,
  `PUR_ID` int(11) DEFAULT NULL,
  `ITEM_NO` int(11) DEFAULT NULL,
  `COUNT` varchar(45) DEFAULT NULL,
  `PRIZE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pkpd`),
  KEY `fk_purchasetab_idx` (`PUR_ID`),
  KEY `fk_itemstabb_idx` (`ITEM_NO`),
  CONSTRAINT `fk_itemstabb` FOREIGN KEY (`ITEM_NO`) REFERENCES `ic_items` (`ITEM_NO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_purchasetab` FOREIGN KEY (`PUR_ID`) REFERENCES `ic_purchase` (`PUR_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
-- Table structure for table `ic_supplier`
--

DROP TABLE IF EXISTS `ic_supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ic_supplier` (
  `SUP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  `ADDRESS` varchar(200) DEFAULT NULL,
  `TP_MOBILE` varchar(45) DEFAULT NULL,
  `TP_OFFICE` varchar(45) DEFAULT NULL,
  `STATUS` varchar(2) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `TIMESTAMP` timestamp NULL DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ic_user_pf_module`
--

LOCK TABLES `ic_user_pf_module` WRITE;
/*!40000 ALTER TABLE `ic_user_pf_module` DISABLE KEYS */;
INSERT INTO `ic_user_pf_module` VALUES (1,'01','0101',1),(1,'01','0102',2),(1,'01','0103',3),(1,'02','0201',4),(1,'02','0202',5),(1,'02','0203',6),(1,'03','0301',7),(1,'03','0302',8),(1,'03','0303',9),(1,'04','0401',10),(1,'04','0402',11),(1,'04','0403',12),(1,'04','0404',13);
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
INSERT INTO `mt_modules` VALUES ('01','Customer Management'),('02','Supplier Management'),('03','Item Management'),('04','Sales & Purches Management');
/*!40000 ALTER TABLE `mt_modules` ENABLE KEYS */;
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
INSERT INTO `mt_page` VALUES ('0101','01','Add Customer','addCus'),('0102','01','Edit & View Customer','editViewCus'),('0103','01','View Customer','viewCus'),('0201','02','Add Supplier','addSupplier'),('0202','02','Edit & View Supplier','editViewSupplier'),('0203','02','View Supplier','viewSupplier'),('0301','03','Add Item','addItem'),('0302','03','Edit & View Item','editViewItem'),('0303','03','View Item','viewItem'),('0401','04','Add Sale','addSale'),('0402','04','Edit & View Sale','editViewSale'),('0403','04','Add Purches','addPurches'),('0404','04','Edit & View Purches','editViewPurches');
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
INSERT INTO `mt_status` VALUES ('01','Active'),('02','Delete'),('03','Cancel');
/*!40000 ALTER TABLE `mt_status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-03 23:52:42
