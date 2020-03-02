package ths.ScanPay_User;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import ths.ScanPay_User.Generic.Generic;
import ths.ScanPay_User.PostFunction.PostPay_CreditBalance_Task;
import ths.ScanPay_User.PostFunction.PostPay_Get_OTP_Task;
import ths.ScanPay_User.PostFunction.PostPay_MerchantInfo_Task;
import ths.ScanPay_User.PostFunction.PostPay_Validate_PinNumber_Task;

public class PaymentScanQRActivity extends AppCompatActivity {

    public final int CUSTOMIZED_REQUEST_CODE = 0x0000ffff;
    public static String type,merchantid,amount,lqrcode,qrcode,merchantname;
    public static boolean creditbalance_indicator,merchantinfo_indicator,dailyexplimit_indicator;

    public static String qr_amount;

    public static TextView checkdailylimit,merchant_name;
    public static EditText pin_edit,amount_edit,new_otp_edit;

    public static LinearLayout payment_layout,OTPlayout,set_new_Otp_layout,payment_result_layout;
    public static TextView otp_empty,otp_different;

    public static Button getnewotpbtn,resendotpbtn,saveotpbtn,payment_close_btn;
    public static TextView error_message,user_number;

    ImageView back_btn;

    Button confirm_btn;

    Activity PaymentScanQRActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymentscanqr_activity);

        PaymentScanQRActivity=this;
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("Scan a barcode");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setOrientationLocked(true);
        integrator.setBeepEnabled(true);
        integrator.setCaptureActivity(CaptureActivityPortrait.class);
        integrator.initiateScan();

        back_btn=(ImageView) findViewById(R.id.backbtn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                clear();

            }
        });

        OTPlayout=(LinearLayout)findViewById(R.id.OTPlayout);
        OTPlayout.setVisibility(View.GONE);

        otp_empty=(TextView)findViewById(R.id.otp_empty_textview);
        otp_empty.setVisibility(View.GONE);

        otp_different=(TextView)findViewById(R.id.otp_different_textview);
        otp_different.setVisibility(View.GONE);

        getnewotpbtn=(Button)findViewById(R.id.getnewotpbtn);



        set_new_Otp_layout=(LinearLayout)findViewById(R.id.set_new_otp_layout);
        set_new_Otp_layout.setVisibility(View.GONE);

        resendotpbtn=(Button)findViewById(R.id.resendotpbtn);

        user_number=(TextView)findViewById(R.id.user_number);

        new_otp_edit=(EditText)findViewById(R.id.new_otp_edit);

        saveotpbtn = (Button)findViewById(R.id.saveotpbtn);
        saveotpbtn.setVisibility(View.INVISIBLE);
        payment_layout=(LinearLayout)findViewById(R.id.payment_layout);
        payment_layout.setVisibility(View.GONE);

        checkdailylimit= (TextView)findViewById(R.id.checkdailylimit);
        merchant_name = (TextView)findViewById(R.id.merchant_name);

        pin_edit=(EditText)findViewById(R.id.pin_edit);
        amount_edit=(EditText)findViewById(R.id.amount_edit);

        error_message=(TextView)findViewById(R.id.error_message);
        error_message.setVisibility(View.INVISIBLE);

        confirm_btn= (Button)findViewById(R.id.confirmbtn);
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(amount_edit.getText().length()==0 )
                {
                    Toast.makeText(PaymentScanQRActivity,"amount cant be empty",Toast.LENGTH_SHORT).show();
                }
                else if(pin_edit.getText().length()==0)
                {
                    Toast.makeText(PaymentScanQRActivity,"pin number cant be empty",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    new PostPay_Validate_PinNumber_Task(PaymentScanQRActivity).execute(MainActivity.LoginID,pin_edit.getText().toString());
                }



            }
        });

        payment_result_layout=(LinearLayout)findViewById(R.id.payment_result_layout);
        payment_result_layout.setVisibility(View.GONE);

        payment_close_btn=(Button)findViewById(R.id.payment_close_btn);
        payment_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                clear();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != CUSTOMIZED_REQUEST_CODE && requestCode != IntentIntegrator.REQUEST_CODE) {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        switch (requestCode) {
            case CUSTOMIZED_REQUEST_CODE: {
                Toast.makeText(this, "REQUEST_CODE = " + requestCode, Toast.LENGTH_LONG).show();
                break;
            }
            default:
                break;
        }

        IntentResult result = IntentIntegrator.parseActivityResult(resultCode, data);

        if(result.getContents() == null) {

        } else {

            new PostPay_Get_OTP_Task(PaymentScanQRActivity).execute(MainActivity.LoginID);

            new PostPay_CreditBalance_Task(this).execute(MainActivity.LoginID);
            try{
                String[] arrayString = result.getContents().split("\\|\\|");
                for (int a=0; a<arrayString.length;a++)
                {
                    if(arrayString.length==3)
                    {
                        type="pay";
                        merchantid=arrayString[0];
                        amount=arrayString[1];
                        qr_amount=amount;
                        lqrcode = arrayString[2];
                        new PostPay_MerchantInfo_Task(this).execute(type,merchantid,amount,lqrcode,qrcode);
                        Log.d("Scanned: " , "pay"+arrayString[a] );
                    }
                    else if(arrayString.length==2)
                    {
                        type="pay_cashier";
                        merchantid = arrayString[0];
                        qrcode=arrayString[1];
                        new PostPay_MerchantInfo_Task(this).execute(type,merchantid,amount,lqrcode,qrcode);
                        Log.d("Scanned: " , "cashier"+arrayString[a] );
                    }

                }
            }
            catch (Exception ex)
            {

            }



        }
    }
    public static void clear()
    {

        type="";
        merchantid="";
        amount="";
        lqrcode="";
        qrcode="";
        merchantname="";
        checkdailylimit.setText("");
        merchant_name.setText("");
        amount_edit.setText("");
        qr_amount="";
    }

}
