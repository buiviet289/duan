package house.duan.appchitieu;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import house.duan.appchitieu.dao.chiTieuDAO;
import house.duan.appchitieu.model.chiTieu;

public class EditChiTieuActivity extends AppCompatActivity {
    private chiTieuDAO chiTieuDAO;
    private chiTieu expense;
    private EditText nameEditText, priceEditText, noteEditText, dateEditText;
    private ImageButton deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_chi_tieu);

        chiTieuDAO = new chiTieuDAO(this);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        int chiTieuId = intent.getIntExtra("chiTieuId", -1);
        String name = intent.getStringExtra("name");
        double price = intent.getDoubleExtra("price", 0);
        String note = intent.getStringExtra("note");
        String date = intent.getStringExtra("date");

        // Gán dữ liệu vào các EditText
        nameEditText = findViewById(R.id.editName);
        priceEditText = findViewById(R.id.editPrice);
        noteEditText = findViewById(R.id.editNote);
        dateEditText = findViewById(R.id.editDate);
        deleteButton = findViewById(R.id.buttondelete);

        nameEditText.setText(name);
        priceEditText.setText(String.valueOf(price));
        noteEditText.setText(note);
        dateEditText.setText(date);

        // Sự kiện chọn ngày cho EditText
        dateEditText.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            if (date != null) {
                calendar.setTimeInMillis(Long.parseLong(date)); // Ví dụ sử dụng Long.parseLong(date) nếu `date` là một số timestamp
            }
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth1) -> {
                calendar.set(year1, month1, dayOfMonth1);
                String selectedDate = String.format("%d-%02d-%02d", year1, month1 + 1, dayOfMonth1);
                dateEditText.setText(selectedDate);
            }, year, month, dayOfMonth);
            datePickerDialog.show();
        });

        // Sự kiện lưu dữ liệu sau khi sửa
        findViewById(R.id.btnSave).setOnClickListener(v -> saveExpense(chiTieuId));
        deleteButton.setOnClickListener(v -> deleteExpense(chiTieuId));
    }

    private void saveExpense(int chiTieuId) {
        String name = nameEditText.getText().toString().trim(); // Loại bỏ khoảng trắng đầu cuối
        String priceString = priceEditText.getText().toString().trim();
        String note = noteEditText.getText().toString().trim();
        String date = dateEditText.getText().toString().trim();

        if (name.isEmpty() || priceString.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double price = Double.parseDouble(priceString); // Kiểm tra kiểu dữ liệu số
            boolean success = chiTieuDAO.updateItem(chiTieuId, name, price, note, date);
            if (success) {
                Toast.makeText(this, "Chi tiêu đã được cập nhật", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Cập nhật chi tiêu thất bại", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Giá phải là một số hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }
        private void deleteExpense(int chiTieuId) {
            boolean success = chiTieuDAO.deleteItem(chiTieuId); // Gọi hàm xóa từ `chiTieuDAO`
            if (success) {
                Toast.makeText(this, "Chi tiêu đã được xóa", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Xóa chi tiêu thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }
