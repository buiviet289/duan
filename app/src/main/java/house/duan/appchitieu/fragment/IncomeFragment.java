package house.duan.appchitieu.fragment;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import house.duan.appchitieu.R;
import house.duan.appchitieu.adapter.adapterthunhap;
import house.duan.appchitieu.adapter.adaterchitieu;



import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import house.duan.appchitieu.R;

public class IncomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Dữ liệu mẫu
        List<String> categoryList = new ArrayList<>();
        categoryList.add("Ăn uống");
        categoryList.add("Quần áo");
        categoryList.add("Mua sắm");
        categoryList.add("ngân hàng");

        // Gán Adapter vào RecyclerView
        adapterthunhap adapter = new adapterthunhap(categoryList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}

