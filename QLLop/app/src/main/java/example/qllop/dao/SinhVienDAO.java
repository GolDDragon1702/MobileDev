package example.qllop.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import example.qllop.database.DatabaseHelper;
import example.qllop.models.SinhVien;

public class SinhVienDAO {
    private DatabaseHelper dbHelper;
    private SimpleDateFormat dateFormat;

    public SinhVienDAO(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    }

    // Add a new student
    public long insertSinhVien(SinhVien sinhVien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_SV_MSSV, sinhVien.getMssv());
        values.put(DatabaseHelper.KEY_SV_HOTEN, sinhVien.getHoTen());
        values.put(DatabaseHelper.KEY_SV_NGAYSINH, dateFormat.format(sinhVien.getNgaySinh()));
        values.put(DatabaseHelper.KEY_SV_DIACHI, sinhVien.getDiaChi());
        values.put(DatabaseHelper.KEY_SV_DIENTHOAI, sinhVien.getDienThoai());
        values.put(DatabaseHelper.KEY_SV_MALOP, sinhVien.getMaLop());

        long id = db.insert(DatabaseHelper.TABLE_SINHVIEN, null, values);
        db.close();

        return id;
    }

    // Get a student by ID
    public SinhVien getSinhVienById(String mssv) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_SINHVIEN,
                new String[] {
                        DatabaseHelper.KEY_SV_MSSV,
                        DatabaseHelper.KEY_SV_HOTEN,
                        DatabaseHelper.KEY_SV_NGAYSINH,
                        DatabaseHelper.KEY_SV_DIACHI,
                        DatabaseHelper.KEY_SV_DIENTHOAI,
                        DatabaseHelper.KEY_SV_MALOP
                },
                DatabaseHelper.KEY_SV_MSSV + "=?",
                new String[] { mssv },
                null, null, null, null
        );

        SinhVien sinhVien = null;
        if (cursor != null && cursor.moveToFirst()) {
            try {
                sinhVien = new SinhVien();
                sinhVien.setMssv(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_SV_MSSV)));
                sinhVien.setHoTen(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_SV_HOTEN)));
                String ngaySinhStr = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_SV_NGAYSINH));
                sinhVien.setNgaySinh(dateFormat.parse(ngaySinhStr));
                sinhVien.setDiaChi(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_SV_DIACHI)));
                sinhVien.setDienThoai(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_SV_DIENTHOAI)));
                sinhVien.setMaLop(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_SV_MALOP)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cursor.close();
        }

        db.close();
        return sinhVien;
    }

    // Get all students
    public List<SinhVien> getAllSinhVien() {
        List<SinhVien> sinhVienList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_SINHVIEN;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                try {
                    SinhVien sinhVien = new SinhVien();
                    sinhVien.setMssv(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_SV_MSSV)));
                    sinhVien.setHoTen(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_SV_HOTEN)));
                    String ngaySinhStr = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_SV_NGAYSINH));
                    sinhVien.setNgaySinh(dateFormat.parse(ngaySinhStr));
                    sinhVien.setDiaChi(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_SV_DIACHI)));
                    sinhVien.setDienThoai(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_SV_DIENTHOAI)));
                    sinhVien.setMaLop(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_SV_MALOP)));

                    sinhVienList.add(sinhVien);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return sinhVienList;
    }

    // Get students by class ID
    public List<SinhVien> getSinhVienByLop(String maLop) {
        List<SinhVien> sinhVienList = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_SINHVIEN,
                new String[] {
                        DatabaseHelper.KEY_SV_MSSV,
                        DatabaseHelper.KEY_SV_HOTEN,
                        DatabaseHelper.KEY_SV_NGAYSINH,
                        DatabaseHelper.KEY_SV_DIACHI,
                        DatabaseHelper.KEY_SV_DIENTHOAI,
                        DatabaseHelper.KEY_SV_MALOP
                },
                DatabaseHelper.KEY_SV_MALOP + "=?",
                new String[] { maLop },
                null, null, null, null
        );

        if (cursor.moveToFirst()) {
            do {
                try {
                    SinhVien sinhVien = new SinhVien();
                    sinhVien.setMssv(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_SV_MSSV)));
                    sinhVien.setHoTen(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_SV_HOTEN)));
                    String ngaySinhStr = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_SV_NGAYSINH));
                    sinhVien.setNgaySinh(dateFormat.parse(ngaySinhStr));
                    sinhVien.setDiaChi(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_SV_DIACHI)));
                    sinhVien.setDienThoai(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_SV_DIENTHOAI)));
                    sinhVien.setMaLop(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_SV_MALOP)));

                    sinhVienList.add(sinhVien);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return sinhVienList;
    }

    // Update a student
    public int updateSinhVien(SinhVien sinhVien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_SV_HOTEN, sinhVien.getHoTen());
        values.put(DatabaseHelper.KEY_SV_NGAYSINH, dateFormat.format(sinhVien.getNgaySinh()));
        values.put(DatabaseHelper.KEY_SV_DIACHI, sinhVien.getDiaChi());
        values.put(DatabaseHelper.KEY_SV_DIENTHOAI, sinhVien.getDienThoai());
        values.put(DatabaseHelper.KEY_SV_MALOP, sinhVien.getMaLop());

        int rowsAffected = db.update(
                DatabaseHelper.TABLE_SINHVIEN,
                values,
                DatabaseHelper.KEY_SV_MSSV + "=?",
                new String[] { sinhVien.getMssv() }
        );

        db.close();
        return rowsAffected;
    }

    // Delete a student
    public int deleteSinhVien(String mssv) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int rowsAffected = db.delete(
                DatabaseHelper.TABLE_SINHVIEN,
                DatabaseHelper.KEY_SV_MSSV + "=?",
                new String[] { mssv }
        );

        db.close();
        return rowsAffected;
    }

    // Delete all students in a class
    public int deleteSinhVienByLop(String maLop) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int rowsAffected = db.delete(
                DatabaseHelper.TABLE_SINHVIEN,
                DatabaseHelper.KEY_SV_MALOP + "=?",
                new String[] { maLop }
        );

        db.close();
        return rowsAffected;
    }

    // Count students in a class
    public int countSinhVienInLop(String maLop) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String countQuery = "SELECT COUNT(*) FROM " + DatabaseHelper.TABLE_SINHVIEN +
                " WHERE " + DatabaseHelper.KEY_SV_MALOP + " = ?";

        Cursor cursor = db.rawQuery(countQuery, new String[] { maLop });
        cursor.moveToFirst();
        int count = cursor.getInt(0);

        cursor.close();
        db.close();
        return count;
    }
}