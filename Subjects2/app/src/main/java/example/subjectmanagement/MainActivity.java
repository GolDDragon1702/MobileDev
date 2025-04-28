package example.subjectmanagement;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtSubjectId, edtSubjectName, edtLessonCount;
    private Button btnInsert, btnLoad, btnUpdate, btnDelete;
    private ListView lvSubjects;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private ArrayList<MonHoc> monHocList;
    private MonHocAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapViews();
        setupDatabase();
        setupListView();
        setupEventHandlers();
    }

    private void mapViews() {
        edtSubjectId = findViewById(R.id.edtMaMH);
        edtSubjectName = findViewById(R.id.edtTenMH);
        edtLessonCount = findViewById(R.id.edtSoTiet);
        btnInsert = findViewById(R.id.btnInsert);
        btnLoad = findViewById(R.id.btnLoad);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        lvSubjects = findViewById(R.id.lvMonHoc);
    }

    private void setupDatabase() {
        dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();
    }

    private void setupListView() {
        monHocList = new ArrayList<>();
        adapter = new MonHocAdapter(this, R.layout.listview_item, monHocList);
        lvSubjects.setAdapter(adapter);
    }

    private void setupEventHandlers() {
        btnInsert.setOnClickListener(view -> insertSubject());
        btnLoad.setOnClickListener(view -> loadSubjects());
        btnUpdate.setOnClickListener(view -> updateSubject());
        btnDelete.setOnClickListener(view -> deleteSubject());

        lvSubjects.setOnItemClickListener((parent, view, position, id) -> selectSubject(position));
    }

    private void insertSubject() {
        String idStr = edtSubjectId.getText().toString().trim();
        String name = edtSubjectName.getText().toString().trim();
        String lessonCountStr = edtLessonCount.getText().toString().trim();

        if (idStr.isEmpty() || name.isEmpty() || lessonCountStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        int subjectId = Integer.parseInt(idStr);
        int lessonCount = Integer.parseInt(lessonCountStr);

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_MAMH, subjectId);
        values.put(DatabaseHelper.COL_TENMH, name);
        values.put(DatabaseHelper.COL_SOTIET, lessonCount);

        long result = database.insert(DatabaseHelper.TABLE_NAME, null, values);
        if (result == -1) {
            Toast.makeText(this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
            loadSubjects();
        }
    }

    private void loadSubjects() {
        monHocList.clear();
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME,
                null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int subjectId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_MAMH));
                String subjectName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_TENMH));
                int lessonCount = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_SOTIET));

                monHocList.add(new MonHoc(subjectId, subjectName, lessonCount));
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }

    private void updateSubject() {
        String idStr = edtSubjectId.getText().toString().trim();
        String name = edtSubjectName.getText().toString().trim();
        String lessonCountStr = edtLessonCount.getText().toString().trim();

        if (idStr.isEmpty() || name.isEmpty() || lessonCountStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        int subjectId = Integer.parseInt(idStr);
        int lessonCount = Integer.parseInt(lessonCountStr);

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_TENMH, name);
        values.put(DatabaseHelper.COL_SOTIET, lessonCount);

        int rowsAffected = database.update(DatabaseHelper.TABLE_NAME, values,
                DatabaseHelper.COL_MAMH + "=?", new String[]{String.valueOf(subjectId)});

        if (rowsAffected > 0) {
            Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
            loadSubjects();
        } else {
            Toast.makeText(this, "Không tìm thấy mã môn học!", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteSubject() {
        String idStr = edtSubjectId.getText().toString().trim();

        if (idStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập mã môn học cần xóa!", Toast.LENGTH_SHORT).show();
            return;
        }

        int subjectId = Integer.parseInt(idStr);

        int rowsDeleted = database.delete(DatabaseHelper.TABLE_NAME,
                DatabaseHelper.COL_MAMH + "=?", new String[]{String.valueOf(subjectId)});

        if (rowsDeleted > 0) {
            Toast.makeText(this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
            loadSubjects();
        } else {
            Toast.makeText(this, "Không tìm thấy mã môn học!", Toast.LENGTH_SHORT).show();
        }
    }

    private void selectSubject(int position) {
        MonHoc selectedMonHoc = monHocList.get(position);

        edtSubjectId.setText(String.valueOf(selectedMonHoc.getMaMH()));
        edtSubjectName.setText(selectedMonHoc.getTenMH());
        edtLessonCount.setText(String.valueOf(selectedMonHoc.getSoTiet()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (database != null && database.isOpen()) {
            database.close();
        }
    }
}
