package com.monhoc;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtNgay, edtHeSo, edtDiem;
    private Spinner spMonHoc, spSinhVien;
    private Button btnThem, btnXoa, btnSua, btnClear;
    private ListView lvDiem;
    private ArrayList<DiemMonHoc> listDiem;
    private DiemMonHocAdapter adapter;
    private int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setEventHandlers();
    }

    private void initializeViews() {
        edtNgay = findViewById(R.id.edtNgayCapNhat);
        edtHeSo = findViewById(R.id.edtHeSo);
        edtDiem = findViewById(R.id.edtDiem);
        spMonHoc = findViewById(R.id.spMonHoc);
        spSinhVien = findViewById(R.id.spSinhVien);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnClear = findViewById(R.id.btnClear);
        lvDiem = findViewById(R.id.lvDiem);

        ArrayAdapter<CharSequence> adapterMon = ArrayAdapter.createFromResource(this,
                R.array.spList1, android.R.layout.simple_spinner_item);
        adapterMon.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMonHoc.setAdapter(adapterMon);

        ArrayAdapter<CharSequence> adapterSV = ArrayAdapter.createFromResource(this,
                R.array.spList2, android.R.layout.simple_spinner_item);
        adapterSV.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSinhVien.setAdapter(adapterSV);

        listDiem = new ArrayList<>();
        adapter = new DiemMonHocAdapter(this, R.layout.item_diem_mon_hoc, listDiem);
        lvDiem.setAdapter(adapter);
    }

    private void setEventHandlers() {
        setButtonThemHandler();
        setButtonXoaHandler();
        setButtonSuaHandler();
        setButtonClearHandler();
        setListViewItemClickHandler();
    }

    private void setButtonThemHandler() {
        btnThem.setOnClickListener(v -> {
            if (validateInput()) {
                addDiem();
                Toast.makeText(this, "Đã thêm điểm môn học!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setButtonXoaHandler() {
        btnXoa.setOnClickListener(v -> {
            if (selectedIndex >= 0 && selectedIndex < listDiem.size()) {
                deleteDiem();
                Toast.makeText(this, "Đã xóa điểm môn học!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Chưa chọn điểm để xóa!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setButtonSuaHandler() {
        btnSua.setOnClickListener(v -> {
            if (selectedIndex >= 0 && selectedIndex < listDiem.size()) {
                if (validateInput()) {
                    updateDiem();
                    Toast.makeText(this, "Đã cập nhật điểm môn học!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Chưa chọn điểm để sửa!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setButtonClearHandler() {
        btnClear.setOnClickListener(v -> clearInputFields());
    }

    private void setListViewItemClickHandler() {
        lvDiem.setOnItemClickListener((parent, view, position, id) -> selectDiem(position));
    }

    private boolean validateInput() {
        if (edtNgay.getText().toString().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập ngày cập nhật!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edtHeSo.getText().toString().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập hệ số!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edtDiem.getText().toString().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập điểm!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void addDiem() {
        DiemMonHoc diem = new DiemMonHoc(
                edtNgay.getText().toString(),
                edtHeSo.getText().toString(),
                edtDiem.getText().toString(),
                spMonHoc.getSelectedItem().toString(),
                spSinhVien.getSelectedItem().toString()
        );
        listDiem.add(diem);
        adapter.notifyDataSetChanged();
        clearInputFields();
    }

    private void deleteDiem() {
        if (selectedIndex >= 0 && selectedIndex < listDiem.size()) {
            listDiem.remove(selectedIndex);
            adapter.notifyDataSetChanged();
            clearInputFields();
            selectedIndex = -1;
        }
    }

    private void updateDiem() {
        if (selectedIndex >= 0 && selectedIndex < listDiem.size()) {
            DiemMonHoc diem = listDiem.get(selectedIndex);
            diem.setNgayCapNhat(edtNgay.getText().toString());
            diem.setHeSo(edtHeSo.getText().toString());
            diem.setDiem(edtDiem.getText().toString());
            diem.setMonHoc(spMonHoc.getSelectedItem().toString());
            diem.setSinhVien(spSinhVien.getSelectedItem().toString());
            adapter.notifyDataSetChanged();
            clearInputFields();
        }
    }

    private void selectDiem(int position) {
        DiemMonHoc diem = listDiem.get(position);
        edtNgay.setText(diem.getNgayCapNhat());
        edtHeSo.setText(diem.getHeSo());
        edtDiem.setText(diem.getDiem());

        setSpinnerSelection(spMonHoc, diem.getMonHoc());
        setSpinnerSelection(spSinhVien, diem.getSinhVien());

        selectedIndex = position;
    }

    private void clearInputFields() {
        edtNgay.setText("");
        edtHeSo.setText("");
        edtDiem.setText("");
        spMonHoc.setSelection(0);
        spSinhVien.setSelection(0);
        edtNgay.requestFocus();
    }

    private void setSpinnerSelection(Spinner spinner, String value) {
        ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            if (value.equals(adapter.getItem(i).toString())) {
                spinner.setSelection(i);
                break;
            }
        }
    }
}
