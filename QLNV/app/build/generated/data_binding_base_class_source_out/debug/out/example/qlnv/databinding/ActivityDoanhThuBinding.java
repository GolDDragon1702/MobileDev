// Generated by view binder compiler. Do not edit!
package example.qlnv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import example.qlnv.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDoanhThuBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnHome;

  @NonNull
  public final Button btnThongKe;

  @NonNull
  public final EditText edtDenNgay;

  @NonNull
  public final EditText edtTuNgay;

  @NonNull
  public final ListView lvThongKe;

  @NonNull
  public final TextView tvTongDoanhThu;

  private ActivityDoanhThuBinding(@NonNull LinearLayout rootView, @NonNull Button btnHome,
      @NonNull Button btnThongKe, @NonNull EditText edtDenNgay, @NonNull EditText edtTuNgay,
      @NonNull ListView lvThongKe, @NonNull TextView tvTongDoanhThu) {
    this.rootView = rootView;
    this.btnHome = btnHome;
    this.btnThongKe = btnThongKe;
    this.edtDenNgay = edtDenNgay;
    this.edtTuNgay = edtTuNgay;
    this.lvThongKe = lvThongKe;
    this.tvTongDoanhThu = tvTongDoanhThu;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDoanhThuBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDoanhThuBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_doanh_thu, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDoanhThuBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnHome;
      Button btnHome = ViewBindings.findChildViewById(rootView, id);
      if (btnHome == null) {
        break missingId;
      }

      id = R.id.btnThongKe;
      Button btnThongKe = ViewBindings.findChildViewById(rootView, id);
      if (btnThongKe == null) {
        break missingId;
      }

      id = R.id.edtDenNgay;
      EditText edtDenNgay = ViewBindings.findChildViewById(rootView, id);
      if (edtDenNgay == null) {
        break missingId;
      }

      id = R.id.edtTuNgay;
      EditText edtTuNgay = ViewBindings.findChildViewById(rootView, id);
      if (edtTuNgay == null) {
        break missingId;
      }

      id = R.id.lvThongKe;
      ListView lvThongKe = ViewBindings.findChildViewById(rootView, id);
      if (lvThongKe == null) {
        break missingId;
      }

      id = R.id.tvTongDoanhThu;
      TextView tvTongDoanhThu = ViewBindings.findChildViewById(rootView, id);
      if (tvTongDoanhThu == null) {
        break missingId;
      }

      return new ActivityDoanhThuBinding((LinearLayout) rootView, btnHome, btnThongKe, edtDenNgay,
          edtTuNgay, lvThongKe, tvTongDoanhThu);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
