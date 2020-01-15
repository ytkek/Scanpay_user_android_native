package ths.ScanPay_User;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {

    ImageView back;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);


        recyclerView = (RecyclerView) findViewById(R.id.location_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);


        List<Locationlist> locationlist = new ArrayList<>();
        locationlist.add(new Locationlist("https://f0.pngfuel.com/png/408/791/sticker-sales-hot-price-fire-sticker-white-hot-price-text-with-red-flame-background-illustration-png-clip-art-thumbnail.png","SCANPAY","SCANPAY ON SALES","https://f0.pngfuel.com/png/408/791/sticker-sales-hot-price-fire-sticker-white-hot-price-text-with-red-flame-background-illustration-png-clip-art-thumbnail.png"));
        locationlist.add(new Locationlist("https://f0.pngfuel.com/png/408/791/sticker-sales-hot-price-fire-sticker-white-hot-price-text-with-red-flame-background-illustration-png-clip-art-thumbnail.png","SCANPAY","SCANPAY ON SALES","https://f0.pngfuel.com/png/408/791/sticker-sales-hot-price-fire-sticker-white-hot-price-text-with-red-flame-background-illustration-png-clip-art-thumbnail.png"));
        mAdapter = new LocationAdapter(this,locationlist);

        recyclerView.setAdapter(mAdapter);

        back = (ImageView) findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
}
