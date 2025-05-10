package example.qlnv.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import example.qlnv.R;
import example.qlnv.database.DatabaseHelper;
import example.qlnv.adapters.ThongKeAdapter;
import example.qlnv.models.ThongKe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class SanPhamBanChayActivity extends AppCompatActivity {
    EditText edtTuNgay, edtDenNgay;
    TextView tvTongSanPham;
    ListView lvSanPhamBanChay;
    Button btnThongKe, btnHome;
    DatabaseHelper db;
    ArrayList<ThongKe> list;
    ThongKeAdapter adapter;
    final Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham_ban_chay);

        // Ánh xạ view
        edtTuNgay = findViewById(R.id.edtTuNgay);
        edtDenNgay = findViewById(R.id.edtDenNgay);
        btnThongKe = findViewById(R.id.btnThongKe);
        tvTongSanPham = findViewById(R.id.tvTongSanPham);
        lvSanPhamBanChay = findViewById(R.id.lvSanPhamBanChay);
        btnHome = findViewById(R.id.btnHome);

        // Khởi tạo database
        db = new DatabaseHelper(this);

        // Khởi tạo adapter
        list = new ArrayList<>();
        adapter = new ThongKeAdapter(this, list);
        lvSanPhamBanChay.setAdapter(adapter);

        // Hiển thị DatePicker khi nhấn vào EditText
        edtTuNgay.setOnClickListener(v -> showDatePicker(edtTuNgay));
        edtDenNgay.setOnClickListener(v -> showDatePicker(edtDenNgay));

        // Xử lý sự kiện nút Thống kê
        btnThongKe.setOnClickListener(v -> {
            String tuNgay = edtTuNgay.getText().toString().trim();
            String denNgay = edtDenNgay.getText().toString().trim();

            if (tuNgay.isEmpty() || denNgay.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ ngày bắt đầu và ngày kết thúc", Toast.LENGTH_SHORT).show();
                return;
            }

            loadSanPhamBanChay(tuNgay, denNgay);
        });

        // Xử lý sự kiện nút Home
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(SanPhamBanChayActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Xóa các activity trên MainActivity
            startActivity(intent);
            finish();
        });

        // Set ngày mặc định (đầu tháng đến hiện tại)
        String ngayHienTai = sdf.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String ngayDauThang = sdf.format(calendar.getTime());
        
        edtTuNgay.setText(ngayDauThang);
        edtDenNgay.setText(ngayHienTai);
        
        // Load dữ liệu thống kê mặc định
        loadSanPhamBanChay(ngayDauThang, ngayHienTai);
    }

    private void showDatePicker(EditText editText) {
        new DatePickerDialog(this, (DatePicker view, int year, int month, int dayOfMonth) -> {
            calendar.set(year, month, dayOfMonth);
            editText.setText(sdf.format(calendar.getTime()));
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void loadSanPhamBanChay(String tuNgay, String denNgay) {
        try {
            // Lấy danh sách sản phẩm bán chạy
            List<ThongKe> sanPhamList = db.getSanPhamBanChay(tuNgay, denNgay);

            // Cập nhật UI
            tvTongSanPham.setText(String.format("Tổng số sản phẩm đã bán từ %s đến %s: %d", 
                tuNgay, denNgay, sanPhamList.size()));

            // Cập nhật ListView
            list.clear();
            list.addAll(sanPhamList);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            Toast.makeText(this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
