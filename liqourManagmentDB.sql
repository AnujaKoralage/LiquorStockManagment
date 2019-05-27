-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 27, 2019 at 07:48 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `liquorstockdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `buyer`
--

CREATE TABLE `buyer` (
  `buyerID` int(50) NOT NULL,
  `name` varchar(1000) NOT NULL,
  `address` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `buyer`
--

INSERT INTO `buyer` (`buyerID`, `name`, `address`) VALUES
(1, 'Bonapala', 'Ceolombo'),
(2, 'Unapala', 'Kaluthara'),
(3, 'test1', 'test2');

-- --------------------------------------------------------

--
-- Table structure for table `buyer_grn`
--

CREATE TABLE `buyer_grn` (
  `grnID` int(50) NOT NULL,
  `buyerID` int(50) NOT NULL,
  `date` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `buyer_grn`
--

INSERT INTO `buyer_grn` (`grnID`, `buyerID`, `date`) VALUES
(1, 1, '2019/04/03'),
(2, 2, '2019/04/03'),
(3, 2, '2019/04/03'),
(4, 1, '2019/04/03'),
(5, 1, '2019/04/04'),
(6, 1, '2019/04/04'),
(7, 1, '2019/04/04'),
(8, 1, '2019/04/04'),
(9, 1, '2019/04/07'),
(10, 2, '2019/04/07'),
(11, 3, '2019/04/08');

-- --------------------------------------------------------

--
-- Table structure for table `buyer_item_details`
--

CREATE TABLE `buyer_item_details` (
  `itemID` int(50) NOT NULL,
  `buyerGrnID` int(50) NOT NULL,
  `qty` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `buyer_item_details`
--

INSERT INTO `buyer_item_details` (`itemID`, `buyerGrnID`, `qty`) VALUES
(1, 1, 10),
(2, 1, 10),
(1, 2, 10),
(2, 2, 10),
(3, 3, 25),
(4, 4, 10),
(1, 4, 10),
(4, 5, 40),
(2, 5, 30),
(6, 6, 10),
(5, 6, 25),
(4, 6, 10),
(3, 7, 5),
(4, 7, 10),
(2, 7, 50),
(2, 8, 10),
(4, 9, 10),
(5, 9, 10),
(4, 10, 10),
(8, 11, 10);

-- --------------------------------------------------------

--
-- Table structure for table `buyer_order_confirmation`
--

CREATE TABLE `buyer_order_confirmation` (
  `grnID` int(50) NOT NULL,
  `confirmation` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `itemID` int(50) NOT NULL,
  `name` varchar(1500) NOT NULL,
  `qty` int(50) NOT NULL,
  `supplierID` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`itemID`, `name`, `qty`, `supplierID`) VALUES
(1, 'Absolute Vodka', 0, 1),
(2, 'Gal Bottle', 0, 1),
(3, 'PPP', 0, 4),
(4, 'QQQ', 0, 4),
(5, 'Rum', 0, 3),
(6, 'Grant\'s Whisky', 0, 3),
(7, 'test1', 0, 5),
(8, 'test2', 0, 6);

-- --------------------------------------------------------

--
-- Table structure for table `item_price`
--

CREATE TABLE `item_price` (
  `itemID` int(50) NOT NULL,
  `buyingPrice` double NOT NULL,
  `dellingPrice` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `item_price`
--

INSERT INTO `item_price` (`itemID`, `buyingPrice`, `dellingPrice`) VALUES
(1, 1200, 1400),
(2, 1990, 2200),
(3, 2000, 2500),
(4, 1000, 1200),
(5, 2200, 2500),
(6, 3300, 3600),
(7, 13000, 13300),
(8, 12000, 12300);

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `id` int(50) NOT NULL,
  `name` varchar(1000) NOT NULL,
  `address` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id`, `name`, `address`) VALUES
(1, 'Poorni', 'Kadawatha'),
(2, 'Anuja', 'Kadawatha'),
(3, 'David', 'Panadura'),
(4, 'Dave', 'Kaluthara'),
(5, 'test1', 'test1'),
(6, 'test2', 'test2');

-- --------------------------------------------------------

--
-- Table structure for table `supplier_item_details`
--

CREATE TABLE `supplier_item_details` (
  `itemID` int(50) NOT NULL,
  `supGRNid` int(50) NOT NULL,
  `qty` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier_item_details`
--

INSERT INTO `supplier_item_details` (`itemID`, `supGRNid`, `qty`) VALUES
(2, 1, 100),
(1, 2, 60),
(2, 2, 50),
(2, 3, 100),
(2, 3, 100),
(4, 4, 100),
(1, 5, 10),
(3, 6, 100),
(4, 6, 100),
(5, 7, 100),
(5, 8, 10),
(5, 9, 5),
(5, 10, 5),
(5, 11, 5),
(6, 12, 50),
(7, 13, 100),
(8, 14, 100);

-- --------------------------------------------------------

--
-- Table structure for table `supplier_order_confermation`
--

CREATE TABLE `supplier_order_confermation` (
  `grnID` int(50) NOT NULL,
  `confirmation` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `supplier_order_grn`
--

CREATE TABLE `supplier_order_grn` (
  `grnID` int(50) NOT NULL,
  `supplierID` int(50) NOT NULL,
  `date` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier_order_grn`
--

INSERT INTO `supplier_order_grn` (`grnID`, `supplierID`, `date`) VALUES
(1, 1, '2019/04/01'),
(2, 1, '2019/04/02'),
(3, 1, '2019/04/03'),
(4, 4, '2019/04/03'),
(5, 1, '2019/04/03'),
(6, 4, '2019/04/03'),
(7, 3, '2019/04/03'),
(8, 3, '2019/04/03'),
(9, 3, '2019/04/03'),
(10, 3, '2019/04/03'),
(11, 3, '2019/04/03'),
(12, 3, '2019/04/03'),
(13, 5, '2019/04/08'),
(14, 6, '2019/04/08');

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE `test` (
  `itemID` int(11) NOT NULL,
  `name` varchar(1500) NOT NULL,
  `qty` int(11) NOT NULL,
  `supplierID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`itemID`, `name`, `qty`, `supplierID`) VALUES
(1, 'Absolute Vodka', 0, 1),
(2, 'Gal Bottle', 0, 1),
(3, 'PPP', 0, 4),
(4, 'QQQ', 0, 4),
(5, 'Rum', 0, 3),
(6, 'Grant\'s Whisky', 0, 3),
(7, 'test1', 0, 5),
(8, 'test2', 0, 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buyer`
--
ALTER TABLE `buyer`
  ADD PRIMARY KEY (`buyerID`);

--
-- Indexes for table `buyer_grn`
--
ALTER TABLE `buyer_grn`
  ADD PRIMARY KEY (`grnID`),
  ADD KEY `buyerID` (`buyerID`);

--
-- Indexes for table `buyer_item_details`
--
ALTER TABLE `buyer_item_details`
  ADD KEY `buyerGrnID` (`buyerGrnID`),
  ADD KEY `itemID` (`itemID`);

--
-- Indexes for table `buyer_order_confirmation`
--
ALTER TABLE `buyer_order_confirmation`
  ADD PRIMARY KEY (`grnID`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`itemID`),
  ADD KEY `supplierID` (`supplierID`);

--
-- Indexes for table `item_price`
--
ALTER TABLE `item_price`
  ADD KEY `item_id` (`itemID`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `supplier_item_details`
--
ALTER TABLE `supplier_item_details`
  ADD KEY `itemID` (`itemID`),
  ADD KEY `supGRNid` (`supGRNid`);

--
-- Indexes for table `supplier_order_confermation`
--
ALTER TABLE `supplier_order_confermation`
  ADD PRIMARY KEY (`grnID`);

--
-- Indexes for table `supplier_order_grn`
--
ALTER TABLE `supplier_order_grn`
  ADD PRIMARY KEY (`grnID`),
  ADD KEY `supplierID` (`supplierID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `buyer_grn`
--
ALTER TABLE `buyer_grn`
  ADD CONSTRAINT `buyer_grn_ibfk_1` FOREIGN KEY (`buyerID`) REFERENCES `buyer` (`buyerID`);

--
-- Constraints for table `buyer_item_details`
--
ALTER TABLE `buyer_item_details`
  ADD CONSTRAINT `buyer_item_details_ibfk_2` FOREIGN KEY (`buyerGrnID`) REFERENCES `buyer_grn` (`grnID`),
  ADD CONSTRAINT `buyer_item_details_ibfk_3` FOREIGN KEY (`itemID`) REFERENCES `item` (`itemID`);

--
-- Constraints for table `buyer_order_confirmation`
--
ALTER TABLE `buyer_order_confirmation`
  ADD CONSTRAINT `buyer_order_confirmation_ibfk_1` FOREIGN KEY (`grnID`) REFERENCES `buyer_grn` (`grnID`);

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`supplierID`) REFERENCES `supplier` (`id`);

--
-- Constraints for table `item_price`
--
ALTER TABLE `item_price`
  ADD CONSTRAINT `item_price_ibfk_1` FOREIGN KEY (`itemID`) REFERENCES `item` (`itemID`);

--
-- Constraints for table `supplier_item_details`
--
ALTER TABLE `supplier_item_details`
  ADD CONSTRAINT `supplier_item_details_ibfk_1` FOREIGN KEY (`itemID`) REFERENCES `item` (`itemID`),
  ADD CONSTRAINT `supplier_item_details_ibfk_2` FOREIGN KEY (`supGRNid`) REFERENCES `supplier_order_grn` (`grnID`);

--
-- Constraints for table `supplier_order_confermation`
--
ALTER TABLE `supplier_order_confermation`
  ADD CONSTRAINT `supplier_order_confermation_ibfk_1` FOREIGN KEY (`grnID`) REFERENCES `supplier_order_grn` (`grnID`);

--
-- Constraints for table `supplier_order_grn`
--
ALTER TABLE `supplier_order_grn`
  ADD CONSTRAINT `supplier_order_grn_ibfk_1` FOREIGN KEY (`supplierID`) REFERENCES `supplier` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
