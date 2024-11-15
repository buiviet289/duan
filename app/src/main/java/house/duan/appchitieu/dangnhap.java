package house.duan.appchitieu;

//import android.os.Bundle;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class dangnhap extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_dangnhap);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btn_login), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }
//}

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

public class dangnhap extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private Button btnLogin, btnRegister, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        // Khởi tạo các view
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        btnExit = findViewById(R.id.btn_exit);

        // Xử lý sự kiện đăng nhập
        btnLogin.setOnClickListener(v -> {
           // String username = edtUsername.getText().toString();
          //  String password = edtPassword.getText().toString();

            // Kiểm tra tài khoản và mật khẩu (ví dụ đơn giản)
         //   if (username.equals("admin") && password.equals("123456")) {
                // Chuyển sang màn hình chính sau khi đăng nhập thành công
                Intent intent = new Intent(dangnhap.this, MainActivity.class);
                startActivity(intent);
                finish();
          //  } else {
                // Hiển thị thông báo lỗi nếu đăng nhập không thành công
          //      Toast.makeText(dangnhap.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
         //   }
       });

        // Xử lý sự kiện đăng ký
        btnRegister.setOnClickListener(v -> {
            // Mở màn hình đăng ký (bạn có thể tạo một màn hình đăng ký mới)
            Intent intent = new Intent(dangnhap.this, dangki.class);
            startActivity(intent);
        });

        // Xử lý sự kiện thoát
        btnExit.setOnClickListener(v -> {
            // Đóng ứng dụng
            finish();
        });
    }
}
