package listview.players;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {
    private List<Film> filmList;
    private Context context;

    public FilmAdapter(Context context, List<Film> filmList) {
        this.context = context;
        this.filmList = filmList;
    }

    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_film, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        Film film = filmList.get(position);
        holder.title.setText(film.title);
        holder.description.setText(film.description);
        holder.image.setImageResource(film.imageResId);

        holder.itemView.setOnClickListener(v -> showEditDialog(film, position));
    }

    private void showEditDialog(Film film, int position) {
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_film, null);
        EditText titleEdit = dialogView.findViewById(R.id.editTitle);
        EditText descEdit = dialogView.findViewById(R.id.editDescription);
        RatingBar ratingBar = dialogView.findViewById(R.id.ratingBar);

        titleEdit.setText(film.title);
        descEdit.setText(film.description);
        ratingBar.setRating(film.rating);

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("Edit Films")
                .setView(dialogView)
                .setPositiveButton("OK", (d, which) -> {
                    film.title = titleEdit.getText().toString();
                    film.description = descEdit.getText().toString();
                    film.rating = (int) ratingBar.getRating();
                    notifyItemChanged(position);
                })
                .setNegativeButton("CANCEL", null)
                .create();

        dialog.show();
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public static class FilmViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView image;

        public FilmViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.filmTitle);
            description = itemView.findViewById(R.id.filmDescription);
            image = itemView.findViewById(R.id.filmImage);
        }
    }
}

