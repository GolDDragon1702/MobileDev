package example.qlnv.database;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

import example.qlnv.models.ThongKe;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "SalesDB.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Table NhanVien
        db.execSQL("CREATE TABLE NhanVien (" +
                "maNV TEXT PRIMARY KEY, " +
                "hoTen TEXT NOT NULL, " +
                "ngaySinh TEXT, " +
                "gioiTinh TEXT, " +
                "diaChi TEXT, " +
                "dienThoai TEXT, " +
                "ghiChu TEXT)");

        // Table KhachHang
        db.execSQL("CREATE TABLE KhachHang (" +
                "maKH TEXT PRIMARY KEY, " +
                "hoTen TEXT NOT NULL, " +
                "ngaySinh TEXT, " +
                "gioiTinh TEXT, " +
                "email TEXT, " +
                "dienThoai TEXT)");

        // Table SanPham
        db.execSQL("CREATE TABLE SanPham (" +
                "maSP TEXT PRIMARY KEY, " +
                "tenSP TEXT NOT NULL, " +
                "ngaySX TEXT, " +
                "giaBan REAL, " +
                "soLuong INTEGER)");

        // Table HoaDon
        db.execSQL("CREATE TABLE HoaDon (" +
                "maHD TEXT PRIMARY KEY, " +
                "ngayBan TEXT, " +
                "maNV TEXT, " +
                "maKH TEXT, " +
                "tongTien REAL, " +
                "FOREIGN KEY(maNV) REFERENCES NhanVien(maNV), " +
                "FOREIGN KEY(maKH) REFERENCES KhachHang(maKH))");

        // Table ChiTietHoaDon
        db.execSQL("CREATE TABLE ChiTietHoaDon (" +
                "maHD TEXT, " +
                "maSP TEXT, " +
                "giaBan REAL, " +
                "soLuong INTEGER, " +
                "tongTien REAL, " +
                "PRIMARY KEY(maHD, maSP), " +
                "FOREIGN KEY(maHD) REFERENCES HoaDon(maHD), " +
                "FOREIGN KEY(maSP) REFERENCES SanPham(maSP))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ChiTietHoaDon");
        db.execSQL("DROP TABLE IF EXISTS HoaDon");
        db.execSQL("DROP TABLE IF EXISTS SanPham");
        db.execSQL("DROP TABLE IF EXISTS KhachHang");
        db.execSQL("DROP TABLE IF EXISTS NhanVien");
        onCreate(db);
    }

    public double getDoanhThu(String tuNgay, String denNgay) {
        double tongDoanhThu = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT SUM(tongTien) FROM HoaDon WHERE ngayBan BETWEEN ? AND ?";
        Cursor cursor = db.rawQuery(query, new String[]{tuNgay, denNgay});
        if (cursor.moveToFirst()) {
            tongDoanhThu = cursor.getDouble(0);
        }
        cursor.close();
        return tongDoanhThu;
    }

    public int getSoHoaDon(String tuNgay, String denNgay) {
        int soHoaDon = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM HoaDon WHERE ngayBan BETWEEN ? AND ?";
        Cursor cursor = db.rawQuery(query, new String[]{tuNgay, denNgay});
        if (cursor.moveToFirst()) {
            soHoaDon = cursor.getInt(0);
        }
        cursor.close();
        return soHoaDon;
    }

    public List<ThongKe> getThongKeTheoNgay(String tuNgay, String denNgay) {
        List<ThongKe> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT cthd.maSP, SUM(cthd.soLuong) as tongSoLuong, " +
                      "cthd.giaBan, SUM(cthd.tongTien) as tongTien " +
                      "FROM ChiTietHoaDon cthd " +
                      "JOIN HoaDon hd ON cthd.maHD = hd.maHD " +
                      "WHERE hd.ngayBan BETWEEN ? AND ? " +
                      "GROUP BY cthd.maSP";
        Cursor cursor = db.rawQuery(query, new String[]{tuNgay, denNgay});
        while (cursor.moveToNext()) {
            String maSP = cursor.getString(0);
            int soLuong = cursor.getInt(1);
            double giaBan = cursor.getDouble(2);
            double tongTien = cursor.getDouble(3);
            list.add(new ThongKe(maSP, soLuong, giaBan, tongTien));
        }
        cursor.close();
        return list;
    }

    public List<ThongKe> getSanPhamBanChay(String tuNgay, String denNgay) {
        List<ThongKe> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT cthd.maSP, sp.tenSP, SUM(cthd.soLuong) as tongSoLuong, " +
                      "cthd.giaBan, SUM(cthd.tongTien) as tongTien " +
                      "FROM ChiTietHoaDon cthd " +
                      "JOIN HoaDon hd ON cthd.maHD = hd.maHD " +
                      "JOIN SanPham sp ON cthd.maSP = sp.maSP " +
                      "WHERE hd.ngayBan BETWEEN ? AND ? " +
                      "GROUP BY cthd.maSP " +
                      "ORDER BY tongSoLuong DESC";
        Cursor cursor = db.rawQuery(query, new String[]{tuNgay, denNgay});
        while (cursor.moveToNext()) {
            String maSP = cursor.getString(0);
            String tenSP = cursor.getString(1);
            int soLuong = cursor.getInt(2);
            double giaBan = cursor.getDouble(3);
            double tongTien = cursor.getDouble(4);
            ThongKe tk = new ThongKe(maSP, soLuong, giaBan, tongTien);
            tk.setMaSP(maSP);
            list.add(tk);
        }
        cursor.close();
        return list;
    }
}

