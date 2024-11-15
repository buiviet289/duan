package house.duan.appchitieu;

//import android.os.Bundle;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class dangki extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_dangki);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }
//}


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class dangki extends AppCompatActivity {

    private EditText edtUsername, edtPassword, edtRePassword;
    private Button btnRegister, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);  // Layout đăng ký của bạn

        // Ánh xạ các EditText và Button
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtRePassword = findViewById(R.id.edt_nhaplaipassword);
        btnRegister = findViewById(R.id.btn_register);
        btnExit = findViewById(R.id.btn_exit);

        // Xử lý sự kiện cho nút Đăng ký
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy giá trị từ các trường EditText
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String rePassword = edtRePassword.getText().toString().trim();

                // Kiểm tra tính hợp lệ của các trường dữ liệu
                if (username.isEmpty()) {
                    edtUsername.setError("Tài khoản không được để trống");
                    return;
                }
                if (password.isEmpty()) {
                    edtPassword.setError("Mật khẩu không được để trống");
                    return;
                }
                if (!password.equals(rePassword)) {
                    edtRePassword.setError("Mật khẩu không khớp");
                    return;
                }

                // Nếu tất cả kiểm tra đều hợp lệ, thực hiện đăng ký
                registerUser(username, password);
            }
        });

        // Xử lý sự kiện cho nút Thoát
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đóng activity hoặc quay lại màn hình trước đó
                finish();
            }
        });
    }

    // Hàm xử lý đăng ký
    private void registerUser(String username, String password) {
        // Giả sử đăng ký thành công ở đây. Nếu bạn có backend, bạn sẽ gọi API gửi thông tin lên server.

        // Ví dụ: Bạn có thể gửi yêu cầu lên server ở đây.
        // Ví dụ sử dụng Volley hoặc Retrofit, hoặc lưu vào cơ sở dữ liệu nội bộ nếu không có server.

        // Nếu đăng ký thành công
        Toast.makeText(dangki.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

        // Nếu muốn chuyển qua màn hình chính sau khi đăng ký thành công, bạn có thể làm như sau:
        // Intent intent = new Intent(DangKiActivity.this, MainActivity.class);
        // startActivity(intent);
    }
}
