package listview.players;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PlayerAdapter extends ArrayAdapter<Player> {
    private Context context;
    private List<Player> players;

    public PlayerAdapter(Context context, List<Player> players) {
        super(context, 0, players);
        this.context = context;
        this.players = players;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Player player = players.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.player_item, parent, false);
        }

        ImageView imagePlayer = convertView.findViewById(R.id.imagePlayer);
        ImageView imageFlag = convertView.findViewById(R.id.imageFlag);
        TextView textName = convertView.findViewById(R.id.textName);
        TextView textBirthday = convertView.findViewById(R.id.textBirthday);

        imagePlayer.setImageResource(player.getImageResId());
        imageFlag.setImageResource(player.getFlagResId());
        textName.setText(player.getName());
        textBirthday.setText(player.getBirthday() + " (" + player.getAge() + " age)");

        convertView.setOnClickListener(v -> showEditDialog(player));
        convertView.setOnLongClickListener(v -> {
            showDeleteDialog(player);
            return true;
        });

        return convertView;
    }

    private void showEditDialog(Player player) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Players");

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_player, null);
        EditText editName = view.findViewById(R.id.editName);
        EditText editAge = view.findViewById(R.id.editAge);

        editName.setText(player.getName());
        editAge.setText(String.valueOf(player.getAge()));

        builder.setView(view);
        builder.setPositiveButton("OK", (dialog, which) -> {
            player.setName(editName.getText().toString());
            player.setAge(Integer.parseInt(editAge.getText().toString()));
            notifyDataSetChanged();
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void showDeleteDialog(Player player) {
        new AlertDialog.Builder(context)
                .setTitle("DELETE PLAYER")
                .setMessage("Are you sure to delete this player?")
                .setPositiveButton("YES", (dialog, which) -> {
                    players.remove(player);
                    notifyDataSetChanged();
                })
                .setNegativeButton("NO", null)
                .show();
    }
}
