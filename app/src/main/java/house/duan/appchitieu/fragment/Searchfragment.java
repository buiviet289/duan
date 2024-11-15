//package house.duan.appchitieu.fragment;
//
//
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import house.duan.appchitieu.R;
//
//public class Searchfragment extends Fragment {
//
//        @Nullable
//        @Override
//        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            // Inflate layout for this fragment
//            View view = inflater.inflate(R.layout.fragment_search, container, false);
//
//            return view;
//        }
////
//
//
//    }
package house.duan.appchitieu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import house.duan.appchitieu.R;

public class Searchfragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Initialize TextView tabs
        TextView tabChiTieu = view.findViewById(R.id.tabChiTieu);
        TextView tabThuNhap = view.findViewById(R.id.tabThuNhap);

        // Set initial fragment to "Chi tiêu"
        getChildFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new ExpenseFragment())
                .commit();

        // Handle click on "Chi tiêu" tab
        tabChiTieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new ExpenseFragment())
                        .commit();
            }
        });

        // Handle click on "Thu nhập" tab
        tabThuNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new IncomeFragment())
                        .commit();
            }
        });

        return view;
    }
}


