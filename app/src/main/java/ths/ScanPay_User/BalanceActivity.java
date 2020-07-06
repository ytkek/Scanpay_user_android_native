package ths.ScanPay_User;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import ths.ScanPay_User.PostFunction.PostBalance_SearchDate_Statement_Task;
import ths.ScanPay_User.PostFunction.PostBalance_TotalCredit_Task;

public class BalanceActivity extends AppCompatActivity {
    Activity balanceactivity;
    ImageView backbtn;
    public static TextView totalbalance_textview,daily_limit_textview,daily_exp_textview;
    public static EditText datefromedit,datetoedit;
    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter mAdapter;
    Button search_btn;

    public static String datefrom,dateto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balance);

        balanceactivity=this;

        totalbalance_textview=(TextView)findViewById(R.id.totalbalance_textview);
        daily_limit_textview = (TextView)findViewById(R.id.daily_limit_textview);
       // daily_exp_textview = (TextView)findViewById(R.id.daily_exp_textview);

        new PostBalance_TotalCredit_Task(balanceactivity).execute(MainActivity.LoginID);

        search_btn = (Button)findViewById(R.id.search_btn);
        search_btn.setAlpha(0.5f);
        search_btn.setEnabled(false);


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            datefrom=datefromedit.getText().toString();
            dateto=datetoedit.getText().toString();

            new PostBalance_SearchDate_Statement_Task(balanceactivity).execute();
            }
        });
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

         datefromedit.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0)
                {
                    checkinformation();
                }
             }

             @Override
             public void afterTextChanged(Editable s) {

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

        datetoedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0)
                {
                    checkinformation();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

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








    }
    public void checkinformation()
    {
        if(datefromedit.getText().toString().equals("")||datetoedit.getText().toString().equals(""))
        {
            search_btn.setEnabled(false);
            search_btn.setAlpha(0.5f);
        }
        else
        {
            search_btn.setEnabled(true);
            search_btn.setAlpha(1f);
        }
    }
}
