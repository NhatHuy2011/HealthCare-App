package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailActivity extends AppCompatActivity {

    private String[][] doctor1 = {
            {"Name: Nguyen Manh Dung", "Hospital: Benh Vien Thu Duc", "Exp: 5 years", "Phone: 0909292939"},
            {"Name: Nguyen Huy Hoang", "Hospital: Benh Vien Thu Duc", "Exp: 5 years", "Phone: 0399339399"},
            {"Name: Nguyen Dinh Hai", "Hospital: Benh Vien Thu Duc", "Exp: 5 years", "Phone: 0942039022"}
    };

    private String[][] doctor2 = {
            {"Doctor: Nguyen Thi Tuyet Mai", "Hospital: Benh Vien Thu Duc", "Exp: 5 years", "Phone: 0939283822"},
            {"Doctor: Nguyen Van Dat", "Hospital: Benh Vien Thu Duc", "Exp: 5 years", "Phone: 0333822833"},
            {"Doctor: Nguyen Tien Huy", "Hospital: Benh Vien Thu Duc", "Exp: 5 years", "Phone: 0833828392"}
    };

    private String[][] doctor3 = {
            {"Doctor: Nguyen Hoang Hai", "Hospital: Benh Vien Thu Duc", "Exp: 5 years", "Phone: 0973838372"},
            {"Doctor: Dang Thi Bich Tram", "Hospital: Benh Vien Thu Duc", "Exp: 5 years", "Phone: 0839932992"},
            {"Doctor: Mai Thi Thu Huong", "Hospital: Benh Vien Thu Duc", "Exp: 5 years", "Phone: 0883938292"}
    };

    private String[][] doctor4 = {
            {"Doctor: Ho Thi Thu Mai", "Hospital: Benh Vien Thu Duc", "Exp: 5 years", "Phone: 0748837382"},
            {"Doctor: Chau Thi Bach Mai", "Hospital: Benh Vien Thu Duc", "Exp: 5 years", "Phone: 0893938224"},
            {"Doctor: Vo Thi Thu Hong", "Hospital: Benh Vien Thu Duc", "Exp: 5 years", "Phone: 0743928832"}
    };

    private String[][] doctor5 = {
            {"Doctor: Ha Van Manh", "Hospital: Benh Vien Thu Duc", "Exp: 5 years", "Phone: 073839382"},
            {"Doctor: Mai Van Hoang", "Hospital: Benh Vien Thu Duc", "Exp: 5 years", "Phone: 0383993822"},
            {"Doctor: Le Thi Thu Tuyet", "Hospital: Benh Vien Thu Duc", "Exp: 5 years", "Phone: 0849393839"}
    };

    private String[][] doctor6 = {
            {"Doctor: Nguyen Hoang Mai Nhi", "Hospital: Benh Vien Thu Duc", "Exp: 5 years", "Phone: 073839382"},
            {"Doctor: Mai Van Tien", "Hospital: Benh Vien Thu Duc", "Exp: 5 years", "Phone: 0383993822"},
            {"Doctor: Dao Hai Minh", "Hospital: Benh Vien Thu Duc", "Exp: 5 years", "Phone: 0849393839"}
    };

    String[][] doctor_number ={};

    ArrayList list;
    HashMap<String, String> item;
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        //Back
        Button back = findViewById(R.id.buttonBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailActivity.this, FindDocTorActivity.class));
            }
        });

        //Lấy title textView theo loại bác sĩ
        TextView tv =findViewById(R.id.textViewDocTor);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        tv.setText(title);

        //Gán danh sách bác sĩ đúng với title
        if (title.compareTo("DA LIỄU") == 0)
            doctor_number = doctor1;
        else
            if (title.compareTo("CHẨN ĐOÁN HÌNH ẢNH") == 0)
                doctor_number = doctor2;
            else
                if (title.compareTo("RĂNG HÀM MẶT") == 0)
                    doctor_number = doctor3;
                else
                    if (title.compareTo("TAI MŨI HỌNG") == 0)
                        doctor_number = doctor4;
                    else
                        if (title.compareTo("MẮT") == 0)
                            doctor_number = doctor5;
                        else
                        if (title.compareTo("XÉT NGHIỆM") == 0)
                            doctor_number = doctor6;
        //Duyet qua mang 2 chieu -> gan du lieu mang 2 chieu vao HashMap -> HashMap duoc them vao ArrayList
        list = new ArrayList<>();
        for(int i=0; i<doctor_number.length; i++){
            item = new HashMap<String, String>();
            item.put("line1", doctor_number[i][0]);
            item.put("line2", doctor_number[i][1]);
            item.put("line3", doctor_number[i][2]);
            item.put("line4", doctor_number[i][3]);
            list.add(item);
        }

        //Do du lieu tu adapter vao listview
        adapter = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d});
        ListView listView = findViewById(R.id.listViewInfoDoctor);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(DoctorDetailActivity.this, BookAppointmentActivity.class);
                intent1.putExtra("text1", title);
                intent1.putExtra("text2", doctor_number[position][0]);
                intent1.putExtra("text3", doctor_number[position][1]);
                //intent1.putExtra("text4", doctor_number[position][2]);
                intent1.putExtra("text4", doctor_number[position][3]);
                startActivity(intent1);
            }
        });
    }
}