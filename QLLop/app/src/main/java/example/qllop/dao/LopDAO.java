package example.qllop.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import example.qllop.database.DatabaseHelper;
import example.qllop.models.Lop;

public class LopDAO {
    private DatabaseHelper dbHelper;

    public LopDAO(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    // Add a new class
    public long insertLop(Lop lop) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_LOP_MALOP, lop.getMaLop());
        values.put(DatabaseHelper.KEY_LOP_TENLOP, lop.getTenLop());
        values.put(DatabaseHelper.KEY_LOP_SISO, lop.getSiSo());

        long id = db.insert(DatabaseHelper.TABLE_LOP, null, values);
        db.close();

        return id;
    }

    // Get a class by ID
    public Lop getLopById(String maLop) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_LOP,
                new String[] {
                        DatabaseHelper.KEY_LOP_MALOP,
                        DatabaseHelper.KEY_LOP_TENLOP,
                        DatabaseHelper.KEY_LOP_SISO
                },
                DatabaseHelper.KEY_LOP_MALOP + "=?",
                new String[] { maLop },
                null, null, null, null
        );

        Lop lop = null;
        if (cursor != null && cursor.moveToFirst()) {
            lop = new Lop(
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_LOP_MALOP)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_LOP_TENLOP)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_LOP_SISO))
            );
            cursor.close();
        }

        db.close();
        return lop;
    }

    // Get all classes
    public List<Lop> getAllLop() {
        List<Lop> lopList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_LOP;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Lop lop = new Lop(
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_LOP_MALOP)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_LOP_TENLOP)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.KEY_LOP_SISO))
                );
                lopList.add(lop);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lopList;
    }

    // Update a class
    public int updateLop(Lop lop) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_LOP_TENLOP, lop.getTenLop());
        values.put(DatabaseHelper.KEY_LOP_SISO, lop.getSiSo());

        int rowsAffected = db.update(
                DatabaseHelper.TABLE_LOP,
                values,
                DatabaseHelper.KEY_LOP_MALOP + "=?",
                new String[] { lop.getMaLop() }
        );

        db.close();
        return rowsAffected;
    }

    // Delete a class
    public int deleteLop(String maLop) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int rowsAffected = db.delete(
                DatabaseHelper.TABLE_LOP,
                DatabaseHelper.KEY_LOP_MALOP + "=?",
                new String[] { maLop }
        );

        db.close();
        return rowsAffected;
    }

    // Check if a class exists by ID
    public boolean checkLopExists(String maLop) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + DatabaseHelper.TABLE_LOP +
                " WHERE " + DatabaseHelper.KEY_LOP_MALOP + " = ?";

        Cursor cursor = db.rawQuery(query, new String[] { maLop });
        boolean exists = cursor.getCount() > 0;

        cursor.close();
        db.close();
        return exists;
    }
}