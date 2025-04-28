package buoi2.myapp3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Sum extends AppCompatActivity {

    private EditText edtSoA, edtSoB;
    private TextView tvResult;
    private Button btnTong, btnXoaTrang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sum);

        edtSoA = findViewById(R.id.edtSoA);
        edtSoB = findViewById(R.id.edtSoB);
        tvResult = findViewById(R.id.tvResult);
        btnTong = findViewById(R.id.btnTong);
        btnXoaTrang = findViewById(R.id.btnXoaTrang);

        btnTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinhTong();
            }
        });

        btnXoaTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoaTrang();
            }
        });
    }

    private void tinhTong() {
        String soA = edtSoA.getText().toString();
        String soB = edtSoB.getText().toString();

        if (!soA.isEmpty() && !soB.isEmpty()) {
            try {
                double a = Double.parseDouble(soA);
                double b = Double.parseDouble(soB);
                double tong = a + b;

                tvResult.setText("Tổng 2 số là: " + tong);
                tvResult.setVisibility(View.VISIBLE);
            } catch (NumberFormatException e) {
                tvResult.setText("Vui lòng nhập số hợp lệ!");
                tvResult.setVisibility(View.VISIBLE);
            }
        } else {
            tvResult.setText("Vui lòng nhập đầy đủ số!");
            tvResult.setVisibility(View.VISIBLE);
        }
    }

    private void xoaTrang() {
        edtSoA.setText("");
        edtSoB.setText("");
        tvResult.setText("");
        tvResult.setVisibility(View.GONE);
    }
}