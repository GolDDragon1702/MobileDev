package buoi2.myapp3;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.math.BigDecimal;

public class Calculator extends AppCompatActivity {

    private EditText numberA, numberB, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate);

        numberA = findViewById(R.id.numberA);
        numberB = findViewById(R.id.numberB);
        result = findViewById(R.id.result);

        findViewById(R.id.btnAdd).setOnClickListener(v -> calculate("+") );
        findViewById(R.id.btnSub).setOnClickListener(v -> calculate("-") );
        findViewById(R.id.btnMul).setOnClickListener(v -> calculate("*") );
        findViewById(R.id.btnDiv).setOnClickListener(v -> calculate("/") );
        findViewById(R.id.btnGCD).setOnClickListener(v -> calculateGCD() );
    }

    private void calculate(String op) {
        try {
            BigDecimal a = new BigDecimal(numberA.getText().toString());
            BigDecimal b = new BigDecimal(numberB.getText().toString());
            BigDecimal res = BigDecimal.ZERO;
            switch (op) {
                case "+": res = a.add(b); break;
                case "-": res = a.subtract(b); break;
                case "*": res = a.multiply(b); break;
                case "/": res = b.compareTo(BigDecimal.ZERO) != 0 ? a.divide(b, 2, BigDecimal.ROUND_HALF_UP) : BigDecimal.ZERO; break;
            }
            result.setText(res.toString());
        } catch (Exception e) {
            result.setText("Error");
        }
    }

    private void calculateGCD() {
        try {
            int a = Integer.parseInt(numberA.getText().toString());
            int b = Integer.parseInt(numberB.getText().toString());
            while (b != 0) {
                int temp = b;
                b = a % b;
                a = temp;
            }
            result.setText(String.valueOf(a));
        } catch (Exception e) {
            result.setText("Error");
        }
    }
}
