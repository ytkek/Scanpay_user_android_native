package ths.ScanPay_UserV5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ths.ScanPay_UserV5.GetFunction.GetMessageListTask;

public class MessageCentreDetail extends AppCompatActivity {

    ImageView back;

    public String nob_id;
    public String nob_title;
    public String nob_message;
    public String nob_publishdate;

    public TextView date_title,title,message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messagecentre_detail);

        Intent intent = getIntent();

        nob_id = intent.getStringExtra("nob_id");
        nob_title=intent.getStringExtra("nob_title");
        nob_message=intent.getStringExtra("nob_message");
        nob_publishdate = intent.getStringExtra("nob_publishdate");
        date_title = (TextView)findViewById(R.id.date);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS");
        try {
            Date date = df.parse(nob_publishdate);
            df.applyPattern("yyyy/MM/dd hh:mm:ss aa");
            String result = df.format(date);

            date_title.setText(result);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //date.setText(nob_publishdate);
        title=(TextView)findViewById(R.id.title);
        title.setText(nob_title);
        message=(TextView)findViewById(R.id.message);
        message.setText(nob_message);





        back = (ImageView) findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                MessageCentreActivity.mAdapter = new MessageCentreAdapter(getApplicationContext(),GetMessageListTask.listMockData);

                MessageCentreActivity.recyclerView.setAdapter(MessageCentreActivity.mAdapter);

            }
        });

    }
}


