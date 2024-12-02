package house.duan.appchitieu.fragment;
import android.app.AlertDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import house.duan.appchitieu.R;
import house.duan.appchitieu.adapter.chiTieuAdapter;
import house.duan.appchitieu.dao.chiTieuDAO;
import house.duan.appchitieu.model.chiTieu;

public class Homefragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<chiTieu> list;
    chiTieuDAO chiTieuDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycler_viewChitieu);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        chiTieuDAO = new chiTieuDAO(getContext());
        loadData();
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floadAdd);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialogadd();
            }
        });
        return view;
    }

    private void loadData(){
        ArrayList<chiTieu> list = (ArrayList<chiTieu>) chiTieuDAO.getAllItems();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        chiTieuAdapter chiTieuAdapter = new chiTieuAdapter(getContext(), list, chiTieuDAO);
        recyclerView.setAdapter(chiTieuAdapter);
    }

    private void showdialogadd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogadd, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


        // xử lý chức năng
        EditText edtten = view.findViewById(R.id.edt_ten);
        EditText edtgia = view.findViewById(R.id.edt_gia);
        EditText edtgichu = view.findViewById(R.id.edt_ghichu);
        Button btnThem = view.findViewById(R.id.btn_add);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtten.getText().toString();
                String gia = edtgia.getText().toString();
                String ghichu = edtgichu.getText().toString();

                if(ten.length() == 0 || gia.length() == 0 || ghichu.length() == 0){
                    Toast.makeText(getContext(), "Nhập Đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    chiTieu chiTieu = new chiTieu(ten, Double.parseDouble(gia), ghichu);
                    boolean check = chiTieuDAO.insertItem(chiTieu);
                    if(check){
                        Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        loadData();
                        alertDialog.dismiss();
                    }else{
                        Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}

