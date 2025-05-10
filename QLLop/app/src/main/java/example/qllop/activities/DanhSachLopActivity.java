package example.qllop.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import example.qllop.R;
import example.qllop.dao.LopDAO;
import example.qllop.database.DatabaseHelper;
import example.qllop.models.Lop;

//public class DanhSachLopActivity extends AppCompatActivity {
//    ListView lv;
//    SQLiteDatabase db;
//    ArrayList<String> dsLop;
//    ArrayAdapter<String> adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_danhsachlop);
//
//        lv = findViewById(R.id.lvLop);
//        dsLop = new ArrayList<>();
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsLop);
//        lv.setAdapter(adapter);
//
//        db = openOrCreateDatabase("QLSV.db", MODE_PRIVATE, null);
//        Cursor cursor = db.rawQuery("SELECT * FROM tblLop", null);
//        dsLop.clear();
//        while (cursor.moveToNext()) {
//            String line = cursor.getString(0) + " - " + cursor.getString(1) + " - Sĩ số: " + cursor.getInt(2);
//            dsLop.add(line);
//        }
//        adapter.notifyDataSetChanged();
//    }
//}

public class DanhSachLopActivity extends AppCompatActivity {

    private ListView lvDanhSachLop;
    private Button btnTroVe;

    private DatabaseHelper dbHelper;
    private LopDAO lopDAO;
    private List<Lop> lopList;
    private ArrayAdapter<Lop> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachlop);

        // Initialize database and DAO
        dbHelper = DatabaseHelper.getInstance(this);
        lopDAO = new LopDAO(dbHelper);

        // Initialize UI components
        lvDanhSachLop = findViewById(R.id.lvLop);
        btnTroVe = findViewById(R.id.btnTroVe);

        // Load data
        loadDanhSachLop();

        // Set click listener for the back button
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Set item click listener for the ListView
        lvDanhSachLop.setOnItemClickListener((parent, view, position, id) -> {
            Lop selectedLop = lopList.get(position);
            Toast.makeText(DanhSachLopActivity.this,
                    "Đã chọn: " + selectedLop.getTenLop(),
                    Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload data when the activity is resumed
        loadDanhSachLop();
    }

    private void loadDanhSachLop() {
        lopList = lopDAO.getAllLop();

        if (lopList.isEmpty()) {
            lopList = new ArrayList<>();
            Toast.makeText(this, "Không có lớp nào trong cơ sở dữ liệu!", Toast.LENGTH_SHORT).show();
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lopList);
        lvDanhSachLop.setAdapter(adapter);
    }
}