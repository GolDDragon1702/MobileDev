package example.qllop.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import example.qllop.R;
import example.qllop.adapter.SinhVienAdapter;
import example.qllop.dao.LopDAO;
import example.qllop.dao.SinhVienDAO;
import example.qllop.database.DatabaseHelper;
import example.qllop.models.Lop;
import example.qllop.models.SinhVien;


public class DanhSachSinhVienActivity extends AppCompatActivity {

    private Spinner spnLop;
    private ListView lvDanhSachSV;
    private Button btnTroVe;

    private DatabaseHelper dbHelper;
    private SinhVienDAO sinhVienDAO;
    private LopDAO lopDAO;

    private List<Lop> lopList;
    private List<SinhVien> sinhVienList;
    private SinhVienAdapter sinhVienAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachsinhvien);

        // Initialize database and DAOs
        dbHelper = DatabaseHelper.getInstance(this);
        sinhVienDAO = new SinhVienDAO(dbHelper);
        lopDAO = new LopDAO(dbHelper);

        // Initialize UI components
        spnLop = findViewById(R.id.spnLop);
        lvDanhSachSV = findViewById(R.id.lvDanhSachSV);
        btnTroVe = findViewById(R.id.btnTroVe);

        // Load class list for spinner
        loadLopList();

        // Set listener for class spinner
        spnLop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Load students for selected class
                loadSinhVienByLop();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Set click listener for back button
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Set item click listener for the ListView
        lvDanhSachSV.setOnItemClickListener((parent, view, position, id) -> {
            SinhVien selectedSV = sinhVienList.get(position);
            Toast.makeText(DanhSachSinhVienActivity.this,
                    "Đã chọn: " + selectedSV.getHoTen(),
                    Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload data when the activity is resumed
        loadLopList();
    }

    private void loadLopList() {
        // Get all classes
        lopList = lopDAO.getAllLop();

        // Add "All classes" option
        Lop allLop = new Lop("ALL", "Tất cả các lớp", 0);
        ArrayList<Lop> displayList = new ArrayList<>();
        displayList.add(allLop);
        displayList.addAll(lopList);

        // Create adapter for spinner
        ArrayAdapter<Lop> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, displayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLop.setAdapter(adapter);

        // Load students
        loadSinhVienByLop();
    }

    private void loadSinhVienByLop() {
        if (spnLop.getSelectedItem() == null) {
            return;
        }

        Lop selectedLop = (Lop) spnLop.getSelectedItem();

        // Load students based on selected class
        if (selectedLop.getMaLop().equals("ALL")) {
            // Load all students
            sinhVienList = sinhVienDAO.getAllSinhVien();
        } else {
            // Load students from selected class
            sinhVienList = sinhVienDAO.getSinhVienByLop(selectedLop.getMaLop());
        }

        if (sinhVienList.isEmpty()) {
            Toast.makeText(this, "Không có sinh viên nào trong danh sách!", Toast.LENGTH_SHORT).show();
            sinhVienList = new ArrayList<>();
        }

        // Create and set adapter for ListView
        sinhVienAdapter = new SinhVienAdapter(this, R.layout.item_sinh_vien, sinhVienList);
        lvDanhSachSV.setAdapter(sinhVienAdapter);
    }
}