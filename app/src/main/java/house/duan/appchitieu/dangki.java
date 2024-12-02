package house.duan.appchitieu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import house.duan.appchitieu.database.QuanLyNguoiDungSqlite;

public class dangki extends AppCompatActivity {

    private EditText etUsername, etPassword, etConfirmPassword;
    private Button btnRegister;
    private QuanLyNguoiDungSqlite dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);  // Layout đăng ký của bạn

        etUsername = findViewById(R.id.edtUsernameRegister);
        etPassword = findViewById(R.id.edtPasswordRegister);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnRegister = findViewById(R.id.btnRegister);
        dbHelper = new QuanLyNguoiDungSqlite(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(dangki.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else if (!password.equals(confirmPassword)) {
                    Toast.makeText(dangki.this, "Mật khẩu không trùng khớp!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    boolean success = dbHelper.insertUser(username, password);
                    if (success) {
                        Toast.makeText(dangki.this, "Đăng Kí Thành Công!", Toast.LENGTH_SHORT).show();
                        finish(); // Go back to login
                    } else {
                        Toast.makeText(dangki.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
};
