package example.qlnv.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import example.qlnv.database.DatabaseHelper;
import example.qlnv.models.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    private SQLiteDatabase db;

    public NhanVienDAO(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean themNhanVien(NhanVien nv) {
        ContentValues values = new ContentValues();
        values.put("maNV", nv.getMaNV());
        values.put("hoTen", nv.getHoTen());
        values.put("ngaySinh", nv.getNgaySinh());
        values.put("gioiTinh", nv.getGioiTinh());
        values.put("diaChi", nv.getDiaChi());
        values.put("dienThoai", nv.getDienThoai());
        values.put("ghiChu", nv.getGhiChu());

        long result = -1;
        try {
            result = db.insertOrThrow("NhanVien", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
        return result != -1;
    }

    public boolean suaNhanVien(NhanVien nv) {
        ContentValues values = new ContentValues();
        values.put("hoTen", nv.getHoTen());
        values.put("ngaySinh", nv.getNgaySinh());
        values.put("gioiTinh", nv.getGioiTinh());
        values.put("diaChi", nv.getDiaChi());
        values.put("dienThoai", nv.getDienThoai());
        values.put("ghiChu", nv.getGhiChu());

        int rows = db.update("NhanVien", values, "maNV=?", new String[]{nv.getMaNV()});
        db.close();
        return rows > 0;
    }

    public boolean xoaNhanVien(String maNV) {
        int rows = db.delete("NhanVien", "maNV=?", new String[]{maNV});
        db.close();
        return rows > 0;
    }

    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM NhanVien", null);
        if (cursor.moveToFirst()) {
            do {
                NhanVien nv = new NhanVien(
                        cursor.getString(cursor.getColumnIndexOrThrow("maNV")),
                        cursor.getString(cursor.getColumnIndexOrThrow("hoTen")),
                        cursor.getString(cursor.getColumnIndexOrThrow("ngaySinh")),
                        cursor.getString(cursor.getColumnIndexOrThrow("gioiTinh")),
                        cursor.getString(cursor.getColumnIndexOrThrow("diaChi")),
                        cursor.getString(cursor.getColumnIndexOrThrow("dienThoai")),
                        cursor.getString(cursor.getColumnIndexOrThrow("ghiChu"))
                );
                list.add(nv);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
}
