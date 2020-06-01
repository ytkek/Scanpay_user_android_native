package ths.ScanPay_User;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ths.ScanPay_User.GetFunction.GetScanPayMerchantListTask;

public class LocationActivity extends AppCompatActivity {

    ImageView back;
    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);


        recyclerView = (RecyclerView) findViewById(R.id.location_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        new GetScanPayMerchantListTask(this,recyclerView).execute();


        back = (ImageView) findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
}
