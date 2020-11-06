package ths.ScanPay_UserV5;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ths.ScanPay_UserV5.GetFunction.GetMessageListTask;
import ths.ScanPay_UserV5.MessageNotification.MessageNotification;
import ths.ScanPay_UserV5.SqlLiteHelper.MessageDatabaseHelper;

public class MessageCentreActivity extends AppCompatActivity {

    ImageView back;
    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter mAdapter;
    public static MessageDatabaseHelper db,backupdb;
    public static ArrayList<MessageNotification> arraylist_messagedb = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messagecentre);

        db =new MessageDatabaseHelper(this);
        backupdb = new MessageDatabaseHelper(this);


        recyclerView = (RecyclerView) findViewById(R.id.messagecentre_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        new GetMessageListTask(this, recyclerView).execute(MainActivity.LoginID,MainActivity.Password);




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
