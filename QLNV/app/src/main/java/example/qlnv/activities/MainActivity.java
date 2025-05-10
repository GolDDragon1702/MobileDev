package example.qlnv.activities;

import android.content.Intent;
import android.os.Bundle;

import example.qlnv.R;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnNhanVien, btnKhachHang, btnSanPham, btnHoaDon, btnChiTiet, btnThongKeDT, btnSPBanChay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNhanVien = findViewById(R.id.btnNhanVien);
        btnKhachHang = findViewById(R.id.btnKhachHang);
        btnSanPham = findViewById(R.id.btnSanPham);
        btnHoaDon = findViewById(R.id.btnHoaDon);
        btnChiTiet = findViewById(R.id.btnChiTietHD);
        btnThongKeDT = findViewById(R.id.btnThongKe);
        btnSPBanChay = findViewById(R.id.btnSPBanChay);

        btnNhanVien.setOnClickListener(v -> startActivity(new Intent(this, NhanVienActivity.class)));
        btnKhachHang.setOnClickListener(v -> startActivity(new Intent(this, KhachHangActivity.class)));
        btnSanPham.setOnClickListener(v -> startActivity(new Intent(this, SanPhamActivity.class)));
        btnHoaDon.setOnClickListener(v -> startActivity(new Intent(this, HoaDonActivity.class)));
        btnChiTiet.setOnClickListener(v -> startActivity(new Intent(this, ChiTietHoaDonActivity.class)));
        btnThongKeDT.setOnClickListener(v -> startActivity(new Intent(this, ThongKeDoanhThuActivity.class)));
        btnSPBanChay.setOnClickListener(v -> startActivity(new Intent(this, SanPhamBanChayActivity.class)));
    }
}

