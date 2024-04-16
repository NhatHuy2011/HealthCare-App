package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceCostDetailActivity extends AppCompatActivity {
    TextView txtCost, txtServiceName;
    EditText edDetails;

    Button btnBack;
    Button btnAddCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_cost_detail);

        txtServiceName = findViewById(R.id.textViewNameService);
        edDetails = findViewById(R.id.editServiceCostDetail);
        txtCost = findViewById(R.id.textViewCost);
        btnAddCart = findViewById(R.id.buttonAddCart);

        edDetails.setKeyListener(null);
        Intent intent = getIntent();
        txtServiceName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        txtCost.setText(intent.getStringExtra("text3"));

        //AddCart
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                String product = txtServiceName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(), "healthier", null, 1);
                if(db.checkCart(username, product) == 1)
                {
                    Toast.makeText(getApplicationContext(),"Dịch vụ đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addCart(username, product, price, "service");
                    Toast.makeText(getApplicationContext(), "Add Success", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(ServiceCostDetailActivity.this, ServiceActivity.class));
                }
            }
        });
    }
}