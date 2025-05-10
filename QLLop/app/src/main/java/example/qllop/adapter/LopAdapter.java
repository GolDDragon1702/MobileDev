package example.qllop.adapter;

import example.qllop.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import example.qllop.models.Lop;

public class LopAdapter extends ArrayAdapter<Lop> {

    private Context context;
    private List<Lop> lopList;
    private LayoutInflater inflater;

    public LopAdapter(@NonNull Context context, int resource, @NonNull List<Lop> objects) {
        super(context, resource, objects);
        this.context = context;
        this.lopList = objects;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_lop, parent, false);

            holder = new ViewHolder();
            holder.tvMaLop = convertView.findViewById(R.id.tvMaLop);
            holder.tvTenLop = convertView.findViewById(R.id.tvTenLop);
            holder.tvSiSo = convertView.findViewById(R.id.tvSiSo);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Lop lop = getItem(position);

        if (lop != null) {
            holder.tvMaLop.setText("Mã lớp: " + lop.getMaLop());
            holder.tvTenLop.setText("Tên lớp: " + lop.getTenLop());
            holder.tvSiSo.setText("Sĩ số: " + lop.getSiSo());
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return lopList.size();
    }

    @Nullable
    @Override
    public Lop getItem(int position) {
        return lopList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        TextView tvMaLop;
        TextView tvTenLop;
        TextView tvSiSo;
    }
}