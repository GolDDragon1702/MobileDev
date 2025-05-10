package example.qlnv.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import example.qlnv.R;
import example.qlnv.models.HoaDon;

import java.util.List;

public class HoaDonAdapter extends BaseAdapter {
    private Context context;
    private List<HoaDon> list;
    private LayoutInflater inflater;

    public HoaDonAdapter(Context context, List<HoaDon> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public HoaDon getItem(int position) {
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
            convertView = inflater.inflate(R.layout.item_hoadon, parent, false);
            holder = new ViewHolder();
            holder.tvMaHD = convertView.findViewById(R.id.tvMaHD);
            holder.tvNgayBan = convertView.findViewById(R.id.tvNgayBan);
            holder.tvMaNV = convertView.findViewById(R.id.tvMaNV);
            holder.tvMaKH = convertView.findViewById(R.id.tvMaKH);
            holder.tvTongTien = convertView.findViewById(R.id.tvTongTien);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HoaDon hd = getItem(position);
        holder.tvMaHD.setText("Mã HD: " + hd.getMaHD());
        holder.tvNgayBan.setText("Ngày: " + hd.getNgayBan());
        holder.tvMaNV.setText("NV: " + hd.getMaNV());
        holder.tvMaKH.setText("KH: " + hd.getMaKH());
        holder.tvTongTien.setText(String.format("Tổng: %.2f", hd.getTongTien()));

        return convertView;
    }

    private static class ViewHolder {
        TextView tvMaHD, tvNgayBan, tvMaNV, tvMaKH, tvTongTien;
    }
} 