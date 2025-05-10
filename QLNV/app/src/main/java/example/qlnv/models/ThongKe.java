package example.qlnv.models;

public class ThongKe {
    private String maSP;
    private int soLuong;
    private double giaBan;
    private double tongTien;

    public ThongKe(String maSP, int soLuong, double giaBan, double tongTien) {
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.tongTien = tongTien;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
} 