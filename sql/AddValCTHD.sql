use QLyCHAM

select * from ChiTietTraHang
select * from PhieuTraHang
delete from PhieuTraHang where maPhieu = 'TH00000001'

delete from PhieuTraHang
where maHD = 'HD00000002'
delete from HoaDon
where maHD = 'HD00000002'



insert into HoaDon
values('HD00000002',GETDATE(),'NV00000003','KH0944894321',0,0,0)


insert into ChiTietHoaDon
values
	('SP00000142','HD00000002',3)

insert into ChiTietHoaDon
values('SP00000143','HD00000002',1);

insert into ChiTietHoaDon
values('SP00000143','HD00000002',2);

create procedure getTongDTNV (@maNV varchar(10), @tongDT money out)
as
begin
	
end;
