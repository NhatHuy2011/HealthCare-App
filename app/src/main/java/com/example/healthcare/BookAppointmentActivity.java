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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {

    Button dateButton;

    Button timeButton;
    private DatePickerDialog datePickerDialog;

    private TimePickerDialog timePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        TextView tv = findViewById(R.id.textViewAppTitle);
        EditText ed1 = findViewById(R.id.editTextAppFullName);
        EditText ed2 = findViewById(R.id.editTextAppAddress);
        EditText ed3 = findViewById(R.id.editTextAppContactNumber);
        dateButton = findViewById(R.id.buttonCartDate);
        timeButton = findViewById(R.id.buttonCartTime);

        //Set disable cho 3 edittext
        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);

        //Lay du lieu tu DoctorDetail thong qua Intent
        Intent intent =getIntent();
        String title = intent.getStringExtra("text1");
        String fullname = intent.getStringExtra("text2");
        String address = intent.getStringExtra("text3");
        String contact = intent.getStringExtra("text4");

        //Gan gia tri ung voi moi edit text
        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);

        //Button Date
        //data picker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        //Button time
        //time picker
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        Button buttonBook = findViewById(R.id.buttonBookAppointment);
        buttonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                Database db = new Database(getApplicationContext(), "healthier", null, 1);
                if(db.checkAppointmentExist(username, title+ "=>" +fullname, address, contact, dateButton.getText().toString(), timeButton.getText().toString())==1)
                {
                    Toast.makeText(getApplicationContext(), "Cuộc hẹn đã tồn tại", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    db.addOrder(username, title+ "=>" +fullname, address, contact, dateButton.getText().toString(), timeButton.getText().toString(),100000,"appointment");
                    Toast.makeText(getApplicationContext(), "Cuộc hẹn mới được tạo thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BookAppointmentActivity.this, MainActivity.class));
                }
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

        int style =AlertDialog.THEME_HOLO_DARK;
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