package example.qlnv.models;

public class HoaDon {
    private String maHD;
    private String ngayBan;
    private String maNV;
    private String maKH;
    private double tongTien;

    public HoaDon(String maHD, String ngayBan, String maNV, String maKH, double tongTien) {
        this.maHD = maHD;
        this.ngayBan = ngayBan;
        this.maNV = maNV;
        this.maKH = maKH;
        this.tongTien = tongTien;
    }

    // Getter và Setter
    public String getMaHD() { return maHD; }
    public void setMaHD(String maHD) { this.maHD = maHD; }

    public String getNgayBan() { return ngayBan; }
    public void setNgayBan(String ngayBan) { this.ngayBan = ngayBan; }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public String getMaKH() { return maKH; }
    public void setMaKH(String maKH) { this.maKH = maKH; }

    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }
}
