package ths.ScanPay_User;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DateDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    ImageView close;
    String data;
    DatePicker datepick;
    Button date_time_set;
    Integer monthplus = 1;



    public DateDialog(Activity a,String data) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.data=data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.date_time_picker);
        datepick = (DatePicker)findViewById(R.id.date_picker);

        date_time_set = (Button)findViewById(R.id.date_time_set);
        date_time_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Integer resultmonth= Integer.valueOf(datepick.getMonth());
                Integer allresultmonth =resultmonth+monthplus;
                if(data.equals("datefrom"))
                {
                    BalanceActivity.datefromedit.setText(datepick.getDayOfMonth()+"/"+allresultmonth+"/"+datepick.getYear());
                }
                if(data.equals("dateto"))
                {
                    BalanceActivity.datetoedit.setText(datepick.getDayOfMonth()+"/"+allresultmonth+"/"+datepick.getYear());
                }
                if(data.equals("dob"))
                {
                    EditProfileDialog.dobedit.setText(datepick.getDayOfMonth()+"/"+allresultmonth+"/"+datepick.getYear());
                }
                dismiss();


            }
        });











    }

    @Override
    public void onClick(View v) {

    }

}
