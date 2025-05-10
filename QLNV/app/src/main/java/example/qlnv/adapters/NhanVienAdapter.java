package example.qlnv.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import example.qlnv.R;
import example.qlnv.models.NhanVien;

import java.util.List;

public class NhanVienAdapter extends BaseAdapter {
    private Context context;
    private List<NhanVien> list;
    private LayoutInflater inflater;

    public NhanVienAdapter(Context context, List<NhanVien> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public NhanVien getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position; // hoặc bạn có thể trả về mã nhân viên nếu muốn
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_nhanvien, parent, false);
            holder = new ViewHolder();
            holder.tvMaNV = convertView.findViewById(R.id.tvMaNV);
            holder.tvHoTen = convertView.findViewById(R.id.tvHoTen);
            holder.tvSDT = convertView.findViewById(R.id.tvSDT);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NhanVien nv = getItem(position);
        holder.tvMaNV.setText("Mã: " + nv.getMaNV());
        holder.tvHoTen.setText("Tên: " + nv.getHoTen());
        holder.tvSDT.setText("SĐT: " + nv.getDienThoai());

        return convertView;
    }

    private static class ViewHolder {
        TextView tvMaNV, tvHoTen, tvSDT;
    }
}
