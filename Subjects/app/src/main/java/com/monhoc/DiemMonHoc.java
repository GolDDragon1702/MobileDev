package com.monhoc;

public class DiemMonHoc {
    private String ngayCapNhat;
    private String heSo;
    private String diem;
    private String monHoc;
    private String sinhVien;

    public DiemMonHoc(String ngayCapNhat, String heSo, String diem, String monHoc, String sinhVien) {
        this.ngayCapNhat = ngayCapNhat;
        this.heSo = heSo;
        this.diem = diem;
        this.monHoc = monHoc;
        this.sinhVien = sinhVien;
    }

    public String getNgayCapNhat() {
        return ngayCapNhat;
    }
    public String getHeSo() {
        return heSo;
    }
    public String getDiem() {
        return diem;
    }
    public String getMonHoc() {
        return monHoc;
    }
    public String getSinhVien() {
        return sinhVien;
    }

    public void setNgayCapNhat(String ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }
    public void setHeSo(String heSo) {
        this.heSo = heSo;
    }
    public void setDiem(String diem) {
        this.diem = diem;
    }
    public void setMonHoc(String monHoc) {
        this.monHoc = monHoc;
    }
    public void setSinhVien(String sinhVien) {
        this.sinhVien = sinhVien;
    }
}
