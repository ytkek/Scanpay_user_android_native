package ths.ScanPay_User;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ths.ScanPay_User.GetFunction.GetFindMerchantListTask;
import ths.ScanPay_User.GetFunction.GetMessageListTask;
import ths.ScanPay_User.MessageNotification.MessageNotification;
import ths.ScanPay_User.SqlLiteHelper.MessageDatabaseHelper;

public class MessageCentreActivity extends AppCompatActivity {

    ImageView back;
    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter mAdapter;
    public static MessageDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messagecentre);

        db =new MessageDatabaseHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.messagecentre_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        new GetMessageListTask(this, recyclerView).execute();




       // List<MessageNotification> lol2 = new ArrayList<>();

       // lol2.addAll(db.getAllMessage());







        //lol=db.getAllMessage();




       // List<MessageCentrelist> messagecentreist = new ArrayList<>();
       // messagecentreist.add(new MessageCentrelist("aaa","aaa","aaaa"));
        //messagecentreist.add(new MessageCentrelist("bbb","bbb","bbb"));
       // mAdapter = new MessageCentreAdapter(this,messagecentreist);

        //recyclerView.setAdapter(mAdapter);


        back = (ImageView) findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
    public void insertallindicator()
    {

    }
}
