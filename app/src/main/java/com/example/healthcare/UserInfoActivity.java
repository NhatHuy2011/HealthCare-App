package com.example.healthcare;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class UserInfoActivity extends AppCompatActivity {

    EditText editTextFullname, editPassWord, editTextEmail;
    Button btnUpdate;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        // Ánh xạ các EditText từ layout
        editTextFullname = findViewById(R.id.editTextFullname);
        editPassWord = findViewById(R.id.editPassWord);
        editTextEmail = findViewById(R.id.editTextEmail);
        btnUpdate = findViewById(R.id.buttonUpdate);

        // Khởi tạo cơ sở dữ liệu
        database = new Database(this, "HealthCare.db", null, 1);

        // Truy xuất dữ liệu người dùng từ cơ sở dữ liệu
        User user = database.getUserFromDatabase();


        // Gán giá trị vào các trường EditText
        editTextFullname.setText(user.getUsername());
        editPassWord.setText(user.getPassword());
        editTextEmail.setText(user.getEmail());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Update User
                String username = editTextFullname.getText().toString();
                String newEmail = editTextEmail.getText().toString();
                String newPassword = editPassWord.getText().toString();
                // Gọi phương thức cập nhật thông tin người dùng
                database.updateUser(username, newEmail, newPassword);
                // Hiển thị thông báo hoặc thực hiện hành động khác sau khi cập nhật thành công
                Toast.makeText(UserInfoActivity.this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

}