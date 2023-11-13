/**
 * Khởi tạo các lịch sử tồn khi nhập thêm hàng vào hoặc các hoạt động trả hàng
 * Phiếu trả hàng được khởi tạo khi có khách hàng muốn trả quần áo về cho cửa hàng
 * Hóa đơn được khởi tạo khi mà có khách hàng muốn mua 1 san phẩm nào đó
 * @author: Phạm Thế Mạnh
 */

use QLyCHAM
go
create table LichSuTon(
	maLSTon varchar(20) primary key,
	maSP varchar(10),
	soLuongNhap int,
	ngayThayDoi timestamp
)
go
create table ChiTietTraHang(
	maPhieuTraHang varchar(10) not null,
	maSP varchar(10) not null,
	soLuongNhap int
);
ALTER TABLE ChiTietTraHang
ADD CONSTRAINT PK_ChiTietTraHang PRIMARY KEY (maPhieuTraHang, maSP);

go
create table PhieuTraHang(
	maPhieu varchar(10) primary key,
	maKH varchar(12) not null,
	maNQL varchar(10) not null,
	maHD varchar(10) not null,
	ngayTraHang datetime
);
--alter table PhieuTraHang
--add ngayTraHang datetime

create table HoaDon(
	maHD varchar(10) primary key,
	ngayLapHD datetime,
	maNV varchar(10) not null,
	maKH varchar(12) not null,
	coKhuyenMai float,
	tienKhachDua money,
	tongHoaDon money default 0
);

alter table HoaDon 
add constraint df_tongHD
default 0 for tongHoaDon

create index HoaDon_MaHD_Idx
on HoaDon(maHD)

select * from HoaDon

--go
--create trigger tinhKhuyenMai
--on HoaDon
--AFTER INSERT, UPDATE
--AS
--begin
	
--end;

create table ChiTietHoaDon(
	maSP varchar(10) not null,
	hoaDon varchar(10) not null,
	soLuong int
);

create index CTHD_MaSP_Idx
on ChiTietHoaDon(maSP)

go
--alter table ChiTietHoaDon
--drop constraint FK_hoaDon_HoaDon

--alter table ChiTietHoaDon
--add constraint FK_hoaDon_HoaDon Foreign key (hoaDon) references HoaDon(maHD) on delete cascade

go

ALTER TABLE ChiTietHoaDon
ADD CONSTRAINT PK_ChiTietHoaDon PRIMARY KEY (maSP, hoaDon);

alter table PhieuTraHang
add constraint FK_MaHD_PhieuTraHang_HoaDon foreign key (maHD) references HoaDon(maHD)

alter table ChiTietTraHang
DROP CONSTRAINT FK_maSp_CTTH_PhieuTraHang;
go
alter table ChiTietTraHang
add CONSTRAINT FK_maSp_CTTH_PhieuTraHang foreign key (maPhieuTraHang) references PhieuTraHang(maPhieu) on delete cascade

select* from SanPham where maSP = 'SP00000000'
/*set Trigger*/

go
create trigger trg_ThemtongHD
on ChiTietHoaDon
after insert
as
begin
	declare @productValue money
	set @productValue = (select giaNhap from inserted inner join SanPham on inserted.maSP = SanPham.maSP )
	declare @maSP varchar(10) = (select SanPham.maSP from SanPham join inserted on inserted.maSP = SanPham.maSP) 
	declare @productCount int =  (select soLuong from inserted)
	declare @currentProductCount int = (select slTonKho from SanPham where maSP = @maSP)
	declare @thue float = (select thue from SanPham where maSP = @maSP)
	declare @maHD varchar(10) = (select hoaDon from inserted)
	if (@thue = NULL)
		set @thue = 0
	declare @totalValue money = (@productValue *(1.25 + @thue))* @productCount
	if @productCount > @currentProductCount
	begin;
		throw 51000, 'so luong san pham nhap lon hon so luong san pham cho phep',1;
	end;
	update SanPham
	set slTonKho = slTonKho - @productCount
	where maSP = @maSP;
	update HoaDon
	set tongHoaDon = tongHoaDon + @totalValue
	where maHD = @maHD
	declare @tongHD money =( select tongHoaDon from HoaDon where maHD = @maHD)
	declare @khuyenMai float = 0
	if (@tongHD > 4000000)
		set @khuyenMai = 0.1
	else if (@tongHD > 2000000)
		set @khuyenMai = 0.05
	update HoaDon
	set coKhuyenMai = @khuyenMai
	where maHD = @maHD
end;

--go
--create trigger trg_SuatongHD
--on ChiTietHoaDon
--after update
--as
--begin
--	declare @productValue money
--	set @productValue = (select giaNhap from inserted inner join SanPham on inserted.maSP = SanPham.maSP )
--	declare @maSP varchar(10) = (select SanPham.maSP from SanPham join inserted on inserted.maSP = SanPham.maSP) 
--	declare @productCount int =  (select soLuong from inserted)
--	declare @currentProductCount int = (select slTonKho from SanPham where maSP = @maSP)
--	declare @thue float = (select thue from SanPham where maSP = @maSP)
--	if (@thue = NULL)
--		set @thue = 0
--	declare @totalValue money = (@productValue *(1.25 + @thue))* @productCount
--	if @productCount > @currentProductCount
--	begin;
--		throw 51000, 'so luong san pham nhap lon hon so luong san pham cho phep',1;
--	end;
--	update SanPham
--	set slTonKho = slTonKho - @productCount
--	where maSP = @maSP;
--	update HoaDon
--	set tongHoaDon = tongHoaDon + @totalValue
--	where maHD = (select maHD from inserted)
--end;


select sum(tongHoaDon) from HoaDon hd 
where (ngayLapHD between GETDATE() - 10 and GETDATE())
