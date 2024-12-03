package house.duan.appchitieu;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import house.duan.appchitieu.dao.chiTieuDAO;

public class EditChiTieuActivity extends AppCompatActivity {
    private EditText editName, editPrice, editNote, editDate;
    private Button btnSave;
    private int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_chi_tieu);

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        itemId = intent.getIntExtra("ITEM_ID", -1);
        String name = intent.getStringExtra("ITEM_NAME");
        double price = intent.getDoubleExtra("ITEM_PRICE", 0);
        String note = intent.getStringExtra("ITEM_NOTE");
        String date = intent.getStringExtra("ITEM_DATE");

        // Ánh xạ các view
        editName = findViewById(R.id.editName);
        editPrice = findViewById(R.id.editPrice);
        editNote = findViewById(R.id.editNote);
        editDate = findViewById(R.id.editDate);
        btnSave = findViewById(R.id.btnSave);

        // Hiển thị thông tin hiện tại của item vào các EditText
        editName.setText(name);
        editPrice.setText(String.valueOf(price));
        editNote.setText(note);
        editDate.setText(date);

        // Xử lý sự kiện nhấn nút "Lưu"
        btnSave.setOnClickListener(v -> {
            // Lấy thông tin người dùng đã chỉnh sửa
            String newName = editName.getText().toString().trim();
            String newPriceStr = editPrice.getText().toString().trim();
            String newNote = editNote.getText().toString().trim();
            String newDate = editDate.getText().toString().trim();

            if (newName.isEmpty() || newPriceStr.isEmpty() || newNote.isEmpty() || newDate.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            double newPrice = Double.parseDouble(newPriceStr);

            // Cập nhật thông tin chi tiêu trong cơ sở dữ liệu
            chiTieuDAO dao = new chiTieuDAO(this);
            boolean isUpdated = dao.updateItem(itemId, newName, newPrice, newNote, newDate);

            if (isUpdated) {
                Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                // Trả lại kết quả cho Activity trước đó
                Intent returnIntent = new Intent();
                returnIntent.putExtra("UPDATED_ITEM_ID", itemId);
                returnIntent.putExtra("UPDATED_ITEM_DATE", newDate);
                setResult(RESULT_OK, returnIntent);
                finish(); // Trở lại Activity trước đó
            } else {
                Toast.makeText(this, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
            }
        });

        // Chọn ngày cho chi tiêu
        editDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    EditChiTieuActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        String selectedDate = year1 + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        editDate.setText(selectedDate);
                    },
                    year, month, day);

            datePickerDialog.show();
        });
    }
}
