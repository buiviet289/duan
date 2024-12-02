package house.duan.appchitieu.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import house.duan.appchitieu.R;

public class SettingsFragment extends Fragment {
    private Switch switchDarkMode;
    private Spinner spinnerLanguage;
//    private Button btnPersonalInfo, btnChangePassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        // Ánh xạ view
        switchDarkMode = view.findViewById(R.id.switch_dark_mode);
        spinnerLanguage = view.findViewById(R.id.spinner_language);

        // Dark mode toggle
        SharedPreferences prefs = requireContext().getSharedPreferences("Settings", requireContext().MODE_PRIVATE);
        boolean isDarkMode = prefs.getBoolean("dark_mode", false);
        switchDarkMode.setChecked(isDarkMode);
        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("dark_mode", isChecked);
            editor.apply();
            AppCompatDelegate.setDefaultNightMode(isChecked
                    ? AppCompatDelegate.MODE_NIGHT_YES
                    : AppCompatDelegate.MODE_NIGHT_NO);
        });

        // Language spinner
        String[] languages = {"Tiếng Việt", "English"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item, languages);
        spinnerLanguage.setAdapter(adapter);
        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLanguage = languages[position];
                // Đổi ngôn ngữ (cần tích hợp logic thay đổi ngôn ngữ)
                Toast.makeText(requireContext(), "Đã chọn " + selectedLanguage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

//        // Personal Info Button
//        btnPersonalInfo.setOnClickListener(v -> {
//            // Chuyển đến trang thông tin cá nhân
//            Toast.makeText(requireContext(), "Mở trang thông tin cá nhân", Toast.LENGTH_SHORT).show();
//        });
//
//        // Change Password Button
//        btnChangePassword.setOnClickListener(v -> {
//            // Chuyển đến trang đổi mật khẩu
//            Toast.makeText(requireContext(), "Mở trang đổi mật khẩu", Toast.LENGTH_SHORT).show();
//        });

        return view;
    }
}
