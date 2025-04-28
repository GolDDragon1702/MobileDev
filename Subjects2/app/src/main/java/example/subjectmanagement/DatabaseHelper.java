package example.subjectmanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MonHocDB.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "MonHoc";
    public static final String COL_MAMH = "maMH";
    public static final String COL_TENMH = "tenMH";
    public static final String COL_SOTIET = "soTiet";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_MAMH + " INTEGER PRIMARY KEY, " +
                COL_TENMH + " TEXT, " +
                COL_SOTIET + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
