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
import example.qllop.dao.LopDAO;
import example.qllop.database.DatabaseHelper;
import example.qllop.models.Lop;
import example.qllop.models.SinhVien;


public class SinhVienAdapter extends ArrayAdapter<SinhVien> {

    private Context context;
    private List<SinhVien> sinhVienList;
    private LayoutInflater inflater;
    private LopDAO lopDAO;

    public SinhVienAdapter(@NonNull Context context, int resource, @NonNull List<SinhVien> objects) {
        super(context, resource, objects);
        this.context = context;
        this.sinhVienList = objects;
        this.inflater = LayoutInflater.from(context);
        this.lopDAO = new LopDAO(DatabaseHelper.getInstance(context));
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_sinh_vien, parent, false);

            holder = new ViewHolder();
            holder.tvMSSV = convertView.findViewById(R.id.tvMSSV);
            holder.tvHoTen = convertView.findViewById(R.id.tvHoTen);
            holder.tvNgaySinh = convertView.findViewById(R.id.tvNgaySinh);
            holder.tvLop = convertView.findViewById(R.id.tvLop);
            holder.tvDiaChi = convertView.findViewById(R.id.tvDiaChi);
            holder.tvDienThoai = convertView.findViewById(R.id.tvDienThoai);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SinhVien sinhVien = getItem(position);

        if (sinhVien != null) {
            holder.tvMSSV.setText("MSSV: " + sinhVien.getMssv());
            holder.tvHoTen.setText("Họ tên: " + sinhVien.getHoTen());
            holder.tvNgaySinh.setText("Ngày sinh: " + sinhVien.getNgaySinhString());

            // Lấy tên lớp từ mã lớp
            String tenLop = "Lớp: " + sinhVien.getMaLop();
            Lop lop = lopDAO.getLopById(sinhVien.getMaLop());
            if (lop != null) {
                tenLop = "Lớp: " + lop.getTenLop() + " (" + sinhVien.getMaLop() + ")";
            }
            holder.tvLop.setText(tenLop);

            holder.tvDiaChi.setText("Địa chỉ: " + sinhVien.getDiaChi());
            holder.tvDienThoai.setText("SĐT: " + sinhVien.getDienThoai());
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Nullable
    @Override
    public SinhVien getItem(int position) {
        return sinhVienList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        TextView tvMSSV;
        TextView tvHoTen;
        TextView tvNgaySinh;
        TextView tvLop;
        TextView tvDiaChi;
        TextView tvDienThoai;
    }
}