/*
Author: Phạm Thế Mạnh
*/

create table NhaCC(
	maNCC varchar(10) primary key,
	tenNCC nvarchar(100),
	diachi nvarchar(100),
	maQuocGia varchar(2)
)

insert into NhaCC values 
('NCC0000000',N'An Phước',N'100/11-12 An Dương Vương, P.9, Q.5, TP. Hồ Chí Minh, Việt Nam','VN'),
('NCC0000001',N'ZARA','Arteixo, in A Coruña in Galicia','ES'),
('NCC0000002',N'Mando',N'Số 5, ngõ 18, đường Phạm Hùng, phường Mỹ Đình 2, quận Nam Từ Liêm, thành phố Hà Nội, Việt Nam','VN')

