package house.duan.appchitieu.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import house.duan.appchitieu.R;
import house.duan.appchitieu.model.nguoidung;
import house.duan.appchitieu.dao.nguoiDungDAO;


public class DoimatkhauFragment extends Fragment {
    // Khai báo các textView
    EditText edtPassOld, edtPassChange, edtRepass;
    Button btnXacnhan, btnHuy;
    nguoiDungDAO nguoiDungDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doimatkhau, container, false);
        //ánh xạ
        nguoiDungDAO = new nguoiDungDAO(getActivity());
        edtPassOld = view.findViewById(R.id.edtPassOld);
        edtPassChange = view.findViewById(R.id.edtPassChange);
        edtRepass = view.findViewById(R.id.edtRepass);
        btnXacnhan = view.findViewById(R.id.btnXacnhandoi);
        btnHuy = view.findViewById(R.id.btnHuydoi);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtPassOld.setText("");
                edtPassChange.setText("");
                edtRepass.setText("");
            }
        });
        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATA", Context.MODE_PRIVATE);
                String user = sharedPreferences.getString("User", "");
                if (validate() > 0) {
                    nguoidung nguoidung = nguoiDungDAO.getID(user);
                    nguoidung.setMATKHAU(edtPassChange.getText().toString());
                    if (nguoiDungDAO.updatePass(nguoidung) > 0) {
                        Toast.makeText(getContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edtPassOld.setText("");
                        edtPassChange.setText("");
                        edtRepass.setText("");
                    } else {
                        Toast.makeText(getContext(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

    public int validate() {
        int kiemtra = 1;
        if (edtPassOld.length() == 0 || edtPassChange.length() == 0 || edtRepass.length() == 0) {
            edtPassOld.setError("Vui lòng nhập mật khẩu");
            edtPassChange.setError("Vui lòng nhập mật khẩu");
            edtRepass.setError("Vui lòng nhập mật khẩu");
            kiemtra = -1;
        } else {
            //lấy ra text của user, pass
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATA", Context.MODE_PRIVATE);
            String passOld = sharedPreferences.getString("Pass", "");
            String passChange = edtPassChange.getText().toString();
            String repass = edtRepass.getText().toString();
            if (!passOld.equals(edtPassOld.getText().toString())) {
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                kiemtra = -1;
            }
            if (!passChange.equals(repass)) {
                Toast.makeText(getContext(), "Mật khẩu không trùng", Toast.LENGTH_SHORT).show();
                kiemtra = -1;
            }
        }
        return kiemtra;
    }
}
