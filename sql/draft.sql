--select sum(soLuong) from HoaDon hd
--inner join ChiTietHoaDon ct on hd.maHD = ct.hoaDon
--where (ngayLapHD between GETDATE() - 4 and GETDATE()) and maSP = 'SP00000001'
--group by maSP



--select * from SanPham where maSP in (
--	select distinct maSP from HoaDon hd
--	inner join ChiTietHoaDon ct on hd.maHD = ct.hoaDon
--	where (ngayLapHD between GETDATE() - 4 and GETDATE()) order
--)


--insert into LichSuTon
--values ('SP00000012211202314','SP00000012',23,GETDATE())
--select sum(soLuongNhap) from LichSuTon
--where ngayThayDoi between GETDATE() - 1 and GETDATE() 
--group by maSP

delete from HoaDon where maHD = 'HD00000002'

insert into HoaDon values
('HD00000002',GETDATE(),'NV00000003','KH0944894321',0,5000000,0)

insert into ChiTietHoaDon values 
('SP00000425','HD00000002',2),
('SP00000142','HD00000002',1),
('SP00000146','HD00000002',2)