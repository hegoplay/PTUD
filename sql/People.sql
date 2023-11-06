/*
 * các class trong sql nhằm lưu trữ thông tin khách hàng và nhân viên
 * author: Phạm Thế Mạnh
*/

create table NhanVien(
	maNV varchar(10) primary key,
	tenNV nvarchar(50),
	isNam bit,
	diachi nvarchar(max),
	sdt varchar(10),
	email varchar(max),
	luong money,
	chucVu bit,
	dangLamViec bit,
	maNQL varchar(10) not null,
	userName varchar(32) not null,
	cuaHangQL nvarchar(max)
)
create table TaiKhoan (
	userName varchar(32) primary key,
	password varchar(32),
	ngayLapTK date
)

create table KhachHang(
	maKH varchar(12) primary key,
	tenKH nvarchar(50),
	diaChi nvarchar(max),
	sdt varchar(10),
	namSinh int,
	gioiTinh bit,
)

insert into TaiKhoan
values ('hegoplay','abc',GETDATE()),
('hoang','abc',GETDATE()),
('linh','abc',GETDATE()),
('khoi','abc',GETDATE())
--update NhanVien
--set sdt = '0944713015'
--where maNV = 'NV00000000'

insert into NhanVien
values('NV00000000',N'Phạm Thế Mạnh',1,N'Phường Tăng Nhơn Phú B, TP Thủ Đức, TPHCM','0944713015','thmnh200@gmail.com',950000,1,1,'NV00000000','hegoplay',N'12 Nguyễn Văn Bảo, Quận Gò Vấp, TPHCM'),
('NV00000001',N'Nguyễn Xuân Khôi',1,N'Gò Vấp, TPHCM','0933224411','pmanhh19@gmail.com',635000,1,0,'NV00000000','khoi',NULL),
('NV00000002',N'Nguyễn Huy Hoàng',1,N'Gò Vấp, TPHCM','0933224231','huyhoangg191103@gmail.com',423000,1,0,'NV00000000','hoang',NULL),
('NV00000003',N'Hoàng Thị Mỹ Linh',0,N'Gò Vấp, TPHCM','0931324411','mylinh@gmail.com',20000000,1,0,'NV00000000','linh',NULL)

go
insert into KhachHang
values('KH0947842274',N'Đinh Thiện Quang',N'Phường 27, Quận Gò Vấp, TPHCM','0947842274',2002,1),
('KH0944894321',N'Lê Thị Hồng Vương',N'Bạch Mai, Hai Bà Trưng, Hà Nội','0944894321',1963,0)
