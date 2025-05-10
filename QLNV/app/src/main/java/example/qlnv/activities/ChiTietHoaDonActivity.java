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

import example.qlnv.DAO.ChiTietHoaDonDAO;
import example.qlnv.R;
import example.qlnv.adapters.ChiTietHoaDonAdapter;
import example.qlnv.models.ChiTietHoaDon;

public class ChiTietHoaDonActivity extends AppCompatActivity {
    EditText edtMaHD, edtMaSP, edtGiaBan, edtSoLuong, edtTongTien;
    Button btnThem, btnSua, btnXoa, btnClear, btnHome;
    ListView lvChiTietHoaDon;

    ChiTietHoaDonDAO chiTietHoaDonDAO;
    ArrayList<ChiTietHoaDon> list;
    ChiTietHoaDonAdapter adapter;

    String selectedMaHD = null;
    String selectedMaSP = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don);

        // Ánh xạ view
        edtMaHD = findViewById(R.id.edtMaHoaDon_CT);
        edtMaSP = findViewById(R.id.edtMaSP_CT);
        edtGiaBan = findViewById(R.id.edtGiaBan_CT);
        edtSoLuong = findViewById(R.id.edtSoLuong_CT);
        edtTongTien = findViewById(R.id.edtTongTien_CT);

        btnThem = findViewById(R.id.btnThemCTHD);
        btnSua = findViewById(R.id.btnSuaCTHD);
        btnXoa = findViewById(R.id.btnXoaCTHD);
        btnClear = findViewById(R.id.btnClearCTHD);

        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(ChiTietHoaDonActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        lvChiTietHoaDon = findViewById(R.id.lvChiTietHoaDon);

        chiTietHoaDonDAO = new ChiTietHoaDonDAO(this);
        list = new ArrayList<>();
        adapter = new ChiTietHoaDonAdapter(this, list);
        lvChiTietHoaDon.setAdapter(adapter);

        // Load danh sách chi tiết hóa đơn
        loadChiTietHoaDon();

        // Xử lý nút Thêm
        btnThem.setOnClickListener(v -> {
            String maHD = edtMaHD.getText().toString().trim();
            String maSP = edtMaSP.getText().toString().trim();
            String giaBanStr = edtGiaBan.getText().toString().trim();
            String soLuongStr = edtSoLuong.getText().toString().trim();

            if (TextUtils.isEmpty(maHD) || TextUtils.isEmpty(maSP) || 
                TextUtils.isEmpty(giaBanStr) || TextUtils.isEmpty(soLuongStr)) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double giaBan = Double.parseDouble(giaBanStr);
                int soLuong = Integer.parseInt(soLuongStr);
                double tongTien = giaBan * soLuong;

                ChiTietHoaDon cthd = new ChiTietHoaDon(maHD, maSP, giaBan, soLuong, tongTien);
                if (chiTietHoaDonDAO.themChiTietHoaDon(cthd)) {
                    Toast.makeText(this, "Thêm chi tiết hóa đơn thành công", Toast.LENGTH_SHORT).show();
                    clearInputs();
                    loadChiTietHoaDon();
                } else {
                    Toast.makeText(this, "Thêm thất bại. Có thể chi tiết hóa đơn đã tồn tại", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Giá bán và số lượng phải là số", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút Sửa
        btnSua.setOnClickListener(v -> {
            if (selectedMaHD == null || selectedMaSP == null) {
                Toast.makeText(this, "Vui lòng chọn chi tiết hóa đơn cần sửa", Toast.LENGTH_SHORT).show();
                return;
            }

            String maHD = edtMaHD.getText().toString().trim();
            String maSP = edtMaSP.getText().toString().trim();
            String giaBanStr = edtGiaBan.getText().toString().trim();
            String soLuongStr = edtSoLuong.getText().toString().trim();

            if (TextUtils.isEmpty(giaBanStr) || TextUtils.isEmpty(soLuongStr)) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double giaBan = Double.parseDouble(giaBanStr);
                int soLuong = Integer.parseInt(soLuongStr);
                double tongTien = giaBan * soLuong;

                ChiTietHoaDon cthd = new ChiTietHoaDon(maHD, maSP, giaBan, soLuong, tongTien);
                if (chiTietHoaDonDAO.suaChiTietHoaDon(cthd)) {
                    Toast.makeText(this, "Cập nhật chi tiết hóa đơn thành công", Toast.LENGTH_SHORT).show();
                    clearInputs();
                    loadChiTietHoaDon();
                } else {
                    Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Giá bán và số lượng phải là số", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút Xóa
        btnXoa.setOnClickListener(v -> {
            if (selectedMaHD == null || selectedMaSP == null) {
                Toast.makeText(this, "Vui lòng chọn chi tiết hóa đơn cần xóa", Toast.LENGTH_SHORT).show();
                return;
            }

            if (chiTietHoaDonDAO.xoaChiTietHoaDon(selectedMaHD, selectedMaSP)) {
                Toast.makeText(this, "Xóa chi tiết hóa đơn thành công", Toast.LENGTH_SHORT).show();
                clearInputs();
                loadChiTietHoaDon();
            } else {
                Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút Clear
        btnClear.setOnClickListener(v -> clearInputs());

        // Xử lý khi chọn item trong ListView
        lvChiTietHoaDon.setOnItemClickListener((parent, view, position, id) -> {
            ChiTietHoaDon cthd = list.get(position);
            selectedMaHD = cthd.getMaHD();
            selectedMaSP = cthd.getMaSP();
            
            edtMaHD.setText(cthd.getMaHD());
            edtMaSP.setText(cthd.getMaSP());
            edtGiaBan.setText(String.valueOf(cthd.getGiaBan()));
            edtSoLuong.setText(String.valueOf(cthd.getSoLuong()));
            edtTongTien.setText(String.valueOf(cthd.getTongTien()));
        });
    }

    private void loadChiTietHoaDon() {
        list.clear();
        list.addAll(chiTietHoaDonDAO.getAllChiTietHoaDon());
        adapter.notifyDataSetChanged();
    }

    private void clearInputs() {
        edtMaHD.setText("");
        edtMaSP.setText("");
        edtGiaBan.setText("");
        edtSoLuong.setText("");
        edtTongTien.setText("");
        selectedMaHD = null;
        selectedMaSP = null;
    }
}
