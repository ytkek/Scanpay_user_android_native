package ths.ScanPay_UserV5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ths.ScanPay_UserV5.GetFunction.GetFindMerchantListTask;

public class FindMerchantActivity  extends AppCompatActivity {
    Activity findmerchantactivity;

    public static Spinner spinner;
    ImageView backbtn;
    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findmerchant);

        findmerchantactivity = this;

        backbtn = (ImageView) findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        spinner = (Spinner) findViewById(R.id.findmerchant_menu_list);
        List<String> list = new ArrayList<String>();
        list.add("Food And Beverage");
        list.add("Health Care");
        list.add("Hair Care And Saloon");
        list.add("Services");
        list.add("Retail");
        list.add("Mobile Truck");
        list.add("Others");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        recyclerView = (RecyclerView) findViewById(R.id.findmerchant_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        new GetFindMerchantListTask(this, recyclerView).execute(MainActivity.LoginID,MainActivity.Password);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new GetFindMerchantListTask(findmerchantactivity, recyclerView).execute(MainActivity.LoginID,MainActivity.Password);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
      //  List<FindMerchantlist> findmerchantlist = new ArrayList<>();
       // findmerchantlist.add(new FindMerchantlist("https://insideretail.hk/wp-content/uploads/2019/08/Monocle-HKIA-1.jpg", "Monocle", "911,jalan teratai"));
       // findmerchantlist.add(new FindMerchantlist("https://insideretail.hk/wp-content/uploads/2019/08/Monocle-HKIA-1.jpg", "Monocle", "911,jalan teratai"));
       // findmerchantlist.add(new FindMerchantlist("https://insideretail.hk/wp-content/uploads/2019/08/Monocle-HKIA-1.jpg", "Monocle", "911,jalan teratai"));
       // findmerchantlist.add(new FindMerchantlist("https://insideretail.hk/wp-content/uploads/2019/08/Monocle-HKIA-1.jpg", "Monocle", "911,jalan teratai"));
      //  mAdapter = new FindMerchantAdapter(this, findmerchantlist);

       // recyclerView.setAdapter(mAdapter);


    }
}
