package house.duan.appchitieu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import house.duan.appchitieu.R;
import house.duan.appchitieu.dao.chiTieuDAO;

public class EditChiTieuActivity extends AppCompatActivity {
    private EditText editName, editPrice, editNote;
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
        int price = intent.getIntExtra("ITEM_PRICE", 0);
        String note = intent.getStringExtra("ITEM_NOTE");

        // Ánh xạ các view
        editName = findViewById(R.id.editName);
        editPrice = findViewById(R.id.editPrice);
        editNote = findViewById(R.id.editNote);
        btnSave = findViewById(R.id.btnSave);

        // Hiển thị thông tin hiện tại của item vào các EditText
        if (name != null) {
            editName.setText(name);
        }
        editPrice.setText(String.valueOf(price));
        if (note != null) {
            editNote.setText(note);
        }

        // Xử lý sự kiện nhấn nút "Lưu"
        btnSave.setOnClickListener(v -> {
            // Lấy thông tin người dùng đã chỉnh sửa
            String newName = editName.getText().toString().trim();
            String newPriceStr = editPrice.getText().toString().trim();
            String newNote = editNote.getText().toString().trim();

            if (newName.isEmpty() || newPriceStr.isEmpty() || newNote.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            double newPrice = Double.parseDouble(newPriceStr);

            // Cập nhật thông tin chi tiêu trong cơ sở dữ liệu
            chiTieuDAO dao = new chiTieuDAO(this);
            boolean isUpdated = dao.updateItem(itemId, newName, newPrice, newNote);

            if (isUpdated) {
                Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                finish(); // Trở lại Activity trước đó
            } else {
                Toast.makeText(this, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
            }


    });

    }
}
