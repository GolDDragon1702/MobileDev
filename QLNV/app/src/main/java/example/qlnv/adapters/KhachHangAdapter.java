package example.qlnv.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import example.qlnv.R;
import example.qlnv.models.KhachHang;

import java.util.List;

public class KhachHangAdapter extends BaseAdapter {
    private Context context;
    private List<KhachHang> list;
    private LayoutInflater inflater;

    public KhachHangAdapter(Context context, List<KhachHang> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public KhachHang getItem(int position) {
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
            convertView = inflater.inflate(R.layout.item_khachhang, parent, false);
            holder = new ViewHolder();
            holder.tvMaKH = convertView.findViewById(R.id.tvMaKH);
            holder.tvHoTen = convertView.findViewById(R.id.tvHoTen);
            holder.tvSDT = convertView.findViewById(R.id.tvSDT);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        KhachHang kh = getItem(position);
        holder.tvMaKH.setText("Mã: " + kh.getMaKH());
        holder.tvHoTen.setText("Tên: " + kh.getHoTen());
        holder.tvSDT.setText("SĐT: " + kh.getDienThoai());

        return convertView;
    }

    private static class ViewHolder {
        TextView tvMaKH, tvHoTen, tvSDT;
    }
}
