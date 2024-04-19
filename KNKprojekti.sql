SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

-- Database : 'KNKprojekti'

-- ------------------------------------------------------------------------------------------------------------------

/*Table structure for table Adresat*/
CREATE TABLE `Adresat` (
  `IDadresa` int(11) NOT NULL,
  `IDrajoni` int(11) NOT NULL,
  `IDkomuna` int(11) NOT NULL,
  `Lagjja` varchar(255) NOT NULL,
  `IDrruges` int(11) NOT NULL,
  `IDobjektit` int(11) NOT NULL,
  `Latitude` double NOT NULL,
  `Longitude` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Adresat` (`IDadresa`, `IDrajoni`, `IDkomuna`, `Lagjja`, `IDrruges`, `IDobjektit`, `Latitude`, `Longitude`) VALUES
(1, 1, 2, 'Dardania', 2, 2, 42.378399, 21.288837),
(2, 1, 1, 'Bregu i Diellit', 1, 1, 42.648608, 21.170843);

-- ----------------------------------------------------------------------------------------------------------------------------

/*Table structure for table Kahjet*/
CREATE TABLE `Kahjet` (
  `IDkahjet` int(11) NOT NULL,
  `Kahu` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------------------------------------------------------------------

/*Table structure for table Komunat*/
CREATE TABLE `Komunat` (
  `IDkomunat` int(11) NOT NULL,
  `EmriKomunes` varchar(255) NOT NULL,
  `KodiPostar` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Komunat` (`IDkomunat`, `EmriKomunes`, `KodiPostar`) VALUES
(1, 'Peje', '51000'),
(2, 'Prishtine', '10000');

-- ----------------------------------------------------------------------------------------------------------------------

/*Table structure for table Objektet*/
CREATE TABLE `Objektet` (
  `IDobjekti` int(11) NOT NULL,
  `LlojiObjektit` varchar(255) NOT NULL,
  `NumriKateve` int(11) NOT NULL,
  `NumriHyrjes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Objektet` (`IDobjekti`, `LlojiObjektit`, `NumriKateve`, `NumriHyrjes`) VALUES
(1, 'Biznis', 3, 23),
(2, 'Privat', 5, 35);

-- -----------------------------------------------------------------------------------------------------------------------

/*Table structure for table Perdoruesit*/
CREATE TABLE `Perdoruesit` (
  `IDperdoruesit` int(11) NOT NULL,
  `Emri` varchar(255) NOT NULL,
  `Mbiemri` varchar(255) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Perdoruesit` (`IDperdoruesit`, `Emri`, `Mbiemri`, `Username`, `Password`, `Email`) VALUES
(1, 'Arlinda', 'Beqiraj', 'arlindabeqiraj', '333a526fftxc43', 'arlindabeqirajj@gmail.com');

-- -----------------------------------------------------------------------------------------------------------------------

/*Table structure for table Rajonet*/
CREATE TABLE `Rajonet` (
  `IDrajoni` int(11) NOT NULL,
  `EmriRajonit` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Rajonet` (`IDrajoni`, `EmriRajonit`) VALUES
(2, 'Rrafshi i Dukagjinit'),
(1, 'Rrafshi i Kosoves');

-- ------------------------------------------------------------------------------------------------------------------------

/*Table structure for table Rruget*/
CREATE TABLE `Rruget` (
  `IDrruget` int(11) NOT NULL,
  `Komuna` varchar(255) NOT NULL,
  `Fshati` varchar(255) DEFAULT NULL,
  `KodiRruges` varchar(255) NOT NULL,
  `EmriRruges` varchar(255) NOT NULL,
  `LlojiRruges` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Rruget` (`IDrruget`, `Komuna`, `Fshati`, `KodiRruges`, `EmriRruges`, `LlojiRruges`) VALUES
(1, 'Peje', 'Leshan', 'DSL_288', 'Tropoja', 'Dytesore'),
(2, 'Prishtine', NULL, 'VKZ_721', 'Ilaz Kodra', 'Dytesore');

-- --------------------------------------------------------------------------------------------------------------------------
/*Indexes for tables*/

ALTER TABLE `Adresat`
  ADD PRIMARY KEY (`IDadresa`),
  ADD UNIQUE KEY `IDkomuna` (`IDkomuna`,`IDrruges`),
  ADD UNIQUE KEY `komuna_rruga` (`IDkomuna`,`IDrruges`),
  ADD KEY `IDobjektit` (`IDobjektit`),
  ADD KEY `IDrajoni` (`IDrajoni`),
  ADD KEY `IDrruges` (`IDrruges`);
  
  
ALTER TABLE `Kahjet`
  ADD PRIMARY KEY (`IDkahjet`);
  
ALTER TABLE `Komunat`
  ADD PRIMARY KEY (`IDkomunat`),
  ADD UNIQUE KEY `EmriKomunes` (`EmriKomunes`);
  
ALTER TABLE `Objektet`
  ADD PRIMARY KEY (`IDobjekti`);
  
ALTER TABLE `Perdoruesit`
  ADD PRIMARY KEY (`IDperdoruesit`),
  ADD UNIQUE KEY `Email` (`Email`),
  ADD UNIQUE KEY `Username` (`Username`);
  
ALTER TABLE `Rajonet`
  ADD PRIMARY KEY (`IDrajoni`),
  ADD UNIQUE KEY `EmriRajonit` (`EmriRajonit`);
  
ALTER TABLE `Rruget`
  ADD PRIMARY KEY (`IDrruget`),
  ADD UNIQUE KEY `unique_rruget` (`Komuna`,`EmriRruges`);
  
-- -------------------------------------------------------------------------------------------------------------------------
/*AUTO_INCREMENT for tables*/

ALTER TABLE `Adresat`
  MODIFY `IDadresa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
  
ALTER TABLE `Kahjet`
  MODIFY `IDkahjet` int(11) NOT NULL AUTO_INCREMENT;
  
ALTER TABLE `Komunat`
  MODIFY `IDkomunat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
  
ALTER TABLE `Objektet`
  MODIFY `IDobjekti` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
  
ALTER TABLE `Perdoruesit`
  MODIFY `IDperdoruesit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
  
ALTER TABLE `Rajonet`
  MODIFY `IDrajoni` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
  
ALTER TABLE `Rruget`
  MODIFY `IDrruget` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
  
-- -----------------------------------------------------------------------------------------------------------------------
/*Constraints for tables*/
ALTER TABLE `Adresat`
  ADD CONSTRAINT `adresat_ibfk_1` FOREIGN KEY (`IDkomuna`) REFERENCES `komunat` (`idkomunat`) ON DELETE CASCADE,
  ADD CONSTRAINT `adresat_ibfk_2` FOREIGN KEY (`IDobjektit`) REFERENCES `objektet` (`idobjekti`) ON DELETE CASCADE,
  ADD CONSTRAINT `adresat_ibfk_3` FOREIGN KEY (`IDrajoni`) REFERENCES `rajonet` (`idrajoni`) ON DELETE CASCADE,
  ADD CONSTRAINT `adresat_ibfk_4` FOREIGN KEY (`IDrruges`) REFERENCES `rruget` (`idrruget`) ON DELETE CASCADE;
COMMIT;

  




