use QLyCHAM

select * from ChiTietTraHang
select * from PhieuTraHang
delete from PhieuTraHang where maPhieu = 'TH00000001'

delete from HoaDon

insert into HoaDon
values('HD00000001',GETDATE(),'NV00000000','KH0947842274',0,0,0)


insert into ChiTietHoaDon
values
	('SP00000143','HD00000002',3)

insert into ChiTietHoaDon
values('SP00000000','HD00000001',12);
