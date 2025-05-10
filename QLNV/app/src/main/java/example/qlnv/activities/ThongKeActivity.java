package example.qlnv.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import example.qlnv.DAO.ChiTietHoaDonDAO;
import example.qlnv.DAO.HoaDonDAO;
import example.qlnv.R;
import example.qlnv.adapters.ThongKeAdapter;
import example.qlnv.models.ThongKe;
import example.qlnv.models.HoaDon;
import example.qlnv.models.ChiTietHoaDon;

public class ThongKeActivity extends AppCompatActivity {
    TextView tvTongDoanhThu, tvTongHoaDon;
    ListView lvThongKe;
    Button btnHome;

    HoaDonDAO hoaDonDAO;
    ChiTietHoaDonDAO chiTietHoaDonDAO;
    ArrayList<ThongKe> list;
    ThongKeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);

        // Ánh xạ view
        tvTongDoanhThu = findViewById(R.id.tvTongDoanhThu);
        tvTongHoaDon = findViewById(R.id.tvTongHoaDon);
        lvThongKe = findViewById(R.id.lvThongKe);

        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(ThongKeActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        // Khởi tạo DAO
        hoaDonDAO = new HoaDonDAO(this);
        chiTietHoaDonDAO = new ChiTietHoaDonDAO(this);

        // Khởi tạo adapter
        list = new ArrayList<>();
        adapter = new ThongKeAdapter(this, list);
        lvThongKe.setAdapter(adapter);

        // Load dữ liệu thống kê
        loadThongKe();
    }

    private void loadThongKe() {
        // Tính tổng doanh thu và số hóa đơn
        double tongDoanhThu = 0;
        int tongHoaDon = 0;
        List<ThongKe> thongKeList = new ArrayList<>();

        // Lấy danh sách hóa đơn
        List<HoaDon> hoaDonList = hoaDonDAO.getAllHoaDon();
        tongHoaDon = hoaDonList.size();

        // Tính toán thống kê cho từng sản phẩm
        for (HoaDon hd : hoaDonList) {
            tongDoanhThu += hd.getTongTien();
            
            // Lấy chi tiết hóa đơn cho hóa đơn hiện tại
            List<ChiTietHoaDon> chiTietList = chiTietHoaDonDAO.getChiTietHoaDonByMaHD(hd.getMaHD());
            
            // Thêm vào danh sách thống kê
            for (ChiTietHoaDon cthd : chiTietList) {
                ThongKe tk = new ThongKe(
                    cthd.getMaSP(),
                    cthd.getSoLuong(),
                    cthd.getGiaBan(),
                    cthd.getTongTien()
                );
                thongKeList.add(tk);
            }
        }

        // Gộp các thống kê trùng lặp
        ArrayList<ThongKe> mergedList = new ArrayList<>();
        for (ThongKe tk : thongKeList) {
            boolean found = false;
            for (ThongKe merged : mergedList) {
                if (merged.getMaSP().equals(tk.getMaSP())) {
                    merged.setSoLuong(merged.getSoLuong() + tk.getSoLuong());
                    merged.setTongTien(merged.getTongTien() + tk.getTongTien());
                    found = true;
                    break;
                }
            }
            if (!found) {
                mergedList.add(tk);
            }
        }

        // Cập nhật UI
        tvTongDoanhThu.setText(String.format("Tổng doanh thu: %.2f", tongDoanhThu));
        tvTongHoaDon.setText(String.format("Tổng số hóa đơn: %d", tongHoaDon));

        // Cập nhật ListView
        list.clear();
        list.addAll(mergedList);
        adapter.notifyDataSetChanged();
    }
} 