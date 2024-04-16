package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartActivity extends AppCompatActivity {
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView listView;
    TextView tvTotal;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, timeButton, btnCheckout, btnBack;
    private String[][] packages = {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        dateButton = findViewById(R.id.buttonCartDate);
        timeButton = findViewById(R.id.buttonCartTime);
        btnBack = findViewById(R.id.buttonBack);
        btnCheckout = findViewById(R.id.buttonCheckout);
        tvTotal = findViewById(R.id.textViewCost);
        listView = findViewById(R.id.listViewOrder);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();

        Database db = new Database(getApplicationContext(), "healthier", null, 1);
        ArrayList dbData = db.getCartData(username, "service");
        //Toast.makeText(getApplicationContext(),""+dbData, Toast.LENGTH_LONG).show();//Test thu du lieu da duoc truyen qua chua

        float total = 0;
        packages = new String[dbData.size()][];

        for(int i=0; i<packages.length; i++)
        {
            packages[i] = new String[4];
        }
        for(int i=0; i<dbData.size(); i++)
        {
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("VND"));
            packages[i][1] = strData[0];
            packages[i][3] = "Cost: "+strData[1];
            total = total + Float.parseFloat(strData[1]);
        }
        tvTotal.setText("Total Cost: "+total);

        list = new ArrayList<>();
        for(int i=0; i<packages.length; i++){
            item = new HashMap<String, String>();
            item.put("line1", packages[i][1]);
            item.put("line2", packages[i][3]);
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines3,
                new String[]{"line1", "line2"},
                new int[]{R.id.line_a, R.id.line_b});
        ListView lst = findViewById(R.id.listViewOrder);
        lst.setAdapter(sa);

        //Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, ServiceActivity.class));
            }
        });

        //Checkout
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
                intent.putExtra("price", tvTotal.getText());
                intent.putExtra("date", dateButton.getText());
                intent.putExtra("time", timeButton.getText());
                startActivity(intent);
            }
        });
        //datepicker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        //timepicker
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener =new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeButton.setText(hourOfDay + "|" + minute);
            }
        };

        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins =cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hrs, mins, true);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month =month + 1;
                dateButton.setText(dayOfMonth + "/" + month + "/" + year);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);
    }
}