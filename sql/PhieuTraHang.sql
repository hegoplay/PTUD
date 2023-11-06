insert into PhieuTraHang
values('TH00000000','KH0947842274','NV00000000',GETDATE(),'HD00000001')
delete from PhieuTraHang
where maPhieu = 'Th00000002'

select * from PhieuTraHang

drop trigger Trg_PTH_SP
go
create trigger Trg_PTH_SP
on [dbo].[ChiTietTraHang]
after insert 
as
begin
	declare @maSP varchar(10) = (select maSP from inserted)
	declare @soLuong int = (select soLuongNhap from inserted)

	declare @currentSL int = (select slTonKho from SanPham where maSP = @maSP)

	if( @soLuong + @currentSL > 100)
	begin
		RAISERROR('tong so luong khong duoc vuot qua 100',1,5);
	end;
	update SanPham
	set slTonKho = @currentSL + @soLuong
	where maSP = @maSP
end;