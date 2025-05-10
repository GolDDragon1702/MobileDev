package example.qlnv.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import example.qlnv.DAO.KhachHangDAO;
import example.qlnv.R;
import example.qlnv.adapters.KhachHangAdapter;
import example.qlnv.models.KhachHang;

public class KhachHangActivity extends AppCompatActivity {
    EditText edtMaKH, edtHoTen, edtNgaySinh, edtEmail, edtDienThoai;
    RadioGroup rgGioiTinh;
    RadioButton rbNam, rbNu;
    Button btnThem, btnSua, btnXoa, btnClear, btnHome;
    ListView lvKhachHang;

    KhachHangDAO khachHangDAO;
    ArrayList<KhachHang> list;
    KhachHangAdapter adapter;

    String selectedMaKH = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khachhang);

        // Ánh xạ view
        edtMaKH = findViewById(R.id.edtMaKhachHang);
        edtHoTen = findViewById(R.id.edtTenKhachHang);
        edtNgaySinh = findViewById(R.id.edtNgaySinhKH);
        edtEmail = findViewById(R.id.edtEmail);
        edtDienThoai = findViewById(R.id.edtDienThoaiKH);

        rgGioiTinh = findViewById(R.id.rgGioiTinhKH);
        rbNam = findViewById(R.id.rbNamKH);
        rbNu = findViewById(R.id.rbNuKH);

        btnThem = findViewById(R.id.btnThemKH);
        btnSua = findViewById(R.id.btnSuaKH);
        btnXoa = findViewById(R.id.btnXoaKH);
        btnClear = findViewById(R.id.btnClearKH);

        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(KhachHangActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        lvKhachHang = findViewById(R.id.lvKhachHang);

        khachHangDAO = new KhachHangDAO(this);
        list = new ArrayList<>();
        adapter = new KhachHangAdapter(this, list);
        lvKhachHang.setAdapter(adapter);

        // Load danh sách khách hàng
        loadKhachHang();

        // Xử lý nút Thêm
        btnThem.setOnClickListener(v -> {
            String maKH = edtMaKH.getText().toString().trim();
            String hoTen = edtHoTen.getText().toString().trim();
            String ngaySinh = edtNgaySinh.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String dienThoai = edtDienThoai.getText().toString().trim();

            // Lấy giới tính
            int selectedId = rgGioiTinh.getCheckedRadioButtonId();
            String gioiTinh = null;
            if (selectedId == rbNam.getId()) {
                gioiTinh = "Nam";
            } else if (selectedId == rbNu.getId()) {
                gioiTinh = "Nữ";
            }

            if (TextUtils.isEmpty(maKH) || TextUtils.isEmpty(hoTen) || TextUtils.isEmpty(gioiTinh)) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ mã khách hàng, họ tên và giới tính", Toast.LENGTH_SHORT).show();
                return;
            }

            KhachHang kh = new KhachHang(maKH, hoTen, ngaySinh, gioiTinh, email, dienThoai);
            if (khachHangDAO.themKhachHang(kh)) {
                Toast.makeText(this, "Thêm khách hàng thành công", Toast.LENGTH_SHORT).show();
                clearInputs();
                loadKhachHang();
            } else {
                Toast.makeText(this, "Thêm thất bại. Có thể mã khách hàng đã tồn tại", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút Sửa
        btnSua.setOnClickListener(v -> {
            if (selectedMaKH == null) {
                Toast.makeText(this, "Vui lòng chọn khách hàng cần sửa", Toast.LENGTH_SHORT).show();
                return;
            }

            String maKH = edtMaKH.getText().toString().trim();
            String hoTen = edtHoTen.getText().toString().trim();
            String ngaySinh = edtNgaySinh.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String dienThoai = edtDienThoai.getText().toString().trim();

            int selectedId = rgGioiTinh.getCheckedRadioButtonId();
            String gioiTinh = null;
            if (selectedId == rbNam.getId()) {
                gioiTinh = "Nam";
            } else if (selectedId == rbNu.getId()) {
                gioiTinh = "Nữ";
            }

            if (TextUtils.isEmpty(hoTen) || TextUtils.isEmpty(gioiTinh)) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ họ tên và giới tính", Toast.LENGTH_SHORT).show();
                return;
            }

            KhachHang kh = new KhachHang(maKH, hoTen, ngaySinh, gioiTinh, email, dienThoai);
            if (khachHangDAO.suaKhachHang(kh)) {
                Toast.makeText(this, "Cập nhật khách hàng thành công", Toast.LENGTH_SHORT).show();
                clearInputs();
                loadKhachHang();
            } else {
                Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút Xóa
        btnXoa.setOnClickListener(v -> {
            if (selectedMaKH == null) {
                Toast.makeText(this, "Vui lòng chọn khách hàng cần xóa", Toast.LENGTH_SHORT).show();
                return;
            }

            if (khachHangDAO.xoaKhachHang(selectedMaKH)) {
                Toast.makeText(this, "Xóa khách hàng thành công", Toast.LENGTH_SHORT).show();
                clearInputs();
                loadKhachHang();
            } else {
                Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút Clear
        btnClear.setOnClickListener(v -> clearInputs());

        // Xử lý khi chọn item trong ListView
        lvKhachHang.setOnItemClickListener((parent, view, position, id) -> {
            KhachHang kh = list.get(position);
            selectedMaKH = kh.getMaKH();
            
            edtMaKH.setText(kh.getMaKH());
            edtHoTen.setText(kh.getHoTen());
            edtNgaySinh.setText(kh.getNgaySinh());
            edtEmail.setText(kh.getEmail());
            edtDienThoai.setText(kh.getDienThoai());

            if (kh.getGioiTinh().equals("Nam")) {
                rbNam.setChecked(true);
            } else if (kh.getGioiTinh().equals("Nữ")) {
                rbNu.setChecked(true);
            } else {
                rgGioiTinh.clearCheck();
            }
        });
    }

    private void loadKhachHang() {
        list.clear();
        list.addAll(khachHangDAO.getAllKhachHang());
        adapter.notifyDataSetChanged();
    }

    private void clearInputs() {
        edtMaKH.setText("");
        edtHoTen.setText("");
        edtNgaySinh.setText("");
        edtEmail.setText("");
        edtDienThoai.setText("");
        rgGioiTinh.clearCheck();
        selectedMaKH = null;
    }
}
