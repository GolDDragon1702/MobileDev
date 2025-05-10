package example.qlnv.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import example.qlnv.database.DatabaseHelper;
import example.qlnv.models.KhachHang;

public class KhachHangDAO {
    private SQLiteDatabase db;

    public KhachHangDAO(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean themKhachHang(KhachHang kh) {
        ContentValues values = new ContentValues();
        values.put("maKH", kh.getMaKH());
        values.put("hoTen", kh.getHoTen());
        values.put("ngaySinh", kh.getNgaySinh());
        values.put("gioiTinh", kh.getGioiTinh());
        values.put("email", kh.getEmail());
        values.put("dienThoai", kh.getDienThoai());

        long result = -1;
        try {
            result = db.insertOrThrow("KhachHang", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result != -1;
    }

    public boolean suaKhachHang(KhachHang kh) {
        ContentValues values = new ContentValues();
        values.put("hoTen", kh.getHoTen());
        values.put("ngaySinh", kh.getNgaySinh());
        values.put("gioiTinh", kh.getGioiTinh());
        values.put("email", kh.getEmail());
        values.put("dienThoai", kh.getDienThoai());

        int rows = db.update("KhachHang", values, "maKH=?", new String[]{kh.getMaKH()});
        return rows > 0;
    }

    public List<KhachHang> getAllKhachHang() {
        List<KhachHang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM KhachHang", null);

        if (cursor.moveToFirst()) {
            do {
                String maKH = cursor.getString(cursor.getColumnIndexOrThrow("maKH"));
                String hoTen = cursor.getString(cursor.getColumnIndexOrThrow("hoTen"));
                String ngaySinh = cursor.getString(cursor.getColumnIndexOrThrow("ngaySinh"));
                String gioiTinh = cursor.getString(cursor.getColumnIndexOrThrow("gioiTinh"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String dienThoai = cursor.getString(cursor.getColumnIndexOrThrow("dienThoai"));

                KhachHang kh = new KhachHang(maKH, hoTen, ngaySinh, gioiTinh, email, dienThoai);
                list.add(kh);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public KhachHang getKhachHangByMa(String maKH) {
        Cursor cursor = db.query("KhachHang", null, "maKH=?", new String[]{maKH}, null, null, null);
        KhachHang kh = null;
        if (cursor.moveToFirst()) {
            String hoTen = cursor.getString(cursor.getColumnIndexOrThrow("hoTen"));
            String ngaySinh = cursor.getString(cursor.getColumnIndexOrThrow("ngaySinh"));
            String gioiTinh = cursor.getString(cursor.getColumnIndexOrThrow("gioiTinh"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            String dienThoai = cursor.getString(cursor.getColumnIndexOrThrow("dienThoai"));

            kh = new KhachHang(maKH, hoTen, ngaySinh, gioiTinh, email, dienThoai);
        }
        cursor.close();
        return kh;
    }

    public boolean xoaKhachHang(String maKH) {
        int rows = db.delete("KhachHang", "maKH=?", new String[]{maKH});
        return rows > 0;
    }

    public List<KhachHang> timKiemKhachHang(String keyword) {
        List<KhachHang> list = new ArrayList<>();
        String query = "SELECT * FROM KhachHang WHERE hoTen LIKE ? OR maKH LIKE ? OR dienThoai LIKE ?";
        String searchPattern = "%" + keyword + "%";
        Cursor cursor = db.rawQuery(query, new String[]{searchPattern, searchPattern, searchPattern});

        if (cursor.moveToFirst()) {
            do {
                String maKH = cursor.getString(cursor.getColumnIndexOrThrow("maKH"));
                String hoTen = cursor.getString(cursor.getColumnIndexOrThrow("hoTen"));
                String ngaySinh = cursor.getString(cursor.getColumnIndexOrThrow("ngaySinh"));
                String gioiTinh = cursor.getString(cursor.getColumnIndexOrThrow("gioiTinh"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String dienThoai = cursor.getString(cursor.getColumnIndexOrThrow("dienThoai"));

                KhachHang kh = new KhachHang(maKH, hoTen, ngaySinh, gioiTinh, email, dienThoai);
                list.add(kh);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}
