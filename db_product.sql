-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 17 Okt 2025 pada 18.34
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_product`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `product`
--

CREATE TABLE `product` (
  `id` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `harga` double NOT NULL,
  `kategori` varchar(255) NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `product`
--

INSERT INTO `product` (`id`, `nama`, `harga`, `kategori`, `stok`) VALUES
('PRD001', 'Laptop Lenovo', 8500000, 'Elektronik', 4),
('PRD004', 'Monitor 24 inch', 2200000, 'Elektronik', 5),
('PRD005', 'Headset Gaming', 350000, 'Elektronik', 7),
('PRD006', 'Smartphone Samsung', 4500000, 'Elektronik', 12),
('PRD007', 'Charger USB-C', 85000, 'Elektronik', 7),
('PRD008', 'Power Bank', 250000, 'Elektronik', 3),
('PRD009', 'Webcam HD', 180000, 'Elektronik', 11),
('PRD010', 'Speaker Bluetooth', 320000, 'Elektronik', 19),
('PRD011', 'Tablet Android', 2800000, 'Elektronik', 4),
('PRD012', 'Smartwatch', 1200000, 'Elektronik', 10),
('PRD013', 'Flash Drive 32GB', 65000, 'Makanan', 2),
('PRD014', 'Hard Disk 1TB', 750000, 'Elektronik', 5),
('PRD015', 'Router WiFi', 420000, 'Elektronik', 7),
('PRD016', 'Kabel HDMI', 45000, 'Elektronik', 5),
('PRD017', 'Printer Inkjet', 850000, 'Elektronik', 3),
('PRD018', 'Scanner Document', 650000, 'Elektronik', 6),
('PRD019', 'Cooling Pad', 120000, 'Aksesoris', 0),
('PRD020', 'Gaming Chair', 1800000, 'Furniture', 0),
('PRD021', 'Hp Xiaomi', 4999999, 'Elektronik', 15),
('PRD022', 'Hp Samsung', 5999999, 'Elektronik', 12),
('PRD023', 'Laptop HP', 9999999, 'Elektronik', 11);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
