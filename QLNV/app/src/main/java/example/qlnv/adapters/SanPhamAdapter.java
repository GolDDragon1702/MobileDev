package example.qlnv.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import example.qlnv.R;
import example.qlnv.models.SanPham;

public class SanPhamAdapter extends BaseAdapter {
    private Context context;
    private List<SanPham> list;
    private LayoutInflater inflater;

    public SanPhamAdapter(Context context, List<SanPham> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
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
            convertView = inflater.inflate(R.layout.item_sanpham, parent, false);
            holder = new ViewHolder();
            holder.tvMaSP = convertView.findViewById(R.id.tvMaSP);
            holder.tvTenSP = convertView.findViewById(R.id.tvTenSP);
            holder.tvNgaySX = convertView.findViewById(R.id.tvNgaySX);
            holder.tvGiaBan = convertView.findViewById(R.id.tvGiaBan);
            holder.tvSoLuong = convertView.findViewById(R.id.tvSoLuong);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SanPham sanPham = list.get(position);
        holder.tvMaSP.setText("Mã SP: " + sanPham.getMaSP());
        holder.tvTenSP.setText("Tên SP: " + sanPham.getTenSP());
        holder.tvNgaySX.setText("NSX: " + sanPham.getNgaySanXuat());
        holder.tvGiaBan.setText("Giá: " + String.format("%,.0f", sanPham.getGiaBan()) + "đ");
        holder.tvSoLuong.setText("SL: " + sanPham.getSoLuong());

        return convertView;
    }

    private static class ViewHolder {
        TextView tvMaSP;
        TextView tvTenSP;
        TextView tvNgaySX;
        TextView tvGiaBan;
        TextView tvSoLuong;
    }
}
