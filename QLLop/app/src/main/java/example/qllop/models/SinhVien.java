package example.qllop.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SinhVien {
    private String mssv;
    private String hoTen;
    private Date ngaySinh;
    private String diaChi;
    private String dienThoai;
    private String maLop;

    // Date format for string conversion
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    public SinhVien() {
    }

    public SinhVien(String mssv, String hoTen, Date ngaySinh, String diaChi, String dienThoai, String maLop) {
        this.mssv = mssv;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.maLop = maLop;
    }

    // Constructor with string date format
    public SinhVien(String mssv, String hoTen, String ngaySinhStr, String diaChi, String dienThoai, String maLop) throws ParseException {
        this.mssv = mssv;
        this.hoTen = hoTen;
        this.ngaySinh = DATE_FORMAT.parse(ngaySinhStr);
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.maLop = maLop;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    // Method to set date from string format
    public void setNgaySinh(String ngaySinhStr) throws ParseException {
        this.ngaySinh = DATE_FORMAT.parse(ngaySinhStr);
    }

    // Get formatted date string
    public String getNgaySinhString() {
        return ngaySinh != null ? DATE_FORMAT.format(ngaySinh) : "";
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    @Override
    public String toString() {
        return "MSSV: " + mssv + " - Họ tên: " + hoTen + " - Ngày sinh: " + getNgaySinhString() + " - Lớp: " + maLop;
    }
}