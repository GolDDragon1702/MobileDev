package com.monhoc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class DiemMonHocAdapter extends ArrayAdapter<DiemMonHoc> {
    private Context context;
    private int layout;
    private List<DiemMonHoc> diemList;

    public DiemMonHocAdapter(Context context, int layout, List<DiemMonHoc> diemList) {
        super(context, layout, diemList);
        this.context = context;
        this.layout = layout;
        this.diemList = diemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout, null);
        }

        ImageView imgMonHoc = convertView.findViewById(R.id.imgMonHoc);
        TextView tvSinhVien = convertView.findViewById(R.id.tvSinhVien);
        TextView tvDiem = convertView.findViewById(R.id.tvDiem);

        DiemMonHoc diem = diemList.get(position);
        tvSinhVien.setText(diem.getSinhVien());
        tvDiem.setText("Điểm: " + diem.getDiem());

        switch (diem.getMonHoc().toLowerCase()) {
            case "lập trình c#":
                imgMonHoc.setImageResource(R.drawable.csharp);
                break;
            case "lập trình php":
                imgMonHoc.setImageResource(R.drawable.php);
                break;
            case "lập trình di động":
                imgMonHoc.setImageResource(R.drawable.mobile);
                break;
        }

        return convertView;
    }
}

