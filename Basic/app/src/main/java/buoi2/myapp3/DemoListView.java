package buoi2.myapp3;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DemoListView extends AppCompatActivity {

    private ListView listViewPhones;
    private TextView txtSelection;
    private String[] phoneBrands = {"Nokia", "Samsung", "iPhone", "HTC", "BPhone", "MobileStar"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_phones);

        listViewPhones = findViewById(R.id.listViewPhones);
        txtSelection = findViewById(R.id.txtSelection);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, phoneBrands);
        listViewPhones.setAdapter(adapter);

        listViewPhones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = phoneBrands[position];
                txtSelection.setText("Bạn chọn: " + selectedItem);
            }
        });
    }
}
