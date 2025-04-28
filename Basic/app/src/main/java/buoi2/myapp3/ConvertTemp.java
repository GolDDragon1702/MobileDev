package buoi2.myapp3;

import static java.lang.String.format;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ConvertTemp extends AppCompatActivity {

    private EditText edtF, edtC;
    private Button btnToC, btnToF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.temp);

        edtF = findViewById(R.id.edtF);
        edtC = findViewById(R.id.edtC);
        btnToC = findViewById(R.id.btnToC);
        btnToF = findViewById(R.id.btnToF);

        // Xử lý sự kiện chuyển từ Fahrenheit sang Celsius
        btnToC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fText = edtF.getText().toString();
                if (!fText.isEmpty()) {
                    double fahrenheit = Double.parseDouble(fText);
                    double celsius = (fahrenheit - 32) * 5 / 9;
                    edtC.setText(format("%.2f", celsius));
                }
            }
        });

        // Xử lý sự kiện chuyển từ Celsius sang Fahrenheit
        btnToF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cText = edtC.getText().toString();
                if (!cText.isEmpty()) {
                    double celsius = Double.parseDouble(cText);
                    double fahrenheit = (celsius * 9 / 5) + 32;
                    edtF.setText(format("%.2f", fahrenheit));
                }
            }
        });
    }
}
