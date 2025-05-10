package example.qllop.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Info
    private static final String DATABASE_NAME = "QuanLySinhVienDB";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    public static final String TABLE_LOP = "tblLop";
    public static final String TABLE_SINHVIEN = "tblSinhvien";

    // Lop Table Columns
    public static final String KEY_LOP_MALOP = "malop";
    public static final String KEY_LOP_TENLOP = "tenlop";
    public static final String KEY_LOP_SISO = "siso";

    // SinhVien Table Columns
    public static final String KEY_SV_MSSV = "mssv";
    public static final String KEY_SV_HOTEN = "hoten";
    public static final String KEY_SV_NGAYSINH = "ngaysinh";
    public static final String KEY_SV_DIACHI = "diachi";
    public static final String KEY_SV_DIENTHOAI = "dienthoai";
    public static final String KEY_SV_MALOP = "malop";

    private static DatabaseHelper sInstance;

    // Singleton pattern to get an instance of DatabaseHelper
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOP_TABLE = "CREATE TABLE " + TABLE_LOP +
                "(" +
                KEY_LOP_MALOP + " TEXT PRIMARY KEY," +
                KEY_LOP_TENLOP + " TEXT," +
                KEY_LOP_SISO + " INTEGER" +
                ")";

        String CREATE_SINHVIEN_TABLE = "CREATE TABLE " + TABLE_SINHVIEN +
                "(" +
                KEY_SV_MSSV + " TEXT PRIMARY KEY," +
                KEY_SV_HOTEN + " TEXT," +
                KEY_SV_NGAYSINH + " DATE," +
                KEY_SV_DIACHI + " TEXT," +
                KEY_SV_DIENTHOAI + " TEXT," +
                KEY_SV_MALOP + " TEXT," +
                "FOREIGN KEY (" + KEY_SV_MALOP + ") REFERENCES " + TABLE_LOP + "(" + KEY_LOP_MALOP + ")" +
                ")";

        db.execSQL(CREATE_LOP_TABLE);
        db.execSQL(CREATE_SINHVIEN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Drop old tables
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SINHVIEN);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOP);
            // Recreate tables
            onCreate(db);
        }
    }

    // Method to reset database (drop and recreate all tables)
    public void resetDatabase() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SINHVIEN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOP);
        onCreate(db);
    }
}