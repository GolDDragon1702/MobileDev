package example.qlnv.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import example.qlnv.DAO.HoaDonDAO;
import example.qlnv.R;
import example.qlnv.adapters.HoaDonAdapter;
import example.qlnv.models.HoaDon;

public class HoaDonActivity extends AppCompatActivity {
    EditText edtMaHD, edtNgayBan, edtMaNV, edtMaKH, edtTongTien;
    Button btnThem, btnSua, btnXoa, btnClear, btnHome;
    ListView lvHoaDon;

    HoaDonDAO hoaDonDAO;
    ArrayList<HoaDon> list;
    HoaDonAdapter adapter;

    String selectedMaHD = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);

        // Ánh xạ view
        edtMaHD = findViewById(R.id.edtMaHoaDon);
        edtNgayBan = findViewById(R.id.edtNgayBan);
        edtMaNV = findViewById(R.id.edtMaNV);
        edtMaKH = findViewById(R.id.edtMaKH);
        edtTongTien = findViewById(R.id.edtTongTien);

        btnThem = findViewById(R.id.btnThemHD);
        btnSua = findViewById(R.id.btnSuaHD);
        btnXoa = findViewById(R.id.btnXoaHD);
        btnClear = findViewById(R.id.btnClearHD);

        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(HoaDonActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        lvHoaDon = findViewById(R.id.lvHoaDon);

        hoaDonDAO = new HoaDonDAO(this);
        list = new ArrayList<>();
        adapter = new HoaDonAdapter(this, list);
        lvHoaDon.setAdapter(adapter);

        // Load danh sách hóa đơn
        loadHoaDon();

        // Xử lý nút Thêm
        btnThem.setOnClickListener(v -> {
            String maHD = edtMaHD.getText().toString().trim();
            String ngayBan = edtNgayBan.getText().toString().trim();
            String maNV = edtMaNV.getText().toString().trim();
            String maKH = edtMaKH.getText().toString().trim();
            String tongTienStr = edtTongTien.getText().toString().trim();

            if (TextUtils.isEmpty(maHD) || TextUtils.isEmpty(ngayBan) || 
                TextUtils.isEmpty(maNV) || TextUtils.isEmpty(maKH) || 
                TextUtils.isEmpty(tongTienStr)) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double tongTien = Double.parseDouble(tongTienStr);
                HoaDon hd = new HoaDon(maHD, ngayBan, maNV, maKH, tongTien);
                if (hoaDonDAO.themHoaDon(hd)) {
                    Toast.makeText(this, "Thêm hóa đơn thành công", Toast.LENGTH_SHORT).show();
                    clearInputs();
                    loadHoaDon();
                } else {
                    Toast.makeText(this, "Thêm thất bại. Có thể hóa đơn đã tồn tại", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Tổng tiền phải là số", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút Sửa
        btnSua.setOnClickListener(v -> {
            if (selectedMaHD == null) {
                Toast.makeText(this, "Vui lòng chọn hóa đơn cần sửa", Toast.LENGTH_SHORT).show();
                return;
            }

            String maHD = edtMaHD.getText().toString().trim();
            String ngayBan = edtNgayBan.getText().toString().trim();
            String maNV = edtMaNV.getText().toString().trim();
            String maKH = edtMaKH.getText().toString().trim();
            String tongTienStr = edtTongTien.getText().toString().trim();

            if (TextUtils.isEmpty(ngayBan) || TextUtils.isEmpty(maNV) || 
                TextUtils.isEmpty(maKH) || TextUtils.isEmpty(tongTienStr)) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double tongTien = Double.parseDouble(tongTienStr);
                HoaDon hd = new HoaDon(maHD, ngayBan, maNV, maKH, tongTien);
                if (hoaDonDAO.suaHoaDon(hd)) {
                    Toast.makeText(this, "Cập nhật hóa đơn thành công", Toast.LENGTH_SHORT).show();
                    clearInputs();
                    loadHoaDon();
                } else {
                    Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Tổng tiền phải là số", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút Xóa
        btnXoa.setOnClickListener(v -> {
            if (selectedMaHD == null) {
                Toast.makeText(this, "Vui lòng chọn hóa đơn cần xóa", Toast.LENGTH_SHORT).show();
                return;
            }

            if (hoaDonDAO.xoaHoaDon(selectedMaHD)) {
                Toast.makeText(this, "Xóa hóa đơn thành công", Toast.LENGTH_SHORT).show();
                clearInputs();
                loadHoaDon();
            } else {
                Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút Clear
        btnClear.setOnClickListener(v -> clearInputs());

        // Xử lý khi chọn item trong ListView
        lvHoaDon.setOnItemClickListener((parent, view, position, id) -> {
            HoaDon hd = list.get(position);
            selectedMaHD = hd.getMaHD();
            
            edtMaHD.setText(hd.getMaHD());
            edtNgayBan.setText(hd.getNgayBan());
            edtMaNV.setText(hd.getMaNV());
            edtMaKH.setText(hd.getMaKH());
            edtTongTien.setText(String.valueOf(hd.getTongTien()));
        });
    }

    private void loadHoaDon() {
        list.clear();
        list.addAll(hoaDonDAO.getAllHoaDon());
        adapter.notifyDataSetChanged();
    }

    private void clearInputs() {
        edtMaHD.setText("");
        edtNgayBan.setText("");
        edtMaNV.setText("");
        edtMaKH.setText("");
        edtTongTien.setText("");
        selectedMaHD = null;
    }
}
