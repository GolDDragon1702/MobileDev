package example.qlnv.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import example.qlnv.database.DatabaseHelper;
import example.qlnv.models.HoaDon;

public class HoaDonDAO {
    private SQLiteDatabase db;

    public HoaDonDAO(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean themHoaDon(HoaDon hd) {
        ContentValues values = new ContentValues();
        values.put("maHD", hd.getMaHD());
        values.put("ngayBan", hd.getNgayBan());
        values.put("maNV", hd.getMaNV());
        values.put("maKH", hd.getMaKH());
        values.put("tongTien", hd.getTongTien());

        long result = -1;
        try {
            result = db.insertOrThrow("HoaDon", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result != -1;
    }

    public boolean suaHoaDon(HoaDon hd) {
        ContentValues values = new ContentValues();
        values.put("ngayBan", hd.getNgayBan());
        values.put("maNV", hd.getMaNV());
        values.put("maKH", hd.getMaKH());
        values.put("tongTien", hd.getTongTien());

        int rows = db.update("HoaDon", values, "maHD=?", new String[]{hd.getMaHD()});
        return rows > 0;
    }

    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM HoaDon", null);

        if (cursor.moveToFirst()) {
            do {
                String maHD = cursor.getString(cursor.getColumnIndexOrThrow("maHD"));
                String ngayBan = cursor.getString(cursor.getColumnIndexOrThrow("ngayBan"));
                String maNV = cursor.getString(cursor.getColumnIndexOrThrow("maNV"));
                String maKH = cursor.getString(cursor.getColumnIndexOrThrow("maKH"));
                double tongTien = cursor.getDouble(cursor.getColumnIndexOrThrow("tongTien"));

                HoaDon hd = new HoaDon(maHD, ngayBan, maNV, maKH, tongTien);
                list.add(hd);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public HoaDon getHoaDonByMa(String maHD) {
        Cursor cursor = db.query("HoaDon", null, "maHD=?", new String[]{maHD}, null, null, null);
        HoaDon hd = null;
        if (cursor.moveToFirst()) {
            String ngayBan = cursor.getString(cursor.getColumnIndexOrThrow("ngayBan"));
            String maNV = cursor.getString(cursor.getColumnIndexOrThrow("maNV"));
            String maKH = cursor.getString(cursor.getColumnIndexOrThrow("maKH"));
            double tongTien = cursor.getDouble(cursor.getColumnIndexOrThrow("tongTien"));

            hd = new HoaDon(maHD, ngayBan, maNV, maKH, tongTien);
        }
        cursor.close();
        return hd;
    }

    public boolean xoaHoaDon(String maHD) {
        int rows = db.delete("HoaDon", "maHD=?", new String[]{maHD});
        return rows > 0;
    }
}
