USE [master]
GO
/****** Object:  Database [Epicgearhub2]    Script Date: 03/11/2023 2:35:31 CH ******/
CREATE DATABASE [Epicgearhub3]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Epicgearhub2', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\Epicgearhub2.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Epicgearhub2_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\Epicgearhub2_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [Epicgearhub2] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Epicgearhub2].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Epicgearhub2] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Epicgearhub2] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Epicgearhub2] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Epicgearhub2] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Epicgearhub2] SET ARITHABORT OFF 
GO
ALTER DATABASE [Epicgearhub2] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [Epicgearhub2] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Epicgearhub2] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Epicgearhub2] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Epicgearhub2] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Epicgearhub2] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Epicgearhub2] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Epicgearhub2] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Epicgearhub2] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Epicgearhub2] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Epicgearhub2] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Epicgearhub2] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Epicgearhub2] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Epicgearhub2] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Epicgearhub2] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Epicgearhub2] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Epicgearhub2] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Epicgearhub2] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Epicgearhub2] SET  MULTI_USER 
GO
ALTER DATABASE [Epicgearhub2] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Epicgearhub2] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Epicgearhub2] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Epicgearhub2] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Epicgearhub2] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Epicgearhub2] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [Epicgearhub2] SET QUERY_STORE = ON
GO
ALTER DATABASE [Epicgearhub2] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [Epicgearhub2]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 03/11/2023 2:35:31 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[username] [nvarchar](200) NOT NULL,
	[password] [varchar](200) NULL,
	[email] [varchar](200) NULL
PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Authorities]    Script Date: 03/11/2023 2:35:32 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Authorities](
	[id] [int]  IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](200) NOT NULL,
	[roleid] [nvarchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Case]    Script Date: 03/11/2023 2:35:32 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Case](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[productid] [int] NULL,
	[colour] [nvarchar](200) NULL,
	[main_type_support] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 03/11/2023 2:35:32 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[id] [int] NOT NULL,
	[name] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CPU]    Script Date: 03/11/2023 2:35:32 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CPU](
	[id] [int] NOT NULL,
	[productid] [int] NULL,
	[socket] [nvarchar](1) NULL,
	[cpu_series] [nvarchar](1) NULL,
	[toltal_cores] [nvarchar](1) NULL,
	[toltal_threads] [nvarchar](1) NULL,
	[performance_core_base_frequency] [nvarchar](1) NULL,
	[max_turbo_frequency] [nvarchar](1) NULL,
	[power] [nvarchar](1) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MainBoard]    Script Date: 03/11/2023 2:35:32 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MainBoard](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[productid] [int] NULL,
	[socket] [nvarchar](200) NULL,
	[mainboard_series] [nvarchar](200) NULL,
	[conjunto_de_chips] [nvarchar](200) NULL,
	[slot] [nvarchar](200) NULL,
	[dimension] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 03/11/2023 2:35:32 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[orderid] [int] NULL,
	[productid] [int] NULL,
	[quantity] [varchar](200) NULL,
	[price] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 03/11/2023 2:35:32 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](200) NULL,
	[createdate] [varchar](200) NULL,
	[address] [varchar](200) NULL,
	[phonenumber] [varchar](200) NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 03/11/2023 2:35:32 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](200) NULL,
	[price] [varchar](200) NULL,
	[image] [varchar](200) NULL,
	[brand] [varchar](200) NULL,
	[categoryid] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PSU]    Script Date: 03/11/2023 2:35:32 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PSU](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[productid] [int] NULL,
	[powersupplytype] [nvarchar](200) NULL,
	[poweroutput] [nvarchar](200) NULL,
	[size] [nvarchar](200) NULL,
	[plugcord] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RAM]    Script Date: 03/11/2023 2:35:32 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RAM](
[id] [int] IDENTITY(1,1) NOT NULL,
	[productid] [int] NULL,
	[ram_type] [nvarchar](200) NULL,
	[capacity] [nvarchar](200) NULL,
	[bus] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 03/11/2023 2:35:32 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[id] [nvarchar](10) NOT NULL,
	[name] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ROM]    Script Date: 03/11/2023 2:35:32 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ROM](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[productid] [int] NULL,
	[rom_type] [nvarchar](200) NULL,
	[capacity] [nvarchar](200) NULL,
	[standard_rom] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[VGA]    Script Date: 03/11/2023 2:35:32 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[VGA](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[productid] [int] NULL,
	[gpuname] [nvarchar](200) NULL,
	[vram] [nvarchar](200) NULL,
	[bus] [nvarchar](200) NULL,
	[processing_units] [nvarchar](200) NULL,
	[gateway] [nvarchar](200) NULL,
	[dimension] [nvarchar](200) NULL,
	[power_consumption] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Authorities]  WITH CHECK ADD  CONSTRAINT [FK_Account_Authorities] FOREIGN KEY([username])
REFERENCES [dbo].[Account] ([username])
GO
ALTER TABLE [dbo].[Authorities] CHECK CONSTRAINT [FK_Account_Authorities]
GO
ALTER TABLE [dbo].[Authorities]  WITH CHECK ADD  CONSTRAINT [FK_Role_Authorities] FOREIGN KEY([roleid])
REFERENCES [dbo].[Role] ([id])
GO
ALTER TABLE [dbo].[Authorities] CHECK CONSTRAINT [FK_Role_Authorities]
GO
ALTER TABLE [dbo].[Case]  WITH CHECK ADD  CONSTRAINT [FK_Case_Product] FOREIGN KEY([productid])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[Case] CHECK CONSTRAINT [FK_Case_Product]
GO
ALTER TABLE [dbo].[CPU]  WITH CHECK ADD  CONSTRAINT [FK_CPU_Product] FOREIGN KEY([productid])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[CPU] CHECK CONSTRAINT [FK_CPU_Product]
GO
ALTER TABLE [dbo].[MainBoard]  WITH CHECK ADD  CONSTRAINT [FK_Mainboard_Product] FOREIGN KEY([productid])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[MainBoard] CHECK CONSTRAINT [FK_Mainboard_Product]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Order] FOREIGN KEY([orderid])
REFERENCES [dbo].[Orders] ([id])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Order]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Product] FOREIGN KEY([productid])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Product]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Account_Order] FOREIGN KEY([username])
REFERENCES [dbo].[Account] ([username])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Account_Order]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Category_Product] FOREIGN KEY([categoryid])
REFERENCES [dbo].[Category] ([id])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Category_Product]
GO
ALTER TABLE [dbo].[PSU]  WITH CHECK ADD  CONSTRAINT [FK_PSU_Product] FOREIGN KEY([productid])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[PSU] CHECK CONSTRAINT [FK_PSU_Product]
GO
ALTER TABLE [dbo].[RAM]  WITH CHECK ADD  CONSTRAINT [FK_RAM_Product] FOREIGN KEY([productid])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[RAM] CHECK CONSTRAINT [FK_RAM_Product]
GO
ALTER TABLE [dbo].[ROM]  WITH CHECK ADD  CONSTRAINT [FK_ROM_Product] FOREIGN KEY([productid])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[ROM] CHECK CONSTRAINT [FK_ROM_Product]
GO
ALTER TABLE [dbo].[VGA]  WITH CHECK ADD  CONSTRAINT [FK_VGA_Product] FOREIGN KEY([productid])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[VGA] CHECK CONSTRAINT [FK_VGA_Product]
GO
USE [master]
GO
ALTER DATABASE [Epicgearhub2] SET  READ_WRITE 
GO


-- Thêm dữ liệu vào bảng Category
INSERT INTO Category (id, name) VALUES (1, 'CPU');
INSERT INTO Category (id, name) VALUES (2, 'VGA');
INSERT INTO Category (id, name) VALUES (3, 'Mainboard');
INSERT INTO Category (id, name) VALUES (4, 'ROM');
INSERT INTO Category (id, name) VALUES (5, 'RAM');
INSERT INTO Category (id, name) VALUES (6, 'PSU');
INSERT INTO Category (id, name) VALUES (7, 'Case');

-- Thêm 10 sản phẩm thuộc danh mục "Nguồn"
INSERT INTO Product (name, price, image, brand, categoryid)
VALUES
( N'Nguồn máy tính Deepcool PF550 - 80 Plus (550W) 990.000₫', '50.00', 'ID1.jpg', 'Deepcool', 6),
( N'Nguồn máy tính Deepcool PK750D - 80 Plus Bronze (750W)', '55.00', 'ID2.jpg', 'Deepcool', 6),
( N'Nguồn máy tính ASUS TUF Gaming 1000W - 80 Plus Gold - Full Modular (1000W)', '60.00', 'ID3.jpg', 'ASUS', 6),
( N'Nguồn máy tính Corsair RM750e - 80 Plus Gold - Full Modular (750W) (CP-9020248-NA)', '60.00', 'ID4.jpg', 'Corsair', 6),
( N'Nguồn máy tính MSI MPG A1000G PCIE5 - 80 Plus Gold - Full Modular (1000W)', '60.00', 'ID5.jpg', 'MSI', 6),
( N'Nguồn máy tính Deepcool PF450D - 80 Plus (450W)', '60.00', 'ID6.jpg', 'Deepcool', 6),
( N'Nguồn máy tính MSI MAG A650BN - 80 Plus Bronze (650W)', '60.00', 'ID7.jpg', 'MSI', 6),
( N'Nguồn máy tính ASUS ROG Thor 1200P2 - 80 Plus Platinum - Full Modular (1200W)', '60.00', 'ID8.jpg', 'ASUS', 6),
( N'Nguồn máy tính NZXT C650W - 80 Plus Bronze - Semi Modular (650W)', '60.00', 'ID9.jpg', 'NZXT', 6),
( N'Nguồn máy tính Corsair RM1200x ATX 3.0 - 80 Plus Gold - Full Modular (1200W)', '60.00', 'ID10.jpg', 'Corsair', 6),
-- Thêm 10 sản phẩm thuộc danh mục "VGA"
( N'Card màn hình ASUS ROG Strix GeForce RTX 4090 OC White Edition 24GB (ROG-STRIX-RTX4090-O24G-WHITE)', '150.00', 'ID11.jpg', 'ASUS', 2),
( N'Card màn hình MSI GeForce RTX 4070 VENTUS 3X 12GB OC', '160.00', 'ID12.jpg', 'MSI', 2),
( N'Card màn hình MSI GeForce RTX 4070 GAMING X TRIO 12GB', '170.00', 'ID13.jpg', 'MSI', 2),
( N'Card màn hình GIGABYTE GeForce RTX 4060 Ti GAMING OC 8G (GV-N406TGAMING-OC-8GD)', '123.00', 'ID14.jpg', 'GIGABYTE', 2),
( N'Card màn hình MSI GeForce RTX 4060 Ti VENTUS 2X BLACK 8G OC', '180.00', 'ID15.jpg', 'MSI', 2),
( N'Card màn hình ASUS ROG Strix GeForce RTX 4080 16GB (ROG-STRIX-RTX4080-16G-GAMING)', '20.00', 'ID16.jpg', 'ASUS', 2),
( N'Card màn hình MSI GeForce RTX 4060 Ti GAMING X SLIM 16G', '165.00', 'ID17.jpg', 'MSI', 2),
( N'Card màn hình GIGABYTE Radeon RX 6600 EAGLE 8G (GV-R66EAGLE-8GD)', '153.00', 'ID18.jpg', 'GIGABYTE', 2),
( N'Card Màn Hình LEADTEK QUADRO RTX A6000 48GB GDDR6 ECC', '155.00', 'ID19.jpg', 'LEADTEK', 2),
( N'Card màn hình ASUS Dual GeForce RTX 4070 White OC Edition 12GB (DUAL-RTX4070-O12G-WHITE)', '18700', 'ID20.jpg', 'ASUS', 2),
-- Thêm 10 sản phẩm thuộc danh mục "Case"
( N'Vỏ máy tính MIK LV12 WHITE', '70.00', 'ID21.jpg', 'Brand', 7),
( N'Vỏ máy tính Corsair 3000D Airflow Mid Tower TG Black (CC-9011251-WW)', '75.00', 'ID22.jpg', 'Brand', 7),
( N'Vỏ máy tính MIK LV12 M BLACK', '80.00', 'ID23.jpg', 'Brand', 7),
( N'Vỏ máy tính Lian Li Lancool 3 RGB White', '70.00', 'ID24.jpg', 'Brand', 7),
( N'Vỏ máy tính Lian Li O11D EVO XL White', '75.00', 'ID25.jpg', 'Brand', 7),
( N'Vỏ máy tính Lian Li O11D EVO XL Black', '80.00', 'ID26.jpg', 'Brand', 7),
( N'Vỏ máy tính GIGABYTE AORUS C300 Glass Mid-Tower', '80.00', 'ID27.jpg', 'Brand', 7),
( N'Vỏ máy tính Cooler Master MasterCase HAF 500 White', '75.00', 'ID28.jpg', 'Brand', 7),
( N'Vỏ máy tính Cougar Duoface RGB', '80.00', 'ID29.jpg', 'Brand', 7),
( N'Vỏ máy tính ASUS TUF Gaming GT502 White', '80.00', 'ID30.jpg', 'Brand', 7),
-- Thêm tiếp 7 sản phẩm khác tương tự cho Case
-- Thêm 10 sản phẩm thuộc danh mục "CPU"
( N'Bộ vi xử lý AMD Athlon 3000G / 3.5GHz / 2 nhân 4 luồng / 5MB / AM4', '80.00', 'ID31.jpg', 'Brand', 1),
( N'Bộ vi xử lý Intel Core i9 13900KS / 3.2GHz Turbo 6.0GHz / 24 Nhân 32 Luồng / 36MB', '210.00', 'ID32.jpg', 'Intel', 1),
( N'Bộ vi xử lý Intel Core i3 13100 / 3.4GHz Turbo 4.5GHz / 4 Nhân 8 Luồng / 12MB /', '220.00', 'ID33.jpg', 'Intel', 1),
( N'Bộ vi xử lý Intel Core i3 13100F / 3.4GHz Turbo 4.5GHz / 4 Nhân 8 Luồng / 12MB /', '210.00', 'ID34.jpg', 'Intel', 1),
( N'Bộ vi xử lý AMD Ryzen 9 5950X / 3.4GHz Boost 4.9GHz / 16 nhân 32 luồng / 64MB /', '240.00', 'ID35.jpg', 'AMD', 1),
( N'Bộ vi xử lý AMD Ryzen 7 7700 / 3.8GHz Boost 5.3GHz / 8 nhân 16 luồng / 40MB /', '200.00', 'ID36.jpg', 'AMD', 1),
( N'Bộ vi xử lý AMD Ryzen 5 7600X / 4.7GHz Boost 5.3GHz / 6 nhân 12 luồng / 38MB /', '220.00', 'ID37.jpg', 'AMD', 1),
( N'Bộ vi xử lý AMD Ryzen 7 5700X / 3.4GHz Boost 4.6GHz / 8 nhân 16 luồng / 32MB /', '190.00', 'ID38.jpg', 'AMD', 1),
( N' Bộ vi xử lý AMD Ryzen 5 5600 / 3.5GHz Boost 4.4GHz / 6 nhân 12 luồng / 32MB /', '220.00', 'ID39.jpg', 'AMD', 1),
( N'Bộ vi xử lý AMD Ryzen Threadripper PRO 5995WX / 2.7GHz Boost 4.5GHz / 64', '150.00', 'ID40.jpg', 'AMD', 1);
--bắt đầu dữ liệu bằng 0)

-- Thêm dữ liệu vào bảng "Account"
INSERT INTO [Account] ([username], [password], [email])
VALUES
    ('minhduy', '123','duy@gmail.com'),
    ('hoangan', '123','suytinh879@gmail.com'),
    ('minhhieu', '123','hieu@gmail.com');

-- Thêm dữ liệu vào bảng Authorities với Role tương ứng
INSERT INTO Authorities ( username, roleid) VALUES ( 'minhduy', 'STAF'); -- Liên kết với Role 'Admin'
INSERT INTO Authorities ( username, roleid) VALUES ( 'hoangan', 'DIRE'); -- Liên kết với Role 'User'
INSERT INTO Authorities ( username, roleid) VALUES ( 'minhhieu', 'CUST'); -- Liên kết với Role 'User'

-- Thêm dữ liệu vào bảng Role
INSERT INTO Role (id, name) VALUES ('CUST', 'Customers');
INSERT INTO Role (id, name) VALUES ('DIRE', 'Directors');
INSERT INTO Role (id, name) VALUES ('STAF', 'Staffs');