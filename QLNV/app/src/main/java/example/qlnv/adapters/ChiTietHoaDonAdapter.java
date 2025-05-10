package example.qlnv.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import example.qlnv.R;
import example.qlnv.models.ChiTietHoaDon;

import java.util.List;

public class ChiTietHoaDonAdapter extends BaseAdapter {
    private Context context;
    private List<ChiTietHoaDon> list;
    private LayoutInflater inflater;

    public ChiTietHoaDonAdapter(Context context, List<ChiTietHoaDon> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ChiTietHoaDon getItem(int position) {
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
            convertView = inflater.inflate(R.layout.item_chitiethoadon, parent, false);
            holder = new ViewHolder();
            holder.tvMaHD = convertView.findViewById(R.id.tvMaHD);
            holder.tvMaSP = convertView.findViewById(R.id.tvMaSP);
            holder.tvGiaBan = convertView.findViewById(R.id.tvGiaBan);
            holder.tvSoLuong = convertView.findViewById(R.id.tvSoLuong);
            holder.tvTongTien = convertView.findViewById(R.id.tvTongTien);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ChiTietHoaDon cthd = getItem(position);
        holder.tvMaHD.setText("Mã HD: " + cthd.getMaHD());
        holder.tvMaSP.setText("Mã SP: " + cthd.getMaSP());
        holder.tvGiaBan.setText(String.format("Giá: %.2f", cthd.getGiaBan()));
        holder.tvSoLuong.setText("SL: " + cthd.getSoLuong());
        holder.tvTongTien.setText(String.format("Tổng: %.2f", cthd.getTongTien()));

        return convertView;
    }

    private static class ViewHolder {
        TextView tvMaHD, tvMaSP, tvGiaBan, tvSoLuong, tvTongTien;
    }
} 