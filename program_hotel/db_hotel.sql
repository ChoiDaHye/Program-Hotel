-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 25, 2018 at 03:33 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 7.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_hotel`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_kamar`
--

CREATE TABLE `tb_kamar` (
  `id_kamar` int(3) NOT NULL,
  `lantai` int(3) NOT NULL,
  `id_tipe` int(1) NOT NULL,
  `status` int(1) NOT NULL,
  `jumlah` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_kamar`
--

INSERT INTO `tb_kamar` (`id_kamar`, `lantai`, `id_tipe`, `status`, `jumlah`) VALUES
(1, 1, 1, 1, 1),
(2, 1, 1, 1, 1),
(3, 1, 2, 1, 1),
(4, 1, 2, 1, 1),
(5, 1, 3, 1, 1),
(6, 1, 1, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_kamar_tipe`
--

CREATE TABLE `tb_kamar_tipe` (
  `id_tipe` varchar(1) NOT NULL,
  `nama_tipe` varchar(30) NOT NULL,
  `harga` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_kamar_tipe`
--

INSERT INTO `tb_kamar_tipe` (`id_tipe`, `nama_tipe`, `harga`) VALUES
('1', 'SUPERIOR ROOM', 745000),
('2', 'DELUXE ROOM', 1235000),
('3', 'FAMILY ROOM', 815000);

-- --------------------------------------------------------

--
-- Table structure for table `tb_login`
--

CREATE TABLE `tb_login` (
  `id` varchar(100) NOT NULL,
  `user` varchar(100) NOT NULL,
  `pass` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_login`
--

INSERT INTO `tb_login` (`id`, `user`, `pass`) VALUES
('YF0001', 'admin', 'admin'),
('YF0002', 'YF0002', ''),
('YF0003', 'YF0003', 'resep');

-- --------------------------------------------------------

--
-- Table structure for table `tb_pegawai`
--

CREATE TABLE `tb_pegawai` (
  `id_karyawan` varchar(6) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `level` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_pegawai`
--

INSERT INTO `tb_pegawai` (`id_karyawan`, `nama`, `gender`, `alamat`, `level`) VALUES
('YF0001', 'Yosef Febrianes', 'L', 'Jln. Diponegoro', 'Administrator'),
('YF0002', 'Choaz', 'L', 'Bumi', 'Manager'),
('YF0003', 'Gedalya Anugrah', 'L', 'Cungkup', 'Resepsionis');

-- --------------------------------------------------------

--
-- Table structure for table `tb_pemesanan`
--

CREATE TABLE `tb_pemesanan` (
  `kode_booking` varchar(6) NOT NULL,
  `nik` varchar(30) NOT NULL,
  `id_karyawan` varchar(6) NOT NULL,
  `harga_total` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_pemesanan_in`
--

CREATE TABLE `tb_pemesanan_in` (
  `kode_booking` varchar(6) NOT NULL,
  `id_kamar` varchar(5) NOT NULL,
  `hari` varchar(7) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_pemesanan_out`
--

CREATE TABLE `tb_pemesanan_out` (
  `kode_booking` varchar(6) NOT NULL,
  `id_kamar` varchar(5) NOT NULL,
  `hari` varchar(7) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_tamu`
--

CREATE TABLE `tb_tamu` (
  `nik` varchar(30) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `telepon` varchar(15) NOT NULL,
  `gender` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_kamar`
--
ALTER TABLE `tb_kamar`
  ADD PRIMARY KEY (`id_kamar`),
  ADD KEY `id_tipe` (`id_tipe`);

--
-- Indexes for table `tb_kamar_tipe`
--
ALTER TABLE `tb_kamar_tipe`
  ADD PRIMARY KEY (`id_tipe`);

--
-- Indexes for table `tb_login`
--
ALTER TABLE `tb_login`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_pegawai`
--
ALTER TABLE `tb_pegawai`
  ADD PRIMARY KEY (`id_karyawan`);

--
-- Indexes for table `tb_pemesanan`
--
ALTER TABLE `tb_pemesanan`
  ADD PRIMARY KEY (`kode_booking`),
  ADD KEY `nik` (`nik`),
  ADD KEY `id_karyawan` (`id_karyawan`);

--
-- Indexes for table `tb_pemesanan_in`
--
ALTER TABLE `tb_pemesanan_in`
  ADD UNIQUE KEY `kode_booking` (`kode_booking`),
  ADD KEY `id_kamar` (`id_kamar`);

--
-- Indexes for table `tb_pemesanan_out`
--
ALTER TABLE `tb_pemesanan_out`
  ADD KEY `kode_booking` (`kode_booking`),
  ADD KEY `id_kamar` (`id_kamar`);

--
-- Indexes for table `tb_tamu`
--
ALTER TABLE `tb_tamu`
  ADD PRIMARY KEY (`nik`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_kamar`
--
ALTER TABLE `tb_kamar`
  MODIFY `id_kamar` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
