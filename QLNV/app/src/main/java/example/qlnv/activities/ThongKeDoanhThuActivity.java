package example.qlnv.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import example.qlnv.database.DatabaseHelper;
import example.qlnv.R;
import example.qlnv.adapters.ThongKeAdapter;
import example.qlnv.models.ThongKe;

public class ThongKeDoanhThuActivity extends AppCompatActivity {
    EditText edtTuNgay, edtDenNgay;
    TextView tvTongDoanhThu, tvTongHoaDon;
    ListView lvThongKe;
    Button btnThongKe, btnHome;
    DatabaseHelper db;
    ArrayList<ThongKe> list;
    ThongKeAdapter adapter;
    final Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_doanh_thu);

        // Ánh xạ view
        edtTuNgay = findViewById(R.id.edtTuNgay);
        edtDenNgay = findViewById(R.id.edtDenNgay);
        btnThongKe = findViewById(R.id.btnThongKe);
        tvTongDoanhThu = findViewById(R.id.tvTongDoanhThu);
        tvTongHoaDon = findViewById(R.id.tvTongHoaDon);
        lvThongKe = findViewById(R.id.lvThongKe);
        btnHome = findViewById(R.id.btnHome);

        // Khởi tạo database
        db = new DatabaseHelper(this);

        // Khởi tạo adapter
        list = new ArrayList<>();
        adapter = new ThongKeAdapter(this, list);
        lvThongKe.setAdapter(adapter);

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

            loadThongKe(tuNgay, denNgay);
        });

        // Xử lý sự kiện nút Home
        btnHome.setOnClickListener(v -> finish());

        // Set ngày mặc định (đầu tháng đến hiện tại)
        String ngayHienTai = sdf.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String ngayDauThang = sdf.format(calendar.getTime());
        
        edtTuNgay.setText(ngayDauThang);
        edtDenNgay.setText(ngayHienTai);
        
        // Load dữ liệu thống kê mặc định
        loadThongKe(ngayDauThang, ngayHienTai);
    }

    private void showDatePicker(EditText editText) {
        new DatePickerDialog(this, (DatePicker view, int year, int month, int dayOfMonth) -> {
            calendar.set(year, month, dayOfMonth);
            editText.setText(sdf.format(calendar.getTime()));
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void loadThongKe(String tuNgay, String denNgay) {
        try {
            // Tính tổng doanh thu và số hóa đơn
            double tongDoanhThu = db.getDoanhThu(tuNgay, denNgay);
            int tongHoaDon = db.getSoHoaDon(tuNgay, denNgay);

            // Lấy danh sách thống kê theo sản phẩm
            List<ThongKe> thongKeList = db.getThongKeTheoNgay(tuNgay, denNgay);

            // Cập nhật UI
            tvTongDoanhThu.setText(String.format("Tổng doanh thu từ %s đến %s: %.2f", 
                tuNgay, denNgay, tongDoanhThu));
            tvTongHoaDon.setText(String.format("Tổng số hóa đơn: %d", tongHoaDon));

            // Cập nhật ListView
            list.clear();
            list.addAll(thongKeList);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            Toast.makeText(this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
