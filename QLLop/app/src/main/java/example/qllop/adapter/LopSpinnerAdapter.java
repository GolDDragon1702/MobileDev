package example.qllop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import example.qllop.R;
import example.qllop.models.Lop;

public class LopSpinnerAdapter extends ArrayAdapter<Lop> {

    private Context context;
    private List<Lop> lopList;
    private LayoutInflater inflater;

    public LopSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Lop> objects) {
        super(context, resource, objects);
        this.context = context;
        this.lopList = objects;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_spinner_lop, parent, false);

            holder = new ViewHolder();
            holder.tvTenLop = convertView.findViewById(R.id.tvSpinnerTenLop);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Lop lop = getItem(position);

        if (lop != null) {
            String displayText = lop.getTenLop();
            if (!lop.getMaLop().equals("ALL")) {
                displayText += " (" + lop.getMaLop() + ")";
            }
            holder.tvTenLop.setText(displayText);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView tvTenLop;
    }
}