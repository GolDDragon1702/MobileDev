package buoi2.myapp3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RadioDemo extends AppCompatActivity {

    private EditText edtName, edtID, edtMoreInfo;
    private RadioGroup rgDegree;
    private CheckBox cbGaming, cbReading, cbNews;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infor);

        edtName = findViewById(R.id.edtName);
        edtID = findViewById(R.id.edtID);
        edtMoreInfo = findViewById(R.id.edtMoreInfo);
        rgDegree = findViewById(R.id.rgDegree);
        cbGaming = findViewById(R.id.cbGaming);
        cbReading = findViewById(R.id.cbReading);
        cbNews = findViewById(R.id.cbNews);
        tvResult = findViewById(R.id.tvResult);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> submitInfo());
    }

    private void submitInfo() {
        String name = edtName.getText().toString().trim();
        String id = edtID.getText().toString().trim();
        String moreInfo = edtMoreInfo.getText().toString().trim();

        if (name.isEmpty() || name.length() < 3) {
            Toast.makeText(this, "Tên phải có ít nhất 3 ký tự", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!id.matches("\\d{12}")) {
            Toast.makeText(this, "CCCD phải chứa đúng 12 số", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedDegreeId = rgDegree.getCheckedRadioButtonId();
        RadioButton selectedDegree = findViewById(selectedDegreeId);
        String degree = (selectedDegree != null) ? selectedDegree.getText().toString() : "Đại học";

        StringBuilder hobbies = new StringBuilder();
        if (cbGaming.isChecked()) hobbies.append("Chơi game, ");
        if (cbReading.isChecked()) hobbies.append("Đọc sách, ");
        if (cbNews.isChecked()) hobbies.append("Đọc báo, ");

        if (hobbies.length() == 0) {
            Toast.makeText(this, "Phải chọn ít nhất 1 sở thích", Toast.LENGTH_SHORT).show();
            return;
        }

        hobbies.setLength(hobbies.length() - 2);

        String result = "Thông tin cá nhân:\n"
                + "Họ và tên: " + name + "\n"
                + "CMND: " + id + "\n"
                + "Bằng cấp: " + degree + "\n"
                + "Sở thích: " + hobbies + "\n"
                + (moreInfo.isEmpty() ? "" : "Thông tin khác: " + moreInfo);

        tvResult.setText(result);
    }
}
