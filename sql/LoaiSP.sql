/**
 * Khởi tạo danh sách các loại quần áo 
 * @author: Phạm Thế Mạnh
 */

--drop table LoaiSP
use QLyCHAM
go
create table LoaiSP (
	maLoai char(3) primary key,
	tenLoai nvarchar(50),
	isDoTT bit default 0
)	
go
insert into LoaiSP values
('PKI',N'Phụ kiện',0),
('BAL',N'Cặp - Balo',0),
('BLZ',N'BLAZERS',0),
('KOA',N'Áo Khoác',0),
('JEA',N'Quần Jean',0),
('SOR',N'Quần ngắn',0),
('LIN',N'Vải lanh',0),
('HOA',N'Nước hoa',0),
('POL',N'Áo polo',1),
('SMI',N'Áo sơ mi',0),
('SHO',N'Giày',0),
('SUT',N'Áo suit',0),
('SPR',N'Quần áo thể dục',1),
('LEN',N'Áo len',0),
('BOI',N'Đồ bơi',1),
('TSH',N'Áo thun',0),
('KIM',N'Quần áo dệt kim',0),
('VAY',N'Váy',0),
('QTT',N'Quần ngắn thể thao',1),
('AOM',N'Áo ôm sát người',0),
('TUI',N'Túi xách thời trang nữ',0);

select * from LoaiSP

create index LoaiSP_PK_idx on LoaiSP(maLoai)