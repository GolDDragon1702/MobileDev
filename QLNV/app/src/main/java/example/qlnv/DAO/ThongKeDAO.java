package example.qlnv.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import example.qlnv.database.DatabaseHelper;

public class ThongKeDAO {
    private SQLiteDatabase db;

    public ThongKeDAO(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getReadableDatabase();
    }

    public List<String> getSanPhamBanChayTrongThang() {
        List<String> list = new ArrayList<>();

        // Lấy tháng và năm hiện tại theo định dạng yyyy-MM
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String currentMonth = sdf.format(calendar.getTime());

        // Câu truy vấn tổng hợp số lượng bán theo sản phẩm trong tháng hiện tại
        String query = "SELECT sp.tenSP, SUM(cthd.soLuong) AS totalSold " +
                "FROM ChiTietHoaDon cthd " +
                "JOIN HoaDon hd ON cthd.maHD = hd.maHD " +
                "JOIN SanPham sp ON cthd.maSP = sp.maSP " +
                "WHERE hd.ngayBan LIKE ? " +
                "GROUP BY sp.maSP " +
                "ORDER BY totalSold DESC";

        Cursor cursor = db.rawQuery(query, new String[]{currentMonth + "%"});

        if (cursor.moveToFirst()) {
            do {
                String tenSP = cursor.getString(cursor.getColumnIndexOrThrow("tenSP"));
                int totalSold = cursor.getInt(cursor.getColumnIndexOrThrow("totalSold"));
                list.add(tenSP + " - Đã bán: " + totalSold);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public double getDoanhThu(String tuNgay, String denNgay) {
        double tongDoanhThu = 0;
        String query = "SELECT SUM(tongTien) as Tong FROM HoaDon WHERE ngayBan BETWEEN ? AND ?";
        Cursor cursor = db.rawQuery(query, new String[]{tuNgay, denNgay});

        if (cursor.moveToFirst()) {
            tongDoanhThu = cursor.getDouble(cursor.getColumnIndexOrThrow("Tong"));
        }
        cursor.close();
        return tongDoanhThu;
    }

    @SuppressLint("DefaultLocale")
    public List<String> getChiTietDoanhThu(String tuNgay, String denNgay) {
        List<String> list = new ArrayList<>();

        String query = "SELECT maHD, ngayBan, tongTien FROM HoaDon WHERE ngayBan BETWEEN ? AND ? ORDER BY ngayBan ASC";
        Cursor cursor = db.rawQuery(query, new String[]{tuNgay, denNgay});

        if (cursor.moveToFirst()) {
            do {
                String maHD = cursor.getString(cursor.getColumnIndexOrThrow("maHD"));
                String ngayBan = cursor.getString(cursor.getColumnIndexOrThrow("ngayBan"));
                double tongTien = cursor.getDouble(cursor.getColumnIndexOrThrow("tongTien"));
                list.add(String.format("HĐ: %s - Ngày: %s - Tổng tiền: %.2f", maHD, ngayBan, tongTien));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
} 