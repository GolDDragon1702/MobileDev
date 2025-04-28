package listview.players;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity1 extends AppCompatActivity {
    private ListView listView;
    private List<Country> countries;
    private CountryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        listView = findViewById(R.id.listCountries);

        countries = new ArrayList<>();
        countries.add(new Country("VN", 1000, R.drawable.vnflag));
        countries.add(new Country("USA", 3000, R.drawable.usflag));
        countries.add(new Country("RU", 5000, R.drawable.ruflag));

        adapter = new CountryAdapter(this, countries);
        listView.setAdapter(adapter);
    }
}