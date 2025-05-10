package example.qlsv;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtName, edtClass;
    Button btnAdd, btnEdit;
    ListView listView;
    DBHelper dbHelper;
    List<SinhVien> list;
    SinhVienAdapter adapter;
    int selectedId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName = findViewById(R.id.edtName);
        edtClass = findViewById(R.id.edtClass);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        listView = findViewById(R.id.listView);

        dbHelper = new DBHelper(this);
        loadData();

        btnAdd.setOnClickListener(v -> {
            dbHelper.insertSV(edtName.getText().toString(), edtClass.getText().toString());
            loadData();
            edtName.setText("");
            edtClass.setText("");
        });

        btnEdit.setOnClickListener(v -> {
            if (selectedId != -1) {
                dbHelper.updateSV(selectedId, edtName.getText().toString(), edtClass.getText().toString());
                loadData();
                edtName.setText("");
                edtClass.setText("");
                selectedId = -1;
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            SinhVien sv = list.get(position);
            edtName.setText(sv.getTen());
            edtClass.setText(sv.getLop());
            selectedId = sv.getId();
        });
    }

    void loadData() {
        list = dbHelper.getAll();
        adapter = new SinhVienAdapter(this, list, id -> {
            dbHelper.deleteSV(id);
            loadData();
        });
        listView.setAdapter(adapter);
    }
}
