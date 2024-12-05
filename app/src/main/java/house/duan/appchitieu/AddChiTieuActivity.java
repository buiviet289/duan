package house.duan.appchitieu;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import house.duan.appchitieu.dao.chiTieuDAO;
import house.duan.appchitieu.model.chiTieu;

public class AddChiTieuActivity extends AppCompatActivity {

    private EditText edtTen, edtGia, edtGhiChu;
    private TextView txtDate;
    private Button btnAdd, btnPickDate;
    private String selectedDate;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private chiTieuDAO chiTieuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogadd);  // Đảm bảo đây là layout đúng

        // Ánh xạ các views
        edtTen = findViewById(R.id.edt_ten);
        edtGia = findViewById(R.id.edt_gia);
        edtGhiChu = findViewById(R.id.edt_ghichu);
        txtDate = findViewById(R.id.txt_date); // TextView để hiển thị ngày
        btnAdd = findViewById(R.id.btn_add);

        chiTieuDAO = new chiTieuDAO(this);

        // Khởi tạo Calendar và định dạng ngày
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Gán ngày hiện tại cho biến `selectedDate` và cập nhật giao diện
        selectedDate = dateFormat.format(calendar.getTime());
        updateDateLabel();
        txtDate.setOnClickListener(v -> {
            Toast.makeText(this, "Đã nhấn vào TextView để chọn ngày", Toast.LENGTH_SHORT).show();
            showDatePickerDialog();
        });


        // Gắn sự kiện khi người dùng nhấn vào TextView
        txtDate.setOnClickListener(v -> showDatePickerDialog());

        // Gắn sự kiện cho nút thêm
        btnAdd.setOnClickListener(v -> addChiTieu());
    }


    // Cập nhật TextView để hiển thị ngày
    private void updateDateLabel() {
        Log.d("UpdateDate", "Ngày được cập nhật: " + selectedDate);
        txtDate.setText("Ngày: " + selectedDate);
    }



    // Hiển thị DatePickerDialog để chọn ngày
    private void showDatePickerDialog() {
        new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    // Cập nhật ngày được chọn
                    selectedDate = dateFormat.format(calendar.getTime());

                    // Gọi phương thức để cập nhật giao diện
                    updateDateLabel();
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }


    // Thêm chi tiêu vào cơ sở dữ liệu
    private void addChiTieu() {
        String ten = edtTen.getText().toString().trim();
        String giaStr = edtGia.getText().toString().trim();
        String ghiChu = edtGhiChu.getText().toString().trim();

        // Kiểm tra dữ liệu đầu vào
        if (ten.isEmpty() || giaStr.isEmpty() || ghiChu.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double gia = Double.parseDouble(giaStr);
            if (gia <= 0) {
                Toast.makeText(this, "Số tiền phải lớn hơn 0!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Thêm chi tiêu vào cơ sở dữ liệu
            chiTieu newItem = new chiTieu(ten, gia, ghiChu, selectedDate);
            boolean success = chiTieuDAO.insertItem(newItem);

            if (success) {
                Toast.makeText(this, "Chi tiêu đã được thêm!", Toast.LENGTH_SHORT).show();
                finish(); // Trở về màn hình trước
            } else {
                Toast.makeText(this, "Thêm chi tiêu thất bại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Vui lòng nhập giá trị hợp lệ!", Toast.LENGTH_SHORT).show();
        }
    }

}
