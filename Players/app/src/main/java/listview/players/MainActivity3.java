package listview.players;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    RecyclerView recyclerView;
    FilmAdapter adapter;
    List<Film> films;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        recyclerView = findViewById(R.id.filmRecyclerView);

        films = new ArrayList<>();
        films.add(new Film("Chú chó nhỏ", "Bichon là chú chó thông minh, vui vẻ...", R.drawable.puppy, 2));
        films.add(new Film("Chú mèo to", "Sau khi chủ nhân ban đầu qua đời, Bronson...", R.drawable.kitten, 3));

        adapter = new FilmAdapter(this, films);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

