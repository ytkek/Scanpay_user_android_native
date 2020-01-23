package ths.ScanPay_User;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BalanceActivity extends AppCompatActivity {

    ImageView backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balance);

         backbtn = (ImageView)findViewById(R.id.backbtn);
         backbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });

    }
}
