package example.qlnv.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import example.qlnv.DAO.SanPhamDAO;
import example.qlnv.R;
import example.qlnv.adapters.SanPhamAdapter;
import example.qlnv.models.SanPham;

public class SanPhamActivity extends AppCompatActivity {
    EditText edtMaSP, edtTenSP, edtNgaySX, edtGiaBan, edtSoLuong;
    Button btnThem, btnSua, btnHome;
    ListView lvSanPham;

    SanPhamDAO sanPhamDAO;
    SanPhamAdapter adapter;
    List<SanPham> list;

    String selectedMaSP = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanpham);

        // Ánh xạ view
        edtMaSP = findViewById(R.id.edtMaSP);
        edtTenSP = findViewById(R.id.edtTenSP);
        edtNgaySX = findViewById(R.id.edtNgaySX);
        edtGiaBan = findViewById(R.id.edtGiaBan);
        edtSoLuong = findViewById(R.id.edtSoLuong);

        btnThem = findViewById(R.id.btnThemSP);
        btnSua = findViewById(R.id.btnSuaSP);

        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(SanPhamActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        lvSanPham = findViewById(R.id.lvSanPham);

        sanPhamDAO = new SanPhamDAO(this);
        list = new ArrayList<>();

        // Khởi tạo adapter
        adapter = new SanPhamAdapter(this, list);
        lvSanPham.setAdapter(adapter);

        // Load danh sách sản phẩm lên ListView
        loadSanPham();

        // Xử lý nút Thêm
        btnThem.setOnClickListener(v -> {
            SanPham sp = getSanPhamFromInput();
            if (sp == null) return;
            if (sanPhamDAO.themSanPham(sp)) {
                Toast.makeText(this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                clearInputs();
                loadSanPham();
            } else {
                Toast.makeText(this, "Thêm thất bại. Có thể mã sản phẩm đã tồn tại", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút Sửa
        btnSua.setOnClickListener(v -> {
            if (selectedMaSP == null) {
                Toast.makeText(this, "Vui lòng chọn sản phẩm để sửa", Toast.LENGTH_SHORT).show();
                return;
            }
            SanPham sp = getSanPhamFromInput();
            if (sp == null) return;
            if (sanPhamDAO.suaSanPham(sp)) {
                Toast.makeText(this, "Cập nhật sản phẩm thành công", Toast.LENGTH_SHORT).show();
                clearInputs();
                loadSanPham();
            } else {
                Toast.makeText(this, "Cập nhật thất bại. Sản phẩm không tồn tại", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý khi chọn item trong ListView
        lvSanPham.setOnItemClickListener((parent, view, position, id) -> {
            SanPham sp = list.get(position);
            selectedMaSP = sp.getMaSP();
            edtMaSP.setText(sp.getMaSP());
            edtTenSP.setText(sp.getTenSP());
            edtNgaySX.setText(sp.getNgaySanXuat());
            edtGiaBan.setText(String.valueOf(sp.getGiaBan()));
            edtSoLuong.setText(String.valueOf(sp.getSoLuong()));
        });
    }

    private void loadSanPham() {
        list.clear();
        list.addAll(sanPhamDAO.getAllSanPham());
        adapter.notifyDataSetChanged();
    }

    private SanPham getSanPhamFromInput() {
        String maSP = edtMaSP.getText().toString().trim();
        String tenSP = edtTenSP.getText().toString().trim();
        String ngaySX = edtNgaySX.getText().toString().trim();
        String giaBanStr = edtGiaBan.getText().toString().trim();
        String soLuongStr = edtSoLuong.getText().toString().trim();

        if (TextUtils.isEmpty(maSP) || TextUtils.isEmpty(tenSP) || TextUtils.isEmpty(giaBanStr) || TextUtils.isEmpty(soLuongStr)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ mã, tên, giá bán và số lượng", Toast.LENGTH_SHORT).show();
            return null;
        }

        double giaBan;
        int soLuong;
        try {
            giaBan = Double.parseDouble(giaBanStr);
            soLuong = Integer.parseInt(soLuongStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Giá bán hoặc số lượng không hợp lệ", Toast.LENGTH_SHORT).show();
            return null;
        }
        return new SanPham(maSP, tenSP, ngaySX, giaBan, soLuong);
    }

    private void clearInputs() {
        edtMaSP.setText("");
        edtTenSP.setText("");
        edtNgaySX.setText("");
        edtGiaBan.setText("");
        edtSoLuong.setText("");
        selectedMaSP = null;
    }
}
