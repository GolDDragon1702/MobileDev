package example.qlsv;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "SinhVienDB";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE SinhVien (id INTEGER PRIMARY KEY AUTOINCREMENT, ten TEXT, lop TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS SinhVien");
        onCreate(db);
    }

    public void insertSV(String ten, String lop) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", ten);
        values.put("lop", lop);
        db.insert("SinhVien", null, values);
    }

    public void updateSV(int id, String ten, String lop) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", ten);
        values.put("lop", lop);
        db.update("SinhVien", values, "id = ?", new String[]{String.valueOf(id)});
    }

    public void deleteSV(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("SinhVien", "id = ?", new String[]{String.valueOf(id)});
    }

    public List<SinhVien> getAll() {
        List<SinhVien> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM SinhVien", null);
        while (c.moveToNext()) {
            list.add(new SinhVien(c.getInt(0), c.getString(1), c.getString(2)));
        }
        return list;
    }
}
