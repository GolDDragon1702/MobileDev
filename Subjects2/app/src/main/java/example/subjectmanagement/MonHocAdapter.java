package example.subjectmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MonHocAdapter extends ArrayAdapter<MonHoc> {
    private Context context;
    private int resource;
    private List<MonHoc> monHocList;

    public MonHocAdapter(Context context, int resource, List<MonHoc> monHocList) {
        super(context, resource, monHocList);
        this.context = context;
        this.resource = resource;
        this.monHocList = monHocList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MonHoc monHoc = monHocList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        TextView txtMaMH = convertView.findViewById(R.id.txtMaMH);
        TextView txtTenMH = convertView.findViewById(R.id.txtTenMH);
        TextView txtSoTiet = convertView.findViewById(R.id.txtSoTiet);

        txtMaMH.setText(String.format("Mã MH: %d", monHoc.getMaMH()));
        txtTenMH.setText(String.format("Tên MH: %s", monHoc.getTenMH()));
        txtSoTiet.setText(String.format("Số Tiết: %d", monHoc.getSoTiet()));

        return convertView;
    }
}
