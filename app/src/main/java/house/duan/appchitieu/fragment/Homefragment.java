package house.duan.appchitieu.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

import house.duan.appchitieu.R;
import house.duan.appchitieu.adapter.chiTieuAdapter;
import house.duan.appchitieu.dao.chiTieuDAO;
import house.duan.appchitieu.model.SharedViewModel;
import house.duan.appchitieu.model.chiTieu;

public class Homefragment extends Fragment {
    RecyclerView recyclerView;
    TextView tongTienTextView;
    ArrayList<chiTieu> list;
    chiTieuDAO chiTieuDAO;

    SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycler_viewChitieu);
        tongTienTextView = view.findViewById(R.id.textchitieu);

        // Khởi tạo ViewModel
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Khởi tạo RecyclerView và hiển thị danh sách chi tiêu
        chiTieuDAO = new chiTieuDAO(getContext());
        loadData();

        FloatingActionButton floatingActionButton = view.findViewById(R.id.floadAdd);
        floatingActionButton.setOnClickListener(v -> showdialogadd());

        return view;
    }

    private void loadData() {
        ArrayList<chiTieu> list = (ArrayList<chiTieu>) chiTieuDAO.getAllItems();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chiTieuAdapter chiTieuAdapter = new chiTieuAdapter(getContext(), list, chiTieuDAO);
        recyclerView.setAdapter(chiTieuAdapter);

        // Tính tổng tiền và hiển thị
        double tongTien = 0;
        for (chiTieu chiTieu : list) {
            tongTien += chiTieu.getPrice();
        }
        tongTienTextView.setText(String.format("Tổng chi tiêu: %.2f VNĐ", tongTien));

        // Cập nhật dữ liệu vào SharedViewModel
        sharedViewModel.setExpenses(list);
    }

    private void showdialogadd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogadd, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // Khởi tạo các trường nhập liệu trong dialog
        EditText nameEditText = view.findViewById(R.id.edt_ten);
        EditText priceEditText = view.findViewById(R.id.edt_gia);
        EditText noteEditText = view.findViewById(R.id.edt_ghichu);
        TextView dateTextView = view.findViewById(R.id.txt_date);
        Button saveButton = view.findViewById(R.id.btn_add);

        // Khởi tạo biến lưu ngày được chọn
        final String[] selectedDate = {""};

        // Sự kiện cho TextView date để mở DatePickerDialog
        dateTextView.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view1, year1, month1, dayOfMonth1) -> {
                // Cập nhật ngày chọn vào TextView
                selectedDate[0] = String.format("%d-%02d-%02d", year1, month1 + 1, dayOfMonth1);
                dateTextView.setText(selectedDate[0]);
            }, year, month, dayOfMonth);

            datePickerDialog.show();
        });

        // Xử lý lưu chi tiêu
        saveButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String priceString = priceEditText.getText().toString();
            String note = noteEditText.getText().toString();

            // Kiểm tra thông tin nhập vào
            if (name.isEmpty() || priceString.isEmpty() || selectedDate[0].isEmpty()) {
                Toast.makeText(getContext(), "Vui lòng điền đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            double price = Double.parseDouble(priceString);
            chiTieu newChiTieu = new chiTieu(name, price, note, selectedDate[0]);

            // Lưu vào cơ sở dữ liệu
            boolean success = chiTieuDAO.insertItem(newChiTieu);
            if (success) {
                Toast.makeText(getContext(), "Chi tiêu đã được thêm!", Toast.LENGTH_SHORT).show();
                loadData(); // Cập nhật lại RecyclerView
                alertDialog.dismiss(); // Đóng dialog
            } else {
                Toast.makeText(getContext(), "Thêm chi tiêu thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
