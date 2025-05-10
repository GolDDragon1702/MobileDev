package example.qllop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import example.qllop.R;
import example.qllop.database.DatabaseHelper;


public class MainActivity extends AppCompatActivity {

    private Button btnCreateDB;
    private Button btnNhapLop;
    private Button btnNhapSinhVien;
    private Button btnDanhSachLop;
    private Button btnDanhSachSinhVien;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize database helper
        dbHelper = DatabaseHelper.getInstance(this);

        // Initialize UI components
        btnCreateDB = findViewById(R.id.btnCreateDB);
        btnNhapLop = findViewById(R.id.btnNhapLop);
        btnNhapSinhVien = findViewById(R.id.btnNhapSinhVien);
        btnDanhSachLop = findViewById(R.id.btnDanhSachLop);
        btnDanhSachSinhVien = findViewById(R.id.btnDanhSachSinhVien);

        // Set click listeners
        btnCreateDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create or reset database
                dbHelper.resetDatabase();
                // Notify user with a Toast
                android.widget.Toast.makeText(MainActivity.this,
                        "Đã tạo cơ sở dữ liệu thành công!",
                        android.widget.Toast.LENGTH_SHORT).show();
            }
        });

        btnNhapLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to LopActivity
                Intent intent = new Intent(MainActivity.this, LopActivity.class);
                startActivity(intent);
            }
        });

        btnNhapSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SinhVienActivity
                Intent intent = new Intent(MainActivity.this, SinhVienActivity.class);
                startActivity(intent);
            }
        });

        btnDanhSachLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to DanhSachLopActivity
                Intent intent = new Intent(MainActivity.this, DanhSachLopActivity.class);
                startActivity(intent);
            }
        });

        btnDanhSachSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to DanhSachSinhVienActivity
                Intent intent = new Intent(MainActivity.this, DanhSachSinhVienActivity.class);
                startActivity(intent);
            }
        });
    }
}