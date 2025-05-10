package example.qllop.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import example.qllop.R;
import example.qllop.dao.LopDAO;
import example.qllop.database.DatabaseHelper;
import example.qllop.models.Lop;


public class LopActivity extends AppCompatActivity {

    private EditText etMaLop, etTenLop, etSiSo;
    private Button btnLuuLop, btnXoa, btnTroVe;

    private DatabaseHelper dbHelper;
    private LopDAO lopDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lop);

        // Initialize database and DAO
        dbHelper = DatabaseHelper.getInstance(this);
        lopDAO = new LopDAO(dbHelper);

        // Initialize UI components
        etMaLop = findViewById(R.id.etMaLop);
        etTenLop = findViewById(R.id.etTenLop);
        etSiSo = findViewById(R.id.etSiSo);
        btnLuuLop = findViewById(R.id.btnLuuLop);
        btnXoa = findViewById(R.id.btnXoa);
        btnTroVe = findViewById(R.id.btnTroVe);

        // Set click listeners
        btnLuuLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLop();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteLop();
            }});

        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void saveLop() {
        String maLop = etMaLop.getText().toString().trim();
        String tenLop = etTenLop.getText().toString().trim();
        String siSoStr = etSiSo.getText().toString().trim();

        // Validate input
        if (maLop.isEmpty() || tenLop.isEmpty() || siSoStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int siSo = Integer.parseInt(siSoStr);

            // Check if class exists
            if (lopDAO.checkLopExists(maLop)) {
                // Update existing class
                Lop lop = new Lop(maLop, tenLop, siSo);
                int rowsAffected = lopDAO.updateLop(lop);

                if (rowsAffected > 0) {
                    Toast.makeText(this, "Cập nhật lớp thành công!", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(this, "Cập nhật lớp thất bại!", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Add new class
                Lop lop = new Lop(maLop, tenLop, siSo);
                long id = lopDAO.insertLop(lop);

                if (id != -1) {
                    Toast.makeText(this, "Thêm lớp thành công!", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(this, "Thêm lớp thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Sĩ số phải là số nguyên!", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteLop(){
        String maLop = etMaLop.getText().toString().trim();
        if (maLop.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập mã lớp!", Toast.LENGTH_SHORT).show();
            return;
        }
        int rowsAffected = lopDAO.deleteLop(maLop);
        if (rowsAffected > 0) {
            Toast.makeText(this, "Xóa lớp thành công!", Toast.LENGTH_SHORT).show();
            clearFields();
        } else {
            Toast.makeText(this, "Xóa lớp thất bại!", Toast.LENGTH_SHORT).show();
        }
    }
    private void clearFields() {
        etMaLop.setText("");
        etTenLop.setText("");
        etSiSo.setText("");
        etMaLop.requestFocus();
    }
}