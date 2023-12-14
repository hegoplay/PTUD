/*
created by Pham The Manh
table SanPham
*/

use QLyCHAM
go
create table SanPham(
	maSP varchar(10) primary key,
	tenSP nvarchar(150),
	giaNhap money,
	slTonKho smallint,
	kichThuoc varchar(5),
	mauSac nvarchar(50),
	isNam bit,
	conKinhDoanh bit,
	hinhAnh nvarchar(MAX),
	maLoaiSP varchar(3),
	maNCC varchar(10),
	thue float default 0
)
create index PK_idx on SanPham(maSP)
--alter table SanPham
--alter column maLoaiSP char(3)
--go
--alter table SanPham
--add constraint df_thue
--default 0 for thue


go

create TRIGGER trg_thueSP
ON SanPham
AFTER INSERT, UPDATE
AS
BEGIN
	UPDATE SanPham
    SET thue = 0
    WHERE maSP IN (SELECT maSP FROM inserted);
  DECLARE @ladoTT AS int = (SELECT count(*) FROM inserted
                            INNER JOIN LoaiSP ON inserted.maLoaiSP = LoaiSP.maLoai where isDoTT = 1);

  IF (@ladoTT > 0)
  BEGIN
    UPDATE SanPham
    SET thue = thue + 0.1
    WHERE maSP IN (SELECT maSP FROM inserted);
  END
  ELSE
  BEGIN
    UPDATE SanPham
    SET thue = thue + 0.05
    WHERE maSP IN (SELECT maSP FROM inserted);
  END;

  DECLARE @isEU INT;
  SET @isEU = (
                SELECT count(*) FROM inserted i
                INNER JOIN NhaCC ncc ON i.MaNCC = ncc.maNCC
                INNER JOIN eucountries eu ON eu.code = ncc.maQuocGia
              );
  Declare @isBlazer int = (select count(*) from inserted where maLoaiSP = 'BLZ')
  IF (@isEU > 0 and @isBlazer > 0)
  BEGIN
    UPDATE SanPham
    SET thue = thue + 0.1
    WHERE maSP IN (SELECT maSP FROM inserted);
  END;
  else if(@isEU > 0)
  begin
	UPDATE SanPham
    SET thue = thue + 0.066
    WHERE maSP IN (SELECT maSP FROM inserted);
  end;
END;
go
CREATE INDEX idx_maSP
ON SanPham (maSP);

--delete from SanPham where maSP >= 'SP00000536'

--update SanPham
--set isNam = 0
--where  maSP >= 'SP00000700'

--insert into SanPham(maSP,tenSP,giaNhap,slTonKho,kichThuoc,mauSac,isNam,ConKinhDoanh,maLoaiSP,hinhAnh,MaNCC) 
--select * from cleaned_wm_aom

UPDATE SanPham
SET slTonKho = 100
where maSP = 'SP00000626';

select * from SanPham where maSP = 'SP00000062'
select * from ChiTietHoaDon
select * from HoaDon

delete from PhieuTraHang where maPhieu = 'TH00000006'

select count(*) from SanPham
select distinct maLoaiSP,tenLoai from SanPham 
left join LoaiSP on SanPham.maLoaiSP = LoaiSP.maLoai


--update SanPham
--set hinhAnh = 'https://static.zara.net/photos///2023/I/0/2/p/0495/311/406/2/w/495/0495311406_6_3_1.jpg?ts=1684231099656'
--where maSP = 'SP00000083'

DECLARE @i int = 0

WHILE @i < (select count(*) from SanPham)
BEGIN
    update SanPham
	set thue = 0
	where maSP = concat('SP', (SELECT FORMAT(@i, '00000000')))
    SET @i = @i + 1
END


USE [QLyCHAM]
GO
/****** Object:  Trigger [dbo].[trg_ThemtongHD]    Script Date: 11/19/2023 4:34:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create trigger [dbo].[trg_ThemtongHD]
on [dbo].[ChiTietHoaDon]
after insert
as
begin
	declare @maSP varchar(10) = (select SanPham.maSP from SanPham join inserted on inserted.maSP = SanPham.maSP) 
	declare @productCount int =  (select soLuong from inserted)
	declare @currentProductCount int = (select slTonKho from SanPham where maSP = @maSP)
	if @productCount > @currentProductCount
	begin;
		throw 51000, 'so luong san pham nhap lon hon so luong san pham cho phep',1;
	end;
	update SanPham
	set slTonKho = slTonKho - @productCount
	where maSP = @maSP;
end;


Select * from SanPham inner join NhaCC on SanPham.MaNCC = NhaCC.maNCC inner join LoaiSP on LoaiSP.maLoai = SanPham.maLoaiSP