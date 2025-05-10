package example.qlnv.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import example.qlnv.R;
import example.qlnv.models.ThongKe;

import java.util.List;

public class ThongKeAdapter extends BaseAdapter {
    private Context context;
    private List<ThongKe> list;
    private LayoutInflater inflater;

    public ThongKeAdapter(Context context, List<ThongKe> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ThongKe getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_thongke, parent, false);
            holder = new ViewHolder();
            holder.tvMaSP = convertView.findViewById(R.id.tvMaSP);
            holder.tvSoLuong = convertView.findViewById(R.id.tvSoLuong);
            holder.tvGiaBan = convertView.findViewById(R.id.tvGiaBan);
            holder.tvTongTien = convertView.findViewById(R.id.tvTongTien);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ThongKe tk = getItem(position);
        holder.tvMaSP.setText("Mã SP: " + tk.getMaSP());
        holder.tvSoLuong.setText("SL: " + tk.getSoLuong());
        holder.tvGiaBan.setText(String.format("Giá: %.2f", tk.getGiaBan()));
        holder.tvTongTien.setText(String.format("Tổng: %.2f", tk.getTongTien()));

        return convertView;
    }

    private static class ViewHolder {
        TextView tvMaSP, tvSoLuong, tvGiaBan, tvTongTien;
    }
} 