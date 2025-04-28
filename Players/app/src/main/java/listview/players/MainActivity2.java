package listview.players;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private ListView listView;
    private List<Player> players;
    private PlayerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = findViewById(R.id.listViewPlayers);

        players = new ArrayList<>();
        players.add(new Player("Xuan Truong", "28/4/1995", 23, R.drawable.xuantruong, R.drawable.vnflag));
        players.add(new Player("David Beckham", "2/5/1975", 43, R.drawable.beckham, R.drawable.vnflag));
        players.add(new Player("Cris Ronaldo", "5/2/1985", 34, R.drawable.mbappe, R.drawable.vnflag));
        players.add(new Player("Kylian Mbappe", "20/12/1998", 20, R.drawable.ronaldo, R.drawable.vnflag));

        adapter = new PlayerAdapter(this, players);
        listView.setAdapter(adapter);
    }
}