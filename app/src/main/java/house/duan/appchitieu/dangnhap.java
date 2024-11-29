package house.duan.appchitieu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import house.duan.appchitieu.database.QuanLyChiTieuSqlite;

public class dangnhap extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin, btnDangki;
    private QuanLyChiTieuSqlite dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        etUsername = findViewById(R.id.etUsernameLogin);
        etPassword = findViewById(R.id.etPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnDangki = findViewById(R.id.btnDangki);

        dbHelper = new QuanLyChiTieuSqlite(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(dangnhap.this, "Vui Lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isValid = dbHelper.checkUser(username, password);
                    if (isValid) {
                        Toast.makeText(dangnhap.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        // Navigate to main activity
                        Intent intent = new Intent(dangnhap.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(dangnhap.this, "Tên Đăng nhập hoặc mật khẩu sai!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnDangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dangnhap.this, dangki.class);
                startActivity(intent);
            }
        });

    }
}
