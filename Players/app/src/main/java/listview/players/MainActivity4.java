package listview.players;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity4 extends AppCompatActivity {
    EditText editSoPhieu, editNgay, editNoi;
    Spinner spinnerTuyen, spinnerXe;
    TextView resultText;
    LinearLayout layoutDanhSach;

    ArrayList<String> dsTuyen = new ArrayList<>(Arrays.asList("TPHCM - Hà Nội", "Hà Nội - Đà Nẵng"));
    ArrayList<String> dsXe = new ArrayList<>(Arrays.asList("Hoàng Long", "Mai Linh", "Phương Trang"));

    ArrayList<String> danhSachQuanLy = new ArrayList<>();
    int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        editSoPhieu = findViewById(R.id.editSoPhieu);
        editNgay = findViewById(R.id.editNgay);
        editNoi = findViewById(R.id.editNoi);
        spinnerTuyen = findViewById(R.id.spinnerTuyen);
        spinnerXe = findViewById(R.id.spinnerXe);
        layoutDanhSach = findViewById(R.id.layoutDanhSach);

        spinnerTuyen.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dsTuyen));
        spinnerXe.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dsXe));

        findViewById(R.id.btnThem).setOnClickListener(v -> add());
        findViewById(R.id.btnXoa).setOnClickListener(v -> delete());
        findViewById(R.id.btnSua).setOnClickListener(v -> edit());
        findViewById(R.id.btnClear).setOnClickListener(v -> clear());
    }

    void add() {
        String info = getInfo();
        if (info.isEmpty()) return;

        danhSachQuanLy.add(info);
        selectedIndex = danhSachQuanLy.size() - 1;
        addViewToList(info, spinnerXe.getSelectedItem().toString());
    }

    void addViewToList(String text, String xe) {
        LinearLayout itemLayout = new LinearLayout(this);
        itemLayout.setOrientation(LinearLayout.HORIZONTAL);
        itemLayout.setPadding(8, 8, 8, 8);
        itemLayout.setBackgroundColor(Color.parseColor("#e0f0ff"));

        ImageView img = new ImageView(this);
        img.setLayoutParams(new LinearLayout.LayoutParams(150, 150));

        switch (xe) {
            case "Hoàng Long":
                img.setImageResource(R.drawable.bus1);
                break;
            case "Mai Linh":
                img.setImageResource(R.drawable.bus2);
                break;
            case "Phương Trang":
                img.setImageResource(R.drawable.bus3);
                break;
            default:
                img.setImageResource(R.drawable.ic_launcher_foreground); // fallback
                break;
        }

        TextView tv = new TextView(this);
        tv.setText(text.replace("\n", "\n")); // giữ định dạng
        tv.setPadding(16, 0, 0, 0);
        tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        itemLayout.addView(img);
        itemLayout.addView(tv);
        layoutDanhSach.addView(itemLayout);
    }


    void delete() {
        if (selectedIndex >= 0 && selectedIndex < danhSachQuanLy.size()) {
            danhSachQuanLy.remove(selectedIndex);
            resultText.setText("Đã xoá!");
            selectedIndex = -1;
        }
    }

    void edit() {
        String info = getInfo();
        if (info.isEmpty() || selectedIndex < 0) return;

        danhSachQuanLy.set(selectedIndex, info);
        resultText.setText(info);
    }

    void clear() {
        editSoPhieu.setText("");
        editNgay.setText("");
        editNoi.setText("");
        spinnerTuyen.setSelection(0);
        spinnerXe.setSelection(0);
        resultText.setText("");
        selectedIndex = -1;
    }

    String getInfo() {
        String soPhieu = editSoPhieu.getText().toString().trim();
        String ngay = editNgay.getText().toString().trim();
        String noi = editNoi.getText().toString().trim();
        String tuyen = spinnerTuyen.getSelectedItem().toString();
        String xe = spinnerXe.getSelectedItem().toString();

        if (soPhieu.isEmpty() || ngay.isEmpty() || noi.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ!", Toast.LENGTH_SHORT).show();
            return "";
        }

        return "Số phiếu: " + soPhieu + "\nNgày: " + ngay + "\nXuất phát: " + noi +
                "\nTuyến: " + tuyen + "\nXe: " + xe;
    }
}

