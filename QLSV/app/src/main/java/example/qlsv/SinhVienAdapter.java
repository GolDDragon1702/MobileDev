package example.qlsv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {
    Context context;
    List<SinhVien> list;
    LayoutInflater inflater;
    OnDeleteClickListener deleteClickListener;

    public interface OnDeleteClickListener {
        void onDelete(int id);
    }

    public SinhVienAdapter(Context context, List<SinhVien> list, OnDeleteClickListener listener) {
        this.context = context;
        this.list = list;
        this.deleteClickListener = listener;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() { return list.size(); }

    @Override
    public Object getItem(int position) { return list.get(position); }

    @Override
    public long getItemId(int position) { return list.get(position).getId(); }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_sinhvien, null);
        TextView txtName = view.findViewById(R.id.txtName);
        TextView txtClass = view.findViewById(R.id.txtClass);
        ImageView imgDelete = view.findViewById(R.id.imgDelete);

        SinhVien sv = list.get(i);
        txtName.setText(sv.getTen());
        txtClass.setText(sv.getLop());

        imgDelete.setOnClickListener(v -> deleteClickListener.onDelete(sv.getId()));

        return view;
    }
}

