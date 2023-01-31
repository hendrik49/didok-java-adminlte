-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 30, 2023 at 03:18 PM
-- Server version: 8.0.18
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `didok_adminlte`
--

-- --------------------------------------------------------

--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `authority` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` (`username`, `authority`) VALUES
('didok', 'USER'),
('naruto', 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `barangs`
--

CREATE TABLE `barangs` (
  `id` bigint(50) NOT NULL,
  `nama` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `harga` double NOT NULL,
  `stock` double NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `barangs`
--

INSERT INTO `barangs` (`id`, `nama`, `harga`, `stock`, `added_date`) VALUES
(1, 'Barang 1', 100, 0, '2020-03-29 00:52:34'),
(2, 'Barang 2', 120, 5, '2020-03-29 01:18:59'),
(3, 'Barang 3', 150, 4, '2020-03-29 01:18:59'),
(4, 'Barang 4', 200, 2, '2020-03-29 01:18:59'),
(5, 'Barang 5', 300, 5, '2020-03-29 01:18:59'),
(6, 'Barang 6', 400, 6, '2023-01-30 07:36:49'),
(7, 'Barang 23', 200, 10, '2023-01-30 08:37:14');

-- --------------------------------------------------------

--
-- Table structure for table `perusahaans`
--

CREATE TABLE `perusahaans` (
  `id` bigint(11) NOT NULL,
  `kode` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `nama` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `perusahaans`
--

INSERT INTO `perusahaans` (`id`, `kode`, `nama`, `date`) VALUES
(1, 'AB', 'Perushaan 1', '2023-01-30 08:37:31'),
(2, 'AB2', 'Perushaan 2', '2023-01-30 09:02:48');

-- --------------------------------------------------------

--
-- Table structure for table `transaksis`
--

CREATE TABLE `transaksis` (
  `id` bigint(20) NOT NULL,
  `id_perusahaan` bigint(11) NOT NULL,
  `id_barang` bigint(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `grand_total` double NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `transaksis`
--

INSERT INTO `transaksis` (`id`, `id_perusahaan`, `id_barang`, `qty`, `grand_total`, `date`) VALUES
(1, 1, 1, 2, 200, '2023-01-30 10:02:35'),
(2, 1, 1, 5, 500, '2023-01-30 10:42:04'),
(3, 2, 3, 6, 900, '2023-01-30 10:42:19'),
(4, 1, 1, 5, 500, '2023-01-30 10:58:14'),
(5, 1, 1, 5, 500, '2023-01-30 11:01:26');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
('didok', '$2a$10$JN6dgvRarM/3G9EGNdMLFOqVIMrC.j7jhv0j5K6AWlZTa8zL9Bvfq', 1),
('naruto', '$2a$10$726Tb35wehWFmrixtdy55.ChWcTKMcg.LwTwDDcPEry2Gx0dy.QxO', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `authorities`
--
ALTER TABLE `authorities`
  ADD UNIQUE KEY `authorities_idx_1` (`username`,`authority`);

--
-- Indexes for table `barangs`
--
ALTER TABLE `barangs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `perusahaans`
--
ALTER TABLE `perusahaans`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaksis`
--
ALTER TABLE `transaksis`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barangs`
--
ALTER TABLE `barangs`
  MODIFY `id` bigint(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `perusahaans`
--
ALTER TABLE `perusahaans`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `transaksis`
--
ALTER TABLE `transaksis`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
