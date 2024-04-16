package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceCostActivity extends AppCompatActivity {

    private String[][] service1 = {
            {"STT: 1", "Tên: Đắp mặt nạ điều trị một số bệnh da","-Đắp mặt nạ là một kỹ thuật được sử dụng tương đối rộng rãi nhằm mục đích:\n" +
                    "\n" +
                    "  +Giúp duy trì vẻ đẹp bản chất của làn da.\n" +
                    "\n" +
                    "  +Duy trì sự mềm mại, bổ sung độ ẩm, dưỡng chất và thuốc (một số bệnh da).\n" +
                    "\n" +
                    "  +Thúc đẩy quá trình thay da diễn ra bình thường.\n" +
                    "\n" +
                    "  +Bảo vệ và duy trì làn da khỏe mạnh.",  "150000"},
            {"STT: 2", "Tên: Điều trị u mềm lây bằng nạo thương tổn"," ", "300000"},
            {"STT: 3", "Tên: Khám da liễu"," ", "160000"}
    };

    private String[][] service2 = {
            {"STT: 1", "Tên: Chụp Xquang sọ thẳng nghiêng"," ", "200000"},
            {"STT: 2", "Tên: Chụp Xquang mặt thẳng nghiêng"," ", "200000"},
            {"STT: 3", "Tên: Chụp Xquang hốc mắt thẳng nghiêng"," ", "200000"},
            {"STT: 4", "Tên: Chụp Xquang Blondeau"," ", "200000"},
            {"STT: 5", "Tên: Chụp Xquang Hirtz"," ", "200000"},
            {"STT: 6", "Tên: Chụp Xquang hàm chếch một bên"," ", "200000"},
            {"STT: 7", "Tên: Chụp Xquang xương chính mũi nghiêng hoặc tiếp tuyến"," ", "200000"},
            {"STT: 8", "Tên: Chụp Xquang Schuller"," ", "200000"},
            {"STT: 9", "Tên: Chụp Xquang khớp thái dương hàm"," ", "200000"},
            {"STT: 10", "Tên: Chụp Xquang cột sống cổ thẳng nghiêng"," ", "200000"},
            {"STT: 11", "Tên: Chụp Xquang cột sống cổ chếch hai bên"," ", "200000"},
            {"STT: 12", "Tên: Chụp Xquang cột sống cổ động, nghiêng 3 tư thế"," ", "200000"}
    };

    private String[][] service3 = {
            {"STT: 1", "Tên: Điều trị viêm lợi trẻ em (do mảng bám)"," ", "100000"},
            {"STT: 2", "Tên: Chích Apxe lợi trẻ em"," ", "120000"},
            {"STT: 3", "Tên: Nhổ chân răng sữa"," ", "100000"},
            {"STT: 4", "Tên: Nhổ răng sữa"," ", "50000"},
            {"STT: 5", "Tên: Điều trị răng sữa sâu ngà phục hồi bằng GlassIonomer Cement"," ", "250000"},
            {"STT: 6", "Tên: Điều trị răng sữa sâu ngà phục hồi bằng Amalgam"," ", "250000"},
            {"STT: 7", "Tên: Điều trị đóng cuống răng bằng MTA"," ", "500000"},
            {"STT: 8", "Tên: Điều trị đóng cuống răng bằng Canxi Hydroxit"," ", "250000"},
            {"STT: 9", "Tên: Điều trị răng sữa viêm tuỷ có hồi phục"," ", "200000"},
            {"STT: 10", "Tên: Dự phòng sâu răng bằng máng có Gel Fluor"," ", "150000"},
            {"STT: 11", "Tên: Phòng ngừa sâu răng với thuốc bôi bề mặt"," ", "250000"},
            {"STT: 12", "Tên: Hàn răng không sang chấn với GlassIonomer Cement"," ", "300000"}
    };

    private String[][] service4 = {
            {"STT: 1", "Tên: Thay băng vết mổ"," ", "60000"},
            {"STT: 2", "Tên: Cắt chỉ sau phẫu thuật (người lớn)"," ", "100000"},
            {"STT: 3", "Tên: Lấy dị vật họng miệng"," ", "130000"},
            {"STT: 4", "Tên: Rút meche, rút merocel hốc mũi"," ", "120000"},
            {"STT: 5", "Tên: Lấy dị vật mũi gây tê"," ", "400000"},
            {"STT: 6", "Tên: Cầm máu mũi bằng Merocel"," ", "80000"},
            {"STT: 7", "Tên: Nhét bấc mũi trước"," ", "130000"},
            {"STT: 8", "Tên: Phương pháp Proetz"," ", "120000"},
            {"STT: 9", "Tên: Lấy nút biểu bì ống tai ngoài"," ", "120000"},
            {"STT: 10", "Tên: Làm thuốc tai"," ", "80000"},
            {"STT: 11", "Tên: Chích nhọt ống tai ngoài"," ", "150000"},
            {"STT: 12", "Tên: Lấy dị vật tai"," ", "100000"}
    };

    private String[][] service5 = {
            {"STT: 1", "Tên: Đo khúc xạ máy"," ", "0"},
            {"STT: 2", "Tên: Thử kính"," ", "60000"},
            {"STT: 3", "Tên: Đo thị lực"," ", "160000"},
            {"STT: 4", "Tên: Đo sắc giác"," ", "150000"},
            {"STT: 5", "Tên: Đo nhãn áp (Maclakov, Goldmann, Schiotz…)"," ", "100000"},
            {"STT: 6", "Tên: Theo dõi nhãn áp 3 ngày"," ", "250000"},
            {"STT: 7", "Tên: Soi đáy mắt trực tiếp"," ", "200000"},
            {"STT: 8", "Tên: Tra thuốc nhỏ mắt"," ", "60000"},
            {"STT: 9", "Tên: Thay băng vô khuẩn"," ", "80000"},
            {"STT: 10", "Tên: Nhổ lông siêu"," ", "60000"},
            {"STT: 11", "Tên: Lấy dị vật kết mạc"," ", "250000"},
            {"STT: 12", "Tên: Khám mắt"," ", "160000"}
    };

    private String[][] service6 = {
            {"STT: 1", "Tên: Chẩn đoán sớm bệnh xơ gan mật nguyên phát"," ", "399000"},
            {"STT: 2", "Tên: Dấu ấn ung thư tế bào vảy vòm họng, cổ tử cung"," ", "249000"},
            {"STT: 3", "Tên: Dấu ấn ung thư tuyến giáp thể tuỷ"," ", "309000"},
            {"STT: 4", "Tên: Dấu ấn ung thư dạ dày"," ", "249000"},
            {"STT: 5", "Tên: Dấu ấn ung thư phổi tế bào không nhỏ"," ", "249000"},
            {"STT: 6", "Tên: Dấu hiệu ung thư buồng trứng"," ", "499000"}
    };

    String[][] service_number ={};

    ArrayList list;
    HashMap<String, String> item;
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_cost);
        //Back
        Button back = findViewById(R.id.buttonBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServiceCostActivity.this, ServiceActivity.class));
            }
        });

        //Lấy title textView theo loại dịch vụ
        TextView tv =findViewById(R.id.textViewServiceCost);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        tv.setText(title);

        //Gán danh sách bác sĩ đúng với title
        if (title.compareTo("DA LIỄU") == 0)
            service_number = service1;
        else
        if (title.compareTo("CHẨN ĐOÁN HÌNH ẢNH") == 0)
            service_number = service2;
        else
        if (title.compareTo("RĂNG HÀM MẶT") == 0)
            service_number = service3;
        else
        if (title.compareTo("TAI MŨI HỌNG") == 0)
            service_number = service4;
        else
        if (title.compareTo("MẮT") == 0)
            service_number = service5;
        else
        if (title.compareTo("XÉT NGHIỆM") == 0)
            service_number = service6;

        //Duyet qua mang 2 chieu -> gan du lieu mang 2 chieu vao HashMap -> HashMap duoc them vao ArrayList
        list = new ArrayList<>();
        for(int i=0; i<service_number.length; i++){
            item = new HashMap<String, String>();
            item.put("line1", service_number[i][1]);
            item.put("line2",  "Giá: "+ service_number[i][3]);
            item.put("line3", service_number[i][0]);
            list.add(item);
        }

        //Do du lieu tu adapter vao listview
        adapter = new SimpleAdapter(this, list,
                R.layout.multi_lines2,
                new String[]{"line1", "line2", "line3"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c});
        ListView listView = findViewById(R.id.listViewServiceCost);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(ServiceCostActivity.this, ServiceCostDetailActivity.class);
                it.putExtra("text1", service_number[position][1]);
                it.putExtra("text2", service_number[position][2]);
                it.putExtra("text3", service_number[position][3]);
                startActivity(it);
            }
        });
        //Go to cart
        Button goToCart = findViewById(R.id.buttonGoToCart);
        goToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServiceCostActivity.this, CartActivity.class));
            }
        });
    }
}