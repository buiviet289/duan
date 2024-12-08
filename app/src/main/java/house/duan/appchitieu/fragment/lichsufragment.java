package house.duan.appchitieu.fragment;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import house.duan.appchitieu.R;
import house.duan.appchitieu.adapter.chiTieuAdapter;
import house.duan.appchitieu.model.SharedViewModel;
import house.duan.appchitieu.model.chiTieu;

public class lichsufragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<chiTieu> historyList = new ArrayList<>();
    ArrayList<chiTieu> filteredList = new ArrayList<>();
    TextView fromDateTextView, toDateTextView;
    Button fromDateButton, toDateButton, filterButton;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lichsu, container, false);

        recyclerView = view.findViewById(R.id.recycler_viewHistory);
        fromDateTextView = view.findViewById(R.id.txt_from_date);
        toDateTextView = view.findViewById(R.id.txt_to_date);
        fromDateButton = view.findViewById(R.id.btn_from_date);
        toDateButton = view.findViewById(R.id.btn_to_date);
        filterButton = view.findViewById(R.id.btn_filter);

        // Get the shared view model
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Lấy dữ liệu từ SharedViewModel
        loadHistoryData();

        // Sự kiện chọn ngày
        fromDateButton.setOnClickListener(v -> showDatePicker(fromDateTextView));
        toDateButton.setOnClickListener(v -> showDatePicker(toDateTextView));

        // Sự kiện lọc dữ liệu
        filterButton.setOnClickListener(v -> filterData());

        return view;
    }

    private void loadHistoryData() {
        // Lấy dữ liệu từ SharedViewModel
        ArrayList<chiTieu> list = sharedViewModel.getExpenses().getValue();
        if (list != null) {
            historyList.addAll(list);
        } else {
            Toast.makeText(getContext(), "Không có dữ liệu trong lịch sử!", Toast.LENGTH_SHORT).show();
        }

        // Hiển thị danh sách ban đầu
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chiTieuAdapter adapter = new chiTieuAdapter(getContext(), historyList, null);
        recyclerView.setAdapter(adapter);
    }

    private void showDatePicker(TextView textView) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view, year, month, dayOfMonth) -> {
            String selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, dayOfMonth);
            textView.setText(selectedDate);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void filterData() {
        String fromDateStr = fromDateTextView.getText().toString();
        String toDateStr = toDateTextView.getText().toString();

        if (fromDateStr.isEmpty() || toDateStr.isEmpty() || fromDateStr.equals("--/--/----") || toDateStr.equals("--/--/----")) {
            Toast.makeText(getContext(), "Vui lòng chọn cả hai ngày!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Date fromDate = dateFormat.parse(fromDateStr);
            Date toDate = dateFormat.parse(toDateStr);

            if (fromDate != null && toDate != null) {
                filteredList.clear();
                for (chiTieu item : historyList) {
                    Date itemDate = dateFormat.parse(item.getDate());
                    if (itemDate != null && itemDate.compareTo(fromDate) >= 0 && itemDate.compareTo(toDate) <= 0) {
                        filteredList.add(item);
                    }
                }

                // Hiển thị danh sách lọc
                chiTieuAdapter adapter = new chiTieuAdapter(getContext(), filteredList, null);
                recyclerView.setAdapter(adapter);

                // Thông báo cho người dùng
                Toast.makeText(getContext(), "Đã lọc dữ liệu!", Toast.LENGTH_SHORT).show();

                // Lưu dữ liệu lọc vào SharedPreferences
                saveFilteredData(fromDateStr, toDateStr);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Định dạng ngày không hợp lệ!", Toast.LENGTH_SHORT).show();
        }
    }


    private void saveFilteredData(String fromDateStr, String toDateStr) {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("HistoryData", getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("fromDate", fromDateStr);
        editor.putString("toDate", toDateStr);
        editor.apply();
    }
}
