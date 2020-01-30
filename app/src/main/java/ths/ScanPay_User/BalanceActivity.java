package ths.ScanPay_User;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BalanceActivity extends AppCompatActivity {
    Activity balanceactivity;
    ImageView backbtn;
    public static EditText datefromedit,datetoedit;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balance);

        balanceactivity=this;

         backbtn = (ImageView)findViewById(R.id.backbtn);
         backbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });

         datefromedit = (EditText)findViewById(R.id.datefrom);
         datefromedit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 DateDialog cdd=new DateDialog(balanceactivity,"datefrom");

                 cdd.show();
                 Window window = cdd.getWindow();
                 window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);



             }
         });

         datefromedit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
             @Override
             public void onFocusChange(View v, boolean hasFocus) {

                 if(hasFocus==true)
                 {
                     DateDialog cdd=new DateDialog(balanceactivity,"datefrom");

                     cdd.show();
                     Window window = cdd.getWindow();
                     window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                 }

             }
         });

         datetoedit = (EditText)findViewById(R.id.dateto);
         datetoedit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 DateDialog cdd=new DateDialog(balanceactivity,"dateto");

                 cdd.show();
                 Window window = cdd.getWindow();
                 window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
             }
         });

         datetoedit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
             @Override
             public void onFocusChange(View v, boolean hasFocus) {

                 if(hasFocus==true)
                 {
                     DateDialog cdd=new DateDialog(balanceactivity,"dateto");

                     cdd.show();
                     Window window = cdd.getWindow();
                     window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                 }
             }
         });
        recyclerView = (RecyclerView) findViewById(R.id.trasaction_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);


        List<Balancelist> balancelist = new ArrayList<>();
        balancelist.add(new Balancelist("20/1/2020 11:38:11","Reversal Payment","MyScanPay","1.00"));

        mAdapter = new BalanceAdapter(this,balancelist);

        recyclerView.setAdapter(mAdapter);


    }
}
