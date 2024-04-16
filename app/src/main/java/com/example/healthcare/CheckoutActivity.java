package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CheckoutActivity extends AppCompatActivity {

    EditText edName, edAddress, edPhone;
    Button checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        edName = findViewById(R.id.editTextFullname);
        edAddress = findViewById(R.id.editTextAddress);
        edPhone = findViewById(R.id.editTextPhoneNumber);
        checkout = findViewById(R.id.buttonCheckout);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(": "));
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();

                Database db = new Database(getApplicationContext(), "healthier", null, 1);
                db.addOrder(username, edName.getText().toString(), edAddress.getText().toString(), edPhone.getText().toString(), date.toString(), time.toString(), Float.parseFloat(price[1].toString()),"service");
                db.removeCart(username, "service");
                Toast.makeText(CheckoutActivity.this, "Check out success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CheckoutActivity.this, MainActivity.class));
            }
        });
    }
}