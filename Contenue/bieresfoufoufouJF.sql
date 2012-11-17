-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Ven 09 Novembre 2012 à 21:06
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `bieresfoufoufou`
--

-- --------------------------------------------------------

--
-- Structure de la table `biere`
--

DROP TABLE IF EXISTS ligne;
DROP TABLE IF EXISTS commande;
DROP TABLE IF EXISTS membre;
DROP TABLE IF EXISTS biere;



CREATE TABLE IF NOT EXISTS `biere` (
  `IDBiere` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NomBiere` varchar(20) NOT NULL,
  `NombreCaisse` int(11) NOT NULL,
  `Format` int(11) NOT NULL,
  `NombreParCaisse` int(11) NOT NULL,
  `Prix` double NOT NULL,
  PRIMARY KEY (`IDBiere`),
  UNIQUE KEY `IDBiere` (`IDBiere`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Contenu de la table `biere`
--

INSERT INTO `biere` (`IDBiere`, `NomBiere`, `NombreCaisse`, `Format`, `NombreParCaisse`, `Prix`) VALUES
(1, 'Hornbeer Stout', 4, 500, 12, 101.4),
(2, 'Carolus Triple', 4, 330, 24, 97.8),
(3, 'Boon Faro', 2, 250, 24, 76.8),
(4, 'To Øl Black Ball', 4, 660, 12, 134),
(5, 'Morpheus Extra', 5, 330, 24, 108),
(6, 'Westmalle Double', 10, 330, 24, 96),
(7, 'Slaapmutske Brune', 5, 750, 6, 90),
(8, 'Vapeur Cochonne', 3, 1500, 6, 102.85),
(9, 'Quintine Hercule', 6, 330, 20, 138),
(10, 'Corsendonk Pater', 4, 330, 24, 102);

-- --------------------------------------------------------

-- Structure de la table `membre`
--

CREATE TABLE IF NOT EXISTS `membre` (
  `IDMembre` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `Prenom` varchar(20) NOT NULL,
  `Nom` varchar(20) NOT NULL,
  `NomUtilisateur` varchar(30) NOT NULL,
  `MotPasse` varchar(15) NOT NULL,
  `Ville` varchar(20) NOT NULL,
  `CodePostal` varchar(7) NOT NULL,
  `Courriel` varchar(50) NOT NULL,
  `Admin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IDMembre`),
  UNIQUE KEY `IDMembre` (`IDMembre`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `membre`
--

INSERT INTO `membre` (`IDMembre`, `Prenom`, `Nom`, `NomUtilisateur`, `MotPasse`, `Ville`, `CodePostal`, `Courriel`, `Admin`) VALUES
(1, 'Admn', 'Nistrateur', 'admin', 'admin123', 'Springfield', 'G1Q1Q9', 'admin@hotmail.com', 1),
(2, 'Rory', 'B. Bellows', 'RoryB', 'rory1234', 'Springfield', 'G4G4G4', 'rory@hotmail.com', 0),
(3, 'Jo', 'Lamothe', 'pet', 'pet', 'Petland', 'G1Q1Q9', 'pet@pet.pet', 0),
(9, 'Jung', 'Joe', 'pow', 'pow', 'Petland', 'G1Q1Q9', 'pet@pet.pet', 0);
-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE IF NOT EXISTS `commande` (
  `IDCommande` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `IDMembre` bigint(20) unsigned NOT NULL,
  `CoutTotal` double NOT NULL,
  `TPS` double NOT NULL,
  `TVQ` double NOT NULL,
   `datecom` DATETIME NOT NULL,
  PRIMARY KEY (`IDCommande`),
  UNIQUE KEY `IDCommande` (`IDCommande`),
  FOREIGN KEY `commande` (`IDMembre`) REFERENCES `membre` (`IDMembre`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;
--
-- Contraintes pour la table `commande`
--

--
-- Contenu de la table `commande`
--

INSERT INTO `commande` (`IDCommande`, `IDMembre`, `CoutTotal`, `TPS`, `TVQ`, `datecom`) VALUES

(1, 1, 615.6, 30.78, 61.4061, SYSDATE());


-- --------------------------------------------------------

--
-- Structure de la table `ligne`
--

CREATE TABLE IF NOT EXISTS `ligne` (
  `IDLigne` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `IDCommande` bigint(20) unsigned NOT NULL,
  `IDBiere` bigint(20) unsigned NOT NULL,
  `NbCaisse` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`IDLigne`),
  UNIQUE KEY `IDLigne` (`IDLigne`),
  FOREIGN KEY (`IDCommande`) REFERENCES `commande` (`IDCommande`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`IDBiere`) REFERENCES `biere` (`IDBiere`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Contraintes pour la table `ligne`
--

--
-- Contenu de la table `ligne`
--

INSERT INTO `ligne` (`IDLigne`, `IDCommande`, `IDBiere`, `NbCaisse`) VALUES
(1, 1, 10, 2),
(2, 1, 10, 2);

-- --------------------------------------------------------


--
-- Contraintes pour les tables exportées
--



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
