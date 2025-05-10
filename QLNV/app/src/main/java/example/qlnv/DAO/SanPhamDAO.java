package example.qlnv.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import example.qlnv.database.DatabaseHelper;
import example.qlnv.models.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    private SQLiteDatabase db;

    public SanPhamDAO(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // Thêm sản phẩm
    public boolean themSanPham(SanPham sp) {
        ContentValues values = new ContentValues();
        values.put("maSP", sp.getMaSP());
        values.put("tenSP", sp.getTenSP());
        values.put("ngaySX", sp.getNgaySanXuat());
        values.put("giaBan", sp.getGiaBan());
        values.put("soLuong", sp.getSoLuong());

        long result = -1;
        try {
            result = db.insertOrThrow("SanPham", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result != -1;
    }

    // Sửa sản phẩm
    public boolean suaSanPham(SanPham sp) {
        ContentValues values = new ContentValues();
        values.put("tenSP", sp.getTenSP());
        values.put("ngaySX", sp.getNgaySanXuat());
        values.put("giaBan", sp.getGiaBan());
        values.put("soLuong", sp.getSoLuong());

        int rows = db.update("SanPham", values, "maSP=?", new String[]{sp.getMaSP()});
        return rows > 0;
    }

    // Xóa sản phẩm
    public boolean xoaSanPham(String maSP) {
        int rows = db.delete("SanPham", "maSP=?", new String[]{maSP});
        return rows > 0;
    }

    // Lấy tất cả sản phẩm
    public List<SanPham> getAllSanPham() {
        List<SanPham> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM SanPham", null);

        if (cursor.moveToFirst()) {
            do {
                String maSP = cursor.getString(cursor.getColumnIndexOrThrow("maSP"));
                String tenSP = cursor.getString(cursor.getColumnIndexOrThrow("tenSP"));
                String ngaySX = cursor.getString(cursor.getColumnIndexOrThrow("ngaySX"));
                double giaBan = cursor.getDouble(cursor.getColumnIndexOrThrow("giaBan"));
                int soLuong = cursor.getInt(cursor.getColumnIndexOrThrow("soLuong"));

                SanPham sp = new SanPham(maSP, tenSP, ngaySX, giaBan, soLuong);
                list.add(sp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    // Lấy sản phẩm theo mã
    public SanPham getSanPhamByMa(String maSP) {
        Cursor cursor = db.query("SanPham", null, "maSP=?", new String[]{maSP}, null, null, null);
        SanPham sp = null;
        if (cursor.moveToFirst()) {
            String tenSP = cursor.getString(cursor.getColumnIndexOrThrow("tenSP"));
            String ngaySX = cursor.getString(cursor.getColumnIndexOrThrow("ngaySX"));
            double giaBan = cursor.getDouble(cursor.getColumnIndexOrThrow("giaBan"));
            int soLuong = cursor.getInt(cursor.getColumnIndexOrThrow("soLuong"));

            sp = new SanPham(maSP, tenSP, ngaySX, giaBan, soLuong);
        }
        cursor.close();
        return sp;
    }

    // Cập nhật số lượng sản phẩm
    public boolean capNhatSoLuong(String maSP, int soLuong) {
        ContentValues values = new ContentValues();
        values.put("soLuong", soLuong);
        int rows = db.update("SanPham", values, "maSP=?", new String[]{maSP});
        return rows > 0;
    }
}
