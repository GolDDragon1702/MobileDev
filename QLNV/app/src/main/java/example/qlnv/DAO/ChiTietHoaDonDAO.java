package example.qlnv.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import example.qlnv.database.DatabaseHelper;
import example.qlnv.models.ChiTietHoaDon;

public class ChiTietHoaDonDAO {
    private SQLiteDatabase db;

    public ChiTietHoaDonDAO(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean themChiTietHoaDon(ChiTietHoaDon cthd) {
        ContentValues values = new ContentValues();
        values.put("maHD", cthd.getMaHD());
        values.put("maSP", cthd.getMaSP());
        values.put("giaBan", cthd.getGiaBan());
        values.put("soLuong", cthd.getSoLuong());
        values.put("tongTien", cthd.getTongTien());

        long result = -1;
        try {
            result = db.insertOrThrow("ChiTietHoaDon", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result != -1;
    }

    public boolean suaChiTietHoaDon(ChiTietHoaDon cthd) {
        ContentValues values = new ContentValues();
        values.put("giaBan", cthd.getGiaBan());
        values.put("soLuong", cthd.getSoLuong());
        values.put("tongTien", cthd.getTongTien());

        int rows = db.update("ChiTietHoaDon", values, "maHD=? AND maSP=?", 
            new String[]{cthd.getMaHD(), cthd.getMaSP()});
        return rows > 0;
    }

    public List<ChiTietHoaDon> getAllChiTietHoaDon() {
        List<ChiTietHoaDon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM ChiTietHoaDon", null);

        if (cursor.moveToFirst()) {
            do {
                String maHD = cursor.getString(cursor.getColumnIndexOrThrow("maHD"));
                String maSP = cursor.getString(cursor.getColumnIndexOrThrow("maSP"));
                double giaBan = cursor.getDouble(cursor.getColumnIndexOrThrow("giaBan"));
                int soLuong = cursor.getInt(cursor.getColumnIndexOrThrow("soLuong"));
                double tongTien = cursor.getDouble(cursor.getColumnIndexOrThrow("tongTien"));

                ChiTietHoaDon cthd = new ChiTietHoaDon(maHD, maSP, giaBan, soLuong, tongTien);
                list.add(cthd);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public List<ChiTietHoaDon> getChiTietHoaDonByMaHD(String maHD) {
        List<ChiTietHoaDon> list = new ArrayList<>();
        Cursor cursor = db.query("ChiTietHoaDon", null, "maHD=?", 
            new String[]{maHD}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String maSP = cursor.getString(cursor.getColumnIndexOrThrow("maSP"));
                double giaBan = cursor.getDouble(cursor.getColumnIndexOrThrow("giaBan"));
                int soLuong = cursor.getInt(cursor.getColumnIndexOrThrow("soLuong"));
                double tongTien = cursor.getDouble(cursor.getColumnIndexOrThrow("tongTien"));

                ChiTietHoaDon cthd = new ChiTietHoaDon(maHD, maSP, giaBan, soLuong, tongTien);
                list.add(cthd);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public boolean xoaChiTietHoaDon(String maHD, String maSP) {
        int rows = db.delete("ChiTietHoaDon", "maHD=? AND maSP=?", 
            new String[]{maHD, maSP});
        return rows > 0;
    }
} 