-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Ven 02 Novembre 2012 à 20:16
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

CREATE TABLE IF NOT EXISTS `biere` (
  `IDBiere` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NomBiere` varchar(20) NOT NULL,
  `NombreCaisse` int(11) NOT NULL,
  `Format` int(11) NOT NULL,
  `NombreParCaisse` int(11) NOT NULL,
  `Prix` double NOT NULL,
  PRIMARY KEY (`IDBiere`),
  UNIQUE KEY `IDBiere` (`IDBiere`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
  PRIMARY KEY (`IDCommande`),
  UNIQUE KEY `IDCommande` (`IDCommande`),
  UNIQUE KEY `IDMembre` (`IDMembre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `ligne`
--

CREATE TABLE IF NOT EXISTS `ligne` (
  `IDLigne` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `IDCommande` bigint(20) unsigned NOT NULL,
  `IDBiere` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`IDLigne`),
  UNIQUE KEY `IDLigne` (`IDLigne`),
  UNIQUE KEY `IDCommande` (`IDCommande`),
  UNIQUE KEY `IDBiere` (`IDBiere`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `membre`
--

INSERT INTO `membre` (`IDMembre`, `Prenom`, `Nom`, `NomUtilisateur`, `MotPasse`, `Ville`, `CodePostal`, `Courriel`, `Admin`) VALUES
(1, 'q', 'q', 'q', 'q', 'q', 'q', 'q', 0),
(2, 'q', 'q', 'q', 'q', 'q', 'q', 'q', 0),
(3, 'j', 'gh', 'j', 'r', 'j', 'j', 'j', 0),
(4, 'lkl;kl;k', 'kl;kl;kl;', 'kl;kl;k', 'kl;k', 'kl;', 'kl;', 'kl;', 0);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`IDMembre`) REFERENCES `membre` (`IDMembre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `ligne`
--
ALTER TABLE `ligne`
  ADD CONSTRAINT `ligne_ibfk_1` FOREIGN KEY (`IDCommande`) REFERENCES `commande` (`IDCommande`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ligne_ibfk_2` FOREIGN KEY (`IDBiere`) REFERENCES `biere` (`IDBiere`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
