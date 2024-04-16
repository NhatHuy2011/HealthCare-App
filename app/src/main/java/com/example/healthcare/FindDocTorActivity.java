package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FindDocTorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doc_tor);

        //DaLieu
        CardView dalieu = findViewById(R.id.cardDaLieu);
        dalieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDocTorActivity.this, DoctorDetailActivity.class);
                intent.putExtra("title", "DA LIỄU");
                startActivity(intent);
            }
        });

        //NhiSoSinh
        CardView chandoanhinhanh = findViewById(R.id.cardChanDoanHinhAnh);
        chandoanhinhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDocTorActivity.this, DoctorDetailActivity.class);
                intent.putExtra("title", "CHẨN ĐOÁN HÌNH ẢNH");
                startActivity(intent);
            }
        });

        //RangHamMat
        CardView ranghammat = findViewById(R.id.cardRangHamMat);
        ranghammat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDocTorActivity.this, DoctorDetailActivity.class);
                intent.putExtra("title", "RĂNG HÀM MẶT");
                startActivity(intent);
            }
        });

        //TaiMuiHong
        CardView taimuihong = findViewById(R.id.cardTaiMuiHong);
        taimuihong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDocTorActivity.this, DoctorDetailActivity.class);
                intent.putExtra("title", "TAI MŨI HỌNG");
                startActivity(intent);
            }
        });

        //Mat
        CardView mat = findViewById(R.id.cardMat);
        mat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDocTorActivity.this, DoctorDetailActivity.class);
                intent.putExtra("title", "MẮT");
                startActivity(intent);
            }
        });

        //XetNghiem
        CardView xetnghiem = findViewById(R.id.cardXetNghiem);
        xetnghiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDocTorActivity.this, DoctorDetailActivity.class);
                intent.putExtra("title", "XÉT NGHIỆM");
                startActivity(intent);
            }
        });

        //Back
        Button back = findViewById(R.id.back1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindDocTorActivity.this, MainActivity.class));
            }
        });
    }
}