-- MySQL dump 10.13  Distrib 8.0.22, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: HotelsBooking
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `ClientHotelReview`
--

DROP TABLE IF EXISTS `ClientHotelReview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ClientHotelReview` (
                                     `ClientId` int NOT NULL,
                                     `HotelId` int NOT NULL,
                                     `CreatedAt` datetime NOT NULL,
                                     `Stars` int NOT NULL,
                                     `Comment` text NOT NULL,
                                     PRIMARY KEY (`ClientId`,`HotelId`,`CreatedAt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ClientHotelReview`
--

LOCK TABLES `ClientHotelReview` WRITE;
/*!40000 ALTER TABLE `ClientHotelReview` DISABLE KEYS */;
/*!40000 ALTER TABLE `ClientHotelReview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ClientRoomReservation`
--

DROP TABLE IF EXISTS `ClientRoomReservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ClientRoomReservation` (
                                         `ClientId` int NOT NULL,
                                         `RoomId` int NOT NULL,
                                         `CreatedAt` datetime NOT NULL,
                                         `CheckIn` date NOT NULL,
                                         `CheckOut` date NOT NULL,
                                         `Status` enum('PENDING','CHECKED_IN') DEFAULT NULL,
                                         `TotalPrice` float NOT NULL,
                                         PRIMARY KEY (`ClientId`,`RoomId`,`CreatedAt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ClientRoomReservation`
--

LOCK TABLES `ClientRoomReservation` WRITE;
/*!40000 ALTER TABLE `ClientRoomReservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `ClientRoomReservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Hotel`
--

DROP TABLE IF EXISTS `Hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Hotel` (
                         `HotelId` int NOT NULL AUTO_INCREMENT,
                         `Name` varchar(255) NOT NULL,
                         `Latitude` float DEFAULT NULL,
                         `Longitude` float DEFAULT NULL,
                         `Address` varchar(255) DEFAULT NULL,
                         `Phone` varchar(255) DEFAULT '',
                         `AdminId` int NOT NULL,
                         PRIMARY KEY (`HotelId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Hotel`
--

LOCK TABLES `Hotel` WRITE;
/*!40000 ALTER TABLE `Hotel` DISABLE KEYS */;
INSERT INTO `Hotel` (`HotelId`, `Name`, `Latitude`, `Longitude`, `Address`, `Phone`, `AdminId`) VALUES (1,'sad2133',NULL,NULL,NULL,'21312',1);
/*!40000 ALTER TABLE `Hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HotelImage`
--

DROP TABLE IF EXISTS `HotelImage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `HotelImage` (
                              `URL` varchar(255) NOT NULL,
                              `HotelId` int NOT NULL,
                              PRIMARY KEY (`URL`,`HotelId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HotelImage`
--

LOCK TABLES `HotelImage` WRITE;
/*!40000 ALTER TABLE `HotelImage` DISABLE KEYS */;
/*!40000 ALTER TABLE `HotelImage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Room` (
                        `RoomId` int NOT NULL AUTO_INCREMENT,
                        `PricePerNight` float NOT NULL,
                        `Type` varchar(255) NOT NULL,
                        `MaxAdults` int NOT NULL,
                        `MaxChildren` int NOT NULL,
                        `HotelId` int DEFAULT NULL,
                        `Facilities` varchar(10000) DEFAULT '',
                        PRIMARY KEY (`RoomId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

LOCK TABLES `Room` WRITE;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
INSERT INTO `Room` (`RoomId`, `PricePerNight`, `Type`, `MaxAdults`, `MaxChildren`, `HotelId`, `Facilities`) VALUES (1,12.5,'fdsafasd',12,20,1,'Sauna Kaman'),(2,0.4321,'Master Bedroom',3,50,1,'Sauna'),(3,242,'One',1,1,1,'');
/*!40000 ALTER TABLE `Room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
                        `UserId` int NOT NULL AUTO_INCREMENT,
                        `Name` varchar(255) NOT NULL,
                        `Email` varchar(255) NOT NULL,
                        `Phone` varchar(255) NOT NULL,
                        `Type` enum('CLIENT','ADMIN') NOT NULL,
                        `Password` varchar(255) NOT NULL,
                        PRIMARY KEY (`UserId`),
                        UNIQUE KEY `Email` (`Email`),
                        UNIQUE KEY `Phone` (`Phone`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` (`UserId`, `Name`, `Email`, `Phone`, `Type`, `Password`) VALUES (1,'Bekh','mostafa.omar.m.s@gmail.com','0102','ADMIN','123');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-03 17:03:23
