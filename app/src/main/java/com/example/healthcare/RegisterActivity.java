package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername, edEmail, edPassword, edConfirm;
    Button btnRegis;
    TextView txtAlreadyAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = (EditText) findViewById(R.id.editTextRegUsername);
        edEmail = (EditText) findViewById(R.id.editTextEmail);
        edPassword = (EditText) findViewById(R.id.editTextPassword);
        edConfirm = (EditText) findViewById(R.id.editTextRePassword);
        btnRegis = (Button) findViewById(R.id.buttonRegis);
        txtAlreadyAccount = (TextView) findViewById(R.id.textViewAlreadyAccount);
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                Database db = new Database(getApplicationContext(), "healthier", null, 1);
                if(validateUsername()==true && validateEmail()==true && validatePassword()==true && validateRePassWord()==true) {
                    db.register(username, email, password);
                    Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        txtAlreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
    public boolean validateUsername()
    {
        int f1 = 0;
        String val = edUsername.getText().toString();
        for (int s = 0; s < val.length(); s++)
        {
            char c = val.charAt(s);
            if(Character.isWhitespace(c))
                f1 = 1;
        }
        if (val.isEmpty()) {
            edUsername.setError("Username không được để trống");
            return false;
        } else if (val.length() >= 15 || val.length() < 3) {
            edUsername.setError("Username phải từ 3 đến 14 kí tự");
            return false;
        } else if (f1 == 1) {
            edUsername.setError("Username không được tồn tại khoảng trắng");
            return false;
        } else {
            edUsername.setError(null);
            //binding.editTextRegUsername.setEnabled(false);
            return true;
        }
    }
    public boolean validateEmail()
    {
        String val = edEmail.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            edEmail.setError("Email không được để trống");
            return false;
        } else if (!val.matches(emailPattern)) {
            edEmail.setError("Email không tồn tại");
            return false;
        } else {
            edEmail.setError(null);
            //binding.editTextEmail.setEnabled(false);
            return true;
        }
    }
    public boolean validatePassword()
    {
        String val = edPassword.getText().toString();
        int f1 = 0, f2 = 0, f3 = 0, f4=0;
        if (val.length() < 8)
        {
            edPassword.setError("Mật khẩu phải nhiều hơn 8 kí tự");
            return false;
        }
        else
        {
            for (int p = 0; p < val.length(); p++)
            {
                if (Character.isLetter(val.charAt(p)))
                {
                    f1 = 1;
                }
            }
            for (int r = 0; r < val.length(); r++)
            {
                if (Character.isDigit(val.charAt(r)))
                {
                    f2 = 1;
                }
            }
            for (int s = 0; s < val.length(); s++)
            {
                char c = val.charAt(s);
                if (c >= 33 && c <= 46 || c == 64)
                {
                    f3 = 1;
                }
                if(Character.isWhitespace(c))
                {
                    f4=1;
                }
            }
            if (f1 == 1 && f2 == 1 && f3 == 1 && f4==0)
                return true;
            else
            {
                edPassword.setError("Mật khẩu không được chứa khoảng trắng và phải chứa kí tự thường, kí tự đặc biệt và chữ số");
                return false;
            }
        }
    }
    public boolean validateRePassWord()
    {
        String password = edConfirm.getText().toString();
        String repassword = edConfirm.getText().toString();
        if(password.compareTo(repassword) != 0)
        {
            edConfirm.setError("PassWord không trùng khớp");
            return false;
        }
        else
        {
            edConfirm.setError(null);
            //binding.editTextRePassword.setEnabled(false);
            return true;
        }
    }
}