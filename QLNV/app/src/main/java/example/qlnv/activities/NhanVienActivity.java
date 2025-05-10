package example.qlnv.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import example.qlnv.DAO.NhanVienDAO;
import example.qlnv.R;
import example.qlnv.adapters.NhanVienAdapter;
import example.qlnv.database.DatabaseHelper;
import example.qlnv.models.NhanVien;

import java.util.ArrayList;

public class NhanVienActivity extends AppCompatActivity {
    EditText edtMaNV, edtHoTen, edtNgaySinh, edtGioiTinh, edtDiaChi, edtSDT, edtGhiChu;
    Button btnThem, btnSua, btnXoa, btnClear, btnHome;
    ListView lvNhanVien;

    NhanVienDAO nhanVienDAO;
    ArrayList<NhanVien> list;
    NhanVienAdapter adapter;

    String selectedMaNV = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvien);

        // Ánh xạ view
        edtMaNV = findViewById(R.id.edtMaNV);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        edtGioiTinh = findViewById(R.id.edtGioiTinh);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtSDT = findViewById(R.id.edtSDT);
        edtGhiChu = findViewById(R.id.edtGhiChu);

        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
        btnClear = findViewById(R.id.btnClear);

        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(NhanVienActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Xóa các activity trên MainActivity
            startActivity(intent);
            finish();
        });

        lvNhanVien = findViewById(R.id.lvNhanVien);

        nhanVienDAO = new NhanVienDAO(this);
        list = new ArrayList<>();
        adapter = new NhanVienAdapter(this, list);
        lvNhanVien.setAdapter(adapter);

        loadNhanVien();

        lvNhanVien.setOnItemClickListener((parent, view, position, id) -> {
            NhanVien nv = list.get(position);
            selectedMaNV = nv.getMaNV();
            edtMaNV.setText(nv.getMaNV());
            edtHoTen.setText(nv.getHoTen());
            edtNgaySinh.setText(nv.getNgaySinh());
            edtGioiTinh.setText(nv.getGioiTinh());
            edtDiaChi.setText(nv.getDiaChi());
            edtSDT.setText(nv.getDienThoai());
            edtGhiChu.setText(nv.getGhiChu());
        });

        btnThem.setOnClickListener(v -> {
            NhanVien nv = getNhanVienFromInput();
            if (nv == null) return;
            if (nhanVienDAO.themNhanVien(nv)) {
                Toast.makeText(this, "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                clearInputs();
                loadNhanVien();
            } else {
                Toast.makeText(this, "Thêm thất bại. Mã nhân viên có thể đã tồn tại", Toast.LENGTH_SHORT).show();
            }
        });

        btnSua.setOnClickListener(v -> {
            if (selectedMaNV == null) {
                Toast.makeText(this, "Vui lòng chọn nhân viên để sửa", Toast.LENGTH_SHORT).show();
                return;
            }
            NhanVien nv = getNhanVienFromInput();
            if (nv == null) return;
            if (nhanVienDAO.suaNhanVien(nv)) {
                Toast.makeText(this, "Cập nhật nhân viên thành công", Toast.LENGTH_SHORT).show();
                clearInputs();
                loadNhanVien();
                selectedMaNV = null;
            } else {
                Toast.makeText(this, "Cập nhật thất bại. Nhân viên không tồn tại", Toast.LENGTH_SHORT).show();
            }
        });

        btnXoa.setOnClickListener(v -> {
            if (selectedMaNV == null) {
                Toast.makeText(this, "Vui lòng chọn nhân viên để xóa", Toast.LENGTH_SHORT).show();
                return;
            }
            if (nhanVienDAO.xoaNhanVien(selectedMaNV)) {
                Toast.makeText(this, "Xóa nhân viên thành công", Toast.LENGTH_SHORT).show();
                clearInputs();
                loadNhanVien();
                selectedMaNV = null;
            } else {
                Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        btnClear.setOnClickListener(v -> {
            clearInputs();
            selectedMaNV = null;
        });
    }

    private void loadNhanVien() {
        list.clear();
        list.addAll(nhanVienDAO.getAllNhanVien());
        adapter.notifyDataSetChanged();
    }

    private NhanVien getNhanVienFromInput() {
        String maNV = edtMaNV.getText().toString().trim();
        String hoTen = edtHoTen.getText().toString().trim();
        String ngaySinh = edtNgaySinh.getText().toString().trim();
        String gioiTinh = edtGioiTinh.getText().toString().trim();
        String diaChi = edtDiaChi.getText().toString().trim();
        String dienThoai = edtSDT.getText().toString().trim();
        String ghiChu = edtGhiChu.getText().toString().trim();

        if (TextUtils.isEmpty(maNV) || TextUtils.isEmpty(hoTen)) {
            Toast.makeText(this, "Vui lòng nhập mã và họ tên", Toast.LENGTH_SHORT).show();
            return null;
        }

        return new NhanVien(maNV, hoTen, ngaySinh, gioiTinh, diaChi, dienThoai, ghiChu);
    }

    private void clearInputs() {
        edtMaNV.setText("");
        edtHoTen.setText("");
        edtNgaySinh.setText("");
        edtGioiTinh.setText("");
        edtDiaChi.setText("");
        edtSDT.setText("");
        edtGhiChu.setText("");
    }
}
