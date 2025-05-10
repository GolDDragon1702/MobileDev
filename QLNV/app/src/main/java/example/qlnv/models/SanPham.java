package example.qlnv.models;

public class SanPham {
    private String maSP;
    private String tenSP;
    private String ngaySX;
    private double giaBan;
    private int soLuong;

    public SanPham(String maSP, String tenSP, String ngaySX, double giaBan, int soLuong) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.ngaySX = ngaySX;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
    }

    // Getter và Setter
    public String getMaSP() { return maSP; }
    public void setMaSP(String maSP) { this.maSP = maSP; }

    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }

    public String getNgaySanXuat() { return ngaySX; }
    public void setNgaySanXuat(String ngaySanXuat) { this.ngaySX = ngaySanXuat; }

    public double getGiaBan() { return giaBan; }
    public void setGiaBan(double giaBan) { this.giaBan = giaBan; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
}
