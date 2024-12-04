package house.duan.appchitieu;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
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
    private TextView txtDate;  // TextView để hiển thị ngày
    private Button btnAdd, btnSelectDate;  // Nút thêm và chọn ngày
    private String selectedDate = null;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private chiTieuDAO chiTieuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogadd);  // Đảm bảo layout tên đúng

        // Ánh xạ các views
        edtTen = findViewById(R.id.edt_ten);
        edtGia = findViewById(R.id.edt_gia);
        edtGhiChu = findViewById(R.id.edt_ghichu);
        txtDate = findViewById(R.id.txt_date);  // TextView hiển thị ngày
        btnAdd = findViewById(R.id.btn_add);
//        btnSelectDate = findViewById(R.id.edit_ngay);  // Button chọn ngày

        chiTieuDAO = new chiTieuDAO(this);

        // Khởi tạo Calendar và SimpleDateFormat
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Cập nhật TextView với ngày hiện tại
        updateDateLabel();

        // Xử lý sự kiện click vào Button để chọn ngày
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị DatePickerDialog
                new DatePickerDialog(AddChiTieuActivity.this, dateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // Thêm chi tiêu vào cơ sở dữ liệu
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtTen.getText().toString();
                String giaStr = edtGia.getText().toString();
                String ghiChu = edtGhiChu.getText().toString();
                String date = txtDate.getText().toString();
                try {
                    double gia = Double.parseDouble(giaStr);
                    chiTieu newItem = new chiTieu(ten, gia, ghiChu, date);

                    // Thêm chi tiêu vào cơ sở dữ liệu
                    boolean success = chiTieuDAO.insertItem(newItem);
                    if (success) {Toast.makeText(AddChiTieuActivity.this, "Chi tiêu đã được thêm!", Toast.LENGTH_SHORT).show();
                        finish();  // Trở lại màn hình trước
                    } else {
                        Toast.makeText(AddChiTieuActivity.this, "Thêm chi tiêu thất bại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    // Nếu không phải định dạng số hợp lệ, hiển thị thông báo lỗi
                    Toast.makeText(AddChiTieuActivity.this, "Vui lòng nhập giá trị hợp lệ cho trường giá.", Toast.LENGTH_SHORT).show();
                }

//            if (!ten.isEmpty() && !giaStr.isEmpty() && !ghiChu.isEmpty() && (date == null || date.isEmpty() ))
//            {
//                try {
//                    double gia = Double.parseDouble(giaStr);
//                    chiTieu newItem = new chiTieu(ten, gia, ghiChu, date);
//
//                    // Thêm chi tiêu vào cơ sở dữ liệu
//                    boolean success = chiTieuDAO.insertItem(newItem);
//                    if (success) {Toast.makeText(this, "Chi tiêu đã được thêm!", Toast.LENGTH_SHORT).show();
//                        finish();  // Trở lại màn hình trước
//                    } else {
//                        Toast.makeText(this, "Thêm chi tiêu thất bại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (NumberFormatException e) {
//                    // Nếu không phải định dạng số hợp lệ, hiển thị thông báo lỗi
//                    Toast.makeText(this, "Vui lòng nhập giá trị hợp lệ cho trường giá.", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
//            }
            }
        });
    }

    // Định nghĩa listener khi người dùng chọn ngày từ DatePicker
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
            // Cập nhật ngày đã chọn vào Calendar
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            // Cập nhật lại TextView với ngày đã chọn
            selectedDate = dateFormat.format(calendar.getTime());
            updateDateLabel();
        }
    };

    // Phương thức để cập nhật TextView với ngày hiện tại hoặc ngày đã chọn
    private void updateDateLabel() {
        String formattedDate = dateFormat.format(calendar.getTime());
        txtDate.setText("Ngày hiện tại: " + formattedDate);
    }
}