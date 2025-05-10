package example.qlnv.models;

public class ChiTietHoaDon {
    private String maHD;
    private String maSP;
    private double giaBan;
    private int soLuong;
    private double tongTien;

    public ChiTietHoaDon(String maHD, String maSP, double giaBan, int soLuong, double tongTien) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    // Getter và Setter
    public String getMaHD() { return maHD; }
    public void setMaHD(String maHD) { this.maHD = maHD; }

    public String getMaSP() { return maSP; }
    public void setMaSP(String maSP) { this.maSP = maSP; }

    public double getGiaBan() { return giaBan; }
    public void setGiaBan(double giaBan) { this.giaBan = giaBan; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }
}
