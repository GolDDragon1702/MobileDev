package buoi2.myapp3;

import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class StudentInfor extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.student);

        EditText edtAccount = findViewById(R.id.edtAccount);
        EditText edtPassword = findViewById(R.id.edtPassword);
        EditText edtDOB = findViewById(R.id.edtDOB);
        EditText edtEmail = findViewById(R.id.edtEmail);
        Button btnSubmit = findViewById(R.id.btnSubmit);
        TextView infor = findViewById(R.id.Infor);

        btnSubmit.setOnClickListener(view -> {
            String account = edtAccount.getText().toString();
            String password = edtPassword.getText().toString();
            String dob = edtDOB.getText().toString();
            String email = edtEmail.getText().toString();

            if (account.isEmpty() || password.isEmpty() || dob.isEmpty() || email.isEmpty()) {
                Toast.makeText(StudentInfor.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(StudentInfor.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                String infoText = "Tài khoản: " + account + "\nMật khẩu: " + password +
                        "\nNgày sinh: " + dob + "\nEmail: " + email;
                infor.setText(infoText);
            }
        });
    }
}
