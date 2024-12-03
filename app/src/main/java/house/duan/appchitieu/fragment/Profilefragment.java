package house.duan.appchitieu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import house.duan.appchitieu.R;

public class Profilefragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize TextView tabs
        TextView tabbieudo = view.findViewById(R.id.tabbieudo);
        TextView tablichsu = view.findViewById(R.id.tablichsu);

        // Set default fragment
        getChildFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer1, new bieudofragment())
                .commit();

        // Handle click events
        tabbieudo.setOnClickListener(v -> getChildFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer1, new bieudofragment())
                .commit());

        tablichsu.setOnClickListener(v -> getChildFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer1, new lichsufragment())
                .commit());

        return view;
    }
}
