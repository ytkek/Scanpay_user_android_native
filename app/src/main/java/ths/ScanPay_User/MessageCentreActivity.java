package ths.ScanPay_User;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ths.ScanPay_User.GetFunction.GetFindMerchantListTask;
import ths.ScanPay_User.GetFunction.GetMessageListTask;

public class MessageCentreActivity extends AppCompatActivity {

    ImageView back;
    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messagecentre);


        recyclerView = (RecyclerView) findViewById(R.id.messagecentre_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        new GetMessageListTask(this, recyclerView).execute();

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
}
