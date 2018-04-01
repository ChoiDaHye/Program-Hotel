-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 01 Apr 2018 pada 14.48
-- Versi Server: 10.1.19-MariaDB
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
-- Struktur dari tabel `tb_kamar`
--

CREATE TABLE `tb_kamar` (
  `id_kamar` int(3) NOT NULL,
  `lantai` int(3) NOT NULL,
  `id_tipe` int(1) NOT NULL,
  `status` int(1) NOT NULL,
  `jumlah` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_kamar`
--

INSERT INTO `tb_kamar` (`id_kamar`, `lantai`, `id_tipe`, `status`, `jumlah`) VALUES
(1, 1, 1, 2, 1),
(2, 1, 1, 2, 1),
(3, 1, 1, 2, 1),
(4, 1, 1, 2, 1),
(5, 1, 1, 1, 1),
(6, 1, 1, 2, 1),
(7, 1, 1, 1, 1),
(8, 1, 1, 1, 1),
(9, 1, 1, 1, 1),
(10, 1, 1, 1, 1),
(11, 1, 1, 1, 1),
(12, 1, 1, 1, 1),
(13, 1, 1, 1, 1),
(14, 1, 1, 1, 1),
(15, 1, 1, 1, 1),
(16, 2, 1, 1, 1),
(17, 2, 1, 1, 1),
(18, 2, 1, 1, 1),
(19, 2, 1, 1, 1),
(20, 2, 1, 1, 1),
(21, 2, 3, 1, 1),
(22, 2, 3, 1, 1),
(23, 2, 3, 1, 1),
(24, 2, 3, 1, 1),
(25, 2, 3, 1, 1),
(26, 2, 3, 1, 1),
(27, 2, 3, 1, 1),
(28, 2, 3, 1, 1),
(29, 2, 3, 1, 1),
(30, 2, 3, 1, 1),
(31, 3, 2, 2, 1),
(32, 3, 2, 2, 1),
(33, 3, 2, 1, 1),
(34, 3, 2, 1, 1),
(35, 3, 2, 1, 1),
(36, 3, 2, 1, 1),
(37, 3, 2, 1, 1),
(38, 3, 2, 1, 1),
(39, 3, 2, 1, 1),
(40, 3, 2, 1, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_kamar_tipe`
--

CREATE TABLE `tb_kamar_tipe` (
  `id_tipe` varchar(1) NOT NULL,
  `nama_tipe` varchar(30) NOT NULL,
  `harga` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_kamar_tipe`
--

INSERT INTO `tb_kamar_tipe` (`id_tipe`, `nama_tipe`, `harga`) VALUES
('1', 'SUPERIOR ROOM', 745000),
('2', 'DELUXE ROOM', 1235000),
('3', 'FAMILY ROOM', 815000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_login`
--

CREATE TABLE `tb_login` (
  `id` varchar(100) NOT NULL,
  `user` varchar(100) NOT NULL,
  `pass` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_login`
--

INSERT INTO `tb_login` (`id`, `user`, `pass`) VALUES
('YF0001', 'admin', 'admin'),
('YF0002', 'YF0002', ''),
('YF0003', 'YF0003', 'resep');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_pegawai`
--

CREATE TABLE `tb_pegawai` (
  `id_karyawan` varchar(6) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `level` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_pegawai`
--

INSERT INTO `tb_pegawai` (`id_karyawan`, `nama`, `gender`, `alamat`, `level`) VALUES
('YF0001', 'Yosef Febrianes', 'L', 'Jln. Diponegoro', 'Administrator'),
('YF0002', 'Choaz', 'L', 'Bumi', 'Manager'),
('YF0003', 'Gedalya Anugrah', 'L', 'Cungkup', 'Resepsionis');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_pemesanan`
--

CREATE TABLE `tb_pemesanan` (
  `kode_booking` varchar(20) NOT NULL,
  `nik` varchar(30) NOT NULL,
  `t_r` date NOT NULL,
  `t_in` date NOT NULL,
  `t_out` date NOT NULL,
  `lama` int(3) NOT NULL,
  `harga_total` int(10) DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_pemesanan`
--

INSERT INTO `tb_pemesanan` (`kode_booking`, `nik`, `t_r`, `t_in`, `t_out`, `lama`, `harga_total`, `status`) VALUES
('B0104181528', '672017191', '2018-04-01', '2018-04-01', '2018-04-02', 1, 2235000, 0),
('B0104181552', '672017183', '2018-04-01', '2018-04-02', '2018-04-04', 2, 3960000, 0),
('B3103181233', '672017180', '2018-03-28', '2018-03-29', '2018-03-31', 2, 3960000, 1),
('B3103181438', '672017182', '2018-03-31', '2018-03-31', '2018-04-04', 4, 7920000, 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_pemesanan_detail`
--

CREATE TABLE `tb_pemesanan_detail` (
  `kode_booking` varchar(20) NOT NULL,
  `id_kamar` int(3) DEFAULT NULL,
  `harga` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_pemesanan_detail`
--

INSERT INTO `tb_pemesanan_detail` (`kode_booking`, `id_kamar`, `harga`) VALUES
('B3103181233', 2, 745000),
('B3103181233', 31, 1235000),
('B3103181438', 2, 745000),
('B3103181438', 32, 1235000),
('B0104181528', 1, 745000),
('B0104181528', 3, 745000),
('B0104181528', 4, 745000),
('B0104181552', 31, 1235000),
('B0104181552', 6, 745000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_pemesanan_tmp`
--

CREATE TABLE `tb_pemesanan_tmp` (
  `id_kamar` int(3) NOT NULL,
  `harga` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_tamu`
--

CREATE TABLE `tb_tamu` (
  `nik` varchar(30) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `telepon` varchar(15) NOT NULL,
  `gender` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_tamu`
--

INSERT INTO `tb_tamu` (`nik`, `nama`, `alamat`, `telepon`, `gender`) VALUES
('672017180', 'Choaz Bone', 'Seruni', '081234567890', 'L'),
('672017182', 'Gedalya', 'Cungkup', '081234567890', 'L'),
('672017183', 'Yosef', 'Bumi', '081234567890', 'L'),
('672017191', 'Rehuel', 'Bumi', '081234567890', 'L');

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
  ADD KEY `nik` (`nik`);

--
-- Indexes for table `tb_pemesanan_detail`
--
ALTER TABLE `tb_pemesanan_detail`
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
  MODIFY `id_kamar` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
