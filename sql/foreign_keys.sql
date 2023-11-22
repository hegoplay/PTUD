go

use QLyCHAM


ALTER TABLE SanPham
ADD CONSTRAINT FK_maLoaiSP_LoaiSP
FOREIGN KEY (maLoaiSP) REFERENCES LoaiSP(maLoai);

alter table SanPham
add constraint FK_maNCC_NhaCC
foreign key (maNCC) references NhaCC(maNCC);

go
alter table LichSuTon
add constraint FK_maSP_SanPham
foreign key (maSP) references SanPham(maSP)

go
alter table ChiTietHoaDon
add constraint FK_maSP_CTHD_SanPham
foreign key (maSP) references SanPham(maSP)

go
alter table ChiTietHoaDon
add constraint FK_hoaDon_HoaDon
foreign key (hoaDon) references HoaDon(maHD)

go
alter table HoaDon
add constraint FK_maKH_HoaDon_KhachHang
foreign key (maKH) references KhachHang(maKH)

go
alter table PhieuTraHang
add constraint FK_maKH_PhieuTraHang_KhachHang
foreign key (maKH) references KhachHang(maKH)

go
alter table ChiTietTraHang
add constraint FK_maSp_CTTH_SanPham
foreign key (maSP) references SanPham(maSP)

go
alter table ChiTietTraHang
add constraint FK_maSp_CTTH_PhieuTraHang
foreign key (maPhieuTraHang) references PhieuTraHang(maPhieu)

go
alter table ChiTietTraHang
add constraint FK_maSp_CTTH_PhieuTraHang
foreign key (maPhieuTraHang) references PhieuTraHang(maPhieu)

go
alter table PhieuTraHang
add constraint FK_maNQL_PhieuTraHang_NQL
foreign key (maNQL) references NhanVien(maNV)

go
alter table HoaDon
add constraint FK_maNV_HoaDon_NhanVien
foreign key (maNV) references NhanVien(maNV)

go
alter table NhanVien
add constraint FK_maNQL_NhanVien_NQL
foreign key (maNQL) references NhanVien(maNV)

go
alter table NhanVien
add constraint FK_username_NhanVien_TK
foreign key (userName) references TaiKhoan(userName)