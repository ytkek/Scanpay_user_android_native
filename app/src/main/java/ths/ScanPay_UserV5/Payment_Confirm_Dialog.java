package ths.ScanPay_UserV5;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import ths.ScanPay_UserV5.PostFunction.PostPay_Confirm_Pay_Task;

public class Payment_Confirm_Dialog extends Dialog implements
        android.view.View.OnClickListener
{
    Activity c;
    String merchantname,amount;
    TextView payment_textview;
    EditText payment_amount;
    Button confirmbtn;
    ImageView x_btn;

    public Payment_Confirm_Dialog(Activity a,String merchantname,String amount) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.merchantname=merchantname;
        this.amount=amount;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.payment_confirm_dialog);
        x_btn=(ImageView)findViewById(R.id.close);
        x_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        payment_textview=(TextView)findViewById(R.id.payment_merchant);
        payment_textview.setText("Paid to Merchant "+merchantname);

        payment_amount=(EditText)findViewById(R.id.payment_amount);
        payment_amount.setText("Amount : RM "+amount);

        confirmbtn=(Button)findViewById(R.id.confirmbtn);
        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                new PostPay_Confirm_Pay_Task(c).execute(MainActivity.LoginID,PaymentScanQRActivity.merchantid,PaymentScanQRActivity.merchantname,PaymentScanQRActivity.type,PaymentScanQRActivity.amount_edit.getText().toString(),PaymentScanQRActivity.qrcode,PaymentScanQRActivity.lqrcode,MainActivity.LoginID,MainActivity.Password);
            }
        });

    }

        @Override
    public void onClick(View v) {

    }
}
