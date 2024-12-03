package house.duan.appchitieu.fragment;
import android.app.AlertDialog;
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


    private void updatePieChart(ArrayList<chiTieu> data) {
        // Tạo instance của ProfileFragment
        Profilefragment profileFragment = new Profilefragment();

        // Tạo Bundle để truyền dữ liệu
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("categorizedData", data);  // Truyền dữ liệu chi tiêu không phân loại

        // Đặt dữ liệu vào ProfileFragment
        profileFragment.setArguments(bundle);

        // Thực hiện giao dịch fragment
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, profileFragment)  // Đảm bảo ID đúng
                .addToBackStack(null)
                .commit();
    }


    private void showdialogadd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogadd, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();





    }

}

