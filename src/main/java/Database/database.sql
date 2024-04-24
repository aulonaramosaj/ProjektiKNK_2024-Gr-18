create database KNK;
use KNK

CREATE TABLE `Adresa` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Qyteti` varchar(50) DEFAULT NULL,
  `Komuna` varchar(50) DEFAULT NULL,
  `LlojiVendbanimit` char(1) DEFAULT NULL,
  `Fshati` varchar(50) DEFAULT NULL,
  `Rruga` varchar(100) DEFAULT NULL,
  `Objekti` varchar(50) DEFAULT NULL,
  `Hyrja` varchar(10) DEFAULT NULL,
  `Numri` int DEFAULT NULL,
  `NumriPostal` int DEFAULT NULL,
  `Created_at` datetime,
  `user` int ,
  PRIMARY KEY (`Id`)
);


CREATE TABLE `Qytetari` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `NrPersonal` char(10) DEFAULT NULL,
  `Emri` varchar(50) DEFAULT NULL,
  `EmriBabait` varchar(50) DEFAULT NULL,
  `EmriNenes` varchar(50) DEFAULT NULL,
  `Mbiemri` varchar(50) DEFAULT NULL,
  `Gjinia` varchar(20) DEFAULT NULL,
  `Ditelindja` varchar(10) DEFAULT NULL,
  `Adresa` int DEFAULT NULL,
  `NrTelefonit` varchar(20) DEFAULT NULL,
  `Email` varchar(150) DEFAULT NULL,
  `Created_at` datetime DEFAULT NULL,
  `user` int ,
  PRIMARY KEY (`Id`),
	FOREIGN KEY (`adresa`) REFERENCES `Adresa` (`Id`)
);

CREATE TABLE `User` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `NrPersonal` char(10) DEFAULT NULL,
  `Emri` varchar(50) DEFAULT NULL,
  `Mbiemri` varchar(50) DEFAULT NULL,
  `Email` varchar(150) DEFAULT NULL,
  `Username` varchar(50) DEFAULT NULL,
  `Salt` varchar(100) DEFAULT NULL,
  `Password` varchar(256) DEFAULT NULL,
  `Created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;