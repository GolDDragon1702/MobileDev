package example.qllop.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import example.qllop.R;
import example.qllop.adapter.LopSpinnerAdapter;
import example.qllop.dao.LopDAO;
import example.qllop.dao.SinhVienDAO;
import example.qllop.database.DatabaseHelper;
import example.qllop.models.Lop;
import example.qllop.models.SinhVien;


public class SinhVienActivity extends AppCompatActivity {

    private EditText etMSSV;
    private EditText etHoTen;
    private EditText etNgaySinh;
    private EditText etDiaChi;
    private EditText etDienThoai;
    private Spinner spnMaLop;
    private Button btnLuuSV;
    private Button btnTroVe;

    private DatabaseHelper dbHelper;
    private SinhVienDAO sinhVienDAO;
    private LopDAO lopDAO;

    private List<Lop> lopList;
    private SimpleDateFormat dateFormat;
    private LopSpinnerAdapter lopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinhvien);

        // Initialize date format
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // Initialize database and DAOs
        dbHelper = DatabaseHelper.getInstance(this);
        sinhVienDAO = new SinhVienDAO(dbHelper);
        lopDAO = new LopDAO(dbHelper);

        // Initialize UI components
        etMSSV = findViewById(R.id.etMSSV);
        etHoTen = findViewById(R.id.etHoTen);
        etNgaySinh = findViewById(R.id.etNgaySinh);
        etDiaChi = findViewById(R.id.etDiaChi);
        etDienThoai = findViewById(R.id.etDienThoai);
        spnMaLop = findViewById(R.id.spnMaLop);
        btnLuuSV = findViewById(R.id.btnLuuSV);
        btnTroVe = findViewById(R.id.btnTroVe);

        // Load class list for spinner
        loadLopList();

        // Set date picker for birthday field
        etNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Set click listeners
        btnLuuSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSinhVien();
            }
        });

        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadLopList() {
        lopList = lopDAO.getAllLop();

        if (lopList.isEmpty()) {
            Toast.makeText(this, "Không có lớp nào trong cơ sở dữ liệu! Vui lòng thêm lớp trước.", Toast.LENGTH_LONG).show();
            lopList = new ArrayList<>();
        }

        // Create adapter for spinner
        lopAdapter = new LopSpinnerAdapter(this, R.layout.item_spinner_lop, lopList);
        spnMaLop.setAdapter(lopAdapter);
    }

    private void showDatePickerDialog() {
        // Get current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Parse existing date if available
        String currentDate = etNgaySinh.getText().toString().trim();
        if (!currentDate.isEmpty()) {
            try {
                calendar.setTime(dateFormat.parse(currentDate));
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // Show date picker dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Set selected date to EditText
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, monthOfYear, dayOfMonth);
                        etNgaySinh.setText(dateFormat.format(selectedDate.getTime()));
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }

    private void saveSinhVien() {
        // Get values from fields
        String mssv = etMSSV.getText().toString().trim();
        String hoTen = etHoTen.getText().toString().trim();
        String ngaySinhStr = etNgaySinh.getText().toString().trim();
        String diaChi = etDiaChi.getText().toString().trim();
        String dienThoai = etDienThoai.getText().toString().trim();

        // Validate input
        if (mssv.isEmpty() || hoTen.isEmpty() || ngaySinhStr.isEmpty() || diaChi.isEmpty() || dienThoai.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get selected class
        if (lopList.isEmpty() || spnMaLop.getSelectedItem() == null) {
            Toast.makeText(this, "Vui lòng chọn lớp!", Toast.LENGTH_SHORT).show();
            return;
        }

        Lop selectedLop = (Lop) spnMaLop.getSelectedItem();
        String maLop = selectedLop.getMaLop();

        try {
            // Create SinhVien object
            SinhVien sinhVien = new SinhVien(mssv, hoTen, ngaySinhStr, diaChi, dienThoai, maLop);

            // Check if student exists
            SinhVien existingSinhVien = sinhVienDAO.getSinhVienById(mssv);

            if (existingSinhVien != null) {
                // Update existing student
                int rowsAffected = sinhVienDAO.updateSinhVien(sinhVien);

                if (rowsAffected > 0) {
                    Toast.makeText(this, "Cập nhật sinh viên thành công!", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(this, "Cập nhật sinh viên thất bại!", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Add new student
                long id = sinhVienDAO.insertSinhVien(sinhVien);

                if (id != -1) {
                    Toast.makeText(this, "Thêm sinh viên thành công!", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(this, "Thêm sinh viên thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (ParseException e) {
            Toast.makeText(this, "Định dạng ngày không hợp lệ!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void clearFields() {
        etMSSV.setText("");
        etHoTen.setText("");
        etNgaySinh.setText("");
        etDiaChi.setText("");
        etDienThoai.setText("");
        if (spnMaLop.getAdapter() != null && spnMaLop.getAdapter().getCount() > 0) {
            spnMaLop.setSelection(0);
        }
        etMSSV.requestFocus();
    }
}